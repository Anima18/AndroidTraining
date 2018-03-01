package com.example.chris.androidtraining.window_manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.chris.androidtraining.R;

/**
 * Created by Administrator on 2018/3/1.
 */

public class ViewLifeCycleActivity extends AppCompatActivity {
    private static final String TAG = "CustomView";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_life_cycle);

        Log.i(TAG, "activity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "activity onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "activity onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "activity onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "activity onDestroy");
    }
}
