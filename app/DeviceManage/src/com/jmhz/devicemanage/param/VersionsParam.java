package com.jmhz.devicemanage.param;

import java.io.Serializable;

public class VersionsParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean forcedUpdate;
	private int versionCode;
	private String versionName;
	private String downloadUrl;
	private String updateTime;
	
	public VersionsParam() {
		super();
	}

	public VersionsParam(Boolean forcedUpdate, int versionCode,
			String versionName, String downloadUrl, String updateTime) {
		super();
		this.forcedUpdate = forcedUpdate;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.downloadUrl = downloadUrl;
		this.updateTime = updateTime;
	}

	public Boolean getForcedUpdate() {
		return forcedUpdate;
	}

	public void setForcedUpdate(Boolean forcedUpdate) {
		this.forcedUpdate = forcedUpdate;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
