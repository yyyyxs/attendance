package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.StockSummaryReportItem;
import com.jmhz.devicemanage.stocksummary.StockSummaryReportActivity;

public class StockSummaryReportAdapter extends BaseAdapter {

	private ArrayList<StockSummaryReportItem> mData = null;
	private StockSummaryReportActivity mActivity = null;

	public StockSummaryReportAdapter(ArrayList<StockSummaryReportItem> mData,
			StockSummaryReportActivity mActivity) {
		this.mData = mData;
		this.mActivity = mActivity;
	}

	protected class ViewHolder {
		protected TextView goodNameTextView_key;
		protected TextView stockNumberTextView_key;
		protected TextView goodStandardTextView_key;
		protected TextView goodPriceTextView_key;
		protected TextView goodNameTextView_value;
		protected TextView stockNumberTextView_value;
		protected TextView goodStandardTextView_value;
		protected TextView goodPriceTextView_value;
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

	public void addItem(StockSummaryReportItem item) {
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

	public void remove(StockSummaryReportItem item) {
		mData.remove(item);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(
					R.layout.stocksummaryreport_list_item, null);
			holder = new ViewHolder();
			holder.goodNameTextView_key = ((TextView) convertView.findViewById(
					R.id.good_name).findViewById(R.id.tv_key));
			holder.stockNumberTextView_key = ((TextView) convertView
					.findViewById(R.id.stock_number).findViewById(R.id.tv_key));
			holder.goodStandardTextView_key = ((TextView) convertView
					.findViewById(R.id.good_standard).findViewById(R.id.tv_key));
			holder.goodPriceTextView_key = ((TextView) convertView
					.findViewById(R.id.good_unitprice)
					.findViewById(R.id.tv_key));
			holder.goodNameTextView_value = ((TextView) convertView
					.findViewById(R.id.good_name).findViewById(R.id.tv_value));
			holder.stockNumberTextView_value = ((TextView) convertView
					.findViewById(R.id.stock_number)
					.findViewById(R.id.tv_value));
			holder.goodStandardTextView_value = ((TextView) convertView
					.findViewById(R.id.good_standard).findViewById(
							R.id.tv_value));
			holder.goodPriceTextView_value = ((TextView) convertView
					.findViewById(R.id.good_unitprice).findViewById(
							R.id.tv_value));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		StockSummaryReportItem stockSummaryReportItem = mData.get(position);
		holder.goodNameTextView_key.setText("商品名称: ");
		holder.stockNumberTextView_key.setText("库存数量： ");
		holder.goodStandardTextView_key.setText("商品规格： ");
		holder.goodPriceTextView_key.setText("商品价格： ");
		holder.goodNameTextView_value.setText(stockSummaryReportItem
				.getGoodName());
		holder.stockNumberTextView_value.setText(stockSummaryReportItem
				.getStockNumber());
		holder.goodStandardTextView_value.setText(stockSummaryReportItem
				.getGoodStandard());
		holder.goodPriceTextView_value.setText(stockSummaryReportItem
				.getGoodPrice() + "");
		return convertView;
	}
}
