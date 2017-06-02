package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.TotalNumberItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TotalNumberAdapter extends BaseAdapter {
	
	private List<TotalNumberItem> mTotalNumberItem = new ArrayList<TotalNumberItem>();
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	public TotalNumberAdapter(List<TotalNumberItem> mTotalNumberItem, Context context) {
		// TODO Auto-generated constructor stub
		this.mTotalNumberItem = mTotalNumberItem;
		this.mContext = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTotalNumberItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mTotalNumberItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.item_total_number_query, parent, false);
			holder = new ViewHolder();
			holder.eidt_total_number = (TextView) convertView.findViewById(R.id.eidt_total_number);
			holder.eidt_public = (TextView) convertView.findViewById(R.id.eidt_public);
			holder.eidt_private = (TextView) convertView.findViewById(R.id.eidt_private);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.eidt_total_number.setText(mTotalNumberItem.get(position).getDeviceNumber());
		holder.eidt_public.setText(mTotalNumberItem.get(position).getPubHave());
		holder.eidt_private.setText(mTotalNumberItem.get(position).getPriHave());

		return convertView;
	}
	
	protected class ViewHolder {
		protected TextView eidt_total_number;
		protected TextView eidt_public;
		protected TextView eidt_private;
	}
    
	public void addItem(TotalNumberItem item) {
		mTotalNumberItem.add(item);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mTotalNumberItem.clear();
		notifyDataSetChanged();
	}
	
	public void remove(int position) {
		mTotalNumberItem.remove(position);
		notifyDataSetChanged();
	}
	
	public void remove(TotalNumberItem item) {
		mTotalNumberItem.remove(item);
		notifyDataSetChanged();
	}
}
