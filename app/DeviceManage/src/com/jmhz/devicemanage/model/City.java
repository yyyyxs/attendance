package com.jmhz.devicemanage.model;

import java.io.Serializable;


public class City implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String C_CITY_ID;
	private String C_CITY_NAME;
	private String C_PROVINCE_ID;
	public String getC_CITY_ID() {
		return C_CITY_ID;
	}
	public void setC_CITY_ID(String c_CITY_ID) {
		C_CITY_ID = c_CITY_ID;
	}
	public String getC_CITY_NAME() {
		return C_CITY_NAME;
	}
	public void setC_CITY_NAME(String c_CITY_NAME) {
		C_CITY_NAME = c_CITY_NAME;
	}
	public String getC_PROVINCE_ID() {
		return C_PROVINCE_ID;
	}
	public void setC_PROVINCE_ID(String c_PROVINCE_ID) {
		C_PROVINCE_ID = c_PROVINCE_ID;
	}
	@Override
	public String toString() {
		return "City [C_CITY_ID=" + C_CITY_ID + ", C_CITY_NAME=" + C_CITY_NAME
				+ ", C_PROVINCE_ID=" + C_PROVINCE_ID + "]";
	}
}
