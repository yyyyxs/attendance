package com.jmhz.devicemanage.model;

import android.os.Parcel;
import android.os.Parcelable;

//ʹ��Parcelable�������л�,ͨ��writeToParcel����Ķ���ӳ���Parcel������ͨ��createFromParcel��Parcel����ӳ�����Ķ���
//Ҳ���Խ�Parcel������һ������ͨ��writeToParcel�Ѷ���д�������棬��ͨ��createFromParcel�������ȡ����ֻ�������������Ҫ����ʵ�֣����д��˳��Ͷ���˳�����һ�¡�
public class TotalNumberItem implements Parcelable {
	
	private String deviceNumber;
	private String pubHave;
	private String priHave;
	//ʵ������̬�ڲ�����CREATORʵ�ֽӿ�Parcelable.Creator
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
