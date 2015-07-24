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
	//如果网页中有javascript，则webview必须设置支持javascript
	WebSettings settings;
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_page);
		
		view = (WebView) findViewById(R.id.webView1);
		//设置webview支持javascript
		settings = view.getSettings();
		settings.setJavaScriptEnabled(true);
		
		Intent intent = getIntent();
		
		URL = intent.getStringExtra("URL");
		init();
	}
	private void init() {
		// webview加载Web资源
		view.loadUrl(URL);
		//覆盖webView默认使用第三方或系统默认浏览器打开网页的行为，使网页用webview打开
		view.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//返回值是ture的时候控制区webview打开，false调用系统浏览器或者第三发浏览器
				view.loadUrl(url);
				return true;
			}
			
		});
	}
	
	

}
