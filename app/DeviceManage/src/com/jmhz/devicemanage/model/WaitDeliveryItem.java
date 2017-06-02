package com.jmhz.devicemanage.model;

import java.io.Serializable;
import java.util.List;

import com.uid.trace.module.stock.schema.NodeStockUid;

public class WaitDeliveryItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String commodityName;
	private String commodityNum;
	private String commodityUnum;
	private List<NodeStockUid> nsus;
	
	

	public WaitDeliveryItem(String commodityName, String commodityNum,
			String commodityUnum, List<NodeStockUid> nsus) {
		super();
		this.commodityName = commodityName;
		this.commodityNum = commodityNum;
		this.commodityUnum = commodityUnum;
		this.nsus = nsus;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	

	public String getCommodityNum() {
		return commodityNum;
	}

	public void setCommodityNum(String commodityNum) {
		this.commodityNum = commodityNum;
	}

	public String getCommodityUnum() {
		return commodityUnum;
	}

	public void setCommodityUnum(String commodityUnum) {
		this.commodityUnum = commodityUnum;
	}

	public List<NodeStockUid> getNsus() {
		return nsus;
	}

	public void setNsus(List<NodeStockUid> nsus) {
		this.nsus = nsus;
	}

}
