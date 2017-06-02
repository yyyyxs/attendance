package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;

import com.jmhz.devicemanage.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassNumberAdapter extends BaseAdapter {

	private ArrayList<String> mClass = null;
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public ClassNumberAdapter(ArrayList<String> mClass, Context context) {
		this.mClass = mClass;
		this.mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mClass.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mClass.get(position);
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
			holder.class_name = ((TextView) convertView
					.findViewById(R.id.text_content));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.class_name.setText(mClass.get(position));
		return convertView;
	}

	protected class ViewHolder {
		protected TextView class_name;
	}

	public void addItem(String item) {
		mClass.add(item);
		notifyDataSetChanged();
	}

	public void clear() {
		mClass.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		mClass.remove(position);
		notifyDataSetChanged();
	}

	public void remove(String item) {
		mClass.remove(item);
		notifyDataSetChanged();
	}
}
