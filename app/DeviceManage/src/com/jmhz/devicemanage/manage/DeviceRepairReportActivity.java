package com.jmhz.devicemanage.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetApplyInfoApi;
import com.jmhz.devicemanage.model.DeviceRepairInfo;
import com.jmhz.devicemanage.utils.ApproveTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.FaultApply;

public class DeviceRepairReportActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener{
	
	private TextView tv_et_device_num;
	private TextView tv_et_deivce_name;
	private TextView tv_et_device_type;
	private TextView tv_et_device_status;
	private TextView tv_et_device_repair_user;
	private TextView tv_et_device_repair_applytime;
	private TextView tv_et_device_repair_reason;
	private TextView tv_et_device_repair_status;
	private RadioGroup rg_advice;
	private LinearLayout ll_audit_opinion;
	private EditText et_audit_opinion;
	private Button btn_device_audit;
	private Intent intent;
	private Bundle bundle;
	private DeviceRepairInfo mDeviceRepairInfo;
	private String approve = "1";
	private String approveRemark = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devicerepair_applyreport);
		initializeMemberVariable(findViewById(R.id.mainHead));
		intent = getIntent();
		bundle = intent.getExtras();
		mDeviceRepairInfo = bundle.getParcelable("DeviceRepairInfo");
		initWight();
	}
	
	private void initWight() {
		// TODO Auto-generated method stub
		tv_et_device_num = (TextView) findViewById(R.id.tv_et_device_num_applyreport);
		tv_et_deivce_name = (TextView) findViewById(R.id.tv_et_deivce_name_applyreport);
		tv_et_device_type = (TextView) findViewById(R.id.tv_et_device_type_applyreport);
		tv_et_device_status = (TextView) findViewById(R.id.tv_et_device_status_applyreport);
		tv_et_device_repair_user = (TextView) findViewById(R.id.tv_et_device_repair_user_applyreport);
		tv_et_device_repair_applytime = (TextView) findViewById(R.id.tv_et_device_repair_applytime_applyreport);
		tv_et_device_repair_reason = (TextView) findViewById(R.id.tv_et_device_repair_reason_applyreport);
		tv_et_device_repair_status = (TextView) findViewById(R.id.tv_et_device_repair_status_applyreport);
		rg_advice = (RadioGroup) findViewById(R.id.rg_advice);
		ll_audit_opinion = (LinearLayout) findViewById(R.id.ll_audit_opinion);
		et_audit_opinion = (EditText) findViewById(R.id.et_audit_opinion);
		btn_device_audit = (Button) findViewById(R.id.btn_device_audit);
		tv_et_device_num.setText(mDeviceRepairInfo.getDeviceID());
		tv_et_deivce_name.setText(mDeviceRepairInfo.getDeviceName());
		int deviceType = Integer.parseInt(mDeviceRepairInfo.getDeviceType());
		tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
		int deviceStatus = Integer.parseInt(mDeviceRepairInfo.getDeviceStatus());
		tv_et_device_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		tv_et_device_repair_user.setText(mDeviceRepairInfo.getDeviceUser());
		tv_et_device_repair_applytime.setText(mDeviceRepairInfo.getDeviceRepairApplyTime());
		tv_et_device_repair_reason.setText(mDeviceRepairInfo.getDeviceRepairReason());
		int deviceApprove = Integer.parseInt(mDeviceRepairInfo.getDeviceRepairApprove());
		tv_et_device_repair_status.setText(ApproveTranslateUtils.deviceApprove(deviceApprove));
		rg_advice.setOnCheckedChangeListener(this);
		btn_device_audit.setOnClickListener(this);
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.device_repair_report);
		leftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(DeviceRepairReportActivity.this, DeviceRepairActivity.class));
				finish();
			}
		});
		rightButton.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub 
		try {
			commitRepairApplyAudit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void commitRepairApplyAudit() throws Exception {
		// TODO Auto-generated method stub
		FaultApply faultApply = new FaultApply();
		faultApply.setId(Integer.parseInt(mDeviceRepairInfo.getApplyID()));
		faultApply.setStatus(mDeviceRepairInfo.getDeviceStatus());
		faultApply.setApprove(approve);
		faultApply.setApproveRemark(approveRemark);
		new GetApplyInfoApi(this).pushRepairApply(faultApply, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				String result = (String) o;
				toastShort(result);
				startActivity(new Intent(DeviceRepairReportActivity.this, DeviceRepairActivity.class));
				DeviceRepairReportActivity.this.finish();
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				toastShort(errorMsg);
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rb_agree:
			ll_audit_opinion.setVisibility(View.GONE);
			approve = "1";
			approveRemark = "";
			break;
		case R.id.rb_disagree:
			ll_audit_opinion.setVisibility(View.VISIBLE);
			approve = "2";
			approveRemark = et_audit_opinion.getText().toString().trim();
			break;
		default:
			break;
		}
	}

}
