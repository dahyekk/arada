package com.example.arada;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DdayActivity extends AppCompatActivity {
    ListView list = findViewById(R.id.dday_list);
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dday);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        Button btn = findViewById(R.id.dday_insert);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DdayActivity.this, DdayInsertActivity.class);
                DdayActivity.this.startActivity(intent);
                finish();
            }
        });

    }

}
