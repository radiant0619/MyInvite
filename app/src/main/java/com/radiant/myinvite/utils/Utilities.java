package com.radiant.myinvite.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

/**
 * Created by sakthivel on 11/05/2017.
 */

public class Utilities {
    public static boolean checkPermission(final Activity activity, final String permission, String tip, final int requestCode) {
        Log.i("Permission", "start");
        if (!(activity instanceof ActivityCompat.OnRequestPermissionsResultCallback)) {
            throw new IllegalStateException("The activity must implement ActivityCompat.OnRequestPermissionResultCallBack");

        }
        if (checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            Log.i("Permission", "Granted");
            return true;
        }
        Log.i("Permission Existing", String.valueOf(checkSelfPermission(activity, permission)));
        Log.i("Permission Raise", String.valueOf(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)));

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            new AlertDialog.Builder(activity)
                    .setMessage(tip)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            Log.i("Permission Raise", "False");
        }

        return false;
    }
}
