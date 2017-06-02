package com.jmhz.devicemanage.mycenter;

import java.util.List;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.http.api.MemberApi;
import com.jmhz.devicemanage.model.MyDevicesListItem;
import com.jmhz.devicemanage.web.Device;
import com.jmhz.devicemanage.web.DeviceBack;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;

public class AccountManageActivity extends BaseActivity {

	public AccountManageActivity() {
		// TODO Auto-generated constructor stub
	}

	/*密码修改*/
	private Button mBtnSubmit = null;
	private Dialog dialog = null;
	private EditText et_new_pw = null;
	private EditText et_new_again_pw = null;
	private TextView person_userID = null;
	private TextView person_userName = null;
	private String userID="";
	private String userName="";
	private SharedPreferences sharedPreferences;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_manage);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
	}

	private void initWidget() {
		mBtnSubmit = (Button) findViewById(R.id.btn_change_psd_submit);
		person_userID=(TextView) findViewById(R.id.person_userID);
		person_userName=(TextView) findViewById(R.id.person_userName);
		et_new_pw = (EditText) findViewById(R.id.et_new_password);
		et_new_again_pw = (EditText) findViewById(R.id.et_password_again);

		
		sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
		//userID=sharedPreferences.getString("id","");
		userID=Long.toString(sharedPreferences.getLong("id",0));
		userName=sharedPreferences.getString("username", "");
		person_userID.setText(userID);
		person_userName.setText(userName);
		mBtnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//toastShort("dj");
				if (TextUtils.isEmpty(et_new_pw.getText().toString())) {
					toastShort("新密码为空！");
					return;
				}
				if (TextUtils.isEmpty(et_new_again_pw.getText().toString())) {
					toastShort("请再次输入密码！");
					return;
				}
				if(!et_new_again_pw.getText().toString().equals(et_new_pw.getText().toString())){
					toastShort("两次的密码不相同！");
					return;
				}
				try {
					//Log.i("dd",url+userId+password);
					Log.i("dd",userID+ et_new_pw.getText().toString());
					updatePassWord(userID, et_new_pw.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		});
	}

	protected void updatePassWord(String userID, String newpw) throws Exception {
		
		new GetDeviceInfoApi(this).updateUserPassWord(userID,newpw,
				new HttpCallback() {
					
					@Override
					public void onSuccess(Object o) {
						String result = (String) o;
						toastShort(result);
					}

					@Override
					public void onFail(String errorCode, String errorMsg) {
						// TODO Auto-generated method stub
						toastShort(errorMsg);
	
					}
				});

	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.change_password);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AccountManageActivity.this.finish();
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
			 AccountManageActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}
}
	

