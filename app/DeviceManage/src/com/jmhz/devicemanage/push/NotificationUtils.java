package com.jmhz.devicemanage.push;

import android.app.Notification;

import com.jmhz.devicemanage.R;

public class NotificationUtils {

	public static Notification getNotification(String tickerText){
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_notification;
		notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.when = System.currentTimeMillis();
		notification.tickerText = tickerText;
		return notification;
	}
}
