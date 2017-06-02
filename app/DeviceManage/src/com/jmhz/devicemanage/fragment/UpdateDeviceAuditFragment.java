package com.jmhz.devicemanage.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.DeviceUpdateAuditAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetApplyInfoApi;
import com.jmhz.devicemanage.manage.DeviceUpdateAuditReportActivity;
import com.jmhz.devicemanage.model.DeviceUpdateInfo;
import com.jmhz.devicemanage.model.DeviceUpdateReportInfo;
import com.jmhz.devicemanage.web.UpgradeBack;
import com.jmhz.devicemanage.web.upgradeApply;
import com.jmhz.devicemanage.web.upgraderepair;
/**
 * ==================================================
 * 
 * Copyright： 福州大学创新实验室 版权所有（C）2015
 * 
 * @author： 魏德华
 * 
 * @date: 2015-10-18 下午6:58:19
 * 
 * @version： V1.0
 * 
 * @description：已审核（设备升级）
 * 
 * ==================================================
 **/
public class UpdateDeviceAuditFragment extends Fragment implements OnItemClickListener, OnScrollListener{
	
	private ListView lv_device_update_audit = null;
	private List<DeviceUpdateInfo> mDeviceUpdateInfos = new ArrayList<DeviceUpdateInfo>();
	private DeviceUpdateAuditAdapter mDeviceUpdateAuditAdapter = null;
	private boolean isUpdate = false;
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
		View view = inflater.inflate(R.layout.fragment_device_update_audit, container, false);
		btn_reload = (Button) view.findViewById(R.id.bt_reload_audit_update);
		iv_blank = (ImageView) view.findViewById(R.id.blank_audit_update);
		btn_reload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		lv_device_update_audit = (ListView) view.findViewById(R.id.lv_device_update_audit);
		lv_device_update_audit.setOnItemClickListener(this);
		lv_device_update_audit.setOnScrollListener(this);
		mDeviceUpdateAuditAdapter = new DeviceUpdateAuditAdapter(mDeviceUpdateInfos, getActivity());
		lv_device_update_audit.setAdapter(mDeviceUpdateAuditAdapter);
		try {
			initListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		if (isUpdate) {
			Log.i("codingWw", "重新加载数据");
			mDeviceUpdateInfos.clear();
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
	
	private void initListView() throws Exception {
		// TODO Auto-generated method stub
		UpgradeBack upgradeBack = new UpgradeBack();
		upgradeApply upgradeApply = new upgradeApply();
		upgradeApply.setApprove("1");
		upgradeBack.setCurPage(mPage);
		upgradeBack.setCurRows(pageSize);
		upgradeBack.setUpApply(upgradeApply);
		new GetApplyInfoApi(getActivity()).getUpgradeApplyInfo(upgradeBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				lv_device_update_audit.setVisibility(View.VISIBLE);
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
					mDeviceUpdateInfo.setLogmark(upgradeApply.getLogmark());
					mDeviceUpdateInfos.add(mDeviceUpdateInfo);
				}
				mDeviceUpdateAuditAdapter.notifyDataSetChanged();
				btn_reload.setVisibility(View.GONE);
				iv_blank.setVisibility(View.GONE);
				if (upgradeApplies.size() <= 0) {
					lv_device_update_audit.setVisibility(View.GONE);
					btn_reload.setVisibility(View.VISIBLE);
					iv_blank.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				lv_device_update_audit.setVisibility(View.GONE);
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (mDeviceUpdateInfos.get(position).getLogmark()) {
		case 0:
			Toast.makeText(getActivity(), "升级报告未提交", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			try {
				getApplyReport(mDeviceUpdateInfos.get(position).getApplyID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	private void getApplyReport(String applyID) throws Exception {
		// TODO Auto-generated method stub
		UpgradeBack upgradeBack = new UpgradeBack();
		upgraderepair upgradeRepair = new upgraderepair();
		upgradeRepair.setApplyId(Integer.parseInt(applyID));
		upgradeBack.setLog(upgradeRepair);
		new GetApplyInfoApi(getActivity()).getUpgradeApplyReport(upgradeBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				UpgradeBack upgradeBack = (UpgradeBack) o;
				upgraderepair upgradeRepair = upgradeBack.getLog();
				DeviceUpdateReportInfo mDeviceUpdateReportInfo = new DeviceUpdateReportInfo();
				mDeviceUpdateReportInfo.setApplyID(upgradeRepair.getApplyId() + "");
				mDeviceUpdateReportInfo.setDeviceName(upgradeRepair.getDeviceName());
				mDeviceUpdateReportInfo.setDeviceType(upgradeRepair.getDeviceType());
				mDeviceUpdateReportInfo.setDeviceUser(upgradeRepair.getDeviceUser());
				mDeviceUpdateReportInfo.setDeviceDealStatus(upgradeRepair.getDealStatus());
				mDeviceUpdateReportInfo.setDeviceUpdateCost(upgradeRepair.getCost());
				mDeviceUpdateReportInfo.setDeviceChange(upgradeRepair.getUpgradepart());
				mDeviceUpdateReportInfo.setDeviceUpdateResult(upgradeRepair.getupgradeResult());
				
				isUpdate = true;
				Intent intent = new Intent(getActivity(), DeviceUpdateAuditReportActivity.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable("mDeviceUpdateReportInfo", mDeviceUpdateReportInfo);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
			}
		});
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
						upApply.setApprove("1");
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
					mDeviceUpdateInfo.setLogmark(upgradeApply.getLogmark());
					mDeviceUpdateInfos.add(mDeviceUpdateInfo);
				}
				mDeviceUpdateAuditAdapter.notifyDataSetChanged();
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
