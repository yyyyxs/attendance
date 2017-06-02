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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.DeviceRepairNotAuditAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetApplyInfoApi;
import com.jmhz.devicemanage.manage.DeviceRepairReportActivity;
import com.jmhz.devicemanage.model.DeviceRepairInfo;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.FaultBack;

/**
 * ==================================================
 * 
 * Copyright�� ���ݴ�ѧ����ʵ���� ��Ȩ���У�C��2015
 * 
 * @author�� κ�»�
 * 
 * @date: 2015-10-14 ����4:38:21
 * 
 * @version�� V1.0
 * 
 * @description��δ��ˣ��豸ά�ޣ�
 * 
 * ==================================================
 **/
public class RepairDeviceNotAuditFragment extends Fragment implements OnItemClickListener, OnScrollListener{
	
	private ListView lv_device_repair_notaudit = null;
	private List<DeviceRepairInfo> mDeviceRepairInfos = new ArrayList<DeviceRepairInfo>();
	private DeviceRepairNotAuditAdapter mDeviceRepairAdapter = null;
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
		View view = inflater.inflate(R.layout.fragment_device_repair_notaudit, container, false);
		btn_reload = (Button) view.findViewById(R.id.bt_reload_notaudit_repair);
		iv_blank = (ImageView) view.findViewById(R.id.blank_notaudit_repair);
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
		lv_device_repair_notaudit = (ListView) view.findViewById(R.id.lv_device_repair_notaudit);
		lv_device_repair_notaudit.setOnItemClickListener(this);
		lv_device_repair_notaudit.setOnScrollListener(this);
		mDeviceRepairAdapter = new DeviceRepairNotAuditAdapter(mDeviceRepairInfos, getActivity());
		lv_device_repair_notaudit.setAdapter(mDeviceRepairAdapter);
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
		FaultBack faultBack = new FaultBack();
		FaultApply faultApply = new FaultApply();
		faultApply.setApprove("0");
		faultBack.setCurPage(mPage);
		faultBack.setCurRows(pageSize);
		faultBack.setFaultApply(faultApply);
		new GetApplyInfoApi(getActivity()).getRepairApplyInfo(faultBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				lv_device_repair_notaudit.setVisibility(View.VISIBLE);
				FaultBack faultBack = (FaultBack) o;
				List<FaultApply> faultApplies = faultBack.getApplys();
				rowTotal = faultBack.getTotal();
				Toast.makeText(getActivity(), "��ȡ" + faultApplies.size() + "������,�ܹ�" + rowTotal, Toast.LENGTH_SHORT).show();
				for (FaultApply faultApply : faultApplies) {
					DeviceRepairInfo mDeviceRepairInfo = new DeviceRepairInfo();
					mDeviceRepairInfo.setApplyID(faultApply.getId() + "");
					mDeviceRepairInfo.setDeviceID(faultApply.getDeviceId() + "");
					mDeviceRepairInfo.setDeviceName(faultApply.getDeviceName());
					mDeviceRepairInfo.setDeviceType(faultApply.getDeviceType());
					mDeviceRepairInfo.setDeviceUser(faultApply.getDeviceUser());
					mDeviceRepairInfo.setDeviceRepairApplyTime(faultApply.getApplytime());
					mDeviceRepairInfo.setDeviceRepairReason(faultApply.getFaultDescribe());
					mDeviceRepairInfo.setDeviceStatus(faultApply.getStatus());
					mDeviceRepairInfo.setDeviceRepairApprove(faultApply.getApprove());
					mDeviceRepairInfos.add(mDeviceRepairInfo);
				}
				mDeviceRepairAdapter.notifyDataSetChanged();
				btn_reload.setVisibility(View.GONE);
				iv_blank.setVisibility(View.GONE);
				if (faultApplies.size() <= 0) {
					lv_device_repair_notaudit.setVisibility(View.GONE);
					btn_reload.setVisibility(View.VISIBLE);
					iv_blank.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
				lv_device_repair_notaudit.setVisibility(View.GONE);
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
			}
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), DeviceRepairReportActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable("DeviceRepairInfo", mDeviceRepairInfos.get(position));
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
					Toast.makeText(getActivity(), "û�и������ݣ�", Toast.LENGTH_SHORT).show();
				}
				if (rowTotal > mDeviceRepairInfos.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						FaultBack faultBack = new FaultBack();
						FaultApply faultApply = new FaultApply();
						faultApply.setApprove("0");
						faultBack.setCurPage(mPage);
						faultBack.setCurRows(pageSize);
						faultBack.setFaultApply(faultApply);
						try {
							getMoreList(faultBack);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						Toast.makeText(getActivity(), "�����У����Ժ�", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getActivity(), "û�и������ݣ�", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private void getMoreList(FaultBack faultBack) throws Exception {
		// TODO Auto-generated method stub
		new GetApplyInfoApi(getActivity()).getRepairApplyInfo(faultBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				FaultBack faultBack = (FaultBack) o;
				List<FaultApply> faultApplies = faultBack.getApplys();
				for (FaultApply faultApply : faultApplies) {
					DeviceRepairInfo mDeviceRepairInfo = new DeviceRepairInfo();
					mDeviceRepairInfo.setApplyID(faultApply.getId() + "");
					mDeviceRepairInfo.setDeviceID(faultApply.getDeviceId() + "");
					mDeviceRepairInfo.setDeviceName(faultApply.getDeviceName());
					mDeviceRepairInfo.setDeviceType(faultApply.getDeviceType());
					mDeviceRepairInfo.setDeviceUser(faultApply.getDeviceUser());
					mDeviceRepairInfo.setDeviceRepairApplyTime(faultApply.getApplytime());
					mDeviceRepairInfo.setDeviceRepairReason(faultApply.getFaultDescribe());
					mDeviceRepairInfo.setDeviceStatus(faultApply.getStatus());
					mDeviceRepairInfo.setDeviceRepairApprove(faultApply.getApprove());
					mDeviceRepairInfos.add(mDeviceRepairInfo);
				}
				Toast.makeText(getActivity(), "�ٻ�ȡ" + faultApplies.size() + "������,�ܹ�" + mDeviceRepairInfos.size(), Toast.LENGTH_SHORT).show();
				mDeviceRepairAdapter.notifyDataSetChanged();
				mLoading = false;
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "�ٻ�ȡ����ʧ��," + errorMsg, Toast.LENGTH_SHORT).show();
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
