package com.jmhz.devicemanage.utils;

public class StatusTranslateUtils {
	
	public static String deviceStatus(int index) {
		String deviceStatus = "";
		switch (index) {
		case 0:
			deviceStatus = "ʹ��";
			break;
		case 1:
			deviceStatus = "����";
			break;
		case 2:
			deviceStatus = "ά��";
			break;
		case 3:
			deviceStatus = "����";
			break;
		default:
			break;
		}
		return deviceStatus;
	}
	
	public static int deviceStatus(String deviceStatus) {
		int index = 0;
		if ("ʹ��".equals(deviceStatus)) {
			index = 0;
		} else if ("����".equals(deviceStatus)) {
			index = 1;
		} else if ("ά��".equals(deviceStatus)) {
			index = 2;
		} else if ("����".equals(deviceStatus)){
			index = 3;
		}
		return index;
	}
}
