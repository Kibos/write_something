package com.example.netrequestdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.netrequestdemo.utils.BitMapCache;

public class VolleyAty extends Activity {
	private ImageView imageview1;
	private ImageView imageview2;
	private NetworkImageView networkImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volley_request);
		/** 获取控件、图片url地址 */
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		networkImageView = (NetworkImageView) findViewById(R.id.networkImageview);
		String url = "http://img.mukewang.com/55237dcc0001128c06000338-300-170.jpg";

		/**
		 * 下载图片的第二种方式ImageLoader+BitMapCache
		 */
		// imageCache单肚使用是不到缓存效果，需要结合lruCache
//		ImageLoader imageLoader1 = new ImageLoader(
//				MyApplication.getHttpRequestQueue(), new BitMapCache());
//		networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
//		networkImageView.setErrorImageResId(R.drawable.ic_launcher);
//		networkImageView.setImageUrl(url, imageLoader1);

		/**
		 * 下载图片的第三种种方式
		 */
//		ImageLoader imageLoader2 = new ImageLoader(
//				MyApplication.getHttpRequestQueue(), new BitMapCache());
//		// view视图，默认的图片，错误的图片
//		ImageListener listener = imageLoader2.getImageListener(imageview2,
//				R.drawable.ic_launcher, R.drawable.ic_launcher);
//		imageLoader2.get(url, listener);

		/**
		 * 下载网络图片的第一种方式ImageRequest
		 */
		// // 0 是原图的方式加载--Config.RGB_565原图
		ImageRequest imageRequest = new ImageRequest(url,
				new Listener<Bitmap>() {
					//
					@Override
					public void onResponse(Bitmap arg0) {
						imageview1.setImageBitmap(arg0);
					}
				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						imageview1
								.setBackgroundResource(R.drawable.ic_launcher);
					}
				});

		MyApplication.getHttpRequestQueue().add(imageRequest);
		MyApplication.getHttpRequestQueue().start();
	}

}
