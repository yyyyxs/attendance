package com.jmhz.devicemanage.enter;

/**
 /**
 * 出库管理
 */
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

import com.jmhz.devicemanage.BarCodeTestActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.gesture.GestureListener;
import com.jmhz.devicemanage.gesture.OnFlingListener;
import com.jmhz.devicemanage.stock.DeliveryOutListActivity;
import com.zxing.activity.CaptureActivity;

@SuppressWarnings("deprecation")
public class EnterWarehouseListActivity extends TabActivity implements
		OnFlingListener {
	protected Button leftButton;
	protected TextView centreText;
	protected Button rightButton;
	protected TabHost mTabHost;
	private GestureDetector detector = null;
	private String operate = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_stock_management);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initializeActivityHead();
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			operate = bundle.getString("operate");
		}
		

		mTabHost = getTabHost();
		createTab("二维码扫描", new Intent(this, BarCodeTestActivity.class));

		
		
		createTab("属性查找", new Intent(this, AttributesFindActivity.class));
		
		

		
		
		if ("二维码扫描".equals(operate)) {
			mTabHost.setCurrentTab(0);
		} else if ("手动扫描".equals(operate)) {
			mTabHost.setCurrentTab(1);
		} else if ("属性查找".equals(operate)) {
			mTabHost.setCurrentTab(2);
		} else {
			mTabHost.setCurrentTab(0);
		}
		detector = new GestureDetector(this, new GestureListener(this));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
			mTabHost.clearAllTabs();
		createTab("二维码扫描", new Intent(this, BarCodeTestActivity.class));

       
		
		createTab("属性查找", new Intent(this, AttributesFindActivity.class));
		
		

		
		
//		if ("".equals(operate)) {
//			mTabHost.setCurrentTab(0);
//		} else if ("id查找".equals(operate)) {
//			mTabHost.setCurrentTab(1);
//		} 
//		else if ("属性查找".equals(operate)) {
//			mTabHost.setCurrentTab(2);
//		} 
//		else {
//			mTabHost.setCurrentTab(0);
//		}
		
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
				// startActivityWithClass(EnterWarehouseListActivity.class);
				finish();
			}
		});
	}

	private void initializeActivityHead() {
		centreText.setText(R.string.stock_out_management);
		rightButton.setVisibility(View.GONE);
		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
}
