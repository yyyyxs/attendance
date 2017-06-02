package com.jmhz.devicemanage.stocksummary;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.MaintainAdapter;
import com.jmhz.devicemanage.model.MaintainUpdateItem;

public class MaintainNumberQuery extends BaseActivity {

	private ListView mMaintain =null;
	private MaintainAdapter mMaintainAdapter = null;
	private String mFloor = ""; 
	private String mClass = "";
	private List<MaintainUpdateItem> mMaintainUpdateItem = new ArrayList<MaintainUpdateItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistic_query);
     	initializeMemberVariable(findViewById(R.id.mainHead));	
     	initWight();
     	Bundle bundle = this.getIntent().getExtras();
     	String Floor = bundle.getString("Floor");
     	mFloor= Floor;
     	String Class = bundle.getString("Class");
     	mClass= Class;
     	mMaintainAdapter = new MaintainAdapter(getListData(), this);
     	mMaintain.setAdapter(mMaintainAdapter);
	}

	private List<MaintainUpdateItem> getListData() {
		// TODO Auto-generated method stub
		MaintainUpdateItem maintainUpdateItem = new MaintainUpdateItem();
		maintainUpdateItem.setAdress(mFloor+mClass);
		maintainUpdateItem.setAlready("11");
		maintainUpdateItem.setWait("12");
		mMaintainUpdateItem.add(maintainUpdateItem);
		return mMaintainUpdateItem;
	}

	private void initWight() {
		// TODO Auto-generated method stub
		mMaintain = (ListView) findViewById(R.id.device_total_number);
	}

	protected void initializeActivityHead() {
		centreText.setText(R.string.maintain_number);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});  
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Summary.setOperate("维修设备统计");
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	
}
