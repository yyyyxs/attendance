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
import com.jmhz.devicemanage.enter.WaitDeliveryListActivity;
import com.jmhz.devicemanage.model.WaitDeliveryItem;
import com.jmhz.devicemanage.mycenter.MyDialog;
import com.jmhz.devicemanage.utils.DialogUtils;
import com.jmhz.devicemanage.utils.DialogUtils.OnConfirmListener;
import com.uid.trace.module.stock.schema.NodeStockUid;

public class WaitDeliveryAdapter extends BaseAdapter {
	private ArrayList<WaitDeliveryItem> mData = null;
	private WaitDeliveryListActivity mWaitActivity = null;
	private Dialog dialog = null;

	public WaitDeliveryAdapter(ArrayList<WaitDeliveryItem> mData,
			WaitDeliveryListActivity mWaitActivity) {
		this.mData = mData;
		this.mWaitActivity = mWaitActivity;

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

	public void addItem(WaitDeliveryItem item) {
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

	public void remove(WaitDeliveryItem item) {
		mData.remove(item);
		notifyDataSetChanged();
	}

	protected class ViewHolder {

		protected TextView tv_et_commodity_name;
		protected TextView tv_et_commodity_num;
		protected TextView tv_et_commodity_Unum;
		protected TextView tv_wait_deliveryremove;
		protected TextView tv_wait_deliverycheck_subcodee;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mWaitActivity).inflate(
					R.layout.listview_wait_delivery, null);
			holder = new ViewHolder();
			holder.tv_et_commodity_name = (TextView) convertView
					.findViewById(R.id.tv_et_wait_delivery_commodity_name);
			holder.tv_et_commodity_num = (TextView) convertView
					.findViewById(R.id.tv_et_wait_delivery_commodity_num);
			holder.tv_et_commodity_Unum = (TextView) convertView
					.findViewById(R.id.tv_et_wait_delivery_commodity_Unum);
			holder.tv_wait_deliveryremove = (TextView) convertView
					.findViewById(R.id.tv_wait_deliveryremove);
			holder.tv_wait_deliverycheck_subcodee = (TextView) convertView
					.findViewById(R.id.tv_wait_deliverycheck_subcodee);
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
		holder.tv_wait_deliveryremove
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						new DialogUtils(mWaitActivity).showConfirmDialog(
								R.string.enter_warehouse_dialog_msg,
								new OnConfirmListener() {

									@Override
									public void onConfirm() {
										remove(position);
										Toast.makeText(mWaitActivity, "移除成功",
												Toast.LENGTH_SHORT).show();
										if (mData.size() <= 0) {
											mWaitActivity.setBlankView();
										}
									}
								});

					}
				});
		holder.tv_wait_deliverycheck_subcodee
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						String content = "";
						for (NodeStockUid nsu : mData.get(position).getNsus()) {
							content = content + nsu.getUidCode() + "\r\n";
						}
						dialog = new MyDialog(mWaitActivity, R.style.MyDialog);
						dialog.show();
						((TextView) dialog.getWindow().findViewById(
								R.id.tv_changepsd_prompt)).setText("字码列表");

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
