package com.example.administrator.androidtraining_allowotherappmessage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 0;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                int number1 = intent.getIntExtra("NUMBER1", 0);
                if(number1 != 0) {
                    calculateAdd(intent);
                }else {
                    handleSendText(intent); // Handle text being sent
                }

            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // Handle multiple images being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }
    }

    public void selectImage(View view) {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == PICK_IMAGE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri fileUri = data.getData();
                imageView.setImageURI(fileUri);

            }
        }
    }

    public void calculateAdd(Intent intent) {
        int number1 = intent.getIntExtra("NUMBER1", 0);
        int number2 = intent.getIntExtra("NUMBER2", 0);
        int value = number1 + number2;
        // Create intent to deliver some kind of result data
        Intent result = new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri"));
        result.putExtra("ADD_RESULT", value);
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void handleSendText(Intent intent) {
        String textValue = intent.getStringExtra(Intent.EXTRA_TEXT);
        textView.setText(textValue);
    }

    public void handleSendImage(Intent intent) {
        Uri uriToImage = intent.getData();

        imageView.setImageURI(uriToImage);
    }

    public void handleSendMultipleImages(Intent intent) {

    }
}
