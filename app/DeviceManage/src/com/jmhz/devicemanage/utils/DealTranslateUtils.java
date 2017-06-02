package com.jmhz.devicemanage.utils;

public class DealTranslateUtils {
	public static String dealStatus(int index) {
		String dealStatus = "";
		switch (index) {
		case 0:
			dealStatus = "Î´Î¬ĞŞ";
			break;
		case 1:
			dealStatus = "ÕıÔÚÎ¬ĞŞ";
			break;
		case 2:
			dealStatus = "ÒÑÎ¬ĞŞ";
			break;
		default:
			break;
		}
		return dealStatus;
	}
	
	public static int dealStatus(String dealStatus) {
		int index = 0;
		if ("Î´Î¬ĞŞ".equals(dealStatus)) {
			index = 0;
		} else if ("ÕıÔÚÎ¬ĞŞ".equals(dealStatus)) {
			index = 1;
		} else if ("ÒÑÎ¬ĞŞ".equals(dealStatus)) {
			index = 2;
		}
		return index;
	}
}
