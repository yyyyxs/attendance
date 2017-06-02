package com.jmhz.devicemanage.manage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.DeleteDeviceInfoApi;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.DeviceManageItem;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;

public class DeviceInfoDetailActivity extends BaseActivity implements OnItemSelectedListener, OnClickListener{

	private TextView tv_et_device_num;
	private TextView tv_et_deivce_name;
	private TextView tv_et_device_type;
	private TextView tv_et_device_price;
	private TextView tv_et_device_buy_time;
	private TextView tv_et_device_position;
	private TextView tv_et_device_user;
	private TextView tv_et_device_status;
	private TextView tv_et_device_config;
	private Spinner tv_device_config;
	private Button btn_device_audit_detail;
	private Intent intent;
	private Bundle bundle;
	private DeviceManageItem mDeviceManageItem;
	private PopupWindow popupWindow;
	private View root;
	private ArrayAdapter<String> adapter = null;
	private String[] spinnerArray = {"CPU信息", "显卡信息", "内存信息","其他信息"};
	private Boolean isUpdate = false;
	private Button btn_reload_detailinfo = null;
	private ImageView iv_blank_detailinfo = null;
	private LinearLayout ll_detail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deviceinfo_detail);
		initializeMemberVariable(findViewById(R.id.mainHead));
		intent = getIntent();
		bundle = intent.getExtras();
		mDeviceManageItem = bundle.getParcelable("DeviceManageItem");
		adapter = new ArrayAdapter<String>(DeviceInfoDetailActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
		initWight();
		initPopupWindow();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (isUpdate) {
			Log.i("codingWw", "重新加载数据!!!!!");
			try {
				initData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isUpdate = false;
		}
		super.onResume();
	}
	
	private void initData() throws Exception {
		// TODO Auto-generated method stub
		DeviceBack deviceBack = new DeviceBack();
		Device device = new Device();
		device.setId(mDeviceManageItem.getDeviceID());
		deviceBack.setDevice(device);
		new GetDeviceInfoApi(this).getDeviceInfoByID(deviceBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				ll_detail.setVisibility(View.VISIBLE);
				btn_device_audit_detail.setVisibility(View.VISIBLE);
				DeviceBack deviceBack = (DeviceBack) o;
				final Device device = deviceBack.getDevice();
				tv_et_device_num.setText(device.getId() + "");
				tv_et_deivce_name.setText(device.getDeviceName());
				int deviceType = Integer.parseInt(device.getDeviceType());
				tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
				tv_et_device_price.setText(device.getPrice());
				tv_et_device_buy_time.setText(device.getBuyTime());
				int devicePosition = Integer.parseInt(device.getPosition());
				tv_et_device_position.setText(PositionTranslateUtils.devicePosition(devicePosition));
				tv_et_device_user.setText(device.getDeviceUser());
				int deviceStatus = Integer.parseInt(device.getStatus());
				tv_et_device_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
				tv_et_device_config.setText(device.getCPU());
				tv_device_config.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub
						switch (position) {
						case 0:
							tv_et_device_config.setText(device.getCPU());
							break;
						case 1:
							tv_et_device_config.setText(device.getGraphicsCard());
							break;
						case 2:
							tv_et_device_config.setText(device.getInternalMemory());
							break;
						case 3:
							tv_et_device_config.setText(device.getOtherInfo());
							break;
						default:
							break;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
				});
				btn_reload_detailinfo.setVisibility(View.GONE);
				iv_blank_detailinfo.setVisibility(View.GONE);
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				toastShort(errorMsg);
				ll_detail.setVisibility(View.GONE);
				btn_device_audit_detail.setVisibility(View.GONE);
				btn_reload_detailinfo.setVisibility(View.VISIBLE);
				iv_blank_detailinfo.setVisibility(View.VISIBLE);
			}
		});
	}

	private void initPopupWindow() {
		// TODO Auto-generated method stub
		View contentView = LayoutInflater.from(DeviceInfoDetailActivity.this)
				.inflate(R.layout.activity_pop_dialog_menu, null);
		TextView tvUpdate = (TextView) contentView
				.findViewById(R.id.tv_update_deviceinfo);
		TextView tvDelete = (TextView) contentView
				.findViewById(R.id.tv_delete_deviceinfo);
		TextView tvCancle = (TextView) contentView.findViewById(R.id.tv_cancle);
		tvUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updateDeviceInfo();
				if (popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});
		tvDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteDeviceInfo();
			}
		});
		tvCancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});

		// 全屏显示，将内容设置在底部
		popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		popupWindow.setOutsideTouchable(false);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setAnimationStyle(R.style.pop_animation);

	}
	
	private void initWight() {
		// TODO Auto-generated method stub
		btn_reload_detailinfo = (Button) findViewById(R.id.bt_reload_detailinfo);
		iv_blank_detailinfo = (ImageView) findViewById(R.id.blank_detailinfo);
		btn_reload_detailinfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					initData();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		root = findViewById(R.id.ll_root);
		ll_detail = (LinearLayout) findViewById(R.id.ll_detail);
		tv_et_device_num = (TextView) findViewById(R.id.tv_et_device_num);
		tv_et_deivce_name = (TextView) findViewById(R.id.tv_et_deivce_name);
		tv_et_device_type = (TextView) findViewById(R.id.tv_et_device_type);
		tv_et_device_price = (TextView) findViewById(R.id.tv_et_device_price);
		tv_et_device_buy_time = (TextView) findViewById(R.id.tv_et_device_buy_time);
		tv_et_device_position = (TextView) findViewById(R.id.tv_et_device_position);
		tv_et_device_user = (TextView) findViewById(R.id.tv_et_device_user);
		tv_et_device_status = (TextView) findViewById(R.id.tv_et_device_status);
		tv_device_config = (Spinner) findViewById(R.id.tv_device_config);
		tv_device_config.setAdapter(adapter);
		tv_et_device_config = (TextView) findViewById(R.id.tv_et_device_config);
		btn_device_audit_detail = (Button) findViewById(R.id.btn_device_audit_detail);
		tv_et_device_num.setText(mDeviceManageItem.getDeviceID() + "");
		tv_et_deivce_name.setText(mDeviceManageItem.getDeviceName());
		int deviceType = Integer.parseInt(mDeviceManageItem.getDeviceType());
		tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
		tv_et_device_price.setText(mDeviceManageItem.getDevicePrice());
		tv_et_device_buy_time.setText(mDeviceManageItem.getDeciceBuyTime());
		int devicePosition = Integer.parseInt(mDeviceManageItem.getDevicePosition());
		tv_et_device_position.setText(PositionTranslateUtils.devicePosition(devicePosition));
		tv_et_device_user.setText(mDeviceManageItem.getDeviceUser());
		int deviceStatus = Integer.parseInt(mDeviceManageItem.getDeviceStatus());
		tv_et_device_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		tv_et_device_config.setText(mDeviceManageItem.getDeviceCPUInfo());
		btn_device_audit_detail.setOnClickListener(this);
		tv_device_config.setOnItemSelectedListener(this);
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
	

	protected void updateDeviceInfo() {
		// TODO Auto-generated method stub
		isUpdate = true;
		Intent intent = new Intent(DeviceInfoDetailActivity.this, DeviceReviseActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	protected void deleteDeviceInfo() {
		// TODO Auto-generated method stub
		AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
		mBuilder.setTitle("提示");
		mBuilder.setMessage("你确定要删除吗？");
		mBuilder.setIcon(R.drawable.ic_launcher);
		mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//完成用户的操作，比如删除数据，提示请求等
				try {
					deleteDevice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//取消用户的操作
				dialog.dismiss();
			}
		});
		AlertDialog alertDialog = mBuilder.create();
		alertDialog.show();//让对话框显示
	}

	protected void deleteDevice() throws Exception {
		// TODO Auto-generated method stub
		Device device = new Device();
		device.setId(mDeviceManageItem.getDeviceID());
		new DeleteDeviceInfoApi(this).deleteDeviceInfo(device, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				String result = (String) o;
				toastShort(result);
				DeviceInfoDetailActivity.this.finish();
			}
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				toastShort(errorMsg);
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			tv_et_device_config.setText(mDeviceManageItem.getDeviceCPUInfo());
			break;
		case 1:
			tv_et_device_config.setText(mDeviceManageItem.getDeviceGraphicsCard());
			break;
		case 2:
			tv_et_device_config.setText(mDeviceManageItem.getDeviceInternalMemory());
			break;
		case 3:
			tv_et_device_config.setText(mDeviceManageItem.getDeviceOtherInfo());
			break;
		default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		popupWindow.showAtLocation(root, Gravity.BOTTOM, 0, 0);
	}

}
