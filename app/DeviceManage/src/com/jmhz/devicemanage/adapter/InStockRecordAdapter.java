package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.enter.InStockRecodeActivity;
import com.jmhz.devicemanage.model.PaymentItem;
import com.jmhz.devicemanage.salesorder.PaymentListActivity;
import com.uid.trace.module.stock.schema.InOutStockDetail;

public class InStockRecordAdapter extends BaseAdapter {

	private ArrayList<InOutStockDetail> mData = null;
	private InStockRecodeActivity mActivity = null;

	public InStockRecordAdapter(ArrayList<InOutStockDetail> mData,
			InStockRecodeActivity mActivity) {
		this.mData = mData;
		this.mActivity = mActivity;
	}

	protected class ViewHolder {
		protected TextView cid_key;
		protected TextView classCode_key;
		protected TextView proName_key;
		protected TextView proSpec_key;
		protected TextView proPrice_key;
		protected TextView uidNumber_key;
		protected TextView cid_value;
		protected TextView classCode_value;
		protected TextView proName_value;
		protected TextView proSpec_value;
		protected TextView proPrice_value;
		protected TextView uidNumber_value;
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

	public void addItem(InOutStockDetail item) {
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

	public void remove(InOutStockDetail item) {
		mData.remove(item);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(
					R.layout.instockrecord_list_item, null);
			holder = new ViewHolder();
			holder.cid_key = ((TextView) convertView.findViewById(
					R.id.cid).findViewById(R.id.tv_key));
			holder.classCode_key = ((TextView) convertView
					.findViewById(R.id.classCode).findViewById(R.id.tv_key));
			holder.proName_key = ((TextView) convertView
					.findViewById(R.id.proName).findViewById(R.id.tv_key));
			holder.proSpec_key = ((TextView) convertView
					.findViewById(R.id.proSpec).findViewById(
							R.id.tv_key));
			holder.proPrice_key = ((TextView) convertView
					.findViewById(R.id.proPrice).findViewById(
							R.id.tv_key));
			holder.uidNumber_key = ((TextView) convertView
					.findViewById(R.id.uidNumber).findViewById(
							R.id.tv_key));
			holder.cid_value = ((TextView) convertView
					.findViewById(R.id.cid).findViewById(R.id.tv_value));
			holder.classCode_value = ((TextView) convertView
					.findViewById(R.id.classCode).findViewById(R.id.tv_value));
			holder.proName_value = ((TextView) convertView
					.findViewById(R.id.proName).findViewById(R.id.tv_value));
			holder.proSpec_value = ((TextView) convertView
					.findViewById(R.id.proSpec).findViewById(
							R.id.tv_value));
			holder.proPrice_value = ((TextView) convertView
					.findViewById(R.id.proPrice).findViewById(
							R.id.tv_value));
			holder.uidNumber_value = ((TextView) convertView
					.findViewById(R.id.uidNumber).findViewById(
							R.id.tv_value));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		InOutStockDetail inStockDetail = mData.get(position);
		holder.cid_key.setText("流  水  号: ");
		holder.cid_value.setText(inStockDetail.getCid());
		holder.classCode_key.setText("类  别  码: ");
		holder.classCode_value.setText(inStockDetail.getClassCode());
		holder.proName_key.setText("商  品  名: ");
		holder.proName_value.setText(inStockDetail.getProName());
		holder.proSpec_key.setText("商品规格: ");
		holder.proSpec_value.setText(inStockDetail.getProSpec());
		holder.proPrice_key.setText("商品价格: ");
		holder.proPrice_value.setText(inStockDetail.getProPrice()+"");
		holder.uidNumber_key.setText("商品数量: ");
		holder.uidNumber_value.setText(inStockDetail.getUidNumber());

		return convertView;
	}

	

}
