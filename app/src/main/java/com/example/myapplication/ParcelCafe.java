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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Packages;
import com.example.myapplication.Adapter.ParcelC;
import com.example.myapplication.Model.Retrofit.Datum;
import com.example.myapplication.Model.Retrofit.Item;
import com.example.myapplication.Model.Retrofit.PackageModel;
import com.example.myapplication.Model.Retrofit.Parcel;
import com.example.myapplication.Model.Retrofit.ParcelCafeModel;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParcelCafe extends AppCompatActivity {
    RecyclerView parcelmenu;
    TextView back,titlemenu,comingTV;
    ImageView logoIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_cafe);
        parcelmenu=(RecyclerView)findViewById(R.id.parcelmenu);
        back=(TextView)findViewById(R.id.back);
        titlemenu=findViewById(R.id.titlemenu);
        comingTV=findViewById(R.id.comingTV);
        logoIV=findViewById(R.id.logoIV);

        parcelmenu.setHasFixedSize(false);
        ParcelCafe();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParcelCafe.super.onBackPressed();
            }
        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ParcelCafe.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            comingTV.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);

        }
    }

    private void ParcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetParcel(task).enqueue(new Callback<ParcelCafeModel>() {
            @Override
            public void onResponse(Call<ParcelCafeModel> call, Response<ParcelCafeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<Parcel> parcel=response.body().getData();
                    if(parcel.size()>0)
                    {
                        GridLayoutManager latest = new GridLayoutManager(getApplicationContext(),1);
                        latest.setOrientation(GridLayoutManager.VERTICAL);
                        parcelmenu.setItemAnimator(new DefaultItemAnimator());
                        parcelmenu.setLayoutManager(latest);
                        // recyclerView.setNestedScrollingEnabled(false);
                        parcelmenu.setAdapter(new ParcelC(parcel, R.layout.view_menu, ParcelCafe.this));


                    }
                  else
                    {
                        comingTV.setVisibility(View.VISIBLE);
                        logoIV.setVisibility(View.VISIBLE);
                    }
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ParcelCafeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failure"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
