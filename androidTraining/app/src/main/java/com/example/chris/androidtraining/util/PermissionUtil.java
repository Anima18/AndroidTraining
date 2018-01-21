package com.example.chris.androidtraining.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by Admin on 2018/1/20.
 */

public class PermissionUtil {

    public static void createPermission(final Activity context, final int requestCode, final String permissionCode) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context, permissionCode)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale( context, permissionCode)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("You need to allow access to Contacts")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //requestContactPermission();//确定后申请权限。
                                requestPermission(context, requestCode, permissionCode);
                            }
                        });

            } else {

                // No explanation needed, we can request the permission.

                requestPermission(context, requestCode, permissionCode);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    private static void requestPermission(Activity context, int requestCode, String permissionCode) {
        ActivityCompat.requestPermissions(context, new String[]{permissionCode}, requestCode);
    }

}
