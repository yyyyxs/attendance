package com.jmhz.devicemanage.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class PayConnReceiver extends BroadcastReceiver {

	private static final String LOGTAG = "ConnectivityReceiver";

	private LongPollingSocketClient mSocketClient = null;

	public PayConnReceiver(LongPollingSocketClient client) {
		this.mSocketClient = client;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(LOGTAG, "ConnectivityReceiver.onReceive()...");
		String action = intent.getAction();
		Log.d(LOGTAG, "action=" + action);

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null) {
			Log.d(LOGTAG, "Network Type  = " + networkInfo.getTypeName());
			Log.d(LOGTAG, "Network State = " + networkInfo.getState());
			if (networkInfo.isConnected()) {
				Log.i(LOGTAG, "Network connected");
				if (mSocketClient != null){
					mSocketClient.refreshConnection();
				}
			}
		} else {
			Log.e(LOGTAG, "Network unavailable");
			if (mSocketClient != null){
//				mSocketClient.disconnect();
			}
		}
	}

}
