package com.jmhz.devicemanage.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.DeviceManageItem;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;

public class DeviceReviseActivity extends BaseActivity implements OnClickListener {
	
	private TextView tv_et_device_num;
	private TextView tv_et_deivce_name;
	private TextView tv_et_device_type;
	private TextView tv_et_device_price;
	private TextView tv_et_device_buy_time;
	private Spinner tv_et_device_position;
	private EditText tv_et_device_user;
	private TextView tv_et_device_status;
	private TextView tv_et_device_cpu_info;
	private EditText tv_et_device_graphics_card_info;
	private EditText tv_et_device_internal_memory_info;
	private EditText tv_et_device_other_devcie_info;
	private Button btn_device_audit_devicerevise;
	private Bundle bundle;
	private Intent intent;
	private DeviceManageItem mDeviceManageItem;
	String[] spinnerArray = {"301", "302", "303", "304", "305"};
	private ArrayAdapter<String> adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_revise);
		initializeMemberVariable(findViewById(R.id.mainHead));
		intent = getIntent();
		bundle = intent.getExtras();
		mDeviceManageItem = bundle.getParcelable("DeviceManageItem");
		adapter  = new ArrayAdapter<String>(DeviceReviseActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
		initWidget();
	}
	
	private void initWidget() {
		// TODO Auto-generated method stub
		tv_et_device_num = (TextView) findViewById(R.id.tv_et_device_num_devicerevise);
		tv_et_deivce_name = (TextView) findViewById(R.id.tv_et_deivce_name_devicerevise);
		tv_et_device_type = (TextView) findViewById(R.id.tv_et_device_type_devicerevise);
		tv_et_device_price = (TextView) findViewById(R.id.tv_et_device_price_devicerevise);
		tv_et_device_buy_time = (TextView) findViewById(R.id.tv_et_device_buy_time_devicerevise);
		tv_et_device_position = (Spinner) findViewById(R.id.tv_et_device_position_devicerevise);
		tv_et_device_user = (EditText) findViewById(R.id.tv_et_device_user_devicerevise);
		tv_et_device_status = (TextView) findViewById(R.id.tv_et_device_status_devicerevise);
		tv_et_device_cpu_info = (TextView) findViewById(R.id.tv_et_device_cpu_info);
		tv_et_device_graphics_card_info = (EditText) findViewById(R.id.tv_et_device_graphics_card_info);
		tv_et_device_internal_memory_info = (EditText) findViewById(R.id.tv_et_device_internal_memory_info);
		tv_et_device_other_devcie_info = (EditText) findViewById(R.id.tv_et_device_other_devcie_info);
		btn_device_audit_devicerevise = (Button) findViewById(R.id.btn_device_audit_devicerevise);
		tv_et_device_num.setText(mDeviceManageItem.getDeviceID() + "");
		tv_et_deivce_name.setText(mDeviceManageItem.getDeviceName());
		int deviceType = Integer.parseInt(mDeviceManageItem.getDeviceType());
		tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
		tv_et_device_price.setText(mDeviceManageItem.getDevicePrice());
		tv_et_device_buy_time.setText(mDeviceManageItem.getDeciceBuyTime());
		tv_et_device_position.setAdapter(adapter);
		int devicePosition = Integer.parseInt(mDeviceManageItem.getDevicePosition());
		tv_et_device_position.setSelection(adapter.getPosition(PositionTranslateUtils.devicePosition(devicePosition)));
		tv_et_device_user.setText(mDeviceManageItem.getDeviceUser());
		int deviceStatus = Integer.parseInt(mDeviceManageItem.getDeviceStatus());
		tv_et_device_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		tv_et_device_cpu_info.setText(mDeviceManageItem.getDeviceCPUInfo());
		tv_et_device_graphics_card_info.setText(mDeviceManageItem.getDeviceGraphicsCard());
		tv_et_device_internal_memory_info.setText(mDeviceManageItem.getDeviceInternalMemory());
		tv_et_device_other_devcie_info.setText(mDeviceManageItem.getDeviceOtherInfo());
		btn_device_audit_devicerevise.setOnClickListener(this);
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.device_info_detail);
		rightButton.setVisibility(View.INVISIBLE);
		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			updateDeviceInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateDeviceInfo() throws Exception {
		// TODO Auto-generated method stub
//		int devicePosition = Integer.parseInt(mDeviceManageItem.getDevicePosition());
		String newDeviceUser = tv_et_device_user.getText().toString().trim();
		String newDevicePosition = tv_et_device_position.getSelectedItemPosition() + "";
		System.out.println(newDevicePosition + "!!!!!!");
		String newGraphicsCardInfo = tv_et_device_graphics_card_info.getText().toString().trim();
		String newInternalMemoryInfo = tv_et_device_internal_memory_info.getText().toString().trim();
		String newDeviceOtherInfo = tv_et_device_other_devcie_info.getText().toString().trim();
//		if (TextUtils.isEmpty(newDeviceUser) || TextUtils.isEmpty(newGraphicsCardInfo) || TextUtils.isEmpty(newInternalMemoryInfo) || TextUtils.isEmpty(newDeviceOtherInfo)) {
//			toastShort("选项内容不能为空");
//		}
		if (newDeviceUser.equals(mDeviceManageItem.getDeviceUser()) && newDevicePosition.equals(mDeviceManageItem.getDevicePosition()) 
				&& newGraphicsCardInfo.equals(mDeviceManageItem.getDeviceGraphicsCard()) 
				&& newInternalMemoryInfo.equals(mDeviceManageItem.getDeviceInternalMemory()) 
				&& newDeviceOtherInfo.equals(mDeviceManageItem.getDeviceOtherInfo())) {
			toastShort("无内容更新");
		} else {
			DeviceBack deviceBack = new DeviceBack();
			Device device = new Device();
			device.setId(mDeviceManageItem.getDeviceID());
			device.setDeviceUser(newDeviceUser);
			device.setPosition(newDevicePosition);
			device.setGraphicsCard(newGraphicsCardInfo);
			device.setInternalMemory(newInternalMemoryInfo);
			device.setOtherInfo(newDeviceOtherInfo);
			deviceBack.setDevice(device);
			new GetDeviceInfoApi(this).updateDeviceInfo(deviceBack, new HttpCallback() {
				
				@Override
				public void onSuccess(Object o) {
					// TODO Auto-generated method stub
					String result = (String) o;
					toastShort(result);
					DeviceReviseActivity.this.finish();
				}
				
				@Override
				public void onFail(String errorCode, String errorMsg) {
					// TODO Auto-generated method stub
					toastShort(errorMsg);
				}
			});
		}
	}
}
