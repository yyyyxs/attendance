package com.jmhz.devicemanage.model;

import java.io.Serializable;


public class Province implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String C_PROVINCE_ID;
	private String C_PROVINCE_NAME;
	private int C_REGION_ID;
	public String getC_PROVINCE_ID() {
		return C_PROVINCE_ID;
	}
	public void setC_PROVINCE_ID(String c_PROVINCE_ID) {
		C_PROVINCE_ID = c_PROVINCE_ID;
	}
	public String getC_PROVINCE_NAME() {
		return C_PROVINCE_NAME;
	}
	public void setC_PROVINCE_NAME(String c_PROVINCE_NAME) {
		C_PROVINCE_NAME = c_PROVINCE_NAME;
	}
	public int getC_REGION_ID() {
		return C_REGION_ID;
	}
	public void setC_REGION_ID(int c_REGION_ID) {
		C_REGION_ID = c_REGION_ID;
	}
	@Override
	public String toString() {
		return "Province [C_PROVINCE_ID=" + C_PROVINCE_ID
				+ ", C_PROVINCE_NAME=" + C_PROVINCE_NAME + ", C_REGION_ID="
				+ C_REGION_ID + "]";
	}
}
