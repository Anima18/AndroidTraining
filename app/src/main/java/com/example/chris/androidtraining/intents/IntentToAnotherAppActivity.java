package com.example.chris.androidtraining.intents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.example.chris.androidtraining.R;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.CListView;

/**
 * Created by Administrator on 2018/1/18.
 */

public class IntentToAnotherAppActivity extends AppCompatActivity {
    private List<IntentType> activityClassList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device_adapter);

        CListView listView = findViewById(R.id.listAct_lv);
        initData();
        listView.setData(activityClassList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentType intentType = activityClassList.get(i);
                switch (intentType.getId()) {
                    case 1:
                        call();
                        break;
                    case 2:
                        viewWebPage();
                        break;
                    case 3:
                        viewMap();
                        break;
                    case 4:
                        sendEmail();
                        break;
                }
            }
        });
    }

    private void call() {
        Uri number = Uri.parse("tel:5551234");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        if(isIntentSafe(callIntent)) {
            startActivity(callIntent);
        }
    }

    private void viewWebPage() {
        Uri webpage = Uri.parse("http://www.android.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        if(isIntentSafe(webIntent)) {
            startActivity(webIntent);
        }
    }

    private void viewMap() {
        // Map point based on address
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        // Or map point based on latitude/longitude
        // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        if(isIntentSafe(mapIntent)) {
            startActivity(mapIntent);
        }
    }

    private void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
        if(isIntentSafe(emailIntent)) {
            startActivity(Intent.createChooser(emailIntent, "请选择发送邮件应用"));
        }
    }

    private boolean isIntentSafe(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        return isIntentSafe;
    }

    private void initData() {
        activityClassList.add(new IntentType(1, "打电话"));
        activityClassList.add(new IntentType(2, "查看网页"));
        activityClassList.add(new IntentType(3, "查看地图"));
        activityClassList.add(new IntentType(4, "发送邮件"));
    }

    class IntentType {
        private int id;
        private String activityName;

        public IntentType(int id, String activityName) {
            this.id = id;
            this.activityName = activityName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }
    }
}
