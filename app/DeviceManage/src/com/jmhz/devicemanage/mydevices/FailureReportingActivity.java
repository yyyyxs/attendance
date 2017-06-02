package com.jmhz.devicemanage.mydevices;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.R.integer;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.zxing.oned.rss.FinderPattern;
import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.stock.StockManagementActivity;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.upgradeApply;

public class FailureReportingActivity extends BaseActivity {
	private EditText fri_device_id;
	private EditText fri_device_name;
	private EditText edt_fri_device_type;
	private EditText device_user;
	private EditText fri_applytime;
	private EditText fri_applydescribe;
	private Button fri_submit;
	private Button fri_cancel;
	private Intent intent;
	private Bundle bundle;
	private MyDevicesListItem myDeviceItem;
	private SimpleDateFormat formatter;
	private Date curDate;
	private String applyTime;
	private final String faultApplyUrl = "http://59.77.236.107:8080/mobilefault/addfault";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_devices_failure_reporting);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();

		// fri_place.setText(bundle.getString("MyDeviceDetail"));
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		fri_device_id.setText("");
		fri_device_name.setText("");
		edt_fri_device_type.setSelection(0);
		device_user.setText("");
		fri_device_id.setEnabled(true);
		fri_device_name.setEnabled(true);
		edt_fri_device_type.setEnabled(true);
		device_user.setEnabled(true);
	}

	private void initWidget() {
		fri_device_id = (EditText) findViewById(R.id.edt_fri_device_id);
		fri_device_name = (EditText) findViewById(R.id.edt_fri_device_name);
		edt_fri_device_type = (EditText) findViewById(R.id.edt_fri_device_type);
		device_user = (EditText) findViewById(R.id.edt_fri_device_user);
		fri_applydescribe = (EditText) findViewById(R.id.edt_fri_applydescribe);
		fri_applytime = (EditText) findViewById(R.id.edt_fri_applytime);

		fri_submit = (Button) findViewById(R.id.fri_submit);
		fri_cancel = (Button) findViewById(R.id.fri_cancel);
		fri_applytime.setOnClickListener(new FriButtonClickListener());
		fri_submit.setOnClickListener(new FriButtonClickListener());
		fri_cancel.setOnClickListener(new FriButtonClickListener());
		intent = getIntent();
		bundle = intent.getExtras();
		myDeviceItem = (MyDevicesListItem) bundle
				.getSerializable("MyDeviceDetail");

		fri_device_id.setText(myDeviceItem.getDeviceId() + "");
		fri_device_name.setText(myDeviceItem.getDeviceName());
		int devicetype = Integer.parseInt(myDeviceItem.getDeviceType());
		edt_fri_device_type.setText(TypeTranslateUtils.deviceType(devicetype));
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		curDate = new Date(System.currentTimeMillis());
		applyTime = formatter.format(curDate);
		fri_applytime.setText(applyTime);
		device_user.setText(myDeviceItem.getDeviceUser());

		fri_device_id.setEnabled(false);
		fri_device_name.setEnabled(false);
		edt_fri_device_type.setEnabled(false);
		edt_fri_device_type.setEnabled(false);

		device_user.setEnabled(false);

	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case 0:
			Calendar c = Calendar.getInstance();
			dialog = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
						public void onDateSet(DatePicker dp, int year,
								int month, int dayOfMonth) {
							fri_applytime.setText(year + "-" + (month + 1)
									+ "-" + dayOfMonth);
							// et.setText("您选择了：" + year + "年" + (month+1) + "月"
							// + dayOfMonth + "日");
						}
					}, c.get(Calendar.YEAR), // 传入年份
					c.get(Calendar.MONTH), // 传入月份
					c.get(Calendar.DAY_OF_MONTH) // 传入天数
			);
			break;
		case 1:
			c = Calendar.getInstance();
			dialog = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							// et.setText("您选择了："+hourOfDay+"时"+minute+"分");
						}
					}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
					false);
			break;
		}
		return dialog;
	}

	class FriButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.edt_fri_applytime:
				showDialog(0);
				break;
			case R.id.fri_cancel:
				FailureReportingActivity.this.finish();
				break;
			case R.id.fri_submit:
				new AlertDialog.Builder(FailureReportingActivity.this)
						.setTitle("提示")
						.setMessage("确定申报？")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										if (fri_applydescribe.getText()
												.toString().equals(""))
											toastShort("维修描述不能为空！");
										else {
											try {
												faultApply();
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
										// Toast.makeText(getApplicationContext(),
										// "故障申报提交成功！", Toast.LENGTH_SHORT)
										// .show();

									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {

									}
								}).show();
				// FailureReportingActivity.this.finish();
				break;
			}

		}

	}

	protected void faultApply() throws Exception {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		curDate = new Date(System.currentTimeMillis());
		applyTime = formatter.format(curDate);
		FaultApply faultApply=new FaultApply();
		faultApply.setDeviceId(myDeviceItem.getDeviceId());
		faultApply.setUserId(41);
		faultApply.setDeviceName(myDeviceItem.getDeviceName());
		faultApply.setDeviceType(myDeviceItem.getDeviceType());
		faultApply.setDeviceUser(myDeviceItem.getDeviceUser());
		faultApply.setApplytime(applyTime);
		faultApply.setFaultDescribe(fri_applydescribe.getText().toString());
		new GetDeviceInfoApi(this).faultApply(faultApply,
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						// DeviceBack deviceBack = (DeviceBack) o;
						// update = deviceBack.isSuccess();
						String result = (String) o;
						toastShort(result);
						// Toast.makeText(getApplicationContext(), "更新成功！",
						// Toast.LENGTH_SHORT).show();
						FailureReportingActivity.this.finish();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						toastShort(errorMsg);
					}
				});
	}

	protected void initializeActivityHead() {
		if (bundle.getString("applyAgain").equals("true"))
			centreText.setText("重新申报");
		else
			centreText.setText("设备维修申报");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				FailureReportingActivity.this.finish();
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			FailureReportingActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
