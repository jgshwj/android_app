package com.example.webviewtest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends ActionBarActivity {

	private WebView mWebView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        mWebView = (WebView) findViewById(R.id.webview);
        mWebView = new WebView(this);
        mWebView.setBackgroundColor(Color.TRANSPARENT);
        mWebView.setHorizontalScrollBarEnabled(true);
		mWebView.setVerticalScrollBarEnabled(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setSavePassword(false);
		mWebView.getSettings().setSaveFormData(false);
//		mWebView.getSettings().setA
        WebSettings webSettings = mWebView.getSettings();
//		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//		webSettings.setDefaultTextEncodingName("UTF-8");
//		webSettings.setJavaScriptEnabled(true);
//		webSettings.setDomStorageEnabled(true);
		String appCachePath = getApplicationContext().getCacheDir()
				.getAbsolutePath();
		webSettings.setAppCachePath(appCachePath);
		webSettings.setAllowFileAccess(true);
		webSettings.setAppCacheEnabled(true);
		mWebView.setWebViewClient(new WebViewClient(){
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
					super.onPageStarted(view, url, favicon);
					Log.d("test","onPageStarted url:"+url);
				}
				@Override
				public void onPageFinished(WebView view, String url) {
					// TODO Auto-generated method stub
					super.onPageFinished(view, url);
					Log.d("test","onPageFinished url:"+url);
				}
				public boolean shouldOverrideUrlLoading(WebView v ,String url){
					Log.d("test","shouldOverrideUrlLoading url:"+url);
					v.loadUrl(url);
					return true;
				}
				@Override
				public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
					Log.d("test","onReceivedError errorCode:"+errorCode+" failingUrl:"+failingUrl);
				}
				@Override
				public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
					Log.d("test","onReceivedSslError url:"+error.getPrimaryError());
				}
				@Override
				public void onLoadResource(WebView view, String url) {
					Log.d("test","onLoadResource url:"+url);
					super.onLoadResource(view, url);
				}
		});
		mWebView.setWebChromeClient(new WebChromeClient(){
			
		});
		
		//
//		webSettings.setBuiltInZoomControls(true);
//
//		webSettings.setPluginsEnabled(true);
//	    webSettings.setUseWideViewPort(true);
//	    webSettings.setLoadWithOverviewMode(true);
//	    webSettings.setSavePassword(false);
//	    webSettings.setSaveFormData(false);
//	    webSettings.setGeolocationEnabled(true);
//	    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
	    
	    mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                    String mimetype, long contentLength) {
            	Log.d("test","onDownloadStart url:"+url);
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                
            }
        });
		//
	    
	    mWebView.loadUrl("http://shai.ba");
//	    mWebView.loadUrl("http://pinv.me/website/web/home.html");
//	    mWebView.loadUrl("http://y.sdo.com");
	    
	    mWebView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				Log.d("test","onTouch---------------"+arg1.getAction());
				return true;
			}
		});
	    
	    mWebView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("test","onClick---------------");
			}
		});
    }

    @Override
    public void onWindowAttributesChanged(LayoutParams params) {
    	Log.d("test","onWindowAttributesChanged---------------");
    	super.onWindowAttributesChanged(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	Log.d("test","onWindowFocusChanged---------------");
    	super.onWindowFocusChanged(hasFocus);
    }

    
    class MyWebView extends WebView{

		public MyWebView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
//		setOnT
    	@Override
    	protected void onWindowVisibilityChanged(int visibility) {
    		// TODO Auto-generated method stub
    		super.onWindowVisibilityChanged(visibility);
    	}
    }
}
