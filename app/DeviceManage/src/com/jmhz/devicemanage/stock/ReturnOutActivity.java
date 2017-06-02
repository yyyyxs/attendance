package com.jmhz.devicemanage.stock;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

public class ReturnOutActivity extends BaseActivity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_return_out);
		initializeMemberVariable(findViewById(R.id.mainHead));
		
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.return_out);
		rightButton.setVisibility(View.GONE);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
