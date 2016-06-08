package com.example.netrequestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class XUtilAty extends Activity implements OnClickListener {
	private Button get;
	private TextView resultText;
	private HttpUtils http;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xutil_request);

		get = (Button) findViewById(R.id.get);
		resultText = (TextView) findViewById(R.id.result_txt);

		http = new HttpUtils();

		get.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.get) {
			// 设置当前请求的缓存时间
			http.configCurrentHttpCacheExpiry(1000 * 10);
			http.send(HttpMethod.GET, "http://www.baidu.com", null,
					new RequestCallBack<String>() {

						@Override
						public void onStart() {
							resultText.setText("conn...");
						}

						@Override
						public void onLoading(long total, long current,
								boolean isUploading) {
							resultText.setText(current + "/" + total);
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							resultText.setText("response:"
									+ responseInfo.result);
						}

						@Override
						public void onFailure(
								com.lidroid.xutils.exception.HttpException arg0,
								String msg) {
							resultText.setText(msg);

						}
					});

		}

	}
}
