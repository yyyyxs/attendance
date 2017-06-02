package com.jmhz.devicemanage.salesorder;
/**
 *销货订单管理 
 */
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.gesture.GestureListener;
import com.jmhz.devicemanage.gesture.OnFlingListener;

@SuppressWarnings("deprecation")
public class SalesOrderManagementActivity extends TabActivity implements
		OnFlingListener {
	protected Button leftButton;
	protected TextView centreText;
	protected Button rightButton;
	protected TabHost mTabHost;
	private GestureDetector detector = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_salas_order_list);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initializeActivityHead();

		mTabHost = getTabHost();

		createTab("已支付", new Intent(this, PaymentListActivity.class));

		createTab("未支付", new Intent(this, NonPaymentListActivity.class));

		createTab("已配送", new Intent(this, DispatchListActivity.class));

		createTab("已收货", new Intent(this, ReceiveListActivity.class));

		mTabHost.setCurrentTab(0);
		detector = new GestureDetector(this, new GestureListener(this));
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
	}

	private void initializeActivityHead() {
		centreText.setText(R.string.sales_order_management);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivityWithClass(MainActivity.class);
				SalesOrderManagementActivity.this.finish();
			}
		});
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			SalesOrderManagementActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
