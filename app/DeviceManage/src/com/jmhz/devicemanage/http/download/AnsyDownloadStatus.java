package com.jmhz.devicemanage.http.download;

public enum AnsyDownloadStatus{
	START,
	PROCESSING,
	GET_FILE_LENGTH_ERROR,
	URL_ANALYSIS_FAILED,
	FILE_CREATE_FAILED,
	FAIL_CONNECT,
	SUCCESS
}