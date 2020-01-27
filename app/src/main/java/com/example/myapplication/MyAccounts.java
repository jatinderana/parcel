package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Retrofit.Myprofile;
import com.example.myapplication.Model.Retrofit.MyprofileModel;
import com.example.myapplication.ParcelHisotry.ParcelBoxHistoryActivity;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccounts extends AppCompatActivity {
    LinearLayout linearLayout, history, subcribe, logout;
    TextView back;
    TextView tv_date;
    TextView subHistory;
    TextView subsTV;
    TextView parcelTV;
    TextView logoutTV;
    TextView titleTV;
    LinearLayout mainLL;
    LinearLayout parcelBoxTV;
    private String Userid;
    private String savedLocation = "Zirakpur";
    private String savedLatitude = "0.00";
    private String savedLongitude = "0.00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accounts);
        linearLayout = findViewById(R.id.profile);
        history = findViewById(R.id.history);
        mainLL = findViewById(R.id.mainLL);
        subcribe = findViewById(R.id.subcribe);
        logout = findViewById(R.id.logouts);
        back = findViewById(R.id.back);
        tv_date = findViewById(R.id.tv_date);
        logoutTV = findViewById(R.id.logoutTV);
        parcelTV = findViewById(R.id.parcelBoxTV);
        subsTV = findViewById(R.id.subsTV);
        subHistory = findViewById(R.id.subHistory);
        parcelBoxTV = findViewById(R.id.tv_title3);
        titleTV = findViewById(R.id.titleTV);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Userid = preferences.getString("userid", "");

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSavedLocation();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAccounts.super.onBackPressed();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyHistory.class);
                startActivity(intent);
            }
        });

        subcribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyAccountHome.class);
                startActivity(intent);
            }
        });

        parcelBoxTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ParcelBoxHistoryActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTV.setTypeface(custom_font_azab);
            titleTV.setTextSize(24);
            tv_date.setTypeface(custom_font_azab);
            subHistory.setTypeface(custom_font_azab);
            subsTV.setTypeface(custom_font_azab);
            parcelTV.setTypeface(custom_font_azab);
            logoutTV.setTypeface(custom_font_azab);
        } else {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/chauregular.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "fonts/ptsansregular.ttf");
            titleTV.setTypeface(custom_font);
            titleTV.setTextSize(24);
            tv_date.setTypeface(custom_font_azab);
            subHistory.setTypeface(custom_font_azab);
            subsTV.setTypeface(custom_font_azab);
            parcelTV.setTypeface(custom_font_azab);
            logoutTV.setTypeface(custom_font_azab);
        }



        if (languageValue.equalsIgnoreCase("ar")) {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        } else {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

    }


    void getSavedLocation() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        final Myprofile task = new Myprofile();
        task.setUserid(Userid);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetProfile(task).enqueue(new Callback<MyprofileModel>() {
            @Override
            public void onResponse(Call<MyprofileModel> call, Response<MyprofileModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    savedLocation = response.body().getData().get(0).getLocation();
                    savedLatitude = response.body().getData().get(0).getLatitude();
                    savedLongitude = response.body().getData().get(0).getLongitude();
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude))).title("Saved Location"));
                    Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                    intent.putExtra("savedLongitude", savedLongitude);
                    intent.putExtra("savedLatitude", savedLatitude);
                    intent.putExtra("savedLocation", savedLocation);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<MyprofileModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }
}
