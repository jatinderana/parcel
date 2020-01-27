package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SaplashScreen extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_saplash_screen);
        // gps();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //permission to access location
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Android M Permission check
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {

                    // MapsInitializer.initialize(getApplicationContext());
                }
            }
        }, 500);

        imageView =  findViewById(R.id.logo);

        Thread thread = new Thread() {


            public void run() {
                try {
                    animate();
                    Thread.sleep(6000);
                } catch (Exception e) {
                    // Toast.makeText(SaplashScreen.this, "hello", Toast.LENGTH_SHORT).show();
                } finally {
                    Intent language = new Intent(SaplashScreen.this, MainActivity.class);
                    startActivity(language);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                }
            }


        };
        thread.start();
    }

    public void animate() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        imageView.setAnimation(animation);
    }

}
