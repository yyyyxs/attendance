/**
 * 异步HTTP下载</br>
 * 可以多个队列同时依次下载，队列数量为FileType中类型数
 * 执行完毕后务必调用setFinish，否则线程将会占用资源而不会自动释放
 * 
 * 特别注意：要设置DownloadFinish接口，否则完成无任何提示
 * 
 * @version 0.9
 * @author 陈楠
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
	 * 设置下载中事件处理接口
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
	 * 将待下载数据添加进下载队列。
	 * @param item 文件信息，其中servrePath值必须存在。
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
	 * 停止下载服务。
	 * 当不需要使用下载服务时务必执行该操作，否则线程将会占用资源而不会自动释放。
	 * 一旦执行完该操作，再次调用 insertTask 将不会有任何作用。
	 */
	public void setFinish()
	{
		for (int i=0; i<FileType.values().length; i++)
		{
			downThread[i].setFinish();
		}
	}
	
	/**
	 * 检测该文件是否在缓存中
	 * @param item 文件信息
	 * @return
	 */
	public static boolean isFileInCache(NetFileItem item)
	{
		return isFileInCache(item, -1);
	}
	
	/**
	 * 检测该文件是否在缓存中同时文件长度与 fileLength 相同
	 * @param item 文件信息
	 * @param fileLength 文件长度
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
