package com.example.chris.androidtraining.save_data.db;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.chris.androidtraining.ActivityClass;
import com.example.chris.androidtraining.R;
import com.example.chris.androidtraining.save_data.SavePreferenceActivity;
import com.example.chris.androidtraining.save_data.SharedPrefsUtil;
import com.example.chris.androidtraining.save_data.db.database.DBManager;
import com.example.chris.androidtraining.save_data.db.database.student.Student;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.CListView;

/**
 * Created by Admin on 2018/1/16.
 * https://zhuanlan.zhihu.com/p/29472803
 */

public class DBActionActivity extends AppCompatActivity {

    private List<Student> dataLists = new ArrayList<>();
    private CListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_action);

        listView = findViewById(R.id.listAct_lv);
        listView.setData(dataLists);
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_db_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spAct_action_add:
                String id = listView.getData().size() + 1 +"";
                DBManager.getInstance(DBActionActivity.this).getStudentManager().insert(new Student(id, "Hello"+id, id));


                return true;
            case R.id.spAct_action_search:
                initData();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initData() {
        //listView.getData().clear();
        List<Student> students = DBManager.getInstance(DBActionActivity.this).getStudentManager().query();
        listView.setData(students);

    }
}
