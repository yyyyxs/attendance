/*******************************************************************************
 * 版权属于福建省银联和福建省优艾迪网络信息有限公司共同拥有 
 * 
 * 银联二维码支付平?
 *  
 * @author 福建省优艾迪网络信息有限公司
 ******************************************************************************/
package com.jmhz.devicemanage.utils;

import java.lang.reflect.Type;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import android.util.*;

public class JSONUtils {
	private final static String EMPTY_STRING = "";

	/**
	 * Convert <code>Object</code> to JSON format string
	 * 
	 * @param object
	 * @return JSON string
	 */
	public static String ObjectToJson(Object object) {
		String json = EMPTY_STRING;
		Gson gson = new Gson();
		try {
			json = gson.toJson(object);
		} catch (Exception e) {
			Log.e("JSONUtils", "Error:", e);
		}
		return json;
	}

	/**
	 * Convert JSON format string to generic class
	 * 
	 * @param json
	 * @return <code>Data</code>
	 */
	public static <T> T JsonStrToClass(String json, Class<T> valueType) {
		GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(
				byte[].class, new JsonDeserializer<byte[]>() {
					public byte[] deserialize(JsonElement json, Type typeOfT,
							JsonDeserializationContext context)
							throws JsonParseException {
						try {
							String str = json.getAsString();
							byte[] base64DecodeData = Base64.decode(str,
									Base64.DEFAULT);
							Log.i("JSONUtils base64DecodeData",
									Arrays.toString(base64DecodeData));
							return base64DecodeData;
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						return null;
					}
				});
		Gson gson = gsonBuilder.create();
		T resultType = null;
		try {
			resultType = gson.fromJson(json, valueType);
		} catch (Exception e) {
			Log.e("JSONUtils", "Error:", e);
		}
		return (T) resultType;
	}

}
