package com.jmhz.devicemanage.login;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.lbsapi.auth.e;
import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.MemberApi;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;

public class ForgetPsdActivity extends BaseActivity {

	private EditText et_email;
	private EditText et_verification_code;
	private EditText et_password;
	private EditText verify_password;
	private Button btn_verify;
	private Button btn_forgetpsd_submit;
	private String jsessionid = null;
	Timer timer = new Timer();
	Handler handler = new Handler() {

		@SuppressLint("NewApi")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				btn_verify.setBackground(getBaseContext().getResources()
						.getDrawable(R.drawable.big_button_on_off));
				btn_verify.setEnabled(true);
				break;
			}
			super.handleMessage(msg);
		}

	};
	TimerTask task = new TimerTask() {

		public void run() {
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}

	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgetpsd);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initWidget();

	}

	private void initWidget() {

		et_email = (EditText) findViewById(R.id.et_email);
		et_email.setText("759032316@qq.com");
		et_verification_code = (EditText) findViewById(R.id.et_verification_code);
		et_password = (EditText) findViewById(R.id.et_password);
		verify_password = (EditText) findViewById(R.id.verify_password);
		btn_verify = (Button) findViewById(R.id.btn_verify);
		btn_forgetpsd_submit = (Button) findViewById(R.id.btn_forgetpsd_submit);

		btn_verify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				timer.cancel();
//				task.cancel();
//				timer.purge();
//				timer = null;
				// 获取验证码
				if (TextUtils.isEmpty(et_email.getText().toString())) {
					toastShort("请输入邮箱！");
					return;
				}
				TransportNode tNode = new TransportNode();
				tNode.setEmail(et_email.getText().toString());
				try {
					new MemberApi(ForgetPsdActivity.this)
							.getValidateCodeByEmail(tNode, new HttpCallback() {

								@SuppressLint("NewApi")
								@Override
								public void onSuccess(Object o) {

									TransportNodeMessage tnm = (TransportNodeMessage) o;
									toastShort("验证码获取成功,请查收邮件！ ");
									jsessionid = tnm.getMessageBody()
											.getJsessionId();
									btn_verify
											.setBackground(getBaseContext()
													.getResources()
													.getDrawable(
															R.drawable.big_button_view_style_on));
									btn_verify.setEnabled(false);
									timer = new Timer();
									timer.schedule(task, 60 * 1000);
								}

								@Override
								public void onFail(String errorCode,
										String errorMsg) {

									toastShort(errorMsg);

								}
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btn_forgetpsd_submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// 修改密码
				if (TextUtils.isEmpty(et_email.getText().toString())) {
					toastShort("请输入邮箱！");
					return;
				}
				if (TextUtils
						.isEmpty(et_verification_code.getText().toString())) {
					toastShort("请先获取验证码！");
					return;
				}
				if (TextUtils.isEmpty(et_password.getText().toString())) {
					toastShort("请输入密码！");
					return;
				}
				if (!et_password.getText().toString()
						.equals(verify_password.getText().toString())) {
					toastShort("两次密码不一致！");
					return;
				}
				TransportNode tNode = new TransportNode();
				tNode.setEmail(et_email.getText().toString());
				tNode.setModifyPwd(et_password.getText().toString());
				tNode.setValidateCode(et_verification_code.getText().toString());
				try {
					new MemberApi(ForgetPsdActivity.this).retrievePW(tNode,
							jsessionid, new HttpCallback() {

								@Override
								public void onSuccess(Object o) {

									TransportNodeMessage tnm = (TransportNodeMessage) o;
									keepMemberSession(tnm.getMessageHeader()
											.getCustomerToken());
									toastShort("修改成功！");
									setResult(RESULT_OK);
									finish();
								}

								@Override
								public void onFail(String errorCode,
										String errorMsg) {
									// TODO Auto-generated method stub
									toastShort(errorMsg);
								}
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.forget_psd);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ForgetPsdActivity.this.finish();
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
			ForgetPsdActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
