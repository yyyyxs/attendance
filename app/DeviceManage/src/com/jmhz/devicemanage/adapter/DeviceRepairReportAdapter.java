package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;
import java.util.List;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.DeviceRepairReportInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DeviceRepairReportAdapter extends BaseAdapter {
	
	private List<DeviceRepairReportInfo> mDeviceRepairReportInfos = new ArrayList<DeviceRepairReportInfo>();
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	public DeviceRepairReportAdapter(List<DeviceRepairReportInfo> mDeviceRepairReportInfos, Context context) {
		// TODO Auto-generated constructor stub
		this.mDeviceRepairReportInfos = mDeviceRepairReportInfos;
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDeviceRepairReportInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDeviceRepairReportInfos.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.device_repairreport_info, parent, false);
			holder = new ViewHolder();
			holder.tv_et_device_num  = (TextView) convertView.findViewById(R.id.tv_et_device_num_repairreport);
			holder.tv_et_deivce_name  = (TextView) convertView.findViewById(R.id.tv_et_deivce_name_repairreport);
			holder.tv_et_device_type  = (TextView) convertView.findViewById(R.id.tv_et_device_type_repairreport);
			holder.tv_et_device_deal_status  = (TextView) convertView.findViewById(R.id.tv_et_device_deal_status);
			holder.tv_et_device_repair_cost  = (TextView) convertView.findViewById(R.id.tv_et_device_repair_cost);
			holder.tv_et_device_change  = (TextView) convertView.findViewById(R.id.tv_et_device_change);
			holder.tv_et_device_repair_result  = (TextView) convertView.findViewById(R.id.tv_et_device_repair_result);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_et_device_num.setText(mDeviceRepairReportInfos.get(position).getDeviceId());
		holder.tv_et_device_type.setText(mDeviceRepairReportInfos.get(position).getDeviceType());
		holder.tv_et_deivce_name.setText(mDeviceRepairReportInfos.get(position).getDeviceName());
		holder.tv_et_device_deal_status.setText(mDeviceRepairReportInfos.get(position).getDeviceDealStatus());
		holder.tv_et_device_repair_cost.setText(mDeviceRepairReportInfos.get(position).getDeviceRepairCost());
		holder.tv_et_device_change.setText(mDeviceRepairReportInfos.get(position).getDeviceChange());
		holder.tv_et_device_repair_result.setText(mDeviceRepairReportInfos.get(position).getDeviceRepairResult());
		return convertView;
	}
	
	protected class ViewHolder {
		
		protected TextView tv_et_device_num;
		protected TextView tv_et_deivce_name;
		protected TextView tv_et_device_type;
		protected TextView tv_et_device_deal_status;
		protected TextView tv_et_device_repair_cost;
		protected TextView tv_et_device_change;
		protected TextView tv_et_device_repair_result;
	}
}
