package com.example.netrequestdemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
/**
 * @author 王永飞
 * 平台  IT蓝豹 www.itlanbao.com
 * HttpClientAty 加载网络
 * 本类主要是通过URLConnection 实现网络请求的
 */
public class HttpClientAty extends Activity {
	private EditText et_main_content1;
	private TextView et_main_content2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_client);
		et_main_content1 = (EditText) findViewById(R.id.et_main_content1);
		et_main_content2 = (TextView) findViewById(R.id.et_main_content2);
	}

	/*
	 * 使用httpClient提交get请求
	 */
	public void client_get(View view) {
		// ---http://192.168.30.41:8080/android/index.jsp
		final String path = et_main_content1.getText().toString()
				+ "?type=4&num=30";
		final ProgressDialog progressDialog = ProgressDialog.show(this, null,
				"加载中...");
		new Thread() {
			public void run() {
				try {
					// 创建客户端对象
					HttpClient httpClient = new DefaultHttpClient();
					// 设置超时时间
					HttpParams params = httpClient.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 5000);
					HttpConnectionParams.setSoTimeout(params, 5000);
					// 创建get请求（包含路径）
					HttpGet request = new HttpGet(path);
					// 获取响应对象--客户端对象执行请求获取相应对象
					HttpResponse httpResponse = httpClient.execute(request);
					// 获取请求码
					int statusCode = httpResponse.getStatusLine()
							.getStatusCode();
					// 进行判断
					if (statusCode == 200) {
						// 获取响应体数据
						HttpEntity entity = httpResponse.getEntity();
						// 利用工具类获取响应数据
						final String result = EntityUtils.toString(entity);
						runOnUiThread(new Runnable() {
							public void run() {
								// 移除
								progressDialog.dismiss();
								et_main_content2.setText(result);
							}
						});
					}

				} catch (Exception e) {
					e.printStackTrace();
					Log.e("TAG", "联网请求失败");
				}
			}
		}.start();
	}

	/*
	 * 使用httpClient提交post请求 ?type=4&num=30
	 */
	public void client_post(View view) {
		// ---http://192.168.30.41:8080/android/index.jsp
		final String path = et_main_content1.getText().toString();
		final ProgressDialog progressDialog = ProgressDialog.show(this, null,
				"加载中...");
		new Thread() {
			public void run() {
				try {
					// 创建客户端对象
					HttpClient httpClient = new DefaultHttpClient();
					// 设置超时时间
					HttpParams params = httpClient.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 5000);
					HttpConnectionParams.setSoTimeout(params, 5000);
					// 创建post请求对象
					HttpPost request = new HttpPost(path);

					// 设置请求体
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();
					parameters.add(new BasicNameValuePair("type", "4"));
					parameters.add(new BasicNameValuePair("num", "30"));
					HttpEntity entity = new UrlEncodedFormEntity(parameters,
							"utf-8");
					request.setEntity(entity);
					// 获取响应对象
					HttpResponse httpResponse = httpClient.execute(request);
					// 获取请求码
					int statusCode = httpResponse.getStatusLine()
							.getStatusCode();
					// 进行判断
					if (statusCode == 200) {
						// 获取响应体数据
						HttpEntity entityRequest = httpResponse.getEntity();
						// 利用工具类
						final String result = EntityUtils
								.toString(entityRequest);
						runOnUiThread(new Runnable() {
							public void run() {
								// 移除
								progressDialog.dismiss();
								et_main_content2.setText(result);
							}
						});
					}

				} catch (Exception e) {
					e.printStackTrace();
					Log.e("TAG", "联网请求失败");
				}
			}
		}.start();
	}
}
