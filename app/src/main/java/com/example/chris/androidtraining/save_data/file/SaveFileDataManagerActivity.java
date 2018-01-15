package com.example.chris.androidtraining.save_data.file;

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

import chris.com.clistview.CListView;

/**
 * Created by Admin on 2018/1/7.
 */

public class SaveFileDataManagerActivity extends AppCompatActivity {
    private List<ActivityClass> activityClassList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_adapter);

        CListView listView = findViewById(R.id.listAct_lv);
        initData();
        listView.setData(activityClassList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActivityClass activityClass = activityClassList.get(i);
                if(activityClass.getActivityClass() == null) {
                    Toast.makeText(SaveFileDataManagerActivity.this, "没有实现", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(SaveFileDataManagerActivity.this, activityClass.getActivityClass());
                    startActivity(intent);
                }
            }
        });

    }

    private void initData() {
        activityClassList.add(new ActivityClass("Internal Storage", SaveInternalStorageActivity.class));
        activityClassList.add(new ActivityClass("External Storage Public Files", SaveExternalPublicStorageActivity.class));
        activityClassList.add(new ActivityClass("External Storage Private Files", null));
    }
}
