package com.jmhz.devicemanage.ordermessage;

/**
 * 订单配送超时消息
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.jmhz.devicemanage.adapter.ExpiredOrderListAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.OrderMessageApi;
import com.jmhz.devicemanage.model.NewOrderItem;
import com.uid.trace.common.schema.Paging;
import com.uid.trace.module.sale.schema.OrderMessage;
import com.uid.trace.module.sale.schema.OrderMessageParameter;

public class ExpiredOrderListActivity extends Activity implements
		OnItemClickListener, OnScrollListener {
	private ListView mNewOrders = null;
	private ExpiredOrderListAdapter mAdapter = null;
	// private int sum = 0;
	private ArrayList<NewOrderItem> rowResult = new ArrayList<NewOrderItem>();
	private boolean mLoading = false;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private Button btn_reload = null;
	private ImageView iv_blank = null;
	public static final int BACK_POSITION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_neworder_list);
		mNewOrders = (ListView) findViewById(R.id.neworder_list);

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

	@Override
	protected void onResume() {
		if (mAdapter != null) {
			mAdapter.notifyDataSetChanged();
		}

		super.onResume();
	}

	@Override
	protected void onRestart() {
		mAdapter.notifyDataSetChanged();
		super.onRestart();
	}

	private void initListView() throws Exception {

		OrderMessage om = new OrderMessage();
		Paging p = new Paging();
		p.setPage(mPage);
		p.setPageSize(pageSize);
		om.setPaging(p);
		om.setIsExpired(true);
		new OrderMessageApi(this).getOrderMessageList(om, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				iv_blank.setVisibility(View.GONE);
				OrderMessageParameter omp = (OrderMessageParameter) o;
				List<OrderMessage> oms = omp.getMessageBody().getListData();
				Paging paging = omp.getMessageBody().getPaging();
				rowTotal = paging.getRowTotal();

				for (OrderMessage om : oms) {
					NewOrderItem newOrderItem = new NewOrderItem(om
							.getContent(), om.getPushTime(), om.getReceiver(),
							om.getOrderSn(), om.getIsRead());
					rowResult.add(newOrderItem);

				}

				mAdapter = new ExpiredOrderListAdapter(rowResult,
						ExpiredOrderListActivity.this);
				mNewOrders
						.setOnItemClickListener(ExpiredOrderListActivity.this);
				mNewOrders.setOnScrollListener(ExpiredOrderListActivity.this);
				mNewOrders.setAdapter(mAdapter);
				btn_reload.setVisibility(View.GONE);
				if (oms.size() <= 0) {
					iv_blank.setVisibility(View.VISIBLE);
					btn_reload.setVisibility(View.VISIBLE);
				}
				Toast.makeText(ExpiredOrderListActivity.this,
						"获取" + oms.size() + "条数据", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

				Toast.makeText(ExpiredOrderListActivity.this,
						  errorMsg, Toast.LENGTH_SHORT).show();
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mAdapter.clear();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		Intent intent = new Intent(ExpiredOrderListActivity.this,
				OrderMessageDetailsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("orderSn", rowResult.get(position).getOrderSn());
		bundle.putInt("position", position);
		intent.putExtras(bundle);
		startActivityForResult(intent, BACK_POSITION);
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
					Toast.makeText(ExpiredOrderListActivity.this, "没有更多数据！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				// 表示说 还有数据
				if (rowTotal > rowResult.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						OrderMessage om = new OrderMessage();
						Paging p = new Paging();
						p.setPage(mPage);
						p.setPageSize(pageSize);
						om.setPaging(p);
						om.setIsExpired(true);
						try {
							getMoreList(om);
						} catch (Exception e) {
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						Toast.makeText(ExpiredOrderListActivity.this,
								"加载中，请稍后！", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(ExpiredOrderListActivity.this, "没有更多数据！",
							Toast.LENGTH_SHORT).show();
				}

			}

		}
	}

	private void getMoreList(OrderMessage om) throws Exception {

		new OrderMessageApi(this).getOrderMessageList(om, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				OrderMessageParameter omp = (OrderMessageParameter) o;
				List<OrderMessage> oms = omp.getMessageBody().getListData();
				Paging paging = omp.getMessageBody().getPaging();
				rowTotal = paging.getRowTotal();

				for (OrderMessage om : oms) {
					NewOrderItem newOrderItem = new NewOrderItem(om
							.getContent(), om.getPushTime(), om.getReceiver(),
							om.getOrderSn(), om.getIsRead());
					rowResult.add(newOrderItem);
				}
				Toast.makeText(ExpiredOrderListActivity.this,
						"rowResult:" + rowResult.size(), Toast.LENGTH_SHORT)
						.show();

				// mAdapter = new ExpiredOrderListAdapter(rowResult,
				// ExpiredOrderListActivity.this);
				// mNewOrders.setOnItemClickListener(ExpiredOrderListActivity.this);
				// mNewOrders.setOnScrollListener(ExpiredOrderListActivity.this);
				// mNewOrders.setAdapter(mAdapter);
				mAdapter.notifyDataSetChanged();
				mLoading = false;
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

				Toast.makeText(ExpiredOrderListActivity.this,
						errorMsg, Toast.LENGTH_SHORT).show();
				mLoading = false;
				mPage -= 1;
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			int position = bundle.getInt("position", -1);
			if (requestCode == BACK_POSITION) {
				if (position != -1) {
					rowResult.get(position).setIsRead(1);
					Log.i("cfq", rowResult.get(position).getIsRead() + "");
					mAdapter.notifyDataSetChanged();
				}

			}
		}
	}

}
