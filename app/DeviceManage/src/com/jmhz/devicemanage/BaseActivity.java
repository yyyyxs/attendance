package com.jmhz.devicemanage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jmhz.devicemanage.session.SessionKeeper;
import com.jmhz.devicemanage.table.UITableView;

public abstract class BaseActivity extends Activity {

	protected Button leftButton;
	protected TextView centreText;
	protected TextView bottomText;
	protected Button rightButton;
	protected UITableView uitableView = null;
	private static BaseActivity mInstance = null;

	protected void initializeMemberVariable(View headRL) {
		leftButton = (Button) headRL.findViewById(R.id.leftButton);
		centreText = (TextView) headRL.findViewById(R.id.centreText);
		// bottomText = (TextView) headRL.findViewById(R.id.bottomText);
		rightButton = (Button) headRL.findViewById(R.id.rightButton);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mInstance = this;
	}

	@Override
	protected void onResume() {
		super.onResume();
		initializeActivityHead();
	}

	protected boolean isEmpty(String str) {
		return TextUtils.isEmpty(str);
	}

	/** Session Begin */
	public void keepMemberSession(String token) {
		SessionKeeper.keepToken(this, token);
	}

	public String getMemberSession() {
		return SessionKeeper.getToken(this);
	}

	protected void keepUserNameSession(String userName) {
		SessionKeeper.keepUserName(this, userName);
	}

	public String getUserNameSession() {
		return SessionKeeper.getUserName(this);
	}

	protected void keepEmailSession(String email) {
		SessionKeeper.keepEmail(this, email);
	}

	public String getEmailSession() {
		return SessionKeeper.getEmail(this);
	}

	protected void keepPasswordSession(String psw) {
		SessionKeeper.keepPassword(this, psw);
	}

	public String getPasswordSession() {
		return SessionKeeper.getPassword(this);
	}

	protected void clearMemberSession() {
		SessionKeeper.clearSession(this);
	}

	// protected boolean isLogined() {
	// return !isEmpty(getSession());
	// }

	/**
	 * Activity Head Initialize
	 */

	protected abstract void initializeActivityHead();

	/** Toast Begin */
	protected void toastLong(String msg) {
		toast(msg, Toast.LENGTH_LONG);
	}

	protected void toastLong(int resId) {
		toastLong(getText(resId).toString());
	}

	protected void toastShort(String msg) {
		toast(msg, Toast.LENGTH_SHORT);
	}

	private void toast(String msg, int duration) {
		Toast toast = Toast.makeText(this, msg, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		View view = LayoutInflater.from(this).inflate(R.layout.toast, null);
		((TextView) view.findViewById(R.id.text)).setText(msg);
		toast.setView(view);
		toast.show();
	}

	protected void toastShort(int resId) {
		toastShort(getText(resId).toString());
	}

	/** Toast End */
	/** Others */
	public static BaseActivity getInstance() {
		return mInstance;
	}

	protected void startActivityWithClass(Class<?> c) {
		Intent intent = new Intent(this, c);
		startActivity(intent);
	}

	protected void startActivityWithClass(Intent it, Class<?> c) {
		Intent intent = new Intent(this, c);
		intent.putExtras(it);
		startActivity(intent);
	}

	protected void startActivityWithClassForResult(Class<?> c, int requestCode) {
		Intent intent = new Intent(this, c);
		startActivityForResult(intent, requestCode);
	}

	@SuppressLint("SdCardPath")
	protected File provinceDatabaseFile = new File(
			"/data/data/com.fzucfq.umkx/province_city_area");

	protected void initFileArea() {
		// provinceDatabaseFile.delete();
		if (!provinceDatabaseFile.exists()) {
			try {
				InputStream is = getResources().openRawResource(
						R.raw.province_city_area_new);
				int len = -1;
				byte data[] = new byte[1024];
				OutputStream os = new FileOutputStream(provinceDatabaseFile);
				while ((len = is.read(data)) != -1) {
					os.write(data, 0, len);
				}
				os.flush();
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
