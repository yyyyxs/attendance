package com.jmhz.devicemanage.http;

public interface HttpBaseUrl {
	public static final String SERVER_URL = "http://59.77.135.245:8080/device/";
	public static final String DEVICE_BASE_URL = SERVER_URL + "mobiledevicemg/";
	public static final String FAULT_BASE_URL = SERVER_URL + "mobilefault/";
	public static final String UPGRADE_BASE_URL = SERVER_URL + "mobileupgrade/";
	public static final String STATISTICS_BASE_URL = SERVER_URL + "mobilestatistics/";
	/**
	 * 登陆
	 */
	public static final String LOGIN_URL = SERVER_URL+"mobileuser/login";
	//修改密码
	public static final String MEMBER_UPDATE_LOGIN_PW_URL = SERVER_URL+ "mobileuser/changePassword";
	/**
	 *
	 * 设备查询
	 */
	public static final String GET_DEVICE_BY_UUID = DEVICE_BASE_URL + "getmydevicebyUUID";
	public static final String GET_DEVICE_BY_USERTD = DEVICE_BASE_URL + "getmydevicebyuserId";
	public static final String GET__ALL_DEVICE = DEVICE_BASE_URL + "getalldevice";
	public static final String GET_DEVICE_BY_CONDITION = DEVICE_BASE_URL + "getdeviceByCondition";
	public static final String GET_DEVICE_BY_ID = DEVICE_BASE_URL + "getmydevicebyID";
	
	/**
	 * 设备管理
	 */
	public static final String DELETE_DEVICE_BY_ID = DEVICE_BASE_URL + "DeleltebyID";
	public static final String UPDATE_DEVICE_BY_USER = DEVICE_BASE_URL + "updatebyuser";
	public static final String UPDATE_DEVICE_BY_MANAGER = DEVICE_BASE_URL + "updatebymanager";
	
	/**
	 * 设备维修
	 */
	public static final String ADD_FAULT = FAULT_BASE_URL + "addfault";
	public static final String ADD_FAULT_GRADE = FAULT_BASE_URL + "addfaultgrade";
	public static final String GET_FAULTLIST_BY_USER = FAULT_BASE_URL + "getfaultlistbyuser";
	public static final String GET_FAULTLIST_BY_MANAGER = FAULT_BASE_URL + "getfaultlistbymanager";
	public static final String FAULT_APPROVE_OPINION = FAULT_BASE_URL + "approveopinion";
	public static final String ADD_FAULT_LOG = FAULT_BASE_URL + "addfaultlog";
	public static final String SHOW_FAULT_LOG = FAULT_BASE_URL + "showlog";
	
	/**
	 * 设备升级
	 */
	public static final String ADD_UPGRADE = UPGRADE_BASE_URL + "addUpgrade";
	public static final String GET_UPGRADELIST_BY_USER = UPGRADE_BASE_URL + "getupgradelistbyuser";
	public static final String GET_UPGRADELIST_BY_MANAGER = UPGRADE_BASE_URL + "getupgradelistbymanager";
	public static final String UPGRADE_APPROVE_OPINION = UPGRADE_BASE_URL + "approveopinion";
	public static final String ADD_UPGRADE_LOG = UPGRADE_BASE_URL + "addupgradelog";
	public static final String SHOW_UPGRADE_LOG = UPGRADE_BASE_URL + "showlog";
	
	/**
	 * 设备统计 
	 */
	public static final String DEVICE_STATISTICS_BY_CONDITION = STATISTICS_BASE_URL + "deviceStatistics";
	public static final String DEVICE_UPDATE_STATISTICS = STATISTICS_BASE_URL + "updateStatistics";
	public static final String DEVICE_REPAIR_STATISTICS = STATISTICS_BASE_URL + "repairStatistics";
	public static final String ALL_DEVICE_STATISTICS_BY_CONDITION = STATISTICS_BASE_URL + "yearORpositionStatistics";
}
