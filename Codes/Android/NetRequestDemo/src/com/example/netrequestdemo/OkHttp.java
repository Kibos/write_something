package com.example.netrequestdemo;

import java.io.IOException;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class OkHttp extends Activity {
	private String htmlStr;
	private Handler mHandler;
	TextView textView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ok_http);
		textView = (TextView) findViewById(R.id.textView);
		mHandler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 1) {
					
					textView.setText(htmlStr);
				}
			};
		};
		// 创建okHttpClient对象
		OkHttpClient mOkHttpClient = new OkHttpClient();
		// 创建一个Request
		final Request request = new Request.Builder().url(
				"http://www.imooc.com/api/teacher?type=4&num=30").build();
		// new call
		Call call = mOkHttpClient.newCall(request);
		// 请求加入调度
		call.enqueue(new Callback() {
			
			

			@Override
			public void onResponse(Response response) throws IOException {
				htmlStr = response.body().string();
				mHandler.sendEmptyMessage(1);
			}
			
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				
			}
		});
		
		
	}
}
