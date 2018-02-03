package com.github.alexvishneuski.mywebview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String vkurl = "https://oauth.vk.com/authorize?client_id=6261957&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,messages&response_type=token&v=5.69&state=123456";

    Button goToAppButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToAppButton = findViewById(R.id.go_to_app_button);

        goToAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateAccessTokenFromSharedPreferences();
            }
        });
    }

    //fixme must return result from browser activity
    //fixme must be renamed getAccessTokenFromVKApi
    //Intent = new Intent(this, BrowserActivity);
    //startActivityForResult();
    //newAccessToken = intent.getParcellable;
    private void goToBrowserActivity() {
        Uri uri = Uri.parse(vkurl);
        Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
        intent.setData(uri);
        //implicit transition (by action)
        startActivity(intent);
    }

    private void validateAccessTokenFromSharedPreferences() {
        VKAccessToken accessToken = getAccessTokenFromSharedPreferences();

       // if (isExpired(accessToken) || accessToken == null) {
            refreshAccessToken();
       // }

       // goToVKActivity();
    }

    private void refreshAccessToken() {
        VKAccessToken newAccessToken = getNewAccessToken();

        goToBrowserActivity();

        replaceAccessTokenInSharedPreferences(newAccessToken);
    }

    private void replaceAccessTokenInSharedPreferences(VKAccessToken pNewAccessToken) {
        //fixme
        VKAccessToken oldAccessToken = getAccessTokenFromSharedPreferences();

        removeAccessTokenFromSharedPreferences(oldAccessToken);

        saveAccesTokenToSharedPreferences(pNewAccessToken);
    }

    private void removeAccessTokenFromSharedPreferences(VKAccessToken pOldAccessToken) {
        //fixme
    }

    private VKAccessToken getNewAccessToken() {
        //fixme
        return new VKAccessToken();
    }

    private void goToVKActivity() {

        //fixme
        //to VKActivity transition
    }

    private VKAccessToken getAccessTokenFromSharedPreferences() {
        //fixme
        return new VKAccessToken();
    }

    private void saveAccesTokenToSharedPreferences(VKAccessToken pVKAccessToken) {
        //fixme
    }

    public boolean isExpired(VKAccessToken pVKAccessToken) {
        int expiresIn = pVKAccessToken.getExpiresIn();
        long created = pVKAccessToken.getCreated();

        return expiresIn > 0 && expiresIn * 1000 + created < System.currentTimeMillis();
    }
}
