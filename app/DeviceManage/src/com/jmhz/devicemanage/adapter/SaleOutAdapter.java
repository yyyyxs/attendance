package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.NewOrderItem;
import com.jmhz.devicemanage.model.SaleOutItem;
import com.jmhz.devicemanage.stock.SaleOutListActivity;

public class SaleOutAdapter extends BaseAdapter {
	private ArrayList<SaleOutItem> mData = null;
	private SaleOutListActivity mSaleOutListActivity = null;

	public SaleOutAdapter(ArrayList<SaleOutItem> mData,
			SaleOutListActivity mSaleOutListActivity) {
		this.mData = mData;
		this.mSaleOutListActivity = mSaleOutListActivity;

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

		protected TextView tv_et_sale_order_num;
		protected TextView tv_et_sale_order_type;
		protected TextView tv_et_sale_commodity_num;
		protected TextView tv_et_sale_transactor_name;
		protected TextView tv_et_sale_sum;
		protected TextView tv_et_sale_data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mSaleOutListActivity).inflate(
					R.layout.listview_sale_out, null);
			holder = new ViewHolder();
			holder.tv_et_sale_order_num = (TextView) convertView
					.findViewById(R.id.tv_et_sale_order_num);
			holder.tv_et_sale_order_type = (TextView) convertView
					.findViewById(R.id.tv_et_sale_order_type);
			holder.tv_et_sale_commodity_num = (TextView) convertView
					.findViewById(R.id.tv_et_sale_commodity_num);
			holder.tv_et_sale_transactor_name = (TextView) convertView
					.findViewById(R.id.tv_et_sale_transactor_name);
			holder.tv_et_sale_sum = (TextView) convertView
					.findViewById(R.id.tv_et_sale_sum);
			holder.tv_et_sale_data = (TextView) convertView
					.findViewById(R.id.tv_et_sale_data);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_et_sale_order_num.setText(mData.get(position).getOrderNum());
		holder.tv_et_sale_order_type
				.setText(mData.get(position).getOrderType());
		holder.tv_et_sale_commodity_num.setText(mData.get(position)
				.getCommodityNum());
		holder.tv_et_sale_commodity_num.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("cfq", "订单详情");
				Toast.makeText(mSaleOutListActivity, "订单详情", Toast.LENGTH_SHORT);
			}
		});
		holder.tv_et_sale_transactor_name.setText(mData.get(position)
				.getTransactorName());
		holder.tv_et_sale_sum.setText(mData.get(position).getSum());
		holder.tv_et_sale_data.setText(mData.get(position).getData());

		return convertView;
	}

	public void addItem(SaleOutItem item) {
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

}
