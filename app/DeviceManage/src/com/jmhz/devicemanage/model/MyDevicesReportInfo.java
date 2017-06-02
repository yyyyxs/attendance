package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class MyDevicesReportInfo implements Serializable {
	private int deviceId;
	private String deviceName;
	private String deviceType;
	private String deviceUser;
	private String reportState;
	private String reportItem;
	private String reportSpend;
	private String reportResult;

	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceUser() {
		return deviceUser;
	}
	public void setDeviceUser(String deviceUser) {
		this.deviceUser = deviceUser;
	}
	public String getreportState() {
		return reportState;
	}
	public void setreportState(String reportState) {
		this.reportState = reportState;
	}
	public String getreportItem() {
		return reportItem;
	}
	public void setreportItem(String reportItem) {
		this.reportItem = reportItem;
	}
	public String getreportSpend() {
		return reportSpend;
	}
	public void setreportSpend(String reportSpend) {
		this.reportSpend = reportSpend;
	}
	public String getreportResult() {
		return reportResult;
	}
	public void setreportResult(String reportResult) {
		this.reportResult = reportResult;
	}
	public MyDevicesReportInfo(int deviceId, String deviceName,
			String deviceType, String deviceUser, String reportState,
			String reportItem, String reportSpend, String reportResult) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.deviceUser = deviceUser;
		this.reportState = reportState;
		this.reportItem = reportItem;
		this.reportSpend = reportSpend;
		this.reportResult = reportResult;
	}


}
