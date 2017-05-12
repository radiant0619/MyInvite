package com.radiant.myinvite;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.radiant.myinvite.service.BackgroundSoundService;

/**
 * Created by sakthivel on 28/03/2017.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(getApplicationContext(),HomeInviteActivity.class);
//        startActivity(intent);
//        finish();
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 0);
        Intent svc = new Intent(this, BackgroundSoundService.class);
        startService(svc);
        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), HomeInviteActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 3 * 1000);

    }

}
