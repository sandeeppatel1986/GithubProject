package com.reagent.android.testproject.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

/**
 * Http GET request class
 * 
 * @author Sandeep
 * 
 */
public class HttpRequest implements Runnable {

	private final static int TIMEOUT = 500000;

	private String urlstring;
	private int action;
	private HttpCallback cb;
	public static int statusCode;
	private HashMap<String, String> params = null;

	public HttpRequest(String url, HashMap<String, String> params, int action,
			HttpCallback cb) {

		this.urlstring = url;
		this.cb = cb;
		this.action = action;
		this.params = params;

	}

	public void run() {

		String rawResponse = null;

		StringBuilder builder = new StringBuilder(urlstring);
		InputStream inputStream = null;

		if (params != null) {

			builder.append("?");
			Set<String> set = params.keySet();

			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {

				String paramName = iterator.next();
				if (params.get(paramName) != null && paramName != null) {
					try {
						builder.append(paramName).append("=")
								.append(URLEncoder.encode(params.get(paramName), "UTF-8"))
								.append("&");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}

		try {
			Log.d("<<url>>", builder.toString());
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);
			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			
			httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));
			HttpGet httpGet = new HttpGet(builder.toString());
			HttpResponse httpResponse = httpclient.execute(httpGet);

			inputStream = httpResponse.getEntity().getContent();

			byte[] responseData = new byte[1000];
			int length = 0;

			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

			while ((length = inputStream.read(responseData)) != -1) {
				arrayOutputStream.write(responseData, 0, length);
			}

			rawResponse = new String(arrayOutputStream.toByteArray(), "UTF-8");
			statusCode=httpResponse.getStatusLine().getStatusCode();
			arrayOutputStream.close();

		} catch (ConnectTimeoutException e) {

		} catch (SocketException e) {

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (rawResponse != null) {
			cb.onResponse(rawResponse.toString(), action);
		} else
			cb.onResponse(null, action);
	}

}