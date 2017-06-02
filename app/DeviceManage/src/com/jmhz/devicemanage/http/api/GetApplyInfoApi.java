package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.FaultBack;
import com.jmhz.devicemanage.web.UpgradeBack;
import com.jmhz.devicemanage.web.upgradeApply;

public class GetApplyInfoApi extends HttpBase {

	public GetApplyInfoApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}
	
	private void repairApplyInfoPostMethod(FaultBack faultBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("approve", faultBack.getFaultApply().getApprove());
		bundle.putString("page", faultBack.getCurPage() + "");
		bundle.putString("rows", faultBack.getCurRows() + "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				FaultBack faultBack = gson.fromJson(result, FaultBack.class);
				Boolean backReasultInfo = faultBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(faultBack);
				} else {
					callback.onFail(faultBack.getErrcode() + "", "暂无数据");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	private void upgradeApplyInfoPostMethod(UpgradeBack upgradeBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("approve", upgradeBack.getUpApply().getApprove());
		bundle.putString("page", upgradeBack.getCurPage() + "");
		bundle.putString("rows", upgradeBack.getCurRows() + "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UpgradeBack upgradeBack = gson.fromJson(result, UpgradeBack.class);
				Boolean backReasultInfo = upgradeBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(upgradeBack);
				} else {
					callback.onFail(upgradeBack.getErrcode() + "", "暂无数据");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	private void repairApplyAuditPostMethod(FaultApply faultApply, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id", faultApply.getId() + "");
		bundle.putString("status", faultApply.getStatus());
		bundle.putString("approve", faultApply.getApprove());
		bundle.putString("approveRemark", faultApply.getApproveRemark());
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				FaultBack faultBack = gson.fromJson(result, FaultBack.class);
				Boolean backReasultInfo = faultBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("提交成功");
				} else {
					callback.onFail(faultBack.getErrcode() + "", "提交失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	private void upgradeApplyAuditPostMethod(upgradeApply upgradeApply, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id", upgradeApply.getId() + "");
		bundle.putString("status", upgradeApply.getStatus());
		bundle.putString("approve", upgradeApply.getApprove());
		bundle.putString("approveRemark", upgradeApply.getApproveRemark());
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UpgradeBack upgradeBack = gson.fromJson(result, UpgradeBack.class);
				Boolean backReasultInfo = upgradeBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("提交成功");
				} else {
					callback.onFail(upgradeBack.getErrcode() + "", "提交失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	private void repairApplyReportPostMethod(FaultBack faultBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("applyId", faultBack.getLog().getApplyId() + "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				FaultBack faultBack = gson.fromJson(result, FaultBack.class);
				Boolean backReasultInfo = faultBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(faultBack);
				} else {
					callback.onFail(faultBack.getErrcode() + "", "获取失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void upgradeApplyReportPostMethod(UpgradeBack upgradeBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("applyId", upgradeBack.getLog().getApplyId()+ "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UpgradeBack upgradeBack = gson.fromJson(result, UpgradeBack.class);
				Boolean backReasultInfo = upgradeBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(upgradeBack);
				} else {
					callback.onFail(upgradeBack.getErrcode() + "", "获取失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	public void getRepairApplyInfo(FaultBack faultBack, HttpCallback httpCallback) throws Exception {
		repairApplyInfoPostMethod(faultBack, GET_FAULTLIST_BY_MANAGER, true, httpCallback);
	}
	
	public void pushRepairApply(FaultApply faultApply, HttpCallback httpCallback) throws Exception {
		repairApplyAuditPostMethod(faultApply, FAULT_APPROVE_OPINION, true, httpCallback);
	}
	
	public void getRepairApplyReport(FaultBack faultBack, HttpCallback httpCallback) throws Exception {
		repairApplyReportPostMethod(faultBack, SHOW_FAULT_LOG, true, httpCallback);
	}	
	
	public void getUpgradeApplyInfo(UpgradeBack upgradeBack, HttpCallback httpCallback) throws Exception {
		upgradeApplyInfoPostMethod(upgradeBack, GET_UPGRADELIST_BY_MANAGER, true, httpCallback);
	}
	
	public void pushUpdateApply(upgradeApply upgradeApply, HttpCallback httpCallback) throws Exception {
		upgradeApplyAuditPostMethod(upgradeApply, UPGRADE_APPROVE_OPINION, true, httpCallback);
	}
	
	public void getUpgradeApplyReport(UpgradeBack upgradeBack, HttpCallback httpCallback) throws Exception {
		upgradeApplyReportPostMethod(upgradeBack, SHOW_UPGRADE_LOG, true, httpCallback);
	}
}
