package com.example.arada;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DdayInsertActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dday_insert);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();
        final EditText editDate =findViewById(R.id.edit_date);
        final EditText editContent = findViewById(R.id.edit_content);
        final Button btn = findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DdayInsertActivity.this, DdayActivity.class);
                DdayInsertActivity.this.startActivity(intent);
                finish();
            }
        });
    }

}
