package com.example.chris.androidtraining.save_data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.chris.androidtraining.ActivityClass;
import com.example.chris.androidtraining.R;

import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.CListView;

/**
 * Created by Admin on 2018/1/10.
 */

public class SavePreferenceActivity extends AppCompatActivity {

    private String inputValue = "";
    private List<ActivityClass> data = new ArrayList<>();
    private CListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listAct_lv);
        listView.setData(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_preference, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.spAct_action_add:
                /*new MaterialDialog.Builder(ActionErrorActivity.this)
                        .title("Action Error")
                        .content("操作错误，一般有3种情况：\n1. 载入数据错误，参考Empty State。\n2. 提交错误，一般显示SnackBar，包含错误信息和操作。\n3. 应用错误，显示dialog。")
                        .positiveText("agree")
                        .negativeText("disagree")
                        .show();*/

                new MaterialDialog.Builder(SavePreferenceActivity.this)
                        .title("新增记录")
                        .inputType(InputType.TYPE_CLASS_TEXT )
                        .input("输入需要保持的值", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                                inputValue = input.toString();
                                SharedPrefsUtil.putStringValue(SavePreferenceActivity.this, inputValue, inputValue);

                                listView.getData().add(new ActivityClass(SharedPrefsUtil.getStringValue(SavePreferenceActivity.this, inputValue, ""), null));
                            }
                        })
                        .positiveText("保存")
                        .negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {

                            }
                        })
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
