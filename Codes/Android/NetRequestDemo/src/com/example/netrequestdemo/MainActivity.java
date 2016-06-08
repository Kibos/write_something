package com.example.netrequestdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author 王永飞
 *  IT蓝豹 www.itlanbao.com
 */
public class MainActivity extends Activity implements OnClickListener {

	Button httpUrlConn;
	Button httpClient;
	Button volley;
	Button Async;
	Button XUtil;
	Button imageLoader;
	Button okHttp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViewAndClick();
	}

	private void initViewAndClick() {
		httpUrlConn = (Button) findViewById(R.id.httpUrlConn);
		httpClient = (Button) findViewById(R.id.httpClient);
		volley = (Button) findViewById(R.id.volley);
		Async = (Button) findViewById(R.id.Async);
		XUtil = (Button) findViewById(R.id.XUtil);
		imageLoader = (Button) findViewById(R.id.imageLoader);
		okHttp = (Button)findViewById(R.id.okHttp);
		
		httpUrlConn.setOnClickListener(this);
		httpClient.setOnClickListener(this);
		volley.setOnClickListener(this);
		Async.setOnClickListener(this);
		XUtil.setOnClickListener(this);
		imageLoader.setOnClickListener(this);
		okHttp.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.httpUrlConn:
			startActivity(new Intent(this, HttpUrlConnAty.class));
			break;
		case R.id.httpClient:

			startActivity(new Intent(this, HttpClientAty.class));
			break;
		case R.id.volley:
			startActivity(new Intent(this, VolleyAty.class));

			break;
		case R.id.Async:

			startActivity(new Intent(this, AsyncAty.class));
			break;
		case R.id.XUtil:
			startActivity(new Intent(this, XUtilAty.class));
			
			break;
		case R.id.imageLoader:
			startActivity(new Intent(this, ImageLoader.class));

			break;
		case R.id.okHttp:
			startActivity(new Intent(this, OkHttp.class));
			
			break;

		default:
			break;
		}
	}

}
