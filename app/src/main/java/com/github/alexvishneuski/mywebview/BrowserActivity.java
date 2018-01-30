package com.github.alexvishneuski.mywebview;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        Uri data = getIntent().getData();

        if (data != null) {
            webView.loadUrl(data.toString());
        }
    }
}
