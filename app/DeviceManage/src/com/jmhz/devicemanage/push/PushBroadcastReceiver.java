package com.jmhz.devicemanage.push;

import com.jmhz.devicemanage.utils.ServiceUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class PushBroadcastReceiver extends BroadcastReceiver {

	private static final String LOGTAG = "PushBroadcastReceiver";
	private   String pushClientId = null;

	public PushBroadcastReceiver(String pushClientId){
		this.pushClientId = pushClientId;
	}
	@Override
	public void onReceive(final Context context, Intent intent) {
		Log.d(LOGTAG, "PushBroadcastReceiver.onReceive()...");
		if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) { 
			  
			//¼ì²éService×´Ì¬ 
			if (!ServiceUtils.isServiceRunning(context, 
					PushService.PUSH_SERVICE_NAME)) {
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
						Log.i(LOGTAG, "start push service...userName:"
						    + pushClientId);
						i.putExtra("userName",pushClientId);
						context.startService(i);
					}
				}).start();
			}  
			
		}
	}

}
