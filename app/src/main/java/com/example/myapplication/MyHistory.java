package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.HistoryAdapter;
import com.example.myapplication.Adapter.ParcelC;
import com.example.myapplication.Model.Retrofit.HistoryModel;
import com.example.myapplication.Model.Retrofit.Logins;
import com.example.myapplication.Model.Retrofit.Parcel;
import com.example.myapplication.Model.Retrofit.ParcelCafeModel;
import com.example.myapplication.Model.Retrofit.history;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyHistory extends AppCompatActivity {

    RecyclerView parcelmenu;
    TextView back, titlemenu;
    String Userid;
    LinearLayout mainLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);
        parcelmenu =  findViewById(R.id.history);
        back =  findViewById(R.id.back);
        titlemenu =  findViewById(R.id.titlemenu);
        mainLL =  findViewById(R.id.mainLL);
        parcelmenu.setNestedScrollingEnabled(false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Userid = preferences.getString("userid", "");
        ParcelCafe();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHistory.super.onBackPressed();
            }
        });

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);

        }

        if (languageValue.equalsIgnoreCase("ar")) {
          //  mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        } else {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

    }

    private void ParcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final history task = new history();
        task.setUserid(Userid);
        task.setLang(languageValue);
        if(languageValue.equalsIgnoreCase("ar"))
        {
            dialog.setMessage(getString(R.string.loading_history_ar));
        }else {
            dialog.setMessage(getString(R.string.loading_history));

        }

        dialog.show();
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetHistory(task).enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<history> parcel = response.body().getData();


                    GridLayoutManager latest = new GridLayoutManager(getApplicationContext(), 1);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    parcelmenu.setItemAnimator(new DefaultItemAnimator());
                    parcelmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    parcelmenu.setAdapter(new HistoryAdapter(parcel, R.layout.history, MyHistory.this));


                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Response Failure  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}