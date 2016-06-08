package com.example.netrequestdemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * @author 王永飞
 * 平台  IT蓝豹 www.itlanbao.com
 * AsyncAty 加载网络图片例子
 * 本类主要是通过URLConnection 实现网络请求的
 */
public class AsyncAty extends Activity {

	private ImageView imageView;
	private ProgressBar progressBar;
	private static String url = "http://img.mukewang.com/55237dcc0001128c06000338-300-170.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_request);
		imageView = (ImageView) findViewById(R.id.imageView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		new MyAsycTask().execute(url);
	}

	/**
	 * 第一个参数是运行参数 第二个参数是progress是加载的进度 第三个参数是返回的类型是bitmap
	 * 
	 */
	class MyAsycTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			imageView.setImageBitmap(result);
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// 因为只传一个参数
			Bitmap bitmap = null;
			String url = params[0];
			URLConnection connection;
			InputStream inputStream;
			try {
				// 打开连接
				connection = new URL(url).openConnection();
				// 获取输入流，读取网络数据
				inputStream = connection.getInputStream();
				// 包装下
				BufferedInputStream bis = new BufferedInputStream(inputStream);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 直接返回一个图片
				bitmap = BitmapFactory.decodeStream(bis);
				// 关闭流
				inputStream.close();
				bis.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return bitmap;
		}

	}
}
