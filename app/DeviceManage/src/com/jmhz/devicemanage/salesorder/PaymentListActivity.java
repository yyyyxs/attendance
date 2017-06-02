package com.jmhz.devicemanage.salesorder;
/**
 * 已支付
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.PaymentAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.SalesOrderApi;
import com.jmhz.devicemanage.model.PaymentItem;
import com.jmhz.devicemanage.ordermessage.OrderDetailsActivity;
import com.uid.trace.common.schema.Paging;
import com.uid.trace.module.sale.schema.OrderDetail;
import com.uid.trace.module.sale.schema.OrderDetailMessage;
import com.uid.trace.module.sale.schema.OrderStatus;


public class PaymentListActivity extends Activity implements
		OnItemClickListener, OnScrollListener {
	private ListView mPayments = null;
	private PaymentAdapter mAdapter = null;
	private int rowTotal = 0;
	private ArrayList<PaymentItem> rowResult = new ArrayList<PaymentItem>();
	private boolean mLoading = false;
	private int mPage = 1;
	private int pageSize = 10;
	private Button btn_reload = null;
	private ImageView iv_blank = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_salas_payment_list);
		mPayments = (ListView) findViewById(R.id.payment_list);
//		mPayments.setItemsCanFocus(true);
		mPayments.setOnItemClickListener(this);
		mPayments.setOnScrollListener(this);
		mAdapter = new PaymentAdapter(rowResult,
				PaymentListActivity.this);
		mPayments.setAdapter(mAdapter);
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
		try {
			initListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initListView() throws Exception {
		// 调用接口，获取数据
		OrderDetail od = new OrderDetail();
		Paging p = new Paging();
		p.setPage(mPage);
		p.setPageSize(pageSize);
		od.setPaging(p);
		od.setOrderStatus(OrderStatus.HAS_PAID.ordinal() + "");
		getList(od);
	}
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent event) {
//		// TODO Auto-generated method stub
//		return true;
//	}

	private void getList(OrderDetail od) throws Exception {

		new SalesOrderApi(this).getListByStatus(od, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				OrderDetailMessage odm = (OrderDetailMessage) o;
				List<OrderDetail> ods = odm.getMessageBody().getListData();
				Paging paging = odm.getMessageBody().getPaging();
				rowTotal = paging.getRowTotal();

				for (OrderDetail od : ods) {
					PaymentItem paymentItem = new PaymentItem(
							od.getOrderSn(), 
							od.getOrderAddTime(),
							od.getOrderAmount(), 
							od.getLogistics().getShippingName());
					
					rowResult.add(paymentItem);

				}
				mAdapter.addItems(rowResult);
				btn_reload.setVisibility(View.GONE);
				if (ods.size() <= 0) {
					iv_blank.setVisibility(View.VISIBLE);
					btn_reload.setVisibility(View.VISIBLE);
				}
				Toast.makeText(PaymentListActivity.this,
						"获取" + ods.size() + "条数据,总共" + rowTotal,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				Toast.makeText(PaymentListActivity.this, errorMsg,
						Toast.LENGTH_SHORT).show();
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mAdapter != null) {
			mAdapter.clear();
		}

	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		Intent intent = new Intent(PaymentListActivity.this,
				OrderDetailsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("orderSn", rowResult.get(arg2).getOrderId());
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// Log.i("cfq", "onScroll");
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				if (rowTotal < pageSize) {
					Toast.makeText(PaymentListActivity.this, "没有更多数据！",
							Toast.LENGTH_SHORT).show();
					return;
				}

				// 表示说 还有数据
				if (rowTotal > rowResult.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						OrderDetail od = new OrderDetail();
						Paging p = new Paging();
						p.setPage(mPage);
						p.setPageSize(pageSize);
						od.setPaging(p);
						od.setOrderStatus(OrderStatus.HAS_PAID.ordinal() + "");
						try {
							getMoreList(od);
						} catch (Exception e) {
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						Toast.makeText(PaymentListActivity.this, "加载中，请稍后！",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(PaymentListActivity.this, "没有更多数据！",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private void getMoreList(OrderDetail od) throws Exception {
		new SalesOrderApi(this).getListByStatus(od, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				OrderDetailMessage odm = (OrderDetailMessage) o;
				List<OrderDetail> ods = odm.getMessageBody().getListData();
				for (OrderDetail od : ods) {
					PaymentItem paymentItem = new PaymentItem(od
							.getOrderSn(), od.getOrderAddTime(), od
							.getOrderAmount(), "滚动加载");
					rowResult.add(paymentItem);
				}
				Toast.makeText(PaymentListActivity.this,
						"再获取" + ods.size() + "条数据,总共" + rowResult.size(),
						Toast.LENGTH_SHORT).show();
//				mAdapter = new PaymentAdapter(rowResult,
//						PaymentListActivity.this);
//				mPayments.setOnItemClickListener(PaymentListActivity.this);
//				mPayments.setOnScrollListener(PaymentListActivity.this);
//				mPayments.setAdapter(mAdapter);
				mAdapter.notifyDataSetChanged();
				mLoading = false;
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

				Toast.makeText(PaymentListActivity.this, errorMsg,
						Toast.LENGTH_SHORT).show();
				mLoading = false;
				mPage -= 1;
			}
		});
	}



}
