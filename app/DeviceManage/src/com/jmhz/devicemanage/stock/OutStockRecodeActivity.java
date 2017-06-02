package com.jmhz.devicemanage.stock;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.OutStockRecordAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.InStockApi;
import com.uid.trace.module.stock.schema.InOutStock;
import com.uid.trace.module.stock.schema.InOutStockDetail;
import com.uid.trace.module.stock.schema.InOutStockMessage;

public class OutStockRecodeActivity extends BaseActivity implements OnItemClickListener, OnScrollListener {
	private ListView mInStocks = null;
	private ArrayList<InOutStockDetail> rowResult = new ArrayList<InOutStockDetail>();
	private OutStockRecordAdapter mAdapter = null;
	private int cid;
	private String stocknumber;
	private String transactorname;
	private String stocktypename;
	private String stockdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inout_record_details);
		initializeMemberVariable(findViewById(R.id.mainHead));
		mInStocks = (ListView) findViewById(R.id.lv_detail);
		Bundle bundle = this.getIntent().getExtras();
		cid = bundle.getInt("cid");
		stocknumber = bundle.getString("stocknumber");
		transactorname = bundle.getString("transactorname");
		stocktypename = bundle.getString("stocktypename");
		stockdata = bundle.getString("stockdata");
		try {
			initWidget();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initWidget() throws Exception {
		// TODO Auto-generated method stub
		InOutStock inOutStock = new InOutStock();
		inOutStock.setCid(cid);
		new InStockApi(this).getInOutStockRecodeDetail(inOutStock, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				InOutStockMessage ism = (InOutStockMessage) o;
				InOutStock inOutStock = ism.getMessageBody();
				ArrayList<InOutStockDetail> iStockDetails = (ArrayList<InOutStockDetail>) inOutStock.getInOutStockDetailList();
				initData(R.id.cid, "出库单号: ", stocknumber);
				initData(R.id.stock_type, "出库类型: ", stocktypename);
				initData(R.id.operator, "经  办  人: ", transactorname);
				initData(R.id.stock_time, "出库时间: ", stockdata);
				mAdapter = new OutStockRecordAdapter(iStockDetails,
						OutStockRecodeActivity.this);
				mInStocks.setOnItemClickListener(OutStockRecodeActivity.this);
				mInStocks.setOnScrollListener(OutStockRecodeActivity.this);
				mInStocks.setAdapter(mAdapter);
				
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				toastShort(errorMsg);
			}
		});
	}
	private void initData(int widgetId, String key, String value) {
		((TextView) findViewById(widgetId).findViewById(R.id.tv_value))
				.setText(value);
		((TextView) findViewById(widgetId).findViewById(R.id.tv_key))
				.setText(key);
	}
	@Override
	protected void initializeActivityHead() {

		centreText.setText(R.string.stock_out_record);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				OutStockRecodeActivity.this.finish();
			}
		});
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
