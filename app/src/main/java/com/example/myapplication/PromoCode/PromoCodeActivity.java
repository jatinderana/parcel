package com.example.myapplication.PromoCode;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.CalenderView;
import com.example.myapplication.Constants;
import com.example.myapplication.R;

import java.util.Locale;

public class PromoCodeActivity extends Activity {

    private String image,content,contentAr;
    ImageView imageView;
    TextView contentText;
    TextView back;
    TextView titlemenu;
    private String language = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_promo_code);
        contentText = findViewById(R.id.contentText);
        imageView = findViewById(R.id.image);
        titlemenu = findViewById(R.id.titlemenu);
        back = findViewById(R.id.back);
        if(getIntent().hasExtra("image"))
        {
            image = getIntent().getStringExtra("image");
            content = getIntent().getStringExtra("content");
            contentAr = getIntent().getStringExtra("contentAr");
        }
        Glide.with(this)
                .load(image)
                .placeholder(R.color.white)
                .thumbnail( 0.5f )
                .override( 200, 200 )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        /*Glide.with(this)
                .load(image)
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.color.white)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(imageView);*/
       language = String.valueOf(Constants.getCurrentLocale(this));
        if(language.equals("ar"))
        {
            try
            {
                contentText.setText(Html.fromHtml(contentAr));
                if(contentAr.equals(""))
                {
                    contentText.setText("No Content");
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }else
        {
            try
            {
                contentText.setText(Html.fromHtml(content));
                if(content.equals(""))
                {
                    //  contentText.setText("No Content");
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromoCodeActivity.super.onBackPressed();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PromoCodeActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            contentText.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
        }


    }



}
