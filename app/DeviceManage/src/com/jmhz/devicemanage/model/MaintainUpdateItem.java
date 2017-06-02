package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MaintainUpdateItem implements Parcelable {

	private String adress;
	private String already;
	private String wait;

	public static final Parcelable.Creator<MaintainUpdateItem> CREATOR = new Creator<MaintainUpdateItem>() {

		public MaintainUpdateItem createFromParcel(Parcel source) {
			MaintainUpdateItem maintainUpdateItem = new MaintainUpdateItem();
			maintainUpdateItem.adress = source.readString();
			maintainUpdateItem.already = source.readString();
			maintainUpdateItem.wait = source.readString();
			return maintainUpdateItem;
		}

		public MaintainUpdateItem[] newArray(int size) {
			return new MaintainUpdateItem[size];
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
		dest.writeString(adress);
		dest.writeString(already);
		dest.writeString(wait);
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getAlready() {
		return already;
	}

	public void setAlready(String already) {
		this.already = already;
	}

	public String getWait() {
		return wait;
	}

	public void setWait(String wait) {
		this.wait = wait;
	}
}
