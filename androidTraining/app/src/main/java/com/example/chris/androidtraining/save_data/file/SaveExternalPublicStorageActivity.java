package com.example.chris.androidtraining.save_data.file;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.androidtraining.R;
import com.example.chris.androidtraining.util.PermissionUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Admin on 2018/1/11.
 * 这些文件对与用户与其他app来说是public的，当用户卸载我们的app时，这些文件应该保留
 */

public class SaveExternalPublicStorageActivity extends AppCompatActivity {
    private final static int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final String TAG = "SaveExternalPublic";
    private static final String FILE_DIR = "android_training_ExternalPublic";
    private static final String FILE_NAME = "ExternalPublicStorage.txt";

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_internal);
        editText = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        PermissionUtil.createPermission(this, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void saveData(View view) {
        File dir = getAlbumStorageDir();
        String fileName = dir.getAbsolutePath()+File.separator+FILE_NAME;
        Log.i(TAG, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName, true);// 获得文件输出流
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
        File dir = getAlbumStorageDir();
        String fileName = dir.getAbsolutePath()+File.separator+FILE_NAME;
        Log.i(TAG, fileName);
        FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = new FileInputStream(fileName);// 获得文件输入流
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

    public File getAlbumStorageDir() {
        // Get the directory for the user's public pictures directory.
        /*File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), FILE_DIR);*/
        File file = new File(Environment.getExternalStorageDirectory(), FILE_DIR);
        if (!file.mkdirs()) {

        }
        return file;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "ACCESS_FINE_LOCATION is OK", Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "ACCESS_FINE_LOCATION is denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
