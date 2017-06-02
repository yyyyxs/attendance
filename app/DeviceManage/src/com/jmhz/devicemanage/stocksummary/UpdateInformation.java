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

public class UpdateInformation extends Activity {
	public static String LABLE_TEXT[] = { "������", "δ����", "������" };
	private LinearLayout layoutViewContent;
	private double update[] = new double[3];
	private List<String> options = new ArrayList<String>();
	private PieChartView view;
	private Boolean isThree=true;
	//private final String url="http://59.77.236.107:8080/mobilestatistics/updateStatistics";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_maintain);
		Summary.setOperate("�����ſ�");
		try {
			initUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    private void initUpdate() throws Exception{
		new GetStatisticInfoApi(this).getUpdateMaintainStatistic( new HttpCallback(){

			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				StatisticBack statisticBack = (StatisticBack) o;
			    update[0]=statisticBack.getAlready();
			    update[1]=statisticBack.getWait();
			    update[2]=statisticBack.getBeing();
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
		if (update[0] == 0 && update[1] == 0 && update[2] == 0) {
			Toast toast = Toast.makeText(UpdateInformation.this, "��������", 150);
			toast.show();
		}else{
		view = new PieChartView(UpdateInformation.this, isThree);
		layoutViewContent.setBackgroundColor(0xffffffff); 
		view.initData(update,options, "�����ſ���״ͼ"); 
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
		if(keyCode == KeyEvent.KEYCODE_HOME){
			Summary.setOperate("�����ſ�");
		}
		return super.onKeyDown(keyCode, event);
	}

}