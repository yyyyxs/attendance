package com.jmhz.devicemanage.model;

import java.io.Serializable;


public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String C_AREA_ID;
	private String C_AREA_NAME;
	private String C_CITY_ID;
	public String getC_AREA_ID() {
		return C_AREA_ID;
	}
	public void setC_AREA_ID(String c_AREA_ID) {
		C_AREA_ID = c_AREA_ID;
	}
	public String getC_AREA_NAME() {
		return C_AREA_NAME;
	}
	public void setC_AREA_NAME(String c_AREA_NAME) {
		C_AREA_NAME = c_AREA_NAME;
	}
	public String getC_CITY_ID() {
		return C_CITY_ID;
	}
	public void setC_CITY_ID(String c_CITY_ID) {
		C_CITY_ID = c_CITY_ID;
	}
	@Override
	public String toString() {
		return "Area [C_AREA_ID=" + C_AREA_ID + ", C_AREA_NAME=" + C_AREA_NAME
				+ ", C_CITY_ID=" + C_CITY_ID + "]";
	}

}
