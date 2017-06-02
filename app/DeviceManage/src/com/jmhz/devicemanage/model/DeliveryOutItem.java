package com.jmhz.devicemanage.model;

import java.io.Serializable;

public class DeliveryOutItem  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nodeName;
	private String nodeId;
	public DeliveryOutItem(String nodeName, String nodeId) {
		super();
		this.nodeName = nodeName;
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
}
