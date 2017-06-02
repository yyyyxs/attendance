package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.util.Log;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.uid.trace.common.schema.MessageHeader;
import com.uid.trace.module.stock.schema.TransportNodeStock;
import com.uid.trace.module.stock.schema.TransportNodeStockMessage;
import com.uid.trace.module.support.android.CommonUtils;

public class OutStockApi extends HttpBase {

	public OutStockApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}
	private void postMethod(TransportNodeStock tns, String url, boolean needToken,
			boolean needEnctrpt, final HttpCallback callback) throws Exception {
		
		TransportNodeStockMessage tnsm = new TransportNodeStockMessage();
		tnsm.setMessageHeader(new MessageHeader());
		tnsm.getMessageHeader().setCustomerToken(BaseActivity.getInstance()
				.getMemberSession());
		tnsm.setMessageBody(tns);
		String xmlMsg = CommonUtils.marshallObject(TransportNodeStockMessage.class, tnsm);
		post(url, xmlMsg, new PostCallback() {
			TransportNodeStockMessage tnsm = new TransportNodeStockMessage();
			@Override  
			public void onSuccess(String result) {
				try {
					tnsm = (TransportNodeStockMessage) CommonUtils.unmarshallMessage(TransportNodeStockMessage.class, result);
					String messageResult = tnsm.getMessageHeader().getResult();
					Log.i("cfq", messageResult);
					if ("true".equals(messageResult)) {
						callback.onSuccess(tnsm);
					} else {
						String errorDesc = tnsm.getMessageHeader().getErrorDesc();
						callback.onFail("0", errorDesc);
					}
				
				} catch (Exception e) {
					callback.onFail(UNKNOWN_ERROR_CODE, e.getMessage());
				}
			}
			
			@Override
			public void onFail(String errMsg) {
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}
	public void getNodeProductForUidCode(TransportNodeStock tns,
			HttpCallback httpCallback) throws Exception {
		postMethod(tns, OUT_STOCK_FROM_UIDCODE_URL, false, true, httpCallback);
		
	}
	public void outStock(TransportNodeStock tns, HttpCallback httpCallback) throws Exception {
		
		postMethod(tns, OUT_STOCK_OUT_STOCK_URL, false, true, httpCallback);
	}

	

}
