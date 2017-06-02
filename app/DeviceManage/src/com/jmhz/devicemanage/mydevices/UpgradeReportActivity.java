package com.jmhz.devicemanage.mydevices;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesApplyInfo;
import com.jmhz.devicemanage.web.Faultrepair;
import com.jmhz.devicemanage.web.upgraderepair;
public class UpgradeReportActivity extends BaseActivity{

    Button ur_submit;
    Button ur_cancel;
    
    private EditText edt_ur_device_uuid;
    private EditText edt_ur_device_name;
    private  EditText edt_ur_device_type;
    private  EditText edt_ur_device_user;
    private Spinner spinner_ur_repair_state;
    private EditText edt_ur_repair_item;
    private EditText edt_ur_repair_spend;
    private EditText edt_ur_repair_result;
	private Intent intent;
	private Bundle bundle;
	private MyDevicesApplyInfo myDevicesApplyInfo;
	String dealStatus = "";
	String cost = "";
	String upgradepart = "";
	String upgradeResult = "";
	private final String upgradeReportUrl = "http://59.77.236.107:8080/mobileupgrade/addupgradelog";
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_my_devices_upgrade_report);
    	initializeMemberVariable(findViewById(R.id.mainHead));
    	initWight();
    }
	private void initWight() {
		intent=getIntent();
		bundle=intent.getExtras();
		myDevicesApplyInfo=(MyDevicesApplyInfo) bundle.getSerializable("myDevicesApplyInfo");
		ur_submit=(Button) findViewById(R.id.ur_submit);
		ur_cancel=(Button) findViewById(R.id.ur_cancel);
		edt_ur_device_uuid=(EditText) findViewById(R.id.edt_ur_device_uuid);
		edt_ur_device_name=(EditText) findViewById(R.id.edt_ur_device_name);
		edt_ur_device_type=(EditText) findViewById(R.id.edt_ur_device_type);
		edt_ur_device_user=(EditText) findViewById(R.id.edt_ur_device_user);
		spinner_ur_repair_state=(Spinner) findViewById(R.id.spinner_ur_repair_state);
		edt_ur_repair_item=(EditText) findViewById(R.id.edt_ur_repair_item);
		edt_ur_repair_spend=(EditText) findViewById(R.id.edt_ur_repair_spend);
		edt_ur_repair_result=(EditText) findViewById(R.id.edt_ur_repair_result);
		
		edt_ur_device_uuid.setText(myDevicesApplyInfo.getDeviceId()+"");
		edt_ur_device_uuid.setEnabled(false);
		edt_ur_device_name.setText(myDevicesApplyInfo.getDeviceName());
		edt_ur_device_name.setEnabled(false);
		edt_ur_device_type.setText(myDevicesApplyInfo.getDeviceType());
		edt_ur_device_type.setEnabled(false);
		edt_ur_device_user.setText(myDevicesApplyInfo.getDeviceUser());
		edt_ur_device_user.setEnabled(false);
		
		ur_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(UpgradeReportActivity.this)
				  .setTitle("提示")
				  .setMessage("确定提交？")
				  .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int whichButton) {
//		                	Toast.makeText(getApplicationContext(), "提交成功！",
//		                		     Toast.LENGTH_SHORT).show();
//		                	UpgradeReportActivity.this.finish();
		                	dealStatus = spinner_ur_repair_state
									.getSelectedItem().toString();
							cost = edt_ur_repair_spend.getText()
									.toString();
							upgradepart = edt_ur_repair_item
									.getText().toString();
							upgradeResult = edt_ur_repair_result
									.getText().toString();
							if (!dealStatus.equals("")
									&& !cost.equals("")
									&& !upgradepart.equals("")
									&& !upgradeResult.equals("")) {
								try {
									upgradeReport();
								} catch (Exception e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
							} else {
								toastShort("升级报告内容不能为空！");
							}
		                }
		            })
		            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int whichButton) {

		                    
		                }
		            })
				  .show();
				
			}
		});
		ur_cancel.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				finish();
				
			}
		});
		
		
	}
	
	protected void upgradeReport() throws Exception {
		upgraderepair repair = new upgraderepair();
		repair.setApplyId(myDevicesApplyInfo.getApplyId());
		repair.setDeviceName(myDevicesApplyInfo.getDeviceName());
		repair.setDeviceType(myDevicesApplyInfo.getDeviceType());
		repair.setDeviceUser(myDevicesApplyInfo.getDeviceUser());
		repair.setDealStatus(dealStatus);
		repair.setUpgradepart(upgradepart);
		repair.setCost(cost);
		repair.setupgradeResult(upgradeResult);
		new GetDeviceInfoApi(this).upgradeReport(repair,
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						// DeviceBack deviceBack = (DeviceBack) o;
						// update = deviceBack.isSuccess();
						String result = (String) o;
						toastShort(result);
						// Toast.makeText(getApplicationContext(), "更新成功！",
						// Toast.LENGTH_SHORT).show();
						UpgradeReportActivity.this.finish();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						toastShort(errorMsg);
					}
				});
	}
	protected void initializeActivityHead() {
		centreText.setText("升级报告");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_BACK) {
			 UpgradeReportActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}

}
