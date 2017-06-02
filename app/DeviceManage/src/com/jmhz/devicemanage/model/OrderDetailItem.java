package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class OrderDetailItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commodityName;
	private String commodityType;
	private String commodityBuyNum;
	private String commodityMoney;
	private String commoditySumMoney;
	public OrderDetailItem(String commodityName, String commodityType,
			String commodityBuyNum, String commodityMoney,
			String commoditySumMoney) {
		super();
		this.commodityName = commodityName;
		this.commodityType = commodityType;
		this.commodityBuyNum = commodityBuyNum;
		this.commodityMoney = commodityMoney;
		this.commoditySumMoney = commoditySumMoney;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	public String getCommodityBuyNum() {
		return commodityBuyNum;
	}
	public void setCommodityBuyNum(String commodityBuyNum) {
		this.commodityBuyNum = commodityBuyNum;
	}
	public String getCommodityMoney() {
		return commodityMoney;
	}
	public void setCommodityMoney(String commodityMoney) {
		this.commodityMoney = commodityMoney;
	}
	public String getCommoditySumMoney() {
		return commoditySumMoney;
	}
	public void setCommoditySumMoney(String commoditySumMoney) {
		this.commoditySumMoney = commoditySumMoney;
	}
	
	
}
