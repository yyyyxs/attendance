package com.jmhz.devicemanage.mydevices;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.DeleteDeviceInfoApi;
import com.jmhz.devicemanage.manage.DeviceInfoDetailActivity;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;

public class MyDeviceDetailActivity extends BaseActivity implements OnItemSelectedListener{
	private Spinner spinner_md_configinfo;
	private TextView md_id;
	private TextView md_name;
	private TextView md_type;
	private TextView md_price;
	private TextView md_buytime;
	private TextView md_place;
	private TextView md_user;
	private TextView md_state;
	private TextView md_configinfo;
	private ArrayAdapter<String> adapter = null;
	private String[] spinnerArray = {"CPU信息", "显卡信息", "内存信息","设备其他信息"};
	private Intent intent;
	private Bundle bundle;
	private MyDevicesListItem myDeviceItem;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_devices_detail);
		initializeMemberVariable(findViewById(R.id.mainHead));
		adapter = new ArrayAdapter<String>(MyDeviceDetailActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
		initwight();
	}

	private void initwight() {
		intent=getIntent();
		bundle=intent.getExtras();
		myDeviceItem=(MyDevicesListItem) bundle.getSerializable("MyDeviceDetail");
		md_id=(TextView)findViewById(R.id.md_id);
		md_name=(TextView)findViewById(R.id.md_name);
		md_type=(TextView)findViewById(R.id.md_type);
		md_price=(TextView)findViewById(R.id.md_price);
		md_buytime=(TextView)findViewById(R.id.md_buytime);
		md_place=(TextView)findViewById(R.id.md_place);
		md_user=(TextView)findViewById(R.id.md_user);
		md_state=(TextView)findViewById(R.id.md_state);
		md_configinfo=(TextView)findViewById(R.id.md_configinfo);
		spinner_md_configinfo=(Spinner) findViewById(R.id.spinner_md_configinfo);
		spinner_md_configinfo.setAdapter(adapter);
		md_id.setText(myDeviceItem.getDeviceId()+"");
		md_name.setText(myDeviceItem.getDeviceName());
		int deviceType = Integer.parseInt(myDeviceItem.getDeviceType());
		md_type.setText(TypeTranslateUtils.deviceType(deviceType));
		md_price.setText(myDeviceItem.getDevicePrice());
		md_user.setText(myDeviceItem.getDeviceUser());
		md_buytime.setText(myDeviceItem.getDeviceTime());
		int devicePosition = Integer.parseInt(myDeviceItem.getDevicePlace());
		md_place.setText(PositionTranslateUtils.devicePosition(devicePosition));
		int deviceStatus = Integer.parseInt(myDeviceItem.getDeviceState());
		md_state.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		md_configinfo.setText(myDeviceItem.getDeviceCPUInfo());
		spinner_md_configinfo.setOnItemSelectedListener(this);
		
	}
	protected  void Menu() {
		 this.openOptionsMenu();
		
	}
	protected void initializeActivityHead() {
		centreText.setText("设备详情");
		//rightButton.setVisibility(View.GONE);
		rightButton.setText("操作");
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 //Menu();
				PopupMenu popupMenu=new PopupMenu(MyDeviceDetailActivity.this,arg0);
				popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Auto-generated method stub
						switch(item.getItemId())
						{
						
						case R.id.fault:
							intent=new Intent(MyDeviceDetailActivity.this,FailureReportingActivity.class);
							bundle = new Bundle();
							bundle.putString("applyAgain", "false");
							bundle.putSerializable("MyDeviceDetail",myDeviceItem);
							//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
							intent.putExtras(bundle);
							startActivity(intent);
							//Toast.makeText(MyDevicesListActivity.this, "故障升级",Toast.LENGTH_SHORT).show();
							break;
						case R.id.upgrade:
							intent=new Intent(MyDeviceDetailActivity.this,UpgradeReportingActivity.class);
							bundle = new Bundle();
							bundle.putString("applyAgain", "false");
							bundle.putSerializable("MyDeviceDetail",myDeviceItem);
							//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
							intent.putExtras(bundle);
							startActivity(intent);
							MyDeviceDetailActivity.this.finish();
							break;
						case R.id.update:
							intent=new Intent(MyDeviceDetailActivity.this,MyDeviceUpdateActivity.class);
							bundle = new Bundle();
							bundle.putString("applyAgain", "false");
							bundle.putSerializable("MyDeviceDetail",myDeviceItem);
							//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
							intent.putExtras(bundle);
							startActivity(intent);
							MyDeviceDetailActivity.this.finish();
							//Toast.makeText(MyDeviceDetailActivity.this, "跳转到更新设备信息界面",Toast.LENGTH_SHORT).show();
							break;
						case R.id.delete:
							  new AlertDialog.Builder(MyDeviceDetailActivity.this)
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
//					                	Toast.makeText(MyDeviceDetailActivity.this, "设备删除成功！",
//					                		     Toast.LENGTH_SHORT).show();
					              
					                }
					            })
					            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
					                public void onClick(DialogInterface dialog, int whichButton) {

					                    
					                }
					            })
							  .show();
							
							break;
						}
						return false;
					}
				});
				MenuInflater inflater=popupMenu.getMenuInflater();
				inflater.inflate(R.menu.mydevice_detail, popupMenu.getMenu());
				popupMenu.show();
			}
			

		});
		 //this.openOptionsMenu();
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

	}
 @Override
