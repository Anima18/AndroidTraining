package com.example.chris.androidtraining.sharing.sharing_simple_data;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.ImageView;

import com.example.chris.androidtraining.R;

/**
 * Created by Admin on 2018/1/21.
 */

public class SharingImageActivtity extends AppCompatActivity {

    private static int PICK_IMAGE_REQUEST = 1;

    private ShareActionProvider mShareActionProvider;
    private ImageView imageView;
    private Uri uriToImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_image);

        imageView = findViewById(R.id.imageView4);
    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_share2:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                //shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage.toString());
                shareIntent.setDataAndType(
                        uriToImage,
                        getContentResolver().getType(uriToImage));
                shareIntent.addFlags(
                        Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(shareIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/


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
                /*String mimeType = getContentResolver().getType(fileUri);
                try {
                    File file = new File(new URI(fileUri.toString()));
                    uriToImage = FileProvider.getUriForFile(
                            SharingImageActivtity.this,
                            "com.example.chris.androidtraining.fileprovider",
                            file);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}
