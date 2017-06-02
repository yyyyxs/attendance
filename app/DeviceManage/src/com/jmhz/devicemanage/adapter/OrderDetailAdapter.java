package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.OrderDetailItem;
import com.jmhz.devicemanage.ordermessage.OrderDetailsActivity;

public class OrderDetailAdapter extends BaseAdapter {
	private ArrayList<OrderDetailItem> mData = null;
	private OrderDetailsActivity mOrderActivity = null;

	public OrderDetailAdapter(ArrayList<OrderDetailItem> mData,
			OrderDetailsActivity mWaitActivity) {
		this.mData = mData;
		this.mOrderActivity = mWaitActivity;

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

	protected class ViewHolder {

		protected TextView tv_et_order_order_num;
		protected TextView tv_et_order_transactor_name;
		protected TextView tv_et_order_sum;
		protected TextView tv_et_order_data;
		protected TextView tv_et_order_order_type;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mOrderActivity).inflate(
					R.layout.listview_order_detail, null);
			holder = new ViewHolder();
			holder.tv_et_order_order_num = (TextView) convertView
					.findViewById(R.id.tv_et_order_order_num);
			holder.tv_et_order_transactor_name = (TextView) convertView
					.findViewById(R.id.tv_et_order_transactor_name);
			holder.tv_et_order_sum = (TextView) convertView
					.findViewById(R.id.tv_et_order_sum);
			holder.tv_et_order_data = (TextView) convertView
					.findViewById(R.id.tv_et_order_data);
			holder.tv_et_order_order_type = (TextView) convertView
					.findViewById(R.id.tv_et_order_order_type);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_et_order_order_num.setText(mData.get(position)
				.getCommodityName());
		holder.tv_et_order_transactor_name.setText(mData.get(position)
				.getCommodityType());
		holder.tv_et_order_sum
				.setText(mData.get(position).getCommodityBuyNum());
		holder.tv_et_order_data
				.setText(mData.get(position).getCommodityMoney());
		holder.tv_et_order_order_type.setText(mData.get(position)
				.getCommoditySumMoney());

		return convertView;
	}

}
