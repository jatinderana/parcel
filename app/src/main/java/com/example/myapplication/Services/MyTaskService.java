package com.example.myapplication.Services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.example.myapplication.Constants;

import java.util.Locale;

public class MyTaskService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {


        Locale myLocale = new Locale("en");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.SETLANG, "en");
        editor.apply();
        // Intent refresh = new Intent(this, Home.class);
        //  refresh.putExtra(currentLang, localeName);
        //startActivity(refresh);
        // setLocale("fr");

        System.out.println("onTaskRemoved called");
        super.onTaskRemoved(rootIntent);
        // Toast.makeText(this, "App destoryed", Toast.LENGTH_SHORT).show();
        //do something you want
        //stop service
        this.stopSelf();
    }
}