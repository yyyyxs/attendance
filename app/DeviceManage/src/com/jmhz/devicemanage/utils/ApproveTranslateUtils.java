package com.jmhz.devicemanage.utils;

public class ApproveTranslateUtils {
	public static String deviceApprove(int index) {
		String deviceApprove = "";
		switch (index) {
		case 0:
			deviceApprove = "δ���";
			break;
		case 1:
			deviceApprove = "��ͬ��";
			break;
		case 2:
			deviceApprove = "�Ѿܾ�";
			break;
		case 3:
			deviceApprove = "�����";
			break;
		default:
			break;
		}
		return deviceApprove;
	}
	
	public static int deviceApprove(String deviceApprove) {
		int index = 0;
		if ("δ���".equals(deviceApprove)) {
			index = 0;
		} else if ("��ͬ��".equals(deviceApprove)) {
			index = 1;
		} else if ("�Ѿܾ�".equals(deviceApprove)) {
			index = 2;
		} else if ("�����".equals(deviceApprove)){
			index = 3;
		}
		return index;
	}
}
