package com.hhm.trade.download;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public String getHttpJSON(String url) {
		HttpGet httpRequest = new HttpGet(url);
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = httpclient.execute(httpRequest);

			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String jsonStr = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");
				return jsonStr;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static int getHttpStatus() {
		int status = 0;
		HttpGet httpRequest = new HttpGet(
				"http://localhost:8080/vote/AndroidConnServlet");
		try {
			// /生成HttpParams对象，设置网络连接的时间
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
			HttpConnectionParams.setSoTimeout(httpParameters, 5000);
			HttpConnectionParams.setTcpNoDelay(httpParameters, true);

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			// HttpParams params = httpclient.getParams();
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			// HttpConnectionParams.setConnectionTimeout(params, 3000);
			// HttpConnectionParams.setSoTimeout(params, 5000);

			status = httpResponse.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("==============connection wifi fail,e.printStackTrace() : "
							+ e.getMessage());
		}
		return status;
	}

}
