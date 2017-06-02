package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.PaymentItem;
import com.jmhz.devicemanage.salesorder.PaymentListActivity;

public class PaymentAdapter extends BaseAdapter {

	private ArrayList<PaymentItem> mData = null;
	private PaymentListActivity mActivity = null;

	public PaymentAdapter(ArrayList<PaymentItem> mData,
			PaymentListActivity mActivity) {
		this.mData = mData;
		this.mActivity = mActivity;
	}

	protected class ViewHolder {
		protected TextView orderIdTextView_key;
		protected TextView sorderTimeTextView_key;
		protected TextView orderTotalTextView_key;
		protected TextView distributionModeTextView_key;
		protected TextView orderIdTextView_value;
		protected TextView orderTimeTextView_value;
		protected TextView orderTotalTextView_value;
		protected TextView distributionModeTextView_value;
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

	public void addItem(PaymentItem item) {
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

	public void remove(PaymentItem item) {
		mData.remove(item);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(
					R.layout.payment_list_item, null);
			holder = new ViewHolder();
			holder.orderIdTextView_key = ((TextView) convertView.findViewById(
					R.id.order_id).findViewById(R.id.tv_key));
			holder.sorderTimeTextView_key = ((TextView) convertView
					.findViewById(R.id.order_time).findViewById(R.id.tv_key));
			holder.orderTotalTextView_key = ((TextView) convertView
					.findViewById(R.id.order_total).findViewById(R.id.tv_key));
			holder.distributionModeTextView_key = ((TextView) convertView
					.findViewById(R.id.distribution_mode).findViewById(
							R.id.tv_key));
			holder.orderIdTextView_value = ((TextView) convertView
					.findViewById(R.id.order_id).findViewById(R.id.tv_value));
			holder.orderTimeTextView_value = ((TextView) convertView
					.findViewById(R.id.order_time).findViewById(R.id.tv_value));
			holder.orderTotalTextView_value = ((TextView) convertView
					.findViewById(R.id.order_total).findViewById(R.id.tv_value));
			holder.distributionModeTextView_value = ((TextView) convertView
					.findViewById(R.id.distribution_mode).findViewById(
							R.id.tv_value));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		PaymentItem paymentItem = mData.get(position);
		holder.orderIdTextView_key.setText("订单号: ");
		holder.sorderTimeTextView_key.setText("订单时间： ");
		holder.orderTotalTextView_key.setText("订单金额： ");
		holder.distributionModeTextView_key.setText("配送方式： ");
		holder.orderIdTextView_value.setText(paymentItem.getOrderId());
		holder.orderTimeTextView_value.setText(paymentItem.getOrderTime());
		holder.orderTotalTextView_value.setText(paymentItem.getOrderTotal());
		holder.distributionModeTextView_value.setText(paymentItem
				.getDistributionMode());
		holder.orderTotalTextView_value.setTextColor(mActivity.getResources()
				.getColor(R.color.red));
		holder.distributionModeTextView_value.setTextColor(mActivity
				.getResources().getColor(R.color.red));
		return convertView;
	}

	public void addItems(ArrayList<PaymentItem> rowResult) {
		mData.addAll(rowResult);
		notifyDataSetChanged();
	}

}
