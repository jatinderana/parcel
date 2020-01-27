package com.example.myapplication.AddressSelect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Constants;
import com.example.myapplication.GiftFriend.GiftFriendActivity;
import com.example.myapplication.Model.Retrofit.ChangeAddressModel;
import com.example.myapplication.Model.Retrofit.DeliveryModel;
import com.example.myapplication.Model.Retrofit.GetAddressModel;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.example.myapplication.Retrofit.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAddressActivity extends Activity implements ShowAddressAdapter.ShowAddressListener {

    private TextView titleAddress, addAddress;
    RecyclerView addressRV;
    private String userId = "";
    private String subscriptionId = "";
    private TextView back;
    private ShowAddressAdapter adapter;
    private String addressLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        titleAddress = findViewById(R.id.titleAddress);
        addAddress = findViewById(R.id.addAddress);
        addressRV = findViewById(R.id.addressRV);
        back = (TextView) findViewById(R.id.back);
        if (getIntent().hasExtra("subscriptionId")) {
            subscriptionId = getIntent().getStringExtra("subscriptionId");
            userId = getIntent().getStringExtra("userId");
        }
        addressRV.setLayoutManager(new LinearLayoutManager(this));

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAddressActivity.this, AddAddressActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("subscriptionId", subscriptionId);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectAddressActivity.super.onBackPressed();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        addressLimit = preferences.getString("addressLimit", "0");



        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleAddress.setTypeface(custom_font_azab);
            titleAddress.setTextSize(24);
        }
    }

    private void getSavedAddress() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SelectAddressActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final DeliveryModel task = new DeliveryModel();
        task.setUseriId(userId);
        task.setSubscriptionid(subscriptionId);
        task.setLang(languageValue);
        Log.e("DeliveryModel---","DeliveryModel---"+userId+"----"+subscriptionId+"----"+languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetAddress(task).enqueue(new Callback<GetAddressModel>() {
            @Override
            public void onResponse(Call<GetAddressModel> call, Response<GetAddressModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    adapter = new ShowAddressAdapter(SelectAddressActivity.this, response.body().getData(), addressLimit);
                    addressRV.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<GetAddressModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSavedAddress();
    }


    @Override
    public void onShowaddress(String id) {
        getChangeAddress(id);
    }

    private void getChangeAddress(String id) {

        Log.e("DeliveryModel---","DeliveryModel---2---");
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SelectAddressActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final ChangeAddressModel task = new ChangeAddressModel();
        task.setUserid(userId);
        task.setAddressId(id);
        task.setCtypeId(subscriptionId);
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.ChangeAddress(task).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    getSavedAddress();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }


}
