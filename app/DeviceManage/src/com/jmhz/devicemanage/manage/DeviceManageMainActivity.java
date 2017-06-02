package com.jmhz.devicemanage.manage;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

public class DeviceManageMainActivity extends BaseActivity {
	
	private TextView tv_et_device_manage;
	private TextView tv_et_device_repair;
	private TextView tv_et_device_update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devicemanage_main);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initView();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		tv_et_device_manage = (TextView) findViewById(R.id.tv_et_device_manage);
		tv_et_device_repair = (TextView) findViewById(R.id.tv_et_device_repair);
		tv_et_device_update = (TextView) findViewById(R.id.tv_et_device_update);
		initTableItem(tv_et_device_manage, DeviceManageActivity.class);
		initTableItem(tv_et_device_repair, DeviceRepairActivity.class);
		initTableItem(tv_et_device_update, DeviceUpdateActivity.class);
		
	}
	
	private void initTableItem(TextView view, final Class<?> gotoActivity) {
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.tv_et_device_manage:
					startActivityWithClass(gotoActivity);
					break;
				case R.id.tv_et_device_repair:
					startActivityWithClass(gotoActivity);
					break;
				case R.id.tv_et_device_update:
					startActivityWithClass(gotoActivity);
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.device_manage);
		rightButton.setVisibility(View.INVISIBLE);
		leftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
