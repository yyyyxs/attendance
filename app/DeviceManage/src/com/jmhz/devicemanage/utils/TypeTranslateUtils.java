package com.jmhz.devicemanage.utils;

public class TypeTranslateUtils {
	
	public static String deviceType(int index) {
		String deviceType = "";
		switch (index) {
		case 0:
			deviceType = "˽��";
			break;
		case 1:
			deviceType = "����";
			break;
		default:
			break;
		}
		return deviceType;
	}
	
	public static int deviceType(String deviceType) {
		int index = 0;
		if ("˽��".equals(deviceType)) {
			index = 0;
		} else if ("����".equals(deviceType)) {
			index = 1;
		}
		return index;
	}
}
