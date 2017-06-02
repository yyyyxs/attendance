package com.jmhz.devicemanage.mydevices;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;
import com.jmhz.devicemanage.web.UpgradeBack;
import com.jmhz.devicemanage.web.upgradeApply;
public class UpgradeReportingActivity extends BaseActivity {
	Button uri_cancel;
	Button uri_submit;
	private EditText edt_uri_device_id;
	private EditText edt_uri_device_name;
	private EditText edt_uri_device_type;
	private EditText edt_uri_device_user;
	private EditText edt_uri_applydescribe;
	private EditText edt_uri_applytime;
	private Intent intent;
	private Bundle bundle;
	private MyDevicesListItem myDeviceItem;
	private SimpleDateFormat formatter;
	private Date curDate;
	private String applyTime;
	private final String upgradeApplyUrl = "http://59.77.236.107:8080/mobileupgrade/addUpgrade";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_devices_upgrade_reporting);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();

	}

	private void initWidget() {
		intent = getIntent();
		bundle = intent.getExtras();

		myDeviceItem = (MyDevicesListItem) bundle
				.getSerializable("MyDeviceDetail");
		uri_submit = (Button) findViewById(R.id.uri_submit);
		uri_cancel = (Button) findViewById(R.id.uri_cancel);
		edt_uri_device_id = (EditText) findViewById(R.id.edt_uri_device_id);
		edt_uri_device_name = (EditText) findViewById(R.id.edt_uri_device_name);
		edt_uri_device_type = (EditText) findViewById(R.id.edt_uri_device_type);
		edt_uri_device_user = (EditText) findViewById(R.id.edt_uri_device_user);
		edt_uri_applytime = (EditText) findViewById(R.id.edt_uri_applytime);
		edt_uri_applydescribe = (EditText) findViewById(R.id.edt_uri_applydescribe);
		edt_uri_applytime.setOnClickListener(new UriButtonClickListener());
		uri_cancel.setOnClickListener(new UriButtonClickListener());
		uri_submit.setOnClickListener(new UriButtonClickListener());
		edt_uri_device_id.setText(myDeviceItem.getDeviceId() + "");
		edt_uri_device_name.setText(myDeviceItem.getDeviceName());
		int devicetype = Integer.parseInt(myDeviceItem.getDeviceType());
		edt_uri_device_type.setText(TypeTranslateUtils.deviceType(devicetype));

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		edt_uri_applytime.setText(str);
		edt_uri_device_user.setText(myDeviceItem.getDeviceUser());
		edt_uri_device_id.setEnabled(false);
		edt_uri_device_name.setEnabled(false);
		edt_uri_device_type.setEnabled(false);
		edt_uri_applytime.setEnabled(false);
		edt_uri_device_user.setEnabled(false);

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
							edt_uri_applytime.setText(year + "-" + (month + 1)
									+ "-" + dayOfMonth);
							// et.setText("��ѡ���ˣ�" + year + "��" + (month+1) + "��"
							// + dayOfMonth + "��");
						}
					}, c.get(Calendar.YEAR), // �������
					c.get(Calendar.MONTH), // �����·�
					c.get(Calendar.DAY_OF_MONTH) // ��������
			);
			break;
		case 1:
			c = Calendar.getInstance();
			dialog = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							// et.setText("��ѡ���ˣ�"+hourOfDay+"ʱ"+minute+"��");
						}
					}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
					false);
			break;
		}
		return dialog;
	}

	class UriButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.uri_cancel:
				UpgradeReportingActivity.this.finish();
				break;
			case R.id.uri_submit:
				new AlertDialog.Builder(UpgradeReportingActivity.this)
						.setTitle("��ʾ")
						.setMessage("ȷ���걨��")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										if (edt_uri_applydescribe.getText()
												.toString().equals(""))
											toastShort("������������Ϊ�գ�");
										else {
											try {
												upgradeApply();
											} catch (Exception e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
										// Toast.makeText(getApplicationContext(),
										// "�����걨�ύ�ɹ���", Toast.LENGTH_SHORT)
										// .show();
										// UpgradeReportingActivity.this.finish();
									}
								})
						.setNegativeButton("ȡ��",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {

									}
								}).show();
				break;
			}

		}

	}

	protected void upgradeApply() throws Exception {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		curDate = new Date(System.currentTimeMillis());
		applyTime = formatter.format(curDate);
		//UpgradeBack upgradeBack=new UpgradeBack();
		upgradeApply upApply=new upgradeApply();
		upApply.setDeviceId(myDeviceItem.getDeviceId());
		upApply.setUserId(41);
		upApply.setDeviceName(myDeviceItem.getDeviceName());
		upApply.setDeviceType(myDeviceItem.getDeviceType());
		upApply.setDeviceUser(myDeviceItem.getDeviceUser());
		upApply.setApplytime(applyTime);
		upApply.setUpgradeDescribe(edt_uri_applydescribe.getText().toString());
		
		new GetDeviceInfoApi(this).upgradeApply(upApply, 
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						// DeviceBack deviceBack = (DeviceBack) o;
						// update = deviceBack.isSuccess();
						String result = (String) o;
						toastShort(result);
						// Toast.makeText(getApplicationContext(), "���³ɹ���",
						// Toast.LENGTH_SHORT).show();
						UpgradeReportingActivity.this.finish();
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
			centreText.setText("�����걨");
		else
			centreText.setText("�豸�����걨");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				UpgradeReportingActivity.this.finish();
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			UpgradeReportingActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
