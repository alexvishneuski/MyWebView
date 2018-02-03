package com.github.alexvishneuski.mywebview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static com.github.alexvishneuski.mywebview.VKAccessToken.ACCESS_TOKEN;
import static com.github.alexvishneuski.mywebview.VKAccessToken.CREATED;
import static com.github.alexvishneuski.mywebview.VKAccessToken.EXPIRES_IN;


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

        if (data != null) {
            Log.d(TAG, "onCreate: data " + data.toString());
            webView.loadUrl(data.toString());
        }

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

       /* @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d(TAG, "onPageStarted() called with: url = [" + url + "]");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d(TAG, "onPageFinished() called with: url = [" + url + "]");
        }*/
    }

    public void onLoaded() {
        Log.d(TAG, "onLoaded: " + mRedirectUrl);
        Toast.makeText(this, mRedirectUrl, Toast.LENGTH_LONG).show();

        if (mRedirectUrl.startsWith("https://oauth.vk.com/blank.html")) {
            VKAccessToken vkAccessToken = tokenFromUrlString(mRedirectUrl);
            String accessToken = vkAccessToken.getAccessToken();
            int expiresIn = vkAccessToken.getExpiresIn();
            long created = vkAccessToken.getCreated();

            @SuppressLint("DefaultLocale")
            String msg = String.format("this is the access Token: %s. It was created %d and it will expires in %d secs", accessToken, created, expiresIn);
            Log.d(TAG, "onLoaded: " + msg);
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    public static VKAccessToken tokenFromUrlString(String urlString) {
        if (urlString == null)
            return null;
        Map<String, String> parameters = explodeRedirectUrl(urlString);

        return tokenFromRedirectParameters(parameters);
    }

    public static Map<String, String> explodeRedirectUrl(String pRedirectUrl) {
        String[] url = pRedirectUrl.split("#");
        String[] keyValuePairs = url[1].split("&");
        HashMap<String, String> parameters = new HashMap<>(keyValuePairs.length);

        for (String keyValueString : keyValuePairs) {
            String[] keyValueArray = keyValueString.split("=");
            parameters.put(keyValueArray[0], keyValueArray[1]);
        }

        return parameters;
    }

    public static VKAccessToken tokenFromRedirectParameters(Map<String, String> parameters) {
        if (parameters == null || parameters.size() == 0)
            return null;
        VKAccessToken token = new VKAccessToken();
        try {
            token.setAccessToken(parameters.get(ACCESS_TOKEN));
            token.setExpiresIn(Integer.parseInt(parameters.get(EXPIRES_IN)));

            if (parameters.containsKey(CREATED)) {
                token.setCreated(Long.parseLong(parameters.get(CREATED)));
            } else {
                token.setCreated(System.currentTimeMillis());
            }

            return token;
        } catch (Exception e) {

            return null;
        }
    }
}
