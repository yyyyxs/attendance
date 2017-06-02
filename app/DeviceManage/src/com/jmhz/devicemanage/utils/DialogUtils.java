package com.jmhz.devicemanage.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.jmhz.devicemanage.R;

public class DialogUtils {
	
	private Activity mActivity = null;

	public DialogUtils(Activity mActivity) {
		this.mActivity = mActivity;
	}

	public interface OnConfirmListener {
		public void onConfirm();
	}
	
	public interface OnItemSelectedListener{
		public void onSelect(int position, String text);
	}
	
	public void showConfirmDialog(String msg, final OnConfirmListener listener){
		new AlertDialog.Builder(mActivity)
		.setTitle(R.string.notice)
		.setMessage(msg)
		.setPositiveButton(R.string.ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onConfirm();
			}
		})
		.setNegativeButton(R.string.cancle, null).create().show();
	}
	
	public void showConfirmDialog(int msgId, final OnConfirmListener listener){
		showConfirmDialog(mActivity.getText(msgId).toString(), listener);
	}
	
	public void showListDialog(final String items[], final OnItemSelectedListener listener){
		new AlertDialog.Builder(mActivity)
		.setTitle(R.string.please_choose)
		.setItems(items, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onSelect(which, items[which]);
			}
		}).create().show();
	}
}
