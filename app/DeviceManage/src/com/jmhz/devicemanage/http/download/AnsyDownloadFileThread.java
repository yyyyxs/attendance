package com.jmhz.devicemanage.http.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jmhz.devicemanage.http.item.HttpUtils;
import com.jmhz.devicemanage.http.item.NetFileItem;

class AnsyDownloadFileThread extends Thread {

	private final Logger log4j = Logger.getLogger(AnsyDownloadFileThread.class);

	private Vector<NetFileItem> waitDownList;
	private AnsyDownloadStatusInterface downloadFileInterface;
	private boolean isContinue = true;

	private int connectTimeoutMillis = 3000;
	private int readTimeoutMillis = Integer.MAX_VALUE;
	private boolean isIgnoreCheckFile = false;

	AnsyDownloadFileThread(Vector<NetFileItem> waitDownList) {
		this.waitDownList = waitDownList;
	}

	public AnsyDownloadFileThread setDownloadFileInterface(
			AnsyDownloadStatusInterface downloadFileInterface) {
		this.downloadFileInterface = downloadFileInterface;
		return this;
	}

	public void setFinish() {
		isContinue = false;
	}

	@Override
	public void run() {
		while (true) {
			NetFileItem item = null;
			try {
				if (!isContinue)
					return;
				if (waitDownList.size() == 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}

				item = waitDownList.get(0);
				log4j.debug(item.getServerFullPath());

				String serverPath = item.getServerFullPath();

				AnsyDownloadStatus status;
				status = getFileSize(serverPath, item);
				if (status == AnsyDownloadStatus.SUCCESS) {
					String fileName = HttpUtils.getUrlFile(serverPath);
					if (isIgnoreCheckFile
							|| !AnsyDownload.isFileInCache(item,
									item.getFileLength())) {
						status = downfile(serverPath, item.getCachePath()
								+ fileName, item);
						if (status == AnsyDownloadStatus.SUCCESS)
							item.setLocalPath(item.getCachePath() + fileName);
					} else {

					}
				}
				downloadFileInterface.downStatus(item.type, status, 0);
				waitDownList.remove(0);
			} finally {
				if (item != null)
					item.increaseRetry();
			}
		}
	}

	public AnsyDownloadStatus getFileSize(String sURL, NetFileItem item) {
		long fileLength = -1;
		HttpURLConnection httpConnection = null;
		try {
			URL url = new URL(sURL);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setConnectTimeout(connectTimeoutMillis);
			httpConnection.setReadTimeout(connectTimeoutMillis);

			httpConnection
					.setRequestProperty("User-Agent", "Internet Explorer");
			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400) {
				System.err.println("Error Code : " + responseCode);
				return AnsyDownloadStatus.FAIL_CONNECT; // -2 represent access
														// is error
			}
			String sHeader;
			for (int i = 1;; i++) {
				sHeader = httpConnection.getHeaderFieldKey(i);
				if (sHeader == null) {
					break;
				}
				if (sHeader.equals("Content-Length")) {
					fileLength = Integer.parseInt(httpConnection
							.getHeaderField(sHeader));
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			log4j.error(e.getMessage());
			return AnsyDownloadStatus.GET_FILE_LENGTH_ERROR;
		} catch (MalformedURLException e) {
			log4j.error(e.getMessage());
			return AnsyDownloadStatus.URL_ANALYSIS_FAILED;
		} catch (IOException e) {
			log4j.error(e.getMessage());
			return AnsyDownloadStatus.FAIL_CONNECT;
		} finally {
			if (httpConnection != null)
				httpConnection.disconnect();
		}
		item.setFileLength(fileLength);
		return AnsyDownloadStatus.SUCCESS;
	}

	@SuppressWarnings("resource")
	private AnsyDownloadStatus downfile(String sURL, String savePath,
			NetFileItem item) {
		int nStartPos = 0;
		int nRead = 0;
		try {
			if (downloadFileInterface != null)
				downloadFileInterface.downStatus(item.type,
						AnsyDownloadStatus.START, 0);

			URL url = new URL(sURL);

			// Step 1 获得文件长度
			long nEndPos = item.getFileLength();

			// Step 2 创建文件
			new File(savePath).delete();
			RandomAccessFile oSavedFile = new RandomAccessFile(savePath, "rw");

			// Step 3 打开并设置连接
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection();
			httpConnection.setConnectTimeout(connectTimeoutMillis);
			httpConnection.setReadTimeout(readTimeoutMillis);

			// Step 3.1模拟IE客户端
			httpConnection
					.setRequestProperty("User-Agent", "Internet Explorer");

			// Step 3.2 告诉服务器文件从nStartPos字节开始传
			String sProperty = "bytes=" + nStartPos + "-";
			httpConnection.setRequestProperty("RANGE", sProperty);
			// System.out.println(sProperty);

			// Step 4 获取输入流并保存
			InputStream input = httpConnection.getInputStream();
			int rate = 0;
			byte[] b = new byte[1024];
			// 读取网络文件,写入指定的文件中
			while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
				oSavedFile.write(b, 0, nRead);
				nStartPos += nRead;
				if (nStartPos * 100 / nEndPos > rate) {
					if (downloadFileInterface != null)
						downloadFileInterface.downStatus(item.type,
								AnsyDownloadStatus.PROCESSING, rate);
					rate++;
				}
			}
			if (downloadFileInterface != null)
				downloadFileInterface.downStatus(item.type,
						AnsyDownloadStatus.PROCESSING, 100);
			// Step 5 关闭Http连接
			httpConnection.disconnect();
			System.out.println("下载完成");
		} catch (MalformedURLException e) {
			log4j.error(e.getMessage());
			return AnsyDownloadStatus.URL_ANALYSIS_FAILED;
		} catch (FileNotFoundException e) {
			log4j.error(e.getMessage());
			return AnsyDownloadStatus.FILE_CREATE_FAILED;
		} catch (IOException e) {
			log4j.error(e.getMessage());
			return AnsyDownloadStatus.FAIL_CONNECT;
		}
		return AnsyDownloadStatus.SUCCESS;
	}

	public void setConnectTimeoutMillis(int connectTimeoutMillis) {
		this.connectTimeoutMillis = connectTimeoutMillis;
	}

	public void setReadTimeoutMillis(int readTimeoutMillis) {
		this.readTimeoutMillis = readTimeoutMillis;
	}

	public void setIgnoreCheckFile(boolean isIgnoreCheckFile) {
		this.isIgnoreCheckFile = isIgnoreCheckFile;
	}
}
