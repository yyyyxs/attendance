package com.jmhz.devicemanage.manage;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.fragment.UpdateDeviceAuditFragment;
import com.jmhz.devicemanage.fragment.UpdateDeviceNotAuditFragment;

public class DeviceUpdateActivity extends BaseActivity implements OnClickListener{
	
	private TextView deviceUpdateNotAudit;
	private TextView deviceUpdateAudit;
	private UpdateDeviceNotAuditFragment updateDeviceNotAuditFragment;
	private UpdateDeviceAuditFragment updateDeviceAuditFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_device_update);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
		initFragment();
	}
	
	private void initWidget() {
		// TODO Auto-generated method stub
		deviceUpdateNotAudit = (TextView) findViewById(R.id.tab_deviceupdate_notaudit);
		deviceUpdateNotAudit.setOnClickListener(this);
		deviceUpdateAudit = (TextView) findViewById(R.id.tab_deviceupdate_audit);
		deviceUpdateAudit.setOnClickListener(this);
		deviceUpdateNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
		deviceUpdateAudit.setBackgroundResource(R.drawable.white_normal);
	}

	private void initFragment() {
		// TODO Auto-generated method stub
		updateDeviceNotAuditFragment = (UpdateDeviceNotAuditFragment) getFragmentManager().findFragmentById(R.id.fm_device_update_notaudit);
		updateDeviceAuditFragment = (UpdateDeviceAuditFragment) getFragmentManager().findFragmentById(R.id.fm_device_update_audit);
		updateDeviceNotAuditFragment.getView().setVisibility(View.VISIBLE);
		updateDeviceAuditFragment.getView().setVisibility(View.GONE);
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.device_update);
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
		case R.id.tab_deviceupdate_notaudit:
			deviceUpdateNotAudit.setBackgroundResource(R.drawable.headtab_pressed);
			deviceUpdateAudit.setBackgroundResource(R.drawable.white_normal);
			updateDeviceNotAuditFragment.getView().setVisibility(View.VISIBLE);
			updateDeviceAuditFragment.getView().setVisibility(View.GONE);
			break;
		case R.id.tab_deviceupdate_audit:
			deviceUpdateNotAudit.setBackgroundResource(R.drawable.white_normal);
			deviceUpdateAudit.setBackgroundResource(R.drawable.headtab_pressed);
			updateDeviceNotAuditFragment.getView().setVisibility(View.GONE);
			updateDeviceAuditFragment.getView().setVisibility(View.VISIBLE);
		default:
			break;
		}
	}

}
