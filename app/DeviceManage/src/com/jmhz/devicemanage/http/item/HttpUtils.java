package com.jmhz.devicemanage.http.item;

public class HttpUtils {
	public static String getUrlFile(String url)
	{
		return url.substring(url.lastIndexOf("/")+1);
	}
}
