package com.jmhz.devicemanage.mydevices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.EnterWarehouseAdapter;
import com.jmhz.devicemanage.adapter.MyDevicesAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.enter.EnterWarehouseListActivity;
import com.jmhz.devicemanage.enter.InStockRecodeActivity;
import com.jmhz.devicemanage.http.api.DeleteDeviceInfoApi;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.manage.DeviceInfoDetailActivity;
import com.jmhz.devicemanage.model.DeviceManageItem;
import com.jmhz.devicemanage.model.EnterWarehouseItem;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.mycenter.EmailActivity;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;

public class MyDevicesListActivity extends BaseActivity implements
		OnItemClickListener, OnScrollListener {
	private MyDevicesAdapter myDevicesAdapter = null;
	private List<MyDevicesListItem> mydevicesList = new ArrayList<MyDevicesListItem>();
	private List<MyDevicesListItem> mydevicesList_search = new ArrayList<MyDevicesListItem>();
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private ImageButton mbtnSearch;
	private ImageButton mbtnClear;
	private EditText edtContent;
	private ListView myDevicesListview;
	private ImageView iv_mydevices_blank;
	private Bundle bundle;
	private Intent intent;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private boolean mLoading = false;
	private Boolean isUpdate = false;// 更新表示
	private AdapterView.AdapterContextMenuInfo menuInfo;
	private final String url = "http://59.77.236.107:8080/mobiledevicemg/getmydevicebyuserId";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_devices_list);
		// initMyDevicesArraylist();
		// Log.i("listsize","list大小："+mydevicesList.size());
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
		myDevicesAdapter = new MyDevicesAdapter(MyDevicesListActivity.this,
				mydevicesList);
		myDevicesListview.setOnItemClickListener(this);
		myDevicesListview.setOnScrollListener(this);
		myDevicesListview.setAdapter(myDevicesAdapter);
		try {
			initListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Toast.makeText(this, "共" + mydevicesList.size() + "条数据",
		// Toast.LENGTH_SHORT).show();
		// 给listview注册上下文菜单
		registerForContextMenu(myDevicesListview);
	}

	protected void onResume() {
		// TODO Auto-generated method stub
		if (isUpdate) {
			System.out.println("onResume!!!!!");
			Log.i("dingh", "重新加载数据");
			mydevicesList.clear();
			try {
				initListView();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isUpdate = false;
		}
		super.onResume();
	}

	// private void initMyDevicesArraylist()
	// {
	// MyDevicesListItem mdli1=new
	// MyDevicesListItem("t1","联想SN45","公有","美美","Y4100","中2-303","使用","2015-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli2=new
	// MyDevicesListItem("t2","华硕N43S","私有","墨墨","Y5100","中2-301","使用","2014-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli3=new
	// MyDevicesListItem("t3","联想SN41","公有","飞飞","Y3100","中5-107","报废","2011-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli4=new
	// MyDevicesListItem("t4","联想SN42","公有","花花","Y4600","中5-307","使用","2013-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli5=new
	// MyDevicesListItem("t5","联想SN42","公有","美美","Y4600","中5-307","使用","2013-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli6=new
	// MyDevicesListItem("t6","联想SN42","公有","美美","Y4600","中5-307","使用","2013-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli7=new
	// MyDevicesListItem("t7","联想SN42","公有","美美","Y4600","中5-307","使用","2013-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli8=new
	// MyDevicesListItem("t8","联想SN42","公有","美美","Y4600","中5-307","使用","2013-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli9=new
	// MyDevicesListItem("t9","联想SN42","公有","美美","Y4600","中5-307","使用","2013-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// MyDevicesListItem mdli10=new
	// MyDevicesListItem("t10","联想SN42","公有","美美","Y4600","中5-307","使用","2013-10-1","因特尔 i5-3470","内存：8G","Nvidia GeForce GT 620","固态硬盘250G");
	// mydevicesList.add(mdli1);
	// mydevicesList.add(mdli2);
	// mydevicesList.add(mdli3);
	// mydevicesList.add(mdli4);
	// mydevicesList.add(mdli5);
	// mydevicesList.add(mdli6);
	// mydevicesList.add(mdli7);
	// mydevicesList.add(mdli8);
	// mydevicesList.add(mdli9);
	// mydevicesList.add(mdli10);
	//
	//
	// }
	private void initListView() throws Exception {
		// TODO Auto-generated method stub
		DeviceBack deviceBack = new DeviceBack();
		deviceBack.setCurPage(mPage);
		deviceBack.setCurRows(pageSize);
		new GetDeviceInfoApi(this).getUserDeviceInfo(deviceBack,
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						iv_mydevices_blank.setVisibility(View.GONE);
						// TODO Auto-generated method stub
						DeviceBack deviceBack = (DeviceBack) o;
						rowTotal = deviceBack.getTotal();
						List<Device> deviceList = deviceBack.getDevices();
						toastShort("获取" + deviceList.size() + "条数据,总共"
								+ rowTotal);
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

							// DeviceManageItem mDeviceManageItem = new
							// DeviceManageItem();
							// mDeviceManageItem.setDeviceID(device.getId());
							// mDeviceManageItem.setDeviceName(device.getDeviceName());
							// mDeviceManageItem.setDeviceType(device.getDeviceType());
							// mDeviceManageItem.setDeviceBrand(device.getBrand());
							// mDeviceManageItem.setDevicePrice(device.getPrice());

							mydevicesList.add(myDeviceListItem);
						}
						myDevicesAdapter.notifyDataSetChanged();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						toastShort(errorMsg);
						iv_mydevices_blank.setVisibility(View.VISIBLE);
					}
				});

	}

	private void initWidget() {
		mbtnSearch = (ImageButton) findViewById(R.id.ibtn_mydevices_search);
		mbtnClear = (ImageButton) findViewById(R.id.search_content_clear);
		edtContent = (EditText) findViewById(R.id.edt_search_content);
		iv_mydevices_blank = (ImageView) findViewById(R.id.iv_mydevices_blank);
		myDevicesListview = (ListView) findViewById(R.id.mydevices_list);
		mbtnSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(edtContent.getText().toString())) {
					toastShort("编号为空！");
					return;
				}
				mydevicesList_search.clear();
				for (MyDevicesListItem ew : mydevicesList) {
					if (edtContent.getText().toString()
							.equals(Integer.toString(ew.getDeviceId()))) {
						mydevicesList_search.add(ew);
					}
					// if (ew.getDeviceId()!=-1)
					// mydevicesList_search.add(ew);
				}
				toastShort("共" + mydevicesList_search.size() + "记录符合");
				myDevicesAdapter = new MyDevicesAdapter(
						MyDevicesListActivity.this, mydevicesList_search);

				myDevicesListview
						.setOnItemClickListener(MyDevicesListActivity.this);
				myDevicesListview
						.setOnScrollListener(MyDevicesListActivity.this);
				myDevicesListview.setAdapter(myDevicesAdapter);

			}
		});
		mbtnClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(edtContent.getText().toString()))
					edtContent.setText("");
				mydevicesList_search.clear();
				// toastShort("共" + mydevicesList_search.size() + "记录符合");
				myDevicesAdapter = new MyDevicesAdapter(
						MyDevicesListActivity.this, mydevicesList);

				myDevicesListview
						.setOnItemClickListener(MyDevicesListActivity.this);
				myDevicesListview
						.setOnScrollListener(MyDevicesListActivity.this);
				myDevicesListview.setAdapter(myDevicesAdapter);

			}
		});


	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText("我的设备列表");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MyDevicesListActivity.this.finish();
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MyDevicesListActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		isUpdate = true;
		Intent intent = new Intent(this, MyDeviceDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("MyDeviceDetail", mydevicesList.get(position));
		intent.putExtras(bundle);
		startActivity(intent);

	}

	// 添加xml上下文菜单
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		super.onCreateContextMenu(menu, v, menuInfo);
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.mydeviceslist, menu);
	}

	// x响应上下文菜单操作
	public boolean onContextItemSelected(MenuItem item) {
		menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {

		case R.id.fault:
			isUpdate = true;
			intent = new Intent(MyDevicesListActivity.this,
					FailureReportingActivity.class);
			bundle = new Bundle();
			// AdapterView.AdapterContextMenuInfo menuInfo =
			// (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

			// menuInfo.position; //这个就是当前的index
			bundle.putString("applyAgain", "false");
			bundle.putSerializable("MyDeviceDetail",
					mydevicesList.get(menuInfo.position));
			// bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
			intent.putExtras(bundle);
			startActivity(intent);
			// Toast.makeText(MyDevicesListActivity.this,
			// "故障升级",Toast.LENGTH_SHORT).show();
			break;
		case R.id.upgrade:
			isUpdate = true;
			intent = new Intent(MyDevicesListActivity.this,
					UpgradeReportingActivity.class);
			bundle = new Bundle();
			// AdapterView.AdapterContextMenuInfo menuInfo1 =
			// (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
			bundle.putString("applyAgain", "false");
			bundle.putSerializable("MyDeviceDetail",
					mydevicesList.get(menuInfo.position));
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.update:
			isUpdate = true;
			intent = new Intent(MyDevicesListActivity.this,
					MyDeviceUpdateActivity.class);
			bundle = new Bundle();
			// AdapterView.AdapterContextMenuInfo menuInfo2 =
			// (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
			bundle.putString("applyAgain", "false");
			bundle.putSerializable("MyDeviceDetail",
					mydevicesList.get(menuInfo.position));
			// bundle.putString("MyDeviceDetail",mydevicesList.get(menuInfo.position).getDevicePlace());
			intent.putExtras(bundle);
			startActivity(intent);
//			Toast.makeText(MyDevicesListActivity.this, "跳转到更新设备信息界面",
//					Toast.LENGTH_SHORT).show();
			break;
		case R.id.delete:
			isUpdate = true;
			new AlertDialog.Builder(MyDevicesListActivity.this)
					.setTitle("提示")
					.setMessage("确定删除该设备？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// Toast.makeText(MyDevicesListActivity.this,
									// "设备删除成功！",
									// Toast.LENGTH_SHORT).show();
									try {
										deleteDevice(mydevicesList
												.get(menuInfo.position));
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

								}
							}).show();

			break;

		}
		return super.onContextItemSelected(item);
	}

	protected void deleteDevice(MyDevicesListItem myDevicesListItem)
			throws Exception {
		// TODO Auto-generated method stub
		Device device = new Device();
		device.setId(myDevicesListItem.getDeviceId());
		new DeleteDeviceInfoApi(this).deleteDeviceInfo(device,
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// TODO Auto-generated method stub
						String result = (String) o;
						toastShort(result);
						if (isUpdate)
							Log.i("dingh", "ture");
						isUpdate = true;
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
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				if (rowTotal < pageSize) {
					toastShort("没有更多数据！");
					return;
				}

				// 表示说 还有数据
				if (rowTotal > mydevicesList.size()) {
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
						toastShort("加载中，请稍后！");
					}
				} else {
					toastShort("没有更多数据！");
				}
			}
		}

	}

	private void getMoreList(DeviceBack deviceBack) throws Exception {
		// TODO Auto-generated method stub
		new GetDeviceInfoApi(this).getUserDeviceInfo(deviceBack,
				new HttpCallback() {

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
							mydevicesList.add(myDeviceListItem);
						}
						toastShort("再获取" + deviceList.size() + "条数据,总共"
								+ mydevicesList.size());
						// toastShort("再获取" + deviceList.size() + "条数据,总共" +
						// rowTotal);
						myDevicesAdapter.notifyDataSetChanged();
						mLoading = false;
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						toastShort("再获取数据失败," + errorMsg);
						mLoading = false;
						mPage -= 1;
					}
				});
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

}
