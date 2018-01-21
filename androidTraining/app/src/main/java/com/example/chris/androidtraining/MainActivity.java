package com.example.chris.androidtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.chris.androidtraining.dynamic_ui_with_fragments.DynamicUIFragmentActivity;
import com.example.chris.androidtraining.intents.IntentManagerActivity;
import com.example.chris.androidtraining.save_data.SaveDataManagerActivity;
import com.example.chris.androidtraining.sharing.SharingManagerActivity;
import com.example.chris.androidtraining.start_android.FirstActivity;
import com.example.chris.androidtraining.support_different_devices.DeviceAdaptManagerActivity;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.CListView;

public class MainActivity extends AppCompatActivity {

    private List<ActivityClass> activityClassList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CListView listView = findViewById(R.id.listAct_lv);
        initData();
        listView.setData(activityClassList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActivityClass activityClass = activityClassList.get(i);
                if(activityClass.getActivityClass() == null) {
                    Toast.makeText(MainActivity.this, "没有实现", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, activityClass.getActivityClass());
                    startActivity(intent);
                }
            }
        });

    }

    private void initData() {
        activityClassList.add(new ActivityClass("Building your first App", FirstActivity.class));
        activityClassList.add(new ActivityClass("Supporting different devices", DeviceAdaptManagerActivity.class));
        activityClassList.add(new ActivityClass("Building a Dynamic UI with Fragments", DynamicUIFragmentActivity.class));
        activityClassList.add(new ActivityClass("Save data", SaveDataManagerActivity.class));
        activityClassList.add(new ActivityClass("Send intents", IntentManagerActivity.class));
        activityClassList.add(new ActivityClass("Sharing content", SharingManagerActivity.class));
    }
}
