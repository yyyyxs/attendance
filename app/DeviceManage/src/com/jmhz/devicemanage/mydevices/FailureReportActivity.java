package com.jmhz.devicemanage.mydevices;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesApplyInfo;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.Faultrepair;

public class FailureReportActivity extends BaseActivity {

	private Button fr_submit;
	private Button fr_cancel;
	private EditText edt_fr_device_id;
	private EditText edt_fr_device_name;
	private EditText edt_fr_device_type;
	private EditText edt_fr_device_user;
	private Spinner spinner_fr_repair_state;
	private EditText edt_fr_repair_item;
	private EditText edt_fr_repair_spend;
	private EditText edt_fr_repair_result;
	private Intent intent;
	private Bundle bundle;
	private MyDevicesApplyInfo myDevicesApplyInfo;
	String dealStatus = "";
	String cost = "";
	String repairPart = "";
	String repariResult = "";
	private final String faultReportUrl = "http://59.77.236.107:8080/mobilefault/addfaultlog";

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_devices_failure_report);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWight();
	}

	private void initWight() {
		intent = getIntent();
		bundle = intent.getExtras();
		myDevicesApplyInfo = (MyDevicesApplyInfo) bundle
				.getSerializable("myDevicesApplyInfo");

		fr_submit = (Button) findViewById(R.id.fr_submit);
		fr_cancel = (Button) findViewById(R.id.fr_cancel);
		edt_fr_device_id = (EditText) findViewById(R.id.edt_fr_device_id);
		edt_fr_device_name = (EditText) findViewById(R.id.edt_fr_device_name);
		edt_fr_device_type = (EditText) findViewById(R.id.edt_fr_device_type);
		edt_fr_device_user = (EditText) findViewById(R.id.edt_fr_device_user);
		spinner_fr_repair_state = (Spinner) findViewById(R.id.spinner_fr_repair_state);
		edt_fr_repair_item = (EditText) findViewById(R.id.edt_fr_repair_item);
		edt_fr_repair_spend = (EditText) findViewById(R.id.edt_fr_repair_spend);
		edt_fr_repair_result = (EditText) findViewById(R.id.edt_fr_repair_result);
		edt_fr_device_id.setText(myDevicesApplyInfo.getDeviceId() + "");
		edt_fr_device_id.setEnabled(false);
		edt_fr_device_name.setText(myDevicesApplyInfo.getDeviceName());
		edt_fr_device_name.setEnabled(false);
		edt_fr_device_type.setText(myDevicesApplyInfo.getDeviceType());
		edt_fr_device_type.setEnabled(false);
		edt_fr_device_user.setText(myDevicesApplyInfo.getDeviceUser());
		edt_fr_device_user.setEnabled(false);
		fr_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(FailureReportActivity.this)
						.setTitle("提示")
						.setMessage("确定提交？")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										// Toast.makeText(getApplicationContext(),
										// "提交成功！",
										// Toast.LENGTH_SHORT).show();
										dealStatus = spinner_fr_repair_state
												.getSelectedItem().toString();
										cost = edt_fr_repair_spend.getText()
												.toString();
										repairPart = edt_fr_repair_item
												.getText().toString();
										repariResult = edt_fr_repair_result
												.getText().toString();
										if (!dealStatus.equals("")
												&& !cost.equals("")
												&& !repairPart.equals("")
												&& !repariResult.equals("")) {
											try {
												faultReport();
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										} else {
											toastShort("维修报告内容不能为空！");
										}
										// FailureReportActivity.this.finish();
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {

									}
								}).show();

			}
		});
		fr_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();

			}
		});

	}

	protected void faultReport() throws Exception {
		Faultrepair repair = new Faultrepair();
		repair.setApplyId(myDevicesApplyInfo.getApplyId());
		repair.setDeviceName(myDevicesApplyInfo.getDeviceName());
		repair.setDeviceType(myDevicesApplyInfo.getDeviceType());
		repair.setDeviceUser(myDevicesApplyInfo.getDeviceUser());
		repair.setDealStatus(dealStatus);
		repair.setRepairpart(repairPart);
		repair.setCost(cost);
		repair.setRepairResult(repariResult);
		new GetDeviceInfoApi(this).faultReport(repair,
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
						FailureReportActivity.this.finish();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						toastShort(errorMsg);
					}
				});
	}

	protected void initializeActivityHead() {
		centreText.setText("维修报告");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			FailureReportActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
