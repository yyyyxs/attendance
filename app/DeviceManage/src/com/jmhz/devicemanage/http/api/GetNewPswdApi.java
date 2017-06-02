package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.jmhz.devicemanage.web.DeviceBack;

public class GetNewPswdApi extends HttpBase{

	public GetNewPswdApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	private void postMethod(DeviceBack deviceBack, String url, boolean needEnctrpt, final HttpCallback callback) throws Exception{
		Bundle bundle = new Bundle();
		bundle.putString("id", "");
		bundle.putString("newPswd", "");
		post(url, bundle, new PostCallback() {

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
//				DeviceBack deviceBack = gson.fromJson(result, DeviceBack.class);
//				Boolean backReasultNewPswd = deviceBack.isSuccess();
//				if (backReasultInfo) {
//					callback.onSuccess(deviceBack);
//				} else {
//					callback.onFail("0", "·µ»Ø´íÎó");
//				}
			}

			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
}
	
	/*public void getAllDeviceInfo(DeviceBack deviceBack, String url, HttpCallback callback) throws Exception{
		postMethod(deviceBack, url, true, callback);
	}*/

}