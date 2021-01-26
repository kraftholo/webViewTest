package com.sk.webviewexample;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

// -Defining WebAppInterface------------------------------------------------------------------------------------------------------------------
class WebAppInterface {
    private Context mContext;
    private MyCallBack mCallBack;

    public WebAppInterface(Context ctx, MyCallBack callBack) {
        this.mContext = ctx;
        this.mCallBack = callBack;
    }

    // The functions in our interface(WebAppInterface) will be called from the website js code
    @JavascriptInterface
    public void onSuccess(String email) {
        Toast.makeText(mContext, "WebAppInterface : onSuccess()", Toast.LENGTH_LONG).show();
        mCallBack.onSuccess(email);
    }

    @JavascriptInterface
    public void onError(String errorMsg) {
        Toast.makeText(mContext, "WebAppInterface : onError()", Toast.LENGTH_LONG).show();
        mCallBack.onError(errorMsg);
    }

    @JavascriptInterface
    public void onLoading(Boolean isLoading) {
        Toast.makeText(mContext, "WebAppInterface : onLoading()", Toast.LENGTH_LONG).show();
        mCallBack.onLoading(isLoading);
    }

    // Implemented by mainactivity
    interface MyCallBack {
        void onSuccess(String email);

        void onError(String errorMsg);

        void onLoading(Boolean isLoading);
    }
}

