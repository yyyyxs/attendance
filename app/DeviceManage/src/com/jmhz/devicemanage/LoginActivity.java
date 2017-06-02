package com.jmhz.devicemanage;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.GetDeviceInfoApi;
import com.jmhz.devicemanage.http.api.MemberApi;
import com.jmhz.devicemanage.login.ForgetPsdActivity;
import com.jmhz.devicemanage.push.PushBroadcastReceiver;
import com.jmhz.devicemanage.push.PushService;
import com.jmhz.devicemanage.session.SessionKeeper;
import com.jmhz.devicemanage.utils.ServiceUtils;
import com.jmhz.devicemanage.web.DeviceBack;
import com.jmhz.devicemanage.web.User;
import com.jmhz.devicemanage.web.UserBack;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;

public class LoginActivity extends BaseActivity {

	private EditText mUsername = null;
	private EditText mPassword = null;
	private Button submit = null;
	private TextView forget_pwd = null;
	private CheckBox ibtn_selete = null;
//	private RadioButton rb = null;
//	private RadioGroup rg = null;
//	private RadioButton rb1 = null;
//	private RadioButton rb2 = null;
	private TextView tv = null;
	private CheckBox auto_login = null;
	private TextView tv2 = null;
	private SharedPreferences sp;
	private int id;
	private String name;
	private String password;

	int userrole = 1;
	private final String url = "http://218.193.126.55:8080/mobileuser/login";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		initializeMemberVariable(findViewById(R.id.mainHead));
//		auto_login=(CheckBox) findViewById(R.id.checkBox1);
		mUsername = (EditText) findViewById(R.id.et_username);
		mPassword = (EditText) findViewById(R.id.et_password);
		auto_login=(CheckBox) findViewById(R.id.auto_login);
		 SharedPreferences pref=getSharedPreferences("data0", MODE_PRIVATE);
	      String name=pref.getString("name", "");
	      String password=pref.getString("password", "");
	      
	      mUsername.setText(name);
	      mPassword.setText(password);
	      if(auto_login.isChecked()){
	    	  try {
				doLogin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	   
		initWidget();
		
     
      
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			finish();
		}

	}

	private void initWidget() {
		ibtn_selete = (CheckBox) findViewById(R.id.ibtn_selete);
		forget_pwd = (TextView) findViewById(R.id.tv_forget_psd);
		
//  ibtn_selete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//	
//	@Override
//	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//		// TODO Auto-generated method stub
//		 if(isChecked){ 
//			 String name = mUsername.getText().toString();
//				String password = mPassword.getText().toString();
//				
////			 SharedPreferences.Editor editor=getSharedPreferences("data0", MODE_PRIVATE).edit();
////				editor.putString("name", name);
////				editor.putString("password", password);
////				editor.commit();
//				
////            SharedPreferences pref=getSharedPreferences("data0", MODE_PRIVATE);
////            String name=pref.getString("name", "");
////            String password=pref.getString("password", "");
////            mUsername.setText(name);
////            mPassword.setText(password);
//         }else{
//        	 String name = mUsername.getText().toString();
//				String password = mPassword.getText().toString();
//				
//			 SharedPreferences.Editor editor=getSharedPreferences("data0", MODE_PRIVATE).edit();
//				editor.putString("name", name);
//				editor.putString("password", "");
//				editor.commit();
//         }
//	}
//});
		forget_pwd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivityWithClassForResult(ForgetPsdActivity.class, 1);
			}
		});
		submit = (Button) findViewById(R.id.btn_login);
//		rg = (RadioGroup) findViewById(R.id.radioGroup1);
//		tv = (TextView) findViewById(R.id.textView1);
//		tv.setText("您的身份是一般用户");
//		rb1 = (RadioButton) findViewById(R.id.radio0);
//		rb2 = (RadioButton) findViewById(R.id.radio1);
//		rb3 = (RadioButton) findViewById(R.id.radio2);

