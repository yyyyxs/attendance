package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class StockSummaryReportItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String goodName;
	private String stockNumber;
	private String goodStandard;
	private double goodPrice;
	
	public StockSummaryReportItem(String goodName, String stockNumber,
			String goodStandard, double d) {
		super();
		this.goodName = goodName;
		this.stockNumber = stockNumber;
		this.goodStandard = goodStandard;
		this.goodPrice = d;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(String stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getGoodStandard() {
		return goodStandard;
	}

	public void setGoodStandard(String goodStandard) {
		this.goodStandard = goodStandard;
	}

	public double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}

	
}
