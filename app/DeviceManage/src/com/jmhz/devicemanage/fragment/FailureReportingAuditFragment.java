package com.jmhz.devicemanage.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.MyDevicesFaultApplyAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesApplyInfo;
import com.jmhz.devicemanage.mydevices.FailureReportActivity;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.FaultBack;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/*
 我的设备--》故障申报已审核
 */
public class FailureReportingAuditFragment extends Fragment implements OnScrollListener {

	private ListView friReportAuditListview;
	private List<MyDevicesApplyInfo> friAuditList = new ArrayList<MyDevicesApplyInfo>();
	private MyDevicesFaultApplyAdapter myDevicesApplyAdapter;
	private boolean mLoading = false;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private final String url = "http://59.77.236.107:8080/mobilefault/getfaultlistbyuser";

	public void onCreate(Bundle savedInstanceState) {
		// initList();
		try {
			getAuditReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onCreate(savedInstanceState);
	}

	// private void initList() {
	// friAuditList=new ArrayList<MyDevicesApplyInfo>();
	// MyDevicesApplyInfo apply1=new
	// MyDevicesApplyInfo(12,"联想N56","私有","丁鸿","2014-10-20","显示屏坏了");
	// friAuditList.add(apply1);
	// // MyDevicesApplyInfo apply2=new
	// MyDevicesApplyInfo("t2","华硕N43S","私有","丁鸿","2015-9-20","硬盘坏了");
	// // friAuditList.add(apply2);
	// // MyDevicesApplyInfo apply3=new
	// MyDevicesApplyInfo("t3","联想S34","私有","飞飞","2015-9-10","键盘坏了");
	// // friAuditList.add(apply3);
	// // MyDevicesApplyInfo apply4=new
	// MyDevicesApplyInfo("t4","联想N56","私有","小小","2014-8-20","主机坏了");
	// // friAuditList.add(apply4);
	// // MyDevicesApplyInfo apply5=new
	// MyDevicesApplyInfo("t5","联想N56","私有","默默","2015-10-20","风扇坏了");
	// // friAuditList.add(apply5);
	//
	// }
	private void getAuditReport() throws Exception {
		FaultBack faultBack = new FaultBack();
		FaultApply faultApply = new FaultApply();
		faultApply.setApprove("1");
		faultBack.setFaultApply(faultApply);
		faultBack.setCurPage(mPage);
		faultBack.setCurRows(pageSize);

		new GetDeviceInfoApi(getActivity()).getFaultReport(faultBack,
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// iv_failure_notaudit_blank.setVisibility(View.GONE);
						// TODO Auto-generated method stub
						FaultBack faultBack = (FaultBack) o;
						rowTotal = faultBack.getTotal();
						List<FaultApply> applys = faultBack.getApplys();

						Toast.makeText(getActivity(),
								"获取" + applys.size() + "条数据,总共" + rowTotal,
								Toast.LENGTH_SHORT).show();
						for (FaultApply faultApply : applys) {
							MyDevicesApplyInfo myDevicesApplyInfo = new MyDevicesApplyInfo();
							myDevicesApplyInfo.setDeviceId(faultApply
									.getDeviceId());
							myDevicesApplyInfo.setDeviceName(faultApply
									.getDeviceName());
							myDevicesApplyInfo.setDeviceType(faultApply
									.getDeviceType());
							myDevicesApplyInfo.setDeviceUser(faultApply
									.getDeviceUser());
							myDevicesApplyInfo.setApplyTime(faultApply
									.getApplytime());
							myDevicesApplyInfo.setApplydescribe(faultApply
									.getFaultDescribe());

							friAuditList.add(myDevicesApplyInfo);
						}
						myDevicesApplyAdapter.notifyDataSetChanged();
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), errorMsg,
								Toast.LENGTH_SHORT).show();
						// iv_failure_notaudit_blank.setVisibility(View.VISIBLE);
					}
				});

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mydevice_failure_audit,
				null);
		friReportAuditListview = (ListView) view
				.findViewById(R.id.lv_mydevice_failure_audit);
		myDevicesApplyAdapter = new MyDevicesFaultApplyAdapter(friAuditList,
				getActivity());
		friReportAuditListview.setOnScrollListener(this);
		friReportAuditListview.setAdapter(myDevicesApplyAdapter);
		return view;
	}

	@Override
	public void onPause() {

		super.onPause();
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			if (view.getLastVisiblePosition() == view.getCount() - 1) {
				if (rowTotal < pageSize) {
					Toast.makeText(getActivity(), "没有更多数据！", Toast.LENGTH_SHORT)
							.show();

					return;
				}

				// 表示说 还有数据
				if (rowTotal > friAuditList.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						FaultBack faultBack = new FaultBack();
						FaultApply faultApply = new FaultApply();
						faultApply.setApprove("1");
						faultBack.setFaultApply(faultApply);
						faultBack.setCurPage(mPage);
						faultBack.setCurRows(pageSize);
						try {
							getMoreList(faultBack);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							mLoading = false;
							e.printStackTrace();
							mPage -= 1;
						}
					} else {
						Toast.makeText(getActivity(), "加载中，请稍后！",
								Toast.LENGTH_SHORT).show();

					}
				} else {
					Toast.makeText(getActivity(), "没有更多数据！", Toast.LENGTH_SHORT)
							.show();

				}
			}
		}

	}

	private void getMoreList(FaultBack faultBack) throws Exception {
		// TODO Auto-generated method stub
		new GetDeviceInfoApi(getActivity()).getFaultReport(faultBack,
			 new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// iv_failure_notaudit_blank.setVisibility(View.GONE);
						// TODO Auto-generated method stub
						FaultBack faultBack = (FaultBack) o;
						rowTotal = faultBack.getTotal();
						List<FaultApply> applys = faultBack.getApplys();
						for (FaultApply faultApply : applys) {
							MyDevicesApplyInfo myDevicesApplyInfo = new MyDevicesApplyInfo();
							myDevicesApplyInfo.setDeviceId(faultApply
									.getDeviceId());
							myDevicesApplyInfo.setDeviceName(faultApply
									.getDeviceName());
							myDevicesApplyInfo.setDeviceType(faultApply
									.getDeviceType());
							myDevicesApplyInfo.setDeviceUser(faultApply
									.getDeviceUser());
							myDevicesApplyInfo.setApplyTime(faultApply
									.getApplytime());
							myDevicesApplyInfo.setApplydescribe(faultApply
									.getFaultDescribe());

							friAuditList.add(myDevicesApplyInfo);
						}
						Toast.makeText(
								getActivity(),
								"再获取" + applys.size() + "条数据,总共"
										+ friAuditList.size(),
								Toast.LENGTH_SHORT).show();
						myDevicesApplyAdapter.notifyDataSetChanged();
						mLoading = false;
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), "再获取数据失败," + errorMsg,
								Toast.LENGTH_SHORT).show();
						mLoading = false;
						mPage -= 1;
						// iv_failure_notaudit_blank.setVisibility(View.VISIBLE);
					}
				});
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}
}
