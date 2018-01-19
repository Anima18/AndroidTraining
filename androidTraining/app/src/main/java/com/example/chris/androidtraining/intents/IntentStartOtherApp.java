package com.example.chris.androidtraining.intents;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.androidtraining.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 * 向App2发送number1和number2，app2会接收这2个数字，并发2个数字相加返回
 */

public class IntentStartOtherApp extends AppCompatActivity {

    private static final int ADD_REQUEST = 1;

    private EditText number1Et;
    private EditText number2Et;
    private TextView resultTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_start_otherapp);

        number1Et = findViewById(R.id.editText3);
        number2Et = findViewById(R.id.editText4);
        resultTv = findViewById(R.id.textView4);
    }

    public void resultForAdd(View view) {
        Log.i("IntentStartOtherApp", "resultForAdd");
        String number1 = number1Et.getText().toString();
        String number2= number2Et.getText().toString();
        if(TextUtils.isEmpty(number1)) {
            Toast.makeText(this, "number1不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(number2)) {
            Toast.makeText(this, "number2不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        int number1Value = Integer.parseInt(number1);
        int number2Value = Integer.parseInt(number2);

        Intent addIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        addIntent.setType("text/plain");
        addIntent.putExtra("NUMBER1",number1Value); // recipients
        addIntent.putExtra("NUMBER2",number2Value); // recipients

        if(isIntentSafe(addIntent)) {
            startActivityForResult(Intent.createChooser(addIntent, "请选择计算应用"), ADD_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == ADD_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact

                int result = data.getIntExtra("ADD_RESULT", 0);
                // Do something with the phone number...
                resultTv.setText(result+"");
            }
        }
    }
    private boolean isIntentSafe(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        return isIntentSafe;
    }
}
