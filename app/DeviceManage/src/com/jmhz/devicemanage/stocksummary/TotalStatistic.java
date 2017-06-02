package com.jmhz.devicemanage.stocksummary;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetStatisticInfoApi;
import com.jmhz.devicemanage.web.StatisticsYPBack;

public class TotalStatistic extends Activity implements OnItemSelectedListener {
	private Button btnquery = null;
	private Spinner tostatistic = null;
	public static String LABLE_TEXT_YEAR[] = { "", "2011", "2012", "2013",
			"2014", "2015", "2016" };
	public static String LABLE_TEXT_ROOM[] = { "", "301", "302", "303", "304",
			"305", "306" };
	private LinearLayout layoutViewContent;
	private double first[] = new double[7];
	private double second[] = new double[7];
	private List<String> options = new ArrayList<String>();
	private boolean isSingleView;
	private BarChartView view;
	private String chosed = "";
	private String type = "";
	//private final String url = "http://59.77.236.107:8080/mobilestatistics/yearORpositionStatistics";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one_spinner);
		Summary.setOperate("总数统计");
		isSingleView = true;
		initView();
	}

	private void initfirst() throws Exception {
		StatisticsYPBack statisticsYPBack = new StatisticsYPBack();
		new GetStatisticInfoApi(this).getTotalStatistic(statisticsYPBack,
				type, new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						StatisticsYPBack statisticsYPBack = (StatisticsYPBack) o;
						first[0] = statisticsYPBack.getYp1();
						first[1] = statisticsYPBack.getYp2();
						first[2] = statisticsYPBack.getYp3();
						first[3] = statisticsYPBack.getYp4();
						first[4] = statisticsYPBack.getYp5();
						first[5] = statisticsYPBack.getYp6();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
					}

				});
	}

	private void initView() {
		// TODO Auto-generated method stub
		tostatistic = (Spinner) findViewById(R.id.spinner_total_number);
		tostatistic.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				chosed = tostatistic.getSelectedItem().toString();
				if ("显示所有年份设备量".equals(chosed)) {
					type = "year";
				} else if ("显示所有实验室设备量".equals(chosed)) {
					type = "position";
				}
				try {
					initfirst();
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
		btnquery = (Button) findViewById(R.id.total_number_query);
		btnquery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layoutViewContent = (LinearLayout) findViewById(R.id.barview_content);
				layoutViewContent.removeAllViews();
				if (first[0] == 0 && first[1] == 0 && first[2] == 0
						&& first[3] == 0 && first[4] == 0 && first[5] == 0
						&& first[6] == 0) {
					Toast toast = Toast.makeText(TotalStatistic.this, "暂无数据",
							150);
					toast.show();
				} else {
					view = new BarChartView(TotalStatistic.this, isSingleView);
					options = new ArrayList<String>();

					if (type.equals("year")) {
						for (String tem : LABLE_TEXT_YEAR) {
							options.add(tem);
						}
						view.initData("年份", first, second, options, chosed);
					} else if ("position".equals(type)) {
						for (String tem : LABLE_TEXT_ROOM) {
							options.add(tem);
						}
						view.initData("实验室", first, second, options, chosed);
					}
					layoutViewContent.setBackgroundColor(0xffffffff);
					layoutViewContent.addView(view.getBarChartView());
				}
			}
		});
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
		if (keyCode == KeyEvent.KEYCODE_HOME) {
			Summary.setOperate("总数统计");
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
