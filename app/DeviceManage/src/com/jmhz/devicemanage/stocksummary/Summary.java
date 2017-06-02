package com.jmhz.devicemanage.stocksummary;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.gesture.GestureListener;
import com.jmhz.devicemanage.gesture.OnFlingListener;

/*import android.os.Bundle;
 import android.view.KeyEvent;
 import android.view.View;
 import android.view.View.OnClickListener;
 import android.widget.ImageView;
 import android.widget.TextView;

 import com.jmhz.devicemanage.BaseActivity;
 import com.jmhz.devicemanage.R;



 public class Summary extends BaseActivity{
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);

 setContentView(R.layout.activity_statistical_information);
 initializeMemberVariable(findViewById(R.id.mainHead));
 setImageTextClass(R.id.totalNumber,
 R.drawable.total_number_on_off, R.string.total_number,
 DeviceTotalNumber.class);
 setImageTextClass(R.id.maintainNumber,
 R.drawable.maintain_number_on_off, R.string.maintain_number,
 MaintainNumber.class);
 setImageTextClass(R.id.updateNumber,
 R.drawable.update_number_on_off, R.string.update_number,
 UpdateNumber.class);

 }

 private void setImageTextClass(int widgetId, int imageId, int headlineId,
 final Class<?> gotoActivity) {
 View v = findViewById(widgetId);
 ((ImageView) v.findViewById(R.id.image)).setImageResource(imageId);
 ((ImageView) v.findViewById(R.id.image))
 .setOnClickListener(new OnClickListener() {
 @Override
 public void onClick(View v) {
 startActivityWithClass(gotoActivity);
 // finish();
 }
 });
 ((TextView) v.findViewById(R.id.explain)).setText(headlineId);
 }

 @Override
 protected void initializeActivityHead() {
 // TODO Auto-generated method stub
 centreText.setText(R.string.statistical_information);
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
 public boolean onKeyDown(int keyCode, KeyEvent event) {
 if (keyCode == KeyEvent.KEYCODE_BACK) {
 Summary.this.finish();
 }
 return super.onKeyDown(keyCode, event);
 }
 }*/

@SuppressLint("InflateParams")
@SuppressWarnings("deprecation")
public class Summary extends TabActivity implements OnFlingListener {
	protected Button leftButton;
	protected TextView centreText;
	protected Button rightButton;
	protected TabHost mTabHost;
	private GestureDetector detector = null;
	private static String operate = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ���ر������ĺ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_statistical_information);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initializeActivityHead();
		// getIntent�õ�һ��Intent������intent����intent.getExtras�����õ�intent�������Ķ�������
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			operate = bundle.getString("operate");
		}

		mTabHost = getTabHost();
		
		createTab("����ͳ��", new Intent(this, TotalStatistic.class));
		
		createTab("�豸ͳ��", new Intent(this, DeviceStatus.class));

		createTab("ά��״̬", new Intent(this, MaintainStatus.class));

		createTab("�����ſ�", new Intent(this, UpdateInformation.class));

		if ("����ͳ��".equals(operate)) {
			mTabHost.setCurrentTab(0);
		} else if ("�豸ͳ��".equals(operate)) {
			mTabHost.setCurrentTab(1);
		} else if ("ά��״̬".equals(operate)) {
			mTabHost.setCurrentTab(2);
		} else if ("�����ſ�".equals(operate)) {
			mTabHost.setCurrentTab(3);
		} else {
			mTabHost.setCurrentTab(0);
		}
		// ���Ƽ�����
		detector = new GestureDetector(this, new GestureListener(this));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mTabHost.clearAllTabs();
		createTab("����ͳ��", new Intent(this, TotalStatistic.class));

		createTab("�豸ͳ��", new Intent(this, DeviceStatus.class));

		createTab("ά��״̬", new Intent(this, MaintainStatus.class));

		createTab("�����ſ�", new Intent(this, UpdateInformation.class));

		if ("����ͳ��".equals(operate)) {
			mTabHost.setCurrentTab(0);
		} else if ("�豸ͳ��".equals(operate)) {
			mTabHost.setCurrentTab(1);
		} else if ("ά��״̬".equals(operate)) {
			mTabHost.setCurrentTab(2);
		} else if ("�����ſ�".equals(operate)) {
			mTabHost.setCurrentTab(3);
		}else {
			mTabHost.setCurrentTab(0);
		}
		super.onResume();
	}

	private void createTab(String text, Intent intent) {

		mTabHost.addTab(mTabHost.newTabSpec(text)
				.setIndicator(createTabView(text)).setContent(intent));

	}

	private View createTabView(String text) {
		View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator,
				null);
		((TextView) view.findViewById(R.id.tv_tab)).setText(text);

		return view;
	}

	private void initializeMemberVariable(View headRL) {
		leftButton = (Button) headRL.findViewById(R.id.leftButton);
		centreText = (TextView) headRL.findViewById(R.id.centreText);
		rightButton = (Button) headRL.findViewById(R.id.rightButton);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void initializeActivityHead() {
		centreText.setText(R.string.statistical_information);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onFlingLeft() {
		mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
	}

	@Override
	public void onFlingRight() {
		mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		super.dispatchTouchEvent(ev);
		return detector.onTouchEvent(ev);
	}

	public static String getOperate() {
		return operate;
	}

	public static void setOperate(String operate) {
		Summary.operate = operate;
	}

}
