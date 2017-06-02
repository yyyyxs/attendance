package com.jmhz.devicemanage.mycenter;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends BaseActivity{
	
	protected Button btn_commit = null;
	protected EditText et_email = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
		
	}

	private void initWidget() {
		
		btn_commit = (Button) findViewById(R.id.btn_email_submit);
		et_email = (EditText) findViewById(R.id.et_email);
		btn_commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent resultIntent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("update_msg", et_email.getText().toString());
				resultIntent.putExtras(bundle);
				setResult(RESULT_OK, resultIntent);
				EmailActivity.this.finish();
			}
		});
		
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.email);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				EmailActivity.this.finish();
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
			 EmailActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}
}
