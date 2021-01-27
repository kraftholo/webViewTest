package com.sk.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements WebAppInterface.MyCallBack {

    private WebView mWebView;
    static final String CWM_URL = "https://open-api.xyz/password_reset";
    private EditText mEditText;
    private Button mButton;
    private WebView mWebView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToWebApp(mEditText.getText().toString());
            }
        });

        setupWebViews();
    }

    void setupWebViews() {
        mWebView = findViewById(R.id.myWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());         //doesnt open in browser
        mWebView.loadUrl(CWM_URL);

        //"AndroidTextListener" will be used in website js code ; eg - AndroidListener.onLoading(false)
        mWebView.addJavascriptInterface(new WebAppInterface(this, this), "AndroidTextListener");


        mWebView2 = findViewById(R.id.myWebView2);
        mWebView2.getSettings().setJavaScriptEnabled(true);
        mWebView2.setWebViewClient(new WebViewClient());
        mWebView2.loadUrl("file:///android_asset/webview.html");
    }

    void sendToWebApp(String textToSend) {
        //Interact with the web app
        Log.d("MainActivity","sendToWebApp() -"+textToSend);
        mWebView2.evaluateJavascript("javascript:updateFromAndroid(\""+textToSend+"\")",null);

    }

    //--Callback demo------------------------------------------------------------------------------------------------------
    @Override
    public void onSuccess(String email) {
        Log.d("MainActivity", "OnSuccess()!!!!" + email);
    }

    @Override
    public void onError(String errorMsg) {
        Log.d("MainActivity", "onError()!!!!" + errorMsg);
    }

    @Override
    public void onLoading(Boolean isLoading) {
        Log.d("MainActivity", "onLoading()!!!!" + isLoading);
    }
}