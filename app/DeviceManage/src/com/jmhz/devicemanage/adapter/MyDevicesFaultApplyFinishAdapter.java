package com.jmhz.devicemanage.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.model.MyDevicesApplyInfo;

public class MyDevicesFaultApplyFinishAdapter extends BaseAdapter {

	private Context context;
	private List<MyDevicesApplyInfo> applyInfoList = new ArrayList<MyDevicesApplyInfo>();
	private LayoutInflater layoutInflater;

	public MyDevicesFaultApplyFinishAdapter(
			List<MyDevicesApplyInfo> applyInfoList, Context context) {
		// TODO Auto-generated constructor stub
		this.applyInfoList = applyInfoList;
		this.context = context;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return applyInfoList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return applyInfoList.get(position);
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
			convertView = layoutInflater.inflate(
					R.layout.mydevice_fault_apply_finish_info, parent, false);
			holder = new ViewHolder();
			holder.tv_apply_device_id = (TextView) convertView
					.findViewById(R.id.tv_fault_apply_finish_device_id);
			holder.tv_apply_device_name = (TextView) convertView
					.findViewById(R.id.tv_fault_apply_finish_device_name);
			holder.tv_apply_device_type = (TextView) convertView
					.findViewById(R.id.tv_fault_apply_finish_device_type);
			holder.tv_apply_device_user = (TextView) convertView
					.findViewById(R.id.tv_fault_apply_finish_device_user);
			holder.tv_apply_time = (TextView) convertView
					.findViewById(R.id.tv_fault_apply_finish_time);
			holder.tv_apply_describe = (TextView) convertView
					.findViewById(R.id.tv_fault_apply_finish_describe);
			holder.tv_fault_report= (TextView) convertView
					.findViewById(R.id.tv_fault_report_send);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_apply_device_id.setText(applyInfoList.get(position).getDeviceId()+"");
		holder.tv_apply_device_name.setText(applyInfoList.get(position).getDeviceName());
		holder.tv_apply_device_type.setText(applyInfoList.get(position).getDeviceType());
		holder.tv_apply_device_user.setText(applyInfoList.get(position).getDeviceUser());
		holder.tv_apply_time.setText(applyInfoList.get(position).getApplyTime());
		holder.tv_apply_describe.setText(applyInfoList.get(position).getApplydescribe());
		if(applyInfoList.get(position).getLogmark()==0)
		{
			holder.tv_fault_report.setTextColor(Color.RED);
			holder.tv_fault_report.setText("未提交");
		}
		else 
			holder.tv_fault_report.setText("已提交");
		return convertView;
	}

	protected class ViewHolder {
		protected TextView tv_apply_device_id;
		protected TextView tv_apply_device_name;
		protected TextView tv_apply_device_type;
		protected TextView tv_apply_device_user;
		protected TextView tv_apply_time;
		protected TextView tv_apply_describe;
		protected TextView tv_fault_report;
	}

	public void addItem(MyDevicesApplyInfo item) {
		applyInfoList.add(item);
		notifyDataSetChanged();
	}

	public void clear() {
		applyInfoList.clear();
		notifyDataSetChanged();
	}

	public void remove(int position) {
		applyInfoList.remove(position);
		notifyDataSetChanged();
	}

	public void remove(MyDevicesApplyInfo item) {
		applyInfoList.remove(item);
		notifyDataSetChanged();
	}
}
