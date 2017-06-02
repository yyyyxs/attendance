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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.DeviceRepairAuditAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetApplyInfoApi;
import com.jmhz.devicemanage.manage.DeviceAuditReportActivity;
import com.jmhz.devicemanage.model.DeviceRepairInfo;
import com.jmhz.devicemanage.model.DeviceRepairReportInfo;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.FaultBack;
import com.jmhz.devicemanage.web.Faultrepair;
/**
 * ==================================================
 * 
 * Copyright： 福州大学创新实验室 版权所有（C）2015
 * 
 * @author： 魏德华
 * 
 * @date: 2015-10-14 下午4:37:34
 * 
 * @version： V1.0
 * 
 * @description：已审核（设备维修）
 * 
 * ==================================================
 **/
public class RepairDeviceAuditFragment extends Fragment implements OnItemClickListener, OnScrollListener{
	
	private ListView lv_device_repair_audit = null;
	private List<DeviceRepairInfo> mDeviceRepairInfos = new ArrayList<DeviceRepairInfo>();
	private DeviceRepairAuditAdapter mDeviceRepairAdapter = null;
	private boolean mLoading = false;
	private boolean isUpdate = false;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private Button btn_reload = null;
	private ImageView iv_blank = null;
	private Intent intent;
	private Bundle bundle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_device_repair_audit, container, false);
		btn_reload = (Button) view.findViewById(R.id.bt_reload_audit_repair);
		iv_blank = (ImageView) view.findViewById(R.id.blank_audit_repair);
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
		lv_device_repair_audit = (ListView) view.findViewById(R.id.lv_device_repair_audit);
		lv_device_repair_audit.setOnItemClickListener(this);
		lv_device_repair_audit.setOnScrollListener(this);
		mDeviceRepairAdapter = new DeviceRepairAuditAdapter(mDeviceRepairInfos, getActivity());
		lv_device_repair_audit.setAdapter(mDeviceRepairAdapter);
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
			mDeviceRepairInfos.clear();
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
		FaultBack faultBack = new FaultBack();
		FaultApply faultApply = new FaultApply();
		faultApply.setApprove("1");
		faultBack.setCurPage(mPage);
		faultBack.setCurRows(pageSize);
		faultBack.setFaultApply(faultApply);
		new GetApplyInfoApi(getActivity()).getRepairApplyInfo(faultBack, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				lv_device_repair_audit.setVisibility(View.VISIBLE);
				FaultBack faultBack = (FaultBack) o;
				List<FaultApply> faultApplies = faultBack.getApplys();
				rowTotal = faultBack.getTotal();
				Toast.makeText(getActivity(), "获取" + faultApplies.size() + "条数据,总共" + rowTotal, Toast.LENGTH_SHORT).show();
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
					mDeviceRepairInfo.setLogmark(faultApply.getLogmark());
					mDeviceRepairInfos.add(mDeviceRepairInfo);
				}
				mDeviceRepairAdapter.notifyDataSetChanged();
				btn_reload.setVisibility(View.GONE);
				iv_blank.setVisibility(View.GONE);
				if (faultApplies.size() <= 0) {
					lv_device_repair_audit.setVisibility(View.GONE);
					btn_reload.setVisibility(View.VISIBLE);
					iv_blank.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
				lv_device_repair_audit.setVisibility(View.GONE);
				btn_reload.setVisibility(View.VISIBLE);
				iv_blank.setVisibility(View.VISIBLE);
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
				}
				if (rowTotal > mDeviceRepairInfos.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						FaultBack faultBack = new FaultBack();
						FaultApply faultApply = new FaultApply();
						faultApply.setApprove("1");
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
						Toast.makeText(getActivity(), "加载中，请稍后！", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getActivity(), "没有更多数据！", Toast.LENGTH_SHORT).show();
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
					mDeviceRepairInfo.setDeviceApproveRemark(faultApply.getApproveRemark());
					mDeviceRepairInfo.setLogmark(faultApply.getLogmark());
					mDeviceRepairInfos.add(mDeviceRepairInfo);
				}
				Toast.makeText(getActivity(), "再获取" + faultApplies.size() + "条数据,总共" + mDeviceRepairInfos.size(), Toast.LENGTH_SHORT).show();
				mDeviceRepairAdapter.notifyDataSetChanged();
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (mDeviceRepairInfos.get(position).getLogmark()) {
		case 0:
			Toast.makeText(getActivity(), "维修报告未提交", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			try {
				getApplyReport(mDeviceRepairInfos.get(position).getApplyID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void getApplyReport(final String applyId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(applyId + "!!!!!!");
		FaultBack faultBack = new FaultBack();
		Faultrepair faultRepair = new Faultrepair();
		faultRepair.setApplyId(Integer.parseInt(applyId));
		faultBack.setLog(faultRepair);
		new GetApplyInfoApi(getActivity()).getRepairApplyReport(faultBack, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				// TODO Auto-generated method stub
				FaultBack faultBack = (FaultBack) o;
				Faultrepair faultRepair = faultBack.getLog();
				System.out.println(faultRepair);
				DeviceRepairReportInfo mDeviceRepairReportInfo = new DeviceRepairReportInfo();
				mDeviceRepairReportInfo.setApplyID(faultRepair.getApplyId() + "");
				mDeviceRepairReportInfo.setDeviceName(faultRepair.getDeviceName());
				mDeviceRepairReportInfo.setDeviceType(faultRepair.getDeviceType());
				mDeviceRepairReportInfo.setDeviceUser(faultRepair.getDeviceUser());
				mDeviceRepairReportInfo.setDeviceDealStatus(faultRepair.getDealStatus());
				mDeviceRepairReportInfo.setDeviceRepairCost(faultRepair.getCost());
				mDeviceRepairReportInfo.setDeviceChange(faultRepair.getRepairpart());
				mDeviceRepairReportInfo.setDeviceRepairResult(faultRepair.getRepairResult());
				
				intent = new Intent(getActivity(), DeviceAuditReportActivity.class);
				bundle = new Bundle();
				bundle.putParcelable("mDeviceRepairReportInfo", mDeviceRepairReportInfo);
				intent.putExtras(bundle);
				startActivity(intent);
				isUpdate = true;
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
