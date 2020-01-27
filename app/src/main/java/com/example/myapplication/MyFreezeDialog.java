package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.myapplication.Adapter.ShowCardAdapter;
import com.example.myapplication.AddressSelect.SelectAddressActivity;
import com.example.myapplication.FaqPage.FaqActivtiy;
import com.example.myapplication.Model.Retrofit.Canupgrade;
import com.example.myapplication.Model.Retrofit.CyberData;
import com.example.myapplication.Model.Retrofit.CyberResponse;
import com.example.myapplication.Model.Retrofit.FoodData;
import com.example.myapplication.Model.Retrofit.FreezeModel;
import com.example.myapplication.Model.Retrofit.FreezePackageModel;
import com.example.myapplication.Model.Retrofit.Freezes;
import com.example.myapplication.Model.Retrofit.GatewayModel;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.Model.Retrofit.LoginModel;
import com.example.myapplication.Model.Retrofit.Logins;
import com.example.myapplication.Model.Retrofit.ParcelDataModel;
import com.example.myapplication.Model.Retrofit.SubcribeModel;
import com.example.myapplication.Model.Retrofit.Subscription;
import com.example.myapplication.Model.Retrofit.TermsConditionModel;
import com.example.myapplication.Model.Retrofit.checkout;
import com.example.myapplication.Model.Retrofit.history;
import com.example.myapplication.Model.Retrofit.paypalmodel;
import com.example.myapplication.Model.Retrofit.subcribe;
import com.example.myapplication.ParcelGeathering.ParcelBoxActivity;
import com.example.myapplication.ParcelHisotry.ParcelBoxHistoryActivity;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.example.myapplication.Views.CustomTypefaceSpan;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.widget.Toast.makeText;

