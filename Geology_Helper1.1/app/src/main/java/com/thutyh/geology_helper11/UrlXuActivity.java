package com.thutyh.geology_helper11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UrlXuActivity extends AppCompatActivity {

    private WebView webvXu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_xu);
        init();
    }
    private void init(){
        webvXu = (WebView)findViewById(R.id.webviewXu);
        WebSettings websettings = webvXu.getSettings();
        //支持JavaScript
        websettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        websettings.setSupportZoom(true);
        // 设置出现缩放工具
        websettings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        websettings.setUseWideViewPort(true);
        //自适应屏幕
        websettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        websettings.setLoadWithOverviewMode(true);
        //设置可以访问文件
        websettings.setAllowFileAccess(true);
        webvXu.loadUrl("http://www.tsinghua.edu.cn/publish/he/index.html");
        webvXu.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }
    //返回时回到上一层
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webvXu.canGoBack()) {
            webvXu.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
