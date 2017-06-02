package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class SaleOutItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cid;
	private String orderNum;
	private String orderType;
	private String commodityNum;
	private String transactorName;
	private String sum;
	private String purposeSite;
	private String data;

	public SaleOutItem(int cid, String orderNum, String orderType,
			String commodityNum, String transactorName, String sum,
			String purposeSite, String data) {
		super();
		this.cid = cid;
		this.orderNum = orderNum;
		this.orderType = orderType;
		this.commodityNum = commodityNum;
		this.transactorName = transactorName;
		this.sum = sum;
		this.purposeSite = purposeSite;
		this.data = data;
	}
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityNum(String commodityNum) {
		this.commodityNum = commodityNum;
	}
	public String getTransactorName() {
		return transactorName;
	}
	public void setTransactorName(String transactorName) {
		this.transactorName = transactorName;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getPurposeSite() {
		return purposeSite;
	}
	public void setPurposeSite(String purposeSite) {
		this.purposeSite = purposeSite;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
