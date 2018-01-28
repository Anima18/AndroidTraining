package com.example.chris.androidtraining.sharing.sharing_file_data;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.example.chris.androidtraining.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import chris.com.clistview.CListView;

/**
 * Created by Admin on 2018/1/28.
 */

public class SharingFileActivity extends AppCompatActivity {

    private final static String FILE_DIR = "DCIM/Camera";
    private Intent mResultIntent;
    private File[] files;
    List<FileInfo> fileInfoList = new ArrayList<>();
    private Uri fileUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_provider_select);

        mResultIntent = new Intent("com.example.chris.androidtraining.ACTION_RETURN_FILE");

        files = getFileStorageDir().listFiles();
        for(File file : files) {
            fileInfoList.add(new FileInfo(file.getAbsolutePath(), file.getName()));
        }

        CListView listView = findViewById(R.id.listAct_lv);
        listView.setData(fileInfoList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File requestFile = files[i];
                /*
                 * Most file-related method calls need to be in
                 * try-catch blocks.
                 */
                // Use the FileProvider to get a content URI
                try {
                   fileUri = FileProvider.getUriForFile(
                            SharingFileActivity.this,
                            "com.example.chris.androidtraining.fileprovider",
                            requestFile);
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    //shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage.toString());
                    shareIntent.setDataAndType(
                            fileUri,
                            getContentResolver().getType(fileUri));
                    shareIntent.addFlags(
                            Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(shareIntent);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public File getFileStorageDir() {
        // Get the directory for the user's public pictures directory.
        /*File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), FILE_DIR);*/
        File file = new File(Environment.getExternalStorageDirectory(), FILE_DIR);
        if (!file.mkdirs()) {

        }
        return file;
    }

}
