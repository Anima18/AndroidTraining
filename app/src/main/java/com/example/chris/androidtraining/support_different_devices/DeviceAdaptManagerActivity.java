package com.example.chris.androidtraining.support_different_devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.chris.androidtraining.ActivityClass;
import com.example.chris.androidtraining.R;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.UTListView;

/**
 * Created by Admin on 2018/1/7.
 */

public class DeviceAdaptManagerActivity extends AppCompatActivity {
    private List<ActivityClass> activityClassList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_adapter);

        UTListView listView = findViewById(R.id.listAct_lv);
        initData();
        listView.setData(activityClassList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActivityClass activityClass = activityClassList.get(i);
                if(activityClass.getActivityClass() == null) {
                    Toast.makeText(DeviceAdaptManagerActivity.this, "没有实现", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(DeviceAdaptManagerActivity.this, activityClass.getActivityClass());
                    startActivity(intent);
                }
            }
        });

    }

    private void initData() {
        activityClassList.add(new ActivityClass("Supporting Different Languages and Cultures", DifferentLanguagesActivity.class));
        activityClassList.add(new ActivityClass("Supporting Different Screens", DifferentScreenActivity.class));
        activityClassList.add(new ActivityClass("Supporting Different Platform Versions", DifferentPlatformVersionsActivity.class));
    }
}
