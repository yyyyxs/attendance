package com.jmhz.devicemanage.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import android.content.Context;
import android.util.Log;

public class HttpClientHelper {
	private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	private static final String ENCODING_GZIP = "gzip";
	
	public synchronized static HttpClient getSSLHttpClient(Context context,boolean zipEncode) {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
			DefaultHttpClient client = new DefaultHttpClient(ccm, params);
			if(zipEncode){
				//set compress indicator to header
				client.addRequestInterceptor(new HttpRequestInterceptor() {
					  public void process(HttpRequest request, HttpContext context) {
					    // Add header to accept gzip content
					    if (!request.containsHeader(HEADER_ACCEPT_ENCODING)) {
					      request.addHeader(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
					    }
					  }
					});
				//set uncompress stream to unzip the data
				client.addResponseInterceptor(new HttpResponseInterceptor() {
					  public void process(HttpResponse response, HttpContext context) {
					    // Inflate any responses compressed with gzip
					    final HttpEntity entity = response.getEntity();
					    final Header encoding = entity.getContentEncoding();
					    if (encoding != null) {
					      for (HeaderElement element : encoding.getElements()) {
					        if (element.getName().equalsIgnoreCase(ENCODING_GZIP)) {
						      Log.i("LongPollingSocketClient","Incoming Compressed data entry:**********["+response.getEntity().getContentLength()+"]");
						      GzipDecompressingEntity eEntry = new GzipDecompressingEntity(response.getEntity());
					          response.setEntity(eEntry);
					          break;
					        }
					      }
					    }
					  }
				});
			}
			return client;
		} catch (Exception e) {
			Log.e("HttpClientHelper", e.getMessage());
			return new DefaultHttpClient();
		}
	}
	public synchronized static HttpClient getSSLHttpClient(Context context) {
		return getSSLHttpClient(context,true);
	}
	
	public static class GzipDecompressingEntity extends HttpEntityWrapper {

	    public GzipDecompressingEntity(final HttpEntity entity) {
	        super(entity);
	    }

	    public InputStream getContent()
	        throws IOException, IllegalStateException {
	        InputStream wrappedin = wrappedEntity.getContent();
	        return new GZIPInputStream(wrappedin);
	    }

	    public long getContentLength() {
	        return -1;
	    }

	}

	public synchronized static SSLSocketFactory getSSLSocket(Context context) {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			
			SSLSocketFactory sf = new SSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			return sf;
		} catch (Exception e) {
			Log.e("HttpClientHelper", e.getMessage());
			return null;
		}
	}

	public static class WebSocketSSLSocketFactory extends SSLSocketFactory {
		private javax.net.ssl.SSLSocketFactory factory;

		public WebSocketSSLSocketFactory(Context context) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
			super(null);
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}
			} };
			try {
				SSLContext sslcontext = SSLContext.getInstance("TLS");
				sslcontext.init(null, trustAllCerts, new java.security.SecureRandom());
				factory = sslcontext.getSocketFactory();
				setHostnameVerifier(new AllowAllHostnameVerifier());
			} catch (Exception ex) {
			}
		}

		public static SocketFactory getDefault(Context context) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
				UnrecoverableKeyException {
			return new WebSocketSSLSocketFactory(context);
		}

		@Override
		public Socket createSocket() throws IOException {
			return factory.createSocket();
		}

		@Override
		public Socket createSocket(Socket socket, String s, int i, boolean flag) throws IOException {
			return factory.createSocket(socket, s, i, flag);
		}

		public Socket createSocket(InetAddress inaddr, int i, InetAddress inaddr1, int j) throws IOException {
			return factory.createSocket(inaddr, i, inaddr1, j);
		}

		public Socket createSocket(InetAddress inaddr, int i) throws IOException {
			return factory.createSocket(inaddr, i);
		}

		public Socket createSocket(String s, int i, InetAddress inaddr, int j) throws IOException {
			return factory.createSocket(s, i, inaddr, j);
		}

		public Socket createSocket(String s, int i) throws IOException {
			return factory.createSocket(s, i);
		}

		public String[] getDefaultCipherSuites() {
			return factory.getDefaultCipherSuites();
		}

		public String[] getSupportedCipherSuites() {
			return factory.getSupportedCipherSuites();
		}
	}
}
