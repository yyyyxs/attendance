package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.util.Log;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.uid.trace.common.schema.MessageHeader;
import com.uid.trace.module.sale.schema.OrderMessage;
import com.uid.trace.module.sale.schema.OrderMessageParameter;
import com.uid.trace.module.support.android.CommonUtils;

public class OrderMessageApi extends HttpBase {

	public OrderMessageApi(Activity mActivity) {
		super(mActivity);
	}

	private void postMethod(OrderMessage om, String url, boolean b, boolean c,
			final HttpCallback callback) throws Exception {

		OrderMessageParameter omp = new OrderMessageParameter();
		omp.setMessageHeader(new MessageHeader());
		omp.getMessageHeader().setCustomerToken(
				BaseActivity.getInstance().getMemberSession());
		omp.setMessageBody(om);
		String xmlMsg = CommonUtils.marshallObject(OrderMessageParameter.class,
				omp);
		post(url, xmlMsg, new PostCallback() {

			@Override
			public void onSuccess(String result) {
				OrderMessageParameter omp = new OrderMessageParameter();
				try {
					omp = (OrderMessageParameter) CommonUtils
							.unmarshallMessage(OrderMessageParameter.class,
									result);
					String messageResult = omp.getMessageHeader().getResult();
					if ("true".equals(messageResult)) {
						callback.onSuccess(omp);
					} else {
						String errorDesc = omp.getMessageHeader()
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

	public void getOrderMessageList(OrderMessage om, HttpCallback httpCallback)
			throws Exception {

		postMethod(om, ORDER_MESSAGE_LIST_URL, false, true, httpCallback);
	}

	public void getOrderMessageDetail(OrderMessage om, HttpCallback httpCallback) throws Exception {

		postMethod(om, ORDER_MESSAGE_DETAIL_URL, false, true, httpCallback);
		
	}

	public void updateMsgRead(OrderMessage om, HttpCallback httpCallback) throws Exception {

		postMethod(om, ORDER_MESSAGE_UPDATE_MAGREAD_URL, false, true, httpCallback);
		
	}

	public void deleteReadList(OrderMessage om, HttpCallback httpCallback) throws Exception {
		
		postMethod(om, ORDER_MESSAGE_DEL_READ_LIST_URL, false, true, httpCallback);
		
	}

}
