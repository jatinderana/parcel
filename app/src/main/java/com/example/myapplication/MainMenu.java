package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.HomeMenuAdapter;
import com.example.myapplication.Model.Retrofit.PassValue;
import com.example.myapplication.Model.Retrofit.homemenu;
import com.example.myapplication.Model.Retrofit.homemenumodel;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenu extends AppCompatActivity {
    RecyclerView parcelmenu;
    TextView back, titlemenu, replaceBT, closeBT;
    String Duration;
    LinearLayout scrollView;
    private String languageValue = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        parcelmenu =  findViewById(R.id.parcelmenu);
        back =  findViewById(R.id.back);
        titlemenu =  findViewById(R.id.titlemenu);
        replaceBT =  findViewById(R.id.replaceBT);
        closeBT =  findViewById(R.id.closeBT);
        scrollView =  findViewById(R.id.scrollView);

        parcelmenu.setNestedScrollingEnabled(false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font);
            titlemenu.setTextSize(24);


        }

        if (languageValue.equalsIgnoreCase("ar")) {
            scrollView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            scrollView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        Duration = preferences.getString("time", "");
       // Toast.makeText(getApplicationContext(), "" + Duration, Toast.LENGTH_LONG).show();
        ParcelCafe();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenu.super.onBackPressed();
            }
        });
    }


    private void ParcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        languageValue = preferences.getString(Constants.SETLANG, "en");
        final PassValue task = new PassValue();
        task.setSdate("");
        task.setDuration(Duration);
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetHomeMenu(task).enqueue(new Callback<homemenumodel>() {
            @Override
            public void onResponse(Call<homemenumodel> call, Response<homemenumodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<homemenu> menu = response.body().getData();


                    GridLayoutManager latest = new GridLayoutManager(getApplicationContext(), 1);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    parcelmenu.setItemAnimator(new DefaultItemAnimator());
                    parcelmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    parcelmenu.setAdapter(new HomeMenuAdapter(menu, R.layout.menu_list, getApplicationContext()));


                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<homemenumodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
