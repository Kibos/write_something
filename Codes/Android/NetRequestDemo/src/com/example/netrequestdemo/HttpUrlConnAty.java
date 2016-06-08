package com.example.netrequestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.netrequestdemo.utils.HttpThread;
/**
 * @author 王永飞
 * 平台  IT蓝豹 www.itlanbao.com
 * HttpUrlConnAty 加载网络
 * 本类主要是通过HttpThread 实现网络请求的
 */
public class HttpUrlConnAty extends Activity {

	private ImageView imageView;  
	private Handler handler = new Handler();  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_url_conn);
		imageView = (ImageView) findViewById(R.id.imageView);  
        new HttpThread("http://img.mukewang.com/552640c300018a9606000338-300-170.jpg", imageView, handler).start();
	}
}
