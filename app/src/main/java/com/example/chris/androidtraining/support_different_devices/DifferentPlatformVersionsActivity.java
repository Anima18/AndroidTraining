package com.example.chris.androidtraining.support_different_devices;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.chris.androidtraining.R;

/**
 * Created by Admin on 2018/1/7.
 */

public class DifferentPlatformVersionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_versions);

        TextView textView = findViewById(R.id.versionAct_version_textView);
        String value = getResources().getString(R.string.current_version, Build.VERSION.SDK_INT);
        textView.setText(value);
    }
}
