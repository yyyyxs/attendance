package com.jmhz.devicemanage.http;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;

import com.jmhz.devicemanage.callback.DownloadCallback;
import com.jmhz.devicemanage.callback.PostCallback;

public class HttpBase implements HttpUrl, HttpBaseUrl {

	protected static final int MSG_POST = Menu.FIRST;
	protected static final int MSG_POST_OK = Menu.FIRST + 1;
	protected static final int MSG_POST_ERR = Menu.FIRST + 2;
	protected static final int MSG_POST_NOT_LOGIN_ERR = Menu.FIRST + 3;

	protected static final int UPLOAD_FILE = Menu.FIRST + 4;
	protected static final int UPLOAD_FILE_OK = Menu.FIRST + 5;
	protected static final int UPLOAD_FILE_ERR = Menu.FIRST + 6;

	protected static final int DOWNLOAD_FILE = Menu.FIRST + 7;
	protected static final int DOWNLOAD_FILE_OK = Menu.FIRST + 8;
	protected static final int DOWNLOAD_FILE_ERR = Menu.FIRST + 9;

	protected static final int MSG_GET = Menu.FIRST + 10;
	protected static final int MSG_GET_OK = Menu.FIRST + 11;
	protected static final int MSG_GET_ERR = Menu.FIRST + 12;

	protected HttpClient client = new DefaultHttpClient();
	protected HttpGet get = null;
	protected HttpPost post = null;
	protected HttpResponse response = null;

	protected Activity mActivity;
	protected ProgressDialog dialog;
	protected boolean showDialog = true;

	protected String mDialogText = "请稍等...";

	protected static final String DEFAULT_ERROR_CODE = "-100011";
	protected static final String UNKNOWN_ERROR_CODE = "-100012";

	protected static final String NETWORK_ERROR = "无法连接到服务器!";
	protected static final String NOT_LOGIN_ERROR = "未登录或登录无效!";

	protected String mUrl;
	protected ArrayList<NameValuePair> mParams;

	protected PostCallback mPostCallback = null;
	protected DownloadCallback mDownloadCallback = null;

	private static final int TIMEOUT = 5000;

	public HttpBase(Activity mActivity) {
		this.mActivity = mActivity;
		HttpConnectionParams.setConnectionTimeout(client.getParams(), TIMEOUT);
	}

