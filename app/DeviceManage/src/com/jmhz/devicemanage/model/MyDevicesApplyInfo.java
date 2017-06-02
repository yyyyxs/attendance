package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class MyDevicesApplyInfo implements Serializable {
	private int applyId ;
	private int logmark;
	private int deviceId;
	private String deviceName;
	private String deviceType;
	private String deviceUser;
	private String applyTime;
	private String applydescribe;
	
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
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getApplydescribe() {
		return applydescribe;
	}
	public void setApplydescribe(String applydescribe) {
		this.applydescribe = applydescribe;
	}
	public MyDevicesApplyInfo()
	{
		
	}
	public int getApplyId() {
		return applyId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	public int getLogmark() {
		return logmark;
	}
	public void setLogmark(int logmark) {
		this.logmark = logmark;
	}



}
