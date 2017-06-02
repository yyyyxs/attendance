package com.jmhz.devicemanage.mycenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

public class TelnumberActivity extends BaseActivity{

	protected Button btn_commit = null;
	protected EditText et_telnumber = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_telnumber);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
		
	}

	private void initWidget() {
		btn_commit = (Button) findViewById(R.id.btn_telnumber_submit);
		et_telnumber = (EditText) findViewById(R.id.et_telnumber);
		btn_commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent resultIntent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("update_msg", et_telnumber.getText().toString());
				resultIntent.putExtras(bundle);
				setResult(RESULT_OK, resultIntent);
				TelnumberActivity.this.finish();
			}
		});
		
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.telnumber);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TelnumberActivity.this.finish();
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
			 TelnumberActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}
}
