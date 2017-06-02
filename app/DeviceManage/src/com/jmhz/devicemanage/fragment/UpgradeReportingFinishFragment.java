package com.jmhz.devicemanage.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.MyDevicesFaultApplyAdapter;
import com.jmhz.devicemanage.adapter.MyDevicesUpgradeApplyFinishAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesApplyInfo;
import com.jmhz.devicemanage.mydevices.FailureReportActivity;
import com.jmhz.devicemanage.mydevices.UpgradeReportActivity;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.FaultBack;
import com.jmhz.devicemanage.web.UpgradeBack;
import com.jmhz.devicemanage.web.upgradeApply;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class UpgradeReportingFinishFragment extends Fragment implements
		OnItemClickListener, OnScrollListener {

	private List<MyDevicesApplyInfo> uriFinishList = new ArrayList<MyDevicesApplyInfo>();

	private ListView uriReportFinishListview;
	//private myDevicesUpgradeApplyFinishAdapter myDevicesUpgradeApplyFinishAdapter;
	private MyDevicesUpgradeApplyFinishAdapter myDevicesUpgradeApplyFinishAdapter;
	private boolean mLoading = false;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private final String url = "http://59.77.236.107:8080/mobileupgrade/getupgradelistbyuser";

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

	// public void initList()
	// {
	// uriFinishList=new ArrayList<MyDevicesApplyInfo>();
	// // MyDevicesApplyInfo apply1=new
	// MyDevicesApplyInfo("t1","联想N56","私有","丁鸿","2014-10-20","显示屏坏了");
	// // uriFinishList.add(apply1);
	// // MyDevicesApplyInfo apply2=new
	// MyDevicesApplyInfo("t2","华硕N43S","私有","丁鸿","2015-9-20","硬盘坏了");
	// // uriFinishList.add(apply2);
	// // MyDevicesApplyInfo apply3=new
	// MyDevicesApplyInfo("t3","联想S34","私有","飞飞","2015-9-10","键盘坏了");
	// // uriFinishList.add(apply3);
	// // MyDevicesApplyInfo apply4=new
	// MyDevicesApplyInfo("t4","联想N56","私有","小小","2014-8-20","主机坏了");
	// // uriFinishList.add(apply4);
	// MyDevicesApplyInfo apply5=new
	// MyDevicesApplyInfo(15,"联想N56","私有","默默","2015-10-20","风扇坏了");
	// uriFinishList.add(apply5);
	// }
	private void getFinishReport() throws Exception {
		UpgradeBack upgradeBack = new UpgradeBack();
		upgradeApply upApply = new upgradeApply();
		upApply.setApprove("3");
		upgradeBack.setUpApply(upApply);
		upgradeBack.setCurPage(mPage);
		upgradeBack.setCurRows(pageSize);

		new GetDeviceInfoApi(getActivity()).getUpgradeReport(upgradeBack, 
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// iv_failure_notaudit_blank.setVisibility(View.GONE);
						// TODO Auto-generated method stub
						UpgradeBack upgradeBack = (UpgradeBack) o;
						rowTotal = upgradeBack.getTotal();
						List<upgradeApply> applys = upgradeBack.getApplys();

						Toast.makeText(getActivity(),
								"获取" + applys.size() + "条数据,总共" + rowTotal,
								Toast.LENGTH_SHORT).show();
						for (upgradeApply upApply : applys) {
							MyDevicesApplyInfo myDevicesApplyInfo = new MyDevicesApplyInfo();
							myDevicesApplyInfo.setApplyId(upApply.getId());
							myDevicesApplyInfo.setDeviceId(upApply
									.getDeviceId());
							myDevicesApplyInfo.setDeviceName(upApply
									.getDeviceName());
							myDevicesApplyInfo.setDeviceType(upApply
									.getDeviceType());
							myDevicesApplyInfo.setDeviceUser(upApply
									.getDeviceUser());
							myDevicesApplyInfo.setApplyTime(upApply
									.getApplytime());
							myDevicesApplyInfo.setApplydescribe(upApply
									.getUpgradeDescribe());
							myDevicesApplyInfo.setLogmark(upApply
									.getLogmark());
							uriFinishList.add(myDevicesApplyInfo);
						}
						myDevicesUpgradeApplyFinishAdapter.notifyDataSetChanged();
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

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mydevice_upgrade_finish,
				null);
		uriReportFinishListview = (ListView) view
				.findViewById(R.id.lv_mydevice_upgrade_finish);
		uriReportFinishListview.setOnScrollListener(this);
		uriReportFinishListview.setOnItemClickListener(this);
		myDevicesUpgradeApplyFinishAdapter = new MyDevicesUpgradeApplyFinishAdapter(
				uriFinishList, getActivity());
		uriReportFinishListview.setAdapter(myDevicesUpgradeApplyFinishAdapter);
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
				if (rowTotal > uriFinishList.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						UpgradeBack upgradeBack = new UpgradeBack();
						upgradeApply upApply = new upgradeApply();
						upApply.setApprove("3");
						upgradeBack.setUpApply(upApply);
						upgradeBack.setCurPage(mPage);
						upgradeBack.setCurRows(pageSize);
						try {
							getMoreList(upgradeBack);
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

	private void getMoreList(UpgradeBack upgradeBack) throws Exception {
		new GetDeviceInfoApi(getActivity()).getUpgradeReport(upgradeBack, 
				new HttpCallback() {

					@Override
					public void onSuccess(Object o) {
						// iv_failure_notaudit_blank.setVisibility(View.GONE);
						// TODO Auto-generated method stub
						UpgradeBack upgradeBack = (UpgradeBack) o;
						rowTotal = upgradeBack.getTotal();
						List<upgradeApply> applys = upgradeBack.getApplys();
						for (upgradeApply upApply : applys) {
							MyDevicesApplyInfo myDevicesApplyInfo = new MyDevicesApplyInfo();
							myDevicesApplyInfo.setApplyId(upApply.getId());
							myDevicesApplyInfo.setDeviceId(upApply
									.getDeviceId());
							myDevicesApplyInfo.setDeviceName(upApply
									.getDeviceName());
							myDevicesApplyInfo.setDeviceType(upApply
									.getDeviceType());
							myDevicesApplyInfo.setDeviceUser(upApply
									.getDeviceUser());
							myDevicesApplyInfo.setApplyTime(upApply
									.getApplytime());
							myDevicesApplyInfo.setApplydescribe(upApply
									.getUpgradeDescribe());
							myDevicesApplyInfo.setLogmark(upApply
									.getLogmark());
							uriFinishList.add(myDevicesApplyInfo);
						}
						Toast.makeText(
								getActivity(),
								"再获取" + applys.size() + "条数据,总共"
										+ uriFinishList.size(),
								Toast.LENGTH_SHORT).show();
						myDevicesUpgradeApplyFinishAdapter.notifyDataSetChanged();
						mLoading = false;
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						Toast.makeText(getActivity(), errorMsg,
								Toast.LENGTH_SHORT).show();
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

		if (uriFinishList.get(position).getLogmark() == 0) {
			Log.i("logmark",uriFinishList.get(position).getLogmark()+"");
			Intent intent = new Intent(getActivity(),
					UpgradeReportActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("myDevicesApplyInfo",
					uriFinishList.get(position));
			intent.putExtras(bundle);
			startActivity(intent);
		} else {
			Toast.makeText(getActivity(), "已提交升级报告！", Toast.LENGTH_SHORT).show();
		}

	}
}
