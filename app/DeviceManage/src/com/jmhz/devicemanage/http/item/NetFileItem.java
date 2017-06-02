/**
 * 上传文件信息
 * 
 * @version 0.9
 * @author 陈楠
 */
package com.jmhz.devicemanage.http.item;

import java.io.Serializable;

import com.jmhz.devicemanage.http.download.FileType;

public class NetFileItem implements Serializable{

	private static final long serialVersionUID = -2720359765064996324L;
	
	public final FileType type;
	private String localPath;
	private String serverPath;
	private long fileLength;
	private int retry;
	
	public String toString()
	{
		return type.toString() + " " + 
				"localPath=" + localPath + " " + 
				"serverPath=" + serverPath + " " + 
				"length=" + fileLength + " " + 
				"retry=" + retry;
	}
	
	public void increaseRetry()
	{
		retry++;
	}
	public int getRetry()
	{
		return retry;
	}
	public void initRetry()
	{
		retry = 0;
	}
	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long l) {
		this.fileLength = l;
	}
	
	
	public NetFileItem(FileType fileType)
	{
		this.type = fileType;
		localPath = null;
		serverPath = null;
		retry = 0;
	}
	
	public String getLocalPath() {
		return localPath;
	}
	public String getServerPath() {
		return serverPath;
	}
	
	public String getServerFullPath()
	{
		switch(type)
		{
		case Picture:
			return serverPath;
		case Voice:
			return serverPath;
		case Video:
			return serverPath;
		case NormalFile:
			return serverPath;
		}
		return serverPath;
	}
	
	public String getCachePath()
	{
		switch(type)
		{
		case Picture:
			return Profile.PictureCacheDir;
		case Voice:
			return Profile.VoiceCacheDir;
		case Video:
			return Profile.VideoCacheDir;
		case NormalFile:
			return Profile.NormalCacheDir;
		}
		return "/mnt/sdcard/";
	}
	
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public void setLocalPath(String localPath)
	{
		this.localPath = localPath;
	}
}
