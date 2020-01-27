package com.example.myapplication.FaqPage;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Constants;
import com.example.myapplication.ContactUs;
import com.example.myapplication.Model.Retrofit.FaqDataModel;
import com.example.myapplication.Model.Retrofit.Item;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.example.myapplication.Retrofit.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqActivtiy extends AppCompatActivity {

    private ImageView back;
    RecyclerView faqRL;
    TextView titleTV;
    private String Userid = "";
    private String dataValue = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_faq_activtiy);

        back = findViewById(R.id.imageBack);
        faqRL = findViewById(R.id.faqRL);
        titleTV = findViewById(R.id.titleTV);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaqActivtiy.super.onBackPressed();
            }
        });
        if (getIntent().hasExtra("pageFrom")) {
            dataValue = getIntent().getStringExtra("pageFrom");
        }
        getFaqData(dataValue);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FaqActivtiy.this);

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTV.setTypeface(custom_font_azab);
            titleTV.setTextSize(24);

            /*first.setTextSize(20);
            last.setTextSize(20);
            input_email.setTextSize(20);
            input_phone.setTextSize(20);
            subject.setTextSize(20);
            messages.setTextSize(20);*/
        }
    }


    private void getFaqData(String data) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FaqActivtiy.this);
        Userid = preferences.getString("userid", "");
        final ProgressDialog dialog = new ProgressDialog(FaqActivtiy.this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FaqActivtiy.this);
        String languageValue = pref.getString(Constants.SETLANG, "en");
        final Item task = new Item();
        task.setCategory(data);
        task.setLang(languageValue);
        Log.e("data====","data---"+data+"-----"+languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.getFaqData(task).enqueue(new Callback<FaqDataModel>() {
            @Override
            public void onResponse(Call<FaqDataModel> call, Response<FaqDataModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    String resp = response.body().toString();

                    if (!resp.isEmpty()) {
                        faqRL.setLayoutManager(new LinearLayoutManager(FaqActivtiy.this));
                        faqRL.setAdapter(new FaqAdapter(FaqActivtiy.this, response.body().getData()));
                    }

                }
            }

            @Override
            public void onFailure(Call<FaqDataModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(FaqActivtiy.this, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