	public HttpBase setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
		return this;
	}

	public void setDialogText(String text) {
		this.mDialogText = text;
	}

	protected String result = null;

	public void get(String url, PostCallback callback) {
		this.mPostCallback = callback;
		this.mUrl = url;
		handler.obtainMessage(MSG_GET).sendToTarget();
	}

	public void post(String url, ArrayList<NameValuePair> params,
			PostCallback callback) {
		mUrl = url;
		mParams = params;
		// if (callback != null)
		mPostCallback = callback;
		handler.obtainMessage(MSG_POST).sendToTarget();
	}

	public void post(String url, Bundle bundle, PostCallback callback) {
		post(url, formatParams(bundle), callback);
	}

	public void post(String url, String xmlMsg, PostCallback callback) {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("msg", xmlMsg));
		post(url, params, callback);
	}

	public void post(String url, PostCallback callback) {
		post(url, new Bundle(), callback);
	}

	// public void uploadFile(FileItem item, PostCallback callback) {
	// mPostCallback = callback;
	// String url = item.getUrl();
	// try {
	// url += "?accessToken="
	// + URLEncoder.encode(item.getToken(), "UTF-8");
	// } catch (UnsupportedEncodingException e1) {
	// e1.printStackTrace();
	// }
	// url += "&imageType=" + item.getImageType();
	// item.setUrl(url);
	// Message msg = handler.obtainMessage(UPLOAD_FILE);
	// msg.obj = item;
	// handler.sendMessage(msg);
	// }
	//
	// private void doUploadFile(final FileItem item) {
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// org.apache.commons.httpclient.HttpClient httpClient = new
	// org.apache.commons.httpclient.HttpClient();
	// httpClient.setConnectionTimeout(TIMEOUT);
	// MultipartPostMethod post = new MultipartPostMethod(item
	// .getUrl());
	// try {
	// post.addParameter("file", item.getFile());
	// int code = httpClient.executeMethod(post);
	// if (code == 200) {
	// result = post.getResponseBodyAsString();
	// handler.obtainMessage(UPLOAD_FILE_OK).sendToTarget();
	// } else {
	// handler.obtainMessage(UPLOAD_FILE_ERR).sendToTarget();
	// }
	// post.releaseConnection();
	// } catch (Exception e) {
	// handler.obtainMessage(UPLOAD_FILE_ERR).sendToTarget();
	// }
	// }
	// }).start();
	//
	// }
	//
	// protected void downloadFile(FileItem item, DownloadCallback callback) {
	// mDownloadCallback = callback;
	// String url = item.getUrl();
	// try {
	// url += "?accessToken="
	// + URLEncoder.encode(item.getToken(), "UTF-8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// ;
	// url += "&imageType=" + item.getImageType();
	// url += "&nId=" + item.getNid();
	// if (item.getImageType().equals("PRODUCT"))
	// url += "&subId=" + item.getSubid();
	// if (!TextUtils.isEmpty(item.getIsThumb()))
	// url += "&isThumb=" + item.getIsThumb();
	//
	// System.out.println("url-->"+url);
	// // String url =
	// //
	// "http://58.22.108.220:8080/UnionPayWebApp/r/image/getImage?accessToken=ENC(XFfSp7YEat5MrBit29cPVnvMQdV2G8Wdc8UrRo/I46t1I8bHY/0AYjKie6LMHbctRXDhJ2ohC79iw0lsded1mQ==)&imageType=HEAD&nId=18259049604";
	// item.setUrl(url);
	//
	// Message msg = handler.obtainMessage(DOWNLOAD_FILE);
	// msg.obj = item;
	// handler.sendMessage(msg);
	// }
	//
	// private void doDownloadFile(final FileItem item) {
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// URL url = null;
	// try {
	// url = new URL(item.getUrl());
	// URLConnection urlConn = url.openConnection();
	// InputStream inputStream = urlConn.getInputStream();
	// Message msg = handler.obtainMessage(DOWNLOAD_FILE_OK);
	// msg.obj = BitmapFactory.decodeStream(inputStream);
	// if (msg.obj == null)
	// msg.what = DOWNLOAD_FILE_ERR;
	// handler.sendMessage(msg);
	//
	// } catch (Exception e) {
	// handler.obtainMessage(DOWNLOAD_FILE_ERR).sendToTarget();
	// }
	//
	// }
	// }).start();
	//
	// }

	public ArrayList<NameValuePair> formatParams(Bundle bundle) {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String key : bundle.keySet()) {
			params.add(new BasicNameValuePair(key, bundle.getString(key)));
		}
		return params;
	}

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_POST:
				if (showDialog)
					dialog = ProgressDialog.show(mActivity, null, mDialogText,
							true, true);
				doPost();
				break;
			case MSG_POST_OK:
				dissmissDlg();
				if (mPostCallback != null) {
					mPostCallback.onSuccess(result);
				}
				break;
			case MSG_POST_ERR:
				dissmissDlg();
				if (mPostCallback != null) {
					mPostCallback.onFail(NETWORK_ERROR);
				}
				break;
			case MSG_POST_NOT_LOGIN_ERR:
				dissmissDlg();
				if (mPostCallback != null) {
					mPostCallback.onFail(NOT_LOGIN_ERROR);
				}
				break;
			// case UPLOAD_FILE:
			// if (showDialog)
			// dialog = ProgressDialog.show(mActivity, null, mDialogText,
			// true, true);
			// FileItem item = (FileItem) msg.obj;
			// doUploadFile(item);
			// break;
			case UPLOAD_FILE_OK:
				dissmissDlg();
				if (mPostCallback != null) {
					mPostCallback.onSuccess(result);
				}
				break;
			case UPLOAD_FILE_ERR:
				dissmissDlg();
				if (mPostCallback != null) {
					mPostCallback.onFail(NETWORK_ERROR);
				}
				break;
			// case DOWNLOAD_FILE:
			// if (showDialog)
			// dialog = ProgressDialog.show(mActivity, null, mDialogText,
			// true, true);
			// item = (FileItem) msg.obj;
			// doDownloadFile(item);
			// break;
			case DOWNLOAD_FILE_OK:
				dissmissDlg();
				if (mDownloadCallback != null) {
					mDownloadCallback.onSuccess(msg.obj);
				}
				break;
			case DOWNLOAD_FILE_ERR:
				dissmissDlg();
				if (mDownloadCallback != null) {
					mDownloadCallback.onFail(NETWORK_ERROR);
				}
				break;
			case MSG_GET:
				if (showDialog)
					dialog = ProgressDialog.show(mActivity, null, mDialogText,
							true, true);
				doGet();
				break;
			case MSG_GET_OK:
				dissmissDlg();
				if (mPostCallback != null) {
					mPostCallback.onSuccess(result);
				}
				break;
			case MSG_GET_ERR:
				dissmissDlg();
				if (mPostCallback != null) {
					mPostCallback.onFail(NETWORK_ERROR);
				}
				break;
			}
		}
	};

	protected void doPost() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Log.i("url", mUrl);
//				Log.i("arg name", mParams.get(0).getName());
//				Log.i("arg value", mParams.get(0).getValue());
				post = new HttpPost(mUrl);
				try {
					post.setEntity(new UrlEncodedFormEntity(mParams, HTTP.UTF_8));
					response = client.execute(post);
					int code = response.getStatusLine().getStatusCode();
					if (code == 200) {
						result = EntityUtils.toString(response.getEntity());
						result = new String(result.getBytes("UTF_8"),
								HTTP.UTF_8);
						handler.obtainMessage(MSG_POST_OK).sendToTarget();
					} else if (code == 401) {
						handler.obtainMessage(MSG_POST_NOT_LOGIN_ERR)
								.sendToTarget();
					} else {
						handler.obtainMessage(MSG_POST_ERR).sendToTarget();
					}
				} catch (Exception e) {
					result = null;
					handler.obtainMessage(MSG_POST_ERR).sendToTarget();
				}
			}
		}).start();
	}

	protected void doGet() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				get = new HttpGet(mUrl);
				try {
					response = client.execute(get);
					if (response.getStatusLine().getStatusCode() == 200) {
						result = EntityUtils.toString(response.getEntity());
						handler.obtainMessage(MSG_GET_OK).sendToTarget();
					} else {
						handler.obtainMessage(MSG_GET_ERR).sendToTarget();
					}
				} catch (Exception e) {
					result = null;
					handler.obtainMessage(MSG_GET_ERR).sendToTarget();
				}
			}
		}).start();
	}

	protected void dissmissDlg() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

}
