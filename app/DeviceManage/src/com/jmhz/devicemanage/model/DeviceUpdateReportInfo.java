package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceUpdateReportInfo implements Parcelable {
	
	private String applyID;
	private String deviceId;
	private String deviceName;
	private String deviceType;
	private String deviceUser;
	private String deviceDealStatus;
	private String deviceUpdateCost;
	private String deviceChange;
	private String deviceUpdateResult;
	
	public static final Parcelable.Creator<DeviceUpdateReportInfo> CREATOR = new Creator<DeviceUpdateReportInfo>() {
		
		@Override
		public DeviceUpdateReportInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			DeviceUpdateReportInfo deviceUpdateReportInfo = new DeviceUpdateReportInfo();
			deviceUpdateReportInfo.applyID = source.readString();
			deviceUpdateReportInfo.deviceId = source.readString();
			deviceUpdateReportInfo.deviceName = source.readString();
			deviceUpdateReportInfo.deviceType = source.readString();
			deviceUpdateReportInfo.deviceUser = source.readString();
			deviceUpdateReportInfo.deviceDealStatus = source.readString();
			deviceUpdateReportInfo.deviceUpdateCost = source.readString();
			deviceUpdateReportInfo.deviceChange = source.readString();
			deviceUpdateReportInfo.deviceUpdateResult = source.readString();
			return deviceUpdateReportInfo;
		}
		@Override
		public DeviceUpdateReportInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new DeviceUpdateReportInfo[size];
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
		dest.writeString(deviceUpdateCost);
		dest.writeString(deviceChange);
		dest.writeString(deviceUpdateResult);
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

	public String getDeviceUpdateCost() {
		return deviceUpdateCost;
	}

	public void setDeviceUpdateCost(String deviceUpdateCost) {
		this.deviceUpdateCost = deviceUpdateCost;
	}

	public String getDeviceChange() {
		return deviceChange;
	}

	public void setDeviceChange(String deviceChange) {
		this.deviceChange = deviceChange;
	}

	public String getDeviceUpdateResult() {
		return deviceUpdateResult;
	}

	public void setDeviceUpdateResult(String deviceUpdateResult) {
		this.deviceUpdateResult = deviceUpdateResult;
	}
}
