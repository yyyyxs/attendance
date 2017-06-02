package com.jmhz.devicemanage.enter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.DeleteDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.mydevices.FailureReportingActivity;
import com.jmhz.devicemanage.mydevices.MyDeviceDetailActivity;
import com.jmhz.devicemanage.mydevices.MyDeviceUpdateActivity;
import com.jmhz.devicemanage.mydevices.UpgradeReportingActivity;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;

public class SearchDetailActivity extends BaseActivity implements OnItemSelectedListener{

	private Spinner spinner_search_configinfo;
	private TextView search_id;
	private TextView search_name;
	private TextView search_type;
	private TextView search_price;
	private TextView search_buytime;
	private TextView search_place;
	private TextView search_user;
	private TextView search_state;
	private TextView search_configinfo;
	private ArrayAdapter<String> adapter = null;
	private String[] spinnerArray = {"CPU信息", "显卡信息", "内存信息","设备其他信息"};
	private Intent intent;
	private Bundle bundle;
	private MyDevicesListItem myDeviceItem;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_detail);
		initializeMemberVariable(findViewById(R.id.mainHead));
		adapter = new ArrayAdapter<String>(SearchDetailActivity.this, android.R.layout.simple_spinner_item, spinnerArray);
		initwight();
	}

	private void initwight() {
		intent=getIntent();
		bundle=intent.getExtras();
		myDeviceItem=(MyDevicesListItem) bundle.getSerializable("MyDeviceDetail");
		search_id=(TextView)findViewById(R.id.search_id);
		search_name=(TextView)findViewById(R.id.search_name);
		search_type=(TextView)findViewById(R.id.search_type);
		search_price=(TextView)findViewById(R.id.search_price);
		search_buytime=(TextView)findViewById(R.id.search_buytime);
		search_user=(TextView)findViewById(R.id.search_user);
		search_place=(TextView)findViewById(R.id.search_place);
		search_state=(TextView)findViewById(R.id.search_state);
		search_configinfo=(TextView)findViewById(R.id.search_configinfo);
		spinner_search_configinfo=(Spinner) findViewById(R.id.spinner_search_configinfo);
		spinner_search_configinfo.setAdapter(adapter);
		search_id.setText(myDeviceItem.getDeviceId()+"");
		search_name.setText(myDeviceItem.getDeviceName());
		search_user.setText(myDeviceItem.getDeviceUser());
		int deviceType = Integer.parseInt(myDeviceItem.getDeviceType());
		search_type.setText(TypeTranslateUtils.deviceType(deviceType));
		search_price.setText(myDeviceItem.getDevicePrice());
		search_buytime.setText(myDeviceItem.getDeviceTime());
		int devicePosition = Integer.parseInt(myDeviceItem.getDevicePlace());
		search_place.setText(PositionTranslateUtils.devicePosition(devicePosition));
		int deviceStatus = Integer.parseInt(myDeviceItem.getDeviceState());
		search_state.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		search_configinfo.setText(myDeviceItem.getDeviceCPUInfo());
		spinner_search_configinfo.setOnItemSelectedListener(this);
		
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
				 Menu();
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
	
	getMenuInflater().inflate(R.menu.search_detail, menu);
	return true;
}
public boolean onMenuItemSelected(int featureId, MenuItem item) {
	switch(item.getItemId())
	{
	
	

	case R.id.search_update:
		intent=new Intent(SearchDetailActivity.this,MyDeviceUpdateActivity.class);
		bundle = new Bundle();
		bundle.putString("applyAgain", "false");
		bundle.putSerializable("MyDeviceDetail",myDeviceItem);
		//bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
		intent.putExtras(bundle);
		startActivity(intent);
		SearchDetailActivity.this.finish();
		//Toast.makeText(MyDeviceDetailActivity.this, "跳转到更新设备信息界面",Toast.LENGTH_SHORT).show();
		break;
	case R.id.search_delete:
		  new AlertDialog.Builder(SearchDetailActivity.this)
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
					SearchDetailActivity.this.finish();
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
		search_configinfo.setText(myDeviceItem.getDeviceCPUInfo());
		break;
	case 1:
		search_configinfo.setText(myDeviceItem.getDeviceGraphicsCard());
		break;
	case 2:
		search_configinfo.setText(myDeviceItem.getDeviceInternalMemory());
		break;
	case 3:
		search_configinfo.setText(myDeviceItem.getDeviceOtherInfo());
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
