package com.jmhz.devicemanage.enter;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.WaitDeliveryAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.InStockApi;
import com.jmhz.devicemanage.model.WaitDeliveryItem;
import com.uid.trace.module.stock.schema.NodeStockUid;
import com.uid.trace.module.stock.schema.TransportNodeStock;
import com.uid.trace.module.stock.schema.TransportNodeStockMessage;
import com.zxing.activity.CaptureActivity;

public class WaitDeliveryListActivity extends BaseActivity implements
		OnItemClickListener, OnScrollListener {

	/**
	 * 待入库列表的详细信息
	 */
	private ListView mLvWaitDelivery = null;
	private WaitDeliveryAdapter mWaitDeliveryAdapter = null;
	// private WaitDeliveryItem mWaitDeliveryItem = null;
	private ArrayList<WaitDeliveryItem> mWaitList = new ArrayList<WaitDeliveryItem>();
	private String ucode = null;
	private Button continueScanBtn = null;
	private ImageView ivBlank = null;
	public static final int SCAN_GOOD = 1;
	TransportNodeStock tns = null;
	List<NodeStockUid> nlist = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wait_delivery_list);
		initializeMemberVariable(findViewById(R.id.mainHead));
		mLvWaitDelivery = (ListView) findViewById(R.id.lv_wait_delivery);
		continueScanBtn = (Button) findViewById(R.id.btn_continue_scan);
		ivBlank = (ImageView) findViewById(R.id.blank);
		initializeActivityHead();
		initWaitList();
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.wait_delivery_list);
		rightButton.setText(R.string.submit);
		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					inStock();
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

	protected void inStock() throws Exception {
		if (mWaitList.size() <= 0) {
			toastShort("列表为空，请扫描！");
			return;
		}
		// 从mWaitList中获取数据 begin
		tns = new TransportNodeStock();
		tns.setStockType("1");
		nlist = new ArrayList<NodeStockUid>();
		for (WaitDeliveryItem wdItem : mWaitList) {
			for (NodeStockUid nsu : wdItem.getNsus()) {
				nlist.add(nsu);
			}
		}
		// 从mWaitList中获取数据 over
		// tns = new TransportNodeStock();
		// tns.setStockType("1");
		// nlist = new ArrayList<NodeStockUid>();
		// NodeStockUid n = new NodeStockUid();
		// n.setUidCode("15261002610000000011");
		// nlist.add(n);
		// NodeStockUid n2 = new NodeStockUid();
		// n2.setUidCode("11851002610000000012");
		// nlist.add(n2);

		tns.getUidList().addAll(nlist);
		new InStockApi(this).inStock(tns, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				toastShort("入库成功！");
				mWaitDeliveryAdapter.clear();
				mWaitList.clear();
				ivBlank.setVisibility(View.VISIBLE);
				finish();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort("入库失败！" + errorMsg);
			}
		});
	}

	private void initWaitList() {
		continueScanBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(WaitDeliveryListActivity.this,
						CaptureActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("PageTitle", "入库扫描");
				bundle.putString("operate", "再次入库扫描");
				intent.putExtras(bundle);
				startActivityForResult(intent, SCAN_GOOD);// TODO Auto-generated
															// method stub

			}
		});
		Bundle bundle = this.getIntent().getExtras();
		ucode = bundle.getString("ucode");
		Log.i("cfq", "get:"+ucode);
		try {
			/*
			 * 假数据 WaitDeliveryItem mDeliveryItem = new WaitDeliveryItem("test",
			 * "12", "654331"); mWaitList.add(mDeliveryItem);
			 * mWaitDeliveryAdapter = new WaitDeliveryAdapter(mWaitList,
			 * WaitDeliveryListActivity.this);
			 * mLvWaitDelivery.setAdapter(mWaitDeliveryAdapter); mLvWaitDelivery
			 * .setOnItemClickListener(WaitDeliveryListActivity.this);
			 * mLvWaitDelivery
			 * .setOnScrollListener(WaitDeliveryListActivity.this);
			 */

			getNodeProductForResult(ucode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getNodeProductForResult(String ucode) throws Exception {

		TransportNodeStock tns = new TransportNodeStock();
		tns.setUidCode(ucode);
		new InStockApi(this).getNodeProductForUidCode(tns, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				Log.i("cfq", "onSuccess1"); 
				ivBlank.setVisibility(View.GONE);
				TransportNodeStockMessage tnsm = (TransportNodeStockMessage) o;
				TransportNodeStock tns = tnsm.getMessageBody();
				WaitDeliveryItem mDeliveryItem = new WaitDeliveryItem(tns
						.getProName(), tns.getProSpec(), tns.getUidCode(), tns
						.getUidList());
				mWaitList.add(mDeliveryItem);
				mWaitDeliveryAdapter = new WaitDeliveryAdapter(mWaitList,
						WaitDeliveryListActivity.this);
				mLvWaitDelivery.setAdapter(mWaitDeliveryAdapter);
				mLvWaitDelivery
						.setOnItemClickListener(WaitDeliveryListActivity.this);
				mLvWaitDelivery
						.setOnScrollListener(WaitDeliveryListActivity.this);
				Log.i("cfq", "onSuccess2");
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

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
				//判断码已经在list中
				for (WaitDeliveryItem wItem : mWaitList) {
					if (wItem.getCommodityUnum().equals(ucode)) {
						toastShort("该码已经扫描,已在列表中！");
						return;
					}
				}
				
				/*
				 * 假数据 WaitDeliveryItem mDeliveryItem = new
				 * WaitDeliveryItem("test", "12", "654331");
				 * mWaitList.add(mDeliveryItem);
				 */
				// 实际代码
				TransportNodeStock tns = new TransportNodeStock();
				tns.setUidCode(ucode);
				try {
					new InStockApi(this).getNodeProductForUidCode(tns,
							new HttpCallback() {

								@Override
								public void onSuccess(Object o) {
									ivBlank.setVisibility(View.GONE);
									TransportNodeStockMessage tnsm = (TransportNodeStockMessage) o;
									TransportNodeStock tns = tnsm
											.getMessageBody();
									WaitDeliveryItem mDeliveryItem = new WaitDeliveryItem(
											tns.getProName(), tns.getProSpec(),
											tns.getUidCode(), tns.getUidList());
									mWaitList.add(mDeliveryItem);
									// mWaitDeliveryAdapter = new
									// WaitDeliveryAdapter(mWaitList,
									// WaitDeliveryListActivity.this);
									// mLvWaitDelivery.setAdapter(mWaitDeliveryAdapter);
									// mLvWaitDelivery
									// .setOnItemClickListener(WaitDeliveryListActivity.this);
									// mLvWaitDelivery
									// .setOnScrollListener(WaitDeliveryListActivity.this);
									mWaitDeliveryAdapter.notifyDataSetChanged();
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
			WaitDeliveryListActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public void setBlankView() {

		ivBlank.setVisibility(View.VISIBLE);
	}
}
