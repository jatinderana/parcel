package com.example.myapplication.TrackPackage;

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
import com.example.myapplication.ParcelHisotry.ParcelHistoryModel;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodTrackingActivity extends AppCompatActivity {

    private TextView back;
    private TextView progressTV;
    private TextView dispatchTV;
    private TextView pickUpTV;
    private TextView deliverTV;
    private TextView titleTV;
    View firstStepV;
    View firstStepUV;
    View secondStepV;
    View secondStepUV;
    View fourStepV;
    View fourStepUV;
    RecyclerView itemRV;
    private String Userid = "";
    private FoodTrackingAdapter adapter;
    private String orderId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_food_tracking);
        back = findViewById(R.id.back);
        firstStepV = findViewById(R.id.firstStepV);
        firstStepUV = findViewById(R.id.firstStepUV);
        secondStepV = findViewById(R.id.secondStepV);
        secondStepUV = findViewById(R.id.secondStepUV);
        progressTV = findViewById(R.id.progressTV);
        dispatchTV = findViewById(R.id.dispatchTV);
        pickUpTV = findViewById(R.id.pickUpTV);
        deliverTV = findViewById(R.id.deliverTV);
        fourStepV = findViewById(R.id.fourStepV);
        fourStepUV = findViewById(R.id.fourStepUV);
        itemRV = findViewById(R.id.itemRV);
        titleTV = findViewById(R.id.titleTV);
        if (getIntent().hasExtra("orderId")) {
            orderId = getIntent().getStringExtra("orderId");
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Userid = preferences.getString("userid", "");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodTrackingActivity.super.onBackPressed();
            }
        });
        itemRV.setLayoutManager(new LinearLayoutManager(this));
        parcelCafe();


        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTV.setTypeface(custom_font);
            titleTV.setTextSize(24);
            dispatchTV.setTypeface(custom_font_azab);
            progressTV.setTypeface(custom_font_azab);
            pickUpTV.setTypeface(custom_font_azab);
            deliverTV.setTypeface(custom_font_azab);
        }
    }


    private void parcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.loading_history));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final history task = new history();
        task.setUserid(Userid);
        task.setOrderid(orderId);
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
                        if (response.body().getData().get(0).getItems().size() > 0) {
                            adapter = new FoodTrackingAdapter(FoodTrackingActivity.this, response.body().getData().get(0).getItems(), response.body().getData().get(0).getFinaltotal());
                            itemRV.setAdapter(adapter);
                            if (response.body().getData().get(0).getCstatus().equalsIgnoreCase("Pending")) {
                                firstStepV.setVisibility(View.INVISIBLE);
                                secondStepV.setVisibility(View.INVISIBLE);
                                fourStepV.setVisibility(View.INVISIBLE);
                                firstStepUV.setVisibility(View.VISIBLE);
                                secondStepUV.setVisibility(View.VISIBLE);
                                fourStepUV.setVisibility(View.VISIBLE);
                             } else if (response.body().getData().get(0).getCstatus().equalsIgnoreCase("Picked Up")) {
                              /*  firstStepV.setVisibility(View.VISIBLE);
                                secondStepV.setVisibility(View.INVISIBLE);
                                fourStepV.setVisibility(View.INVISIBLE);
                                firstStepUV.setVisibility(View.INVISIBLE);
                                secondStepUV.setVisibility(View.VISIBLE);
                                fourStepUV.setVisibility(View.VISIBLE);
                                progressTV.setText("In Progress( "+response.body().getData().get(0).getProgresststamp()+" )");
                           */     //pickUpTV.setText("Picked Up( "+response.body().getData().get(0).getPickedtstamp()+" )");


                                firstStepV.setVisibility(View.VISIBLE);
                                secondStepV.setVisibility(View.VISIBLE);
                                fourStepV.setVisibility(View.INVISIBLE);
                                firstStepUV.setVisibility(View.INVISIBLE);
                                secondStepUV.setVisibility(View.INVISIBLE);
                                fourStepUV.setVisibility(View.VISIBLE);
                                dispatchTV.setText(getString(R.string.ready_to_dispatch)+response.body().getData().get(0).getReadytstamp()+" )");
                                progressTV.setText(getString(R.string.in_progress_track_string)+response.body().getData().get(0).getProgresststamp()+" )");
                                pickUpTV.setText(getString(R.string.pick_up_track_string)+response.body().getData().get(0).getPickedtstamp()+" )");

                            } else if (response.body().getData().get(0).getCstatus().equalsIgnoreCase("Ready to Dispatch")) {
                                firstStepV.setVisibility(View.VISIBLE);
                                secondStepV.setVisibility(View.VISIBLE);
                                fourStepV.setVisibility(View.INVISIBLE);
                                firstStepUV.setVisibility(View.INVISIBLE);
                                secondStepUV.setVisibility(View.INVISIBLE);
                                fourStepUV.setVisibility(View.VISIBLE);
                                dispatchTV.setText(getString(R.string.ready_to_dispatch)+response.body().getData().get(0).getReadytstamp()+" )");
                                progressTV.setText(getString(R.string.in_progress_track_string)+response.body().getData().get(0).getProgresststamp()+" )");
                                pickUpTV.setText(getString(R.string.pick_up_track_string)+response.body().getData().get(0).getPickedtstamp()+" )");

                            } else if (response.body().getData().get(0).getCstatus().equalsIgnoreCase("Delivered") || response.body().getData().get(0).getCstatus().equalsIgnoreCase("Not Delivered")) {
                                firstStepV.setVisibility(View.VISIBLE);
                                secondStepV.setVisibility(View.VISIBLE);
                                fourStepV.setVisibility(View.VISIBLE);
                                firstStepUV.setVisibility(View.INVISIBLE);
                                secondStepUV.setVisibility(View.INVISIBLE);
                                fourStepUV.setVisibility(View.INVISIBLE);
                                deliverTV.setText(getString(R.string.not_delivered_string)+response.body().getData().get(0).getDeliveredtstamp()+" )");
                                progressTV.setText(getString(R.string.in_progress_track_string)+response.body().getData().get(0).getProgresststamp()+" )");
                                dispatchTV.setText(getString(R.string.ready_to_dispatch)+response.body().getData().get(0).getReadytstamp()+" )");
                                pickUpTV.setText(getString(R.string.pick_up_track_string)+response.body().getData().get(0).getPickedtstamp()+" )");
                            }
                        }
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
