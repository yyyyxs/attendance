package com.jmhz.devicemanage.http.api;

import android.app.Activity;

import com.google.gson.Gson;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.jmhz.devicemanage.param.VersionsParam;

public class UpdateVersionApi extends HttpBase {

	public UpdateVersionApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	public void getCurrentVersions(final HttpCallback callback) {
		// TODO Auto-generated method stub
		get(CURRENT_VERSIONS_URL, new PostCallback() {

			@Override
			public void onSuccess(String result) {

				Gson gson = new Gson();
				VersionsParam param = gson
						.fromJson(result, VersionsParam.class);
				callback.onSuccess(param);
			}

			@Override
			public void onFail(String errMsg) {
				
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);

			}
		});
	}

}
