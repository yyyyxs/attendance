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

public class ContactsActivity extends BaseActivity {

	protected Button btn_commit = null;
	protected EditText et_contacts = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();

	}

	private void initWidget() {

		btn_commit = (Button) findViewById(R.id.btn_contacts_submit);
		et_contacts = (EditText) findViewById(R.id.et_contacts);
		btn_commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent resultIntent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("update_msg", et_contacts.getText().toString());
				resultIntent.putExtras(bundle);
				setResult(RESULT_OK, resultIntent);
				ContactsActivity.this.finish();
			}
		});

	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.contacts);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ContactsActivity.this.finish();
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
			ContactsActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
