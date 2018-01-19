package com.example.administrator.androidtraining_allowotherappmessage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the intent that started this activity
        Intent intent = getIntent();
        if(intent != null) {
            Uri data = intent.getData();

            // Figure out what to do based on the intent type
            if (intent.getType().indexOf("image/") != -1) {
                // Handle intents with image data ...
            } else if (intent.getType().equals("text/plain")) {
                // Handle intents with text ...
                int number1 = intent.getIntExtra("NUMBER1", 0);
                int number2 = intent.getIntExtra("NUMBER2", 0);
                int value = number1 + number2;
                // Create intent to deliver some kind of result data
                Intent result = new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri"));
                result.putExtra("ADD_RESULT", value);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        }

    }
}
