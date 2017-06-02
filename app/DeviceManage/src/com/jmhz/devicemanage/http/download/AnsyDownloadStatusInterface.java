package com.jmhz.devicemanage.http.download;


public interface AnsyDownloadStatusInterface {
	/**
	 * 下载状态返回接口
	 * @param filetype 当前文件类型（队列序号）
	 * @param status 当前所处状态
	 * @param process 下载进度指示
	 */
	void downStatus(FileType filetype, AnsyDownloadStatus status, int process);
}
