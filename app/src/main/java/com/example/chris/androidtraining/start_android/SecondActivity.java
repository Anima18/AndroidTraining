package com.example.chris.androidtraining.start_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.chris.androidtraining.R;

/**
 * Created by Admin on 2018/1/7.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");

        TextView messageTv = findViewById(R.id.secondAct_message_tv);
        messageTv.setText(name);
    }
}
