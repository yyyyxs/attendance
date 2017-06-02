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
import com.jmhz.devicemanage.adapter.TotalNumberAdapter;
import com.jmhz.devicemanage.model.TotalNumberItem;

public class DeviceTotalNumberQuery extends BaseActivity {
	
	private ListView mTotalNumber =null;
	private TotalNumberAdapter mTotalNumberAdapter = null;

 	
//	private TextView eidt_total_number;
//	private TextView eidt_public;
//	private TextView eidt_private;
//	private Intent intent;
//	private Bundle bundle;
	private String mFloor; 
	private String mClass;
	private String mPubpri;
 	
	private List<TotalNumberItem> mTotalNumberItem = new ArrayList<TotalNumberItem>();
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
     	String Pubpri = bundle.getString("PubPri");
     	mPubpri=Pubpri;
     	
     	mTotalNumberAdapter = new TotalNumberAdapter(getListData(), this);
     	mTotalNumber.setAdapter(mTotalNumberAdapter);

	}

	private List<TotalNumberItem> getListData() {
		// TODO Auto-generated method stub
		TotalNumberItem totalNumberItem =new TotalNumberItem();
		totalNumberItem.setDeviceNumber(mFloor);
		totalNumberItem.setPubHave(mClass);
		totalNumberItem.setPriHave(mPubpri);
//		if(mPubpri.equals("公有")){
//			totalNumberItem.setPubHave("23");
//			totalNumberItem.setPriHave("0");
//		}
//		else if(mPubpri.equals("私有")){
//			totalNumberItem.setPriHave("23");
//			totalNumberItem.setPubHave("0");
//		}
//		else if(mPubpri.equals("all")){
//			totalNumberItem.setPubHave("11");
//			totalNumberItem.setPriHave("12");
//		}
		mTotalNumberItem.add(totalNumberItem);
		return mTotalNumberItem;
	}

	private void initWight() {
		// TODO Auto-generated method stub
		mTotalNumber = (ListView) findViewById(R.id.device_total_number);
	}

	protected void initializeActivityHead() {
		centreText.setText(R.string.total_number);
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
			Summary.setOperate("设备总数统计");
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
