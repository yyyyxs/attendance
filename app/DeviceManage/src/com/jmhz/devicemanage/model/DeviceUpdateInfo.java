package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceUpdateInfo implements Parcelable {
	
	private String applyID;
	private String deviceID;
	private String deviceName;
	private String deviceType;
	private String deviceStatus;
	private String deviceUser;
	private String deviceUpdateReason;
	private String deviceUpdateApplyTime;
	private String deviceUpdateApprove;
	private String deviceApproveRemark;
	private int logmark;
	
	public static final Parcelable.Creator<DeviceUpdateInfo> CREATOR = new Creator<DeviceUpdateInfo>() {

		@Override
		public DeviceUpdateInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			DeviceUpdateInfo deviceUpdateInfo = new DeviceUpdateInfo();
			deviceUpdateInfo.applyID = source.readString();
			deviceUpdateInfo.deviceID = source.readString();
			deviceUpdateInfo.deviceName = source.readString();
			deviceUpdateInfo.deviceType = source.readString();
			deviceUpdateInfo.deviceStatus = source.readString();
			deviceUpdateInfo.deviceUser = source.readString();
			deviceUpdateInfo.deviceUpdateReason = source.readString();
			deviceUpdateInfo.deviceUpdateApplyTime = source.readString();
			deviceUpdateInfo.deviceUpdateApprove = source.readString();
			deviceUpdateInfo.deviceApproveRemark = source.readString();
			deviceUpdateInfo.logmark = source.readInt();
			return deviceUpdateInfo;
		}

		@Override
		public DeviceUpdateInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DeviceUpdateInfo[size];
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
		dest.writeString(applyID);
		dest.writeString(deviceID);
		dest.writeString(deviceName);
		dest.writeString(deviceType);
		dest.writeString(deviceStatus);
		dest.writeString(deviceUser);
		dest.writeString(deviceUpdateReason);
		dest.writeString(deviceUpdateApplyTime);
		dest.writeString(deviceUpdateApprove);
		dest.writeString(deviceApproveRemark);
		dest.writeInt(logmark);
	}

	public String getApplyID() {
		return applyID;
	}

	public void setApplyID(String applyID) {
		this.applyID = applyID;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
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

	public String getDeviceUser() {
		return deviceUser;
	}

	public void setDeviceUser(String deviceUser) {
		this.deviceUser = deviceUser;
	}

	public String getDeviceUpdateReason() {
		return deviceUpdateReason;
	}

	public void setDeviceUpdateReason(String deviceUpdateReason) {
		this.deviceUpdateReason = deviceUpdateReason;
	}

	public String getDeviceUpdateApplyTime() {
		return deviceUpdateApplyTime;
	}

	public void setDeviceUpdateApplyTime(String deviceUpdateApplyTime) {
		this.deviceUpdateApplyTime = deviceUpdateApplyTime;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceUpdateApprove() {
		return deviceUpdateApprove;
	}

	public void setDeviceUpdateApprove(String deviceUpdateApprove) {
		this.deviceUpdateApprove = deviceUpdateApprove;
	}

	public String getDeviceApproveRemark() {
		return deviceApproveRemark;
	}

	public void setDeviceApproveRemark(String deviceApproveRemark) {
		this.deviceApproveRemark = deviceApproveRemark;
	}

	public int getLogmark() {
		return logmark;
	}

	public void setLogmark(int logmark) {
		this.logmark = logmark;
	}

}
