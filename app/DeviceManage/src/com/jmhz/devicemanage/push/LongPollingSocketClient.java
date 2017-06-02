
package com.jmhz.devicemanage.push;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.jmhz.devicemanage.http.HttpClientHelper;
import com.jmhz.devicemanage.http.HttpUrl;
import com.jmhz.devicemanage.http.HttpClientHelper.WebSocketSSLSocketFactory;
import com.jmhz.devicemanage.utils.JSONUtils;
import com.uid.trace.common.schema.MessageHeader;
import com.uid.trace.module.push.schema.ConfirmMsg;
import com.uid.trace.module.push.schema.RFPubMsg;
import com.uid.trace.module.push.schema.UidPushConfirmMessage;
import com.uid.trace.module.push.schema.UidPushConstants;
import com.uid.trace.module.sale.schema.OrderMessageParameter;
import com.uid.trace.module.support.android.CommonUtils;
import com.uid.trace.module.support.android.DESUtil;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;

/**
 * http长连接客户端socket类
 * @author Administrator
 *
 */

public class LongPollingSocketClient {

	private static final String TAG = "LongPollingSocketClient";

	private String clientID;
	private Listener mListener;
	private Socket mSocket;
	private Thread mThread;
	private HandlerThread mHandlerThread;
	private List<BasicNameValuePair> mExtraHeaders;
	private LongPollingSocketPacketParser mParser;
	private PushHandler mHandler;
	private static HashMap<String, RFPubMsg> multiPartMsgs = new HashMap<String, RFPubMsg>();
	private final Object mSendLock = new Object();
	protected static TrustManager[] sTrustManagers;
	public boolean isLogout = false;
	public boolean isRefreshConnection = false;
	public boolean isFirstConnect = false;
	public boolean isPingSuccess = true;	
	protected int curRetryCount = 0;

	public static void setTrustManagers(TrustManager[] tm) {
		sTrustManagers = tm;
	}
	
	private URI getURI()
	{
		URI uri;
		if (curRetryCount % 2 == 0)
		{
			uri = URI.create(HttpUrl.PUSH_URL);
		} else {
			uri = URI.create(HttpUrl.PUSH_URL.replace(HttpUrl.SERVER_URL, 
					HttpUrl.STANDBY_SERVER_URL));
		}
		return uri;
	}
	
	private void addRetryCount()
	{
		curRetryCount++;
	}

	public LongPollingSocketClient(Handler handler, final String clientid) {
		isLogout = false;
		isFirstConnect = true;
		Listener ls = new Listener() {
			@Override
			public void onConnect() {
				Log.d(TAG, "Connected!");
			}

			@Override
			public void onMessage(String message) {
				Log.d(TAG, String.format("Got string message! %s", message));
				readMsg(message);
			}

			@Override
			public void onMessage(byte[] data) {
				Log.d(TAG, String.format("Got binary message! %s", new String(data)));
			}

			@Override
			public void onDisconnect(int code, String reason){
				try{
					if(!isRefreshConnection){
						addRetryCount();
						refreshConnection();
					}
				}catch(Exception ex){
					Log.d(TAG, "Failed to reconnect..."+ex.getMessage());
				}
			}

			@Override
			public void onError(Exception error) {
				if (isLogout)
					return;
				Log.e(TAG, "Error!", error);
			}
		};
		mListener = ls;
		clientID = clientid;
		mExtraHeaders = Arrays.asList(
				new BasicNameValuePair("from", clientID), 
				new BasicNameValuePair("content-type","application/json;charset=GBK"), 
				new BasicNameValuePair("Connection", "Keep-Alive"));
		mParser = new LongPollingSocketPacketParser(this);
		mHandlerThread = new HandlerThread("websocket-thread");
		mHandlerThread.start();
		mHandler = (PushHandler) handler;
	}

	public Listener getListener() {
		return mListener;
	}

