package com.jmhz.devicemanage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.DeleteDeviceInfoApi;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.mydevices.FailureReportingActivity;
import com.jmhz.devicemanage.mydevices.MyDeviceDetailActivity;
import com.jmhz.devicemanage.mydevices.MyDeviceUpdateActivity;
import com.jmhz.devicemanage.mydevices.UpgradeReportingActivity;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;
import com.zxing.activity.CaptureActivity;

public class BarCodeTestActivity extends Activity  implements OnItemSelectedListener{
    /** Called when the activity is first created. */
//	private TextView resultTextView;
//	private EditText qrStrEditText;
//	private ImageView qrImgImageView;
	private String uuid;
	private TextView md_id;
	private TextView md_name;
	private TextView md_place;
	private TextView md_user;
	private TextView md_type;
	private TextView md_state;
	//private TextView md_price;
	private TextView md_buytime;
	private Spinner code_search_information;
	private TextView search_information;
	private LinearLayout search_result;
	private Button btn_operate_barcode;
	private ImageView bk_search;
	private Intent intent;
	private Bundle bundle;
	private ArrayAdapter<String> adapter = null;
	private String[] spinnerArray = {"CPU信息", "显卡信息", "内存信息","设备其他信息"};
	private MyDevicesListItem myDeviceListItem;
	private final String url = "http://59.77.236.107:8080/mobiledevicemg/getmydevicebyUUID";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
//        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
////        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
//        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
    	initwight();
        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				//打开扫描界面扫描条形码或二维码
				Intent openCameraIntent = new Intent(BarCodeTestActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent,0);// 实现activity的跳转
//				finish();
			}
		});
      
//        Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
//        generateQRCodeButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				try {
//					String contentString = qrStrEditText.getText().toString();
//					if (!contentString.equals("")) {
//						//根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小(350)
//						Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
//						qrImgImageView.setImageBitmap(qrCodeBitmap);
//					}else {
//						Toast.makeText(BarCodeTestActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
//					}
//					
//				} catch (WriterException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
    }
