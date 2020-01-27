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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.PartyBoxAdapter;
import com.example.myapplication.Model.Retrofit.ParcelAddModel;
import com.example.myapplication.Model.Retrofit.ParcelDataModel;
import com.example.myapplication.Model.Retrofit.PartyBoxModel;
import com.example.myapplication.Model.Retrofit.PartyModel;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyBox extends AppCompatActivity {
    RecyclerView parcelmenu;
    TextView back,titlemenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_box);
        parcelmenu=(RecyclerView)findViewById(R.id.parcelmenu);
        back=(TextView)findViewById(R.id.back);
        titlemenu=(TextView)findViewById(R.id.titlemenu);
        ParcelCafe();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PartyBox.super.onBackPressed();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(PartyBox.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font);
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
        userService.GetParty(task).enqueue(new Callback<ParcelDataModel>() {
            @Override
            public void onResponse(Call<ParcelDataModel> call, Response<ParcelDataModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<ParcelAddModel> parcel=response.body().getData();


                    GridLayoutManager latest = new GridLayoutManager(getApplicationContext(),1);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    parcelmenu.setItemAnimator(new DefaultItemAnimator());
                    parcelmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                  //  parcelmenu.setAdapter(new PartyBoxAdapter(parcel, R.layout.addcart, PartyBox.this));

                }
            }

            @Override
            public void onFailure(Call<ParcelDataModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failure"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
