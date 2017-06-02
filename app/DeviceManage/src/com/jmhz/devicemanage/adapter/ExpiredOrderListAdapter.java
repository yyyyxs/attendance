package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.NewOrderItem;
import com.jmhz.devicemanage.ordermessage.ExpiredOrderListActivity;

public class ExpiredOrderListAdapter extends BaseAdapter {

	private ArrayList<NewOrderItem> mData = null;
	private ExpiredOrderListActivity mActivity = null;

	public ExpiredOrderListAdapter(ArrayList<NewOrderItem> mData,
			ExpiredOrderListActivity mActivity) {
		this.mData = mData;
		this.mActivity = mActivity;
	}

	protected class ViewHolder {
		protected TextView contentTextView;
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

	public void addItem(NewOrderItem item) {
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

	public void remove(NewOrderItem item) {
		mData.remove(item);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(
					R.layout.neworder_list_item, null);
			holder = new ViewHolder();
			holder.contentTextView = ((TextView) convertView
					.findViewById(R.id.content));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		NewOrderItem newOrderItem = mData.get(position);
		String content = "尊敬的" + newOrderItem.getReceiver() + ":新订单在"
				+ newOrderItem.getPushTime() + "成功提交，内容为："
				+ newOrderItem.getContent();
		holder.contentTextView.setText(content);
		if (newOrderItem.getIsRead() == 1) {
			holder.contentTextView.setTextColor(mActivity.getResources()
					.getColor(R.color.bg_color));
		}
		return convertView;
	}

}