@Override
protected void onResume() {
	search_result.setVisibility(View.GONE);
	bk_search.setVisibility(View.VISIBLE);
	super.onResume();
}
	protected void Menu() {
		// TODO Auto-generated method stub
		this.openOptionsMenu();
	}

	private void initwight() {
		// TODO Auto-generated method stub
		adapter = new ArrayAdapter<String>(BarCodeTestActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
		btn_operate_barcode=(Button) findViewById(R.id.btn_operate_barcode);
		md_id = (TextView) findViewById(R.id.search_id);
		md_name = (TextView) findViewById(R.id.search_name);
		md_type = (TextView) findViewById(R.id.search_type);
		//md_price = (TextView) findViewById(R.id.search_price);
		md_user= (TextView) findViewById(R.id.search_user);
		md_buytime = (TextView) findViewById(R.id.search_buytime);
		md_place = (TextView) findViewById(R.id.search_place);
		md_state = (TextView) findViewById(R.id.search_state);
		code_search_information=(Spinner) findViewById(R.id.code_search_information);
		search_information=(TextView) findViewById(R.id.search_information);
		search_result=(LinearLayout) findViewById(R.id.search_result);
		search_result.setVisibility(View.GONE);
		code_search_information.setAdapter(adapter);
		code_search_information.setOnItemSelectedListener(this);
		bk_search=(ImageView) findViewById(R.id.bk_search);
		btn_operate_barcode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Menu();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//处理扫描结果（在界面上显示）
		if (resultCode == 0) {
			
			uuid = data.getStringExtra("ucode");
		
			initDeviceInfo();
		
		}
	}

	private void initDeviceInfo() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		DeviceBack deviceBack = new DeviceBack();
		Device device = new Device();
		device.setUUID(uuid);
		deviceBack.setDevice(device);
		try {

			new GetDeviceInfoApi(this).getSingleDeviceInfo(deviceBack,
					new HttpCallback() {

						@Override
						public void onSuccess(Object o) {
							myDeviceListItem=new MyDevicesListItem();
							bk_search.setVisibility(View.GONE);
							search_result.setVisibility(View.VISIBLE);
							// TODO Auto-generated method stub
							DeviceBack deviceBack = (DeviceBack) o;
							Device device = deviceBack.getDevice();
							myDeviceListItem.setDeviceId(device.getId());
							myDeviceListItem.setDeviceName(device.getBrand()
									+ device.getDeviceName());
							myDeviceListItem.setDeviceType(device
									.getDeviceType());
							myDeviceListItem.setDeviceUser(device
									.getDeviceUser());
							myDeviceListItem.setDeviceUser(device
									.getDeviceUser());
							myDeviceListItem.setDevicePrice(device.getPrice());
							myDeviceListItem.setDevicePlace(device
									.getPosition());
							myDeviceListItem.setDeviceState(device.getStatus());
							myDeviceListItem.setDeviceTime(device.getBuyTime());
							myDeviceListItem.setDeviceCPUInfo(device.getCPU());
							myDeviceListItem.setDeviceInternalMemory(device
									.getInternalMemory());
							myDeviceListItem.setDeviceGraphicsCard(device
									.getGraphicsCard());
							myDeviceListItem.setDeviceOtherInfo(device
									.getOtherInfo());
							md_id.setText(device.getId() + "");
							md_name.setText(device.getDeviceName());
							int deviceType = Integer.parseInt(device
									.getDeviceType());
							md_type.setText(TypeTranslateUtils
									.deviceType(deviceType));
							//md_price.setText(device.getPrice());
							md_buytime.setText(device.getBuyTime());
							int devicePosition = Integer.parseInt(device
									.getPosition());
							md_place.setText(PositionTranslateUtils
									.devicePosition(devicePosition));
							md_user.setText(device.getDeviceUser());
							int deviceStatus = Integer.parseInt(device
									.getStatus());
							md_state.setText(StatusTranslateUtils
									.deviceStatus(deviceStatus));

							// DeviceManageItem mDeviceManageItem = new
							// DeviceManageItem();
							// mDeviceManageItem.setDeviceID(device.getId());
							// mDeviceManageItem.setDeviceName(device.getDeviceName());
							// mDeviceManageItem.setDeviceType(device.getDeviceType());
							// mDeviceManageItem.setDeviceBrand(device.getBrand());
							// mDeviceManageItem.setDevicePrice(device.getPrice());

						}

						@Override
						public void onFail(String errorCode, String errorMsg) {
							// TODO Auto-generated method st
							Toast.makeText(BarCodeTestActivity.this, errorMsg, Toast.LENGTH_SHORT);
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	 	
	 	getMenuInflater().inflate(R.menu.search_operate, menu);
	 	return true;
	 }
	 public boolean onMenuItemSelected(int featureId, MenuItem item) {
	 	switch(item.getItemId())
	 	{
	 	case R.id.search_update:
	 		intent=new Intent(BarCodeTestActivity.this,MyDeviceUpdateActivity.class);
	 		bundle = new Bundle();
	 		bundle.putString("applyAgain", "false");
	 		bundle.putSerializable("MyDeviceDetail",myDeviceListItem);
	 		//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
	 		intent.putExtras(bundle);
	 		startActivity(intent);
	 		//BarCodeTestActivity.this.finish();
	 		//Toast.makeText(MyDeviceDetailActivity.this, "跳转到更新设备信息界面",Toast.LENGTH_SHORT).show();
	 		break;
	 	case R.id.search_delete:
	 		  new AlertDialog.Builder(BarCodeTestActivity.this)
	 		  .setTitle("提示")
	 		  .setMessage("确定删除该设备？")
	 		  .setPositiveButton("确定", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int whichButton) {
	                 	try {
	 						deleteDevice();
	 					} catch (Exception e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
//	                 	Toast.makeText(MyDeviceDetailActivity.this, "设备删除成功！",
//	                 		     Toast.LENGTH_SHORT).show();
	               
	                 }
	             })
	             .setNegativeButton("取消", new DialogInterface.OnClickListener() {
	                 public void onClick(DialogInterface dialog, int whichButton) {

	                     
	                 }
	             })
	 		  .show();
	 		
	 		break;
	 	}
	 	return super.onMenuItemSelected(featureId, item);
	 }

	protected void deleteDevice() throws Exception {
		// TODO Auto-generated method stub
		Device device = new Device();
		device.setId(myDeviceListItem.getDeviceId());
		new DeleteDeviceInfoApi(this).deleteDeviceInfo(device,
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						String result = (String) o;
						Toast.makeText(BarCodeTestActivity.this, result, Toast.LENGTH_SHORT);
						search_result.setVisibility(View.GONE);
						bk_search.setVisibility(View.VISIBLE);
						//BarCodeTestActivity.this.finish();
						// MyDevicesListActivity.this.finish();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						Toast.makeText(BarCodeTestActivity.this, errorMsg, Toast.LENGTH_SHORT);
					}
				});
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		switch(arg2)
		{
		case 0:
			search_information.setText(myDeviceListItem.getDeviceCPUInfo());
			break;
		case 1:
			search_information.setText(myDeviceListItem.getDeviceGraphicsCard());
			break;
		case 2:
			search_information.setText(myDeviceListItem.getDeviceInternalMemory());
			break;
		case 3:
			search_information.setText(myDeviceListItem.getDeviceOtherInfo());
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


}