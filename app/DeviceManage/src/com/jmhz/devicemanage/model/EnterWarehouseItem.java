package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class EnterWarehouseItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cid;
	private String orderNum;
	private String orderType;
	private String transactorName;
	private String sum;
	private String data;

	public EnterWarehouseItem(int cid, String orderNum, String orderType,
			String transactorName, String sum, String data) {
		super();
		this.cid = cid;
		this.orderNum = orderNum;
		this.orderType = orderType;
		this.transactorName = transactorName;
		this.sum = sum;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
