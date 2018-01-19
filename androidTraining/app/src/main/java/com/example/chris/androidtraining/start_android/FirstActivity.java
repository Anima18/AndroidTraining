package com.example.chris.androidtraining.start_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.chris.androidtraining.R;

/**
 * Created by Admin on 2018/1/7.
 */

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_first);
    }

    public void toSecondActivity(View view) {
        EditText nameEt = findViewById(R.id.editText);
        String name = nameEt.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("NAME", name);
        startActivity(intent);
    }
}
