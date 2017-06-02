package com.jmhz.devicemanage.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.DeviceRepairReportInfo;
import com.jmhz.devicemanage.utils.DealTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;

public class DeviceAuditReportActivity extends BaseActivity {
	
	private TextView tv_et_device_num;
	private TextView tv_et_deivce_name;
	private TextView tv_et_device_type;
	private TextView tv_et_device_user;
	private TextView tv_et_device_deal_status;
	private TextView tv_et_device_repair_cost;
	private TextView tv_et_device_change;
	private TextView tv_et_device_repair_result;
	private DeviceRepairReportInfo mDeviceRepairReportInfo;
	private Intent intent;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_repairreport_info);
		initializeMemberVariable(findViewById(R.id.mainHead));
		intent = getIntent();
		bundle = intent.getExtras();
		mDeviceRepairReportInfo = bundle.getParcelable("mDeviceRepairReportInfo");
		System.out.println("DeviceAuditReportActivity:" + mDeviceRepairReportInfo);
		initWidget();
	}

	private void initWidget() {
		// TODO Auto-generated method stub
		tv_et_device_num = (TextView) findViewById(R.id.tv_et_device_num_repairreport);
		tv_et_deivce_name = (TextView) findViewById(R.id.tv_et_deivce_name_repairreport);
		tv_et_device_type = (TextView) findViewById(R.id.tv_et_device_type_repairreport);
		tv_et_device_user = (TextView) findViewById(R.id.tv_et_device_user_repairreport);
		tv_et_device_deal_status = (TextView) findViewById(R.id.tv_et_device_deal_status);
		tv_et_device_repair_cost = (TextView) findViewById(R.id.tv_et_device_repair_cost);
		tv_et_device_change = (TextView) findViewById(R.id.tv_et_device_change);
		tv_et_device_repair_result = (TextView) findViewById(R.id.tv_et_device_repair_result);
		tv_et_device_num.setText(mDeviceRepairReportInfo.getApplyID());
		tv_et_deivce_name.setText(mDeviceRepairReportInfo.getDeviceName());
		int deviceType = Integer.parseInt(mDeviceRepairReportInfo.getDeviceType());
		tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
		tv_et_device_user.setText(mDeviceRepairReportInfo.getDeviceUser());
		int deviceDealStatus = Integer.parseInt(mDeviceRepairReportInfo.getDeviceDealStatus());
		tv_et_device_deal_status.setText(DealTranslateUtils.dealStatus(deviceDealStatus));
		tv_et_device_repair_cost.setText(mDeviceRepairReportInfo.getDeviceRepairCost());
		tv_et_device_change.setText(mDeviceRepairReportInfo.getDeviceChange());
		tv_et_device_repair_result.setText(mDeviceRepairReportInfo.getDeviceRepairResult());
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText("维修报告");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
