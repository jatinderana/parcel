package com.example.myapplication.FoodCart;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CheckoutPage;
import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.BucketAddModel;
import com.example.myapplication.Model.Retrofit.Myprofile;
import com.example.myapplication.Model.Retrofit.MyprofileModel;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodCartActivity extends AppCompatActivity implements FoodCartAdapter.CartInterface {

    RecyclerView parcelmenu;
    private TextView itemValueTV, priceValueTV, payNowTV, back, titlemenu, itemTV, priceTV, noData;
    private List<BucketAddModel> list;
    RelativeLayout mainBar;
    RelativeLayout mainRL;
    private FoodCartAdapter adapter;
    private String noOfItem, price = "";
    Double priceVal = 0.00;
    private int count = 0;
    private String Userid = "";
    private String savedLocation = "Zirakpur";
    private String savedLatitude = "0.00";
    private String userId = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    private String company = "";
    private String idList = "";
    private String priceList = "";
    private List<String> qtyList = new ArrayList();

    CheckBox termCB;
    private String languageValue = "en";
    private String afterTime = "24";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        setContentView(R.layout.activity_food_cart);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        termCB = findViewById(R.id.termCB);
        noData = findViewById(R.id.noData);
        Userid = preferences.getString("userid", "");
        parcelmenu = findViewById(R.id.parcelmenu);
        itemValueTV = findViewById(R.id.itemValueTV);
        mainBar = findViewById(R.id.mainBar);
        mainRL = findViewById(R.id.mainRL);
        back = findViewById(R.id.back);
        titlemenu = findViewById(R.id.titlemenu);
        itemTV = findViewById(R.id.itemTV);
        priceTV = findViewById(R.id.priceTV);
        priceValueTV = findViewById(R.id.priceValueTV);
        payNowTV = findViewById(R.id.payNowTV);
        getSavedLocation();
        if(getIntent().hasExtra("list")) {
            list = getIntent().getParcelableArrayListExtra("list");
            noOfItem = getIntent().getStringExtra("noOfItem");
            idList = getIntent().getStringExtra("idList");
            priceList = getIntent().getStringExtra("priceList");
            price = getIntent().getStringExtra("price");
            userId = getIntent().getStringExtra("userId");
            count = Integer.parseInt(noOfItem);
            priceVal = Double.parseDouble(price);
            afterTime = getIntent().getStringExtra("afterTime");
        }


        if (!termCB.isChecked()) {
            payNowTV.setAlpha(0.4f);
        }

       /* if(list.size()>0)
        {
            HashSet<BucketAddModel> hashSet = new HashSet<>();
            hashSet.addAll(list);
            list.clear();
            list.addAll(hashSet);
        }*/
        termCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payNowTV.setAlpha(1f);
                } else {
                    payNowTV.setAlpha(0.4f);
                }
            }
        });

        languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            priceTV.setTypeface(custom_font_azab);
            priceValueTV.setTypeface(custom_font_azab);
            payNowTV.setTypeface(custom_font_azab);
            itemValueTV.setTypeface(custom_font_azab);
            itemTV.setTypeface(custom_font_azab);
        }

        if (languageValue.equalsIgnoreCase("ar")) {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        itemValueTV.setText(noOfItem);
        priceValueTV.setText(String.format("%.2f", Double.parseDouble(price)) + " " + getString(R.string.price_arabic));
        setAdapter();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodCartActivity.super.onBackPressed();
            }
        });


        payNowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (termCB.isChecked()) {
                    int countItem = Integer.parseInt(itemValueTV.getText().toString().trim());
                    if (countItem <= 0) {
                        Toast.makeText(FoodCartActivity.this, "Please select item first", Toast.LENGTH_SHORT).show();
                    } else {
                        sureCheckout();
                    }


                }

            }
        });
    }

    private void setAdapter() {
        if (list.size() <= 0) {
            noData.setVisibility(View.VISIBLE);
        }
        parcelmenu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodCartAdapter(this, list);
        for (int i = 0; i < list.size(); i++) {
            qtyList.add("1");
        }
        parcelmenu.setAdapter(adapter);
    }

    @Override
    public void buttonAddMethod(String price, int countValue, int position) {

        if (qtyList.size() == position) {
            qtyList.set(position, String.valueOf(countValue));
        } else {
            qtyList.add(String.valueOf(countValue));
        }
        count = count + 1;
        itemValueTV.setText(String.valueOf(count));
        priceVal = priceVal + Double.parseDouble(price);
        priceValueTV.setText(String.format("%.2f", priceVal) + " " + getString(R.string.price_arabic));
    }

    @Override
    public void buttonRemoveMethod(String price, int countValue, int position) {
        if (qtyList.size() == position) {
            qtyList.set(position, String.valueOf(countValue));
        } else {
            qtyList.add(String.valueOf(countValue));
        }
        count = count - 1;
        itemValueTV.setText(String.valueOf(count));
        priceVal = priceVal - Double.parseDouble(price);
        priceValueTV.setText(String.format("%.2f", priceVal) + " " + getString(R.string.price_arabic));
    }

    @Override
    public void changeViewValue(String price, int countValue, int position) {

        if (list.size() <= 0) {
            noData.setVisibility(View.VISIBLE);
        }

        itemValueTV.setText(String.valueOf(count - countValue));

        count = count - countValue;
        Double calValue = Double.parseDouble(price) * countValue;
        Double finalPrice = priceVal - calValue;

        priceVal = finalPrice;

        priceValueTV.setText(String.format("%.2f", finalPrice) + " " + getString(R.string.price_arabic));

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
            private String savedLongitude;

            @Override
            public void onResponse(Call<MyprofileModel> call, Response<MyprofileModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    userId = response.body().getData().get(0).getUserid();
                    firstName = response.body().getData().get(0).getFirstname();
                    lastName = response.body().getData().get(0).getLastname();
                    email = response.body().getData().get(0).getEmail();
                    phone = response.body().getData().get(0).getPhone();
                    company = response.body().getData().get(0).getCompany();
                    response.body().getData().get(0).getOffice();
                    response.body().getData().get(0).getFloor();
                    response.body().getData().get(0).getDob();
                    response.body().getData().get(0).getLocation();

                    savedLocation = response.body().getData().get(0).getLocation();
                    savedLatitude = response.body().getData().get(0).getLatitude();
                    savedLongitude = response.body().getData().get(0).getLongitude();
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude))).title("Saved Location"));

                }
            }

            @Override
            public void onFailure(Call<MyprofileModel> call, Throwable t) {
                dialog.dismiss();
                //Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void sureCheckout() {

        final Dialog dialog = new Dialog(FoodCartActivity.this, R.style.mytheme);
        dialog.setContentView(R.layout.layout_sure_checkout);
        dialog.setTitle("");
        final TextView dialogButton = dialog.findViewById(R.id.nextBT);

        final TextView cancleButton = dialog.findViewById(R.id.closeBT);

        LinearLayout mainLL = dialog.findViewById(R.id.mainLL);
        final TextView titleTV = dialog.findViewById(R.id.titleTV);
        final TextView termCB = dialog.findViewById(R.id.termCB);

        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            termCB.setTypeface(custom_font_azab);
            titleTV.setTypeface(custom_font_azab);
            termCB.setTypeface(custom_font_azab);
            dialogButton.setTypeface(custom_font_azab);

        }
        termCB.setText(getString(R.string.your_parcel_gathering_box_es_will_be_delivered_after_24_hours_from_now, afterTime));
        if (languageValue.equalsIgnoreCase("ar")) {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> quantList = new ArrayList<>();


                for (int i = 0; i < list.size(); i++) {
                    quantList.add(list.get(i).getCountValue());
                }
                Intent intent = new Intent(FoodCartActivity.this, CheckoutPage.class);
                intent.putExtra("fromFoodCart", "yes");
                intent.putExtra("noOfItem", itemValueTV.getText().toString().trim());
                intent.putExtra("price", String.valueOf(priceVal));
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                intent.putExtra("company", company);
                intent.putExtra("userId", userId);
                intent.putExtra("idList", idList);
                intent.putExtra("priceList", priceList);
                intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);

                intent.putExtra("qtyList", TextUtils.join(", ", quantList));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
