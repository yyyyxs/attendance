package com.jmhz.devicemanage.http.api;

import android.app.Activity;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.callback.PostCallback;
import com.jmhz.devicemanage.http.HttpBase;
import com.uid.trace.common.schema.MessageHeader;
import com.uid.trace.module.stock.schema.InOutStock;
import com.uid.trace.module.stock.schema.InOutStockMessage;
import com.uid.trace.module.stock.schema.TransportNodeStock;
import com.uid.trace.module.stock.schema.TransportNodeStockMessage;
import com.uid.trace.module.support.android.CommonUtils;

public class InStockApi extends HttpBase {

	public InStockApi(Activity mActivity) {
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
	private void postMethod(InOutStock inOutStock, String url,
			boolean needToken, boolean needEnctrpt, final HttpCallback callback)
			throws Exception {
		InOutStockMessage ism = new InOutStockMessage();
		ism.setMessageHeader(new MessageHeader());
		ism.getMessageHeader().setCustomerToken(
				BaseActivity.getInstance().getMemberSession());
		ism.setMessageBody(inOutStock);
		String xmlMsg = CommonUtils
				.marshallObject(InOutStockMessage.class, ism);
		post(url, xmlMsg, new PostCallback() {
			InOutStockMessage ism = new InOutStockMessage();

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				try {
					ism = (InOutStockMessage) CommonUtils.unmarshallMessage(
							InOutStockMessage.class, result);
					String messageResult = ism.getMessageHeader().getResult();
					if ("true".equals(messageResult)) {
						callback.onSuccess(ism);
					} else {
						String errorDesc = ism.getMessageHeader()
								.getErrorDesc();
						callback.onFail("0", errorDesc);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					callback.onFail(UNKNOWN_ERROR_CODE, e.getMessage());
				}

			}

			@Override
			public void onFail(String errMsg) {
				// TODO Auto-generated method stub
				callback.onFail(DEFAULT_ERROR_CODE, errMsg);
			}
		});
	}

	private void postMethod(TransportNodeStock tns, String url,
			boolean needToken, boolean needEnctrpt, final HttpCallback callback)
			throws Exception {

		TransportNodeStockMessage tnsm = new TransportNodeStockMessage();
		tnsm.setMessageHeader(new MessageHeader());
		tnsm.getMessageHeader().setCustomerToken(
				BaseActivity.getInstance().getMemberSession());
		tnsm.setMessageBody(tns);
		String xmlMsg = CommonUtils.marshallObject(
				TransportNodeStockMessage.class, tnsm);
		post(url, xmlMsg, new PostCallback() {
			TransportNodeStockMessage tnsm = new TransportNodeStockMessage();

			@Override
			public void onSuccess(String result) {
				try {
					tnsm = (TransportNodeStockMessage) CommonUtils
							.unmarshallMessage(TransportNodeStockMessage.class,
									result);
					String messageResult = tnsm.getMessageHeader().getResult();

					if ("true".equals(messageResult)) {
						callback.onSuccess(tnsm);
					} else {
						String errorDesc = tnsm.getMessageHeader()
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

	public void getInStockRecord(InOutStock inOutStock, HttpCallback callback)
			throws Exception {
		postMethod(inOutStock, STOCK_RECORD_URL, false, true, callback);
	}

	public void getNodeProductForUidCode(TransportNodeStock tns,
			HttpCallback callback) throws Exception {
		postMethod(tns, STOCK_FROM_UIDCODE_URL, false, true, callback);
	}

	public void inStock(TransportNodeStock tns, HttpCallback callback)
			throws Exception {
		postMethod(tns, STOCK_IN_STOCK_URL, false, true, callback);
	}

	public void nodeStock(TransportNodeStock tns, HttpCallback callback)
			throws Exception {
		postMethod(tns, STOCK_NODE_STOCK_URL, false, true, callback);
	}

	public void getInOutStockRecodeDetail(InOutStock inOutStock,
			HttpCallback httpCallback) throws Exception {
		postMethod(inOutStock, STOCK_RECORD_DETAIL_URL, false, true,
				httpCallback);
	}

}