//		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(RadioGroup arg0, int arg1) {
//				id = arg0.getCheckedRadioButtonId();
//				rb = (RadioButton) findViewById(id);
//				
//				if(id==R.id.radio00){
//					userrole=1;
//				} else {
//					userrole=0;
//				}
//				tv.setText("您的身份是"+rb.getText() + userrole);
//			}
//		});
//		if(id==R.id.radio0){
//		submit.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				try {
//					doLogin();
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});}
//		else if(id==R.id.radio1){
//			submit.setOnClickListener(new View.OnClickListener() {
//
//				@Override
//				public void onClick(View arg0) {
//					try {
//						doLogin1();
//						
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}j
//			});
//		}
				submit.setOnClickListener(new View.OnClickListener() {
					
								@Override
								public void onClick(View arg0) {
									try {
//										if(id==R.id.radio00)
											doLogin();
//										else if(id==R.id.radio11)
//											doLogin1();
											
											
											
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							
							});
				
		
		
		if (!TextUtils.isEmpty(getEmailSession())) {
			mUsername.setText(getEmailSession());
		}
		if (!TextUtils.isEmpty(getPasswordSession())) {
			mPassword.setText(getPasswordSession());
		}
	}

	protected void doLogin() throws Exception {
		String name = mUsername.getText().toString();
		String password = mPassword.getText().toString();
		
		if (TextUtils.isEmpty(name)) {
			toastShort("用户名不能为空");
			mUsername.requestFocus();
			return;
		}
		if (TextUtils.isEmpty(password)) {
			toastShort("密码不能为空");
			mPassword.requestFocus();
			return;
		}

		System.out.println(name+"!!!!!"+password);
		Log.i("dingh", name+"!!!!!"+password);
		new GetDeviceInfoApi(this).getLogDeviceInfo(name,password,
				new HttpCallback() {

			@Override
			public void onSuccess(Object o) {
				Log.i("dingh", "chengg!!!!!");
				UserBack userBack=(UserBack)o;
				User user=userBack.getUser();
				keepMemberSession("success");
				
				SharedPreferences.Editor editor=getSharedPreferences("data", MODE_PRIVATE).edit();
//<<<<<<< HEAD
//				
//				 String name = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				String name = mUsername.getText().toString();
//=======
//>>>>>>> branch 'master' of https://git.oschina.net/codingWw/DeviceManage.git
				editor.putString("name", name);
				editor.putString("password", password);
				editor.putLong("id", user.getId());
				editor.putBoolean("locked", user.getLocked());
				editor.putString("roleIds", user.getRoleIds());
				editor.putString("salt", user.getSalt());
				editor.putString("city", user.getCity());
				editor.putString("town", user.getTown());
				editor.putInt("auth_level", user.getAuth_level());
				editor.putString("tel", user.getTel());
				editor.putString("username", user.getUsername());
				editor.putString("studentName", user.getStudentName());
				editor.putString("teacherName", user.getTeacherName());
				editor.putString("grade", user.getGrade());
				editor.putInt("teacherId", user.getTeacherId());
				editor.commit();
				if(ibtn_selete.isChecked()){
					SharedPreferences.Editor loginEditor=getSharedPreferences("data0", MODE_PRIVATE).edit();
					loginEditor.putString("name", name);
					loginEditor.putString("password", password);
					loginEditor.commit();
				}else{
					SharedPreferences.Editor loginEditor=getSharedPreferences("data0", MODE_PRIVATE).edit();
					loginEditor.putString("name", name);
					loginEditor.putString("password", "");
					loginEditor.commit();
				}
				
				
				toastShort("登录成功");
				Log.i("login", "一般用户");
				
				if ( user.getRoleIds().equals("3")||user.getRoleIds().equals("4")) {
					//跳转
					Intent intent =new Intent(LoginActivity.this,MainActivity1.class);
					startActivity(intent);
					LoginActivity.this.finish();
				}else if (user.getRoleIds().equals("1")||user.getRoleIds().equals("2")) {
					Intent intent =new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					LoginActivity.this.finish();
				}
				
				

				// 是推送线程不被关闭
				IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
				PushBroadcastReceiver pushReceiver = new PushBroadcastReceiver(
						mUsername.getText().toString());
				registerReceiver(pushReceiver, filter);

				// 开启推送Service
				if (!ServiceUtils.isServiceRunning(getBaseContext(),
						PushService.PUSH_SERVICE_NAME)) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
							Log.i("LoginActivity",
									"start push service...userName:"
											+ mUsername.getText().toString());

							i.putExtra("userName", mUsername.getText()
									.toString());
							startService(i);
						}
					}).start();

				} else {
					Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
					stopService(i);

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					new Thread(new Runnable() {
						@Override
						public void run() {
							Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
							Log.i("LoginActivity",
									"start push service...userName:"
											+ mUsername.getText().toString());
							i.putExtra("userName", mUsername.getText()
									.toString());
							startService(i);
						}
					}).start();
				}
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				System.out.println("失败!!!!!");
				Log.i("dingh", "失败!!!!!");
				toastShort(errorMsg);
			}
		});
	
	}
