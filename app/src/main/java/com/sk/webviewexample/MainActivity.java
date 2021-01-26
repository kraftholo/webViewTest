package com.sk.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements WebAppInterface.MyCallBack {

    private WebView mWebView;
    static final String CWM_URL = "https://open-api.xyz/password_reset";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.myWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());         //doesnt open in browser
        mWebView.loadUrl(CWM_URL);

        //"AndroidTextListener" will be used in website js code ; eg - AndroidListener.onLoading(false)
        mWebView.addJavascriptInterface(new WebAppInterface(this, this), "AndroidTextListener");
    }

    //--Callback demo------------------------------------------------------------------------------------------------------
    @Override
    public void onSuccess(String email) {
        Log.d("MainActivity", "OnSuccess()!!!!"+ email);
    }

    @Override
    public void onError(String errorMsg) {
        Log.d("MainActivity", "onError()!!!!"+ errorMsg);
    }

    @Override
    public void onLoading(Boolean isLoading) {
        Log.d("MainActivity", "onLoading()!!!!"+ isLoading);
    }
}