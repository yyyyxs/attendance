package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class PaymentItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String orderTime;
	private String orderTotal;
	private String distributionMode;
	public PaymentItem(String orderId, String orderTime, String orderTotal,
			String distributionMode) {
		super();
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.orderTotal = orderTotal;
		this.distributionMode = distributionMode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}
	public String getDistributionMode() {
		return distributionMode;
	}
	public void setDistributionMode(String distributionMode) {
		this.distributionMode = distributionMode;
	}
	
	
	
	
	
	
}
