package com.jmhz.devicemanage.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.DeviceUpdateNotAuditAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetApplyInfoApi;
import com.jmhz.devicemanage.manage.DeviceUpdateReportActivity;
import com.jmhz.devicemanage.model.DeviceUpdateInfo;
import com.jmhz.devicemanage.web.UpgradeBack;
import com.jmhz.devicemanage.web.upgradeApply;
/**
 * ==================================================
 * 
 * Copyright： 福州大学创新实验室 版权所有（C）2015
 * 
 * @author： 魏德华
 * 
 * @date: 2015-10-16 上午7:59:15
 * 
 * @version： V1.0
 * 
 * @description：未审核（设备升级）
 * 
 * ==================================================
 **/
public class UpdateDeviceNotAuditFragment extends Fragment implements OnItemClickListener, OnScrollListener {
	
	private ListView lv_device_update_notaudit = null;
	private List<DeviceUpdateInfo> mDeviceUpdateInfos = new ArrayList<DeviceUpdateInfo>();
	private DeviceUpdateNotAuditAdapter mDeviceUpdateNotAuditAdapter = null;
	private boolean mLoading = false;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private Button btn_reload = null;
	private ImageView iv_blank = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_device_update_notaudit, container, false);
		btn_reload = (Button) view.findViewById(R.id.bt_reload_notaudit_update);
		iv_blank = (ImageView) view.findViewById(R.id.blank_notaudit_update);
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
		lv_device_update_notaudit = (ListView) view.findViewById(R.id.lv_device_update_notaudit);
		lv_device_update_notaudit.setOnItemClickListener(this);
		lv_device_update_notaudit.setOnScrollListener(this);
		mDeviceUpdateNotAuditAdapter = new DeviceUpdateNotAuditAdapter(mDeviceUpdateInfos, getActivity());
		lv_device_update_notaudit.setAdapter(mDeviceUpdateNotAuditAdapter);
		try {
			initListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}
	
	private void initListView() throws Exception {
		// TODO Auto-generated method stub
		UpgradeBack upgradeBack = new UpgradeBack();
		upgradeApply upgradeApply = new upgradeApply();
		upgradeApply.setApprove("0");
		upgradeBack.setCurPage(mPage);
		upgradeBack.setCurRows(pageSize);
		upgradeBack.setUpApply(upgradeApply);
		new GetApplyInfoApi(getActivity()).getUpgradeApplyInfo(upgradeBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				lv_device_update_notaudit.setVisibility(View.VISIBLE);
				UpgradeBack upgradeBack = (UpgradeBack) o;
				List<upgradeApply> upgradeApplies = upgradeBack.getApplys();
				rowTotal = upgradeBack.getTotal();
				for (upgradeApply upgradeApply : upgradeApplies) {
					DeviceUpdateInfo mDeviceUpdateInfo = new DeviceUpdateInfo();
					mDeviceUpdateInfo.setApplyID(upgradeApply.getId() + "");
					mDeviceUpdateInfo.setDeviceID(upgradeApply.getDeviceId() + "");
					mDeviceUpdateInfo.setDeviceName(upgradeApply.getDeviceName());
					mDeviceUpdateInfo.setDeviceUser(upgradeApply.getDeviceUser());
					mDeviceUpdateInfo.setDeviceType(upgradeApply.getDeviceType());
					mDeviceUpdateInfo.setDeviceUpdateApplyTime(upgradeApply.getApplytime());
					mDeviceUpdateInfo.setDeviceUpdateReason(upgradeApply.getUpgradeDescribe());
					mDeviceUpdateInfo.setDeviceStatus(upgradeApply.getStatus());
					mDeviceUpdateInfo.setDeviceUpdateApprove(upgradeApply.getApprove());
					mDeviceUpdateInfos.add(mDeviceUpdateInfo);
				}
				mDeviceUpdateNotAuditAdapter.notifyDataSetChanged();
				btn_reload.setVisibility(View.GONE);
				iv_blank.setVisibility(View.GONE);
				if (upgradeApplies.size() <= 0) {
					lv_device_update_notaudit.setVisibility(View.GONE);
					btn_reload.setVisibility(View.VISIBLE);
					iv_blank.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				lv_device_update_notaudit.setVisibility(View.GONE);
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), DeviceUpdateReportActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable("DeviceUpdateInfo", mDeviceUpdateInfos.get(position));
		intent.putExtras(bundle);
		startActivity(intent);
		getActivity().finish();
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				if (rowTotal < pageSize) {
					Toast.makeText(getActivity(), "没有更多数据！", Toast.LENGTH_SHORT).show();
					return;
				}
				if (rowTotal > mDeviceUpdateInfos.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						UpgradeBack upgradeBack = new UpgradeBack();
						upgradeApply upApply = new upgradeApply();
						upApply.setApprove("0");
						upgradeBack.setCurPage(mPage);
						upgradeBack.setCurRows(pageSize);
						upgradeBack.setUpApply(upApply);
						try {
							getMoreList(upgradeBack);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						Toast.makeText(getActivity(), "加载中，请稍后！", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getActivity(), "没有更多数据！", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private void getMoreList(UpgradeBack upgradeBack) throws Exception {
		// TODO Auto-generated method stub
		new GetApplyInfoApi(getActivity()).getUpgradeApplyInfo(upgradeBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				UpgradeBack upgradeBack = (UpgradeBack) o;
				List<upgradeApply> upgradeApplies = upgradeBack.getApplys();
				for (upgradeApply upgradeApply : upgradeApplies) {
					DeviceUpdateInfo mDeviceUpdateInfo = new DeviceUpdateInfo();
					mDeviceUpdateInfo.setApplyID(upgradeApply.getId() + "");
					mDeviceUpdateInfo.setDeviceID(upgradeApply.getDeviceId() + "");
					mDeviceUpdateInfo.setDeviceName(upgradeApply.getDeviceName());
					mDeviceUpdateInfo.setDeviceUser(upgradeApply.getDeviceUser());
					mDeviceUpdateInfo.setDeviceType(upgradeApply.getDeviceType());
					mDeviceUpdateInfo.setDeviceUpdateApplyTime(upgradeApply.getApplytime());
					mDeviceUpdateInfo.setDeviceUpdateReason(upgradeApply.getUpgradeDescribe());
					mDeviceUpdateInfo.setDeviceStatus(upgradeApply.getStatus());
					mDeviceUpdateInfo.setDeviceUpdateApprove(upgradeApply.getApprove());
					mDeviceUpdateInfos.add(mDeviceUpdateInfo);
				}
				mDeviceUpdateNotAuditAdapter.notifyDataSetChanged();
				mLoading = false;
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "再获取数据失败," + errorMsg, Toast.LENGTH_SHORT).show();
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
