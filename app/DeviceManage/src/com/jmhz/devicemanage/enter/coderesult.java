//package com.jmhz.devicemanage.enter;
//
//import java.util.List;
//
//import com.jmhz.devicemanage.BaseActivity;
//import com.jmhz.devicemanage.R;
//import com.jmhz.devicemanage.callback.HttpCallback;
//import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
//import com.jmhz.devicemanage.model.MyDevicesListItem;
//import com.jmhz.devicemanage.mydevices.MyDeviceDetailActivity;
//import com.jmhz.devicemanage.utils.PositionTranslateUtils;
//import com.jmhz.devicemanage.utils.StatusTranslateUtils;
//import com.jmhz.devicemanage.utils.TypeTranslateUtils;
//import com.jmhz.devicemanage.web.Device;
//import com.jmhz.devicemanage.web.DeviceBack;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//public class coderesult extends BaseActivity {
//
//	private TextView md_id;
//	private TextView md_name;
//	private TextView md_place;
//	private TextView md_type;
//	private TextView md_state;
//	private TextView md_price;
//	private TextView md_buytime;
//	private Intent intent;
//	private Bundle bundle;
////	private MyDevicesListItem myDeviceItem;
//	private final String url = "http://59.77.236.107:8080/mobiledevicemg/getmydevicebyUUID";
//	private String uuid;
//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.coderesult);
//		initializeMemberVariable(findViewById(R.id.mainHead));
//		intent = getIntent();
//		bundle = intent.getExtras();
//		uuid = bundle.getString("ucode");
//		initwight();
//		initDeviceInfo();
//	}
//
//	private void initDeviceInfo() {
//		// TODO Auto-generated method stub
//		DeviceBack deviceBack = new DeviceBack();
//		Device device = new Device();
//		device.setUUID(uuid);
//		deviceBack.setDevice(device);
//		try {
//
//			new GetDeviceInfoApi(this).getSingleDeviceInfo(deviceBack,
//					new HttpCallback() {
//
//						@Override
//						public void onSuccess(Object o) {
//							// TODO Auto-generated method stub
//							DeviceBack deviceBack = (DeviceBack) o;
//							Device device = deviceBack.getDevice();
//							md_id.setText(device.getId() + "");
//							md_name.setText(device.getDeviceName());
//							int deviceType = Integer.parseInt(device
//									.getDeviceType());
//							md_type.setText(TypeTranslateUtils
//									.deviceType(deviceType));
//							md_price.setText(device.getPrice());
//							md_buytime.setText(device.getBuyTime());
//							int devicePosition = Integer.parseInt(device
//									.getPosition());
//							md_place.setText(PositionTranslateUtils
//									.devicePosition(devicePosition));
//							int deviceStatus = Integer.parseInt(device
//									.getStatus());
//							md_state.setText(StatusTranslateUtils
//									.deviceStatus(deviceStatus));
//
//							// DeviceManageItem mDeviceManageItem = new
//							// DeviceManageItem();
//							// mDeviceManageItem.setDeviceID(device.getId());
//							// mDeviceManageItem.setDeviceName(device.getDeviceName());
//							// mDeviceManageItem.setDeviceType(device.getDeviceType());
//							// mDeviceManageItem.setDeviceBrand(device.getBrand());
//							// mDeviceManageItem.setDevicePrice(device.getPrice());
//
//						}
//
//						@Override
//						public void onFail(String errorCode, String errorMsg) {
//							// TODO Auto-generated method stub
//							toastShort(errorMsg);
//						}
//					});
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	private void initwight() {
//		// TODO Auto-generated method stub
//
////		myDeviceItem = (MyDevicesListItem) bundle
////				.getSerializable("MyDeviceDetail");
//		md_id = (TextView) findViewById(R.id.search_id);
//		md_name = (TextView) findViewById(R.id.search_name);
//		md_type = (TextView) findViewById(R.id.search_type);
//		md_price = (TextView) findViewById(R.id.search_price);
//		md_buytime = (TextView) findViewById(R.id.search_buytime);
//		md_place = (TextView) findViewById(R.id.search_place);
//		md_state = (TextView) findViewById(R.id.search_state);
//	}
//
//	@Override
//	protected void initializeActivityHead() {
//		// TODO Auto-generated method stub
//		centreText.setText("二维码查询结果");
//
//		rightButton.setVisibility(View.GONE);
//		rightButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				finish();
//			}
//		});
//
//		// this.openOptionsMenu();
//		leftButton.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				finish();
//			}
//		});
//	}
//}
