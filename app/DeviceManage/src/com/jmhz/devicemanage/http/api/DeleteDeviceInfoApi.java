package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;

public class DeleteDeviceInfoApi extends HttpBase{

	public DeleteDeviceInfoApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}
	
	private void postMethod(Device device, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id", device.getId() + "");
		post(url, bundle, new PostCallback() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
				Boolean backReasultInfo = deviceBack.isSuccess();
				if (backReasultInfo) {
					callback.onSuccess("É¾³ý³É¹¦");
				} else {
					callback.onFail("0", "É¾³ýÊ§°Ü");
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	
	public void deleteDeviceInfo(Device device, HttpCallback httpCallback) throws Exception {
		postMethod(device, DELETE_DEVICE_BY_ID, true, httpCallback);
	}

}
