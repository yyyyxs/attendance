package com.jmhz.devicemanage.waitExit;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.WaitExitAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.OutStockApi;
import com.jmhz.devicemanage.model.WaitExitItem;
import com.jmhz.devicemanage.stock.StockManagementActivity;
import com.uid.trace.module.stock.schema.NodeStockUid;
import com.uid.trace.module.stock.schema.TransportNodeStock;
import com.uid.trace.module.stock.schema.TransportNodeStockMessage;
import com.zxing.activity.CaptureActivity;

public class WaitExitListActivity extends BaseActivity implements
		OnItemClickListener, OnScrollListener {

	/**
	 * 待出库列表的详细信息
	 */
	private ListView mLvWaitExit = null;
	private WaitExitAdapter mWaitExitAdapter = null;
	// private WaitExitItem mWaitExitItem = null;
	private ArrayList<WaitExitItem> mWaitExitList = new ArrayList<WaitExitItem>();
	private String ucode = null;
	private String operate = null;
	private String orderSn = null;
	private String nodeId = null;
	private Button continueScanBtn = null;
	private ImageView ivBlank = null;
	public static final int SCAN_GOOD = 1;
	TransportNodeStock tns = null;
	List<NodeStockUid> nlist = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_exit_list);
		initializeMemberVariable(findViewById(R.id.mainHead));
		mLvWaitExit = (ListView) findViewById(R.id.lv_wait_exit);
		continueScanBtn = (Button) findViewById(R.id.btn_continue_scan);
		ivBlank = (ImageView) findViewById(R.id.blank);
		Bundle bundle = this.getIntent().getExtras();
		operate = bundle.getString("operate");

		if ("扫描配送".equals(operate)) {
			orderSn = bundle.getString("orderSn");
			toastShort(operate + "，订单号：" + orderSn);
		} else if ("调货出库".equals(operate)) {
			nodeId = bundle.getString("nodeId");
			toastShort(operate + "，配送点：" + nodeId);
		} else if ("退货出库".equals(operate)) {
			toastShort(operate);
		}

		initializeActivityHead();
		initWaitList();
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.wait_exit_list);
		rightButton.setText(R.string.submit);
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				try {
					batchOutStock();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivityWithClass(EnterWarehouseListActivity.class);
				finish();
			}
		});
	}

	protected void batchOutStock() throws Exception {
		if (mWaitExitList.size() <= 0) {
			toastShort("列表为空，请扫描！");
			return;
		}
		// 从mWaitList中获取数据 begin
		tns = new TransportNodeStock();
		// 销售出库
		if ("扫描配送".equals(operate)) {
			tns.setStockType("1");
//			toastShort("setStockType(1)");
			tns.setOrderNumber(orderSn);
		} else if ("调货出库".equals(operate)) {
			tns.setStockType("2");
//			toastShort("setStockType(2)");
			tns.setToNodeId(nodeId);
		} else if ("退货出库".equals(operate)) {
			tns.setStockType("3");
//			toastShort("setStockType(3)");
		}

		nlist = new ArrayList<NodeStockUid>();
		for (WaitExitItem wdItem : mWaitExitList) {
			for (NodeStockUid nsu : wdItem.getNsus()) {
				nlist.add(nsu);
			}
		}
		tns.getUidList().addAll(nlist);
		new OutStockApi(this).outStock(tns, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				toastShort("出库成功！");
				mWaitExitAdapter.clear();
				mWaitExitList.clear();
				ivBlank.setVisibility(View.VISIBLE);
				
				if ("扫描配送".equals(operate)) {
					Intent intent = new Intent(WaitExitListActivity.this,
							StockManagementActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("operate", operate);
					intent.putExtras(bundle);
					startActivity(intent);
				} else if ("调货出库".equals(operate)) {
					Intent intent = new Intent(WaitExitListActivity.this,
							StockManagementActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("operate", operate);
					intent.putExtras(bundle);
					startActivity(intent);
				} else if ("退货出库".equals(operate)) {
					Intent intent = new Intent(WaitExitListActivity.this,
							StockManagementActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("operate", operate);
					intent.putExtras(bundle);
					startActivity(intent);
				}
				finish();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort("出库失败！" + errorMsg);
			}
		});
	}

	private void initWaitList() {
		continueScanBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(WaitExitListActivity.this,
						CaptureActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("PageTitle", "出库扫描");
				bundle.putString("operate", "出库扫描");
				intent.putExtras(bundle);
				startActivityForResult(intent, SCAN_GOOD);// TODO Auto-generated
															// method stub

			}
		});
		Bundle bundle = this.getIntent().getExtras();
		ucode = bundle.getString("ucode");
