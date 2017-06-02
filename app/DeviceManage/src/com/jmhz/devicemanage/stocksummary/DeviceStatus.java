package com.jmhz.devicemanage.stocksummary;

import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.ClassNumberAdapter;
import com.jmhz.devicemanage.adapter.YearAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetStatisticInfoApi;
import com.jmhz.devicemanage.web.StatisticsBack;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class DeviceStatus extends Activity implements OnItemSelectedListener {

	private Button btnquery = null;
	private Spinner YearAdress = null;
	private Spinner relative = null;
	private YearAdapter mYearAdapter = null;
	private ClassNumberAdapter mClassNumberAdapter = null;
	private ArrayList<String> mListYearItem = new ArrayList<String>();
	private ArrayList<String> mListClassNumberItem = new ArrayList<String>();
	public static String LABLE_TEXT[] = { "报废", "使用中", "闲置", "维修", "升级" };
	private Boolean isThree = false;
	private LinearLayout layoutViewContent;
	private double devices[] = new double[5];
	private List<String> options = new ArrayList<String>();
	private PieChartView view;
	private String chosed = "年份";
	private String type;
	private String condition;
	//private final String url = "http://59.77.236.107:8080/mobilestatistics/deviceStatistics";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two_spinner);
		Summary.setOperate("设备统计");
		for (String tem : LABLE_TEXT) {
			options.add(tem);
		}
		initView();
	}

	private void initDevices() throws Exception {
		StatisticsBack statisticsBack = new StatisticsBack();
		new GetStatisticInfoApi(this).getDeviceStatistic(statisticsBack,
				type, condition, new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						StatisticsBack statisticsBack = (StatisticsBack) o;
						devices[0] = statisticsBack.getDiscard();
						devices[1] = statisticsBack.getUsing();
						devices[2] = statisticsBack.getUnused();
						devices[3] = statisticsBack.getMaintain();
						devices[4] = statisticsBack.getUpdate();
						getPie();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
					}

				});
	}

	private void initView() {
		// TODO Auto-generated method stub
		mYearAdapter = new YearAdapter(getYearDate(), this);
		mClassNumberAdapter = new ClassNumberAdapter(getClassDate(), this);

		YearAdress = (Spinner) findViewById(R.id.spinner_adress_year);
		YearAdress.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				chosed = YearAdress.getSelectedItem().toString();
				if (chosed.equals("年份")) {
					type = "year";
					relative.setAdapter(mYearAdapter);
				} else {
					type = "position";
					relative.setAdapter(mClassNumberAdapter);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		relative = (Spinner) findViewById(R.id.spinner_relative);
		relative.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				condition = relative.getSelectedItem().toString();
				try {
					initDevices();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void getPie() {

		btnquery = (Button) findViewById(R.id.total_number_query);
		btnquery.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layoutViewContent = (LinearLayout) findViewById(R.id.barview_content);
				layoutViewContent.removeAllViews();
				if (devices[0] == 0 && devices[1] == 0 && devices[2] == 0&& devices[3] == 0&& devices[4] == 0) {
					Toast toast = Toast
							.makeText(DeviceStatus.this, "暂无数据", 150);
					toast.show();
				} else {
					view = new PieChartView(DeviceStatus.this, isThree);
					view.initData(devices, options, "设备统计饼状图");
					layoutViewContent.setBackgroundColor(0xffffffff);
					layoutViewContent.addView(view.getPieChartView());
				}
			}
		});
	}

	private ArrayList<String> getClassDate() {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 6; i++) {
			String listClassNumberItem = new String();
			listClassNumberItem = "30" + i;
			mListClassNumberItem.add(listClassNumberItem);
		}
		return mListClassNumberItem;
	}

	private ArrayList<String> getYearDate() {
		// TODO Auto-generated method stub
		for (int i = 2010; i <= 2016; i++) {
			String listYearItem = new String();
			listYearItem = "" + i;
			mListYearItem.add(listYearItem);
		}
		return mListYearItem;
	}

	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
