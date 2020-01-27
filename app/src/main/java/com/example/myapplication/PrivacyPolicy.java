package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class PrivacyPolicy extends AppCompatActivity {
    ImageView back;
    TextView titleTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        WebView webView =  findViewById(R.id.webView);
        titleTV =  findViewById(R.id.titleTV);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("details", "");
        // String image = preferences.getString("image", "");
        // Postid=name;
        if(!name.equalsIgnoreCase(""))
        {

        }

        String languageValue = preferences.getString(Constants.SETLANG, "en");

        if(languageValue.equalsIgnoreCase("ar"))
        {
            webView.loadDataWithBaseURL(null, "<html dir=\"rtl\" lang=\"\"><body>" + name + "</body></html>" , "text/html", "utf-8", null);
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTV.setTypeface(custom_font_azab);
            titleTV.setTextSize(24);
        }else
        {
            webView.loadDataWithBaseURL(null, name , "text/html", "utf-8", null);

        }
        //viewtext.setText(Html.fromHtml(name));



        back = findViewById(R.id.imageBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivacyPolicy.super.onBackPressed();
            }
        });



    }

}
