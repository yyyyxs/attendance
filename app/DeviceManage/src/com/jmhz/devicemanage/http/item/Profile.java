package com.jmhz.devicemanage.http.item;

import java.io.File;

import android.os.Environment;

public class Profile {
	
	public static final String SDRoot = Environment.getExternalStorageDirectory().getPath() + File.separator;

	//ª∫¥ÊŒª÷√
	public static final String DownCacheDir = SDRoot + File.separator;
	public static final String PictureCacheDir = DownCacheDir;
	public static final String VoiceCacheDir = DownCacheDir;
	public static final String VideoCacheDir = DownCacheDir;
	public static final String NormalCacheDir = DownCacheDir;
}
