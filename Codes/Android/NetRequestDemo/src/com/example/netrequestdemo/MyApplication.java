package com.example.netrequestdemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


import com.nostra13.universalimageloader.core.ImageLoader;  
  


public class MyApplication extends Application {
	public static RequestQueue queue;

	@Override
	public void onCreate() {
		super.onCreate();
		queue = Volley.newRequestQueue(getApplicationContext());
		
		// 创建默认的ImageLoader配置参数  
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration  
                .createDefault(this);  
  
        // Initialize ImageLoader with configuration.  
        ImageLoader.getInstance().init(configuration); 
        

	}

	public static RequestQueue getHttpRequestQueue() {

		return queue;
	}
}
