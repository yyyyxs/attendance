package com.jmhz.devicemanage.enter;

import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.AttributesFindAdapter;
import com.jmhz.devicemanage.adapter.MyDevicesAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.mydevices.MyDeviceDetailActivity;
import com.jmhz.devicemanage.mydevices.MyDevicesListActivity;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class AttributesFindActivity extends Activity implements OnItemClickListener, OnScrollListener {

	private AttributesFindAdapter attributesFindAdapter =null;
	private List<MyDevicesListItem> myfindList = new ArrayList<MyDevicesListItem>();
	private static final String m[]={"类型","状态","地点","年份"};
	private static final String a[]={"公有","私有"};
	private static final String b[]={"使用","废弃","维修","升级"};//设备状态,0表示使用中，1表示废弃，2表示维修，3表示升级
	private static final String c[]={"301","302","303","304","305"};
	private static final String d[]={"2010","2011","2012","2013","2014","2015"};
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private boolean mLoading = false;
	private Spinner spinner;
	private Spinner spinner1;
	private Button searchattri;
	private ArrayAdapter<String> adapter;
	private ArrayAdapter<String> adapter1;
	private ListView findlist;
	private final String url = "http://59.77.236.107:8080/mobiledevicemg/getdeviceByCondition";
	private String content;
	private String content1;
	private String condition1;
	private String condition2;
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attributes_find);
		findlist=(ListView) findViewById(R.id.findlist);
		spinner=(Spinner) findViewById(R.id.spinner);
		spinner1=(Spinner) findViewById(R.id.spinner1);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				Toast.makeText(this, "您的选择是"+m[arg2], Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
//				System.out.println(arg0+"!!!!!!!!!!!!!!!");
				switch(arg2){
				case 0:
					adapter1=new ArrayAdapter<String>(AttributesFindActivity.this, android.R.layout.simple_spinner_item, a);
					adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinner1.setAdapter(adapter1);
					spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(AttributesFindActivity.this, "您的选择是"+spinner.getSelectedItem().toString()+a[arg2], Toast.LENGTH_SHORT).show();  
					       condition1 = "deviceType";
							switch(arg2){
							case 0:
								condition2 = TypeTranslateUtils.deviceType("公有") + "";
							break;
							
							case 1:
								condition2 = TypeTranslateUtils.deviceType("私有") + "";
							break;
							}
								
					
						}
						
						

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
						
					});
					break;
					
				case 1:
					adapter1=new ArrayAdapter<String>(AttributesFindActivity.this, android.R.layout.simple_spinner_item, b);
					adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinner1.setAdapter(adapter1);
					spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(AttributesFindActivity.this, "您的选择是"+spinner.getSelectedItem().toString()+b[arg2], Toast.LENGTH_SHORT).show();
						condition1="status";
						switch(arg2){
						  case 0:
								condition2 = StatusTranslateUtils.deviceStatus("使用") + "";
							break;
							
							case 1:
								condition2 = StatusTranslateUtils.deviceStatus("废弃") + "";
							break;
							
							case 2:
								condition2 = StatusTranslateUtils.deviceStatus("维修") + "";
							break;
							
							case 3:
								condition2 = StatusTranslateUtils.deviceStatus("升级") + "";
							break;
							  
							
						}
						
						
						
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
						
					});
					break;
					
				case 2:
					adapter1=new ArrayAdapter<String>(AttributesFindActivity.this, android.R.layout.simple_spinner_item, c);
					adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinner1.setAdapter(adapter1);
					spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(AttributesFindActivity.this, "您的选择是"+spinner.getSelectedItem().toString()+c[arg2], Toast.LENGTH_SHORT).show();
						condition1="position";
						switch(arg2){
						  case 0:
								condition2 = PositionTranslateUtils.devicePosition("301") + "";
							break;
							
							case 1:
								condition2 = PositionTranslateUtils.devicePosition("302") + "";
							break;
							
							case 2:
								condition2 = PositionTranslateUtils.devicePosition("303") + "";
							break;
							  
							case 3:
								condition2 = PositionTranslateUtils.devicePosition("304") + "";
							break;
							
							case 4:
								condition2 = PositionTranslateUtils.devicePosition("305") + "";
							break;
							
							
						}
						
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
						
					});
					break;
					
				case 3:
					adapter1=new ArrayAdapter<String>(AttributesFindActivity.this, android.R.layout.simple_spinner_item, d);
					adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinner1.setAdapter(adapter1);
					spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							Toast.makeText(AttributesFindActivity.this, "您的选择是"+spinner.getSelectedItem().toString()+d[arg2], Toast.LENGTH_SHORT).show();
						condition1="buyYear";
						condition2=d[arg2];
						
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
						
					});
					break;
					
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		attributesFindAdapter = new AttributesFindAdapter(AttributesFindActivity.this,
				myfindList);
		findlist.setOnItemClickListener(this);
		
		findlist.setOnScrollListener(this);
		findlist.setAdapter(attributesFindAdapter);
		
		searchattri=(Button) findViewById(R.id.searchattri);
		searchattri.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myfindList.clear();
				
				result();
			}

		
		});
	}
	
	protected void result() {
//		content = (String) spinner.getSelectedItem();
//		content1 = (String) spinner1.getSelectedItem();
//		System.out.println(content + "!!!!!!!!!");
//		System.out.println(content1 + "!!!!!!!!");
//		String condition1 = TypeTranslateUtils.deviceType(content) + "";
//		String condition2 = TypeTranslateUtils.deviceType(content1) + "";
//        System.out.println(condition2 + "!!!!!!!");
		// TODO Auto-generated method stub
		DeviceBack deviceBack = new DeviceBack();
		deviceBack.setCurPage(mPage);
		deviceBack.setCurRows(pageSize);
//		deviceBack.setCondition1("buyYear");
//		deviceBack.setCondition2("2015");
		try {
			new GetDeviceInfoApi(this).getSearchInfoByID(deviceBack, condition1,condition2,
					new HttpCallback() {

						@Override
						public void onSuccess(Object o) {
							
							// TODO Auto-generated method stub
							DeviceBack deviceBack = (DeviceBack) o;
							rowTotal = deviceBack.getTotal();
							List<Device> deviceList = deviceBack.getDevices();
							
							Toast.makeText(AttributesFindActivity.this, "获取" + deviceList.size() + "条数据,总共"
									+ rowTotal, Toast.LENGTH_SHORT).show();
							for (Device device : deviceList) {
								MyDevicesListItem myDeviceListItem = new MyDevicesListItem();
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
								myfindList.add(myDeviceListItem);
			
							}
							attributesFindAdapter.notifyDataSetChanged();
						}

						@Override
						public void onFail(String errorCode, String errorMsg) {
							Toast.makeText(AttributesFindActivity.this, "查找失败", Toast.LENGTH_SHORT).show();
							myfindList.clear();
							attributesFindAdapter.notifyDataSetChanged();
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}


	
	
	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				if (rowTotal < pageSize) {
					Toast.makeText(AttributesFindActivity.this, "没有更多数据！", Toast.LENGTH_SHORT);
					return;
				}

				// 表示说 还有数据
				if (rowTotal > myfindList.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						DeviceBack deviceBack = new DeviceBack();
						deviceBack.setCurPage(mPage);
						deviceBack.setCurRows(pageSize);
						try {
							getMoreList(deviceBack);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						Toast.makeText(AttributesFindActivity.this, "加载中，请稍后！", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(AttributesFindActivity.this, "没有更多数据！", Toast.LENGTH_SHORT).show();
				}
			}
		}

	
	}

	private void getMoreList(DeviceBack deviceBack) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			new GetDeviceInfoApi(this).getSearchInfoByID(deviceBack, condition1,condition2,
					new HttpCallback()  {

						@Override
						public void onSuccess(Object o) {
							// TODO Auto-generated method stub
							DeviceBack deviceBack = (DeviceBack) o;
							List<Device> deviceList = deviceBack.getDevices();
							for (Device device : deviceList) {
								MyDevicesListItem myDeviceListItem = new MyDevicesListItem();
								myDeviceListItem.setDeviceId(device.getId());
								myDeviceListItem.setDeviceName(device.getBrand()
										+ device.getDeviceName()
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
								myfindList.add(myDeviceListItem);
							}
							Toast.makeText(AttributesFindActivity.this, "再获取" + deviceList.size() + "条数据,总共"
									+ myfindList.size(), Toast.LENGTH_SHORT).show();
							// toastShort("再获取" + deviceList.size() + "条数据,总共" +
							// rowTotal);
							attributesFindAdapter.notifyDataSetChanged();
							mLoading = false;
						}

						@Override
						public void onFail(String errorCode, String errorMsg) {
							// TODO Auto-generated method stub
							Toast.makeText(AttributesFindActivity.this, "再获取数据失败," + errorMsg, Toast.LENGTH_SHORT).show();
							mLoading = false;
							mPage -= 1;
						}
					});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		
		Intent intent = new Intent(this, SearchDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("MyDeviceDetail", myfindList.get(position));
		intent.putExtras(bundle);
		startActivity(intent);
	}

	
}

	

