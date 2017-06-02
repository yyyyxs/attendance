package com.jmhz.devicemanage.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionKeeper {
	protected static final String TAG = SessionKeeper.class.getSimpleName();
	protected static final String TOKEN = "token";
	protected static final String USER_NAME = "userName";
	protected static final String EMAIL = "email";
	protected static final String PASSWORD = "password";
	
	public static void keepToken(Context context, String token) {
		SharedPreferences sp = context.getSharedPreferences(TAG, 0);
		Editor editor = sp.edit();
		editor.putString(TOKEN, token);
		editor.commit();
	}
	public static String getToken(Context context) {
		SharedPreferences sp =context.getSharedPreferences(TAG, 0);
		return sp.getString(TOKEN, "");
	}
	
	public static void keepUserName(Context context, String userName) {
		SharedPreferences sp = context.getSharedPreferences(TAG, 0);
		Editor editor = sp.edit();
		editor.putString(USER_NAME, userName);
		editor.commit();
	}
	public static String getUserName(Context context) {
		SharedPreferences sp =context.getSharedPreferences(TAG, 0);
		return sp.getString(USER_NAME, "");
	}
	
	public static void keepEmail(Context context, String email) {
		SharedPreferences sp = context.getSharedPreferences(TAG, 0);
		Editor editor = sp.edit();
		editor.putString(EMAIL, email);
		editor.commit();
	}
	public static String getEmail(Context context) {
		SharedPreferences sp =context.getSharedPreferences(TAG, 0);
		return sp.getString(EMAIL, "");
	}
	
	public static void keepPassword(Context context, String password) {
		SharedPreferences sp = context.getSharedPreferences(TAG, 0);
		Editor editor = sp.edit();
		editor.putString(PASSWORD, password);
		editor.commit();
	}
	public static String getPassword(Context context) {
		SharedPreferences sp =context.getSharedPreferences(TAG, 0);
		return sp.getString(PASSWORD, "");
	}
	
	public static void clearSession(Context context) {
		SharedPreferences sp = context.getSharedPreferences(TAG, 0);
		Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}
}
