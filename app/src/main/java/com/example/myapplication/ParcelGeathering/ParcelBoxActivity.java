package com.example.myapplication.ParcelGeathering;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.PartyBoxAdapter;
import com.example.myapplication.Adapter.PartyBoxParcelAdapter;
import com.example.myapplication.Constants;
import com.example.myapplication.FoodCart.FoodCartActivity;
import com.example.myapplication.Model.Retrofit.BucketAddModel;
import com.example.myapplication.Model.Retrofit.ParcelAddModel;
import com.example.myapplication.Model.Retrofit.ParcelDataModel;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParcelBoxActivity extends AppCompatActivity implements PartyBoxParcelAdapter.ParcelInterface, PartyBoxAdapter.ParcelInterface {

    private TextView back, payNowTV, itemValueTV, priceValueTV, titlemenu, itemTV, priceTV;
    RecyclerView parcelmenu;
    RelativeLayout mainRL;
    private String userId = "";
    private int count = 0;
    private List<BucketAddModel> bucketListValue = new ArrayList<>();
    private List<FoodCartModel> foodList = new ArrayList<>();
    private double priceValue = 0.00;
    private List<String> idList = new ArrayList();
    private List<String> priceList = new ArrayList();
    private String afterTime = "24";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        setContentView(R.layout.activity_parcel_box);
        if (getIntent().hasExtra("userId")) {
            userId = getIntent().getStringExtra("userId");
        }
        back = findViewById(R.id.back);
        parcelmenu = findViewById(R.id.parcelmenu);
        payNowTV = findViewById(R.id.payNowTV);
        priceTV = findViewById(R.id.priceTV);
        itemTV = findViewById(R.id.itemTV);
        titlemenu = findViewById(R.id.titlemenu);
        mainRL = findViewById(R.id.mainRL);
        itemValueTV = findViewById(R.id.itemValueTV);
        priceValueTV = findViewById(R.id.priceValueTV);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParcelBoxActivity.super.onBackPressed();
            }
        });
        ParcelCafe();

        payNowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (priceValue == 0 || priceValue == 0.00) {
                    Toast.makeText(ParcelBoxActivity.this, getString(R.string.please_select_item), Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(ParcelBoxActivity.this, "id list  " + foodList.size(), Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(ParcelBoxActivity.this, "price list  "+priceList.size(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ParcelBoxActivity.this, FoodCartActivity.class);
                    intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) bucketListValue);
                    intent.putExtra("noOfItem", String.valueOf(count));
                    intent.putExtra("price", String.valueOf(priceValue));
                    intent.putExtra("idList", TextUtils.join(",", idList));
                    intent.putExtra("priceList", TextUtils.join(", ", priceList));
                    intent.putExtra("afterTime", afterTime);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }

            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ParcelBoxActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            itemTV.setTypeface(custom_font_azab);
            priceTV.setTypeface(custom_font_azab);
            payNowTV.setTypeface(custom_font_azab);
            priceValueTV.setTypeface(custom_font_azab);
            itemValueTV.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);

        }

        if (languageValue.equalsIgnoreCase("ar")) {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }

    private void ParcelCafe() {

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setUserid(userId);
        task.setLang(languageValue);
        task.setDate(formattedDate);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetParty(task).enqueue(new Callback<ParcelDataModel>() {
            @Override
            public void onResponse(Call<ParcelDataModel> call, Response<ParcelDataModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<ParcelAddModel> parcel = response.body().getData();

                    afterTime = response.body().getData().get(1).getAftertime();
                    GridLayoutManager latest = new GridLayoutManager(getApplicationContext(), 1);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    parcelmenu.setItemAnimator(new DefaultItemAnimator());
                    parcelmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    parcelmenu.setAdapter(new PartyBoxAdapter(parcel, R.layout.parcel_addcart, ParcelBoxActivity.this));

                }
            }

            @Override
            public void onFailure(Call<ParcelDataModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void buttonAddMethod(List<BucketAddModel> bucketList, int countValue, String price, String itemId, String itemName, String itemImage) {
        count = count + 1;
        itemValueTV.setText(String.valueOf(count));

        priceValue = Double.parseDouble(price) + priceValue;
       // priceValueTV.setText(getString(R.string.price_arabic) + " " + priceValue);
        priceValueTV.setText(getString(R.string.price_arabic) + " " + String.format("%.2f", priceValue));
        bucketListValue = bucketList;
        FoodCartModel model = new FoodCartModel();
        model.setId(itemId);
        model.setList(bucketList);
        model.setTitle(itemName);
        model.setImage(itemImage);
        model.setPrice(String.valueOf(priceValue));
        foodList.add(model);
        idList.add(itemId.trim());
        priceList.add(price);

    }

    @Override
    public void buttonAddMethod(String id, String titleEn, List<BucketAddModel> list) {

    }

    @Override
    public void buttonRemoveMethod(String id, String titleEn) {


    }


    @Override
    public void buttonRemovePriceMethod(List<BucketAddModel> bucketList, int countValue, String price, String itemId, String itemName, String itemImage) {

        if (count > 0) {
            count = count - 1;
            itemValueTV.setText(String.valueOf(count));

            priceValue = priceValue - Double.parseDouble(price);
            priceValueTV.setText(getString(R.string.price_arabic) + " " +String.format("%.2f", priceValue) );
            bucketListValue = bucketList;
            FoodCartModel model = new FoodCartModel();
            model.setId(itemId);
            model.setList(bucketList);
            model.setTitle(itemName);
            model.setImage(itemImage);
            model.setPrice(String.valueOf(priceValue));
            foodList.remove(model);
            idList.remove(itemId);
            priceList.remove(price);
        }

    }
}
