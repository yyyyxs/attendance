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
import com.jmhz.devicemanage.model.DeviceUpdateInfo;
import com.jmhz.devicemanage.utils.ApproveTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.upgradeApply;

public class DeviceUpdateReportActivity extends BaseActivity implements OnClickListener, OnCheckedChangeListener {
	
	private TextView tv_et_device_num;
	private TextView tv_et_deivce_name;
	private TextView tv_et_device_type;
	private TextView tv_et_device_status;
	private TextView tv_et_device_repair_user;
	private TextView tv_et_device_repair_applytime;
	private TextView tv_et_device_repair_reason;
	private TextView tv_et_device_repair_status;
	private RadioGroup rg_advice_update;
	private LinearLayout ll_audit_opinion_update;
	private EditText et_audit_opinion_update;
	private Button btn_device_audit;
	private Intent intent;
	private Bundle bundle;
	private DeviceUpdateInfo mDeviceUpdateInfo;
	private String approve = "1";
	private String approveRemark = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deviceupdate_applyreport);
		initializeMemberVariable(findViewById(R.id.mainHead));
		intent = getIntent();
		bundle = intent.getExtras();
		mDeviceUpdateInfo = bundle.getParcelable("DeviceUpdateInfo");
		initWight();
	}
	
	private void initWight() {
		// TODO Auto-generated method stub
		tv_et_device_num = (TextView) findViewById(R.id.tv_et_device_num_applyreport_update);
		tv_et_deivce_name = (TextView) findViewById(R.id.tv_et_deivce_name_applyreport_update);
		tv_et_device_type = (TextView) findViewById(R.id.tv_et_device_type_applyreport_update);
		tv_et_device_status = (TextView) findViewById(R.id.tv_et_device_status_applyreport_update);
		tv_et_device_repair_user = (TextView) findViewById(R.id.tv_et_device_repair_user_applyreport_update);
		tv_et_device_repair_applytime = (TextView) findViewById(R.id.tv_et_device_repair_applytime_applyreport_update);
		tv_et_device_repair_reason = (TextView) findViewById(R.id.tv_et_device_repair_reason_applyreport_update);
		tv_et_device_repair_status = (TextView) findViewById(R.id.tv_et_device_repair_status_applyreport_update);
		rg_advice_update = (RadioGroup) findViewById(R.id.rg_advice_update);
		ll_audit_opinion_update = (LinearLayout) findViewById(R.id.ll_audit_opinion_update);
		et_audit_opinion_update = (EditText) findViewById(R.id.et_audit_opinion_update);
		btn_device_audit = (Button) findViewById(R.id.btn_device_audit_update);
		tv_et_device_num.setText(mDeviceUpdateInfo.getDeviceID());
		tv_et_deivce_name.setText(mDeviceUpdateInfo.getDeviceName());
		int deviceType = Integer.parseInt(mDeviceUpdateInfo.getDeviceType());
		tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
		int deviceStatus = Integer.parseInt(mDeviceUpdateInfo.getDeviceStatus());
		tv_et_device_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		tv_et_device_repair_user.setText(mDeviceUpdateInfo.getDeviceUser());
		tv_et_device_repair_reason.setText(mDeviceUpdateInfo.getDeviceUpdateReason());
		tv_et_device_repair_applytime.setText(mDeviceUpdateInfo.getDeviceUpdateApplyTime());
		int deviceApprove = Integer.parseInt(mDeviceUpdateInfo.getDeviceUpdateApprove());
		tv_et_device_repair_status.setText(ApproveTranslateUtils.deviceApprove(deviceApprove));
		rg_advice_update.setOnCheckedChangeListener(this);
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
				startActivity(new Intent(DeviceUpdateReportActivity.this, DeviceUpdateActivity.class));
				finish();
			}
		});
		rightButton.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			commitUpdateApplyAudit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void commitUpdateApplyAudit() throws Exception {
		// TODO Auto-generated method stub
		upgradeApply upgradeApply = new upgradeApply();
		upgradeApply.setId(Integer.parseInt(mDeviceUpdateInfo.getApplyID()));
		upgradeApply.setStatus(mDeviceUpdateInfo.getDeviceStatus());
		upgradeApply.setApprove(approve);
		upgradeApply.setApproveRemark(approveRemark);
		new GetApplyInfoApi(this).pushUpdateApply(upgradeApply, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				String result = (String) o;
				toastShort(result);
				startActivity(new Intent(DeviceUpdateReportActivity.this, DeviceUpdateActivity.class));
				DeviceUpdateReportActivity.this.finish();
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
		case R.id.rb_agree_update:
			ll_audit_opinion_update.setVisibility(View.GONE);
			approve = "1";
			approveRemark = "";
			break;
		case R.id.rb_disagree_update:
			ll_audit_opinion_update.setVisibility(View.VISIBLE);
			approve = "2";
			approveRemark = et_audit_opinion_update.getText().toString().trim();
			break;
		default:
			break;
		}
	}

}
