package com.example.myapplication.CardScreen;

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
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.Model.Retrofit.PasswordChangeModel;
import com.example.myapplication.Model.Retrofit.checkout;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardActivity extends AppCompatActivity implements CardAdapter.InterfaceCard {

    private TextView back;
    RecyclerView cardRV;
    private String userId = "";
    private CardAdapter adapter;
    private List<GetCardModel> list = new ArrayList<>();
    private TextView noCard;
    private TextView titlemenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_card);
        back = findViewById(R.id.back);
        cardRV = findViewById(R.id.cardRV);
        noCard = findViewById(R.id.no_card);
        titlemenu = findViewById(R.id.titlemenu);
        userId = getIntent().getStringExtra("userId");
        showCards(userId);
        cardRV.setLayoutManager(new LinearLayoutManager(CardActivity.this));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardActivity.this.onBackPressed();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);

        }
    }


    private void showCards(String userId) {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final checkout task = new checkout();
        task.setUserid(userId);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.showCards(task).enqueue(new Callback<List<GetCardModel>>() {
            @Override
            public void onResponse(Call<List<GetCardModel>> call, Response<List<GetCardModel>> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().size() > 0) {
                        list = response.body();
                        adapter = new CardAdapter(CardActivity.this, list);
                        cardRV.setAdapter(adapter);


                    } else {
                        Toast.makeText(CardActivity.this, "No cards to show", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GetCardModel>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(CardActivity.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void deleteCard(String id, final int i) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final checkout task = new checkout();
        task.setId(id);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.deleteCardApi(task).enqueue(new Callback<PasswordChangeModel>() {
            @Override
            public void onResponse(Call<PasswordChangeModel> call, Response<PasswordChangeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus() == 200) {
                        list.remove(i);
                        if (list.size() <= 0) {
                            noCard.setVisibility(View.VISIBLE);
                        }
                        adapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<PasswordChangeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(CardActivity.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }
}