//	protected void doLogin1() throws Exception {
//		String name = mUsername.getText().toString();
//		String password = mPassword.getText().toString();
//		if (TextUtils.isEmpty(name)) {
//			toastShort("用户名不能为空");
//			mUsername.requestFocus();
//			return;
//		}
//		if (TextUtils.isEmpty(password)) {
//			toastShort("密码不能为空");
//			mPassword.requestFocus();
//			return;
//		}
//
//		
//		
//
//		TransportNode transportNode = new TransportNode();
//		transportNode.setUserName(name);
//		transportNode.setPassword(password);
//		new MemberApi(this).login(transportNode, new HttpCallback() {
//
//			@Override
//			public void onSuccess(Object o) {
//				final TransportNodeMessage tnm = (TransportNodeMessage) o;
//				keepMemberSession(tnm.getMessageHeader().getCustomerToken());
//				keepUserNameSession(mUsername.getText().toString());
//				toastShort("登录成功");
//				
//				
//				Intent intent =new Intent(LoginActivity.this,MainActivity1.class);
//				startActivity(intent);
//				LoginActivity.this.finish();
//				
//
//				// 是推送线程不被关闭
//				IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
//				PushBroadcastReceiver pushReceiver = new PushBroadcastReceiver(
//						mUsername.getText().toString());
//				registerReceiver(pushReceiver, filter);
//
//				// 开启推送Service
//				if (!ServiceUtils.isServiceRunning(getBaseContext(),
//						PushService.PUSH_SERVICE_NAME)) {
//					new Thread(new Runnable() {
//						@Override
//						public void run() {
//							Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
//							Log.i("LoginActivity",
//									"start push service...userName:"
//											+ mUsername.getText().toString());
//
//							i.putExtra("userName", mUsername.getText()
//									.toString());
//							startService(i);
//						}
//					}).start();
//
//				} else {
//					Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
//					stopService(i);
//
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//					new Thread(new Runnable() {
//						@Override
//						public void run() {
//							Intent i = new Intent(PushService.PUSH_SERVICE_NAME);
//							Log.i("LoginActivity",
//									"start push service...userName:"
//											+ mUsername.getText().toString());
//							i.putExtra("userName", mUsername.getText()
//									.toString());
//							startService(i);
//						}
//					}).start();
//				}
//			}
//
//			@Override
//			public void onFail(String errorCode, String errorMsg) {
//				toastShort(errorMsg);
//			}
//		});
//	
//	}

	@Override
	protected void initializeActivityHead() {
		centreText.setText(R.string.app_name);
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// startActivityWithClass(MainActivity.class);
				LoginActivity.this.finish();
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
			LoginActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
