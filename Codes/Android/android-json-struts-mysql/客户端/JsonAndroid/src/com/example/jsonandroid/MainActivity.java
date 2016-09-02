package com.example.jsonandroid;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
 //ģ�����Լ����Լ�����localhost�ˣ�������Ӧ��Ϊ10.0.2.2
 private static  String url="http://10.0.2.2:8080/JsonWeb/login.action?";
 private final String url_constant="http://10.0.2.2:8080/JsonWeb/login.action?";
 private EditText txUserName;
 private EditText txPassword;
 private Button btnLogin;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	///��Android2.2�Ժ����������´���
		//��Ӧ�ò��õ�Android4.0
		//�����̵߳Ĳ���
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()   
         .detectDiskReads()   
         .detectDiskWrites()   
         .detectNetwork()   // or .detectAll() for all detectable problems   
         .penaltyLog()   
         .build());   
		//����������Ĳ���
		  StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()   
		         .detectLeakedSqlLiteObjects()   
		         //.detectLeakedClosableObjects()   
		         .penaltyLog()   
		         .penaltyDeath()   
		         .build());
        super.onCreate(savedInstanceState);
        //����ҳ�沼��
        setContentView(R.layout.main);
        //���ó�ʼ����ͼ
        initView();
        //�����¼�����������
        setListener();
    }
    
    /**
     * ������ʼ����ͼ�ķ���
     */
	private void initView() {
		btnLogin=(Button)findViewById(R.id.btnLogin);
		txUserName=(EditText)findViewById(R.id.UserName);
		txPassword=(EditText)findViewById(R.id.textPasswd);
	}
	/**
	 * �����¼��ļ������ķ���
	 */
	private void setListener() {
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String userName=txUserName.getText().toString();
				Log.v("userName = ", userName);
				String password=txPassword.getText().toString();
				Log.v("passwd = ",password);
				loginRemoteService(userName,password);
			}
		});
	}
	
	
	/**
     * ��ȡStruts2 Http ��¼��������Ϣ
     * @param  userName
     * @param  password
     */
    public void loginRemoteService(String userName,String password){
        String result=null;
    	try {
	    		 
	    	//����һ��HttpClient����
	    	HttpClient httpclient = new DefaultHttpClient();
	    	//Զ�̵�¼URL
	    	//���������ԭ�е�
	    	//processURL=processURL+"userName="+userName+"&password="+password;
	    	url= url_constant+"userName="+userName+"&password="+password;
	    	Log.d("Զ��URL", url);
	        //����HttpGet����
	    	HttpGet request=new HttpGet(url);
	    	//������Ϣ����MIMEÿ����Ӧ���͵��������ͨ�ı���html �� XML��json�����������Ӧ����Ӧ��ƥ����Դ�������ɵ� MIME ����
	    	//��Դ�����ɵ� MIME ����Ӧ��ƥ��һ�ֿɽ��ܵ� MIME ���͡�������ɵ� MIME ���ͺͿɽ��ܵ� MIME ���Ͳ� ƥ�䣬��ô��
	    	//���� com.sun.jersey.api.client.UniformInterfaceException�����磬���ɽ��ܵ� MIME ��������Ϊ text/xml������
	    	//���ɵ� MIME ��������Ϊ application/xml�������� UniformInterfaceException��
	    	request.addHeader("Accept","text/json");
	        //��ȡ��Ӧ�Ľ��
			HttpResponse response =httpclient.execute(request);
			//��ȡHttpEntity
			HttpEntity entity=response.getEntity();
			//��ȡ��Ӧ�Ľ����Ϣ
			String json =EntityUtils.toString(entity,"UTF-8");
			//JSON�Ľ�������
			if(json!=null){
				JSONObject jsonObject=new JSONObject(json);
				result=jsonObject.get("message").toString();
			}
		   if(result==null){  
			   json="��¼ʧ�������µ�¼";
		   }
			//������ʾ�������Ƿ��¼�ɹ�
			 AlertDialog.Builder builder=new Builder(MainActivity.this);
			 builder.setTitle("��ʾ")
			 .setMessage(result)
			 .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).create().show();
		 
    	 } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
