package com.jmhz.devicemanage.param;

import java.io.Serializable;

public class CommonParam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean success;
	protected int errcode;
	@Override
	public String toString() {
		return "CommonParam [success=" + success + ", errcode=" + errcode + "]";
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
}
