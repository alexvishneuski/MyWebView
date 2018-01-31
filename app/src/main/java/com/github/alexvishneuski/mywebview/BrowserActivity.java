package com.github.alexvishneuski.mywebview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class BrowserActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    final String URL = "url";
    private String mRedirectUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        WebView webView = findViewById(R.id.web_view);

        final MyWebViewClient myWebViewClient = new MyWebViewClient();
        webView.setWebViewClient(myWebViewClient);


        Uri data = getIntent().getData();
        Log.d(TAG, "onCreate: data " + data.toString());
        if (data != null) {
            webView.loadUrl(data.toString());
        }

        //getting url
        String s = webView.getOriginalUrl();
        Log.d(TAG, "onCreate: " + s);

        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(BrowserActivity.this, VKActivity.class);
        // intent.putExtra(URL, s);
        BrowserActivity.this.startActivity(intent);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "shouldOverrideUrlLoading() 1 called with: url = [" + url + "]");
            mRedirectUrl = url;
            BrowserActivity.this.onLoaded();
            //return true;
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d(TAG, "onPageStarted() called with: url = [" + url + "]");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d(TAG, "onPageFinished() called with: url = [" + url + "]");
        }
    }

    public void onLoaded() {
        Log.d(TAG, "onLoaded: " + mRedirectUrl);
        Toast.makeText(this, mRedirectUrl, Toast.LENGTH_LONG).show();
    }



    public static Map<String,String> explodeRedirectUrl(String pRedirectUrl){
        String[] url = pRedirectUrl.split("#");
        String[] keyValuePairs = url[1].split("&");
        HashMap<String, String> parameters = new HashMap<String, String>(keyValuePairs.length);

        for (String keyValueString : keyValuePairs) {
            String[] keyValueArray = keyValueString.split("=");
            parameters.put(keyValueArray[0], keyValueArray[1]);
        }

        return parameters;
    }


}
