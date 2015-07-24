package com.example.faxian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPage extends Activity{
	private WebView view;
	String URL;
	String url_head = "http://";
	//�����ҳ����javascript����webview��������֧��javascript
	WebSettings settings;
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_page);
		
		view = (WebView) findViewById(R.id.webView1);
		//����webview֧��javascript
		settings = view.getSettings();
		settings.setJavaScriptEnabled(true);
		
		Intent intent = getIntent();
		
		URL = intent.getStringExtra("URL");
		init();
	}
	private void init() {
		// webview����Web��Դ
		view.loadUrl(URL);
		//����webViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��webview��
		view.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//����ֵ��ture��ʱ�������webview�򿪣�false����ϵͳ��������ߵ����������
				view.loadUrl(url);
				return true;
			}
			
		});
	}
	
	

}
