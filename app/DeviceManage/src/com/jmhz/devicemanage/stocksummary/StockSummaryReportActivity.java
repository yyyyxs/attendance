package com.jmhz.devicemanage.stocksummary;

/**
 * 库存统计报表
 */
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.KeyEvent;
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
import com.jmhz.devicemanage.adapter.StockSummaryReportAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.InStockApi;
import com.jmhz.devicemanage.model.StockSummaryReportItem;
import com.uid.trace.common.schema.Paging;
import com.uid.trace.module.stock.schema.NodeStockUid;
import com.uid.trace.module.stock.schema.TransportNodeStock;
import com.uid.trace.module.stock.schema.TransportNodeStockMessage;

public class StockSummaryReportActivity extends BaseActivity implements
		OnScrollListener, OnItemClickListener {
	private ListView mLvStock = null;
	private StockSummaryReportAdapter mAdapter = null;
	private Button btn_reload = null;
	private ImageView iv_blank = null;
	private ArrayList<StockSummaryReportItem> mListResultItem = new ArrayList<StockSummaryReportItem>();
	TransportNodeStock tns = null;
	List<NodeStockUid> nlist = null;
	// private String ucode = null;
	private List<TransportNodeStock> mResult = null;

	private int rowTotal = 0;
	private int mPage = 1;
	private int pageSize = 10;
	private boolean mLoading = false;

	// private int sum = 0;
	// private boolean mLoading = false;
	// private int mPage = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stocksummary_report_list);
		initializeMemberVariable(findViewById(R.id.mainHead));
		mLvStock = (ListView) findViewById(R.id.stocksummaryreport_list);
		btn_reload = (Button) findViewById(R.id.bt_reload);
		iv_blank = (ImageView) findViewById(R.id.blank);
		btn_reload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				initListView();
			}
		});

		initListView();
	}

	protected void nodeStock() throws Exception {
		tns = new TransportNodeStock();
		Paging pag = new Paging();
		pag.setPage(mPage);
		pag.setPageSize(pageSize);
		tns.setPaging(pag);

		new InStockApi(this).nodeStock(tns, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				TransportNodeStockMessage tnsm = (TransportNodeStockMessage) o;
				TransportNodeStock tns = tnsm.getMessageBody();
				mResult = tns.getNodeStockList();
				rowTotal = tns.getPaging().getRowTotal();
				for (TransportNodeStock result : mResult) {
					StockSummaryReportItem mStockItem = new StockSummaryReportItem(
							result.getProName(), result.getUidNumber(), result
									.getProSpec(), result.getProPrice());
					mListResultItem.add(mStockItem);
				}
				mAdapter = new StockSummaryReportAdapter(mListResultItem,
						StockSummaryReportActivity.this);
				mLvStock.setAdapter(mAdapter);
				mLvStock.setOnItemClickListener(StockSummaryReportActivity.this);
				mLvStock.setOnScrollListener(StockSummaryReportActivity.this);

				btn_reload.setVisibility(View.GONE);
				iv_blank.setVisibility(View.GONE);
				if (mResult.size() <= 0) {
					iv_blank.setVisibility(View.VISIBLE);
					btn_reload.setVisibility(View.VISIBLE);
				}
				toastShort("获取" + mResult.size() + "条数据,共"+rowTotal);
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort("请求失败！"+ errorMsg);
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});

	}

	private void initListView() {
		try {
			nodeStock();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.stock_summary_report);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivityWithClass(EnterWarehouseListActivity.class);
				finish();
			}
		});
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
					toastShort("没有更多数据！");
					return;
				}

				if (rowTotal > mResult.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						tns = new TransportNodeStock();
						Paging pag = new Paging();
						pag.setPage(mPage);
						pag.setPageSize(pageSize);
						tns.setPaging(pag);
						try {
							getMoreNode(tns);
						} catch (Exception e) {
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						toastShort("加载中，请稍后！");
					}
				} else {
					toastShort("没有更多数据！");
				}
			}
		}

	}

	protected void getMoreNode(TransportNodeStock tns) throws Exception {
		new InStockApi(this).nodeStock(tns, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				TransportNodeStockMessage tnsm = (TransportNodeStockMessage) o;
				TransportNodeStock tns = tnsm.getMessageBody();
				mResult = tns.getNodeStockList();
				for (TransportNodeStock result : mResult) {
					StockSummaryReportItem mStockItem = new StockSummaryReportItem(
							result.getProName(), result.getUidNumber(), result
									.getProSpec(), result.getProPrice());
					mListResultItem.add(mStockItem);
				}
				// mAdapter = new StockSummaryReportAdapter(mListResultItem,
				// StockSummaryReportActivity.this);
				// mLvStock.setAdapter(mAdapter);
				// mLvStock.setOnItemClickListener(StockSummaryReportActivity.this);
				// mLvStock.setOnScrollListener(StockSummaryReportActivity.this);
				mAdapter.notifyDataSetChanged();
				mLoading = false;
				toastShort("获取" + mResult.size() + "条数据,共"+mListResultItem.size());
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort("请求失败！" + errorMsg);
				mLoading = false;
				mPage -= 1;
			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			StockSummaryReportActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
