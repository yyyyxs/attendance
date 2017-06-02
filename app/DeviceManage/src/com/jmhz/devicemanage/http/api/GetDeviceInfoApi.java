package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;
import com.jmhz.devicemanage.web.FaultApply;
import com.jmhz.devicemanage.web.FaultBack;
import com.jmhz.devicemanage.web.Faultrepair;
import com.jmhz.devicemanage.web.UpgradeBack;
import com.jmhz.devicemanage.web.UserBack;
import com.jmhz.devicemanage.web.upgradeApply;
import com.jmhz.devicemanage.web.upgraderepair;

public class GetDeviceInfoApi extends HttpBase {

	public GetDeviceInfoApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}
	
	private void postMethod(DeviceBack deviceBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("page", deviceBack.getCurPage() + "");
		bundle.putString("rows", deviceBack.getCurRows() + "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(deviceBack);
				} else {
					callback.onFail("0", "返回错误");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void faultReportPostMethod(FaultBack faultBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("userId","41"+"");
		bundle.putString("approve",faultBack.getFaultApply().getApprove()+ "");
		bundle.putString("page",faultBack.getCurPage()+ "");
		bundle.putString("rows",faultBack.getCurRows()+ "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				FaultBack faultBack=gson.fromJson(result, FaultBack.class);
				Boolean backReasultInfo = faultBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(faultBack);
				} else {
					callback.onFail("0", "暂无记录");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void upgradeReporttPostMethod(UpgradeBack upgradeBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("userId","41"+"");
		bundle.putString("approve",upgradeBack.getUpApply().getApprove()+ "");
		bundle.putString("page",upgradeBack.getCurPage()+ "");
		bundle.putString("rows",upgradeBack.getCurRows()+ "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UpgradeBack upgradeBack=gson.fromJson(result, UpgradeBack.class);
				Boolean backReasultInfo = upgradeBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(upgradeBack);
				} else {
					callback.onFail("0", "暂无记录");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void userPostMethod(DeviceBack deviceBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("userId","41"+"");
		bundle.putString("page", deviceBack.getCurPage() + "");
		bundle.putString("rows", deviceBack.getCurRows() + "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(deviceBack);
				} else {
					callback.onFail("0", "返回错误");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	private void singlePostMethod(DeviceBack deviceBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Device device = deviceBack.getDevice();
		Bundle bundle = new Bundle();
		bundle.putString("UUID",device.getUUID());
		Log.i("!@!@!@!", device.getUUID());
//		bundle.putString("page", deviceBack.getCurPage() + "");
//		bundle.putString("rows", deviceBack.getCurRows() + "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				Log.i("!@!@!@!", backReasultInfo + "");
				if (backReasultInfo) {
					callback.onSuccess(deviceBack);
				} else {
					callback.onFail("0", "设备不存在，请联系管理员！");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void updateDeviceInfoPostMethod(DeviceBack deviceBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id",deviceBack.getDevice().getId()+"");
		bundle.putString("position", deviceBack.getDevice().getPosition()+ "");
		bundle.putString("internalMemory", deviceBack.getDevice().getInternalMemory()+ "");
		bundle.putString("graphicsCard", deviceBack.getDevice().getGraphicsCard()+ "");
		bundle.putString("otherInfo", deviceBack.getDevice().getOtherInfo()+ "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("设备信息更新成功");
				} else {
					callback.onFail("0", "更新失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void faultReportMethod(Faultrepair repair, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("applyId",repair.getApplyId()+"");
		bundle.putString("deviceName",repair.getDeviceName()+"");
		bundle.putString("deviceUser",repair.getDeviceUser()+ "");
		bundle.putString("deviceType",repair.getDeviceType()+ "");
		bundle.putString("dealStatus",repair.getDealStatus()+ "");
		bundle.putString("cost",repair.getCost()+"");
		bundle.putString("repairpart",repair.getRepairpart()+"");
		bundle.putString("repairResult",repair.getRepairResult()+"");
		//		Log.i("test","deviceId="+deviceBack.getDevice().getId()+"userId=41"+"deviceName="+deviceBack.getDevice().getDeviceName()
//				+"deviceType="+deviceBack.getDevice().getDeviceType());
//		Log.i("test","deviceUser="+deviceBack.getDevice().getDeviceUser()+"applytime="+deviceBack.getApplyTime()
//				+"faultDescribe="+ deviceBack.getApplyDescribe());
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				FaultBack faultBack=gson.fromJson(result,FaultBack.class);
				Boolean backReasultInfo = faultBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("维修报告提交成功！");
				} else {
					callback.onFail("0", "维修报告提交失败！");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void upgradeReportMethod(upgraderepair repair, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("applyId",repair.getApplyId()+"");
		bundle.putString("deviceName",repair.getDeviceName()+"");
		bundle.putString("deviceUser",repair.getDeviceUser()+ "");
		bundle.putString("deviceType",repair.getDeviceType()+ "");
		bundle.putString("dealStatus",repair.getDealStatus()+ "");
		bundle.putString("cost",repair.getCost()+"");
		bundle.putString("upgradepart",repair.getUpgradepart()+"");
		bundle.putString("upgradeResult",repair.getupgradeResult()+"");
		//		Log.i("test","deviceId="+deviceBack.getDevice().getId()+"userId=41"+"deviceName="+deviceBack.getDevice().getDeviceName()
//				+"deviceType="+deviceBack.getDevice().getDeviceType());
//		Log.i("test","deviceUser="+deviceBack.getDevice().getDeviceUser()+"applytime="+deviceBack.getApplyTime()
//				+"faultDescribe="+ deviceBack.getApplyDescribe());
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UpgradeBack upgradeBack=gson.fromJson(result,UpgradeBack.class);
				Boolean backReasultInfo = upgradeBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("升级报告提交成功！");
				} else {
					callback.onFail("0", "升级报告提交失败！");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void faultApplyMethod(FaultApply faultApply, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("deviceId",faultApply.getDeviceId()+"");
		bundle.putString("userId",faultApply.getUserId()+"");
		bundle.putString("deviceName",faultApply.getDeviceName()+ "");
		bundle.putString("deviceType",faultApply.getDeviceType()+ "");
		bundle.putString("deviceUser",faultApply.getDeviceUser()+ "");
		bundle.putString("applytime", faultApply.getApplytime()+"");
		bundle.putString("faultDescribe",faultApply.getFaultDescribe()+"");
//		Log.i("test","deviceId="+deviceBack.getDevice().getId()+"userId=41"+"deviceName="+deviceBack.getDevice().getDeviceName()
//				+"deviceType="+deviceBack.getDevice().getDeviceType());
//		Log.i("test","deviceUser="+deviceBack.getDevice().getDeviceUser()+"applytime="+deviceBack.getApplyTime()
//				+"faultDescribe="+ deviceBack.getApplyDescribe());
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				FaultBack faultBack=gson.fromJson(result,FaultBack.class);
				Boolean backReasultInfo = faultBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("设备故障申报成功");
				} else {
					callback.onFail("0", "设备故障申报失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void upgradeApplyMethod(upgradeApply upApply, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("deviceId",upApply.getDeviceId()+"");
		bundle.putString("userId",upApply.getUserId()+"");
		bundle.putString("deviceName",upApply.getDeviceName()+ "");
		bundle.putString("deviceType", upApply.getDeviceType()+ "");
		bundle.putString("deviceUser",upApply.getDeviceUser()+ "");
		bundle.putString("applytime",upApply.getApplytime()+"");
		bundle.putString("upgradeDescribe", upApply.getUpgradeDescribe()+"");
//		Log.i("test","deviceId="+deviceBack.getDevice().getId()+"userId=41"+"deviceName="+deviceBack.getDevice().getDeviceName()
//				+"deviceType="+deviceBack.getDevice().getDeviceType());
//		Log.i("test","deviceUser="+deviceBack.getDevice().getDeviceUser()+"applytime="+deviceBack.getApplyTime()
//				+"faultDescribe="+ deviceBack.getApplyDescribe());
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UpgradeBack upgradeBack=gson.fromJson(result,UpgradeBack.class);
				Boolean backReasultInfo = upgradeBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("设备升级申报成功");
				} else {
					callback.onFail("0", "设备升级申报失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void updateDeviceInfo(DeviceBack deviceBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id",deviceBack.getDevice().getId()+"");
		bundle.putString("deviceUser", deviceBack.getDevice().getDeviceUser());
		bundle.putString("position", deviceBack.getDevice().getPosition());
		bundle.putString("internalMemory", deviceBack.getDevice().getInternalMemory());
		bundle.putString("graphicsCard", deviceBack.getDevice().getGraphicsCard());
		bundle.putString("otherInfo", deviceBack.getDevice().getOtherInfo());
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("更新成功");
				} else {
					callback.onFail(deviceBack.getErrcode() + "", "更新失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void deviceInfoPostMethod(DeviceBack deviceBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id",deviceBack.getDevice().getId()+"");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(deviceBack);
				} else {
					callback.onFail(deviceBack.getErrcode() + "", "返回失败");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void updateUserPassWordPostMethod(String userId,String password,String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id",userId);
		bundle.putString("newPassword",password);
		System.out.println(url+userId+password);
		Log.i("dd",url+userId+password);
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UserBack resultBack = gson.fromJson(result, UserBack.class);
				Boolean backReasultInfo = resultBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("密码修改成功！");
				} else {
					callback.onFail("0", "密码修改失败！");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void sesarchInfoPostMethod(DeviceBack deviceBack, String condition1,String condition2,String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("condition1",condition1);
		bundle.putString("condition2",condition2);
		bundle.putString("page",deviceBack.getCurPage()+"");
		bundle.putString("rows",deviceBack.getCurRows()+"");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(deviceBack);
				} else {
					callback.onFail("0", "返回错误");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	private void logDeviceInfoPostMethod(String mUsername,String mPassword,String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("username",mUsername);
		bundle.putString("password",mPassword);
		Log.d("dingh",url+mUsername+mPassword);
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				UserBack userback = gson.fromJson(result, UserBack.class);
				Boolean backReasultInfo = userback.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess(userback);
					Log.d("dingh","1111111111111111");
				} else {
					callback.onFail("0", "返回错误");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				Log.d("dingh","失败啦");
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	public void getAllDeviceInfo(DeviceBack deviceBack, HttpCallback callback) throws Exception{
		postMethod(deviceBack, GET__ALL_DEVICE, true, callback);
	}
	public void getUserDeviceInfo(DeviceBack deviceBack, HttpCallback callback) throws Exception{
		userPostMethod(deviceBack, GET_DEVICE_BY_USERTD, true, callback);
	}
	public void getFaultReport(FaultBack faultBack, HttpCallback callback) throws Exception{
		faultReportPostMethod(faultBack, GET_FAULTLIST_BY_USER, true, callback);
	}
	public void getUpgradeReport(UpgradeBack upgradeBack, HttpCallback callback) throws Exception{
		upgradeReporttPostMethod(upgradeBack, GET_UPGRADELIST_BY_USER, true, callback);
	}
	public void getSingleDeviceInfo(DeviceBack deviceBack, HttpCallback callback) throws Exception{
		singlePostMethod(deviceBack, GET_DEVICE_BY_UUID, true, callback);
	}
	public void updateUserDeviceInfo(DeviceBack deviceBack, HttpCallback callback) throws Exception{
		updateDeviceInfoPostMethod(deviceBack, UPDATE_DEVICE_BY_USER, true, callback);
	}
	public void getLogDeviceInfo(String name,String password, HttpCallback callback) throws Exception{
		logDeviceInfoPostMethod(name,password, LOGIN_URL, true, callback);
	}
	
	public void updateDeviceInfo(DeviceBack deviceBack, HttpCallback callback) throws Exception {
		updateDeviceInfo(deviceBack, UPDATE_DEVICE_BY_MANAGER, true, callback);
	}
	
	public void getDeviceInfoByID(DeviceBack deviceBack, HttpCallback callback) throws Exception {
		deviceInfoPostMethod(deviceBack, GET_DEVICE_BY_ID, true, callback);
	}
	public void faultApply(FaultApply faultApply, HttpCallback callback) throws Exception{
		faultApplyMethod(faultApply,ADD_FAULT, true, callback);
	}
	public void faultReport(Faultrepair repair, HttpCallback callback) throws Exception{
		faultReportMethod(repair, ADD_FAULT_LOG, true, callback);
	}
	public void upgradeReport(upgraderepair repair, HttpCallback callback) throws Exception{
		upgradeReportMethod(repair, ADD_UPGRADE_LOG, true, callback);
	}
	public void upgradeApply(upgradeApply upApply, HttpCallback callback) throws Exception{
		upgradeApplyMethod(upApply, ADD_UPGRADE, true, callback);
	}
	public void getSearchInfoByID(DeviceBack deviceBack, String condition1,String condition2, HttpCallback callback) throws Exception {
		sesarchInfoPostMethod(deviceBack,condition1,condition2,GET_DEVICE_BY_CONDITION, true, callback);
	}
	
	public void updateUserPassWord(String userID,String password ,HttpCallback callback) throws Exception{
		updateUserPassWordPostMethod(userID, password,MEMBER_UPDATE_LOGIN_PW_URL, true, callback);
	}
}
