package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class MyDevicesListItem implements Serializable {
	private int deviceId;
	private String deviceName;
	private String deviceType;
	private String deviceUser;
	private String devicePrice;
	private String devicePlace;
	private String deviceState;
	private String deviceTime;
	//private String deviceConfigInfo;
	private String deviceCPUInfo;//个人电脑CPU
    private String deviceInternalMemory;//个人电脑内存
    private String deviceGraphicsCard;//个人电脑显卡
    private String deviceOtherInfo;//个人电脑其他信息

    public MyDevicesListItem(){}
	public MyDevicesListItem(int deviceId, String deviceName,
			String deviceType, String deviceUser,String devicePrice, String devicePlace,
			String deviceState, String deviceTime,String deviceCPUInfo,String deviceInternalMemory,String deviceGraphicsCard,String deviceOtherInfo) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.deviceUser=deviceUser;
		this.devicePrice = devicePrice;
		this.devicePlace = devicePlace;
		this.deviceState = deviceState;
		this.deviceTime = deviceTime;
		this.deviceCPUInfo=deviceCPUInfo;
		this.deviceInternalMemory=deviceInternalMemory;
		this.deviceGraphicsCard=deviceGraphicsCard;
		this.deviceOtherInfo=deviceOtherInfo;
	}
	public String getDeviceCPUInfo() {
		return deviceCPUInfo;
	}
	public void setDeviceCPUInfo(String deviceCPUInfo) {
		this.deviceCPUInfo = deviceCPUInfo;
	}
	public String getDeviceInternalMemory() {
		return deviceInternalMemory;
	}
	public void setDeviceInternalMemory(String deviceInternalMemory) {
		this.deviceInternalMemory = deviceInternalMemory;
	}
	public String getDeviceGraphicsCard() {
		return deviceGraphicsCard;
	}
	public void setDeviceGraphicsCard(String deviceGraphicsCard) {
		this.deviceGraphicsCard = deviceGraphicsCard;
	}
	public String getDeviceOtherInfo() {
		return deviceOtherInfo;
	}
	public void setDeviceOtherInfo(String deviceOtherInfo) {
		this.deviceOtherInfo = deviceOtherInfo;
	}
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
	public String getDevicePrice() {
		return devicePrice;
	}
	public void setDevicePrice(String devicePrice) {
		this.devicePrice = devicePrice;
	}
	public String getDevicePlace() {
		return devicePlace;
	}
	public void setDevicePlace(String devicePlace) {
		this.devicePlace = devicePlace;
	}
	public String getDeviceState() {
		return deviceState;
	}
	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}
	public String getDeviceTime() {
		return deviceTime;
	}
	public void setDeviceTime(String deviceTime) {
		this.deviceTime = deviceTime;
	}
//	public String getDeviceConfigInfo() {
//		return deviceConfigInfo;
//	}
//	public void setDeviceConfigInfo(String deviceConfigInfo) {
//		this.deviceConfigInfo = deviceConfigInfo;
//	}





}
