package com.jmhz.devicemanage.stocksummary;

import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetStatisticInfoApi;
import com.jmhz.devicemanage.web.StatisticBack;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MaintainStatus extends Activity {
	public static String LABLE_TEXT[] = { "ÒÑÉý¼¶", "Î´Éý¼¶", "Éý¼¶ÖÐ" };
	private LinearLayout layoutViewContent;
	private double maintain[] = new double[3];
	private List<String> options = new ArrayList<String>();
	private PieChartView view;
	private Boolean isThree = true;
	//private final String url = "http://59.77.236.107:8080/mobilestatistics/repairStatistics";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_maintain);
		Summary.setOperate("Î¬ÐÞ×´Ì¬");
		try {
			initMaintain();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initMaintain() throws Exception {
		new GetStatisticInfoApi(this).getUpdateMaintainStatistic(
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						StatisticBack statisticBack = (StatisticBack) o;
						maintain[0] = statisticBack.getAlready();
						maintain[1] = statisticBack.getWait();
						maintain[2] = statisticBack.getBeing();
						for (String tem : LABLE_TEXT) {
							options.add(tem);
						}
						initView();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
					}

				});
	}

	private void initView() {
		// TODO Auto-generated method stub
		layoutViewContent = (LinearLayout) findViewById(R.id.barview_content);
		layoutViewContent.removeAllViews();
		if (maintain[0] == 0 && maintain[1] == 0 && maintain[2] == 0) {
			Toast toast = Toast.makeText(MaintainStatus.this, "ÔÝÎÞÊý¾Ý", 150);
			toast.show();
		} else {
			view = new PieChartView(MaintainStatus.this, isThree);
			view.initData(maintain, options, "Î¬ÐÞ×´Ì¬±ý×´Í¼");
			layoutViewContent.setBackgroundColor(0xffffffff);
			layoutViewContent.addView(view.getPieChartView());
		}
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

}
