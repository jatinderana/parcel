package com.example.myapplication.FoodParcel;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Adapter.ShowCardAdapter;
import com.example.myapplication.Constants;
import com.example.myapplication.DialogAdapterSubc;
import com.example.myapplication.DialogSubcAdapter;
import com.example.myapplication.Dialogpojo;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Retrofit.CyberData;
import com.example.myapplication.Model.Retrofit.CyberResponse;
import com.example.myapplication.Model.Retrofit.FoodData;
import com.example.myapplication.Model.Retrofit.GatewayModel;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.Model.Retrofit.GiftDetail;
import com.example.myapplication.Model.Retrofit.PaypalCancel;
import com.example.myapplication.Model.Retrofit.Subscription;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Model.Retrofit.checkout;
import com.example.myapplication.Model.Retrofit.paypalmodel;
import com.example.myapplication.PayPalConfig;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.makeText;

public class FoodParcelActivity extends AppCompatActivity implements DialogSubcAdapter.DialogInterface, ShowCardAdapter.InterfaceCard {
    private String arrayValue = "";
    private ListView listTeachers;
    private ArrayList<Dialogpojo> alCustom = new ArrayList<Dialogpojo>();
    private String Userid;
    private String date;
    private String resp = "";
    private String doubleId = "";
    ImageView imgCross;
    public static final int PAYPAL_QUANT_REQUEST_CODE = 12345;
    //paytm
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    public static final int PAYPAL_REQUEST_CODE = 123;
    private String dateSelected, restDays;
    private List<Subscription> subscription;
    private String lastIdValue = "";
    private Dialog dialog;
    TextView tv_date;
    private List<GetCardModel> list = new ArrayList<>();
    private CountDownTimer timer = null;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.dialog_inform_subcription);
        setCardDetails();
        listTeachers = findViewById(R.id.list_teachers);
        imgCross = findViewById(R.id.img_cross);
        tv_date = findViewById(R.id.tv_date);
        dateSelected = getIntent().getStringExtra("dateSelected");
        restDays = getIntent().getStringExtra("restDays");
        date = getIntent().getStringExtra("date");
        subscription = getIntent().getParcelableArrayListExtra("subscription");
        Bundle b = getIntent().getExtras();
        imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodParcelActivity.super.onBackPressed();
            }
        });
        arrayValue = b.getString("Array");
        Typeface custom_font_azab = Typeface.createFromAsset(FoodParcelActivity.this.getAssets(), "arajozoor_regular.otf");
        //holder.title.setTypeface(custom_font_azab);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FoodParcelActivity.this);

        String languageValue = pref.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            tv_date.setTypeface(custom_font_azab);
        }


    }

    private void setCardDetails() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FoodParcelActivity.this);
        String Userid = preferences.getString("userid", "");
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final checkout task = new checkout();
        task.setUserid(Userid);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.showCards(task).enqueue(new Callback<List<GetCardModel>>() {
            @Override
            public void onResponse(Call<List<GetCardModel>> call, Response<List<GetCardModel>> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().size() > 0) {
                        list = response.body();
                        saveCardData(list);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<GetCardModel>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(FoodParcelActivity.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveCardData(List<GetCardModel> list) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIs_default() != null) {
                if (list.get(i).getIs_default().equalsIgnoreCase("1")) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();

                    try {
                        editor.putString(Constants.CARD_VALUE, list.get(i).getCard_number());
                        editor.putString(Constants.CVC_VALUE, list.get(i).getCvc());
                        editor.putString(Constants.EXPIRY_MONTH, list.get(i).getCard_month());
                        editor.putString(Constants.EXPIRY_YEAR, list.get(i).getCard_year());

                    } catch (Exception ae) {
                        ae.printStackTrace();
                    }

                    editor.apply();
                }
            }

        }

    }

    private void getFoodData(String date) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FoodParcelActivity.this);
        Userid = preferences.getString("userid", "");
        final ProgressDialog dialog = new ProgressDialog(FoodParcelActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FoodParcelActivity.this);
        String languageValue = pref.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setUserid(Userid);
        task.setDate(date);
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetFoodOfDay(task).enqueue(new Callback<FoodData>() {
            @Override
            public void onResponse(Call<FoodData> call, Response<FoodData> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    resp = response.body().toString();

                    if (!resp.isEmpty()) {
                        listTeachers.setAdapter(new DialogAdapterSubc(FoodParcelActivity.this, getMatchList(arrayValue + ""), response.body(), dateSelected, subscription, Integer.parseInt(restDays)));
                    }

                    if (response.body().getGiftdetails().size() > 0) {
                        showGiftDialog(response);
                    }
                    //   List<Replace> menu = response.body();

                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FoodData> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(FoodParcelActivity.this, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showGiftDialog(Response<FoodData> response) {

        TextView emailTV, dialogTV, comapnyTV, homeTV, floorTV, locationTV, mobileTV, closeBT;


        RelativeLayout relativeLayout1, relativeLayout2;
        dialog = new Dialog(FoodParcelActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_gift_success);
        emailTV = dialog.findViewById(R.id.termCB);
        comapnyTV = dialog.findViewById(R.id.comapnyTV);
        homeTV = dialog.findViewById(R.id.homeTV);
        floorTV = dialog.findViewById(R.id.floorTV);
        locationTV = dialog.findViewById(R.id.locationTV);
        mobileTV = dialog.findViewById(R.id.mobileTV);

        closeBT = dialog.findViewById(R.id.closeBT);
        dialogTV = dialog.findViewById(R.id.dialogTV);
        GiftDetail respData = response.body().getGiftdetails().get(0);
        emailTV.setText(getString(R.string.an_email_sent) + " " + response.body().getGiftdetails().get(0).getEmail() + " " + getString(R.string.following_address));

        comapnyTV.setText(getString(R.string.cpmpany_name) + " " + respData.getCompany());
        homeTV.setText(getString(R.string.home_value) + " " + respData.getOffice());
        floorTV.setText(getString(R.string.floor_value) + " " + respData.getFloor());
        locationTV.setText(getString(R.string.location_value) + " " + respData.getLocation());
        mobileTV.setText(getString(R.string.mobile_value) + " " + respData.getPhone());
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FoodParcelActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");


        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(FoodParcelActivity.this.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(FoodParcelActivity.this.getAssets(), "arajozoor_regular.otf");
            //holder.title.setTypeface(custom_font_azab);
            tv_date.setTypeface(custom_font_azab);

            dialogTV.setTypeface(custom_font_azab);

            dialogTV.setTextSize(24);
            comapnyTV.setTypeface(custom_font_azab);
            homeTV.setTypeface(custom_font_azab);
            floorTV.setTypeface(custom_font_azab);
            locationTV.setTypeface(custom_font_azab);
            mobileTV.setTypeface(custom_font_azab);
            closeBT.setTypeface(custom_font_azab);
            emailTV.setTypeface(custom_font_azab);
        }
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private ArrayList<Dialogpojo> getMatchList(String detail) {
        try {
            JSONArray jsonArray = new JSONArray(detail);
            alCustom = new ArrayList<Dialogpojo>();
            alCustom.clear();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.optJSONObject(i);
                Dialogpojo pojo = new Dialogpojo();
                pojo.setTitles(jsonObject.optString("hnames"));
                pojo.setImage(jsonObject.optString("img"));
                pojo.setSubjects(jsonObject.optString("hsubject"));
                pojo.setDescripts(jsonObject.optString("descript"));

                alCustom.add(pojo);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (alCustom.size() > 1) {
            for (int i = alCustom.size() - 1; i > 0; i--) {
                alCustom.remove(i);
            }
        }
        return alCustom;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Place place = PlaceAutocomplete.getPlace(this, data);
        //citys.setText(place.getName());

        if (requestCode == PAYPAL_QUANT_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                try {
                    String paymentDetails = confirm.toJSONObject().toString(4);
                    FoodData.Uptdatum finalUpdateData1 = new FoodData.Uptdatum();
                    sendUpgradeDataToApi(paymentDetails, lastIdValue, "", "", finalUpdateData1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //if confirmation is not null

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }

    }

    private void sendUpgradeDataToApi(final String paymentDetails, String doubleId, String finalId, String foodtype, FoodData.Uptdatum finalUpdateData1) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String checkstatus = preferences.getString("status", "");
        checkstatus = foodtype;
        String upperString = checkstatus.substring(0, 1).toUpperCase() + checkstatus.substring(1);
        final GatewayModel task = new GatewayModel();
        task.setCheck_id(doubleId);
        if (!finalId.equalsIgnoreCase("0")) {
            task.setCheck_id(finalId);
        }
        task.setCheck_id(finalId);
        // task.setCheck_id(finalUpdateData1.getCheckId());
        task.setFoodtype(checkstatus);
        task.setPaytype("Double");

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalSuccess(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {
                        Toast.makeText(FoodParcelActivity.this, getString(R.string.quantity_double_success), Toast.LENGTH_SHORT).show();
                        getFoodData(date);
                    }
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<paypalmodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure! Payment is not done", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFoodData(date);
    }

    @Override
    public void paypalListener(Double aDouble, String lastId, final String finalId, final String foodtype, final FoodData.Uptdatum finalUpdateData1) {
        lastIdValue = lastId;
       /* PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(aDouble)), "USD", "Payable Amount",
                PayPalPayment.PAYMENT_INTENT_SALE);
        //Creating Paypal Payment activity intent
        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_QUANT_REQUEST_CODE);
*/


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FoodParcelActivity.this);
        String cardValue = preferences.getString(Constants.CARD_VALUE, "");
        String cvcValue = preferences.getString(Constants.CVC_VALUE, "");
        String expiryMonth = preferences.getString(Constants.EXPIRY_MONTH, "");
        String expiryYear = preferences.getString(Constants.EXPIRY_YEAR, "");
        String userFirstName = preferences.getString("userFirstName", "");
        String userLastName = preferences.getString("userLastName", "");
        String userCompanyName = preferences.getString("userCompanyName", "");
        String userEmailId = preferences.getString("userEmailId", "");

        final ProgressDialog dialog = new ProgressDialog(FoodParcelActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final CyberData task = new CyberData();
        task.setAccountNumber(cardValue);
        task.setExpirationMonth(expiryMonth);
        task.setExpirationYear(expiryYear);
        task.setCvNumber(cvcValue);
        task.setFirstName(userFirstName);
        task.setLastName(userLastName);
        task.setStreet1(userCompanyName);
        task.setAmount(String.valueOf(aDouble));
        task.setEmail(userEmailId);
        if (finalId.equalsIgnoreCase("0")) {
            task.setCheck_id(String.valueOf(lastId));
        } else {
            task.setCheck_id(finalId);
        }
/*
        if(finalUpdateData1!=null)
        {
            task.setCheck_id(finalUpdateData1.getCheckId());
        }*/

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.getCyberPay(task).enqueue(new Callback<CyberResponse>() {
            @Override
            public void onResponse(Call<CyberResponse> call, Response<CyberResponse> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    //  Toast.makeText(CheckoutPage.this, "" + response.body().getReasonCode(), Toast.LENGTH_SHORT).show();
                    String responseCode = response.body().getReasonCode();
                    if (response.body().getReasonCode().equalsIgnoreCase("100")) {
                        // sendDataToApi("");
                        sendUpgradeDataToApi("", lastIdValue, finalId, foodtype, finalUpdateData1);
                    } else if (responseCode.equalsIgnoreCase("101")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Declined - The request is missing one or more fields", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("102")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Declined - One or more fields in the request contains invalid data.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("150")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Error - General CyberSource system failure.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("151")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Error - The request was received but there was a server timeout. This error does not include timeouts between the client and the server.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("152")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Error: The request was received, but a service did not finish running in time.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("204")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - Insufficient funds in the account.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("207")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - Issuing bank unavailable.'", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("208")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - Inactive card or card not authorized for card-not-present transactions.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("209")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - card verification number (CVV) did not match.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("210")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - The card has reached the credit limit.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("211")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - Invalid Card Verification Number (CVV).", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("221")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - The customer matched an entry on the processor negative file.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("222")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - customer account is frozen", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("231")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - Invalid account number", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("232")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - The card type is not accepted by the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("240")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Decline - The card type sent is invalid or does not correlate with the credit card number.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("250")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Error - The request was received, but there was a timeout at the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("480")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "The order is marked for review by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("481")) {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "The order has been rejected by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else {
                        hitGatewayCancel();
                        makeText(FoodParcelActivity.this, "Payment has been cancelled", Toast.LENGTH_SHORT).show();
                    }
                 /*   etfirstname.setText(response.body().getData().get(0).getFirstname());
                    lastname.setText(response.body().getData().get(0).getLastname());
                    emails.setText(response.body().getData().get(0).getEmail());
                    phones.setText(response.body().getData().get(0).getPhone());
                    companyname.setText(response.body().getData().get(0).getCompany());
                    office.setText(response.body().getData().get(0).getOffice());
                    floor.setText(response.body().getData().get(0).getFloor());
                    //zip.setText(response.body().getData().get(0).getDob());
                    locationSearch.setText(response.body().getData().get(0).getLocation());
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude))).title("Saved Location"));
*/

                }
            }

            @Override
            public void onFailure(Call<CyberResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(FoodParcelActivity.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void hitGatewayCancel() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String checkstatus = preferences.getString("status", "");
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(String.valueOf(lastIdValue));
        task.setFoodtype("");
        task.setPaytype("");
        task.setLang(languageValue);
        if (Userid.isEmpty()) {
            task.setIs_logged("No");
        } else {
            task.setIs_logged("Yes");
        }
        Log.e("payment check", " check Id  : " + lastIdValue + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalCancel(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {
                        Toast.makeText(FoodParcelActivity.this, "Payment Cancelled", Toast.LENGTH_SHORT).show();
                     /*   startActivity(new Intent(CheckoutPage.this, MainActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));*/
                    }
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<paypalmodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public void cardPaymentDialog(final String priceValue, final int lastId) {

        Button closeBT, nextBT;
        final EditText monthET, cardET, yearET, cvvET;
        final TextView cardTV, cvvTV, minuteTV, secondTV, secureTV;
        ImageView timeIV;
        ConstraintLayout cardCL;
        RecyclerView cardRV;
        final CheckBox saveCB;
        dialog = new Dialog(this, android.R.style.Theme_Light);
        //  dialog = new Dialog(this, R.style.RatingDialog2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.card_payment_dialog);
        cardTV = dialog.findViewById(R.id.cardTV);
        cardET = dialog.findViewById(R.id.cardET);
        monthET = dialog.findViewById(R.id.monthET);
        yearET = dialog.findViewById(R.id.yearET);
        cvvET = dialog.findViewById(R.id.cvvET);
        cvvTV = dialog.findViewById(R.id.cvvTV);
        closeBT = dialog.findViewById(R.id.closeBT);
        nextBT = dialog.findViewById(R.id.nextBT);
        timeIV = dialog.findViewById(R.id.timeIV);
        minuteTV = dialog.findViewById(R.id.minuteTV);
        secondTV = dialog.findViewById(R.id.secondTV);
        cardCL = dialog.findViewById(R.id.cardCL);
        cardRV = dialog.findViewById(R.id.cardRV);
        saveCB = dialog.findViewById(R.id.saveCB);
        secureTV = dialog.findViewById(R.id.secureTV);
        cardCL.setVisibility(View.VISIBLE);
        cardRV.setVisibility(View.GONE);
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();


                if (lastId != 0) {
                    hitCronJob(String.valueOf(lastId), "Subscription");

                }
            }


        });


        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    timer.cancel();

                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }

                    dialog.dismiss();
                }
                return false;
            }
        });

        timer = new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {

                minuteTV.setText("" + String.format("%d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                secondTV.setText("" + String.format(" %d",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                counter++;
            }

            public void onFinish() {
                hitGatewayCancel();
                startActivity(new Intent(FoodParcelActivity.this, MainActivity.class)
                        .putExtra("PaymentDetails", "")
                        .putExtra("PaymentAmount", ""));
            }
        }.start();

        Glide.with(this)
                .load(R.drawable.timer)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(timeIV);

       /* Glide.with(this)
                .load(R.drawable.timer)
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(timeIV);
*/
        ArrayList<String> years = new ArrayList<String>();
        ArrayList<String> monthsList = new ArrayList<String>();
        ArrayList<String> monthIntList = new ArrayList<String>();
        ArrayList<String> monthCurrentList = new ArrayList<String>();

        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            String month = months[i];
            monthsList.add(months[i]);
        }


        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                monthIntList.add("0" + i);
            } else {
                monthIntList.add(Integer.toString(i));
            }

        }

        final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        int thisMonth = Calendar.getInstance().get(Calendar.MONTH);


        for (int i = thisMonth + 1; i <= 12; i++) {
            if (i < 10) {
                monthCurrentList.add("0" + i);
            } else {
                monthCurrentList.add(Integer.toString(i));
            }

        }

        for (int i = thisYear; i <= thisYear + 50; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        final ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthIntList);
        final ArrayAdapter<String> adapterCurrent = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthCurrentList);

        final Spinner spinYear = dialog.findViewById(R.id.yearspin);
        final Spinner monthSpin = dialog.findViewById(R.id.monthSpin);
        spinYear.setAdapter(adapter);


        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (spinYear.getSelectedItem().equals(String.valueOf(thisYear))) {
                    monthSpin.setAdapter(adapterCurrent);

                } else {
                    monthSpin.setAdapter(adapterMonth);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        monthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyboard(FoodParcelActivity.this);
                new RackMonthPicker(FoodParcelActivity.this)
                        .setLocale(Locale.ENGLISH)
                        .setPositiveButton(new DateMonthDialogListener() {
                            @Override
                            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                                if (month < 10) {
                                    monthET.setText(" 0" + month);
                                } else {
                                    monthET.setText(String.valueOf(month));
                                }


                            }
                        })
                        .setNegativeButton(new OnCancelMonthDialogListener() {
                            @Override
                            public void onCancel(AlertDialog dialog) {

                            }
                        }).show();
            }
        });
        yearET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyboard(FoodParcelActivity.this);
                new RackMonthPicker(FoodParcelActivity.this)
                        .setLocale(Locale.ENGLISH)
                        .setPositiveButton(new DateMonthDialogListener() {
                            @Override
                            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                                if (month < 10) {
                                    yearET.setText(" " + year);
                                } else {
                                    yearET.setText(String.valueOf(year));
                                }


                            }
                        })
                        .setNegativeButton(new OnCancelMonthDialogListener() {
                            @Override
                            public void onCancel(AlertDialog dialog) {

                            }
                        }).show();
            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FoodParcelActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");

            cardTV.setTypeface(custom_font_azab);
            cardET.setTypeface(custom_font_azab);
            monthET.setTypeface(custom_font_azab);
            yearET.setTypeface(custom_font_azab);
            cvvTV.setTypeface(custom_font_azab);
            cvvET.setTypeface(custom_font_azab);
            closeBT.setTypeface(custom_font_azab);
            nextBT.setTypeface(custom_font_azab);
            saveCB.setTypeface(custom_font_azab);
            secureTV.setTypeface(custom_font_azab);
        }


        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (lastId != 0) {
                    hitCronJob(String.valueOf(lastId), "Subscription");

                }

                timer.cancel();


            }
        });
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardET.getText().toString().isEmpty()) {
                    makeText(FoodParcelActivity.this, getString(R.string.please_enter_card), Toast.LENGTH_SHORT).show();
                }/* else if (monthET.getText().toString().isEmpty()) {
                    Toast.makeText(CheckoutPage.this, getString(R.string.please_enter_month), Toast.LENGTH_SHORT).show();

                }*//* else if (yearET.getText().toString().isEmpty()) {
                    Toast.makeText(CheckoutPage.this, getString(R.string.please_enter_year), Toast.LENGTH_SHORT).show();

                } */ else if (cvvET.getText().toString().isEmpty()) {
                    makeText(FoodParcelActivity.this, getString(R.string.please_enter_cvv), Toast.LENGTH_SHORT).show();

                } else {


                    //  CheckOut(dialog, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString());
                }

            }
        });
        dialog.show();


    }


    private void hitCronJob(String checkId, final String payType) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String checkstatus = preferences.getString("status", "");
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final PaypalCancel task = new PaypalCancel();
        task.setCheckId(String.valueOf(checkId));
        task.setPaytype(payType);
        task.setLang(languageValue);

        Log.e("payment check", " check Id  : " + checkId + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.manualCronJob(task).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void changeButton(String id, int i, boolean checked, GetCardModel model) {
        if (checked) {
            for (int j = 0; j < DialogSubcAdapter.listCards.size(); j++) {
                DialogSubcAdapter.listCards.get(j).setOnChecked(false);
            }
            model.setOnChecked(true);
            DialogSubcAdapter.listCards.set(i, model);
            DialogSubcAdapter.mainCardRV.post(new Runnable() {
                @Override
                public void run() {
                    //  adapter.notifyItemChanged(i);
                    DialogSubcAdapter.adapter.notifyDataSetChanged();
                }
            });
        } else {
            /*for (int j = 0; j < listCards.size(); j++) {
                listCards.get(j).setOnChecked(true);
            }*/
            model.setOnChecked(false);
            DialogSubcAdapter.listCards.set(i, model);
            DialogSubcAdapter.mainCardRV.post(new Runnable() {
                @Override
                public void run() {
                    //  adapter.notifyItemChanged(i);
                    DialogSubcAdapter.adapter.notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public void showView(String id, int i, boolean checked, GetCardModel model) {
        if (model.getCard_number().equalsIgnoreCase(Constants.NEWCARD)) {
            if (model.isOnChecked()) {
                DialogSubcAdapter.cardCLView.setVisibility(View.VISIBLE);
            } else {
                DialogSubcAdapter.cardCLView.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void hideView(String id, int i, boolean checked, GetCardModel model) {
        if (!model.getCard_number().equalsIgnoreCase(Constants.NEWCARD)) {
            if (model.isOnChecked()) {
                //Dialog mainDialog = new Dialog(this);
                DialogSubcAdapter.getCyberPayData(FoodParcelActivity.this,DialogSubcAdapter.dialog, String.valueOf(DialogSubcAdapter.Price),Integer.parseInt(DialogSubcAdapter.lastId), model.getCard_number(), model.getCvc(), model.getCard_month(), model.getCard_year(),DialogSubcAdapter.saveCB.isChecked());
              // partyCheckOut(dialog, model.getCard_number(), model.getCvc(), model.getCard_month(), model.getCard_year(), false);

            }

        }

        DialogSubcAdapter.cardCLView.setVisibility(View.GONE);
        //Toast.makeText(this, "Another saved acard", Toast.LENGTH_SHORT).show();

        //  CheckOut(dialog, model.getCard_number(), model.getCvc(), model.getCard_month(), model.getCard_year());


    }


}
