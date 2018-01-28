package com.example.chris.androidtraining.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.chris.androidtraining.ActivityClass;
import com.example.chris.androidtraining.R;
import com.example.chris.androidtraining.sharing.sharing_file_data.SharingFileActivity;
import com.example.chris.androidtraining.sharing.sharing_simple_data.SharingSimpleDataManagerActivity;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.CListView;

/**
 * Created by Admin on 2018/1/20.
 */

public class SharingManagerActivity extends AppCompatActivity {
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
                    Toast.makeText(SharingManagerActivity.this, "没有实现", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(SharingManagerActivity.this, activityClass.getActivityClass());
                    startActivity(intent);
                }
            }
        });

    }

    private void initData() {
        activityClassList.add(new ActivityClass("Sharing Simple Data", SharingSimpleDataManagerActivity.class));
        activityClassList.add(new ActivityClass("Sharing Files", SharingFileActivity.class));
        activityClassList.add(new ActivityClass("Sharing Files with NFC", null));
    }
}
