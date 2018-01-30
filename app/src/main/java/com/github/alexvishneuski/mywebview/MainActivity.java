package com.github.alexvishneuski.mywebview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String vkurl = "https://oauth.vk.com/authorize?client_id=6261957&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,messages&response_type=token&v=5.69&state=123456";

    Button webButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webButton = findViewById(R.id.web_button);

        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(vkurl);
                Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
                intent.setData(uri);
                //implicit transition (by action)
                startActivity(intent);
            }
        });
    }
}
