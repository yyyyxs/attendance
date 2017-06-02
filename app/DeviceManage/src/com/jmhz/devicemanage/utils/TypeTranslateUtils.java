package com.jmhz.devicemanage.utils;

public class TypeTranslateUtils {
	
	public static String deviceType(int index) {
		String deviceType = "";
		switch (index) {
		case 0:
			deviceType = "私有";
			break;
		case 1:
			deviceType = "公有";
			break;
		default:
			break;
		}
		return deviceType;
	}
	
	public static int deviceType(String deviceType) {
		int index = 0;
		if ("私有".equals(deviceType)) {
			index = 0;
		} else if ("公有".equals(deviceType)) {
			index = 1;
		}
		return index;
	}
}
