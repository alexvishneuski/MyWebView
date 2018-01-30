package com.github.alexvishneuski.mywebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class VKActivity extends AppCompatActivity {

    Button webButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk);
        webButton = findViewById(R.id.web_button);
    }
}
