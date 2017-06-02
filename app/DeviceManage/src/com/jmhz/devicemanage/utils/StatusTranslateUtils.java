package com.jmhz.devicemanage.utils;

public class StatusTranslateUtils {
	
	public static String deviceStatus(int index) {
		String deviceStatus = "";
		switch (index) {
		case 0:
			deviceStatus = "Ê¹ÓÃ";
			break;
		case 1:
			deviceStatus = "·ÏÆú";
			break;
		case 2:
			deviceStatus = "Î¬ÐÞ";
			break;
		case 3:
			deviceStatus = "Éý¼¶";
			break;
		default:
			break;
		}
		return deviceStatus;
	}
	
	public static int deviceStatus(String deviceStatus) {
		int index = 0;
		if ("Ê¹ÓÃ".equals(deviceStatus)) {
			index = 0;
		} else if ("·ÏÆú".equals(deviceStatus)) {
			index = 1;
		} else if ("Î¬ÐÞ".equals(deviceStatus)) {
			index = 2;
		} else if ("Éý¼¶".equals(deviceStatus)){
			index = 3;
		}
		return index;
	}
}
