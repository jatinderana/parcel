package com.example.myapplication.ParcelHisotry;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.ParcelHistoryNewModel;
import com.example.myapplication.Model.Retrofit.history;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParcelBoxHistoryActivity extends AppCompatActivity {

    RecyclerView historyRV;
    TextView back;
    TextView titleTV;
    TextView noData;
    private List<String> historyList = new ArrayList<>();
    String Userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        setContentView(R.layout.activity_parcel_box_history);
        historyRV = findViewById(R.id.historyRV);
        back = findViewById(R.id.back);
        titleTV = findViewById(R.id.titleTV);
        noData = findViewById(R.id.noData);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Userid = preferences.getString("userid", "");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParcelBoxHistoryActivity.super.onBackPressed();
            }
        });

        historyRV.setLayoutManager(new LinearLayoutManager(this));
        ParcelCafe();
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTV.setTypeface(custom_font_azab);
            titleTV.setTextSize(24);
            noData.setTypeface(custom_font_azab);

        }
    }


    private void ParcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.loading_history));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final history task = new history();
        task.setUserid(Userid);
        task.setLang(languageValue);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPartyHistory(task).enqueue(new Callback<ParcelHistoryNewModel>() {
            @Override
            public void onResponse(Call<ParcelHistoryNewModel> call, Response<ParcelHistoryNewModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<ParcelHistoryNewModel.Data> parcel = response.body().getData();
                    if (parcel.size() > 0) {
                        historyRV.setAdapter(new ParcelHistoryAdapter(ParcelBoxHistoryActivity.this, parcel));
                    } else {
                        noData.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ParcelHistoryNewModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Response Failure  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