//		toastShort(ucode);

		try {
			getNodeProductForResult(ucode);

			// WaitExitItem mExitItem = new WaitExitItem("起得大米", 100, ucode,
			// null);
			// mWaitExitList.add(mExitItem);
			// mWaitExitAdapter = new WaitExitAdapter(mWaitExitList, this);
			// mLvWaitExit.setAdapter(mWaitExitAdapter);
			// mLvWaitExit.setOnItemClickListener(this);
			// mLvWaitExit.setOnScrollListener(this);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getNodeProductForResult(String ucode2) throws Exception {

		TransportNodeStock tns = new TransportNodeStock();
		tns.setUidCode(ucode);

		new OutStockApi(this).getNodeProductForUidCode(tns, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				ivBlank.setVisibility(View.GONE);
				TransportNodeStockMessage tnsm = (TransportNodeStockMessage) o;
				TransportNodeStock tns = tnsm.getMessageBody();
				WaitExitItem mExitItem = new WaitExitItem(tns.getProName(),
						Integer.parseInt(tns.getUidNumber()), tns.getUidCode(), tns.getUidList());
				mWaitExitList.add(mExitItem);
				mWaitExitAdapter = new WaitExitAdapter(mWaitExitList,
						WaitExitListActivity.this);
				mLvWaitExit.setAdapter(mWaitExitAdapter);
				mLvWaitExit.setOnItemClickListener(WaitExitListActivity.this);
				mLvWaitExit.setOnScrollListener(WaitExitListActivity.this);

			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				toastShort(errorMsg);
				ivBlank.setVisibility(View.VISIBLE);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			if (requestCode == SCAN_GOOD) {
				ucode = bundle.getString("ucode");
				for (WaitExitItem wItem : mWaitExitList) {
					if (wItem.getCommodityUnum().equals(ucode)) {
						toastShort("该码已经扫描,已在列表中！");
						return;
					}
				}
				TransportNodeStock tns = new TransportNodeStock();
				tns.setUidCode(ucode);
				try {
					new OutStockApi(this).getNodeProductForUidCode(tns,
							new HttpCallback() {

								@Override
								public void onSuccess(Object o) {
									ivBlank.setVisibility(View.GONE);
									TransportNodeStockMessage tnsm = (TransportNodeStockMessage) o;
									TransportNodeStock tns = tnsm
											.getMessageBody();
									WaitExitItem mExitItem = new WaitExitItem(
											tns.getProName(), Integer.parseInt(tns.getUidNumber()), tns
													.getUidCode(), tns
													.getUidList());
									mWaitExitList.add(mExitItem);
									// mWaitDeliveryAdapter = new
									// WaitDeliveryAdapter(mWaitList,
									// WaitDeliveryListActivity.this);
									// mLvWaitDelivery.setAdapter(mWaitDeliveryAdapter);
									// mLvWaitDelivery
									// .setOnItemClickListener(WaitDeliveryListActivity.this);
									// mLvWaitDelivery
									// .setOnScrollListener(WaitDeliveryListActivity.this);
									mWaitExitAdapter.notifyDataSetChanged();
								}

								@Override
								public void onFail(String errorCode,
										String errorMsg) {

									toastShort(errorMsg);
								}
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			WaitExitListActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public void setBlankView() {

		ivBlank.setVisibility(View.VISIBLE);
	}

	public void outStock(final WaitExitItem waitExitItem) throws Exception {

		tns = new TransportNodeStock();
		// 销售出库
		if ("扫描配送".equals(operate)) {
			tns.setStockType("1");
//			toastShort("setStockType(1)");
			tns.setOrderNumber(orderSn);
		} else if ("调货出库".equals(operate)) {
			tns.setStockType("2");
//			toastShort("setStockType(2)");
			tns.setToNodeId(nodeId);
		} else if ("退货出库".equals(operate)) {
			tns.setStockType("3");
//			toastShort("setStockType(3)");
		}
		nlist = new ArrayList<NodeStockUid>();
		for (NodeStockUid nodeStockUid : waitExitItem.getNsus()) {
			nlist.add(nodeStockUid);
		}
		tns.getUidList().addAll(nlist);

		new OutStockApi(this).outStock(tns, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				toastShort("出库成功！");
				mWaitExitAdapter.remove(waitExitItem);
				if (mWaitExitAdapter.mData.size() <= 0) {
					setBlankView();
				}
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort("出库失败！" + errorMsg);
			}
		});
	}
}
