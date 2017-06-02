package com.jmhz.devicemanage.waitdelivery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;

class WaitDeliveryAdapter extends BaseAdapter {
	Context mContent = null;
	String[] data = null;

	public WaitDeliveryAdapter(Context context, String[] data) {
		this.mContent = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			Log.i("null", " " + position);
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContent).inflate(
					R.layout.listview_wait_delivery_, null);
			holder.tv_et_commodity_name = (TextView) convertView.findViewById(R.id.tv_et_commodity_name);
			holder.tv_et_commodity_num = (TextView) convertView.findViewById(R.id.tv_et_commodity_num);
			holder.tv_et_commodity_Unum = (TextView) convertView.findViewById(R.id.tv_et_commodity_Unum);
			convertView.setTag(holder);
		} else {
			Log.i("not-null", " " + position);
			holder = (ViewHolder) convertView.getTag();
		}

		String item = (String) getItem(position);
		holder.tv_et_commodity_name.setText(item);
		holder.tv_et_commodity_num.setText(item+1);
		holder.tv_et_commodity_Unum.setText(item + 2);

		return convertView;
	}
}

class ViewHolder {
    TextView tv_et_commodity_name;
    TextView tv_et_commodity_num;
    TextView tv_et_commodity_Unum;
   }