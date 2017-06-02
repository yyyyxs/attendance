package com.jmhz.devicemanage.callback;

public interface HttpCallback {

	public void onSuccess(Object o);
	public void onFail(String errorCode, String errorMsg);
}
