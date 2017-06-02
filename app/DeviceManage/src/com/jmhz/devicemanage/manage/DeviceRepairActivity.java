package com.jmhz.devicemanage.manage;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.fragment.RepairDeviceAuditFragment;
import com.jmhz.devicemanage.fragment.RepairDeviceNotAuditFragment;
/**
 * ==================================================
 * 
 * Copyright： 福州大学创新实验室 版权所有（C）2015
 * 
 * @author： 魏德华
 * 
 * @date: 2015-10-24 上午10:13:15
 * 
 * @version： V1.0
 * 
 * @description：维修审核
 * 
 * ==================================================
 **/
public class DeviceRepairActivity extends BaseActivity implements OnClickListener{
	
	private TextView deviceRepairNotAudit;
	private TextView deviceRepairAudit;
	private RepairDeviceNotAuditFragment repairDeviceNotAuditFragment;
	private RepairDeviceAuditFragment repairDeviceAuditFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_device_repair);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
		initFragment();
	}
	
	private void initWidget() {
		// TODO Auto-generated method stub
		deviceRepairNotAudit = (TextView) findViewById(R.id.tab_devicerepair_notaudit);
		deviceRepairNotAudit.setOnClickListener(this);
		deviceRepairAudit = (TextView) findViewById(R.id.tab_devicerepair_audit);
		deviceRepairAudit.setOnClickListener(this);
		deviceRepairNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
		deviceRepairAudit.setBackgroundResource(R.drawable.white_normal);
	}

	private void initFragment() {
		// TODO Auto-generated method stub
		repairDeviceNotAuditFragment = (RepairDeviceNotAuditFragment) getFragmentManager().findFragmentById(R.id.fm_device_repair_notaudit);
		repairDeviceAuditFragment = (RepairDeviceAuditFragment) getFragmentManager().findFragmentById(R.id.fm_device_repair_audit);
		repairDeviceNotAuditFragment.getView().setVisibility(View.VISIBLE);
		repairDeviceAuditFragment.getView().setVisibility(View.GONE);
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.repair_approve);
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
		switch (v.getId()) {
		case R.id.tab_devicerepair_notaudit:
			deviceRepairNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
			deviceRepairAudit.setBackgroundResource(R.drawable.white_normal);
			repairDeviceNotAuditFragment.getView().setVisibility(View.VISIBLE);
			repairDeviceAuditFragment.getView().setVisibility(View.GONE);
			break;
		case R.id.tab_devicerepair_audit:
			deviceRepairNotAudit.setBackgroundResource(R.drawable.white_normal);
			deviceRepairAudit.setBackgroundResource(R.drawable.headtab_pressed);
			repairDeviceNotAuditFragment.getView().setVisibility(View.GONE);
			repairDeviceAuditFragment.getView().setVisibility(View.VISIBLE);
		default:
			break;
		}
	}
}
