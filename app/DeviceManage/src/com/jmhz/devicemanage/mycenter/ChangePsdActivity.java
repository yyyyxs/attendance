package com.jmhz.devicemanage.mycenter;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.MemberApi;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;

public class ChangePsdActivity extends BaseActivity {

	private Button mBtnSubmit = null;
	private Dialog dialog = null;
	private EditText et_old_pw = null;
	private EditText et_new_pw = null;
	private EditText et_new_again_pw = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepsd);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();
	
	}

	private void initWidget() {
		
		mBtnSubmit = (Button) findViewById(R.id.btn_change_psd_submit);
		//et_old_pw = (EditText) findViewById(R.id.et_old_password);
		et_new_pw = (EditText) findViewById(R.id.et_new_password);
		et_new_again_pw = (EditText) findViewById(R.id.et_password_again);
		
		mBtnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(et_old_pw.getText().toString())) {
					toastShort("旧密码为空！");
					return;
				}
				if (TextUtils.isEmpty(et_new_pw.getText().toString())) {
					toastShort("新密码为空！");
					return;
				}
				if (et_old_pw.getText().toString().equals(et_new_pw.getText().toString())) {
					toastShort("旧密码和新密码相同！");
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
					updatePassWord(et_old_pw.getText().toString(), et_new_pw.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		});
	}

	protected void updatePassWord(String oldpw, String newpw) throws Exception {
		TransportNode tn = new TransportNode();
		tn.setPassword(oldpw);
		tn.setModifyPwd(newpw);
		new MemberApi(this).updateLoginPwd(tn, new HttpCallback() {
			
			@Override
			public void onSuccess(Object o) {
				//替换旧的token
				TransportNodeMessage tnm = (TransportNodeMessage) o;
				keepMemberSession(tnm.getMessageHeader().getCustomerToken());
				
				dialog = new MyDialog(ChangePsdActivity.this, R.style.MyDialog);
				dialog.show();
				dialog.getWindow().findViewById(R.id.tv_changepsd_ok)
						.setOnClickListener(new OnClickListener() {
		
							@Override
							public void onClick(View v) {
								dialog.dismiss();
								ChangePsdActivity.this.finish();
							}
						});
				
			}
			
			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort("修改失败！" + errorMsg);
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
				ChangePsdActivity.this.finish();
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
			 ChangePsdActivity.this.finish(); 
		 }
		return super.onKeyDown(keyCode, event);
	}
}
