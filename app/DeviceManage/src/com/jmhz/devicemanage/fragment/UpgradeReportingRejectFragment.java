package com.jmhz.devicemanage.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.MyDevicesFaultApplyAdapter;
import com.jmhz.devicemanage.adapter.MyDevicesUpgradeApplyAdapter;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.model.MyDevicesApplyInfo;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.mydevices.UpgradeReportActivity;
import com.jmhz.devicemanage.mydevices.UpgradeReportingActivity;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.FaultBack;
import com.jmhz.devicemanage.web.UpgradeBack;
import com.jmhz.devicemanage.web.upgradeApply;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class UpgradeReportingRejectFragment extends Fragment implements
		OnItemClickListener, OnScrollListener {
	private List<MyDevicesApplyInfo> uriRejectList = new ArrayList<MyDevicesApplyInfo>();
	private ListView uriReportRejectListview;
	private MyDevicesUpgradeApplyAdapter myDevicesUpgradeApplyAdapter;
	private boolean mLoading = false;
	private int mPage = 1;
	private int pageSize = 10;
	private int rowTotal = 0;
	private final String url = "http://59.77.236.107:8080/mobileupgrade/getupgradelistbyuser";

	public void onCreate(Bundle savedInstanceState) {
		// initList();
		try {
			getFejectReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onCreate(savedInstanceState);
	}

	private void getFejectReport() throws Exception {
		UpgradeBack upgradeBack = new UpgradeBack();
		upgradeApply upApply = new upgradeApply();
		upApply.setApprove("2");
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

							uriRejectList.add(myDevicesApplyInfo);
						}
						myDevicesUpgradeApplyAdapter.notifyDataSetChanged();
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
	// uriRejectList=new ArrayList<MyDevicesApplyInfo>();
	// MyDevicesApplyInfo apply1=new
	// MyDevicesApplyInfo(11,"联想N56","私有","丁鸿","2014-10-20","显示屏坏了");
	// uriRejectList.add(apply1);
	// MyDevicesApplyInfo apply2=new
	// MyDevicesApplyInfo(12,"华硕N43S","私有","丁鸿","2015-9-20","硬盘坏了");
	// uriRejectList.add(apply2);
	// MyDevicesApplyInfo apply3=new
	// MyDevicesApplyInfo(13,"联想S34","私有","飞飞","2015-9-10","键盘坏了");
	// uriRejectList.add(apply3);
	// MyDevicesApplyInfo apply4=new
	// MyDevicesApplyInfo(14,"联想N56","私有","小小","2014-8-20","主机坏了");
	// uriRejectList.add(apply4);
	// MyDevicesApplyInfo apply5=new
	// MyDevicesApplyInfo(15,"联想N56","私有","默默","2015-10-20","风扇坏了");
	// uriRejectList.add(apply5);
	// }
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mydevice_upgrade_reject,
				null);
		uriReportRejectListview = (ListView) view
				.findViewById(R.id.lv_mydevice_upgrade_reject);
		myDevicesUpgradeApplyAdapter = new MyDevicesUpgradeApplyAdapter(
				uriRejectList, getActivity());
		uriReportRejectListview.setOnItemClickListener(this);
		uriReportRejectListview.setOnScrollListener(this);
		uriReportRejectListview.setAdapter(myDevicesUpgradeApplyAdapter);
		return view;
	}

	@Override
	public void onPause() {

		super.onPause();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getActivity(),
				UpgradeReportingActivity.class);
		Bundle bundle = new Bundle();
		MyDevicesListItem myDeviceItem = new MyDevicesListItem();
		myDeviceItem.setDeviceId(uriRejectList.get(position).getDeviceId());
		myDeviceItem.setDeviceName(uriRejectList.get(position).getDeviceName());
		myDeviceItem.setDeviceType(uriRejectList.get(position).getDeviceType());
		myDeviceItem.setDeviceUser(uriRejectList.get(position).getDeviceUser());
		bundle.putSerializable("MyDeviceDetail", myDeviceItem);
		bundle.putString("applyAgain", "true");
		// bundle.putSerializable("myDevicesApplyInfo",uriRejectList.get(position));
		intent.putExtras(bundle);
		startActivity(intent);

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
				if (rowTotal > uriRejectList.size()) {
					if (!mLoading) {
						mPage += 1;
						mLoading = true;
						UpgradeBack upgradeBack = new UpgradeBack();
						upgradeApply upApply = new upgradeApply();
						upApply.setApprove("2");
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

							uriRejectList.add(myDevicesApplyInfo);
						}
						Toast.makeText(
								getActivity(),
								"再获取" + applys.size() + "条数据,总共"
										+ uriRejectList.size(),
								Toast.LENGTH_SHORT).show();
						myDevicesUpgradeApplyAdapter.notifyDataSetChanged();
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
}
