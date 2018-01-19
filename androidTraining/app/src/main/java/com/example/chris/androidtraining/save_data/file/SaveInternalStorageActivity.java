package com.example.chris.androidtraining.save_data.file;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chris.androidtraining.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Admin on 2018/1/11.
 */

public class SaveInternalStorageActivity extends AppCompatActivity {

    private static final String FILE_NAME = "InternalStorage";

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_internal);
        editText = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
    }

    public void saveData(View view) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);// 获得文件输出流
            fos.write(editText.getText().toString().getBytes());// 保存用户名和密码
            fos.flush();// 清除缓存
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();// 关闭文件输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showData(View view) {
        FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = openFileInput(FILE_NAME);// 获得文件输入流
            buffer = new byte[fis.available()];// 定义保存数据的数组
            fis.read(buffer);// 从输入流中读取数据
            textView.setText(new String(buffer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();// 关闭文件输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
