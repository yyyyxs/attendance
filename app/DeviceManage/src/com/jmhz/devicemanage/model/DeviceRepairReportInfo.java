package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceRepairReportInfo implements Parcelable {

	private String applyID;
	private String deviceId;
	private String deviceName;
	private String deviceType;
	private String deviceUser;
	private String deviceDealStatus;
	private String deviceRepairCost;
	private String deviceChange;
	private String deviceRepairResult;

	public static final Parcelable.Creator<DeviceRepairReportInfo> CREATOR = new Creator<DeviceRepairReportInfo>() {

		@Override
		public DeviceRepairReportInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			DeviceRepairReportInfo deviceRepairReportInfo = new DeviceRepairReportInfo();
			deviceRepairReportInfo.applyID = source.readString();
			deviceRepairReportInfo.deviceId = source.readString();
			deviceRepairReportInfo.deviceName = source.readString();
			deviceRepairReportInfo.deviceType = source.readString();
			deviceRepairReportInfo.deviceUser = source.readString();
			deviceRepairReportInfo.deviceDealStatus = source.readString();
			deviceRepairReportInfo.deviceRepairCost = source.readString();
			deviceRepairReportInfo.deviceChange = source.readString();
			deviceRepairReportInfo.deviceRepairResult = source.readString();
			return deviceRepairReportInfo;
		}

		@Override
		public DeviceRepairReportInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DeviceRepairReportInfo[size];
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
		dest.writeString(deviceId);
		dest.writeString(deviceName);
		dest.writeString(deviceType);
		dest.writeString(deviceUser);
		dest.writeString(deviceDealStatus);
		dest.writeString(deviceRepairCost);
		dest.writeString(deviceChange);
		dest.writeString(deviceRepairResult);
	}

	public String getApplyID() {
		return applyID;
	}

	public void setApplyID(String applyID) {
		this.applyID = applyID;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
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

	public String getDeviceDealStatus() {
		return deviceDealStatus;
	}

	public void setDeviceDealStatus(String deviceDealStatus) {
		this.deviceDealStatus = deviceDealStatus;
	}

	public String getDeviceRepairCost() {
		return deviceRepairCost;
	}

	public void setDeviceRepairCost(String deviceRepairCost) {
		this.deviceRepairCost = deviceRepairCost;
	}

	public String getDeviceChange() {
		return deviceChange;
	}

	public void setDeviceChange(String deviceChange) {
		this.deviceChange = deviceChange;
	}

	public String getDeviceRepairResult() {
		return deviceRepairResult;
	}

	public void setDeviceRepairResult(String deviceRepairResult) {
		this.deviceRepairResult = deviceRepairResult;
	}

}
