package com.jmhz.devicemanage.http;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

public class SSLSocketFactoryEx extends SSLSocketFactory {

	SSLContext sslContext = SSLContext.getInstance("TLS");
	private static final String CLIENT_AGREEMENT = "TLS";//使用协议 
	private static final String CLIENT_KEY_MANAGER = "X509";//密钥管理器  
    private static final String CLIENT_TRUST_MANAGER = "X509";// 
    private static final String CLIENT_KEY_KEYSTORE = "BKS";//密库，这里用

	public SSLSocketFactoryEx(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(truststore);

		TrustManager tm = new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
			}
		};
		sslContext.init(null, new TrustManager[] { tm }, null);
	}
	
//	 public SSLSocketFactoryEx(KeyStore truststore, int x) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
//         super(truststore);
//
//         SSLContext sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);   
//         KeyManagerFactory keyManager = KeyManagerFactory.getInstance(CLIENT_KEY_MANAGER);   
//         TrustManagerFactory trustManager = TrustManagerFactory.getInstance(CLIENT_TRUST_MANAGER);   
//         
//         KeyStore keyKeyStore = KeyStore.getInstance(CLIENT_KEY_KEYSTORE);   
//         KeyStore trustKeyStore = KeyStore.getInstance(CLIENT_TRUST_MANAGER);   
//         
//         FileInputStream kIs = new FileInputStream(mPath);
//         keyKeyStore.load(kIs, KSPWD.toCharArray());
//         kIs.close();   
//         
//         FileInputStream tkIS = new FileInputStream(mPath);
//         trustKeyStore.load(tkIS,TKSPWD.toCharArray());
//         tkIS.close();
//         
//         keyManager.init(keyKeyStore,KSPWD.toCharArray());   
//         trustManager.init(trustKeyStore);
//         
//         sslContext.init(keyManager.getKeyManagers(), trustManager.getTrustManagers(), null);
//     }

	@Override
	public Socket createSocket(Socket socket, String host, int port,
			boolean autoClose) throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(socket, host, port,
				autoClose);
	}

	@Override
	public Socket createSocket() throws IOException {
		return sslContext.getSocketFactory().createSocket();
	}
}