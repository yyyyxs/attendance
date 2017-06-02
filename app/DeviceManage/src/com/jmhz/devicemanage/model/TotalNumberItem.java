package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

//使用Parcelable进行序列化,通过writeToParcel将你的对象映射成Parcel对象，再通过createFromParcel将Parcel对象映射成你的对象。
//也可以将Parcel看成是一个流，通过writeToParcel把对象写到流里面，在通过createFromParcel从流里读取对象，只不过这个过程需要你来实现，因此写的顺序和读的顺序必须一致。
public class TotalNumberItem implements Parcelable {
	
	private String deviceNumber;
	private String pubHave;
	private String priHave;
	//实例化静态内部对象CREATOR实现接口Parcelable.Creator
	public static final Parcelable.Creator<TotalNumberItem> CREATOR = new Creator<TotalNumberItem>() {
		
		public TotalNumberItem createFromParcel(Parcel source) {
			TotalNumberItem totalNumberItem = new TotalNumberItem();
			totalNumberItem.deviceNumber = source.readString();
			totalNumberItem.pubHave = source.readString();
			totalNumberItem.priHave = source.readString();
			return totalNumberItem;
		}
		
		public TotalNumberItem[] newArray(int size) {
			return new TotalNumberItem[size];
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
		dest.writeString(deviceNumber);
		dest.writeString(pubHave);
		dest.writeString(priHave);
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getPubHave() {
		return pubHave;
	}

	public void setPubHave(String pubHave) {
		this.pubHave = pubHave;
	}

	public String getPriHave() {
		return priHave;
	}

	public void setPriHave(String priHave) {
		this.priHave = priHave;
	}
    
	
}
