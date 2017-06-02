package com.jmhz.devicemanage.utils;

public class ApproveTranslateUtils {
	public static String deviceApprove(int index) {
		String deviceApprove = "";
		switch (index) {
		case 0:
			deviceApprove = "未审核";
			break;
		case 1:
			deviceApprove = "已同意";
			break;
		case 2:
			deviceApprove = "已拒绝";
			break;
		case 3:
			deviceApprove = "已完成";
			break;
		default:
			break;
		}
		return deviceApprove;
	}
	
	public static int deviceApprove(String deviceApprove) {
		int index = 0;
		if ("未审核".equals(deviceApprove)) {
			index = 0;
		} else if ("已同意".equals(deviceApprove)) {
			index = 1;
		} else if ("已拒绝".equals(deviceApprove)) {
			index = 2;
		} else if ("已完成".equals(deviceApprove)){
			index = 3;
		}
		return index;
	}
}
