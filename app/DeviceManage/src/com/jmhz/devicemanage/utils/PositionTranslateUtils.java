package com.jmhz.devicemanage.utils;

public class PositionTranslateUtils {
	
	public static String devicePosition(int index) {
		String devicePosition = "";
		switch (index) {
		case 0:
			devicePosition = "301";
			break;
		case 1: 
			devicePosition = "302";
			break;
		case 2:
			devicePosition = "303";
			break;
		case 3:
			devicePosition = "304";
			break;
		case 4:
			devicePosition = "305";
			break;
		default:
			break;
		}
		return devicePosition;
	}
	
	public static int devicePosition(String devicePosition) {
		int index = 0;
		if ("301".equals(devicePosition)) {
			index = 0;
		} else if ("302".equals(devicePosition)) {
			index = 1;
		} else if ("303".equals(devicePosition)) {
			index = 2;
		} else if ("304".equals(devicePosition)) {
			index = 3;
		} else if ("305".equals(devicePosition)) {
			index = 4;
		}
		return index;
	}
}
