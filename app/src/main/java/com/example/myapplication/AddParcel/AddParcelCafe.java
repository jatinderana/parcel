package com.example.myapplication.AddParcel;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ParcelC;
import com.example.myapplication.Constants;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Retrofit.GatewayModel;
import com.example.myapplication.Model.Retrofit.Parcel;
import com.example.myapplication.Model.Retrofit.ParcelBucketModel;
import com.example.myapplication.Model.Retrofit.ParcelCafeModel;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Model.Retrofit.paypalmodel;
import com.example.myapplication.ParcelCafe;
import com.example.myapplication.PayPalConfig;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddParcelCafe extends AppCompatActivity implements AddParcelMenuAdapter.AddListener {
    RecyclerView parcelmenu;
    TextView back, titlemenu, itemValueTV, priceValueTV, payNowTV, comingTV;
    private int totalVal = 0;
    ImageView logoIV;
    private double totalPrcie = 0.00;
    private String restDays = "";
    private String duration = "";
    RelativeLayout calRL;
    private List<PriceListModel> priceList = new ArrayList<>();
    private List<String> priceIdList = new ArrayList();
    private List<String> priceValueList = new ArrayList<>();
    private List<String> checkValueList = new ArrayList<>();
    private String userId = "";
    private String selectedData = "";
    public static final int PAYPAL_REQUEST_CODE = 123;
    private List<String> countValueList = new ArrayList();
    private List<String> restDaysList = new ArrayList();
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    private String lastId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_parcel_cafe);
        parcelmenu = findViewById(R.id.parcelmenu);
        back = findViewById(R.id.back);
        titlemenu = findViewById(R.id.titlemenu);
        itemValueTV = findViewById(R.id.itemValueTV);
        comingTV = findViewById(R.id.comingTV);
        calRL = findViewById(R.id.calRL);
        logoIV = findViewById(R.id.logoIV);
        priceValueTV = findViewById(R.id.priceValueTV);
        payNowTV = findViewById(R.id.payNowTV);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userId = preferences.getString("userid", "");
        parcelmenu.setHasFixedSize(false);
        ParcelCafe();
        if (getIntent().hasExtra("restDays")) {
            restDays = getIntent().getStringExtra("restDays");
            duration = getIntent().getStringExtra("duration");
            selectedData = getIntent().getStringExtra("selectedData");
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddParcelCafe.super.onBackPressed();
            }
        });
        payNowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPrcie != 0.00) {
                    showTotalDialog();
                }

            }
        });

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

    private void showTotalDialog() {
        final Dialog dialog = new Dialog(this, R.style.mytheme);
        dialog.setContentView(R.layout.dialog_layout_parcel);
        dialog.setTitle("");
        TextView dialogButton = dialog.findViewById(R.id.nextBT);
        TextView cancleButton = dialog.findViewById(R.id.closeBT);
        final TextView titleTV = dialog.findViewById(R.id.titleTV);

        final Double finalTotalPrice = totalPrcie;

        titleTV.setText(finalTotalPrice + " " + getString(R.string.price_arabic));
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                parcelBucketPay();
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
                    List<Parcel> parcel = response.body().getData();
                    List<String> restDates = response.body().getRestdates();



                    if(parcel.size()>0)
                    {

                        GridLayoutManager latest = new GridLayoutManager(getApplicationContext(), 1);
                        latest.setOrientation(GridLayoutManager.VERTICAL);
                        parcelmenu.setItemAnimator(new DefaultItemAnimator());
                        parcelmenu.setLayoutManager(latest);
                        // recyclerView.setNestedScrollingEnabled(false);
                        parcelmenu.setAdapter(new AddParcelAdapter(parcel, R.layout.view_menu_add, AddParcelCafe.this, AddParcelCafe.this, restDates, restDays, duration));
                        calRL.setVisibility(View.VISIBLE);


                    }
                    else
                    {
                        comingTV.setVisibility(View.VISIBLE);
                        logoIV.setVisibility(View.VISIBLE);
                        calRL.setVisibility(View.GONE);
                    }

 // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ParcelCafeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void parcelBucketPay() {
        for (int i = 0; i < priceList.size(); i++) {
            priceIdList.add(priceList.get(i).id);
            priceValueList.add(priceList.get(i).price);
            checkValueList.add(priceList.get(i).checkValue);
            countValueList.add(priceList.get(i).countValue);
            restDaysList.add(restDays);
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        final ParcelBucketModel task = new ParcelBucketModel();
        task.setUserid(userId);
        task.setPdate(selectedData);
        task.setFoodtype(duration);
        task.setFinaltotal(String.valueOf(totalPrcie));
        task.setIds(android.text.TextUtils.join(",", priceIdList));
        task.setPrices(android.text.TextUtils.join(",", priceValueList));
        task.setTotals(android.text.TextUtils.join(",", priceValueList));
        task.setQtys(android.text.TextUtils.join(",", countValueList));
        task.setDaily(android.text.TextUtils.join(",", checkValueList));
        task.setRestdays(android.text.TextUtils.join(",", restDaysList));
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.sendParcelBucket(task).enqueue(new Callback<ParcelResponseModel>() {
            @Override
            public void onResponse(Call<ParcelResponseModel> call, Response<ParcelResponseModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getData().size() > 0) {
                        lastId = response.body().getData().get(0).getId();
                    }

                    PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(totalPrcie)), "USD", "Payable Amount",
                            PayPalPayment.PAYMENT_INTENT_SALE);
                    //Creating Paypal Payment activity intent
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    //putting the paypal configuration to the intent
                    intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                    //Puting paypal payment to the intent
                    intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                    //Starting the intent activity for result
                    //the request code will be used on the method onActivityResult
                    startActivityForResult(intent, PAYPAL_REQUEST_CODE);
                }
            }

            @Override
            public void onFailure(Call<ParcelResponseModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void addValueMethod(int total) {
        totalVal = totalVal + 1;
        itemValueTV.setText(String.valueOf(totalVal));

    }

    @Override
    public void clearValueMethod() {
        totalVal = totalVal - 1;
        itemValueTV.setText(String.valueOf(totalVal));
    }

    @Override
    public void totalPriceMethod(String text, String price, int size, String restDays, boolean fromCheckBox, String id, int count, String checkBoxVal) {
        if (size > 0) {
            if (fromCheckBox) {
                totalPrcie = (Double.parseDouble(price) * Integer.parseInt(restDays)) + totalPrcie;
                priceValueTV.setText(String.format("%.2f", totalPrcie));
                PriceListModel model = new PriceListModel();
                model.setPrice(String.valueOf((Double.parseDouble(price) * Integer.parseInt(restDays))));
                model.setId(id);
                model.setCheckValue(checkBoxVal);
                model.setCountValue(String.valueOf(count));
                priceList.add(model);
            } else {
                totalPrcie = (Double.parseDouble(price)) + totalPrcie;
                priceValueTV.setText(String.format("%.2f", totalPrcie));
                PriceListModel model = new PriceListModel();
                model.setPrice(price);
                model.setId(id);
                model.setCheckValue(checkBoxVal);
                model.setCountValue(String.valueOf(count));
                priceList.add(model);
            }
        } else {
            totalPrcie = (Double.parseDouble(price)) + totalPrcie;
            priceValueTV.setText(String.format("%.2f", totalPrcie));
            PriceListModel model = new PriceListModel();
            model.setPrice(price);
            model.setCheckValue(checkBoxVal);
            model.setCountValue(String.valueOf(count));
            model.setId(id);
            priceList.add(model);
        }


    }

    @Override
    public void totalMinusPriceMethod(String text, String price, int size, String restDays, boolean fromCheck, String id, int count, String checkBoxVal) {
        if (size > 0) {

            if (fromCheck) {
                totalPrcie = totalPrcie - (Double.parseDouble(price) * Integer.parseInt(restDays));
                priceValueTV.setText(String.format("%.2f", totalPrcie));

                PriceListModel model = new PriceListModel();
                model.setPrice(price);
                model.setId(id);
                model.setCheckValue(checkBoxVal);
                model.setCountValue(String.valueOf(count));
                if (priceList.size() > 0) {
                    priceList.remove(model);
                }

            } else {
                totalPrcie = totalPrcie - (Double.parseDouble(price));
                priceValueTV.setText(String.format("%.2f", totalPrcie));
                PriceListModel model = new PriceListModel();
                model.setPrice(price);
                model.setId(id);
                model.setCheckValue(checkBoxVal);
                model.setCountValue(String.valueOf(count));
                if (priceList.size() > 0) {
                    priceList.remove(model);
                }
            }

        } else {
            totalPrcie = totalPrcie - (Double.parseDouble(price));
            priceValueTV.setText(String.format("%.2f", totalPrcie));
            PriceListModel model = new PriceListModel();
            model.setPrice(price);
            model.setId(id);
            model.setCheckValue(checkBoxVal);
            model.setCountValue(String.valueOf(count));
            if (priceList.size() > 0) {
                priceList.remove(model);
            }
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Place place = PlaceAutocomplete.getPlace(this, data);
        //citys.setText(place.getName());
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);
                        sendDataToApi(paymentDetails);
                        //Starting a new activity for the payment details and also putting the payment details with intent
                      /*  startActivity(new Intent(this, MainActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));*/

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }


    }


    private void sendDataToApi(final String paymentDetails) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        String checkstatus = preferences.getString("status", "");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(lastId);
        task.setFoodtype(duration);
        task.setPaytype("parcel");
        task.setLang(languageValue);

        Log.e("payment check", " check Id  : " + 1 + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalSuccess(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {
                        startActivity(new Intent(AddParcelCafe.this, MainActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", totalPrcie));
                        Toast.makeText(AddParcelCafe.this, getResources().getString(R.string.parcel_added_success), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<paypalmodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
