package com.jmhz.devicemanage.adapter;

import java.util.List;

import com.jmhz.devicemanage.fragment.RepairDeviceNotAuditFragment;
import com.jmhz.devicemanage.model.EnterWarehouseItem;
import com.jmhz.devicemanage.model.MyDevicesListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.mydevices.MyDevicesListActivity;
import com.jmhz.devicemanage.utils.PositionTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;
public class MyDevicesAdapter extends BaseAdapter {
	private MyDevicesListActivity myDevicesActivity = null;
	private List<MyDevicesListItem> myDevicesList = null;

	public MyDevicesAdapter(MyDevicesListActivity myDevicesActivity,
			List<MyDevicesListItem> mydevicesList2) {
		super();
		this.myDevicesActivity = myDevicesActivity;
		this.myDevicesList = mydevicesList2;
	}
	
	public int getCount() {
		 
		return myDevicesList.size();
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myDevicesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	protected class ViewHolder {

		protected TextView et_lv_mydevice_id;
		protected TextView et_lv_mydevice_name;
		protected TextView et_lv_mydevice_type;
		protected TextView et_lv_mydevice_place;
		protected TextView et_lv_mydevice_user;
		protected TextView et_lv_mydevice_state;
		//protected TextView et_lv_mydevice_purchasetime;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) { 
			convertView=LayoutInflater.from(myDevicesActivity).inflate(R.layout.listview_my_devices, parent, false);
			holder = new ViewHolder();
			holder.et_lv_mydevice_id=(TextView) convertView.findViewById(R.id.et_lv_mydevice_id);
			holder.et_lv_mydevice_name=(TextView) convertView.findViewById(R.id.et_lv_mydevice_name);
			holder.et_lv_mydevice_type=(TextView) convertView.findViewById(R.id.et_lv_mydevice_type);
			holder.et_lv_mydevice_user=(TextView) convertView.findViewById(R.id.et_lv_mydevice_user);
			holder.et_lv_mydevice_place=(TextView) convertView.findViewById(R.id.et_lv_mydevice_place);
			holder.et_lv_mydevice_state=(TextView) convertView.findViewById(R.id.et_lv_mydevice_state);
			//holder.et_lv_mydevice_purchasetime=(TextView) convertView.findViewById(R.id.et_lv_mydevice_purchasetime);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.et_lv_mydevice_id.setText(myDevicesList.get(position).getDeviceId()+"");
		holder.et_lv_mydevice_name.setText(myDevicesList.get(position).getDeviceName());
		int deviceType = Integer.parseInt(myDevicesList.get(position).getDeviceType());
		holder.et_lv_mydevice_type.setText(TypeTranslateUtils.deviceType(deviceType));
		int devicePosition = Integer.parseInt(myDevicesList.get(position).getDevicePlace());
		holder.et_lv_mydevice_place.setText(PositionTranslateUtils.devicePosition(devicePosition));
		int deviceStatus = Integer.parseInt(myDevicesList.get(position).getDeviceState());
		holder.et_lv_mydevice_user.setText(myDevicesList.get(position).getDeviceUser());
		holder.et_lv_mydevice_state.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		return convertView;
	}
	public void addItem(MyDevicesListItem item)
	{
		myDevicesList.add(item);
		
		notifyDataSetChanged();
	}
	public void clear() {
		myDevicesList.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		myDevicesList.remove(position);
		notifyDataSetChanged();
	}

	public void remove(EnterWarehouseItem item) {
		myDevicesList.remove(item);
		notifyDataSetChanged();
	}
}
