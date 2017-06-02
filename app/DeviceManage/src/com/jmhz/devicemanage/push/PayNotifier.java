/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jmhz.devicemanage.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jmhz.devicemanage.ordermessage.OrderListActivity;
import com.jmhz.devicemanage.ordermessage.OrderMessageDetailsActivity;
import com.uid.trace.module.sale.schema.OrderMessage;


public class PayNotifier {

	private Context context;

	private NotificationManager notificationManager;

	private String title = "订单消息通知";

	public PayNotifier(Context context) {
		this.context = context;
		this.notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/**
	 * 通知栏弹出订单消息通知
	 * @param msg
	 */
	@SuppressWarnings("deprecation")
	public void notify(OrderMessage msg) {
		if(msg == null){
			 Log.i("PayNotifier", "msg is null...");
		}
        Log.i("PayNotifier", "msg content:"+msg.getContent());      
		notificationManager.cancel(2);
		Notification notification = NotificationUtils.getNotification(title);
		Intent intent = new Intent(context,OrderMessageDetailsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("orderSn", msg.getOrderSn());
		//bundle.putInt("position", position);
		intent.putExtras(bundle);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		notification.setLatestEventInfo(context, title, 
//		        msg.getContent(),contentIntent);
		notification.setLatestEventInfo(context, title,
				"您有新的订单消息,请查收!",contentIntent);
		notificationManager.notify(2, notification);
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
