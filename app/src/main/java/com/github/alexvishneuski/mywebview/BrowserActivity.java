package com.github.alexvishneuski.mywebview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class BrowserActivity extends AppCompatActivity {

    final String URL = "url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        WebView webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        Uri data = getIntent().getData();

        if (data != null) {
            webView.loadUrl(data.toString());
        }

        //getting url
        String s = webView.getUrl();

        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(BrowserActivity.this, VKActivity.class);
       // intent.putExtra(URL, s);
        BrowserActivity.this.startActivity(intent);
    }

}
