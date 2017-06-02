package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.MaintainUpdateItem;
import com.jmhz.devicemanage.model.TotalNumberItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MaintainAdapter extends BaseAdapter {

	private List<MaintainUpdateItem> mMaintainUpdateItem = new ArrayList<MaintainUpdateItem>();
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public MaintainAdapter(List<MaintainUpdateItem> mMaintainUpdateItem,
			Context context) {
		// TODO Auto-generated constructor stub
		this.mMaintainUpdateItem = mMaintainUpdateItem;
		this.mContext = context;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMaintainUpdateItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mMaintainUpdateItem.get(position);
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
			convertView = mLayoutInflater.inflate(
					R.layout.item_maintain_number_query, parent, false);
			holder = new ViewHolder();
			holder.main_total_number = (TextView) convertView
					.findViewById(R.id.main_total_number);
			holder.main_already = (TextView) convertView
					.findViewById(R.id.main_already);
			holder.main_wait = (TextView) convertView
					.findViewById(R.id.main_wait);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.main_total_number.setText(mMaintainUpdateItem.get(position)
				.getAdress());
		holder.main_already.setText(mMaintainUpdateItem.get(position)
				.getAlready());
		holder.main_wait.setText(mMaintainUpdateItem.get(position).getWait());
		return convertView;
	}

	protected class ViewHolder {
		protected TextView main_total_number;
		protected TextView main_already;
		protected TextView main_wait;
	}

	public void addItem(MaintainUpdateItem item) {
		mMaintainUpdateItem.add(item);
		notifyDataSetChanged();
	}

	public void clear() {
		mMaintainUpdateItem.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		mMaintainUpdateItem.remove(position);
		notifyDataSetChanged();
	}

	public void remove(MaintainUpdateItem item) {
		mMaintainUpdateItem.remove(item);
		notifyDataSetChanged();
	}

}
