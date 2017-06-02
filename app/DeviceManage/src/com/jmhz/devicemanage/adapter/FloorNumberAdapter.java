package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import com.jmhz.devicemanage.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FloorNumberAdapter extends BaseAdapter {

	private ArrayList<String> mFloor = null;
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public FloorNumberAdapter(ArrayList<String> mFloor, Context context) {
		this.mFloor = mFloor;
		this.mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFloor.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mFloor.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.floor_class_item,
					parent, false);
			holder = new ViewHolder();
			holder.floor_name = ((TextView) convertView
					.findViewById(R.id.text_content));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.floor_name.setText(mFloor.get(position));
		return convertView;
	}

	protected class ViewHolder {
		protected TextView floor_name;
	}

	public void addItem(String item) {
		mFloor.add(item);
		notifyDataSetChanged();
	}

	public void clear() {
		mFloor.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		mFloor.remove(position);
		notifyDataSetChanged();
	}

	public void remove(String item) {
		mFloor.remove(item);
		notifyDataSetChanged();
	}
}