public boolean onCreateOptionsMenu(Menu menu) {
	
	getMenuInflater().inflate(R.menu.mydevice_detail, menu);
	return true;
}
public boolean onMenuItemSelected(int featureId, MenuItem item) {
	switch(item.getItemId())
	{
	
	case R.id.fault:
		intent=new Intent(MyDeviceDetailActivity.this,FailureReportingActivity.class);
		bundle = new Bundle();
		bundle.putString("applyAgain", "false");
		bundle.putSerializable("MyDeviceDetail",myDeviceItem);
		//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
		intent.putExtras(bundle);
		startActivity(intent);
		//Toast.makeText(MyDevicesListActivity.this, "故障升级",Toast.LENGTH_SHORT).show();
		break;
	case R.id.upgrade:
		intent=new Intent(MyDeviceDetailActivity.this,UpgradeReportingActivity.class);
		bundle = new Bundle();
		bundle.putString("applyAgain", "false");
		bundle.putSerializable("MyDeviceDetail",myDeviceItem);
		//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
		intent.putExtras(bundle);
		startActivity(intent);
		MyDeviceDetailActivity.this.finish();
		break;
	case R.id.update:
		intent=new Intent(MyDeviceDetailActivity.this,MyDeviceUpdateActivity.class);
		bundle = new Bundle();
		bundle.putString("applyAgain", "false");
		bundle.putSerializable("MyDeviceDetail",myDeviceItem);
		//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
		intent.putExtras(bundle);
		startActivity(intent);
		MyDeviceDetailActivity.this.finish();
		//Toast.makeText(MyDeviceDetailActivity.this, "跳转到更新设备信息界面",Toast.LENGTH_SHORT).show();
		break;
	case R.id.delete:
		  new AlertDialog.Builder(MyDeviceDetailActivity.this)
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
//                	Toast.makeText(MyDeviceDetailActivity.this, "设备删除成功！",
//                		     Toast.LENGTH_SHORT).show();
              
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
protected void deleteDevice()
		throws Exception {
	// TODO Auto-generated method stub
	Device device = new Device();
	device.setId(myDeviceItem.getDeviceId());
	new DeleteDeviceInfoApi(this).deleteDeviceInfo(device,
			new HttpCallback() {

				@Override
				public void onSuccess(Object o) {
					// TODO Auto-generated method stub
					String result = (String) o;
					toastShort(result);
					MyDeviceDetailActivity.this.finish();
					// MyDevicesListActivity.this.finish();
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
	switch(position)
	{
	case 0:
		md_configinfo.setText(myDeviceItem.getDeviceCPUInfo());
		break;
	case 1:
		md_configinfo.setText(myDeviceItem.getDeviceGraphicsCard());
		break;
	case 2:
		md_configinfo.setText(myDeviceItem.getDeviceInternalMemory());
		break;
	case 3:
		md_configinfo.setText(myDeviceItem.getDeviceOtherInfo());
		break;
	default:
		break;
	}
	
}

@Override
public void onNothingSelected(AdapterView<?> parent) {
	// TODO Auto-generated method stub
	
}
}
