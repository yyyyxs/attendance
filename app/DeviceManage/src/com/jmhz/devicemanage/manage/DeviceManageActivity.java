package com.jmhz.devicemanage.manage;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.DeviceManageAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.DeviceManageItem;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;

public class DeviceManageActivity extends BaseActivity implements
		OnItemClickListener, OnScrollListener {

	private ListView mDeviceManage = null;
	private List<DeviceManageItem> mDeviceManageItems = new ArrayList<DeviceManageItem>();
	private List<DeviceManageItem> mDeviceManageItems_search = new ArrayList<DeviceManageItem>();
	private DeviceManageAdapter mDeviceManageAdapter = null;
	private ImageButton mBtnSearch = null;
	private ImageButton mBtnClose = null;
	private EditText mEtContent = null;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private boolean mLoading = false;// 刷新标识
	private Boolean isUpdate = false;// 更新标识 
	private Button btn_reload = null;
	private ImageView iv_blank = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_manage);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWight();
		mDeviceManageAdapter = new DeviceManageAdapter(mDeviceManageItems, this);
		mDeviceManage.setOnItemClickListener(this);
		mDeviceManage.setOnScrollListener(this);
		mDeviceManage.setAdapter(mDeviceManageAdapter);
		try {
			initListView();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (isUpdate) {
			Log.i("codingWw", "重新加载数据");
			mDeviceManageItems.clear();
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
	
	private void initWight() {
		// TODO Auto-generated method stub
		mDeviceManage = (ListView) findViewById(R.id.lv_device_info);
		btn_reload = (Button) findViewById(R.id.bt_reload);
		iv_blank = (ImageView) findViewById(R.id.blank);
		btn_reload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					initListView();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mBtnSearch = (ImageButton) findViewById(R.id.ibtn_enter_search);
		mBtnClose = (ImageButton) findViewById(R.id.ibtn_close);
		mEtContent = (EditText) findViewById(R.id.et_content);
		
		mBtnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(mEtContent.getText().toString())) {
					toastShort("编号为空！");
					return;
				}
				mDeviceManageItems_search.clear();
				for (DeviceManageItem deviceManageItem : mDeviceManageItems) {
					if (mEtContent.getText().toString().equals(deviceManageItem.getDeviceID() + "")) {
						mDeviceManageItems_search.add(deviceManageItem);
					}
				}
				toastShort("共" + mDeviceManageItems_search.size() + "记录符合");
				mDeviceManageAdapter = new DeviceManageAdapter(mDeviceManageItems_search, DeviceManageActivity.this);
				mDeviceManage.setOnItemClickListener(DeviceManageActivity.this);
				mDeviceManage.setOnScrollListener(DeviceManageActivity.this);
				mDeviceManage.setAdapter(mDeviceManageAdapter);				
			}
		});
		mBtnClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(mEtContent.getText().toString())) {
					return;
				}
				mEtContent.setText("");
				mDeviceManageAdapter = new DeviceManageAdapter(mDeviceManageItems, DeviceManageActivity.this);
				mDeviceManage.setOnItemClickListener(DeviceManageActivity.this);
				mDeviceManage.setOnScrollListener(DeviceManageActivity.this);
				mDeviceManage.setAdapter(mDeviceManageAdapter);
			}
		});
		
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.device_manage);
		rightButton.setVisibility(View.INVISIBLE);
		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void initListView() throws Exception{
		// TODO Auto-generated method stub
		DeviceBack deviceBack = new DeviceBack();
		deviceBack.setCurPage(mPage);
		deviceBack.setCurRows(pageSize);
		new GetDeviceInfoApi(this).getAllDeviceInfo(deviceBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				DeviceBack deviceBack = (DeviceBack) o;
				rowTotal = deviceBack.getTotal();
				List<Device> deviceList = deviceBack.getDevices();
				toastShort("获取" + deviceList.size() + "条数据,总共" + rowTotal);
				for (Device device : deviceList) {
					DeviceManageItem mDeviceManageItem = new DeviceManageItem();
					System.out.println(device);
					mDeviceManageItem.setDeviceUUID(device.getUUID());
					System.out.println(device.getUUID() + "!!!!!!!!!!!!!!!!1");
					mDeviceManageItem.setDeviceID(device.getId());
					mDeviceManageItem.setDeviceName(device.getDeviceName());
					mDeviceManageItem.setDeviceType(device.getDeviceType());
					mDeviceManageItem.setDeviceBrand(device.getBrand());
					mDeviceManageItem.setDevicePrice(device.getPrice());
					mDeviceManageItem.setDeciceBuyTime(device.getBuyTime());
					mDeviceManageItem.setDevicePosition(device.getPosition());
					mDeviceManageItem.setDeviceUser(device.getDeviceUser());
					mDeviceManageItem.setDeviceStatus(device.getStatus());
					mDeviceManageItem.setDeciceConfigInfo("点击查看详情");
					mDeviceManageItem.setDeviceCPUInfo(device.getCPU());
					mDeviceManageItem.setDeviceGraphicsCard(device.getGraphicsCard());
					mDeviceManageItem.setDeviceInternalMemory(device.getInternalMemory());
					mDeviceManageItem.setDeviceOtherInfo(device.getOtherInfo());
					mDeviceManageItems.add(mDeviceManageItem);
				}
				mDeviceManageAdapter.notifyDataSetChanged();
				btn_reload.setVisibility(View.GONE);
				iv_blank.setVisibility(View.GONE);
				if (deviceList.size() <= 0) {
					btn_reload.setVisibility(View.VISIBLE);
					iv_blank.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				toastShort(errorMsg);
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, DeviceInfoDetailActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable("DeviceManageItem",
				mDeviceManageItems.get(position));
		intent.putExtras(bundle);
		startActivity(intent);
		isUpdate = true;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				if (rowTotal < pageSize) {
					toastShort("没有更多数据！");
					return;
				}

				// 表示说 还有数据
				if (rowTotal > mDeviceManageItems.size()) {
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

	private void getMoreList(DeviceBack deviceBack) throws Exception{
		// TODO Auto-generated method stub
		new GetDeviceInfoApi(this).getAllDeviceInfo(deviceBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				DeviceBack deviceBack = (DeviceBack) o;
				List<Device> deviceList = deviceBack.getDevices();
				for (Device device : deviceList) {
					DeviceManageItem mDeviceManageItem = new DeviceManageItem();
					mDeviceManageItem.setDeviceUUID(device.getUUID());
					mDeviceManageItem.setDeviceID(device.getId());
					mDeviceManageItem.setDeviceName(device.getDeviceName());
					mDeviceManageItem.setDeviceType(device.getDeviceType());
					mDeviceManageItem.setDeviceBrand(device.getBrand());
					mDeviceManageItem.setDevicePrice(device.getPrice());
					mDeviceManageItem.setDeciceBuyTime(device.getBuyTime());
					mDeviceManageItem.setDevicePosition(device.getPosition());
					mDeviceManageItem.setDeviceUser(device.getDeviceUser());
					mDeviceManageItem.setDeviceStatus(device.getStatus());
					mDeviceManageItem.setDeciceConfigInfo("点击查看详情");
					mDeviceManageItem.setDeviceCPUInfo(device.getCPU());
					mDeviceManageItem.setDeviceGraphicsCard(device.getGraphicsCard());
					mDeviceManageItem.setDeviceInternalMemory(device.getInternalMemory());
					mDeviceManageItem.setDeviceOtherInfo(device.getOtherInfo());
					mDeviceManageItems.add(mDeviceManageItem);
				}
				toastShort("再获取" + deviceList.size() + "条数据,总共" + mDeviceManageItems.size());
				mDeviceManageAdapter.notifyDataSetChanged();
				mLoading = false;
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				toastShort("再获取数据失败,"+ errorMsg);
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			DeviceManageActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
