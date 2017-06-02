package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.DeviceManageItem;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;

public class DeviceManageAdapter extends BaseAdapter {
	
	private List<DeviceManageItem> mDeviceManageItem = new ArrayList<DeviceManageItem>();
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	public DeviceManageAdapter(List<DeviceManageItem> mDeviceManageItem, Context context) {
		// TODO Auto-generated constructor stub
		this.mDeviceManageItem = mDeviceManageItem;
		this.mContext = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDeviceManageItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDeviceManageItem.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.listview_devicemanage_info, parent, false);
			holder = new ViewHolder();
			holder.tv_et_device_num = (TextView) convertView.findViewById(R.id.tv_et_device_num);
			holder.tv_et_deivce_name = (TextView) convertView.findViewById(R.id.tv_et_deivce_name);
			holder.tv_et_device_type = (TextView) convertView.findViewById(R.id.tv_et_device_type);
			holder.tv_et_device_buy_time = (TextView) convertView.findViewById(R.id.tv_et_device_buy_time);
			holder.tv_et_device_position = (TextView) convertView.findViewById(R.id.tv_et_device_position);
			holder.tv_et_device_status = (TextView) convertView.findViewById(R.id.tv_et_device_status_adapter);
			holder.tv_et_device__fuser = (TextView) convertView.findViewById(R.id.tv_et_device__fuser);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_et_device_num.setText(mDeviceManageItem.get(position).getDeviceID() + "");
		holder.tv_et_deivce_name.setText(mDeviceManageItem.get(position).getDeviceName());
		int deviceType = Integer.parseInt(mDeviceManageItem.get(position).getDeviceType());
		holder.tv_et_device_type.setText(TypeTranslateUtils.deviceType(deviceType));
		holder.tv_et_device_buy_time.setText(mDeviceManageItem.get(position).getDeciceBuyTime());
		int devicePosition = Integer.parseInt(mDeviceManageItem.get(position).getDevicePosition());
		holder.tv_et_device_position.setText(PositionTranslateUtils.devicePosition(devicePosition));
		int deviceStatus = Integer.parseInt(mDeviceManageItem.get(position).getDeviceStatus());
		holder.tv_et_device_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		holder.tv_et_device__fuser.setText(mDeviceManageItem.get(position).getDeviceUser());
		return convertView;
	}
	
	protected class ViewHolder {
		protected TextView tv_et_device_num;
		protected TextView tv_et_deivce_name;
		protected TextView tv_et_device_type;
		protected TextView tv_et_device_buy_time;
		protected TextView tv_et_device_position;
		protected TextView tv_et_device__fuser;
		protected TextView tv_et_device_status;
	}
	
	public void addItem(DeviceManageItem item) {
		mDeviceManageItem.add(item);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mDeviceManageItem.clear();
		notifyDataSetChanged();
	}
	
	public void remove(int position) {
		mDeviceManageItem.remove(position);
		notifyDataSetChanged();
	}
	
	public void remove(DeviceManageItem item) {
		mDeviceManageItem.remove(item);
		notifyDataSetChanged();
	}
}
