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
import com.jmhz.devicemanage.model.DeviceUpdateReportInfo;

public class DeviceUpdateReportAdapter extends BaseAdapter{
	
	private List<DeviceUpdateReportInfo> mDeviceUpdateReportInfos = new ArrayList<DeviceUpdateReportInfo>();
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	public DeviceUpdateReportAdapter( List<DeviceUpdateReportInfo> mDeviceUpdateReportInfos, Context context) {

		this.mDeviceUpdateReportInfos = mDeviceUpdateReportInfos;
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDeviceUpdateReportInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDeviceUpdateReportInfos.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.device_updatereport_info, parent, false);
			holder = new ViewHolder();
			holder.tv_et_device_num = (TextView) convertView.findViewById(R.id.tv_et_device_num_updatereport);
			holder.tv_et_deivce_name = (TextView) convertView.findViewById(R.id.tv_et_deivce_name_updatereport);
			holder.tv_et_device_type = (TextView) convertView.findViewById(R.id.tv_et_device_type_updatereport);
			holder.tv_et_device_deal_status = (TextView) convertView.findViewById(R.id.tv_et_device_deal_status_updatereport);
			holder.tv_et_device_update_cost = (TextView) convertView.findViewById(R.id.tv_et_device_update_cost);
			holder.tv_et_device_change = (TextView) convertView.findViewById(R.id.tv_et_device_change_updatereport);
			holder.tv_et_device_update_result = (TextView) convertView.findViewById(R.id.tv_et_device_update_result);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_et_device_num.setText(mDeviceUpdateReportInfos.get(position).getDeviceId());
		holder.tv_et_deivce_name.setText(mDeviceUpdateReportInfos.get(position).getDeviceName());
		holder.tv_et_device_type.setText(mDeviceUpdateReportInfos.get(position).getDeviceType());
		holder.tv_et_device_deal_status.setText(mDeviceUpdateReportInfos.get(position).getDeviceDealStatus());
		holder.tv_et_device_update_cost.setText(mDeviceUpdateReportInfos.get(position).getDeviceUpdateCost());
		holder.tv_et_device_change.setText(mDeviceUpdateReportInfos.get(position).getDeviceChange());
		holder.tv_et_device_update_result.setText(mDeviceUpdateReportInfos.get(position).getDeviceUpdateResult());
		return convertView;
	}
	
	protected class ViewHolder {
		protected TextView tv_et_device_num;
		protected TextView tv_et_deivce_name;
		protected TextView tv_et_device_type;
		protected TextView tv_et_device_deal_status;
		protected TextView tv_et_device_update_cost;
		protected TextView tv_et_device_change;
		protected TextView tv_et_device_update_result;
	}
}
