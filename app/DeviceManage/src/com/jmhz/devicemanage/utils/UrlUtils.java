package com.jmhz.devicemanage.utils;

import com.jmhz.devicemanage.callback.ErrorHandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class UrlUtils {

	/**
	 * Simple method to open a URL link.
	 * @param context Activity which wants to open the link.
	 * @param url URL which will be opened.
	 * @param handler Exception handler.If handler is null then do nothing.
	 * @see {@link ErrorHandler}
	 */
	public static void openLink(Context context,String url,ErrorHandler handler){
		try {
			Uri uri = Uri.parse(url);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			context.startActivity(it);
		} catch (Exception e) {
			if(handler != null){
				handler.handle(e.getMessage());
			}
			e.printStackTrace();
		}
	}
}
