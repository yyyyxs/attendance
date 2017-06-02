package com.jmhz.devicemanage.stock;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.DeliveryOutAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.InStockApi;
import com.jmhz.devicemanage.model.SaleOutItem;
import com.jmhz.devicemanage.session.SessionKeeper;
import com.uid.trace.common.schema.Paging;
import com.uid.trace.module.stock.schema.InOutStock;
import com.uid.trace.module.stock.schema.InOutStockMessage;
import com.zxing.activity.CaptureActivity;

public class DeliveryOutListActivity extends Activity implements
		OnItemClickListener, OnScrollListener {

	private ListView mLvSaleOut = null;
	private ArrayList<SaleOutItem> mSaleOutItems = new ArrayList<SaleOutItem>();
	private ArrayList<SaleOutItem> mSaleOutItems_search = new ArrayList<SaleOutItem>();
	private DeliveryOutAdapter mDeliveryOutAdapter = null;
	private Button mBtnSignOut = null;
	private Dialog dialog = null;
	private ImageButton mBtnSearch = null;
	private ImageButton mBtnClose = null;
	private EditText mEtContent = null;
	private InOutStock inOutStock = null;
	private Button btn_reload = null;
	private ImageView iv_blank = null;

	private int rowTotal = 0;
	private int mPage = 1;
	private int pageSize = 3;
	private boolean mLoading = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saleout_list);

		btn_reload = (Button) findViewById(R.id.bt_reload);
		iv_blank = (ImageView) findViewById(R.id.blank);
		btn_reload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					initListView();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		initView();
	}

	private void initView() {
		mLvSaleOut = (ListView) findViewById(R.id.lv_slae_out);

		mBtnSignOut = (Button) findViewById(R.id.btn_stock_sign_out);
		mBtnSignOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog = new SaleOutDialog(DeliveryOutListActivity.this,
						R.style.MyDialog);

				dialog.show();
				dialog.getWindow().findViewById(R.id.btn_dialog_close)
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								dialog.dismiss();
							}
						});
				dialog.getWindow().findViewById(R.id.ibtn_delivery_out)
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								Intent intent = new Intent();
								intent.setClass(DeliveryOutListActivity.this,
										DeliveryOutActivity.class);
								startActivity(intent);

							}
						});
				dialog.getWindow().findViewById(R.id.ibtn_return_out)
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								Intent intent = new Intent(
										DeliveryOutListActivity.this,
										CaptureActivity.class);
								Bundle bundle = new Bundle();
								bundle.putString("PageTitle", "退货出库");
								bundle.putString("operate", "退货出库");
								intent.putExtras(bundle);
								startActivity(intent);
							}
						});
			}
		});
		mBtnSearch = (ImageButton) findViewById(R.id.ibtn_sale_out_search);
		mBtnClose = (ImageButton) findViewById(R.id.ibtn_sale_out_close);
		mEtContent = (EditText) findViewById(R.id.et_sale_out_content);
		mBtnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(mEtContent.getText().toString())) {
					Toast.makeText(DeliveryOutListActivity.this, "单号为空！", 1)
							.show();
					return;
				}
				mSaleOutItems_search.clear();
				for (SaleOutItem si : mSaleOutItems) {
					if (mEtContent.getText().toString()
							.equals(si.getOrderNum())) {
						mSaleOutItems_search.add(si);
					}
				}
				Toast.makeText(DeliveryOutListActivity.this,
						"共" + mSaleOutItems_search.size() + "记录符合", 1).show();
				mDeliveryOutAdapter = new DeliveryOutAdapter(
						mSaleOutItems_search, DeliveryOutListActivity.this);
				mLvSaleOut.setOnItemClickListener(DeliveryOutListActivity.this);
				mLvSaleOut.setOnScrollListener(DeliveryOutListActivity.this);
				mLvSaleOut.setAdapter(mDeliveryOutAdapter);
			}
		});
		mBtnClose.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (TextUtils.isEmpty(mEtContent.getText().toString())) {
					return;
				}
				mEtContent.setText("");
				mDeliveryOutAdapter = new DeliveryOutAdapter(mSaleOutItems,
						DeliveryOutListActivity.this);
				mLvSaleOut.setOnItemClickListener(DeliveryOutListActivity.this);
				mLvSaleOut.setOnScrollListener(DeliveryOutListActivity.this);
				mLvSaleOut.setAdapter(mDeliveryOutAdapter);
			}
		});
		try {
			initListView();
			// SaleOutItem maSaleOutItem = new SaleOutItem("123456","正常出库",
			// "3452345234",
			// "王晓", "100", "福州", "2014-08-13 09:14:25");
			// SaleOutItem maSaleOutItem2 = new SaleOutItem("123456","正常出库",
			// "3452345234",
			// "王晓", "100", "福州", "2014-08-13 09:14:25");
			// SaleOutItem maSaleOutItem3 = new SaleOutItem("123456","正常出库",
			// "3452345234",
			// "王晓", "100", "福州", "2014-08-13 09:14:25");
			// SaleOutItem maSaleOutItem4 = new SaleOutItem("123456","正常出库",
			// "3452345234",
			// "王晓", "100", "福州", "2014-08-13 09:14:25");
			// SaleOutItem maSaleOutItem5 = new SaleOutItem("123456","正常出库",
			// "3452345234",
			// "王晓", "100", "福州", "2014-08-13 09:14:25");
			// mSaleOutItems.add(maSaleOutItem);
			// mSaleOutItems.add(maSaleOutItem2);
			// mSaleOutItems.add(maSaleOutItem3);
			// mSaleOutItems.add(maSaleOutItem4);
			// mSaleOutItems.add(maSaleOutItem5);
			// mDeliveryOutAdapter = new SaleOutAdapter(mSaleOutItems,
			// DeliveryOutListActivity.this);
			// mLvSaleOut.setOnItemClickListener(DeliveryOutListActivity.this);
			// mLvSaleOut.setOnScrollListener(DeliveryOutListActivity.this);
			// mLvSaleOut.setAdapter(mDeliveryOutAdapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initListView() throws Exception {
		inOutStock = new InOutStock();
		inOutStock.setCtype("3");// 出库
		inOutStock.setStockType("2");// 调货出库
		Paging pag = new Paging();
		pag.setPage(mPage);
		pag.setPageSize(pageSize);
		inOutStock.setPaging(pag);
		new InStockApi(this).getInStockRecord(inOutStock, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				InOutStockMessage iosm = (InOutStockMessage) o;
				List<InOutStock> ioss = iosm.getMessageBody()
						.getInOutStockList();
				rowTotal = iosm.getMessageBody().getPaging().getRowTotal();
				for (InOutStock inOutStock : ioss) {
					SaleOutItem maSaleOutItem = new SaleOutItem(inOutStock.getCid(),inOutStock
							.getStockNumber(), inOutStock.getStockTypeName(),
							inOutStock.getOrderNumber(),  SessionKeeper.getEmail(DeliveryOutListActivity.this), inOutStock.getCount(),
							inOutStock.getToNodeName(), inOutStock
									.getCreateTime());
					mSaleOutItems.add(maSaleOutItem);
				}
				mDeliveryOutAdapter = new DeliveryOutAdapter(mSaleOutItems,
						DeliveryOutListActivity.this);
				mLvSaleOut.setOnItemClickListener(DeliveryOutListActivity.this);
				mLvSaleOut.setOnScrollListener(DeliveryOutListActivity.this);
				mLvSaleOut.setAdapter(mDeliveryOutAdapter);

				btn_reload.setVisibility(View.GONE);
				iv_blank.setVisibility(View.GONE);
				if (mSaleOutItems.size() <= 0) {
					iv_blank.setVisibility(View.VISIBLE);
					btn_reload.setVisibility(View.VISIBLE);
				}
				Toast.makeText(DeliveryOutListActivity.this,
						"获取" + mSaleOutItems.size() + "条数据,共" + rowTotal,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				Toast.makeText(DeliveryOutListActivity.this,
						"请求失败！" + errorMsg, 1).show();
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mDeliveryOutAdapter.clear();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, OutStockRecodeActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("cid", mSaleOutItems.get(arg2).getCid());
		bundle.putString("stocknumber", mSaleOutItems.get(arg2).getOrderNum());
		bundle.putString("transactorname", mSaleOutItems.get(arg2).getTransactorName());
		bundle.putString("stocktypename", mSaleOutItems.get(arg2).getOrderType());
		bundle.putString("stockdata", mSaleOutItems.get(arg2).getData());
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				if (rowTotal < pageSize) {
					Toast.makeText(DeliveryOutListActivity.this, "没有更多数据！", 1)
							.show();
					return;
				}

				if (rowTotal > mSaleOutItems.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						inOutStock = new InOutStock();
						inOutStock.setCtype("3");// 出库
						inOutStock.setStockType("2");// 调货出库
						Paging pag = new Paging();
						pag.setPage(mPage);
						pag.setPageSize(pageSize);
						inOutStock.setPaging(pag);
						try {
							getMoreNode(inOutStock);
						} catch (Exception e) {
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						Toast.makeText(DeliveryOutListActivity.this,
								"加载中，请稍后！", 1).show();
					}
				} else {
					Toast.makeText(DeliveryOutListActivity.this, "没有更多数据！", 1)
							.show();
				}
			}
		}
	}

	private void getMoreNode(InOutStock inOutStock) throws Exception {
		new InStockApi(this).getInStockRecord(inOutStock, new HttpCallback() {
			@Override
			public void onSuccess(Object o) {
				InOutStockMessage iosm = (InOutStockMessage) o;
				InOutStock ios = iosm.getMessageBody();
				List<InOutStock> mResult = ios.getInOutStockList();

				for (InOutStock result : mResult) {
					SaleOutItem maSaleOutItem = new SaleOutItem(result.getCid(),result
							.getStockNumber(), result.getStockTypeName(), result.getOrderNumber(),
							 SessionKeeper.getEmail(DeliveryOutListActivity.this), result.getCount(), result
									.getToNodeName(), result.getCreateTime());
					mSaleOutItems.add(maSaleOutItem);
				}
				mDeliveryOutAdapter.notifyDataSetChanged();
				mLoading = false;
				Toast.makeText(
						DeliveryOutListActivity.this,
						"再获取" + mResult.size() + "条数据,共" + mSaleOutItems.size(),
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				Toast.makeText(DeliveryOutListActivity.this,
						"请求失败！" + errorMsg, 1).show();
				mLoading = false;
				mPage -= 1;
			}
		});
	}
}
