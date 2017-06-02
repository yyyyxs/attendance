package com.jmhz.devicemanage.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.uid.trace.module.sale.schema.OrderMessage;


public class PushHandler extends Handler {

	protected OrderMessage notification = null;

	private Context mContext = null;
	
	private PayNotifier payNotifier = null;

	private LongPollingSocketClient mSocketClient = null;
	
	private String mClientId;

	public PushHandler(Context context, final String clientId) {
		this.mContext = context;
		this.mClientId = clientId;
		payNotifier = new PayNotifier(mContext);
		mSocketClient = new LongPollingSocketClient(PushHandler.this, clientId);
	}

	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Looper.prepare();
					mSocketClient.connect();
					Looper.loop();
				} catch (Exception e) {
					mSocketClient = null;
				}
			}
		}).start();
	}
	
	public void onReceive(){
		if(mSocketClient == null){
			mSocketClient = new LongPollingSocketClient(this, mClientId);
			start();
		} else {
			new Thread(new Runnable() {
				@Override
				public void run() {
					mSocketClient.refreshConnection();
				}
			}).start();
		}
	}

	public void handleMessage(final android.os.Message msg) {
		switch (msg.what) {
		case 1:
			Log.i("PushHandler", "Payment notification message");
			notification = (OrderMessage) msg.obj;
			payNotifier.notify(notification);
			break;

		}
	}

	public void stop() {
		Log.i("PushHandler", "stop");
		if (mSocketClient != null){
			new Thread(new Runnable() {
				@Override
				public void run() {
					mSocketClient.logout();
				}
			}).start();
		}
//		if(mReceiver != null){
//			mContext.unregisterReceiver(mReceiver);
//		}
	};
	
	public LongPollingSocketClient getWebSocketClient(){
		return mSocketClient;
	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}
}
