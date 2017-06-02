package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class NewOrderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	private String pushTime;
	private String receiver;
	private String orderSn;
	private int isRead;
	
	
	
	
	public NewOrderItem(String content, String pushTime, String receiver,
			String orderSn, int isRead) {
		super();
		this.content = content;
		this.pushTime = pushTime;
		this.receiver = receiver;
		this.orderSn = orderSn;
		this.isRead = isRead;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPushTime() {
		return pushTime;
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	
	
	
	
}
