package com.jmhz.devicemanage.utils;

public class DealTranslateUtils {
	public static String dealStatus(int index) {
		String dealStatus = "";
		switch (index) {
		case 0:
			dealStatus = "δά��";
			break;
		case 1:
			dealStatus = "����ά��";
			break;
		case 2:
			dealStatus = "��ά��";
			break;
		default:
			break;
		}
		return dealStatus;
	}
	
	public static int dealStatus(String dealStatus) {
		int index = 0;
		if ("δά��".equals(dealStatus)) {
			index = 0;
		} else if ("����ά��".equals(dealStatus)) {
			index = 1;
		} else if ("��ά��".equals(dealStatus)) {
			index = 2;
		}
		return index;
	}
}
