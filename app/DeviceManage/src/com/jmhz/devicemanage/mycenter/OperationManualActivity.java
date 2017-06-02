package com.jmhz.devicemanage.mycenter;


import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

public class OperationManualActivity extends BaseActivity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operation_manual);
		initializeMemberVariable(findViewById(R.id.mainHead));
		
		
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.operation_manual);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				OperationManualActivity.this.finish();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 if (keyCode == KeyEvent.KEYCODE_BACK) {
			 OperationManualActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}
}
