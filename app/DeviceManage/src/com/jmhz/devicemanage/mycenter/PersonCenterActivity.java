package com.jmhz.devicemanage.mycenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.UpdateVersionApi;
import com.jmhz.devicemanage.http.download.NotifyDownload;
import com.jmhz.devicemanage.param.VersionsParam;

/*个人中心
 * 
 * @SuppressWarnings("deprecation")
public class PersonCenterActivity extends TabActivity implements
		OnFlingListener {
	
	protected Button leftButton;
	protected TextView centreText;
	protected Button rightButton;
	protected TabHost mTabHost;
	private GestureDetector detector = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order_list);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initializeActivityHead();
		
		mTabHost = getTabHost();
		createTab("个人信息查看", new Intent(this, PersonMesgLookActivity.class));
		Intent newPCIntent2 = new Intent(this,AccountManageActivity.class);
		createTab("账号管理",newPCIntent2);
		mTabHost.setCurrentTab(0);
		detector = new GestureDetector(this, new GestureListener(this));
	}
	
	private void createTab(String text, Intent intent) {

		mTabHost.addTab(mTabHost.newTabSpec(text)
				.setIndicator(createTabView(text)).setContent(intent));

	}
	
	private View createTabView(String text) {
		View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator,
				null);
		((TextView) view.findViewById(R.id.tv_tab)).setText(text);

		return view;
	}
	
	private void initializeMemberVariable(View headRL) {
		leftButton = (Button) headRL.findViewById(R.id.leftButton);
		centreText = (TextView) headRL.findViewById(R.id.centreText);
		rightButton = (Button) headRL.findViewById(R.id.rightButton);

	}
	
	private void initializeActivityHead() {
		centreText.setText(R.string.person_mesg_look);
		centreText.setTextSize(22);
		rightButton.setText("");
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				PersonCenterActivity.this.finish();
			}
		});
	}

		@Override
		public void onFlingLeft() {
			mTabHost.setCurrentTab(mTabHost.getCurrentTab() + 1);
		}

		@Override
		public void onFlingRight() {
			mTabHost.setCurrentTab(mTabHost.getCurrentTab() - 1);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			return detector.onTouchEvent(event);
		}

		@Override
		public boolean dispatchTouchEvent(MotionEvent ev) {
			// TODO Auto-generated method stub
			super.dispatchTouchEvent(ev);
			return detector.onTouchEvent(ev);
		}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				PersonCenterActivity.this.finish();
			}
			return super.onKeyDown(keyCode, event);
		}

}

 * */

public class PersonCenterActivity extends BaseActivity{
		
	RelativeLayout rl_per_info_look;
    RelativeLayout rl_account_manage;
    RelativeLayout rl_version_check;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_center);
		initializeMemberVariable(findViewById(R.id.mainHead));
		rl_per_info_look=(RelativeLayout)findViewById(R.id.rl_per_info_look);
		rl_account_manage=(RelativeLayout)findViewById(R.id.rl_account_manage);
		rl_version_check = (RelativeLayout) findViewById(R.id.rl_version_check);
		rl_per_info_look.setOnClickListener(new RelayoutClickListener());
		rl_account_manage.setOnClickListener(new RelayoutClickListener());
		rl_version_check.setOnClickListener(new RelayoutClickListener());
	}
	class RelayoutClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it=new Intent();
			switch (v.getId())
			{
			
			case R.id.rl_per_info_look:
				it.setClass(PersonCenterActivity.this,PersonMesgLookActivity.class);
				startActivity(it);
				break;
			case R.id.rl_account_manage:
				it.setClass(PersonCenterActivity.this,AccountManageActivity.class);
				startActivity(it);
				break;
			case R.id.rl_version_check:
				Toast.makeText(PersonCenterActivity.this,
						"敬请期待！", Toast.LENGTH_SHORT)
						.show();
			/*	new UpdateVersionApi(PersonCenterActivity.this)
						.getCurrentVersions(new HttpCallback() {

							@Override
							public void onSuccess(Object o) {

								final VersionsParam vParam = (VersionsParam) o;
								int versionCode = getVersionCode(); // 当前本地版本
								int currentCode = vParam.getVersionCode(); // 服务器发布版本
								if (versionCode >= currentCode) {
									Toast.makeText(PersonCenterActivity.this,
											"当前版本是最新版本", Toast.LENGTH_SHORT)
											.show();
								} else {
									new AlertDialog.Builder(
											PersonCenterActivity.this)
											.setTitle("新版本")
											.setMessage(
													"当前最新版本为"
															+ vParam.getVersionName()
															+ "\n建议更新")
											.setPositiveButton("确定",
													new OnClickListener() {
														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															NotifyDownload down = new NotifyDownload(
																	PersonCenterActivity.this);
															down.startDownload(
																	PersonCenterActivity.this,
																	vParam.getDownloadUrl());
														}
													})
											.setNegativeButton("取消", null)
											.create().show();
								}
							}

							@Override
							public void onFail(String errorCode,
									String errorMsg) {
								// TODO Auto-generated method stub
								Toast.makeText(PersonCenterActivity.this,
										errorMsg, Toast.LENGTH_SHORT)
										.show();
							}
						});*/
				break;
			}
		}
   }	
		
		/*
		 * 获取当前程序的版本号
		 */
		private int getVersionCode() {
			// 获取packagemanager的实例
			PackageManager packageManager = PersonCenterActivity.this
					.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo;
			try {
				packInfo = packageManager.getPackageInfo(
						PersonCenterActivity.this.getPackageName(), 0);
				return packInfo.versionCode;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		/*
		 * 获取当前程序的版本名
		 */
		private String getVersionName() {
			// 获取packagemanager的实例
			PackageManager packageManager = PersonCenterActivity.this
					.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo;
			try {
				packInfo = packageManager.getPackageInfo(
						PersonCenterActivity.this.getPackageName(), 0);
				return packInfo.versionName;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}

		
		
	protected void initializeActivityHead() {
		centreText.setText("个人中心");
		rightButton.setVisibility(View.GONE);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				PersonCenterActivity.this.finish();
			}
		});
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			PersonCenterActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
