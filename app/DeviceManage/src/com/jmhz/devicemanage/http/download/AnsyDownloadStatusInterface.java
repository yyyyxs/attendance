package com.jmhz.devicemanage.http.download;


public interface AnsyDownloadStatusInterface {
	/**
	 * ����״̬���ؽӿ�
	 * @param filetype ��ǰ�ļ����ͣ�������ţ�
	 * @param status ��ǰ����״̬
	 * @param process ���ؽ���ָʾ
	 */
	void downStatus(FileType filetype, AnsyDownloadStatus status, int process);
}
