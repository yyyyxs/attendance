package com.jmhz.devicemanage.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.DeviceUpdateReportInfo;
import com.jmhz.devicemanage.utils.DealTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;

public class DeviceUpdateAuditReportActivity extends BaseActivity {
	
	private TextView tv_et_device_num;
	private TextView tv_et_deivce_name;
	private TextView tv_et_device_type;
	private TextView tv_et_device_user;
	private TextView tv_et_device_deal_status;
	private TextView tv_et_device_update_cost;
	private TextView tv_et_device_change;
	private TextView tv_et_device_update_result;
	private DeviceUpdateReportInfo mDeviceUpdateReportInfo;
	private Intent intent;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deviceupdate_report);
		initializeMemberVariable(findViewById(R.id.mainHead));
		intent = getIntent();
		bundle = intent.getExtras();
		mDeviceUpdateReportInfo = bundle.getParcelable("mDeviceUpdateReportInfo");
		initWidget();	
	}
	
	private void initWidget() {
		// TODO Auto-generated method stub
		tv_et_device_num = (TextView) findViewById(R.id.tv_et_device_num_updatereport);
		tv_et_deivce_name = (TextView) findViewById(R.id.tv_et_deivce_name_updatereport);
		tv_et_device_type = (TextView) findViewById(R.id.tv_et_device_type_updatereport);
		tv_et_device_user = (TextView) findViewById(R.id.tv_et_device_user_updatereport);
		tv_et_device_deal_status = (TextView) findViewById(R.id.tv_et_device_deal_status_updatereport);
		tv_et_device_update_cost = (TextView) findViewById(R.id.tv_et_device_update_cost);
		tv_et_device_change = (TextView) findViewById(R.id.tv_et_device_change_updatereport);
		tv_et_device_update_result = (TextView) findViewById(R.id.tv_et_device_update_result);
		tv_et_device_num.setText(mDeviceUpdateReportInfo.getApplyID());
		tv_et_deivce_name.setText(mDeviceUpdateReportInfo.getDeviceName());
		int deviceType = Integer.parseInt(mDeviceUpdateReportInfo.getDeviceType());
		tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
		tv_et_device_user.setText(mDeviceUpdateReportInfo.getDeviceName());
		int deviceDealStatus = Integer.parseInt(mDeviceUpdateReportInfo.getDeviceDealStatus());
		tv_et_device_deal_status.setText(DealTranslateUtils.dealStatus(deviceDealStatus));
		tv_et_device_update_cost.setText(mDeviceUpdateReportInfo.getDeviceUpdateCost());
		tv_et_device_change.setText(mDeviceUpdateReportInfo.getDeviceChange());
		tv_et_device_update_result.setText(mDeviceUpdateReportInfo.getDeviceUpdateResult());
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText("升级报告");
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
