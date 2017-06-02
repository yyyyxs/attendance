package com.jmhz.devicemanage.stocksummary;

import java.util.ArrayList;

import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.adapter.ClassNumberAdapter;
import com.jmhz.devicemanage.adapter.FloorNumberAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Spinner;




public class MaintainNumber extends Activity implements OnItemClickListener{
	
	private Spinner mSpfloor = null;
	private Spinner mSclass = null;
	private FloorNumberAdapter mFloorNumberAdapter = null;
	private ClassNumberAdapter mClassNumberAdapter = null;
	private ArrayList<String> mListFloorNumberItem = new ArrayList<String>();
	private ArrayList<String> mListClassNumberItem = new ArrayList<String>();
	private Button btnquery =null;
	private String FloorNum="";
	private String ClassNum="";
	private CheckBox orFloor,orClass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maintain_number);
		try {
			initView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> getClassDate() {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 9; i++) {
			String listClassNumberItem = new String();
			listClassNumberItem = "30"+i;
			mListClassNumberItem.add(listClassNumberItem);
		}
		return mListClassNumberItem;
	}


	private ArrayList<String> getFloorDate() {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 9; i++) {
			String listFloorNumberItem = new String();
			listFloorNumberItem = "Ñ§Ôº"+i+"ºÅÂ¥";
			mListFloorNumberItem.add(listFloorNumberItem);
		}
		return mListFloorNumberItem;
	}


	private void initView() {
		// TODO Auto-generated method stub
		mSpfloor = (Spinner) findViewById(R.id.spinner_floor_number);
		mSclass = (Spinner) findViewById(R.id.spinner_class_number);
		
		orFloor = (CheckBox) findViewById(R.id.checkbox_floor_number);
		orFloor.setChecked(true);
		
		orClass = (CheckBox) findViewById(R.id.checkbox_class_number);
		orClass.setChecked(true);
		
		mFloorNumberAdapter =new FloorNumberAdapter(getFloorDate(),this);
		mSpfloor.setAdapter(mFloorNumberAdapter);
		
		mClassNumberAdapter =new ClassNumberAdapter(getClassDate(),this);
		mSclass.setAdapter(mClassNumberAdapter);
		
		//getTransportNode();
		btnquery = (Button) findViewById(R.id.maintain_query);
		btnquery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (orFloor.isChecked()) {
					mSpfloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub
						}
					});
					String Floor = mSpfloor.getSelectedItem().toString();
					FloorNum=Floor;
				}
				else {
					FloorNum = "all";
				}
				if(orClass.isChecked()){
					mSclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
						}
						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub
						}
					});
					String Class = mSclass.getSelectedItem().toString();
					ClassNum = Class;
				}
				else{
					ClassNum="all";
				}
				Intent intent = new Intent(MaintainNumber.this,
						MaintainNumberQuery.class);
				Bundle buddle = new Bundle();
				buddle.putString("Floor", FloorNum);
				buddle.putString("Class", ClassNum);
				intent.putExtras(buddle);
				startActivity(intent);
			}
		});
	}


	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mFloorNumberAdapter != null) {
			mFloorNumberAdapter.clear();
		}
		if (mClassNumberAdapter != null) {
			mClassNumberAdapter.clear();
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
