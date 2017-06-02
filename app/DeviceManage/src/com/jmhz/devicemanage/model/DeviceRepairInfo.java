package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceRepairInfo implements Parcelable {
	
	private String applyID;
	private String deviceID;
	private String deviceName;
	private String deviceType;
	private String deviceStatus;
	private String deviceUser;
	private String deviceRepairReason;
	private String deviceRepairApprove;
	private String deviceRepairApplyTime;
	private String deviceApprove;
	private String deviceApproveRemark;
	private int logmark;

	public static final Parcelable.Creator<DeviceRepairInfo> CREATOR = new Creator<DeviceRepairInfo>() {

		@Override
		public DeviceRepairInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			DeviceRepairInfo deviceRepairInfo = new DeviceRepairInfo();
			deviceRepairInfo.applyID = source.readString();
			deviceRepairInfo.deviceID = source.readString();
			deviceRepairInfo.deviceName = source.readString();
			deviceRepairInfo.deviceType = source.readString();
			deviceRepairInfo.deviceUser = source.readString();
			deviceRepairInfo.deviceRepairReason = source.readString();
			deviceRepairInfo.deviceRepairApprove = source.readString();
			deviceRepairInfo.deviceRepairApplyTime = source.readString();
			deviceRepairInfo.deviceStatus = source.readString();
			deviceRepairInfo.deviceApprove = source.readString();
			deviceRepairInfo.deviceApproveRemark = source.readString();
			deviceRepairInfo.logmark = source.readInt();
			return deviceRepairInfo;
		}

		@Override
		public DeviceRepairInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DeviceRepairInfo[size];
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
		dest.writeString(deviceUser);
		dest.writeString(deviceRepairReason);
		dest.writeString(deviceRepairApprove);
		dest.writeString(deviceRepairApplyTime);
		dest.writeString(deviceStatus);
		dest.writeString(deviceApprove);
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

	public String getDeviceRepairApplyTime() {
		return deviceRepairApplyTime;
	}

	public void setDeviceRepairApplyTime(String deviceRepairApplyTime) {
		this.deviceRepairApplyTime = deviceRepairApplyTime;
	}

	public String getDeviceRepairReason() {
		return deviceRepairReason;
	}

	public void setDeviceRepairReason(String deviceRepairReason) {
		this.deviceRepairReason = deviceRepairReason;
	}

	public String getDeviceRepairApprove() {
		return deviceRepairApprove;
	}

	public void setDeviceRepairApprove(String deviceRepairApprove) {
		this.deviceRepairApprove = deviceRepairApprove;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceApprove() {
		return deviceApprove;
	}

	public void setDeviceApprove(String deviceApprove) {
		this.deviceApprove = deviceApprove;
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