	public void connect() {
		if (mThread != null && mThread.isAlive()) {
			return;
		}
		if (mSocket != null && !mSocket.isClosed()) {
			try {
				mSocket.close();
				mSocket = null;
			} catch (Exception ex) {
				Log.d(TAG, "Error close existing socket" + ex.getMessage(), ex);
			}
		}
		mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int port = (getURI().getPort() != -1) ? getURI().getPort() : (getURI().getScheme().equals("https") ? 443 : 80);
					String path = TextUtils.isEmpty(getURI().getPath()) ? "/" : getURI().getPath();
					if (!TextUtils.isEmpty(getURI().getQuery())) {
						path += "?" + getURI().getQuery();
					}
					String originScheme = getURI().getScheme().equals("https") ? "https" : "http";
					URI origin = new URI(originScheme, "//" + getURI().getHost(), null);
					if (originScheme.equalsIgnoreCase("https")) {
						org.apache.http.conn.scheme.SocketFactory factory = WebSocketSSLSocketFactory
								  .getDefault(mHandler.getmContext());
						mSocket = factory.createSocket();
					} else {
						javax.net.SocketFactory factory = javax.net.SocketFactory.getDefault();
						mSocket = factory.createSocket();
					}

					SocketAddress socketAddress = new InetSocketAddress(getURI().getHost(), port);
					mSocket.connect(socketAddress, 15000);
					// create websocket connection with server
					PrintWriter out = new PrintWriter(mSocket.getOutputStream());
					out.print("GET " + path + " HTTP/1.1\r\n");
					Log.i("socket req", "GET " + path + " HTTP/1.1\r\n");
					out.print("Origin: " + origin.toString() + ":" + port + "\r\n");
					Log.i("socket req", "Origin: " + origin.toString() + ":" + port + "\r\n");
					out.print("Host: " + getURI().getHost() + ":" + port + "\r\n");
					Log.i("socket req", "Host: " + getURI().getHost() + ":" + port + "\r\n");
					if (mExtraHeaders != null) {
						for (NameValuePair pair : mExtraHeaders) {
							out.print(String.format("%s: %s\r\n", pair.getName(), pair.getValue()));
							Log.i("socket req", String.format("%s: %s\r\n", pair.getName(), pair.getValue()));
						}
					}
					out.print("\r\n");
					out.flush();
					LongPollingSocketPacketParser.HappyDataInputStream stream = new LongPollingSocketPacketParser
							.HappyDataInputStream(mSocket.getInputStream());
					
					mListener.onConnect();
					mParser.start(stream);
				} catch (EOFException ex) {
					Log.e(TAG, "Disconnected..Most likely the connection is being recreate.."+ex.getMessage());
					if(isFirstConnect && !isLogout){
						isFirstConnect = false;
						refreshConnection();
					}
				} catch (SSLException ex) {
					// Connection reset by peer
					Log.e(TAG, "Websocket SSL error!"+ex.getMessage());
					if(isFirstConnect && !isLogout){
						isFirstConnect = false;
						refreshConnection();
					}
				} catch (SocketException ex) {
					Log.i(TAG, "Disconnected..Most likely the connection is being recreate.."+ex.getMessage());
					if(isFirstConnect && !isLogout){
						isFirstConnect = false;
						refreshConnection();
					}
				} catch (IOException ex) {
					Log.e(TAG, "Disconnected..Most likely the connection is being recreate.."+ex.getMessage());
					if(isFirstConnect && !isLogout){
						isFirstConnect = false;
						refreshConnection();
					}
				} catch (Exception ex) {
					Log.e(TAG, "Unkown Exception " + ex.getMessage());
					if(isFirstConnect && !isLogout){
						isFirstConnect = false;
						refreshConnection();
					}
				}
			}
		});
		mThread.start();
	}

	/**
	 * Disconnect the connection, only called by logout.
	 */
	public void disconnect() {
		Log.i(TAG, "disconnect");
		if (mSocket != null) {
			try {
				if (mThread != null && mThread.isAlive()) {
					mThread.interrupt();
					mThread = null;
				}
				mSocket.close();
				mSocket = null;
			} catch (IOException ex) {
				Log.d(TAG, "Error while disconnecting", ex);
				mListener.onError(ex);
			}
		}
	}
	
	/**
	 * Refresh connection every xx mins
	 */
	public void refreshConnection(final boolean isForceToRun) {
		// Do something that takes a while
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
//				if(!isForceToRun){
//					try{
//						isPingSuccess = false;
//						pingMsg();
//						Thread.sleep(10000);
//					}catch(Exception ex){
//						Log.e(TAG, "Ping server failed"+ex.getMessage());
//					}
//				}else{
//					isFirstConnect = true;
//					Log.i(TAG, "Network switched..reconnect");
//				}
//				if(!isPingSuccess || isForceToRun){
					Log.i(TAG, "Refresh Connection");
					isRefreshConnection = true;
					if (mThread != null && mThread.isAlive()) {
						mThread.interrupt();
						mThread = null;
					}
					if (mSocket != null) {
						try {
							if (!mSocket.isClosed()) {
								mSocket.close();
								mSocket = null;
							}
							connect();
						} catch (IOException ex) {
							Log.d(TAG, "Error while disconnecting", ex);
							mListener.onError(ex);
						}
					} else {
						// connection no longer exists, reconnect....
						connect();
					}
//				}else{
//					Log.d(TAG, "Connection still exists, no need to reconnect***.");
//				}
			}
		};
		new Thread(runnable).start();
	}


	/**
	 * Refresh connection every xx mins
	 */
	public void refreshConnection() {
		refreshConnection(false);
	}

	/**
	 * Logout action
	 */
	public void logout() {
		Log.i(TAG, "logout");
		isLogout = true;
		disconnect();
	}

	public void send(String data) {
		try {
			synchronized (mSendLock) {
				ArrayList<NameValuePair> mParams = new ArrayList<NameValuePair>();
				mParams.add(new BasicNameValuePair("msg", data));
				HttpPost post = new HttpPost(getURI());
				String originScheme = getURI().getScheme().equals("https") ? "https" : "http";
				if (originScheme.equalsIgnoreCase("https")) {
					HttpClient sslHttpClient = HttpClientHelper.getSSLHttpClient(mHandler.getmContext(),false);
					post.setEntity(new UrlEncodedFormEntity(mParams, HTTP.UTF_8));
					sslHttpClient.execute(post);
				}else{
					HttpClient httpClient = new DefaultHttpClient();
					post.setEntity(new UrlEncodedFormEntity(mParams, HTTP.UTF_8));
					httpClient.execute(post);
				}
				
			}
		} catch (Exception e) {
			mListener.onError(e);
		}
	}

	public void send(byte[] data) {
		sendFrame(mParser.frame(data));
	}

	void sendFrame(final byte[] frame) {
		try {
			synchronized (mSendLock) {
				ArrayList<NameValuePair> mParams = new ArrayList<NameValuePair>();
				mParams.add(new BasicNameValuePair("msg", new String(frame)));
				HttpPost post = new HttpPost(getURI());
				HttpClient sslHttpClient = HttpClientHelper.getSSLHttpClient(mHandler.getmContext(),false);
				post.setEntity(new UrlEncodedFormEntity(mParams, HTTP.UTF_8));
				HttpResponse response = sslHttpClient.execute(post);
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) {
					Log.i(TAG, "  Reply to confirm success!!");
				}else{
					Log.i(TAG, "sendFrame Failure to identify reply!!");
					Log.i(TAG, "response code:"+code);
					Log.i(TAG, EntityUtils.toString(response.getEntity(),"UTF-8"));
				}
			}
		} catch (Exception e) {
			mListener.onError(e);
		}
	}

	public interface Listener {
		public void onConnect();

		public void onMessage(String message);

		public void onMessage(byte[] data);

		public void onDisconnect(int code, String reason);

		public void onError(Exception error);
	}

	// read message from the server push.
	// Will concatenate the multiple messages into one if the message size
	// is large than 5k.
	@SuppressWarnings("incomplete-switch")
	protected void readMsg(String inMsg) {		
	    Log.i("inMsg", inMsg);
		// Read the response
		if (TextUtils.isEmpty(inMsg))
			return;
		try {
			if(inMsg.startsWith("{PING="+clientID+"}")){
				isPingSuccess = true;
				return;
			}
//			String testInmsg = "{\"success\":\"true\",\"currentSize\":\"0\",\"id\":\"f5d2bc4c-4443-4ef3-b409-b4e96ace0051\",\"messageBytes\":\"PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48VW5pb25QYXlNZXNzYWdlPjxNZXNzYWdlSGVhZGVyPjxNZXNzYWdlU291cmNlSUQ+VU5JT05QQVlTRVJWRVI8L01lc3NhZ2VTb3VyY2VJRD48TWVzc2FnZURlc3RpbmF0aW9uPjxEZXN0aW5hdGlvbj48RGVzdGluYXRpb25JRD5VTklPTlBBWU1PQklMRTwvRGVzdGluYXRpb25JRD48Q3VzdG9tZXJJRD44MTYzNTAxODM5ODAwMjI8L0N1c3RvbWVySUQ+PC9EZXN0aW5hdGlvbj48L01lc3NhZ2VEZXN0aW5hdGlvbj48TWVzc2FnZVR5cGU+T1JERVJNU0c8L01lc3NhZ2VUeXBlPjxEYXRlU2VudD4yMDE0MDkwMjwvRGF0ZVNlbnQ+PFRpbWVTZW50PjE0MjcyNTwvVGltZVNlbnQ+PE1lc3NhZ2VJRD5BMTZUMFVaSU80PC9NZXNzYWdlSUQ+PE1lc3NhZ2VLZXk+c3lhZSswSm0rRTYyRnExclRtQ3RrKy9PVEVOQmVOcXE8L01lc3NhZ2VLZXk+PC9NZXNzYWdlSGVhZGVyPjxNZXNzYWdlQm9keT5FTkMoZTBVWDlOM25Yb2FQUkdXZFMra0F4QTBmSkd6TUJzZHQ0NVhIS1p5RkVpOFJvdmxZL1VyQXcrV0dxM0R2c2ZRNHloczREMDZWK2w4Rgo4TTdXb0hPbEZGUjlwV3pNb0FVNFI5Q0pLMGNNWHhjdGVXMmdJK0tXMjlCdmRmK0NIZitNMlRyNzVyajl0aVJrR3REWW43NHdpaTJHCnRmOU1aYllYNDVEaS9zU2tvQ3Jxa3hjN3ZMR1ZHcGl4N0RwQ0t0aHliRnIyQ2JDQ3dNVi9BM3F3elZ2aXFnRnVPbktxbFR4V3ZGU1kKR0hTZ2J6aFIwS3hwaUYwdnJRekszYUt0Q2ZTeHExcjBGUEt2L2tMb2JteWFlS2p2eDB2cVNDcENFRi9nWlF4NTZEbHBhWklZb0pnQgo2M0hENkx4Vzhwa1JPOGtnb3ZBYlZ0VER6Tlp2VTNjbWY5K3lEU3ZtazRzOU1IUU9JeEFOUlBIelNaUjZIOHJyanNMQlFScVlPUXZZCnpJYkcpPC9NZXNzYWdlQm9keT48L1VuaW9uUGF5TWVzc2FnZT4=\",\"messageSize\":\"908\",\"totalSize\":\"908\"}";

			RFPubMsg msg = JSONUtils.JsonStrToClass(inMsg, RFPubMsg.class);
			Log.i("msg.getMessage", msg.getMessage()+"");
			Log.i("msg.getMessageBytes", Arrays.toString(msg.getMessageBytes())+"");
			// uncompress the message first
			// byte[] uncompressData =
			// CommonUtils.decompress(msg.getMessageBytes()).getBytes();
			// System.out.println("********Uncompressed date from ["+msg.getMessageBytes().length+"] to ["+uncompressData.length+"]");
			// msg.setMessageBytes(uncompressData);
			if (msg == null) {
				return;
			}
			RFPubMsg mMsg = multiPartMsgs.get(msg.getId());
			if (mMsg != null) {
				byte[] messageBody = bytesAppend(mMsg.getMessageBytes(), msg.getMessageBytes());
				Log.i("messageBody", Arrays.toString(messageBody)+"");
				mMsg.setMessageBytes(messageBody);
				if (mMsg.getTotalSize() != (msg.getMessageSize() + mMsg.getCurrentSize())) {
					mMsg.setCurrentSize(msg.getMessageSize() + mMsg.getCurrentSize());
				} else {
					String message = new String(mMsg.getMessageBytes());
					OrderMessageParameter orderMsg;
//					try {
//						message = new String(message.getBytes("utf-8"),"GBK");
//						Log.i("handle chartSet readMsg ",message);
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
					// 接收到通知信息处理
					switch (UidPushConstants.MESSAGE_TYPE.valueOf(getMessageType(message))) {
					case ORDER_MSG: // 订单消息
						orderMsg = (OrderMessageParameter) CommonUtils.unmarshallMessage(
								OrderMessageParameter.class, message);
						String enContent = DESUtil.decrypt(orderMsg.getMessageBody().getContent(),null);
						Log.i("enContent:", enContent);
						orderMsg.getMessageBody().setContent(enContent);
						mHandler.obtainMessage(1, orderMsg.getMessageBody()).sendToTarget();
						confirmMsg(orderMsg.getMessageHeader().getMessageID());
						break;
					
					case PING: // PING信息
						isPingSuccess = true;
						break;
					}
				}

			} else {
				if (msg.getTotalSize() != msg.getMessageSize()) {
					msg.setCurrentSize(msg.getCurrentSize() + msg.getMessageSize());
					multiPartMsgs.put(msg.getId(), msg);
				} else {
					String message = new String(msg.getMessageBytes(),"utf-8");
					OrderMessageParameter orderMsg;
//					try {
//						message = new String(message.getBytes("utf-8"),"GBK");
//						Log.i("handle chartSet readMsg ",message);
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
					System.out.println("Receiving message:[" + message + "]");
					// 接收到通知信息处理
					switch (UidPushConstants.MESSAGE_TYPE.valueOf(getMessageType(message))) {
					case ORDER_MSG: // 支付通知信息
						orderMsg = (OrderMessageParameter) CommonUtils.unmarshallMessage(
								OrderMessageParameter.class, message);
						String enContent = DESUtil.decrypt(orderMsg.getMessageBody().getContent(),null);
						Log.i("enContent:", enContent);
						orderMsg.getMessageBody().setContent(enContent);
						mHandler.obtainMessage(1, orderMsg.getMessageBody()).sendToTarget();
						confirmMsg(orderMsg.getMessageHeader().getMessageID());
						break;
						
					case PING: // PING信息
						isPingSuccess = true;
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 确认收到推送信息
	 * 
	 * @param messageId
	 * @throws Exception
	 */
	protected void confirmMsg(String messageId) throws Exception {
		UidPushConfirmMessage confMsg = new UidPushConfirmMessage();
		MessageHeader header = CommonUtils.createMessageHeader(
				UidPushConstants.MESSAGE_SOURCE.UIDPUSH_MOBILE.toString(),
				UidPushConstants.MESSAGE_SOURCE.UIDPUSH_MOBILE.toString(), 
				UidPushConstants.MESSAGE_TYPE.CONFIRM_MSG.toString(), 
				UidPushConstants.MESSAGE_SOURCE.UIDPUSH_SERVER.toString());
		
		confMsg.setMessageHeader(header);
		ConfirmMsg configmsg = new ConfirmMsg();
		configmsg.setReplyMsgId(messageId);
		confMsg.setMessageBody(configmsg);
		String message = CommonUtils.marshallObject(UidPushConfirmMessage.class, confMsg);
		Log.i("comfirm msg",message);
		pushMessage(clientID, UidPushConstants.MESSAGE_SOURCE
				.UIDPUSH_SERVER.toString(), message);
	}
	
	/**
	 * HEARTBEAT Message
	 * 
	 * @param messageId
	 * @throws Exception
	 */
	protected void pingMsg() throws Exception {
		//pushMessage(clientID, "UNIONPAYSERVER", message);
		Log.i(TAG, "start pingMsg...");
		HttpGet get = new HttpGet(getURI()+"/PING");
		get.addHeader("PING", clientID);
		String originScheme = getURI().getScheme().equals("https") ? "https" : "http";
		if (originScheme.equalsIgnoreCase("https")) {
			HttpClient sslHttpClient = HttpClientHelper
					.getSSLHttpClient(mHandler.getmContext(),false);
			HttpResponse response = sslHttpClient.execute(get);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				Log.i(TAG, "Reply to confirm success!!");
			}else{
				Log.i(TAG, "https pingMsg  Failure to identify reply!!");
				Log.i(TAG, "respon code:"+code);
				Log.i(TAG, EntityUtils.toString(response.getEntity(),"UTF-8"));
				addRetryCount();
			}
		}else{
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(get);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				Log.i(TAG, "  Reply to confirm success!!");
			}else{
				Log.i(TAG, "http pingMsg Failure to identify reply!!");
				Log.i(TAG, "respon code:"+code);
				Log.i(TAG, EntityUtils.toString(response.getEntity(),"UTF-8"));
				addRetryCount();
			}
		}
	}

	public String getMessageType(final String message) throws Exception {
		return CommonUtils.getXMLNodeValue(message, 
				UidPushConstants.XPATH_UIDPUSH_MESSAGE_TYPE);
	}

	public static byte[] bytesAppend(final byte[] src, final byte[] array) {
		byte[] srcBytes = new byte[src.length + array.length];
		System.arraycopy(src, 0, srcBytes, 0, src.length);
		System.arraycopy(array, 0, srcBytes, src.length, array.length);
		return srcBytes;
	}

	public void pushMessage(final String from, final String to, final String message) {
		if (mSocket != null) {
			try {
				String[] toList = to.split(",");
				RFPubMsg oMsg = new RFPubMsg();
				oMsg.setFrom(from);
				oMsg.setTo(toList);
				oMsg.setMessage(message);
				send(JSONUtils.ObjectToJson(oMsg));
			} catch (Exception ex) {
				Log.e(TAG, ex.getMessage());
			}
		}
	}
}
