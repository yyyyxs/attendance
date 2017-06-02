package com.jmhz.devicemanage.ordermessage;

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
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.gesture.GestureListener;
import com.jmhz.devicemanage.gesture.OnFlingListener;
import com.jmhz.devicemanage.http.api.OrderMessageApi;
import com.jmhz.devicemanage.utils.DialogUtils;
import com.jmhz.devicemanage.utils.DialogUtils.OnConfirmListener;
import com.uid.trace.module.sale.schema.OrderMessage;

/**
 * 订单消息管理 主页
 * 
 * @author 锋情
 * 
 */
@SuppressWarnings("deprecation")
public class OrderListActivity extends TabActivity implements OnFlingListener {

	protected Button leftButton;
	protected TextView centreText;
	protected Button rightButton;
	protected TabHost mTabHost;
	private GestureDetector detector = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order_list);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initializeActivityHead();

		mTabHost = getTabHost();
		createTab("新订单消息", new Intent(this, NewOrderListActivity.class));

		Intent newOrderIntent2 = new Intent(this,
				ExpiredOrderListActivity.class);
		createTab("超时消息", newOrderIntent2);
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
		centreText.setText(R.string.order_message_list);
		centreText.setTextSize(22);
		rightButton.setText(R.string.clear_all);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				OrderListActivity.this.finish();
			}
		});
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				new DialogUtils(OrderListActivity.this).showConfirmDialog(
						"清除所有已读数据", new OnConfirmListener() {

							@Override
							public void onConfirm() {

								try {
									deleteReadList();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						});
			}
		});
	}

	private void deleteReadList() throws Exception {
		OrderMessage om = new OrderMessage();
		new OrderMessageApi(this).deleteReadList(om, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				Toast.makeText(OrderListActivity.this, "清除成功",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(OrderListActivity.this,
						OrderListActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {

				Toast.makeText(OrderListActivity.this,
						"清除失败," + errorMsg, Toast.LENGTH_SHORT)
						.show();

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
			OrderListActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
