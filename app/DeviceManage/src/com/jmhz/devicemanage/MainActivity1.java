package com.jmhz.devicemanage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jmhz.devicemanage.enter.EnterWarehouseListActivity;
import com.jmhz.devicemanage.mycenter.PersonCenterActivity;
import com.jmhz.devicemanage.mydevices.FailureReportingProcessActivity;
import com.jmhz.devicemanage.mydevices.MyDevicesActivity;
import com.jmhz.devicemanage.mydevices.MyDevicesListActivity;
import com.jmhz.devicemanage.mydevices.UpgradeReportingProcessActivity;
import com.jmhz.devicemanage.ordermessage.OrderListActivity;
import com.jmhz.devicemanage.push.PushService;
import com.jmhz.devicemanage.stocksummary.Summary;
import com.jmhz.devicemanage.utils.DialogUtils;
import com.jmhz.devicemanage.utils.ServiceUtils;
import com.jmhz.devicemanage.utils.DialogUtils.OnConfirmListener;

public class MainActivity1 extends BaseActivity{
	private static boolean isExit = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main1);
		initializeMemberVariable(findViewById(R.id.mainHead));
		setImageTextClass(R.id.stockManagement,
				R.drawable.stockmanagement_on_off, R.string.stock_management,
				EnterWarehouseListActivity.class);
//		setImageTextClass(R.id.warehouseManagement,
//				R.drawable.warehousemanagement_on_off,
//				R.string.warehouse_management, DeviceManageMainActivity.class);
//		setImageTextClass(R.id.salesOrderManagement,
//				R.drawable.salesordermanagement_on_off,
//				R.string.sales_order_management,
//				SalesOrderManagementActivity.class);
//		setImageTextClass(R.id.orderMessageManagement,
//				R.drawable.ordermessagemanagement_on_off,
//				R.string.order_message_management, OrderListActivity.class);
		setImageTextClass(R.id.myDevices, R.drawable.mycenter_on_off,
				R.string.myDevices, MyDevicesListActivity.class);
		
		setImageTextClass(R.id.stockSummaryReport,
				R.drawable.stocksummaryreport_on_off,
				R.string.stock_summary_report, Summary.class);
		setImageTextClass(R.id.myCenter,
				R.drawable.ordermessagemanagement_on_off, R.string.my_center,
				PersonCenterActivity.class);
		
		setImageTextClass(R.id.myDevices_repair,
				R.drawable.stocksummaryreport_on_off,
				R.string.my_devices_repair, FailureReportingProcessActivity.class);
		setImageTextClass(R.id.myDevices_upgrade,
				R.drawable.ordermessagemanagement_on_off, R.string.my_devices_upgrade,
				UpgradeReportingProcessActivity.class);
//		setImageTextClass(R.id.kong, R.drawable.mycenter_on_off,
//				R.string.myDevices, MyCenterActivity.class);

	}

	private void setImageTextClass(int widgetId, int imageId, int headlineId,
			final Class<?> gotoActivity) {
		View v = findViewById(widgetId);
		((ImageView) v.findViewById(R.id.image)).setImageResource(imageId);
		((ImageView) v.findViewById(R.id.image))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (TextUtils.isEmpty(getMemberSession())) {
							toastShort(R.string.no_login);
							startActivityWithClass(LoginActivity.class);
							// finish();
						} else {
							startActivityWithClass(gotoActivity);
							// finish();
						}

					}
				});
		((TextView) v.findViewById(R.id.explain)).setText(headlineId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void initializeActivityHead() {
		if (!TextUtils.isEmpty(getMemberSession())) {
			// 若是不存在推送线程,则开启
			if (!ServiceUtils.isServiceRunning(getBaseContext(),
					PushService.PUSH_SERVICE_NAME)) {

				new Thread(new Runnable() {
					@Override
					public void run() {
						Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
						i.putExtra("userName", getUserNameSession());
						startService(i);
					}
				}).start();
			}
		}

		// TODO Auto-generated method stub
		leftButton.setVisibility(View.GONE);
		centreText.setText(R.string.home_page);
		if (TextUtils.isEmpty(getMemberSession())) {
			rightButton.setText(R.string.log_in);

		} else {
			rightButton.setText(R.string.log_out);
		}
		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				SharedPreferences.Editor loginEditor=getSharedPreferences("data0", MODE_PRIVATE).edit();
				loginEditor.putString("name", "");
				loginEditor.putString("password", "");
				loginEditor.commit();
				
				if (TextUtils.isEmpty(getMemberSession())) {
					startActivityWithClass(LoginActivity.class);
				} else {
					new DialogUtils(MainActivity1.this).showConfirmDialog(
							R.string.dialog_msg, new OnConfirmListener() {

								@Override
								public void onConfirm() {
									clearMemberSession();
									if (TextUtils.isEmpty(getMemberSession())) {
										rightButton.setText(R.string.log_in);
										toastShort(R.string.log_out_success);

										// 账户成功退出时,关闭掉推动后台线程
										Intent i = new Intent(
												PushService.PUSH_SERVICE_NAME);
										stopService(i);
										startActivityWithClass(LoginActivity.class);

									} else {
										toastShort(R.string.log_out_false);
									}

								}
							});
				}

			}
		});

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {
			// 监控/拦截菜单键
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		// TODO Auto-generated method stub
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			mHandler.sendEmptyMessageDelayed(0, 2000);

		} else {
			finish();
			System.exit(0);
		}
	}
}
