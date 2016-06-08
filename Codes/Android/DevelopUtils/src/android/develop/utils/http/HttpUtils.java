package android.develop.utils.http;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.develop.utils.InnerContacts;
import android.develop.utils.fastjson.JSON;
import android.develop.utils.io.IOUtils;
import android.develop.utils.log.LogUtils;
import android.os.Handler;
import android.os.Message;

/**
 * HTTP 工具类<br>
 * 用于项目中的简单请求<br>
 * 内部已经封装了打印功能,只需要把DEBUG参数改为true即可<br>
 * 如果需要更换tag可以直接更改,默认为KEZHUANG
 * 
 * @author KEZHUANG
 * @param <T>
 *
 */
@SuppressWarnings("deprecation")
public class HttpUtils {
	/**
	 * Log的开关<br>
	 * true为开启<br>
	 * false为关闭<br>
	 */
	public static boolean DEBUG = InnerContacts.DEFAULT_DEBUG;

	/**
	 * Log 输出标签
	 */
	public static String TAG = InnerContacts.DEFAULT_TAG;
	
	/**
	 * JSON格式的参数<br>
	 * 例如:json={id:1}
	 */
	public static final int FLAG_PARAMS_JSON = 0x00000004;
	/**
	 * 用List集合直接存储参数<br>
	 * 例如:
	 * list.add(new BasicNameValuePair("id","1"));
	 */
	public static final int FLAG_PARAMS_SPLICING = 0x00000005;
	private static final int FLAG_PARAMS_GET = 0x00000006;
	
	
	private static final int HTTP_FINISH_JSON = 0x00000000;
	private static final int HTTP_FINISH_INPUT = 0x00000003;
	private static final int HTTP_ERROR = 0x00000001;
	private static final int HTTP_LISTENER = 0x00000002;
	
	/**
	 * POST请求
	 * 
	 * @param url
	 *            请求的URL
	 * @param params
	 *            请求参数
	 * @param flag
	 *            参数格式,是json还是零散的参数
	 * @param listener
	 *            HTTP请求监听是否完成
	 */
	public static void doPost(String url, Map<String, String> params,int flag,HttpListener listener) {
		if (listener == null) {
			LogUtils.inner_e("监听接口为null,无法继续完成请求操作",
					!InnerContacts.DEFAULT_DEBUG);
			return;
		}
		new InnerThread(url, params,flag,listener,new InnerHandler()).start();
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *            请求的URL
	 * @param clz
	 *            需要解析的实体类
	 * @param listener
	 *            HTTP请求监听是否完成
	 */
	public static void doGet(String url, HttpListener listener) {
		if (listener == null) {
			LogUtils.inner_e("监听接口为null,无法继续完成请求操作",
					!InnerContacts.DEFAULT_DEBUG);
			return;
		}
		new InnerThread(url,null,FLAG_PARAMS_GET,listener,new InnerHandler()).start();
	}

	/**
	 * HTTP 监听请求结束
	 * @author KEZHUANG
	 *
	 */
	public interface HttpListener {
		/**
		 * 请求出错
		 * @param errorCode 错误码
		 */
		void onError(int errorCode);

		/**
		 * 请求成功 
		 * @param entity 服务器返回的json,如果你想要操作InputStream,可以忽略此方法
		 */
		void onFinish(String json);
		
		/**
		 * 请求成功
		 * @param is 服务器返回的in流
		 */
		void onFinish(InputStream is);
	}

	private static class InnerHandler extends Handler{
		private HttpListener listener;
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HTTP_FINISH_JSON:
				String json = (String) msg.obj;
				listener.onFinish(json);
				break;
			case HTTP_FINISH_INPUT:
				InputStream is = (InputStream) msg.obj;
				listener.onFinish(is);
				break;
			case HTTP_ERROR:
				int code= (Integer) msg.obj;
				listener.onError(code);
				break;
			case HTTP_LISTENER:
				listener = (HttpListener) msg.obj;
				break;
			}
		}
	}
	
	private static class InnerThread extends Thread {
		private String url;
		private Map<String, String> params;
		private InnerHandler handler;
		private int flag;

		public InnerThread(String url, Map<String, String> map,int flag,
				HttpListener listener,InnerHandler handler) {
			super();
			this.url = url;
			this.params = map;
			this.handler = handler;
			this.flag = flag;
			handler.sendMessage(handler.obtainMessage(HTTP_LISTENER, listener));
			LogUtils.inner_d("请求连接：\r\n" + url, DEBUG);
		}

		@Override
		public void run() {
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = null;
			HttpGet get = null;
			HttpPost post = null;
			try {
				if (params != null && params.size() > 0) {
					post = new HttpPost(url);
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					switch (flag) {
					case FLAG_PARAMS_JSON:
						String json = JSON.toJSONString(params);
						LogUtils.inner_d("请求参数：\r\njson="+json, DEBUG);
						nameValuePairs.add(new BasicNameValuePair("json", json));
						break;
					case FLAG_PARAMS_SPLICING:
						Set<String> keys = params.keySet();
						for (String key : keys) {
							nameValuePairs.add(new BasicNameValuePair(key, params
									.get(key)));
							LogUtils.inner_d("key:"+key+"----"+"value:"+params.get(key), DEBUG);
						}
						break;
						
					default:
						break;
					}
					post.setEntity(new UrlEncodedFormEntity(nameValuePairs,
							HTTP.UTF_8));
					response = client.execute(post);
				} else {
					get = new HttpGet(url);
					response = client.execute(get);
				}
				if (response.getStatusLine().getStatusCode() == 200) {
					InputStream is = response.getEntity().getContent();
					handler.sendMessage(handler.obtainMessage(HTTP_FINISH_INPUT, is));
					String json = IOUtils.read(is);
					LogUtils.inner_d("请求结果\r\n" + json, DEBUG);
					handler.sendMessage(handler.obtainMessage(HTTP_FINISH_JSON, json));
				} else {
					int errorCode = response.getStatusLine().getStatusCode();
					handler.sendMessage(handler.obtainMessage(HTTP_ERROR, errorCode));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
