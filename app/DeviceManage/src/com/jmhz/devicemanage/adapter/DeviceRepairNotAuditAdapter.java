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
import com.jmhz.devicemanage.model.DeviceRepairInfo;
import com.jmhz.devicemanage.utils.ApproveTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;

public class DeviceRepairNotAuditAdapter extends BaseAdapter {

	private Context mContext;
	private List<DeviceRepairInfo> mDeviceRepairInfos = new ArrayList<DeviceRepairInfo>();
	private LayoutInflater mLayoutInflater;

	public DeviceRepairNotAuditAdapter(
			List<DeviceRepairInfo> mDeviceRepairInfos, Context context) {
		// TODO Auto-generated constructor stub
		this.mDeviceRepairInfos = mDeviceRepairInfos;
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDeviceRepairInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDeviceRepairInfos.get(position);
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
					R.layout.devicerepair_notaudit_info, parent, false);
			holder = new ViewHolder();
			holder = new ViewHolder();
			holder.tv_et_device_num = (TextView) convertView.findViewById(R.id.tv_et_device_num_repairnotaudit);
			holder.tv_et_deivce_name = (TextView) convertView.findViewById(R.id.tv_et_deivce_name_repairnotaudit);
			holder.tv_et_device_status = (TextView) convertView.findViewById(R.id.tv_et_device_status_repairnotaudit);
			holder.tv_et_device_repair_user = (TextView) convertView.findViewById(R.id.tv_et_device_repair_user_repairnotaudit);
			holder.tv_et_device_repair_applytime = (TextView) convertView.findViewById(R.id.tv_et_device_repair_applytime_repairnotaudit);
			holder.tv_et_device_repair_reason = (TextView) convertView.findViewById(R.id.tv_et_device_repair_reason_repairnotaudit);
			holder.tv_et_device_repair_approve = (TextView) convertView.findViewById(R.id.tv_et_device_repair_approve_repairnotaudit);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_et_device_num.setText(mDeviceRepairInfos.get(position)
				.getDeviceID());
		holder.tv_et_deivce_name.setText(mDeviceRepairInfos.get(position)
				.getDeviceName());
		int deviceStatus = Integer.parseInt(mDeviceRepairInfos.get(position).getDeviceStatus());
		holder.tv_et_device_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		holder.tv_et_device_repair_user.setText(mDeviceRepairInfos
				.get(position).getDeviceUser());
		holder.tv_et_device_repair_applytime.setText(mDeviceRepairInfos.get(
				position).getDeviceRepairApplyTime());
		holder.tv_et_device_repair_reason.setText(mDeviceRepairInfos.get(
				position).getDeviceRepairReason());
		int deviceApprove = Integer.parseInt(mDeviceRepairInfos.get(position).getDeviceRepairApprove());
		holder.tv_et_device_repair_approve.setText(ApproveTranslateUtils.deviceApprove(deviceApprove));
		return convertView;
	}

	protected class ViewHolder {
		protected TextView tv_et_device_num;
		protected TextView tv_et_deivce_name;
		protected TextView tv_et_device_status;
		protected TextView tv_et_device_repair_user;
		protected TextView tv_et_device_repair_applytime;
		protected TextView tv_et_device_repair_reason;
		protected TextView tv_et_device_repair_approve;
	}

	public void addItem(DeviceRepairInfo item) {
		mDeviceRepairInfos.add(item);
		notifyDataSetChanged();
	}

	public void clear() {
		mDeviceRepairInfos.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		mDeviceRepairInfos.remove(position);
		notifyDataSetChanged();
	}

	public void remove(DeviceRepairInfo item) {
		mDeviceRepairInfos.remove(item);
		notifyDataSetChanged();
	}
}
