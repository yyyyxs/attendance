package com.jmhz.devicemanage.http.api;

import android.app.Activity;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.uid.trace.common.schema.MessageHeader;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;
import com.uid.trace.module.support.android.CommonUtils;

public class TransportNodeApi extends HttpBase {

	public TransportNodeApi(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}
	
	private void postMethod(TransportNode tn, String url, boolean needToken,
			boolean needEnctrpt, final HttpCallback callback) throws Exception {
		TransportNodeMessage tnm = new TransportNodeMessage();
		tnm.setMessageHeader(new MessageHeader());
		tnm.getMessageHeader().setCustomerToken(BaseActivity.getInstance()
				.getMemberSession());
		tnm.setMessageBody(tn);
		String xmlMsg = CommonUtils.marshallObject(TransportNodeMessage.class, tnm);
		post(url, xmlMsg, new PostCallback() {
			TransportNodeMessage tnm = new TransportNodeMessage();
			
			@Override
			public void onSuccess(String result) {
				try {
					tnm = (TransportNodeMessage) CommonUtils.unmarshallMessage(TransportNodeMessage.class, result);
					String messageResult = tnm.getMessageHeader().getResult();
					if ("true".equals(messageResult)) {
						callback.onSuccess(tnm);
					} else {
						String errorDesc = tnm.getMessageHeader().getErrorDesc();
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
	
	public void getTransportNode(TransportNode tn, HttpCallback callback) throws Exception {
		postMethod(tn, TRANSPORT_Node_LIST_URL, false, true, callback);	
	}

	public void updateTransportNode(TransportNode tn, HttpCallback callback) throws Exception {
		postMethod(tn, TRANSPORT_Node_UPDATE_URL, false, true, callback);	
	}

	public void getNode(TransportNode tn, HttpCallback httpCallback) throws Exception {
		postMethod(tn, TRANSPORT_Node_MY_URL, false, true, httpCallback);	
		
	} 



}
