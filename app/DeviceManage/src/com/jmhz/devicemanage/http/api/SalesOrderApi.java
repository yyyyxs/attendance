package com.jmhz.devicemanage.http.api;

import android.app.Activity;
import android.util.Log;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.uid.trace.common.schema.MessageHeader;
import com.uid.trace.module.sale.schema.OrderDetail;
import com.uid.trace.module.sale.schema.OrderDetailMessage;
import com.uid.trace.module.support.android.CommonUtils;

public class SalesOrderApi extends HttpBase {

	public SalesOrderApi(Activity mActivity) {
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
	private void postMethod(OrderDetail od, String url, boolean needToken,
			boolean needEnctrpt, final HttpCallback callback) throws Exception {
		OrderDetailMessage odm = new OrderDetailMessage();
		odm.setMessageHeader(new MessageHeader());
		odm.getMessageHeader().setCustomerToken(
				BaseActivity.getInstance().getMemberSession());
		odm.setMessageBody(od);
		String xmlMsg = CommonUtils.marshallObject(OrderDetailMessage.class,
				odm);
		post(url, xmlMsg, new PostCallback() {

			@Override
			public void onSuccess(String result) {
				OrderDetailMessage odm = new OrderDetailMessage();
				try {
					odm = (OrderDetailMessage) CommonUtils.unmarshallMessage(
							OrderDetailMessage.class, result);
					String messageResult = odm.getMessageHeader().getResult();

					if ("true".equals(messageResult)) {
						callback.onSuccess(odm);
					} else {
						String errorDesc = odm.getMessageHeader()
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

	public void getListByStatus(OrderDetail od, HttpCallback callback)
			throws Exception {

		postMethod(od, SALES_ORDER_LIST_URL, false, false, callback);
	}

	public void getCompleteOrderDetail(OrderDetail od, HttpCallback callback) throws Exception {
		
		postMethod(od, SALES_ORDER_DETAIL_URL, false, false, callback);
	}
}
