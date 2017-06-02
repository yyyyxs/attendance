package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceManageItem implements Parcelable {

	private String deviceUUID; //�豸UUID
	private int deviceID;// �豸����
	private String deviceName;// �豸����
	private String deviceType;// �豸����
	private String deviceBrand;// �豸Ʒ��
	private String devicePrice;// ����۸�
	private String deciceBuyTime;// ����ʱ��
	private String devicePosition;// ��ŵص�
	private String deviceUser;// ʹ����
	private String deviceStatus;// �豸״̬
	private String deciceConfigInfo;// ������Ϣ
	private String deviceCPUInfo;//���˵���CPU
    private String deviceInternalMemory;//���˵����ڴ�
    private String deviceGraphicsCard;//���˵����Կ�
    private String deviceOtherInfo;//���˵���������Ϣ

	public static final Parcelable.Creator<DeviceManageItem> CREATOR = new Creator<DeviceManageItem>() {

		@Override
		public DeviceManageItem createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			DeviceManageItem deviceManageItem = new DeviceManageItem();
			deviceManageItem.deviceUUID = source.readString();
			deviceManageItem.deviceID = source.readInt();
			deviceManageItem.deviceName = source.readString();
			deviceManageItem.deviceType = source.readString();
			deviceManageItem.deviceBrand = source.readString();
			deviceManageItem.devicePrice = source.readString();
			deviceManageItem.deciceBuyTime = source.readString();
			deviceManageItem.devicePosition = source.readString();
			deviceManageItem.deviceUser = source.readString();
			deviceManageItem.deviceStatus = source.readString();
			deviceManageItem.deciceConfigInfo = source.readString();
			deviceManageItem.deviceCPUInfo = source.readString();
			deviceManageItem.deviceInternalMemory = source.readString();
			deviceManageItem.deviceGraphicsCard = source.readString();
			deviceManageItem.deviceOtherInfo = source.readString();
			return deviceManageItem;
		}

		@Override
		public DeviceManageItem[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DeviceManageItem[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(deviceUUID);
		dest.writeInt(deviceID);
		dest.writeString(deviceName);
		dest.writeString(deviceType);
		dest.writeString(deviceBrand);
		dest.writeString(devicePrice);
		dest.writeString(deciceBuyTime);
		dest.writeString(devicePosition);
		dest.writeString(deviceUser);
		dest.writeString(deviceStatus);
		dest.writeString(deciceConfigInfo);
		dest.writeString(deviceCPUInfo);
		dest.writeString(deviceInternalMemory);
		dest.writeString(deviceGraphicsCard);
		dest.writeString(deviceOtherInfo);
	}

	public String getDeviceUUID() {
		return deviceUUID;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
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

	public String getDevicePrice() {
		return devicePrice;
	}

	public void setDevicePrice(String devicePrice) {
		this.devicePrice = devicePrice;
	}

	public String getDeciceBuyTime() {
		return deciceBuyTime;
	}

	public void setDeciceBuyTime(String deciceBuyTime) {
		this.deciceBuyTime = deciceBuyTime;
	}

	public String getDevicePosition() {
		return devicePosition;
	}

	public void setDevicePosition(String devicePosition) {
		this.devicePosition = devicePosition;
	}

	public String getDeviceUser() {
		return deviceUser;
	}

	public void setDeviceUser(String deviceUser) {
		this.deviceUser = deviceUser;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeciceConfigInfo() {
		return deciceConfigInfo;
	}

	public void setDeciceConfigInfo(String deciceConfigInfo) {
		this.deciceConfigInfo = deciceConfigInfo;
	}

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
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
	
}
