package com.jmhz.devicemanage.callback;


public interface DownloadCallback {
	public void onSuccess(Object result);
	public void onFail(String errMsg);
}
