package com.example.arada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent= new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
