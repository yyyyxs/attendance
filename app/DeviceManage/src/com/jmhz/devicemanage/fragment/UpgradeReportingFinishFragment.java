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
	// MyDevicesApplyInfo("t1","����N56","˽��","����","2014-10-20","��ʾ������");
	// // uriFinishList.add(apply1);
	// // MyDevicesApplyInfo apply2=new
	// MyDevicesApplyInfo("t2","��˶N43S","˽��","����","2015-9-20","Ӳ�̻���");
	// // uriFinishList.add(apply2);
	// // MyDevicesApplyInfo apply3=new
	// MyDevicesApplyInfo("t3","����S34","˽��","�ɷ�","2015-9-10","���̻���");
	// // uriFinishList.add(apply3);
	// // MyDevicesApplyInfo apply4=new
	// MyDevicesApplyInfo("t4","����N56","˽��","СС","2014-8-20","��������");
	// // uriFinishList.add(apply4);
	// MyDevicesApplyInfo apply5=new
	// MyDevicesApplyInfo(15,"����N56","˽��","ĬĬ","2015-10-20","���Ȼ���");
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
								"��ȡ" + applys.size() + "������,�ܹ�" + rowTotal,
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
					Toast.makeText(getActivity(), "û�и������ݣ�", Toast.LENGTH_SHORT)
							.show();

					return;
				}

				// ��ʾ˵ ��������
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
						Toast.makeText(getActivity(), "�����У����Ժ�",
								Toast.LENGTH_SHORT).show();

					}
				} else {
					Toast.makeText(getActivity(), "û�и������ݣ�", Toast.LENGTH_SHORT)
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
								"�ٻ�ȡ" + applys.size() + "������,�ܹ�"
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
			Toast.makeText(getActivity(), "���ύ�������棡", Toast.LENGTH_SHORT).show();
		}

	}
}
