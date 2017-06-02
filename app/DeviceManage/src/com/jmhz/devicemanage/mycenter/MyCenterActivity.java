package com.jmhz.devicemanage.mycenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jmhz.devicemanage.BaseActivity;
import com.jmhz.devicemanage.R;
import com.jmhz.devicemanage.callback.HttpCallback;
import com.jmhz.devicemanage.http.api.TransportNodeApi;
import com.jmhz.devicemanage.http.api.UpdateVersionApi;
import com.jmhz.devicemanage.http.download.NotifyDownload;
import com.jmhz.devicemanage.param.VersionsParam;
import com.uid.trace.common.schema.TransportNode;
import com.uid.trace.module.stock.schema.TransportNodeMessage;

public class MyCenterActivity extends BaseActivity {

	TransportNode tn = null;
	public static final int UPDATE_NODE_NAME = 1;
	public static final int UPDATE_CONTACT_PERSON = 2;
	public static final int UPDATE_ADDRESS = 3;
	public static final int UPDATE_PHONE = 4;
	public static final int UPDATE_EMAIL = 5;
	public TextView tv_et_site_name = null;
	public TextView tv_et_contacts_name = null;
	public TextView tv_et_site_address = null;
	public TextView tv_et_telnumber = null;
	public TextView tv_et_email = null;
	public TextView tv_et_change_psd = null;
	public TextView tv_et_check_version = null;
	public Button btn_submit = null;
	public Button btn_map = null;
	public String blank = "                            ";
	public String lon = "";
	public String lat = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycenter);
		initializeMemberVariable(findViewById(R.id.mainHead));
		initFileArea();
		try {
			initTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initSubmit();

	}

	private void initSubmit() {

		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_map = (Button) findViewById(R.id.btn_map);

		btn_submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					new TransportNodeApi(MyCenterActivity.this)
							.updateTransportNode(tn, new HttpCallback() {

								@Override
								public void onSuccess(Object o) {
									toastShort("更新成功！");
								}

								@Override
								public void onFail(String errorCode,
										String errorMsg) {
									toastShort("更新失败！" + errorMsg);
								}
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btn_map.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(MyCenterActivity.this,
						NodeMapActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("lon", lon);
				bundle.putString("lat", lat);
				intent.putExtras(bundle);
				startActivity(intent);
				// startActivityForResult(intent, BACK_POSITION);
			}
		});
	}

	private void initTable() throws Exception {

		tv_et_site_name = (TextView) findViewById(R.id.tv_et_site_name);
		tv_et_contacts_name = (TextView) findViewById(R.id.tv_et_contacts_name);
		tv_et_site_address = (TextView) findViewById(R.id.tv_et_site_address);
		tv_et_telnumber = (TextView) findViewById(R.id.tv_et_telnumber);
		tv_et_email = (TextView) findViewById(R.id.tv_et_email);
		tv_et_change_psd = (TextView) findViewById(R.id.tv_et_change_psd);
		tv_et_check_version = (TextView) findViewById(R.id.tv_et_check_version);

		TransportNode tn_request = new TransportNode();
		new TransportNodeApi(this).getNode(tn_request, new HttpCallback() {

			@Override
			public void onSuccess(Object o) {

				TransportNodeMessage tnm = (TransportNodeMessage) o;
				tn = tnm.getMessageBody();
				initTableItem(tv_et_site_name, tn.getNodeName() + "  ",
						SiteActivity.class);
				initTableItem(tv_et_contacts_name,
						tn.getContactPerson() + "  ", ContactsActivity.class);
				initTableItem(tv_et_site_address, tn.getAddress() + "  ",
						ProvinceSelectActivity.class);
				initTableItem(tv_et_telnumber, tn.getPhone() + "  ",
						TelnumberActivity.class);
				initTableItem(tv_et_email, tn.getEmail() + "  ",
						EmailActivity.class);
				initTableItem(tv_et_change_psd, blank, ChangePsdActivity.class);
				initTableItem(tv_et_check_version, "当前版本： " + getVersionName()
						+ "  ", MyCenterActivity.class);
				lon = tn.getLon();
				lat = tn.getLat();
			}

			@Override
			public void onFail(String errorCode, String errorMsg) {
				toastShort(errorMsg);
				initTableItem(tv_et_site_name, blank, SiteActivity.class);
				initTableItem(tv_et_contacts_name, blank,
						ContactsActivity.class);
				initTableItem(tv_et_site_address, blank,
						ProvinceSelectActivity.class);
				initTableItem(tv_et_telnumber, blank, TelnumberActivity.class);
				initTableItem(tv_et_email, blank, EmailActivity.class);
				initTableItem(tv_et_change_psd, blank, ChangePsdActivity.class);
				initTableItem(tv_et_check_version, "当前版本： " + getVersionName()
						+ "  ", MyCenterActivity.class);
			}
		});

	}

	private void initTableItem(TextView view, String value,
			final Class<?> gotoActivity) {

		view.setText(value);
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.tv_et_site_name:
					startActivityWithClassForResult(gotoActivity,
							UPDATE_NODE_NAME);
					break;
				case R.id.tv_et_contacts_name:
					startActivityWithClassForResult(gotoActivity,
							UPDATE_CONTACT_PERSON);
					break;
				case R.id.tv_et_site_address:
					// startActivityWithClassForResult(gotoActivity,
					// UPDATE_ADDRESS);
					break;
				case R.id.tv_et_telnumber:
					startActivityWithClassForResult(gotoActivity, UPDATE_PHONE);
					break;
				case R.id.tv_et_email:
					startActivityWithClassForResult(gotoActivity, UPDATE_EMAIL);
					break;
				case R.id.tv_et_change_psd:
					startActivityWithClass(gotoActivity);
					break;
				case R.id.tv_et_check_version:

					new UpdateVersionApi(MyCenterActivity.this)
							.getCurrentVersions(new HttpCallback() {

								@Override
								public void onSuccess(Object o) {

									final VersionsParam vParam = (VersionsParam) o;
									int versionCode = getVersionCode(); // 当前本地版本
									int currentCode = vParam.getVersionCode(); // 服务器发布版本
									if (versionCode >= currentCode) {
										Toast.makeText(MyCenterActivity.this,
												"当前版本是最新版本", Toast.LENGTH_SHORT)
												.show();
									} else {
										new AlertDialog.Builder(
												MyCenterActivity.this)
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
																		MyCenterActivity.this);
																down.startDownload(
																		MyCenterActivity.this,
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
									Toast.makeText(MyCenterActivity.this,
											errorMsg, Toast.LENGTH_SHORT)
											.show();
								}
							});
					break;
				}
			}
		});
	}

	/*
	 * 获取当前程序的版本号
	 */
	private int getVersionCode() {
		// 获取packagemanager的实例
		PackageManager packageManager = MyCenterActivity.this
				.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(
					MyCenterActivity.this.getPackageName(), 0);
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
		PackageManager packageManager = MyCenterActivity.this
				.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(
					MyCenterActivity.this.getPackageName(), 0);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String update_msg = bundle.getString("update_msg");
			switch (requestCode) {
			case UPDATE_NODE_NAME:
				tn.setNodeName(update_msg);
				tv_et_site_name.setText(update_msg + "  ");
				break;
			case UPDATE_CONTACT_PERSON:
				// 调用接口
				tn.setContactPerson(update_msg);
				tv_et_contacts_name.setText(update_msg + "  ");
				break;
			case UPDATE_ADDRESS:
				break;
			case UPDATE_PHONE:
				// 调用接口
				tn.setPhone(update_msg);
				tv_et_telnumber.setText(update_msg + "  ");
				break;
			case UPDATE_EMAIL:
				// 调用接口
				tn.setEmail(update_msg);
				tv_et_email.setText(update_msg + "  ");
				break;
			}
		}
	}

	@Override
	protected void initializeActivityHead() {
		// TODO Auto-generated method stub
		centreText.setText(R.string.personal_manager);
		rightButton.setText(R.string.operation_method);
		rightButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivityWithClass(OperationManualActivity.class);
			}
		});
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MyCenterActivity.this.finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MyCenterActivity.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
