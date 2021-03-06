package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.WaitExitItem;
import com.jmhz.devicemanage.mycenter.MyDialog;
import com.jmhz.devicemanage.utils.DialogUtils;
import com.jmhz.devicemanage.utils.DialogUtils.OnConfirmListener;
import com.jmhz.devicemanage.waitExit.WaitExitListActivity;
import com.uid.trace.module.stock.schema.NodeStockUid;

public class WaitExitAdapter extends BaseAdapter {
	public ArrayList<WaitExitItem> mData = null;
	private WaitExitListActivity mWaitExitActivity = null;
	private Dialog dialog = null;

	public WaitExitAdapter(ArrayList<WaitExitItem> mData,
			WaitExitListActivity mWaitActivity) {
		this.mData = mData;
		this.mWaitExitActivity = mWaitActivity;

	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addItem(WaitExitItem item) {
		mData.add(item);
		notifyDataSetChanged();
	}

	public void clear() {
		mData.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		mData.remove(position);
		notifyDataSetChanged();
	}

	public void remove(WaitExitItem item) {
		mData.remove(item);
		notifyDataSetChanged();
	}

	protected class ViewHolder {

		protected TextView tv_et_commodity_name;
		protected TextView tv_et_commodity_num;
		protected TextView tv_et_commodity_Unum;
		protected TextView tv_remove;
		protected TextView tv_wait_exit_confirm_delivery;
		protected TextView tv_wait_exit_check_subcodee;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mWaitExitActivity).inflate(
					R.layout.listview_wait_exit, null);
			holder = new ViewHolder();
			holder.tv_et_commodity_name = (TextView) convertView
					.findViewById(R.id.tv_et_wait_exit_commodity_name);
			holder.tv_et_commodity_num = (TextView) convertView
					.findViewById(R.id.tv_et_wait_exit_commodity_num);
			holder.tv_et_commodity_Unum = (TextView) convertView
					.findViewById(R.id.tv_et_wait_exit_commodity_Unum);
			holder.tv_remove = (TextView) convertView
					.findViewById(R.id.tv_remove);
			holder.tv_wait_exit_confirm_delivery = (TextView) convertView
					.findViewById(R.id.tv_wait_exit_confirm_delivery);
			holder.tv_wait_exit_check_subcodee = (TextView) convertView
					.findViewById(R.id.tv_wait_exit_check_subcodee);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_et_commodity_name.setText(mData.get(position)
				.getCommodityName());
		holder.tv_et_commodity_num.setText(mData.get(position)
				.getCommodityNum() + "");
		holder.tv_et_commodity_Unum.setText(mData.get(position)
				.getCommodityUnum());
		holder.tv_remove.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				new DialogUtils(mWaitExitActivity).showConfirmDialog(
						R.string.enter_warehouse_dialog_msg,
						new OnConfirmListener() {

							@Override
							public void onConfirm() {
								remove(position);
								Toast.makeText(mWaitExitActivity, "�Ƴ��ɹ�",
										Toast.LENGTH_SHORT).show();
								if (mData.size() <= 0) {
									mWaitExitActivity.setBlankView();
								}
							}
						});

			}
		});
		holder.tv_wait_exit_confirm_delivery
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						try {
							mWaitExitActivity.outStock(mData.get(position));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
		holder.tv_wait_exit_check_subcodee
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						String content = "";
						for (NodeStockUid nsu : mData.get(position).getNsus()) {
							content = content + nsu.getUidCode() + "\r\n";
						}
						dialog = new MyDialog(mWaitExitActivity,
								R.style.MyDialog);
						dialog.show();
						((TextView) dialog.getWindow().findViewById(
								R.id.tv_changepsd_prompt)).setText("�����б�");

						((TextView) dialog.getWindow().findViewById(
								R.id.tv_changepsd_prompt_content))
								.setText(content);
						((TextView) dialog.getWindow().findViewById(
								R.id.tv_changepsd_prompt_content))
								.setTextSize(22);

						dialog.getWindow().findViewById(R.id.btn_divide)
								.setVisibility(View.GONE);
						dialog.getWindow().findViewById(R.id.tv_changepsd_ok)
								.setVisibility(View.GONE);
						dialog.getWindow().findViewById(R.id.tv_changepsd_ok)
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View v) {
										dialog.dismiss();
									}
								});
					}
				});
		return convertView;
	}

}
