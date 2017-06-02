package com.jmhz.devicemanage.mydevices;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

public class ChangeLogActivity extends BaseActivity {


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_devices_changelog);
		initializeMemberVariable(findViewById(R.id.mainHead));
	}
	
	protected void initializeActivityHead() {
		centreText.setText(R.string.changelog);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				ChangeLogActivity.this.finish();
			}
		});

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_BACK) {
			 ChangeLogActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}

}
