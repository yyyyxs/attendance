/**
 * �첽HTTP����</br>
 * ���Զ������ͬʱ�������أ���������ΪFileType��������
 * ִ����Ϻ���ص���setFinish�������߳̽���ռ����Դ�������Զ��ͷ�
 * 
 * �ر�ע�⣺Ҫ����DownloadFinish�ӿڣ�����������κ���ʾ
 * 
 * @version 0.9
 * @author ���
 */
package com.jmhz.devicemanage.http.download;

import java.io.File;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jmhz.devicemanage.http.item.HttpUtils;
import com.jmhz.devicemanage.http.item.NetFileItem;

public class AnsyDownload{
	
	private final Logger log4j = Logger.getLogger(AnsyDownload.class);

	private Vector<Vector<NetFileItem>> downList = new Vector<Vector<NetFileItem>>();
	private AnsyDownloadFileThread[] downThread;
	
	public AnsyDownload(){
		int listCount = FileType.values().length;
		downList = new Vector<Vector<NetFileItem>>();
		
		downThread = new AnsyDownloadFileThread[listCount];
		for (int i=0; i<listCount; i++)
		{
			downList.add(new Vector<NetFileItem>());
			downThread[i] = new AnsyDownloadFileThread(downList.get(i));
			downThread[i].start();
		}
	}
	
	/**
	 * �����������¼�����ӿ�
	 * @param downloadFinish
	 * @return
	 */
	public AnsyDownload setDownloadFinish(AnsyDownloadStatusInterface downloadFinish) {
		for (int i=0; i<FileType.values().length; i++)
		{
			downThread[i].setDownloadFileInterface(downloadFinish);
		}
		return this;
	}

	/**
	 * ��������������ӽ����ض��С�
	 * @param item �ļ���Ϣ������servrePathֵ������ڡ�
	 */
	public AnsyDownload insertTask(NetFileItem item)
	{
		synchronized (downList) {
			log4j.info("StartDown " + item);
			downList.get(item.type.ordinal()).add(item);
		}
		return this;
	}
	
	/**
	 * ֹͣ���ط���
	 * ������Ҫʹ�����ط���ʱ���ִ�иò����������߳̽���ռ����Դ�������Զ��ͷš�
	 * һ��ִ����ò������ٴε��� insertTask ���������κ����á�
	 */
	public void setFinish()
	{
		for (int i=0; i<FileType.values().length; i++)
		{
			downThread[i].setFinish();
		}
	}
	
	/**
	 * �����ļ��Ƿ��ڻ�����
	 * @param item �ļ���Ϣ
	 * @return
	 */
	public static boolean isFileInCache(NetFileItem item)
	{
		return isFileInCache(item, -1);
	}
	
	/**
	 * �����ļ��Ƿ��ڻ�����ͬʱ�ļ������� fileLength ��ͬ
	 * @param item �ļ���Ϣ
	 * @param fileLength �ļ�����
	 * @return
	 */
	public static boolean isFileInCache(NetFileItem item, long fileLength)
	{
		if (item.getServerFullPath() == null || item.getServerFullPath().length() == 0)
		{
			return false;
		}
		File localFile = new File(item.getCachePath() + HttpUtils.getUrlFile(item.getServerFullPath()));
		if (fileLength <= 0 && localFile.exists())
		{
			item.setLocalPath(item.getCachePath() + HttpUtils.getUrlFile(item.getServerFullPath()));
			return true;
		}
		if (fileLength > 0 && localFile.exists() && localFile.length() == fileLength)
		{
			item.setLocalPath(item.getCachePath() + HttpUtils.getUrlFile(item.getServerFullPath()));
			return true;
		}
		return false;
	}
	public AnsyDownload setConnectTimeoutMillis(int connectTimeoutMillis) {
		if (connectTimeoutMillis <= 0)
			return this;
		for (int i=0; i<FileType.values().length; i++)
		{
			downThread[i].setConnectTimeoutMillis(connectTimeoutMillis);
		}
		return this;
	}

	public AnsyDownload setReadTimeoutMillis(int readTimeoutMillis) {
		if (readTimeoutMillis < 0)
			return this;
		for (int i=0; i<FileType.values().length; i++)
		{
			downThread[i].setReadTimeoutMillis(readTimeoutMillis);
		}
		return this;
	}
	
	public AnsyDownload setIgnoreCheckFile(boolean isIgnoreCheckFile) {
		for (int i=0; i<FileType.values().length; i++)
		{
			downThread[i].setIgnoreCheckFile(isIgnoreCheckFile);
		}
		return this;
	}
}
