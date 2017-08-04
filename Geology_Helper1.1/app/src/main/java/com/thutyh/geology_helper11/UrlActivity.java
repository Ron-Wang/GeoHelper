package com.thutyh.geology_helper11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UrlActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        init();
    }
    private void init(){
        webview = (WebView)findViewById(R.id.webview);
        WebSettings websettings = webview.getSettings();
        //支持JavaScript
        websettings.setJavaScriptEnabled(true);
        //自适应屏幕
        websettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        websettings.setLoadWithOverviewMode(true);
        //设置可以访问文件
        websettings.setAllowFileAccess(true);
        webview.loadUrl("http://www.meggs-thu.com/");
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }
}