public class MyFreezeDialog extends Activity
        implements  WheelPicker.OnItemSelectedListener, DialogSubcAdapter.DialogInterface,ShowCardAdapter.InterfaceCard {
    CheckBox weeklycheck;
    Dialog dialog;
    public GregorianCalendar cal_month, cal_month_copy;
    private HwAdapterSubcription hwAdapter;
    private TextView tv_month, title, back;
    LinearLayout linearLayout;
    String Title, getdiscription, checkstatus;
    String date_time, date, name, email;
    Integer mYear, mMonth, mDay;
    ViewPager viewPager;
    RecyclerView foodmenu;
    TextView textView, nametitle, packageupgrade, packagefreeze, deliveryAddress, titleTB;
    Locale myLocale;
    String currentLanguage = "en", currentLang;
    String Menus = "gone";
    String DialogCheck, Userid;
    //paytm
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    public static final int PAYPAL_REQUEST_CODE = 123;
    public static final int PAYPAL_QUANT_REQUEST_CODE = 12345;


    ArrayList<String> listPrice = new ArrayList<>();
    ArrayList<String> listPriceId = new ArrayList<>();
    String[] DayOfWeek = {"Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday"};
    String titledate, titledate1, maintitle1, maintitle, titledate2, maintitle2, titledate3,
            maintitle3, titledate4, titledate5, maintitle4, checkdate, duration, imageboth, imageboth1, imageboth2, imageboth3, imageboth4,
            images1, images2, images3, images4, images5, newdate, titlemain, titlemain1, titlemain2, titlemain3,
            titlemain4;

    String weekstitle1, weekstitle2, weekstitle3, weekstitle4, weekstitle5, weeksimage1, weeksimage2, weeksimage3, weeksimage4, weeksimage5,
            weeksdate1, weeksdate2, weeksdate3, weeksdate4, weeksdate5, weektitlemain1, weektitlemain2, weektitlemain3, weektitlemain4, weektitlemain5,
            getWeektitlemain6, getWeektitlemain7, getWeektitlemain8, getWeektitlemain9, getWeektitlemain10;

    String monthtitle1, monthtitle2, monthtitle3, monthtitle4, monthtitle5, monthtitle6, monthtitle7, monthtitle8, monthtitle9, monthtitle10, monthtitle11, monthtitle12,
            monthtitle13, monthtitle14, monthtitle15, monthtitle16, monthtitle17, monthtitle18, monthtitle19, monthtitle20, monthtitle21, monthtitle22, monthtitle23, monthtitle24, monthtitle25,
            monthtitle26, monthtitle27, monthtitle28, monthtitle29, monthtitle30,
            monthimage1, monthimage2, monthimage3, monthimage4, monthimage5, monthimage6, monthimage7, monthimage8, monthimage9, monthimage10,
            monthimage11, monthimage12, monthimage13, monthimage14, monthimage15, monthimage16, monthimage17, monthimage18, monthimage19,
            monthimage20, monthimage21, monthimage22, monthimage23, monthimage24, monthimage25, monthimage26, monthimage27, monthimage28, monthimage29, monthimage30,
            monthdate1, monthdate2, monthdate3, monthdate4, monthdate5, monthdate6, monthdate7, monthdate8, monthdate9, monthdate10,
            monthdate11, monthdate12, monthdate13, monthdate14, monthdate15, monthdate16, monthdate17, monthdate18, monthdate19, monthdate20, monthdate21,
            monthdate22, monthdate23, monthdate24, monthdate25, monthdate26, monthdate27, monthdate28, monthdate29, monthdate30, monthdate31, monthtitlemain1, monthtitlemain2,
            monthtitlemain3, monthtitlemain4, monthtitlemain5, monthtitlemain6, monthtitlemain7, monthtitlemain8, monthtitlemain9, monthtitlemain10, monthtitlemain11, monthtitlemain12,
            monthtitlemain13, monthtitlemain14, monthtitlemain15, monthtitlemain16, monthtitlemain17, monthtitlemain18, monthtitlemain19, monthtitlemain20, monthtitlemain21, monthtitlemain22, monthtitlemain23, monthtitlemain24, monthtitlemain25, monthtitlemain26, monthtitlemain27,
            monthtitlemain28, monthtitlemain29, monthtitlemain30;
    private String choose;
    private TextView spine;
    private TextView faqID;
    private List<Canupgrade> canupgrade = new ArrayList<>();
    private TextView priceText;
    private String priceDetail = "";
    private String userId = "";
    private String subscriptionId = "";
    private String token = "";
    private String addressLimit;
    private List<Canupgrade> upgradePackage = new ArrayList<>();
    private String upgradeIt = "no";
    private String canFreeze = "no";
    private String endDate = "";
    private String startDate = "";
    private String priceIdDetail = "1";
    private String upgradeID = "";
    private String frozenValue = "No";
    private String doubleId = "";
    private int restDays = 0;
    private String lastIdValue = "0";
    private String formattedDate = "";
    private boolean your_date_is_outdated = false;
    private String subscribeNow = "no";
    private Typeface typeface;
    private String startDateMin = "";
    private List<GetCardModel> list = new ArrayList<>();
    private int counter = 0;
    private ConstraintLayout cardCLView;
    private List<GetCardModel> listCards = new ArrayList<>();
    private RecyclerView mainCardRV;
    private ShowCardAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_freeze);
        AssetManager am = getApplicationContext().getAssets();
        setCardDetails();

        typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "jameel_nori_kashida.ttf"));


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c);


        packageupgrade = findViewById(R.id.packageupgarde);
        LinearLayout lowerbutton = findViewById(R.id.lowerbutton);
        lowerbutton.setVisibility(View.GONE);
        faqID = findViewById(R.id.faqID);
        ImageView close = findViewById(R.id.close);
        packagefreeze = findViewById(R.id.packagefreeze);
        deliveryAddress = findViewById(R.id.deliveryAddress);
        titleTB = findViewById(R.id.titleTB);

        //  packagefreeze.setTypeface(typeface);

        // viewPager = (ViewPager) findViewById(R.id.viewPager);
        /////////////Hit Package Api Here///////////
        // Package();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });


        packagefreeze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FreezePackage();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
                String subscribeNow = preferences.getString(Constants.SUBSCRIBENOW, "no");
                if (subscribeNow.equalsIgnoreCase("yes")) {
                    Toast.makeText(MyFreezeDialog.this, getString(R.string.you_don_t_have_any_subscription_yet), Toast.LENGTH_SHORT).show();

                } else {
                    if (canFreeze.equalsIgnoreCase("yes")) {
                        Toast.makeText(MyFreezeDialog.this, getString(R.string.you_have_exceeded), Toast.LENGTH_SHORT).show();


                    } else {
                        FreezePackage();
                    }
                }

            }
        });

        faqID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FaqActivtiy.class);
                intent.putExtra("pageFrom", "dashboard");
                startActivity(intent);

            }
        });

        packageupgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
                String subscribeNow = preferences.getString(Constants.SUBSCRIBENOW, "no");
                if (subscribeNow.equalsIgnoreCase("yes")) {
                    Toast.makeText(MyFreezeDialog.this, getString(R.string.you_don_t_have_any_subscription_yet), Toast.LENGTH_SHORT).show();

                } else {
                    if (upgradeIt.equalsIgnoreCase("yes")) {
                        UpgradePackage();
                    } else {
                        Toast.makeText(MyFreezeDialog.this, getString(R.string.you_already_upgraded), Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        deliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
                String subscribeNow = preferences.getString(Constants.SUBSCRIBENOW, "no");
                if (subscribeNow.equalsIgnoreCase("yes")) {
                    Toast.makeText(MyFreezeDialog.this, getString(R.string.you_don_t_have_any_subscription_yet), Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(MyFreezeDialog.this, SelectAddressActivity.class);
                    intent.putExtra("userId", userId);
                    intent.putExtra("subscriptionId", subscriptionId);
                    startActivity(intent);

                }


            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        DialogCheck = preferences.getString("title", "");
        Userid = preferences.getString("userid", "");
        name = preferences.getString("name", "");
        email = preferences.getString("email", "");


        //  ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        //  viewPager.setAdapter(viewPagerAdapter);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTB.setTypeface(custom_font_azab);
            faqID.setTypeface(custom_font_azab);
            titleTB.setTextSize(24);
            packageupgrade.setTypeface(custom_font_azab);
            packagefreeze.setTypeface(custom_font_azab);
            deliveryAddress.setTypeface(custom_font_azab);
        }

        FloatingActionButton fab = findViewById(R.id.fab);




    }

    @Override
    public void onBackPressed() {
            //super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainhome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Buyitnow();
            return true;
        }

        if (id == R.id.mainprofile) {
            Intent intent = new Intent(getApplicationContext(), MyAccounts.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animleft, R.anim.animright);


            //profile();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();

        if (DialogCheck == "title") {
            //Buyitnow();
        } else {
            Package();
        }

        ParcelCafe();


    }

    public void profile() {

        Button cancel, send;
        TextView title;
        final Spinner spine, spin;
        final EditText etdate;
        LinearLayout relativeLayout1, relativeLayout2;

        dialog.setContentView(R.layout.profile);

        relativeLayout1 = dialog.findViewById(R.id.tv_title);
        relativeLayout2 = dialog.findViewById(R.id.tv_title2);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.show();

    }


    public void Buyitnow() {

        Button cancel, send;
        TextView title;
        final Spinner spine, spin;
        final EditText etdate;
        RelativeLayout relativeLayout1, relativeLayout2;

        dialog.setContentView(R.layout.chooselanguage);

        relativeLayout1 = dialog.findViewById(R.id.tv_title);
        relativeLayout2 = dialog.findViewById(R.id.tv_title2);
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.SETLANG, "en");
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ar");
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.SETLANG, "ar");
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        dialog.show();


    }

    public void UpgradePackage() {
        TextView cancel;


        Button send, cancl, sendButton;
        TextView title, priceTV, emails;
        final WheelPicker packageWP;
        final Spinner spin;
        final EditText etdate;

        dialog = new Dialog(MyFreezeDialog.this);
        //  dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.choosepackage);
        packageWP = dialog.findViewById(R.id.packageWP);
        sendButton = dialog.findViewById(R.id.send_button);
        priceTV = dialog.findViewById(R.id.priceTV);
        title = dialog.findViewById(R.id.title);
        emails = dialog.findViewById(R.id.emails);
        priceText = priceTV;
        spine = dialog.findViewById(R.id.spine);
        cancl = dialog.findViewById(R.id.cancel_button);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");

            title.setTypeface(custom_font_azab);
            emails.setTypeface(custom_font_azab);
            cancl.setTypeface(custom_font_azab);
            spine.setTypeface(custom_font_azab);
            sendButton.setTypeface(custom_font_azab);
        }
        try {
            packageWP.setData(getList());
            packageWP.setOnItemSelectedListener(MyFreezeDialog.this);
        } catch (Exception ae) {
            ae.printStackTrace();
        }


        cancl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                cardPartyPaymentDialog(priceDetail, Integer.parseInt(subscriptionId));



            }
        });

        spine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (packageWP.getVisibility() == View.VISIBLE) {
                    packageWP.setVisibility(View.GONE);
                } else {
                    packageWP.setVisibility(View.VISIBLE);
                }

            }
        });

        dialog.show();


    }


    private void setCardDetails() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
        String Userid = preferences.getString("userid", "");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.data_loading));
        progressDialog.show();

        final checkout task = new checkout();
        task.setUserid(Userid);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.showCards(task).enqueue(new Callback<List<GetCardModel>>() {
            @Override
            public void onResponse(Call<List<GetCardModel>> call, Response<List<GetCardModel>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().size() > 0) {
                        list = response.body();
                        saveCardData(list);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<GetCardModel>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyFreezeDialog.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveCardData(List<GetCardModel> list) {
        try {


            for (int i = 0; i < list.size(); i++) {
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


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void sendUpgradeData(final String priceDetail, String priceIdDetail, String subscriptionId, String userId, final GetCardModel model, Dialog dialogMain) {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        final FreezePackageModel task = new FreezePackageModel();
        task.setUserid(userId);
        task.setSubscriptionid(subscriptionId);
        task.setFormtype("Upgrade");
        task.setPackageid(priceIdDetail);
        task.setPackageprice(priceDetail);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.FreezePackage(task).enqueue(new Callback<ParcelDataModel>() {
            @Override
            public void onResponse(Call<ParcelDataModel> call, Response<ParcelDataModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getData().size() > 0) {
                        upgradeID = response.body().getData().get(0).getId();
                    }
                    if (!priceDetail.isEmpty()) {
                      /*  PayPalPayment payment = new PayPalPayment(new BigDecimal(priceDetail), "USD", "Payable Amount",
                                PayPalPayment.PAYMENT_INTENT_SALE);
                        //Creating Paypal Payment activity intent
                        Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                        //putting the paypal configuration to the intent
                        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                        //Puting paypal payment to the intent
                        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                        //Starting the intent activity for result
                        //the request code will be used on the method onActivityResult
                        startActivityForResult(intent, PAYPAL_REQUEST_CODE);*/
                        sendCyberPay(priceDetail, upgradeID,model);


                    }

                    // recyclerView.setNestedScrollingEnabled(false);
                    //  parcelmenu.setAdapter(new PartyBoxAdapter(parcel, R.layout.addcart, PartyBox.this));

                }
            }

            @Override
            public void onFailure(Call<ParcelDataModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    void sendCyberPay(String priceDetail, final String upgradeID, GetCardModel model) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
        String cardValue = preferences.getString(Constants.CARD_VALUE, "");
        String cvcValue = preferences.getString(Constants.CVC_VALUE, "");
        String expiryMonth = preferences.getString(Constants.EXPIRY_MONTH, "");
        String expiryYear = preferences.getString(Constants.EXPIRY_YEAR, "");
        String userFirstName = preferences.getString("userFirstName", "");
        String userLastName = preferences.getString("userLastName", "");
        String userCompanyName = preferences.getString("userCompanyName", "");
        String userEmailId = preferences.getString("userEmailId", "");

        final ProgressDialog dialog = new ProgressDialog(MyFreezeDialog.this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final CyberData task = new CyberData();
        task.setAccountNumber(model.getCard_number());
        task.setExpirationMonth(model.getCard_month());
        task.setExpirationYear(model.getCard_year());
        task.setCvNumber(model.getCvc());
        task.setFirstName(userFirstName);
        task.setLastName(userLastName);
        task.setStreet1(userCompanyName);
        task.setAmount(String.valueOf(priceDetail));
        task.setEmail(userEmailId);
        task.setCheck_id(String.valueOf(upgradeID));


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
                        sendUpgradeDataToApi("", upgradeID);
                    } else if (responseCode.equalsIgnoreCase("101")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Declined - The request is missing one or more fields", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("102")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Declined - One or more fields in the request contains invalid data.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("150")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Error - General CyberSource system failure.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("151")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Error - The request was received but there was a server timeout. This error does not include timeouts between the client and the server.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("152")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Error: The request was received, but a service did not finish running in time.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("204")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - Insufficient funds in the account.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("207")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - Issuing bank unavailable.'", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("208")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - Inactive card or card not authorized for card-not-present transactions.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("209")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - card verification number (CVV) did not match.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("210")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - The card has reached the credit limit.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("211")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - Invalid Card Verification Number (CVV).", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("221")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - The customer matched an entry on the processor negative file.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("222")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - customer account is frozen", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("231")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - Invalid account number", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("232")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - The card type is not accepted by the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("240")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Decline - The card type sent is invalid or does not correlate with the credit card number.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("250")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Error - The request was received, but there was a timeout at the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("480")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "The order is marked for review by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("481")) {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "The order has been rejected by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else {
                        hitGatewayCancel();
                        makeText(MyFreezeDialog.this, "Payment has been cancelled", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MyFreezeDialog.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }


    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        listPrice = new ArrayList<>();
        listPriceId = new ArrayList<>();
        if (canupgrade.size() > 0) {
            for (int i = 0; i <= canupgrade.size() - 1; i++) {
                list.add(canupgrade.get(i).getTitleEN());
                if (duration.equalsIgnoreCase("breakfast")) {
                    listPrice.add(canupgrade.get(i).getBreakfast());
                    listPriceId.add(canupgrade.get(i).getId());
                } else if (duration.equalsIgnoreCase("lunch")) {
                    listPrice.add(canupgrade.get(i).getLunch());
                    listPriceId.add(canupgrade.get(i).getId());
                } else {
                    listPrice.add(canupgrade.get(i).getBothh());
                    listPriceId.add(canupgrade.get(i).getId());
                }

            }
        }

        return list;
    }

    public void FreezePackageNew() {
    }



    public void FreezePackage() {
        TextView cancel;


        Button send, cancel_button;
        TextView title, emails;
        final TextView spine, spin;
        final EditText etdate;


        dialog.setContentView(R.layout.freezepackage);

        spine = dialog.findViewById(R.id.spine);
        spin = dialog.findViewById(R.id.spin);
        send = dialog.findViewById(R.id.send_button);
        title = dialog.findViewById(R.id.title);
        emails = dialog.findViewById(R.id.emails);
        cancel_button = dialog.findViewById(R.id.cancel_button);
        spin.setText(endDate);
        spine.setText(startDate);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");

            title.setTypeface(custom_font_azab);
            emails.setTypeface(custom_font_azab);
            cancel_button.setTypeface(custom_font_azab);
            spine.setTypeface(custom_font_azab);
            send.setTypeface(custom_font_azab);
        }
        dialog.show();
        spine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(spine);
            }
        });
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndDatePickerDialog(spin);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFreezeData(subscriptionId, userId, spine.getText().toString().trim(), spin.getText().toString().trim());
                dialog.dismiss();
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void sendFreezeData(String subscriptionId, String userId, String startDate, String endDate) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        final FreezePackageModel task = new FreezePackageModel();
        task.setUserid(userId);
        task.setSubscriptionid(subscriptionId);
        task.setFormtype("Freeze");
        task.setFreezesdate(startDate);
        task.setFreezeedate(endDate);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.FreezePackage(task).enqueue(new Callback<ParcelDataModel>() {
            @Override
            public void onResponse(Call<ParcelDataModel> call, Response<ParcelDataModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(MyFreezeDialog.this, getString(R.string.package_freeze), Toast.LENGTH_SHORT).show();
                    ParcelCafe();
                    // recyclerView.setNestedScrollingEnabled(false);
                    //  parcelmenu.setAdapter(new PartyBoxAdapter(parcel, R.layout.addcart, PartyBox.this));

                }
            }

            @Override
            public void onFailure(Call<ParcelDataModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Create and show a DatePickerDialog when click button.
    private void showDatePickerDialog(final TextView spine) {
        // Get open DatePickerDialog button.
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                StringBuffer strBuf = new StringBuffer();
                strBuf.append(year);
                strBuf.append("-");
                strBuf.append(month + 1);
                strBuf.append("-");
                strBuf.append(dayOfMonth);
                spine.setText(strBuf.toString());
                startDateMin = dayOfMonth + "/" + month + "/" + year;
              /*  TextView datePickerValueTextView = (TextView)findViewById(R.id.datePickerValue);
                datePickerValueTextView.setText(strBuf.toString());*/
            }
        };

        long startDateLong = 0, endDateLong = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(startDate);

            startDateLong = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(endDate);

            endDateLong = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Get current year, month and day.
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        // Create the new DatePickerDialog instance.
        DatePickerDialog datePickerDialog = new DatePickerDialog(MyFreezeDialog.this, onDateSetListener, year, month, day);

        datePickerDialog.setTitle("Please select date.");
        datePickerDialog.getDatePicker().setMaxDate(endDateLong);
        datePickerDialog.getDatePicker().setMinDate(startDateLong);
        // Popup the dialog.
        datePickerDialog.show();
    }


    // Create and show a DatePickerDialog when click button.
    // Create and show a DatePickerDialog when click button.
    private void showEndDatePickerDialog(final TextView spin) {
        // Get open DatePickerDialog button.


        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                StringBuffer strBuf = new StringBuffer();
                strBuf.append(year);
                strBuf.append("-");
                strBuf.append(month + 1);
                strBuf.append("-");
                strBuf.append(dayOfMonth);
                spin.setText(strBuf.toString());


              /*  TextView datePickerValueTextView = (TextView)findViewById(R.id.datePickerValue);
                datePickerValueTextView.setText(strBuf.toString());*/
            }
        };

        long startDateLong = 0, endDateLong = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(startDate);

            startDateLong = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(endDate);

            endDateLong = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Get current year, month and day.
        DatePickerDialog datePickerDialog;

        if (!startDateMin.isEmpty()) {
            String getfromdate = startDateMin.trim();
            String[] getfrom = getfromdate.split("/");
            int year, month, day;
            year = Integer.parseInt(getfrom[2]);
            month = Integer.parseInt(getfrom[1]);
            day = Integer.parseInt(getfrom[0]);
            final Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            datePickerDialog = new DatePickerDialog(MyFreezeDialog.this, onDateSetListener, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        } else {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);


            datePickerDialog = new DatePickerDialog(MyFreezeDialog.this, onDateSetListener, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(startDateLong);
        }


        // Create the new DatePickerDialog instance.

        datePickerDialog.setTitle("Please select date.");
        datePickerDialog.getDatePicker().setMaxDate(endDateLong);

        // Popup the dialog.
        datePickerDialog.show();
    }


    private void datePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        Toast.makeText(getApplicationContext(), "You have Choose " + date_time, Toast.LENGTH_LONG).show();
                        date = date_time;
                        Intent intent = new Intent(MyFreezeDialog.this, CheckoutPage.class);
                        startActivity(intent);
                        //*************Call Time Picker Here ********************
                        // tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    private void Package() {


        HomeCollection.date_collection_arr = new ArrayList<HomeCollection>();
        // Toast.makeText(getApplicationContext(), "" + maintitle + titledate, Toast.LENGTH_LONG).show();
        // HomeCollection.date_collection_arr.add(new HomeCollection("2019-04-22", "Weekly", "Weekly Food", "this is title"));
        //HomeCollection.date_collection_arr.add(new HomeCollection(""+titledate, "Weekly", "Weekly Food", maintitle2,images1));
        // HomeCollection.date_collection_arr.add(new HomeCollection("2019-04-21", Title, "Weekly Food", maintitle1));
        //HomeCollection.date_collection_arr.add(new HomeCollection(titledate, Title, "Weekly Food", maintitle,images1));
       /* HomeCollection.date_collection_arr.add( new HomeCollection("2019-04-11" ,"Weekly","Weekly Food","this is Your Food List"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2019-04-12" ,"weekly off","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-13" ,"Events","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-01-14" ,"Dasahara","Holiday","this is holiday"));
        HomeCollection.date_collection_arr.add( new HomeCollection("2018-02-15" ,"Christmas","Holiday","this is holiday"));*/


        // Price = preferences.getString("price", "");
        List<Subscription> subscriptions = new ArrayList<>();
        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        hwAdapter = new HwAdapterSubcription(this, cal_month, HomeCollection.date_collection_arr, subscriptions, restDays);

        tv_month = findViewById(R.id.tv_month);
        title = findViewById(R.id.title);
        title.setText(Title);


        linearLayout = findViewById(R.id.ok);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                startActivity(intent);
            }
        });

        ImageButton previous = findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2017) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(MyFreezeDialog.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                } else {
                    setPreviousMonth();
                    refreshCalendar();
                }


            }
        });
        ImageButton next = findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 5 && cal_month.get(GregorianCalendar.YEAR) == 2018) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(getApplicationContext(), "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                } else {
                    setNextMonth();
                    refreshCalendar();
                }
            }
        });
        GridView gridview = findViewById(R.id.gv_calendar);
        gridview.setAdapter(hwAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                try {
                    if (HwAdapter.day_string.size() > 0) {
                        String selectedGridDate = HwAdapter.day_string.get(position);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date strDate = null;
                        Date strDate1 = null;
                        try {
                            strDate = sdf.parse(selectedGridDate);
                            strDate1 = sdf.parse(formattedDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        for(int i=0;i<HomeCollection.date_collection_arr.size();i++)
                        {
                            HomeCollection cal_obj = HomeCollection.date_collection_arr.get(i);
                            Log.e("strDate---","strDate----3---1---"+cal_obj.date+"---"+cal_obj.freeze);
                            if(selectedGridDate.equalsIgnoreCase(cal_obj.date))
                            {
                                if(cal_obj.freeze.equalsIgnoreCase("Yes"))
                                {
                                    showFreezeDialog(cal_obj.date,subscriptionId);
                                }else{
                                    showFrozenDialog(cal_obj.date,subscriptionId);
                                }
                                break;
                            }
                        }

                        //                        if (strDate.after(strDate1)) {
//                            ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyFreezeDialog.this);
//                        }


                       /* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date strDate = sdf.parse(selectedGridDate);
                        if (System.currentTimeMillis() > strDate.getTime()) {
                            your_date_is_outdated = true;
                        } else {
                            your_date_is_outdated = false;
                            ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyAccountHome.this);

                        }*/

                     /*   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        long parsedMillis = sdf.parse(selectedGridDate).getTime();
                        long now = System.currentTimeMillis(); // 22:54:15

                        if (parsedMillis > now) {
                            ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyAccountHome.this);

                        }*/
                    }
                } catch (Exception ae) {
                    ae.printStackTrace();
                }


            }

        });
    }

    protected void setNextMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) + 1);
        }
    }

    protected void setPreviousMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
        }
    }

    public void refreshCalendar() {
        hwAdapter.refreshDays();
        hwAdapter.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));


        // Intent intent = new Intent(getApplicationContext(),CalenderView.class);
        // startActivity(intent);
       /* final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPackage().enqueue(new Callback<PackageModel>() {
            @Override
            public void onResponse(Call<PackageModel> call, Response<PackageModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<Datum> packages= response.body().getData();
                    String details= packages.get(0).getTitle();

                    GridLayoutManager latest = new GridLayoutManager(getApplicationContext(),2);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    foodmenu.setItemAnimator(new DefaultItemAnimator());
                    foodmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    foodmenu.setAdapter(new Packages(packages, R.layout.menuslayouts, getApplicationContext()));


                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+details,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PackageModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failure"+t.getMessage(),Toast.LENGTH_LONG).show();
            }*/
        //});
    }

    private void week() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final history task = new history();
        task.setUserid(Userid);
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetSubcribe(task).enqueue(new Callback<SubcribeModel>() {
            @Override
            public void onResponse(Call<SubcribeModel> call, Response<SubcribeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    canupgrade = response.body().getCanupgrade();
                    addressLimit = response.body().getAddressLimit();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("addressLimit", addressLimit);
                    editor.apply();
                    duration = response.body().getDuration();
                    List<Subscription> menu = response.body().getSubscription();
                    if (menu.size() > 0) {
                        subscriptionId = menu.get(0).getId();
                        userId = menu.get(0).getUserid();
                    }
                    if (response.body().getSubscription().get(0).getDuration().equals("Breakfast")) {


                        maintitle = response.body().getData().get(0).getMenu().getBdescription();
                        titledate = response.body().getData().get(0).getDate();
                        images1 = response.body().getData().get(0).getMenu().getBimage();


                        maintitle1 = response.body().getData().get(1).getMenu().getBdescription();
                        titledate1 = response.body().getData().get(1).getDate();
                        images2 = response.body().getData().get(1).getMenu().getBimage();

                        maintitle2 = response.body().getData().get(2).getMenu().getBdescription();
                        titledate2 = response.body().getData().get(2).getDate();
                        images3 = response.body().getData().get(2).getMenu().getBimage();

                        maintitle3 = response.body().getData().get(3).getMenu().getBdescription();
                        titledate3 = response.body().getData().get(3).getDate();
                        images4 = response.body().getData().get(3).getMenu().getBimage();


                        maintitle4 = response.body().getData().get(4).getMenu().getBdescription();
                        titledate4 = response.body().getData().get(4).getDate();
                        images5 = response.body().getData().get(4).getMenu().getBimage();

                    }

                    if (response.body().getSubscription().get(0).getDuration().equals("lunch")) {


                        maintitle = response.body().getData().get(0).getMenu().getLdescription();
                        titledate = response.body().getData().get(0).getDate();
                        images1 = response.body().getData().get(0).getMenu().getLimage();


                        maintitle1 = response.body().getData().get(1).getMenu().getLdescription();
                        titledate1 = response.body().getData().get(1).getDate();
                        images2 = response.body().getData().get(1).getMenu().getLimage();

                        maintitle2 = response.body().getData().get(2).getMenu().getLdescription();
                        titledate2 = response.body().getData().get(2).getDate();
                        images3 = response.body().getData().get(2).getMenu().getLimage();

                        maintitle3 = response.body().getData().get(3).getMenu().getLdescription();
                        titledate3 = response.body().getData().get(3).getDate();
                        images4 = response.body().getData().get(3).getMenu().getLimage();


                        maintitle4 = response.body().getData().get(4).getMenu().getLdescription();
                        titledate4 = response.body().getData().get(4).getDate();
                        images5 = response.body().getData().get(4).getMenu().getLimage();

                    }

                    if (response.body().getSubscription().get(0).getDuration().equals("both")) {


                        maintitle = response.body().getData().get(0).getMenu().getBdescription();
                        titledate = response.body().getData().get(0).getDate();
                        images1 = response.body().getData().get(0).getMenu().getBimage();
                        imageboth = response.body().getData().get(0).getMenu().getLimage();
                        titlemain = response.body().getData().get(0).getMenu().getLdescription();


                        maintitle1 = response.body().getData().get(1).getMenu().getBdescription();
                        titledate1 = response.body().getData().get(1).getDate();
                        images2 = response.body().getData().get(1).getMenu().getBimage();
                        imageboth1 = response.body().getData().get(1).getMenu().getLimage();
                        titlemain1 = response.body().getData().get(1).getMenu().getLdescription();

                        maintitle2 = response.body().getData().get(2).getMenu().getBdescription();
                        titledate2 = response.body().getData().get(2).getDate();
                        images3 = response.body().getData().get(2).getMenu().getBimage();
                        imageboth2 = response.body().getData().get(2).getMenu().getLimage();
                        titlemain2 = response.body().getData().get(2).getMenu().getLdescription();

                        maintitle3 = response.body().getData().get(3).getMenu().getBdescription();
                        titledate3 = response.body().getData().get(3).getDate();
                        images4 = response.body().getData().get(3).getMenu().getBimage();
                        imageboth3 = response.body().getData().get(3).getMenu().getLimage();
                        titlemain3 = response.body().getData().get(3).getMenu().getLdescription();


                        maintitle4 = response.body().getData().get(4).getMenu().getBdescription();
                        titledate4 = response.body().getData().get(4).getDate();
                        images5 = response.body().getData().get(4).getMenu().getBimage();
                        imageboth4 = response.body().getData().get(4).getMenu().getLimage();
                        titlemain4 = response.body().getData().get(4).getMenu().getLdescription();

                    }
                    /*else{
                        maintitle = response.body().getData().get(0).getMenu().getBdescription();
                        titledate = response.body().getData().get(0).getDate();
                        images1=response.body().getData().get(0).getMenu().getBimage();


                        maintitle1 = response.body().getData().get(1).getMenu().getBdescription();
                        titledate1 = response.body().getData().get(1).getDate();
                        images2=response.body().getData().get(1).getMenu().getBimage();

                        maintitle2 = response.body().getData().get(2).getMenu().getBdescription();
                        titledate2 = response.body().getData().get(2).getDate();
                        images3=response.body().getData().get(2).getMenu().getBimage();

                        maintitle3 = response.body().getData().get(3).getMenu().getBdescription();
                        titledate3 = response.body().getData().get(3).getDate();
                        images4=response.body().getData().get(3).getMenu().getBimage();


                        maintitle4 = response.body().getData().get(4).getMenu().getBdescription();
                        titledate4 = response.body().getData().get(4).getDate();
                        images5=response.body().getData().get(4).getMenu().getBimage();

                    }*/

                    //    Toast.makeText(getApplicationContext(), "Response" + maintitle + titledate, Toast.LENGTH_LONG).show();

                    HomeCollection.date_collection_arr = new ArrayList<HomeCollection>();
/*
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, maintitle, images1));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate1, maintitle1, titlemain1, maintitle1, images2));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate2, maintitle2, titlemain2, maintitle2, images3));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate3, maintitle3, titlemain3, maintitle3, images4));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate4, maintitle4, titlemain4, maintitle4, images5));
                 */

                    if (response.body().getData().size() > 0) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            titledate = response.body().getData().get(i).getDate();
                            HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, maintitle, images1, ""));
                        }
                    }

                    cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
                    cal_month_copy = (GregorianCalendar) cal_month.clone();
                    hwAdapter = new HwAdapterSubcription(MyFreezeDialog.this, cal_month, HomeCollection.date_collection_arr, response.body().getSubscription(), restDays);

                    tv_month = findViewById(R.id.tv_month);
                    title = findViewById(R.id.title);
                    title.setText(Title);
                    linearLayout = findViewById(R.id.ok);
                    tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                            startActivity(intent);
                        }
                    });

                    ImageButton previous = findViewById(R.id.ib_prev);
                    previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2017) {
                                cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(MyFreezeDialog.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                            } else {
                                setPreviousMonth();
                                refreshCalendar();
                            }


                        }
                    });
                    ImageButton next = findViewById(R.id.Ib_next);
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 5 && cal_month.get(GregorianCalendar.YEAR) == 2018) {
                                //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(getApplicationContext(), "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                            } else {
                                setNextMonth();
                                refreshCalendar();
                            }
                        }
                    });
                    GridView gridview = findViewById(R.id.gv_calendar);
                    gridview.setAdapter(hwAdapter);
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            // String selectedGridDate = HwAdapterSubcription.day_string.get(position);

                            if (HwAdapter.day_string.size() > 0) {
                                String selectedGridDate = HwAdapter.day_string.get(position);


                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                Date strDate = null;
                                Date strDate1 = null;
                                try {
                                    strDate = sdf.parse(selectedGridDate);
                                    strDate1 = sdf.parse(formattedDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                                for(int i=0;i<HomeCollection.date_collection_arr.size();i++)
                                {
                                    HomeCollection cal_obj = HomeCollection.date_collection_arr.get(i);
                                    Log.e("strDate---","strDate----3---1---"+cal_obj.date+"---"+cal_obj.freeze);
                                    if(selectedGridDate.equalsIgnoreCase(cal_obj.date))
                                    {
                                        if(cal_obj.freeze.equalsIgnoreCase("Yes"))
                                        {
                                            showFreezeDialog(cal_obj.date,subscriptionId);
                                        }else{
                                            showFrozenDialog(cal_obj.date,subscriptionId);
                                        }
                                        break;
                                    }
                                }
//                                if (strDate.after(strDate1)) {
//                                    ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyFreezeDialog.this);
//                                }



                               /* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                Date strDate = null;
                                try {
                                    strDate = sdf.parse(selectedGridDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (System.currentTimeMillis() > strDate.getTime()) {
                                    your_date_is_outdated = true;
                                } else {
                                    your_date_is_outdated = false;
                                    ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyAccountHome.this);
                                }*/

                               /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                long parsedMillis = 0;
                                try {
                                    parsedMillis = sdf.parse(selectedGridDate).getTime();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                long now = System.currentTimeMillis(); // 22:54:15

                                if (parsedMillis > now) {
                                    ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyAccountHome.this);

                                }*/
                            }


                        }

                    });
                }

            }

            @Override
            public void onFailure(Call<SubcribeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void ParcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final history task = new history();
        task.setUserid(Userid);
        task.setLang(languageValue);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetSubcribe(task).enqueue(new Callback<SubcribeModel>() {
            @Override
            public void onResponse(Call<SubcribeModel> call, Response<SubcribeModel> response) {
                if (response.isSuccessful()) {
                    subscribeNow = "no";
                    dialog.dismiss();
                    List<subcribe> menu = response.body().getData();

                    addressLimit = response.body().getAddressLimit();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    restDays = 0;
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        if (!response.body().getData().get(i).getFrozen().equalsIgnoreCase("yes")) {
                            restDays = restDays + 1;
                        }
                    }

                    editor.putString("addressLimit", addressLimit);
                    editor.apply();
                    upgradePackage = response.body().getCanupgrade();
                    if (upgradePackage.size() > 0) {
                        upgradeIt = "yes";
                    } else {
                        upgradeIt = "no";
                    }
                    List<Subscription> subscriptionList = response.body().getSubscription();
                    if (subscriptionList.size() > 0) {
                        subscriptionId = subscriptionList.get(0).getId();
                        userId = subscriptionList.get(0).getUserid();
                        canFreeze = subscriptionList.get(0).getFreeze();

                    }
                    canupgrade = response.body().getCanupgrade();
                    duration = response.body().getDuration();
                    if (response.body().getSubscription().get(0).getPackagename().equals("Weekly")) {
                        //  week();

                    }
                    if (response.body().getSubscription().get(0).getPackagename().equals("3 Months")) {

                        if (response.body().getSubscription().get(0).getDuration().equals("Breakfast")) {

                            maintitle = response.body().getData().get(0).getMenu().getBdescription();
                            titledate = response.body().getData().get(0).getDate();
                            images1 = response.body().getData().get(0).getMenu().getBimage();

                            maintitle1 = response.body().getData().get(1).getMenu().getBdescription();
                            titledate1 = response.body().getData().get(1).getDate();
                            images2 = response.body().getData().get(1).getMenu().getBimage();

                            maintitle2 = response.body().getData().get(2).getMenu().getBdescription();
                            titledate2 = response.body().getData().get(2).getDate();
                            images3 = response.body().getData().get(2).getMenu().getBimage();

                            maintitle3 = response.body().getData().get(3).getMenu().getBdescription();
                            titledate3 = response.body().getData().get(3).getDate();
                            images4 = response.body().getData().get(3).getMenu().getBimage();


                            maintitle4 = response.body().getData().get(4).getMenu().getBdescription();
                            titledate4 = response.body().getData().get(4).getDate();
                            images5 = response.body().getData().get(4).getMenu().getBimage();

                            weekstitle1 = response.body().getData().get(5).getMenu().getBdescription();
                            weeksdate1 = response.body().getData().get(5).getDate();
                            weeksimage1 = response.body().getData().get(5).getMenu().getBimage();


                            weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                            weeksdate2 = response.body().getData().get(6).getDate();
                            weeksimage2 = response.body().getData().get(6).getMenu().getBimage();

                            weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                            weeksdate3 = response.body().getData().get(7).getDate();
                            weeksimage3 = response.body().getData().get(7).getMenu().getBimage();

                            weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                            weeksdate4 = response.body().getData().get(8).getDate();
                            weeksimage4 = response.body().getData().get(8).getMenu().getBimage();

                            weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                            weeksdate5 = response.body().getData().get(9).getDate();
                            weeksimage5 = response.body().getData().get(9).getMenu().getBimage();

                            monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                            monthdate1 = response.body().getData().get(10).getDate();
                            monthimage1 = response.body().getData().get(10).getMenu().getBimage();


                            monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                            monthdate2 = response.body().getData().get(11).getDate();
                            monthimage2 = response.body().getData().get(11).getMenu().getBimage();

                            monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                            monthdate3 = response.body().getData().get(12).getDate();
                            monthimage3 = response.body().getData().get(12).getMenu().getBimage();

                            monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                            monthdate4 = response.body().getData().get(13).getDate();
                            monthimage4 = response.body().getData().get(13).getMenu().getBimage();

                            monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                            monthdate5 = response.body().getData().get(14).getDate();
                            monthimage5 = response.body().getData().get(14).getMenu().getBimage();

                            monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                            monthdate6 = response.body().getData().get(15).getDate();
                            monthimage6 = response.body().getData().get(16).getMenu().getBimage();


                            monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                            monthdate7 = response.body().getData().get(16).getDate();
                            monthimage7 = response.body().getData().get(16).getMenu().getBimage();

                            monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                            monthdate8 = response.body().getData().get(17).getDate();
                            monthimage8 = response.body().getData().get(17).getMenu().getBimage();

                            monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                            monthdate9 = response.body().getData().get(18).getDate();
                            monthimage9 = response.body().getData().get(18).getMenu().getBimage();

                            monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                            monthdate10 = response.body().getData().get(19).getDate();
                            monthimage10 = response.body().getData().get(19).getMenu().getBimage();


                            monthtitle11 = response.body().getData().get(20).getMenu().getBdescription();
                            monthdate11 = response.body().getData().get(20).getDate();
                            monthimage11 = response.body().getData().get(20).getMenu().getBimage();


                            monthtitle12 = response.body().getData().get(21).getMenu().getBdescription();
                            monthdate12 = response.body().getData().get(21).getDate();
                            monthimage12 = response.body().getData().get(21).getMenu().getBimage();

                            monthtitle13 = response.body().getData().get(22).getMenu().getBdescription();
                            monthdate13 = response.body().getData().get(22).getDate();
                            monthimage13 = response.body().getData().get(22).getMenu().getBimage();

                            monthtitle14 = response.body().getData().get(23).getMenu().getBdescription();
                            monthdate14 = response.body().getData().get(23).getDate();
                            monthimage14 = response.body().getData().get(23).getMenu().getBimage();


                            monthtitle15 = response.body().getData().get(24).getMenu().getBdescription();
                            monthdate15 = response.body().getData().get(24).getDate();
                            monthimage15 = response.body().getData().get(24).getMenu().getBimage();

                            monthtitle16 = response.body().getData().get(25).getMenu().getBdescription();
                            monthdate16 = response.body().getData().get(25).getDate();
                            monthimage16 = response.body().getData().get(25).getMenu().getBimage();


                            monthtitle17 = response.body().getData().get(26).getMenu().getBdescription();
                            monthdate17 = response.body().getData().get(26).getDate();
                            monthimage17 = response.body().getData().get(26).getMenu().getBimage();

                            monthtitle18 = response.body().getData().get(27).getMenu().getBdescription();
                            monthdate18 = response.body().getData().get(27).getDate();
                            monthimage18 = response.body().getData().get(27).getMenu().getBimage();

                            monthtitle19 = response.body().getData().get(28).getMenu().getBdescription();
                            monthdate19 = response.body().getData().get(28).getDate();
                            monthimage19 = response.body().getData().get(28).getMenu().getBimage();

                            monthtitle20 = response.body().getData().get(29).getMenu().getBdescription();
                            monthdate20 = response.body().getData().get(29).getDate();
                            monthimage20 = response.body().getData().get(29).getMenu().getBimage();

                            monthtitle21 = response.body().getData().get(30).getMenu().getBdescription();
                            monthdate21 = response.body().getData().get(30).getDate();
                            monthimage21 = response.body().getData().get(30).getMenu().getBimage();


                            monthtitle22 = response.body().getData().get(31).getMenu().getBdescription();
                            monthdate22 = response.body().getData().get(31).getDate();
                            monthimage22 = response.body().getData().get(31).getMenu().getBimage();

                            monthtitle23 = response.body().getData().get(32).getMenu().getBdescription();
                            monthdate23 = response.body().getData().get(32).getDate();
                            monthimage23 = response.body().getData().get(32).getMenu().getBimage();

                            monthtitle24 = response.body().getData().get(33).getMenu().getBdescription();
                            monthdate24 = response.body().getData().get(33).getDate();
                            monthimage24 = response.body().getData().get(33).getMenu().getBimage();

                            monthtitle25 = response.body().getData().get(34).getMenu().getBdescription();
                            monthdate25 = response.body().getData().get(34).getDate();
                            monthimage25 = response.body().getData().get(34).getMenu().getBimage();

                            monthtitle26 = response.body().getData().get(35).getMenu().getBdescription();
                            monthdate26 = response.body().getData().get(35).getDate();
                            monthimage26 = response.body().getData().get(35).getMenu().getBimage();


                            monthtitle27 = response.body().getData().get(36).getMenu().getBdescription();
                            monthdate27 = response.body().getData().get(36).getDate();
                            monthimage27 = response.body().getData().get(36).getMenu().getBimage();

                            monthtitle28 = response.body().getData().get(37).getMenu().getBdescription();
                            monthdate28 = response.body().getData().get(37).getDate();
                            monthimage28 = response.body().getData().get(37).getMenu().getBimage();

                            monthtitle29 = response.body().getData().get(38).getMenu().getBdescription();
                            monthdate29 = response.body().getData().get(38).getDate();
                            monthimage29 = response.body().getData().get(38).getMenu().getBimage();

                            monthtitle30 = response.body().getData().get(39).getMenu().getBdescription();
                            monthdate30 = response.body().getData().get(39).getDate();
                            monthimage30 = response.body().getData().get(39).getMenu().getBimage();


                        }
                    }

                    if (response.body().getSubscription().get(0).getPackagename().equals("3 Months")) {


                        if (response.body().getSubscription().get(0).getDuration().equals("lunch")) {


                            maintitle = response.body().getData().get(0).getMenu().getLdescription();
                            titledate = response.body().getData().get(0).getDate();
                            images1 = response.body().getData().get(0).getMenu().getLimage();


                            maintitle1 = response.body().getData().get(1).getMenu().getLdescription();
                            titledate1 = response.body().getData().get(1).getDate();
                            images2 = response.body().getData().get(1).getMenu().getLimage();

                            maintitle2 = response.body().getData().get(2).getMenu().getLdescription();
                            titledate2 = response.body().getData().get(2).getDate();
                            images3 = response.body().getData().get(2).getMenu().getLimage();

                            maintitle3 = response.body().getData().get(3).getMenu().getLdescription();
                            titledate3 = response.body().getData().get(3).getDate();
                            images4 = response.body().getData().get(3).getMenu().getLimage();


                            maintitle4 = response.body().getData().get(4).getMenu().getLdescription();
                            titledate4 = response.body().getData().get(4).getDate();
                            images5 = response.body().getData().get(4).getMenu().getLimage();

                            weekstitle1 = response.body().getData().get(5).getMenu().getLdescription();
                            weeksdate1 = response.body().getData().get(5).getDate();
                            weeksimage1 = response.body().getData().get(5).getMenu().getLimage();


                            weekstitle2 = response.body().getData().get(6).getMenu().getLdescription();
                            weeksdate2 = response.body().getData().get(6).getDate();
                            weeksimage2 = response.body().getData().get(6).getMenu().getLimage();

                            weekstitle3 = response.body().getData().get(7).getMenu().getLdescription();
                            weeksdate3 = response.body().getData().get(7).getDate();
                            weeksimage3 = response.body().getData().get(7).getMenu().getLimage();

                            weekstitle4 = response.body().getData().get(8).getMenu().getLdescription();
                            weeksdate4 = response.body().getData().get(8).getDate();
                            weeksimage4 = response.body().getData().get(8).getMenu().getLimage();

                            weekstitle5 = response.body().getData().get(9).getMenu().getLdescription();
                            weeksdate5 = response.body().getData().get(9).getDate();
                            weeksimage5 = response.body().getData().get(9).getMenu().getLimage();

                            monthtitle1 = response.body().getData().get(10).getMenu().getLdescription();
                            monthdate1 = response.body().getData().get(10).getDate();
                            monthimage1 = response.body().getData().get(10).getMenu().getLimage();


                            monthtitle2 = response.body().getData().get(11).getMenu().getLdescription();
                            monthdate2 = response.body().getData().get(11).getDate();
                            monthimage2 = response.body().getData().get(11).getMenu().getLimage();

                            monthtitle3 = response.body().getData().get(12).getMenu().getLdescription();
                            monthdate3 = response.body().getData().get(12).getDate();
                            monthimage3 = response.body().getData().get(12).getMenu().getLimage();

                            monthtitle4 = response.body().getData().get(13).getMenu().getLdescription();
                            monthdate4 = response.body().getData().get(13).getDate();
                            monthimage4 = response.body().getData().get(13).getMenu().getLimage();

                            monthtitle5 = response.body().getData().get(14).getMenu().getLdescription();
                            monthdate5 = response.body().getData().get(14).getDate();
                            monthimage5 = response.body().getData().get(14).getMenu().getLimage();

                            monthtitle6 = response.body().getData().get(15).getMenu().getLdescription();
                            monthdate6 = response.body().getData().get(15).getDate();
                            monthimage6 = response.body().getData().get(16).getMenu().getLimage();


                            monthtitle7 = response.body().getData().get(16).getMenu().getLdescription();
                            monthdate7 = response.body().getData().get(16).getDate();
                            monthimage7 = response.body().getData().get(16).getMenu().getLimage();

                            monthtitle8 = response.body().getData().get(17).getMenu().getLdescription();
                            monthdate8 = response.body().getData().get(17).getDate();
                            monthimage8 = response.body().getData().get(17).getMenu().getLimage();

                            monthtitle9 = response.body().getData().get(18).getMenu().getLdescription();
                            monthdate9 = response.body().getData().get(18).getDate();
                            monthimage9 = response.body().getData().get(18).getMenu().getLimage();

                            monthtitle10 = response.body().getData().get(19).getMenu().getLdescription();
                            monthdate10 = response.body().getData().get(19).getDate();
                            monthimage10 = response.body().getData().get(19).getMenu().getLimage();

                            monthtitle11 = response.body().getData().get(20).getMenu().getLdescription();
                            monthdate11 = response.body().getData().get(20).getDate();
                            monthimage11 = response.body().getData().get(20).getMenu().getLimage();

                            monthtitle12 = response.body().getData().get(21).getMenu().getLdescription();
                            monthdate12 = response.body().getData().get(21).getDate();
                            monthimage12 = response.body().getData().get(21).getMenu().getLimage();

                            monthtitle13 = response.body().getData().get(22).getMenu().getLdescription();
                            monthdate13 = response.body().getData().get(22).getDate();
                            monthimage13 = response.body().getData().get(22).getMenu().getLimage();


                            monthtitle14 = response.body().getData().get(23).getMenu().getLdescription();
                            monthdate14 = response.body().getData().get(23).getDate();
                            monthimage14 = response.body().getData().get(23).getMenu().getLimage();

                            monthtitle15 = response.body().getData().get(24).getMenu().getLdescription();
                            monthdate15 = response.body().getData().get(24).getDate();
                            monthimage15 = response.body().getData().get(24).getMenu().getLimage();


                            monthtitle16 = response.body().getData().get(25).getMenu().getLdescription();
                            monthdate16 = response.body().getData().get(25).getDate();
                            monthimage16 = response.body().getData().get(25).getMenu().getLimage();

                            monthtitle17 = response.body().getData().get(26).getMenu().getLdescription();
                            monthdate17 = response.body().getData().get(26).getDate();
                            monthimage17 = response.body().getData().get(26).getMenu().getLimage();

                            monthtitle18 = response.body().getData().get(27).getMenu().getLdescription();
                            monthdate18 = response.body().getData().get(27).getDate();
                            monthimage18 = response.body().getData().get(27).getMenu().getLimage();

                            monthtitle19 = response.body().getData().get(28).getMenu().getLdescription();
                            monthdate19 = response.body().getData().get(28).getDate();
                            monthimage19 = response.body().getData().get(28).getMenu().getLimage();

                            monthtitle20 = response.body().getData().get(29).getMenu().getLdescription();
                            monthdate20 = response.body().getData().get(29).getDate();
                            monthimage20 = response.body().getData().get(29).getMenu().getLimage();


                            monthtitle21 = response.body().getData().get(30).getMenu().getLdescription();
                            monthdate21 = response.body().getData().get(30).getDate();
                            monthimage21 = response.body().getData().get(30).getMenu().getLimage();

                            monthtitle22 = response.body().getData().get(31).getMenu().getLdescription();
                            monthdate22 = response.body().getData().get(31).getDate();
                            monthimage22 = response.body().getData().get(31).getMenu().getLimage();

                            monthtitle23 = response.body().getData().get(32).getMenu().getLdescription();
                            monthdate23 = response.body().getData().get(32).getDate();
                            monthimage23 = response.body().getData().get(32).getMenu().getLimage();

                            monthtitle24 = response.body().getData().get(33).getMenu().getLdescription();
                            monthdate24 = response.body().getData().get(33).getDate();
                            monthimage24 = response.body().getData().get(33).getMenu().getLimage();

                            monthtitle25 = response.body().getData().get(34).getMenu().getLdescription();
                            monthdate25 = response.body().getData().get(34).getDate();
                            monthimage25 = response.body().getData().get(34).getMenu().getLimage();


                            monthtitle26 = response.body().getData().get(35).getMenu().getLdescription();
                            monthdate26 = response.body().getData().get(35).getDate();
                            monthimage26 = response.body().getData().get(35).getMenu().getLimage();

                            monthtitle27 = response.body().getData().get(36).getMenu().getLdescription();
                            monthdate27 = response.body().getData().get(36).getDate();
                            monthimage27 = response.body().getData().get(36).getMenu().getLimage();

                            monthtitle28 = response.body().getData().get(37).getMenu().getLdescription();
                            monthdate28 = response.body().getData().get(37).getDate();
                            monthimage28 = response.body().getData().get(37).getMenu().getLimage();

                            monthtitle29 = response.body().getData().get(38).getMenu().getLdescription();
                            monthdate29 = response.body().getData().get(38).getDate();
                            monthimage29 = response.body().getData().get(38).getMenu().getLimage();

                            monthtitle30 = response.body().getData().get(39).getMenu().getLdescription();
                            monthdate30 = response.body().getData().get(39).getDate();
                            monthimage30 = response.body().getData().get(39).getMenu().getLimage();


                        }


                        if (response.body().getSubscription().get(0).getPackagename().equals("3 Months")) {
                            if (response.body().getSubscription().get(0).getDuration().equals("Both")) {

                                maintitle = response.body().getData().get(0).getMenu().getBdescription();
                                titledate = response.body().getData().get(0).getDate();
                                images1 = response.body().getData().get(0).getMenu().getBimage();
                                imageboth = response.body().getData().get(0).getMenu().getLimage();
                                titlemain = response.body().getData().get(0).getMenu().getLdescription();


                                maintitle1 = response.body().getData().get(1).getMenu().getBdescription();
                                titledate1 = response.body().getData().get(1).getDate();
                                images2 = response.body().getData().get(1).getMenu().getBimage();
                                imageboth1 = response.body().getData().get(1).getMenu().getLimage();
                                titlemain1 = response.body().getData().get(1).getMenu().getLdescription();

                                maintitle2 = response.body().getData().get(2).getMenu().getBdescription();
                                titledate2 = response.body().getData().get(2).getDate();
                                images3 = response.body().getData().get(2).getMenu().getBimage();
                                imageboth2 = response.body().getData().get(2).getMenu().getLimage();
                                titlemain2 = response.body().getData().get(2).getMenu().getLdescription();

                                maintitle3 = response.body().getData().get(3).getMenu().getBdescription();
                                titledate3 = response.body().getData().get(3).getDate();
                                images4 = response.body().getData().get(3).getMenu().getBimage();
                                imageboth3 = response.body().getData().get(3).getMenu().getLimage();
                                titlemain3 = response.body().getData().get(3).getMenu().getLdescription();


                                maintitle4 = response.body().getData().get(4).getMenu().getBdescription();
                                titledate4 = response.body().getData().get(4).getDate();
                                images5 = response.body().getData().get(4).getMenu().getBimage();
                                imageboth4 = response.body().getData().get(4).getMenu().getLimage();
                                titlemain4 = response.body().getData().get(4).getMenu().getLdescription();

                                weekstitle1 = response.body().getData().get(5).getMenu().getBdescription();
                                weeksdate1 = response.body().getData().get(5).getDate();
                                weeksimage1 = response.body().getData().get(5).getMenu().getBimage();
                                weektitlemain1 = response.body().getData().get(5).getMenu().getLdescription();


                                weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                                weeksdate2 = response.body().getData().get(6).getDate();
                                weeksimage2 = response.body().getData().get(6).getMenu().getBimage();
                                weektitlemain2 = response.body().getData().get(6).getMenu().getLdescription();

                                weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                                weeksdate3 = response.body().getData().get(7).getDate();
                                weeksimage3 = response.body().getData().get(7).getMenu().getBimage();
                                weektitlemain3 = response.body().getData().get(7).getMenu().getLdescription();

                                weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                                weeksdate4 = response.body().getData().get(8).getDate();
                                weeksimage4 = response.body().getData().get(8).getMenu().getBimage();
                                weektitlemain4 = response.body().getData().get(8).getMenu().getLdescription();

                                weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                                weeksdate5 = response.body().getData().get(9).getDate();
                                weeksimage5 = response.body().getData().get(9).getMenu().getBimage();
                                weektitlemain5 = response.body().getData().get(9).getMenu().getLdescription();

                                monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                                monthdate1 = response.body().getData().get(10).getDate();
                                monthimage1 = response.body().getData().get(10).getMenu().getBimage();
                                monthtitlemain1 = response.body().getData().get(10).getMenu().getLdescription();


                                monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                                monthdate2 = response.body().getData().get(11).getDate();
                                monthimage2 = response.body().getData().get(11).getMenu().getBimage();
                                monthtitlemain2 = response.body().getData().get(11).getMenu().getLdescription();

                                monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                                monthdate3 = response.body().getData().get(12).getDate();
                                monthimage3 = response.body().getData().get(12).getMenu().getBimage();
                                monthtitlemain3 = response.body().getData().get(12).getMenu().getLdescription();

                                monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                                monthdate4 = response.body().getData().get(13).getDate();
                                monthimage4 = response.body().getData().get(13).getMenu().getBimage();
                                monthtitlemain4 = response.body().getData().get(13).getMenu().getLdescription();

                                monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                                monthdate5 = response.body().getData().get(14).getDate();
                                monthimage5 = response.body().getData().get(14).getMenu().getBimage();
                                monthtitlemain5 = response.body().getData().get(14).getMenu().getLdescription();

                                monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                                monthdate6 = response.body().getData().get(15).getDate();
                                monthimage6 = response.body().getData().get(15).getMenu().getBimage();
                                monthtitlemain6 = response.body().getData().get(15).getMenu().getLdescription();


                                monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                                monthdate7 = response.body().getData().get(16).getDate();
                                monthimage7 = response.body().getData().get(16).getMenu().getBimage();
                                monthtitlemain7 = response.body().getData().get(16).getMenu().getLdescription();

                                monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                                monthdate8 = response.body().getData().get(17).getDate();
                                monthimage8 = response.body().getData().get(17).getMenu().getBimage();
                                monthtitlemain8 = response.body().getData().get(17).getMenu().getLdescription();

                                monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                                monthdate9 = response.body().getData().get(18).getDate();
                                monthimage9 = response.body().getData().get(18).getMenu().getBimage();
                                monthtitlemain9 = response.body().getData().get(18).getMenu().getLdescription();

                                monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                                monthdate10 = response.body().getData().get(19).getDate();
                                monthimage10 = response.body().getData().get(19).getMenu().getBimage();
                                monthtitlemain10 = response.body().getData().get(19).getMenu().getLdescription();

                                monthtitle11 = response.body().getData().get(20).getMenu().getBdescription();
                                monthdate11 = response.body().getData().get(20).getDate();
                                monthimage11 = response.body().getData().get(20).getMenu().getBimage();
                                monthtitlemain11 = response.body().getData().get(20).getMenu().getLdescription();


                                monthtitle12 = response.body().getData().get(21).getMenu().getBdescription();
                                monthdate12 = response.body().getData().get(21).getDate();
                                monthimage12 = response.body().getData().get(21).getMenu().getBimage();
                                monthtitlemain12 = response.body().getData().get(21).getMenu().getLdescription();

                                monthtitle13 = response.body().getData().get(22).getMenu().getBdescription();
                                monthdate13 = response.body().getData().get(22).getDate();
                                monthimage13 = response.body().getData().get(22).getMenu().getBimage();
                                monthtitlemain13 = response.body().getData().get(22).getMenu().getLdescription();

                                monthtitle14 = response.body().getData().get(23).getMenu().getBdescription();
                                monthdate14 = response.body().getData().get(23).getDate();
                                monthimage14 = response.body().getData().get(23).getMenu().getBimage();
                                monthtitlemain14 = response.body().getData().get(23).getMenu().getLdescription();


                                monthtitle15 = response.body().getData().get(24).getMenu().getBdescription();
                                monthdate15 = response.body().getData().get(24).getDate();
                                monthimage15 = response.body().getData().get(24).getMenu().getBimage();
                                monthtitlemain15 = response.body().getData().get(24).getMenu().getLdescription();

                                monthtitle16 = response.body().getData().get(25).getMenu().getBdescription();
                                monthdate16 = response.body().getData().get(25).getDate();
                                monthimage16 = response.body().getData().get(25).getMenu().getBimage();
                                monthtitlemain16 = response.body().getData().get(25).getMenu().getLdescription();


                                monthtitle17 = response.body().getData().get(26).getMenu().getBdescription();
                                monthdate17 = response.body().getData().get(26).getDate();
                                monthimage17 = response.body().getData().get(26).getMenu().getBimage();
                                monthtitlemain17 = response.body().getData().get(26).getMenu().getLdescription();

                                monthtitle18 = response.body().getData().get(27).getMenu().getBdescription();
                                monthdate18 = response.body().getData().get(27).getDate();
                                monthimage18 = response.body().getData().get(27).getMenu().getBimage();
                                monthtitlemain18 = response.body().getData().get(27).getMenu().getLdescription();

                                monthtitle19 = response.body().getData().get(28).getMenu().getBdescription();
                                monthdate19 = response.body().getData().get(28).getDate();
                                monthimage19 = response.body().getData().get(28).getMenu().getBimage();
                                monthtitlemain19 = response.body().getData().get(28).getMenu().getLdescription();

                                monthtitle20 = response.body().getData().get(29).getMenu().getBdescription();
                                monthdate20 = response.body().getData().get(29).getDate();
                                monthimage20 = response.body().getData().get(29).getMenu().getBimage();
                                monthtitlemain20 = response.body().getData().get(29).getMenu().getLdescription();

                                monthtitle21 = response.body().getData().get(30).getMenu().getBdescription();
                                monthdate21 = response.body().getData().get(30).getDate();
                                monthimage21 = response.body().getData().get(30).getMenu().getBimage();
                                monthtitlemain21 = response.body().getData().get(30).getMenu().getLdescription();


                                monthtitle22 = response.body().getData().get(31).getMenu().getBdescription();
                                monthdate22 = response.body().getData().get(31).getDate();
                                monthimage22 = response.body().getData().get(31).getMenu().getBimage();
                                monthtitlemain22 = response.body().getData().get(31).getMenu().getLdescription();

                                monthtitle23 = response.body().getData().get(32).getMenu().getBdescription();
                                monthdate23 = response.body().getData().get(32).getDate();
                                monthimage23 = response.body().getData().get(32).getMenu().getBimage();
                                monthtitlemain23 = response.body().getData().get(32).getMenu().getLdescription();

                                monthtitle24 = response.body().getData().get(33).getMenu().getBdescription();
                                monthdate24 = response.body().getData().get(33).getDate();
                                monthimage24 = response.body().getData().get(33).getMenu().getBimage();
                                monthtitlemain24 = response.body().getData().get(33).getMenu().getLdescription();

                                monthtitle25 = response.body().getData().get(34).getMenu().getBdescription();
                                monthdate25 = response.body().getData().get(34).getDate();
                                monthimage25 = response.body().getData().get(34).getMenu().getBimage();
                                monthtitlemain25 = response.body().getData().get(34).getMenu().getLdescription();

                                monthtitle26 = response.body().getData().get(35).getMenu().getBdescription();
                                monthdate26 = response.body().getData().get(35).getDate();
                                monthimage26 = response.body().getData().get(35).getMenu().getBimage();
                                monthtitlemain26 = response.body().getData().get(35).getMenu().getLdescription();


                                monthtitle27 = response.body().getData().get(36).getMenu().getBdescription();
                                monthdate27 = response.body().getData().get(36).getDate();
                                monthimage27 = response.body().getData().get(36).getMenu().getBimage();
                                monthtitlemain27 = response.body().getData().get(36).getMenu().getLdescription();

                                monthtitle28 = response.body().getData().get(37).getMenu().getBdescription();
                                monthdate28 = response.body().getData().get(37).getDate();
                                monthimage8 = response.body().getData().get(17).getMenu().getBimage();
                                monthtitlemain28 = response.body().getData().get(37).getMenu().getLdescription();

                                monthtitle29 = response.body().getData().get(38).getMenu().getBdescription();
                                monthdate29 = response.body().getData().get(38).getDate();
                                monthimage29 = response.body().getData().get(38).getMenu().getBimage();
                                monthtitlemain29 = response.body().getData().get(38).getMenu().getLdescription();

                                monthtitle30 = response.body().getData().get(39).getMenu().getBdescription();
                                monthdate30 = response.body().getData().get(39).getDate();
                                monthimage30 = response.body().getData().get(39).getMenu().getBimage();
                                monthtitlemain30 = response.body().getData().get(39).getMenu().getLdescription();


                            }

                        }
                    }
                    if (response.body().getData().size() > 0) {
                        startDate = response.body().getData().get(0).getDate();
                        endDate = response.body().getData().get(response.body().getData().size() - 1).getDate();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            titledate = response.body().getData().get(i).getDate();
                            frozenValue = response.body().getData().get(i).getFrozen();
                            HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, maintitle, images1, frozenValue));
                        }
                    }

                  /*  if (response.body().getSubscription().get(0).getPackagename().isEmpty()) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            titledate = response.body().getData().get(i).getDate();
                            HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, maintitle, images1));
                        }

                    } else {
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, maintitle, images1));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate1, maintitle1, titlemain1, maintitle1, images2));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate2, maintitle2, titlemain2, maintitle2, images3));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate3, maintitle3, titlemain3, maintitle3, images4));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate4, maintitle4, titlemain4, maintitle4, images5));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate1, weekstitle1, weektitlemain1, weektitlemain1, weeksimage1));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate2, weekstitle2, weektitlemain2, weektitlemain2, weeksimage2));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate3, weekstitle3, weektitlemain3, weektitlemain3, weeksimage3));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate4, weekstitle4, weektitlemain4, weektitlemain4, weeksimage4));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate5, weekstitle5, weektitlemain5, weektitlemain5, weeksimage5));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate1, monthtitle1, monthtitlemain1, monthtitlemain1, monthimage1));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate2, monthtitle2, monthtitlemain2, monthtitlemain2, monthimage2));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate3, monthtitle3, monthtitlemain3, monthtitlemain3, monthimage3));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate4, monthtitle4, monthtitlemain4, monthtitlemain4, monthimage4));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate5, monthtitle5, monthtitlemain5, monthtitlemain5, monthimage5));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate6, monthtitle6, monthtitlemain6, monthtitlemain6, monthimage6));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate7, monthtitle7, monthtitlemain7, monthtitlemain7, monthimage7));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate8, monthtitle8, monthtitlemain8, monthtitlemain8, monthimage8));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate9, monthtitle9, monthtitlemain9, monthtitlemain9, monthimage9));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate10, monthtitle10, monthtitlemain10, monthtitlemain10, monthimage10));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate11, monthtitle11, monthtitlemain11, maintitle, monthimage11));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate12, monthtitle12, monthtitlemain12, maintitle1, monthimage12));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate13, monthtitle13, monthtitlemain13, maintitle2, monthimage13));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate14, monthtitle14, monthtitlemain14, maintitle3, monthimage14));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate15, monthtitle15, monthtitlemain15, maintitle4, monthimage15));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate16, monthtitle16, monthtitlemain16, weektitlemain1, monthimage16));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate17, monthtitle17, monthtitlemain17, weektitlemain2, monthimage17));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate18, monthtitle18, monthtitlemain18, weektitlemain3, monthimage18));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate19, monthtitle19, monthtitlemain19, weektitlemain4, monthimage19));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate20, monthtitle20, monthtitlemain20, weektitlemain5, monthimage20));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate21, monthtitle21, monthtitlemain21, monthtitlemain1, monthimage21));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate22, monthtitle22, monthtitlemain22, monthtitlemain2, monthimage22));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate23, monthtitle23, monthtitlemain23, monthtitlemain3, monthimage23));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate24, monthtitle24, monthtitlemain24, monthtitlemain4, monthimage24));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate25, monthtitle25, monthtitlemain25, monthtitlemain5, monthimage25));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate26, monthtitle26, monthtitlemain26, monthtitlemain6, monthimage26));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate27, monthtitle27, monthtitlemain27, monthtitlemain7, monthimage27));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate28, monthtitle28, monthtitlemain28, monthtitlemain8, monthimage28));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate29, monthtitle29, monthtitlemain29, monthtitlemain9, monthimage29));
                        HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate30, monthtitle30, monthtitlemain30, monthtitlemain10, monthimage30));

                    }
*/
                    // Toast.makeText(getApplicationContext(), "Response" + maintitle + titledate, Toast.LENGTH_LONG).show();

                    cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
                    cal_month_copy = (GregorianCalendar) cal_month.clone();
                    hwAdapter = new HwAdapterSubcription(MyFreezeDialog.this, cal_month, HomeCollection.date_collection_arr, response.body().getSubscription(), restDays);

                    tv_month = findViewById(R.id.tv_month);
                    title = findViewById(R.id.title);
                    title.setText(Title);
                    linearLayout = findViewById(R.id.ok);
                    tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                            startActivity(intent);
                        }
                    });

                    ImageButton previous = findViewById(R.id.ib_prev);
                    previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2017) {
                                cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(MyFreezeDialog.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                            } else {
                                setPreviousMonth();
                                refreshCalendar();
                            }


                        }
                    });
                    ImageButton next = findViewById(R.id.Ib_next);
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 5 && cal_month.get(GregorianCalendar.YEAR) == 2018) {
                                //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(MyFreezeDialog.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                            } else {
                                setNextMonth();
                                refreshCalendar();
                            }
                        }
                    });
                    GridView gridview = findViewById(R.id.gv_calendar);
                    gridview.setAdapter(hwAdapter);
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            String selectedGridDate = HwAdapterSubcription.day_string.get(position);

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date strDate = null;
                            Date strDate1 = null;
                            try {
                                strDate = sdf.parse(selectedGridDate);
                                strDate1 = sdf.parse(formattedDate);
                            } catch (ParseException e) {
                                Log.e("strDate---","strDate----3---exce--"+e);
                                e.printStackTrace();
                            }
                            Log.e("strDate---","strDate----3---"+selectedGridDate+"-----"+strDate1+"----"+strDate+"---"+position);


                            for(int i=0;i<HomeCollection.date_collection_arr.size();i++)
                            {
                                HomeCollection cal_obj = HomeCollection.date_collection_arr.get(i);
                                Log.e("strDate---","strDate----3---1---"+cal_obj.date+"---"+cal_obj.freeze);
                            if(selectedGridDate.equalsIgnoreCase(cal_obj.date))
                            {
                                if(cal_obj.freeze.equalsIgnoreCase("Yes"))
                                {
                                    showFreezeDialog(cal_obj.date,subscriptionId);
                                }else{
                                    showFrozenDialog(cal_obj.date,subscriptionId);
                                }
                                break;
                            }
                            }








