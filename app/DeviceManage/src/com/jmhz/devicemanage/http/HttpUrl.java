package com.jmhz.devicemanage.http;

public interface HttpUrl {

	// public static final String SERVER_URL = "http://220.250.12.251:8080/";
	public static final String SERVER_URL = "http://222.76.126.53:31019/";
	

	public static final String BASE_URL = SERVER_URL + "UIDTraceWebApp/r/";
    public static final String PUSH_URL =
//    		 "http://220.250.12.251:9696/UIDPushServiceApp/pubsub/UnionPayPushService";
			 "http://222.76.126.53:31016/UIDPushServiceApp/pubsub/UnionPayPushService";
	 
	 public static final String STANDBY_SERVER_URL = SERVER_URL; 
	//
	// public static final String FILE_DOWNLOAD_GET_URL = SERVER_URL
	// + "MobileUploadApp/servlet/webgetimgrequest";
	//
	// public static final String UPDATE_URL = BASE_URL + "version/vs_android";

	/** Member */
	public static final String MEMBER_LOGIN_URL = BASE_URL
			+ "transportNode/login";
	//public static final String MEMBER_UPDATE_LOGIN_PW_URL = BASE_URL
			//+ "transportNode/pv/updateLoginPwd";
	public static final String MEMBER_FORGET_PW_URL = BASE_URL
			+ "transportNode/sendValidateCodeByEmail";
	public static final String MEMBER_GET_NODE_URL = BASE_URL
			+ "transportNode/findPwd;jsessionid=";
	/** 入库管理 */
	public static final String STOCK_RECORD_URL = BASE_URL
			+ "nodeStock/pv/inOutStockRecode";
	public static final String STOCK_FROM_UIDCODE_URL = BASE_URL
			+ "nodeStock/pv/getProductForUidCode";
	public static final String STOCK_IN_STOCK_URL = BASE_URL
			+ "nodeStock/pv/inStock";
	public static final String STOCK_NODE_STOCK_URL = BASE_URL
			+ "nodeStock/pv/nodeStock";
	public static final String STOCK_RECORD_DETAIL_URL = BASE_URL
			+ "nodeStock/pv/inOutStockRecodeDetail";
	/** 出库管理 */
	public static final String OUT_STOCK_FROM_UIDCODE_URL = BASE_URL
			+ "nodeStock/pv/getNodeProductForUidCode";
	public static final String OUT_STOCK_OUT_STOCK_URL = BASE_URL
			+ "nodeStock/pv/outStock";
	/** 配送点 */
	public static final String TRANSPORT_Node_LIST_URL = BASE_URL
			+ "transportNode/pv/getTransportNode";
	public static final String TRANSPORT_Node_UPDATE_URL = BASE_URL
			+ "transportNode/pv/updateTransportNode";
	public static final String TRANSPORT_Node_MY_URL = BASE_URL
			+ "transportNode/pv/getNode";

	/** 销售订单 */
	public static final String SALES_ORDER_LIST_URL = BASE_URL
			+ "order/pv/getListByStatus";
	public static final String SALES_ORDER_DETAIL_URL = BASE_URL
			+ "order/pv/getOrderDetail";

	/** 订单消息 */
	public static final String ORDER_MESSAGE_LIST_URL = BASE_URL
			+ "orderMsg/pv/getList";
	public static final String ORDER_MESSAGE_DETAIL_URL = BASE_URL
			+ "orderMsg/pv/getDetail";
	public static final String ORDER_MESSAGE_UPDATE_MAGREAD_URL = BASE_URL
			+ "orderMsg/pv/updateMsgRead";
	public static final String ORDER_MESSAGE_DEL_READ_LIST_URL = BASE_URL
			+ "orderMsg/pv/deleteReadList";

	/** 系统 */
	public static final String CURRENT_VERSIONS_URL = BASE_URL
			+ "version/android";
	/*
	 * 登录
	 */
	//public static final String LOGIN_URL = "http://218.193.126.55:8080/mobileuser/login";

}
