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
import com.jmhz.devicemanage.model.DeviceUpdateInfo;
import com.jmhz.devicemanage.utils.ApproveTranslateUtils;
import com.jmhz.devicemanage.utils.StatusTranslateUtils;
import com.jmhz.devicemanage.utils.TypeTranslateUtils;

public class DeviceUpdateAuditAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<DeviceUpdateInfo> mDeviceUpdateInfos = new ArrayList<DeviceUpdateInfo>();
	private LayoutInflater mLayoutInflater;
	
	public DeviceUpdateAuditAdapter(List<DeviceUpdateInfo> mDeviceUpdateInfos, Context context) {
		// TODO Auto-generated constructor stub
		this.mDeviceUpdateInfos = mDeviceUpdateInfos;
		this.mContext = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDeviceUpdateInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDeviceUpdateInfos.get(position);
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
			convertView = mLayoutInflater.inflate(R.layout.deviceupdate_audit_info, parent, false);
			holder = new ViewHolder();
			holder.tv_et_device_num = (TextView) convertView.findViewById(R.id.tv_et_device_num_updateaudit);
			holder.tv_et_deivce_name = (TextView) convertView.findViewById(R.id.tv_et_deivce_name_updateaudit);
			holder.tv_et_deivce_type = (TextView) convertView.findViewById(R.id.tv_et_deivce_type_updateaudit);
			holder.tv_et_deivce_status = (TextView) convertView.findViewById(R.id.tv_et_deivce_status_updateaudit);
			holder.tv_et_device_update_user = (TextView) convertView.findViewById(R.id.tv_et_device_update_user_updateaudit);
			holder.tv_et_device_update_applytime = (TextView) convertView.findViewById(R.id.tv_et_device_update_applytime_updateaudit);
			holder.tv_et_device_update_reason = (TextView) convertView.findViewById(R.id.tv_et_device_update_reason_updateaudit);
			holder.tv_et_device_update_approve = (TextView) convertView.findViewById(R.id.tv_et_device_update_approve_updateaudit);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_et_device_num.setText(mDeviceUpdateInfos.get(position).getDeviceID());
		holder.tv_et_deivce_name.setText(mDeviceUpdateInfos.get(position).getDeviceName());
		int deviceType = Integer.parseInt(mDeviceUpdateInfos.get(position).getDeviceType());
		holder.tv_et_deivce_type.setText(TypeTranslateUtils.deviceType(deviceType));
		int deviceStatus = Integer.parseInt(mDeviceUpdateInfos.get(position).getDeviceStatus());
		holder.tv_et_deivce_status.setText(StatusTranslateUtils.deviceStatus(deviceStatus));
		holder.tv_et_device_update_user.setText(mDeviceUpdateInfos.get(position).getDeviceUser());
		holder.tv_et_device_update_applytime.setText(mDeviceUpdateInfos.get(position).getDeviceUpdateApplyTime());
		holder.tv_et_device_update_reason.setText(mDeviceUpdateInfos.get(position).getDeviceUpdateReason());
		int deviceApprove = Integer.parseInt(mDeviceUpdateInfos.get(position).getDeviceUpdateApprove());
		holder.tv_et_device_update_approve.setText(ApproveTranslateUtils.deviceApprove(deviceApprove));
		return convertView;
	}
	
	protected class ViewHolder {
		protected TextView tv_et_device_num;
		protected TextView tv_et_deivce_name;
		protected TextView tv_et_deivce_type;
		protected TextView tv_et_deivce_status;
		protected TextView tv_et_device_update_user;
		protected TextView tv_et_device_update_applytime;
		protected TextView tv_et_device_update_reason;
		protected TextView tv_et_device_update_approve;
	}

}
