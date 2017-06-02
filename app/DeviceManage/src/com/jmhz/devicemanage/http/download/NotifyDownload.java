package com.jmhz.devicemanage.http.download;

import java.io.File;
import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.http.item.NetFileItem;


public class NotifyDownload {
	
	private Activity activity;
	private static AnsyDownload andyDownload;
	
	private NotificationManager manager; 
    private Notification notif; 
    private NetFileItem apkItem;
	
	public NotifyDownload(Activity activity)
	{
		this.activity = activity;
		downloadHandle = new DownloadHandler(this, activity);
		manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	public void startDownload(Activity activity, String url)
	{
		notif = new Notification(); 
        notif.tickerText = "新通知"; 
        //通知栏显示所用到的布局文件 
        notif.contentView = new RemoteViews(activity.getPackageName(), R.layout.util_notify_content_view);
        notif.icon = R.drawable.logo;
        notif.tickerText = "开始下载文件";
        
        PendingIntent contentIntent = PendingIntent.getActivity(activity, 0,  
        		activity.getIntent(), 0);  
        notif.contentIntent = contentIntent; 
        manager.notify(0, notif); 
        apkItem = new NetFileItem(FileType.NormalFile);
        apkItem.setServerPath(url);

        andyDownload = new AnsyDownload()
        .setDownloadFinish(downloadFinish)
        .setIgnoreCheckFile(true)
        .insertTask(apkItem);
	}
	
	private AnsyDownloadStatusInterface downloadFinish = new AnsyDownloadStatusInterface(){

		public void downStatus(FileType filetype, AnsyDownloadStatus status, int process) {
			Message msg = new Message();
			msg.getData().putInt("filetype", filetype.ordinal());
			msg.getData().putInt("process", process);
			
			downloadHandle.obtainMessage(status.ordinal(), msg).sendToTarget();
		}
		
	};
	
	private Handler downloadHandle;
	static class DownloadHandler extends Handler
	{
		WeakReference<NotifyDownload> mVersionUpdate;
		WeakReference<Context> mContext;
		DownloadHandler(NotifyDownload versionUpdate, Context context)
		{
			mVersionUpdate = new WeakReference<NotifyDownload>(versionUpdate);
			mContext = new WeakReference<Context>(context);
		}
		@Override
		public void handleMessage(Message msg) {
			NotifyDownload versionUpdate = mVersionUpdate.get();
			Activity activity = (Activity) mContext.get();
			Message submsg = (Message) msg.obj;
			AnsyDownloadStatus status = AnsyDownloadStatus.values()[msg.what];
			int progress = submsg.getData().getInt("process");
			
			switch (status) {
			case START:
				versionUpdate.notif.contentView.setTextViewText(R.id.content_view_text1, 0+"%"); 
				versionUpdate.notif.contentView.setProgressBar(R.id.content_view_progress, 100, progress, false); 
				versionUpdate.manager.notify(0, versionUpdate.notif);
				showShort(activity, "开始下载");
				break;
			case PROCESSING:
				versionUpdate.notif.contentView.setTextViewText(R.id.content_view_text1, progress+"%"); 
				versionUpdate.notif.contentView.setProgressBar(R.id.content_view_progress, 100, progress, false); 
				versionUpdate.manager.notify(0, versionUpdate.notif); 
				break;
			case SUCCESS:
				versionUpdate.manager.cancelAll();
				andyDownload.setFinish();
				versionUpdate.update();
				showShort(activity, "下载成功");
				break;
			case GET_FILE_LENGTH_ERROR:
				versionUpdate.manager.cancelAll();
				andyDownload.setFinish();
				showShort(activity, "下载失败:获得文件长度失败");
				break;
			case URL_ANALYSIS_FAILED:
				versionUpdate.manager.cancelAll();
				andyDownload.setFinish();
				showShort(activity, "下载失败:URL解析出错");
				break;
			case FILE_CREATE_FAILED:
				versionUpdate.manager.cancelAll();
				andyDownload.setFinish();
				showShort(activity, "下载失败:本地文件创建失败");
				break;
			case FAIL_CONNECT:
				versionUpdate.manager.cancelAll();
				andyDownload.setFinish();
				showShort(activity, "下载失败:建立连接有误");
				break;

			}
		}
	}
	
	public void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        
        intent.setDataAndType(
                Uri.fromFile(new File(apkItem.getLocalPath())),
                "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }
	
	private static void showShort(Activity activity, String msg) {
		Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		View view = LayoutInflater.from(activity).inflate(R.layout.toast, null);
		((TextView) view.findViewById(R.id.text)).setText(msg);
		toast.setView(view);
		toast.show();
	}
}
