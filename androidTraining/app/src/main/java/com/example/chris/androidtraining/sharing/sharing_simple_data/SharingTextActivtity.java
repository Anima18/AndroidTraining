package com.example.chris.androidtraining.sharing.sharing_simple_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chris.androidtraining.R;

/**
 * Created by Admin on 2018/1/21.
 */

public class SharingTextActivtity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;
    private String textValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_text);

        TextView textView = findViewById(R.id.textView5);
        textValue = textView.getText().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider=(ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, textValue);
        sendIntent.setType("text/plain");
        setShareIntent(sendIntent);

        return super.onCreateOptionsMenu(menu);
    }

    private void setShareIntent(Intent shareIntent) {
        if(mShareActionProvider!=null)
        {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}
