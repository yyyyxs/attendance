package com.jmhz.devicemanage.mydevices;

import com.jmhz.devicemanage.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.mycenter.EmailActivity;
import com.jmhz.devicemanage.mycenter.MyCenterActivity;

public class MyDevicesActivity extends BaseActivity {

	RelativeLayout relativelayout1;
    RelativeLayout relativelayout2;
    RelativeLayout relativelayout3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_devices);
		initializeMemberVariable(findViewById(R.id.mainHead));
		relativelayout1=(RelativeLayout)findViewById(R.id.relayout1);
		relativelayout2=(RelativeLayout)findViewById(R.id.relayout2);
		relativelayout3=(RelativeLayout)findViewById(R.id.relayout3);
		relativelayout1.setOnClickListener(new RelayoutClickListener());
		relativelayout2.setOnClickListener(new RelayoutClickListener());
		relativelayout3.setOnClickListener(new RelayoutClickListener());
	}
	class RelayoutClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it=new Intent();
			switch (v.getId())
			{
			
			case R.id.relayout1:
				it.setClass(MyDevicesActivity.this,MyDevicesListActivity.class);
				startActivity(it);
				break;
			case R.id.relayout2:
				it.setClass(MyDevicesActivity.this,FailureReportingProcessActivity.class);
				startActivity(it);
				break;
			case R.id.relayout3:
				it.setClass(MyDevicesActivity.this,UpgradeReportingProcessActivity.class);
				startActivity(it);
				break;
			}

		}
		
	}
	protected void initializeActivityHead() {
		centreText.setText("我的设备");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MyDevicesActivity.this.finish();
			}
		});
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MyDevicesActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