//                            if (strDate.after(strDate1)) {
//                                ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyFreezeDialog.this);
//
//                            }


                          /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
                            try {
                                Date date1 = sdf.parse(selectedGridDate);
                                Date date2 = sdf.parse(formattedDate);
                                if(date1.after(date2))
                                {
                                    ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyAccountHome.this);

                                }
                            } catch (ParseException e) {


                            }*/





                           /* try {
                                if (HwAdapter.day_string.size() > 0) {
                                    String selectedGridDate = HwAdapter.day_string.get(position);
                                *//*     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    Date strDate = sdf.parse(selectedGridDate);
                                    if (System.currentTimeMillis() > strDate.getTime()) {
                                        your_date_is_outdated = true;
                                    }
                                    else{
                                        your_date_is_outdated = false;
                                        ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyAccountHome.this);
                                    }
*//*
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    long parsedMillis = sdf.parse(selectedGridDate).getTime();
                                    long now = System.currentTimeMillis(); // 22:54:15

                                    if (parsedMillis > now) {
                                        ((HwAdapterSubcription) parent.getAdapter()).getPositionList(selectedGridDate, MyAccountHome.this);

                                    }
                                }


                            } catch (Exception ae) {
                                ae.printStackTrace();
                            }*/

                        }

                    });
                }
            }


            @Override
            public void onFailure(Call<SubcribeModel> call, Throwable t) {
                dialog.dismiss();
                subscribeNow = "yes";
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.SUBSCRIBENOW, subscribeNow);
                editor.apply();
                // Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
                showSubscriberDialog();
            }
        });

    }

    private void showSubscriberDialog() {
        Button closeBT, forgotBT;
        final EditText emailET;
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_subscribe_now);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        emailET = dialog.findViewById(R.id.emailET);
        closeBT = dialog.findViewById(R.id.closeBT);
        forgotBT = dialog.findViewById(R.id.forgotBT);
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        forgotBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(MyFreezeDialog.this, ActivityMainHome.class);
                startActivity(intent);
            }
        });
        dialog.show();

    }


    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            // Intent refresh = new Intent(this, Home.class);
            //  refresh.putExtra(currentLang, localeName);
            //startActivity(refresh);
            // setLocale("fr");
        } else {
            Toast.makeText(MyFreezeDialog.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        if (picker.getId() == R.id.packageWP) {
            choose = data.toString();
            spine.setText(data.toString());
            picker.setVisibility(View.GONE);
            priceDetail = listPrice.get(position);
            priceIdDetail = listPriceId.get(position);
            priceText.setVisibility(View.VISIBLE);
            priceText.setText(getString(R.string.breakfast_and_lunch) + priceDetail);

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
                try {
                    String paymentDetails = confirm.toJSONObject().toString(4);
                    sendDataToApi(paymentDetails);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //if confirmation is not null

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
                hitGatewayCancel();

            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }

        if (requestCode == PAYPAL_QUANT_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                try {
                    String paymentDetails = confirm.toJSONObject().toString(4);
                    sendUpgradeDataToApi(paymentDetails, lastIdValue);
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

    private void sendDataToApi(final String paymentDetails) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String checkstatus = preferences.getString("status", "");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(upgradeID);
        task.setFoodtype(checkstatus);
        task.setPaytype("Upgrade");

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalSuccess(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {
                        Toast.makeText(MyFreezeDialog.this, "Package Upgraded Successfully!", Toast.LENGTH_SHORT).show();
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


    private void hitGatewayCancel() {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String checkstatus = preferences.getString("status", "");
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(String.valueOf(upgradeID));
        task.setFoodtype("");
        task.setPaytype("");
        task.setLang(languageValue);
        if (Userid.isEmpty()) {
            task.setIs_logged("No");
        } else {
            task.setIs_logged("Yes");
        }
        Log.e("payment check", " check Id  : " + upgradeID + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalCancel(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {

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

    private void sendUpgradeDataToApi(final String paymentDetails, String doubleId) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String checkstatus = preferences.getString("status", "");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(doubleId);
        task.setFoodtype(checkstatus);
        /*   task.setPaytype("Double");*/
        task.setPaytype("Upgrade");

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalSuccess(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {
                        Toast.makeText(MyFreezeDialog.this, "Your package has been upgraded!", Toast.LENGTH_SHORT).show();

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


    @Override
    public void paypalListener(Double aDouble, String lastId, String finalId, String foodtype, FoodData.Uptdatum finalUpdateData1) {
        lastIdValue = lastId;
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(aDouble)), "USD", "Payable Amount",
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


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
        String cardValue = preferences.getString(Constants.CARD_VALUE, "");
        String cvcValue = preferences.getString(Constants.CVC_VALUE, "");
        String expiryMonth = preferences.getString(Constants.EXPIRY_MONTH, "");
        String expiryYear = preferences.getString(Constants.EXPIRY_YEAR, "");
        String userFirstName = preferences.getString("userFirstName", "");
        String userLastName = preferences.getString("userLastName", "");
        String userCompanyName = preferences.getString("userCompanyName", "");
        String userEmailId = preferences.getString("userEmailId", "");


    }


    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


    public void cardPartyPaymentDialog(final String priceValue, final int lastId) {

        Button closeBT, nextBT;
        final EditText monthET, cardET, yearET, cvvET;
        final TextView cardTV, cvvTV, minuteTV, secondTV;
        RecyclerView cardRV;
        ConstraintLayout cardCL;
        dialog = new Dialog(this, android.R.style.Theme_Light);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
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
        cardRV = dialog.findViewById(R.id.cardRV);
        cardCL = dialog.findViewById(R.id.cardCL);
        minuteTV = dialog.findViewById(R.id.minuteTV);
        secondTV = dialog.findViewById(R.id.secondTV);
        cardCLView = cardCL;
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {

                minuteTV.setText("" + String.format("%d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                secondTV.setText("" + String.format(" %d",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                counter++;
            }

            public void onFinish() {
                startActivity(new Intent(MyFreezeDialog.this, MainActivity.class)
                        .putExtra("PaymentDetails", "")
                        .putExtra("PaymentAmount", ""));
            }
        }.start();
        ArrayList<String> years = new ArrayList<String>();
        ArrayList<String> monthsList = new ArrayList<String>();
        ArrayList<String> monthIntList = new ArrayList<String>();
        ArrayList<String> monthCurrentList = new ArrayList<String>();


        if (!Userid.isEmpty()) {
            showCards(Userid, cardRV);
        }

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
        monthSpin.setAdapter(adapterCurrent);
        spinYear.setAdapter(adapter);

       /* spinYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(spinYear.getSelectedItem().equals(thisYear))
                {
                    monthSpin.setAdapter(adapterCurrent);

                }else
                {
                    monthSpin.setAdapter(adapterMonth);
                }
            }
        });*/

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
    /*    spinYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinYear.getSelectedItem().equals(thisYear))
                {
                    monthSpin.setAdapter(adapterCurrent);

                }else
                {
                    monthSpin.setAdapter(adapterMonth);
                }
            }
        });

*/

        monthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyboard(MyFreezeDialog.this);
                new RackMonthPicker(MyFreezeDialog.this)
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
                            public void onCancel(android.support.v7.app.AlertDialog dialog) {

                            }
                        }).show();
            }
        });
        yearET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyboard(MyFreezeDialog.this);
                new RackMonthPicker(MyFreezeDialog.this)
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
                            public void onCancel(android.support.v7.app.AlertDialog dialog) {

                            }
                        }).show();
            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFreezeDialog.this);
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
        }


        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardET.getText().toString().isEmpty()) {
                    makeText(MyFreezeDialog.this, getString(R.string.please_enter_card), Toast.LENGTH_SHORT).show();
                }/* else if (monthET.getText().toString().isEmpty()) {
                    Toast.makeText(CheckoutPage.this, getString(R.string.please_enter_month), Toast.LENGTH_SHORT).show();

                }*/ else if (cvvET.getText().toString().isEmpty()) {
                    makeText(MyFreezeDialog.this, getString(R.string.please_enter_cvv), Toast.LENGTH_SHORT).show();

                } else {
                    GetCardModel model = new GetCardModel();
                    model.setCard_number(cardET.getText().toString());
                    model.setCard_month(monthET.getText().toString());
                    model.setCard_year(yearET.getText().toString());
                    model.setCvc(cvvET.getText().toString());
                    sendUpgradeData(priceDetail, priceIdDetail, subscriptionId, userId, model,dialog);
                    // CheckOut(dialog,cardET.getText().toString(), cvvET.getText().toString(), monthET.getText().toString(), yearET.getText().toString());
                }

            }
        });
        dialog.show();


    }


    private void showCards(String userId, final RecyclerView cardRV) {
        mainCardRV = cardRV;
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
                        listCards = response.body();
                        GetCardModel model = new GetCardModel();
                        model.setCard_number(Constants.NEWCARD);
                        listCards.add(model);
                        mainCardRV.setLayoutManager(new LinearLayoutManager(MyFreezeDialog.this));
                        adapter = new ShowCardAdapter(MyFreezeDialog.this, listCards);
                        mainCardRV.setAdapter(adapter);


                    } else {
                        GetCardModel model = new GetCardModel();
                        model.setCard_number(Constants.NEWCARD);
                        listCards.add(model);
                        mainCardRV.setLayoutManager(new LinearLayoutManager(MyFreezeDialog.this));
                        adapter = new ShowCardAdapter(MyFreezeDialog.this, listCards);
                        mainCardRV.setAdapter(adapter);
                        Toast.makeText(MyFreezeDialog.this, "No cards to show", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GetCardModel>> call, Throwable t) {
                dialog.dismiss();
                //   Toast.makeText(CheckoutPage.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void changeButton(String id, int i, boolean checked, GetCardModel model) {
        if (checked) {
            for (int j = 0; j < listCards.size(); j++) {
                listCards.get(j).setOnChecked(false);
            }
            model.setOnChecked(true);
            listCards.set(i, model);
            mainCardRV.post(new Runnable() {
                @Override
                public void run() {
                    //  adapter.notifyItemChanged(i);
                    adapter.notifyDataSetChanged();
                }
            });

        } else {
            /*for (int j = 0; j < listCards.size(); j++) {
                listCards.get(j).setOnChecked(true);
            }*/
            model.setOnChecked(false);
            listCards.set(i, model);
            mainCardRV.post(new Runnable() {
                @Override
                public void run() {
                    //  adapter.notifyItemChanged(i);
                    adapter.notifyDataSetChanged();
                }
            });
        }



    }

    @Override
    public void showView(String id, int i, boolean checked, GetCardModel model) {
        if (model.getCard_number().equalsIgnoreCase(Constants.NEWCARD)) {
            if (model.isOnChecked()) {
                cardCLView.setVisibility(View.VISIBLE);
            } else {
                cardCLView.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public void hideView(String id, int i, boolean checked, GetCardModel model) {

        if (!model.getCard_number().equalsIgnoreCase(Constants.NEWCARD)) {
            if (model.isOnChecked()) {
                Dialog mainDialog = new Dialog(this);
                //  getCyberPayData(mainDialog, String.valueOf(Price), lastId, model.getCard_number(), model.getCvc(), model.getCard_month(), model.getCard_year());
               // partyCheckOut(dialog, model.getCard_number(), model.getCvc(), model.getCard_month(), model.getCard_year());


            }

        }
        if(!model.getCard_number().equalsIgnoreCase(Constants.NEWCARD))
        {
            if (model.isOnChecked()) {
                sendUpgradeData(priceDetail, priceIdDetail, subscriptionId, userId,model, dialog);
            }

        }
        cardCLView.setVisibility(View.GONE);
    }

    public void freezapi(String date,String id)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.login));
        dialog.show();
        final Freezes task = new Freezes();
        task.setDate(date);
        task.setSubscriptionid(id);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.freezedate(task).enqueue(new Callback<FreezeModel>() {
            @Override
            public void onResponse(Call<FreezeModel> call, Response<FreezeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    String resp = response.body().toString();

                    Intent intent = new Intent(getApplicationContext(), MyAccountHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


             /*      Gson gson =  new Gson();
                   gson.fromJson();
                    if(response.body())*/

                    //  Intent intent = new Intent(getApplicationContext(), MyAccountHome.class);
                    // startActivity(intent); //EditText is not empty
                }
            }

            @Override
            public void onFailure(Call<FreezeModel> call, Throwable t) {
                dialog.dismiss();
            //    Toast.makeText(MyFreezeDialog.this, getString(R.string.enter_correct_number), Toast.LENGTH_SHORT).show();

                  Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void showFreezeDialog(final String date,final String id) {
        final Dialog dialog = new Dialog(this, R.style.mytheme);
        dialog.setContentView(R.layout.layout_freeze_dialog);
        dialog.setTitle("");
        final TextView dialogButton = dialog.findViewById(R.id.nextBT);
        final TextView cancleButton = dialog.findViewById(R.id.closeBT);





        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
               freezapi(date,id);


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

    private void showFrozenDialog(final String date,final String id) {
        final Dialog dialog = new Dialog(this, R.style.mytheme);
        dialog.setContentView(R.layout.layout_frozen_dialog);
        dialog.setTitle("");
        final TextView dialogButton = dialog.findViewById(R.id.nextBT);
        final TextView cancleButton = dialog.findViewById(R.id.closeBT);





        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                freezapi(date,id);


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
