package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;

public class YearAdapter extends BaseAdapter{
	private ArrayList<String> mYear = null;
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public YearAdapter(ArrayList<String> mYear, Context context) {
		this.mYear = mYear;
		this.mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mYear.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mYear.get(position);
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
			holder.year_name = ((TextView) convertView
					.findViewById(R.id.text_content));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.year_name.setText(mYear.get(position));
		return convertView;
	}

	protected class ViewHolder {
		protected TextView year_name;
	}

	public void addItem(String item) {
		mYear.add(item);
		notifyDataSetChanged();
	}

	public void clear() {
		mYear.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		mYear.remove(position);
		notifyDataSetChanged();
	}

	public void remove(String item) {
		mYear.remove(item);
		notifyDataSetChanged();
	}
}
