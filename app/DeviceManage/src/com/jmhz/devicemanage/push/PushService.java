package com.jmhz.devicemanage.push;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * 推送后台线程类
 * @author Administrator
 *
 */
public class PushService extends Service {
	
	//推送Service名称
	public static final String PUSH_SERVICE_NAME = "com.fzucfq.umkx.push.PushService";
	
	private static final String ACTION = "com.fzucfq.umkx.push";
	
	private BroadcastReceiver mAlarmReceiver;
	
	private AlarmManager am;
	
	private PendingIntent pi;
	
	private PushHandler mPushHandler = null;
	
	private BroadcastReceiver connectivityReceiver;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		startForeground(-1213, new Notification());
		Log.i("PushService", "onCreate");
		createReceiver();
		initAlarmManager();
		
	}
	
	private void createReceiver(){
		mAlarmReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				Log.d("Merchant AlarmManager", "Alarm to refresh connection..");
				if(mPushHandler != null){
					mPushHandler.onReceive();
				}
			}
		};
	}
	
	/**
	 * 定时链接推送服务器,保持长连接心跳
	 */
	private void initAlarmManager(){
		Log.i("PushService", "initAlarmManager...");
		am = (AlarmManager) (getSystemService(Context.ALARM_SERVICE));
		if(pi != null){
			Log.i("PushService", "before recreate initAlarmManager, cancel the current one....");
			am.cancel(pi);
		}
		IntentFilter intentFilter = new IntentFilter(ACTION);
		registerReceiver(mAlarmReceiver, intentFilter);
		pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION), 0);
		//每xx分钟发送一次心跳包
		am.setRepeating(AlarmManager.RTC_WAKEUP, 
				System.currentTimeMillis(), 1000 * 25, pi);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent != null){
			String userName = intent.getStringExtra("userName");
			Log.i("PushService", "userName:" + userName);
			mPushHandler = new PushHandler(this, userName);
			mPushHandler.start();
			connectivityReceiver = new PayConnReceiver(mPushHandler.getWebSocketClient());
			registerConnectivityReceiver();
		}
		return START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		Log.i("PushService", "onDestroy");
		if(mPushHandler != null)
			mPushHandler.stop();
		unRegisterConnectivityReceiver();
		connectivityReceiver = null;
		if(mAlarmReceiver != null){
			Log.i("PushService", "unregister alarm...");
			unregisterReceiver(mAlarmReceiver);
			mAlarmReceiver = null;
		}
		if(am != null){
			Log.i("PushService", "cancel the alarm...");
			am.cancel(pi);
			am = null;
		}
		super.onDestroy();
	}
	
	private void registerConnectivityReceiver() {
		IntentFilter filter = new IntentFilter();
        filter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectivityReceiver, filter);
	}

	private void unRegisterConnectivityReceiver() {
		if (connectivityReceiver != null) {
			unregisterReceiver(connectivityReceiver);
		}
	}	
	

	
	
}
