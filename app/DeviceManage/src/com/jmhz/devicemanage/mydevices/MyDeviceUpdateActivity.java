package com.jmhz.devicemanage.mydevices;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.R.string;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.mydevices.FailureReportingActivity.FriButtonClickListener;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;

public class MyDeviceUpdateActivity extends BaseActivity {
	private final String url = "http://59.77.236.107:8080/mobiledevicemg/updatebyuser";
	private TextView et_device_num_update;
	private TextView et_device_name_update;
	private TextView et_device_type_update;
	private TextView et_device_price_update;
	private TextView et_device_buytime_update;
	private TextView et_device_status_update;
	private TextView et_device_user_update;
	private Spinner spinner_device_position_update;
	private TextView et_device_CPUInfo_update;
	private EditText et_device_InternalMemory_update;
	private EditText et_device_GraphicsCard_update;
	private EditText et_device_otherInfo_update;
	private Button mydevice_update_submit;
	private Button mydevice_update_cancel;
	private ArrayAdapter<String> positionAdapter = null;
	private ArrayAdapter<String> configAdapter = null;
	private String[] spinnerArray = { "301", "302", "303", "304", "305" };
	private String[] config = { "CPU信息", "显卡信息", "内存信息", "设备其他信息" };
	private Intent intent;
	private Bundle bundle;
	private boolean isUpdate=false;
	private MyDevicesListItem myDeviceItem;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_device_update);
		initializeMemberVariable(findViewById(R.id.mainHead));
		positionAdapter = new ArrayAdapter<String>(MyDeviceUpdateActivity.this,
				android.R.layout.simple_spinner_item, spinnerArray);
		configAdapter = new ArrayAdapter<String>(MyDeviceUpdateActivity.this,
				android.R.layout.simple_spinner_item, config);

		initWight();
	}

	private void initWight() {
		intent = getIntent();
		bundle = intent.getExtras();
		myDeviceItem = (MyDevicesListItem) bundle
				.getSerializable("MyDeviceDetail");
		et_device_num_update = (TextView) findViewById(R.id.et_device_num_update);
		et_device_name_update = (TextView) findViewById(R.id.et_device_name_update);
		et_device_type_update = (TextView) findViewById(R.id.et_device_type_update);
		et_device_price_update = (TextView) findViewById(R.id.et_device_price_update);
		et_device_user_update = (TextView) findViewById(R.id.et_device_user_update);
		
		et_device_buytime_update = (TextView) findViewById(R.id.et_device_buytime_update);
		et_device_status_update = (TextView) findViewById(R.id.et_device_status_update);
		spinner_device_position_update = (Spinner) findViewById(R.id.spinner_device_position_update);
		et_device_CPUInfo_update = (TextView) findViewById(R.id.et_device_CPUInfo_update);
		et_device_InternalMemory_update = (EditText) findViewById(R.id.et_device_InternalMemory_update);
		et_device_otherInfo_update = (EditText) findViewById(R.id.et_device_otherInfo_update);
		et_device_GraphicsCard_update = (EditText) findViewById(R.id.et_device_GraphicsCard_update);
		mydevice_update_submit = (Button) findViewById(R.id.mydevice_update_submit);
		mydevice_update_cancel = (Button) findViewById(R.id.mydevice_update_cancel);
		mydevice_update_submit.setOnClickListener(new FriButtonClickListener());
		mydevice_update_cancel.setOnClickListener(new FriButtonClickListener());

		et_device_num_update.setText(myDeviceItem.getDeviceId() + "");
		et_device_name_update.setText(myDeviceItem.getDeviceName());

		int deviceType = Integer.parseInt(myDeviceItem.getDeviceType());
		et_device_type_update
				.setText(TypeTranslateUtils.deviceType(deviceType));
		et_device_user_update.setText(myDeviceItem.getDeviceUser());
		et_device_price_update.setText(myDeviceItem.getDevicePrice());
		et_device_buytime_update.setText(myDeviceItem.getDeviceTime());
		int deviceStatus = Integer.parseInt(myDeviceItem.getDeviceState());
		et_device_status_update.setText(StatusTranslateUtils
				.deviceStatus(deviceStatus));
		spinner_device_position_update.setAdapter(positionAdapter);
		int devicePosition = Integer.parseInt(myDeviceItem.getDevicePlace());
		spinner_device_position_update.setSelection(positionAdapter.getPosition(PositionTranslateUtils.devicePosition(devicePosition)));
		et_device_CPUInfo_update.setText(myDeviceItem.getDeviceCPUInfo());
		et_device_GraphicsCard_update.setText(myDeviceItem
				.getDeviceGraphicsCard());
		et_device_InternalMemory_update.setText(myDeviceItem
				.getDeviceInternalMemory());
		et_device_otherInfo_update.setText(myDeviceItem.getDeviceOtherInfo());

	}

	protected void initializeActivityHead() {

		centreText.setText("更新设备信息");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void update() throws Exception {
		// .getSelectedItem()获得301，302之类
		// Integer.parseInt(myDeviceItem.getDeviceType());
		String positionUpdate = spinner_device_position_update
				.getSelectedItem().toString();
		String deviceInternalMemoryUpdate = et_device_InternalMemory_update
				.getText().toString();
		String deviceGraphicsCardUpdate = et_device_GraphicsCard_update
				.getText().toString();
		String deviceOtherInfoUpdate = et_device_otherInfo_update.getText()
				.toString();
		if (!"".equals(deviceInternalMemoryUpdate)&& !"".equals(deviceGraphicsCardUpdate)
				&&!"".equals(deviceOtherInfoUpdate)) {
			System.out.println("TTTT"+deviceInternalMemoryUpdate+"1"+deviceGraphicsCardUpdate+"2"+deviceOtherInfoUpdate);
			DeviceBack deviceBack = new DeviceBack();
			Device device = new Device();
			device.setId(myDeviceItem.getDeviceId());
			device.setPosition(String.valueOf(PositionTranslateUtils
					.devicePosition(positionUpdate)));
			device.setInternalMemory(deviceInternalMemoryUpdate);
			device.setOtherInfo(deviceOtherInfoUpdate);
			device.setGraphicsCard(deviceGraphicsCardUpdate);
			deviceBack.setDevice(device);
			new GetDeviceInfoApi(this).updateUserDeviceInfo(deviceBack,
					new HttpCallback() {

						@Override
						public void onSuccess(Object o) {
							// TODO Auto-generated method stub
//							DeviceBack deviceBack = (DeviceBack) o;
//							update = deviceBack.isSuccess();
							String result = (String) o;
							toastShort(result);
//							Toast.makeText(getApplicationContext(), "更新成功！",
//									Toast.LENGTH_SHORT).show();
							MyDeviceUpdateActivity.this.finish();
						}

						@Override
						public void onFail(String errorCode, String errorMsg) {
							// TODO Auto-generated method stub
							toastShort(errorMsg);
						}
					});

		} else
			Toast.makeText(getApplicationContext(), "选项不能为空！",
					Toast.LENGTH_SHORT).show();

	}

	class FriButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mydevice_update_cancel:
				MyDeviceUpdateActivity.this.finish();
				break;
			case R.id.mydevice_update_submit:
				new AlertDialog.Builder(MyDeviceUpdateActivity.this)
						.setTitle("提示")
						.setMessage("确定修改设备信息？")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										try {
											update();
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

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

}
