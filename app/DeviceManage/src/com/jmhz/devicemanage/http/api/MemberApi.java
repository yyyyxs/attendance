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

public class MemberApi extends HttpBase {

	public MemberApi(Activity mActivity) {
		super(mActivity);
	}

	/**
	 * @param member
	 *            消息主体
	 * @param url
	 *            接口地址
	 * @param needToken
	 *            是否需要token
	 * @param needEnctrpt
	 *            是否需要加密
	 * @param callback
	 *            消息回调接口
	 * @throws Exception
	 */
	private void postMethod(TransportNode transportNode, String url,
			boolean needToken, boolean needEnctrpt, final HttpCallback callback)
			throws Exception {
		TransportNodeMessage tnm = new TransportNodeMessage();
		tnm.setMessageHeader(new MessageHeader());
		tnm.setMessageBody(transportNode);
		if (needToken) {
			tnm.getMessageHeader().setCustomerToken(BaseActivity.getInstance()
					.getMemberSession());
		}
		String xmlMsg = CommonUtils.marshallObject(TransportNodeMessage.class,
				tnm);
		post(url, xmlMsg, new PostCallback() {
			@Override
			public void onSuccess(String result) {
				TransportNodeMessage tnm = new TransportNodeMessage();
				try {
					tnm = (TransportNodeMessage) CommonUtils.unmarshallMessage(
							TransportNodeMessage.class, result);
					String messageResult = tnm.getMessageHeader().getResult();

					if ("true".equals(messageResult)) {
						callback.onSuccess(tnm);
					} else {
						String errorDesc = tnm.getMessageHeader()
								.getErrorDesc();
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

	public void login(TransportNode transportNode, HttpCallback callback)
			throws Exception {
		postMethod(transportNode, MEMBER_LOGIN_URL, false, false, callback);
	}

	public void updateLoginPwd(TransportNode tn, HttpCallback callback)
			throws Exception {
		postMethod(tn, MEMBER_UPDATE_LOGIN_PW_URL, true, false, callback);
	}

	public void getValidateCodeByEmail(TransportNode tNode,
			HttpCallback httpCallback) throws Exception {
		postMethod(tNode, MEMBER_FORGET_PW_URL, true, false, httpCallback);
	}

	public void retrievePW(TransportNode tNode, String jsessionid, HttpCallback httpCallback) throws Exception {
		
		postMethod(tNode, MEMBER_GET_NODE_URL + jsessionid, false, false, httpCallback);
		
	}

}
