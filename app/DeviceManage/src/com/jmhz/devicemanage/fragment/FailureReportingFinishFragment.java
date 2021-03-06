package com.jmhz.devicemanage.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.MyDevicesFaultApplyAdapter;
import com.jmhz.devicemanage.adapter.MyDevicesFaultApplyFinishAdapter;
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
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FailureReportingFinishFragment extends Fragment implements
		OnItemClickListener, OnScrollListener {
	private List<MyDevicesApplyInfo> friFinishList = new ArrayList<MyDevicesApplyInfo>();
	private ListView friReportFinishListview;
	//private MyDevicesFaultApplyAdapter myDevicesApplyFinishAdapter;
	private MyDevicesFaultApplyFinishAdapter myDevicesApplyFinishAdapter;
	private boolean mLoading = false;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private final String url = "http://59.77.236.107:8080/mobilefault/getfaultlistbyuser";

	public void onCreate(Bundle savedInstanceState) {
		// initList();
		try {
			getFinishReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.onCreate(savedInstanceState);
	}

	private void getFinishReport() throws Exception {
		FaultBack faultBack = new FaultBack();
		FaultApply faultApply = new FaultApply();
		faultApply.setApprove("3");
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
							myDevicesApplyInfo.setApplyId(faultApply.getId());
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
							myDevicesApplyInfo.setLogmark(faultApply
									.getLogmark());

							friFinishList.add(myDevicesApplyInfo);
						}
						myDevicesApplyFinishAdapter.notifyDataSetChanged();
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

	// public void initList()
	// {
	// friFinishList=new ArrayList<MyDevicesApplyInfo>();
	// // MyDevicesApplyInfo apply1=new
	// MyDevicesApplyInfo("t1","联想N56","私有","丁鸿","2014-10-20","显示屏坏了");
	// // friFinishList.add(apply1);
	// // MyDevicesApplyInfo apply2=new
	// MyDevicesApplyInfo("t2","华硕N43S","私有","丁鸿","2015-9-20","硬盘坏了");
	// // friFinishList.add(apply2);
	// // MyDevicesApplyInfo apply3=new
	// MyDevicesApplyInfo("t3","联想S34","私有","飞飞","2015-9-10","键盘坏了");
	// // friFinishList.add(apply3);
	// // MyDevicesApplyInfo apply4=new
	// MyDevicesApplyInfo("t4","联想N56","私有","小小","2014-8-20","主机坏了");
	// // friFinishList.add(apply4);
	// MyDevicesApplyInfo apply5=new
	// MyDevicesApplyInfo(15,"联想N56","私有","默默","2015-10-20","风扇坏了");
	// friFinishList.add(apply5);
	// }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mydevice_failure_finish,
				null);
		friReportFinishListview = (ListView) view
				.findViewById(R.id.lv_mydevice_failure_finish);
		myDevicesApplyFinishAdapter = new MyDevicesFaultApplyFinishAdapter(friFinishList,
				getActivity());
		friReportFinishListview.setOnScrollListener(this);
		friReportFinishListview.setOnItemClickListener(this);
		friReportFinishListview.setAdapter(myDevicesApplyFinishAdapter);

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
				if (rowTotal > friFinishList.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						FaultBack faultBack = new FaultBack();
						FaultApply faultApply = new FaultApply();
						faultApply.setApprove("3");
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
							myDevicesApplyInfo.setApplyId(faultApply.getId());
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
							myDevicesApplyInfo.setLogmark(faultApply
									.getLogmark());

							friFinishList.add(myDevicesApplyInfo);
						}
						Toast.makeText(
								getActivity(),
								"再获取" + applys.size() + "条数据,总共"
										+ friFinishList.size(),
								Toast.LENGTH_SHORT).show();
						myDevicesApplyFinishAdapter.notifyDataSetChanged();
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (friFinishList.get(position).getLogmark() == 0) {
			Intent intent = new Intent(getActivity(),
					FailureReportActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("myDevicesApplyInfo",
					friFinishList.get(position));
			intent.putExtras(bundle);
			startActivity(intent);
		} else {
			Toast.makeText(getActivity(), "已提交维修报告!", Toast.LENGTH_SHORT).show();
		}

	}
}
