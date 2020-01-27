package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cybersource.mpos.client.ManagerDelegate;
import com.cybersource.mpos.client.PaymentError;
import com.cybersource.mpos.client.PaymentResponse;
import com.cybersource.mpos.client.Transaction;
import com.cybersource.mpos.client.TransactionSearchResult;
import com.example.myapplication.Adapter.ShowCardAdapter;
import com.example.myapplication.Checkout.PlaceArrayAdapter;
import com.example.myapplication.Model.Retrofit.BucketAddModel;
import com.example.myapplication.Model.Retrofit.CheckoutModel;
import com.example.myapplication.Model.Retrofit.CyberData;
import com.example.myapplication.Model.Retrofit.CyberResponse;
import com.example.myapplication.Model.Retrofit.GatewayModel;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.Model.Retrofit.Myprofile;
import com.example.myapplication.Model.Retrofit.MyprofileModel;
import com.example.myapplication.Model.Retrofit.PasswordChangeModel;
import com.example.myapplication.Model.Retrofit.PaypalCancel;
import com.example.myapplication.Model.Retrofit.PromoResponse;
import com.example.myapplication.Model.Retrofit.PromoValidate;
import com.example.myapplication.Model.Retrofit.checkout;
import com.example.myapplication.Model.Retrofit.paypalmodel;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.example.myapplication.Views.WorkaroundMapFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.makeText;
import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class CheckoutPage extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, PlaceSelectionListener, ShowCardAdapter.InterfaceCard {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 121;
    Button btnnext;
    private GoogleMap mMap;
    Location mLastLocation;
    private List<BucketAddModel> list = new ArrayList<>();
    private Marker currentLocationMarker;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    String locationSend = "Zirakpur";
    String latitudeSend = "30.6581";
    String longitudeSend = "76.8355";
    LocationRequest mLocationRequest;
    SupportMapFragment mapFragment;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private TextView resutText, titlemenu;
    TextInputEditText etfirstname;
    EditText lastname, emails, companyname, phones, office, floor;
    EditText locationSearch, comments;
    MediaPlayer mPlayer2;
    String Title, Price, ed_text, ed_last, ed_email, ed_company, ed_phone, ed_floor, ed_office, Checkstatus, person, staus, duration, date, drink;
    Button promoBT;
    EditText promoET;
    TextView pack;
    TextView noSubscriber;
    TextView totalTV;
    TextView total;
    TextView back;
    TextView promoCodeTV;
    TextView price;
    TextView subsTV;
    TextView quantityTV, packageSummary;
    private String paymentAmount;
    private boolean firstTimeFlag = true;
    protected LocationManager locationManager;
    RelativeLayout mapRL;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    ScrollView mainSV;
    public static final int PAYPAL_REQUEST_CODE = 123;
    private static final int AUTO_COMP_REQ_CODE = 2;
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    private double currentLat = 30.6581;
    private double currentLong = 76.8355;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location currentLocation;
    private int lastId = 0;
    LinearLayout promoLL;
    LinearLayout subLL;
    private AutoCompleteTextView mAutocompleteTextView;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private static final int REQUEST_SELECT_PLACE = 1000;
    private PlaceAutocompleteFragment autocompleteFragment;
    private boolean searchCheck = false;
    private String searchPlace = "";
    private double lng, lat;
    private String userid;
    private String userCompanyName;
    private String userFirstName;
    private String userLastName;
    private String userEmailId;
    private String userFloor;
    private String userSimpleId;
    private String userOffice;
    private String userPhone;


    private String titleMain = "";
    private String datesMain = "";
    private String status = "";
    private String packageId = "";
    private String packageTitle = "";
    private String packageDuration = "";
    private String choosepersonCheck = "";
    private String softDrinkCheck = "";
    private String drinkId = "";
    private String peopleMain = "";
    private String noOfItem = "";
    private String priceValue = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    private String company = "";
    private String userId = "";
    private String qtyList = "";
    private String priceList = "";
    private String idList = "";
    private String offerValue = "";
    private String offerType = "";
    private String Userid = "";
    private String subscribeNumber = "1";
    TextInputLayout name_text_input;
    TextInputLayout name_text_inputs;
    TextInputLayout name_text_inputss;
    TextInputLayout name_text_inputsss;
    TextInputLayout name_text_inputssss;
    TextInputLayout name_text_inputsssss;
    TextInputLayout floorLayout;
    TextInputLayout name_text_inputsssssss;
    TextInputLayout commentLayout;

    CardView mainCV;
    private Dialog dialog;
    public int counter;
    String locationSetValue = "Zirakpur";
    String latitudeSetValue = "30.6581";
    String longitudeSetValue = "76.8355";
    private List<GetCardModel> listCards = new ArrayList<>();
    private ShowCardAdapter adapter;
    private RecyclerView mainCardRV;
    private ConstraintLayout cardCLView;
    private boolean checkoutDone = false;
    private CountDownTimer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);
        btnnext = findViewById(R.id.btnnext);
        titlemenu = findViewById(R.id.titlemenu);
        mainCV = findViewById(R.id.mainCV);
        subsTV = findViewById(R.id.subsTV);
        subLL = findViewById(R.id.subLL);
        etfirstname = findViewById(R.id.etfirstname);
        lastname = findViewById(R.id.lastname);
        emails = findViewById(R.id.emails);
        phones = findViewById(R.id.phone);
        office = findViewById(R.id.office);
        floor = findViewById(R.id.floor);
        promoET = findViewById(R.id.promoET);
        companyname = findViewById(R.id.companyname);
        price = findViewById(R.id.price);
        pack = findViewById(R.id.pack);
        total = findViewById(R.id.total);
        quantityTV = findViewById(R.id.quantityTV);
        packageSummary = findViewById(R.id.packageSummary);
        promoCodeTV = findViewById(R.id.promoCodeTV);
        promoLL = findViewById(R.id.promoLL);
        back = findViewById(R.id.back);
        locationSearch = findViewById(R.id.delivery);
        mapRL = findViewById(R.id.mapRL);
        comments = findViewById(R.id.comment);
        mainSV = findViewById(R.id.mainSV);
        noSubscriber = findViewById(R.id.noSubscriber);
        totalTV = findViewById(R.id.totalTV);
        promoBT = findViewById(R.id.promoBT);


        name_text_input = findViewById(R.id.name_text_input);
        name_text_inputs = findViewById(R.id.name_text_inputs);
        name_text_inputss = findViewById(R.id.name_text_inputss);
        name_text_inputsss = findViewById(R.id.name_text_inputsss);
        name_text_inputssss = findViewById(R.id.name_text_inputssss);
        name_text_inputsssss = findViewById(R.id.name_text_inputsssss);
        floorLayout = findViewById(R.id.floorLayout);
        name_text_inputsssssss = findViewById(R.id.name_text_inputsssssss);
        commentLayout = findViewById(R.id.commentLayout);


        mAutocompleteTextView = findViewById(R.id.autoCompleteTextView);
        mAutocompleteTextView.setThreshold(3);
        setAutoCompleteView();


        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CheckoutPage.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            etfirstname.setTypeface(custom_font_azab);
            lastname.setTypeface(custom_font_azab);
            emails.setTypeface(custom_font_azab);
            phones.setTypeface(custom_font_azab);
            companyname.setTypeface(custom_font_azab);
            office.setTypeface(custom_font_azab);
            floor.setTypeface(custom_font_azab);
            locationSearch.setTypeface(custom_font_azab);
            packageSummary.setTypeface(custom_font_azab);
            quantityTV.setTypeface(custom_font_azab);
            pack.setTypeface(custom_font_azab);
            noSubscriber.setTypeface(custom_font_azab);
            totalTV.setTypeface(custom_font_azab);
            total.setTypeface(custom_font_azab);
            comments.setTypeface(custom_font_azab);
            btnnext.setTypeface(custom_font_azab);
            subsTV.setTypeface(custom_font_azab);
            promoCodeTV.setTypeface(custom_font_azab);


            name_text_input.setTypeface(custom_font_azab);
            name_text_inputs.setTypeface(custom_font_azab);
            name_text_inputss.setTypeface(custom_font_azab);
            name_text_inputsss.setTypeface(custom_font_azab);
            name_text_inputssss.setTypeface(custom_font_azab);
            name_text_inputsssss.setTypeface(custom_font_azab);
            name_text_inputsssss.setTypeface(custom_font_azab);
            floorLayout.setTypeface(custom_font_azab);
            name_text_inputsssssss.setTypeface(custom_font_azab);
            commentLayout.setTypeface(custom_font_azab);
            promoBT.setTypeface(custom_font_azab);
            promoET.setTypeface(custom_font_azab);
        }


        if (languageValue.equalsIgnoreCase("ar")) {
            mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            etfirstname.setTextDirection(View.TEXT_DIRECTION_RTL);
            lastname.setTextDirection(View.TEXT_DIRECTION_RTL);
            emails.setTextDirection(View.TEXT_DIRECTION_RTL);
            phones.setTextDirection(View.TEXT_DIRECTION_RTL);
            companyname.setTextDirection(View.TEXT_DIRECTION_RTL);
            office.setTextDirection(View.TEXT_DIRECTION_RTL);
            floor.setTextDirection(View.TEXT_DIRECTION_RTL);
            locationSearch.setTextDirection(View.TEXT_DIRECTION_RTL);
            packageSummary.setTextDirection(View.TEXT_DIRECTION_RTL);
            comments.setTextDirection(View.TEXT_DIRECTION_RTL);

        } else {
            mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setText("");
        autocompleteFragment.setHint("");
        final EditText autoEdit = findViewById(R.id.place_autocomplete_search_input);

        findViewById(R.id.place_autocomplete_search_button).setVisibility(View.GONE);
        //((View)findViewById(R.id.place_autocomplete_clear_button)).setVisibility(View.GONE);
        ((EditText) autocompleteFragment.getView().findViewById(R.id.place_autocomplete_search_input)).setTextSize(1.0f);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i("places", "Place: " + place.getName());//get place details here
              /*  searchPlace = String.valueOf(place.getName());
                searchCheck = true;
                locationSearch.setText(place.getAddress());
                autoEdit.setText("");*/
            }

            @Override
            public void onError(Status status) {

                Log.i("places", "An error occurred: " + status);
            }
        });
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mapFragment = (WorkaroundMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.rideCar_mapView);
        mapFragment.getMapAsync(this);
        configureCameraIdle();

        ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.rideCar_mapView)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mainSV.requestDisallowInterceptTouchEvent(true);
            }
        });
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(currentLat, currentLong))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

           /*     mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(currentLat, currentLong))
                        .draggable(true)
                        .title("Set Location"));*/
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
            }
        });


        locationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                            .build();
                    Intent intent = new PlaceAutocomplete.IntentBuilder
                            (PlaceAutocomplete.MODE_FULLSCREEN)
                            .setFilter(typeFilter)
                            .build(CheckoutPage.this);
                    startActivityForResult(intent, REQUEST_SELECT_PLACE);
                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                    Log.e("GooglePlayServicesNotAvailableException","GooglePlayServicesNotAvailableException----------"+e);
                }


                String location = locationSearch.getText().toString();
                List<Address> addressList = null;

              /*  if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        // mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        Toast.makeText(getApplicationContext(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }*/

            }
        });

        promoCodeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promoCodeTV.setVisibility(View.GONE);
                promoLL.setVisibility(View.VISIBLE);
            }
        });

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Title = preference.getString("title", "");
        Price = preference.getString("both", "");
        Checkstatus = preference.getString("status", "");
        staus = preference.getString("choose1", "");
        person = preference.getString("choose2", "");
        duration = preference.getString("time", "");
        date = preference.getString("dates", "");
        drink = preference.getString("drink", "");


        pack.setText(Title);
        price.setText(Price);

        total.setText("﷼" + Price);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckoutPage.super.onBackPressed();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed_text = etfirstname.getText().toString().trim();
                ed_last = lastname.getText().toString().trim();
                ed_email = emails.getText().toString().trim();
                ed_company = companyname.getText().toString().trim();
                ed_phone = phones.getText().toString().trim();
                ed_office = office.getText().toString().trim();
                ed_floor = floor.getText().toString().trim();

                if (phones.getText().toString().length() == 0 || lastname.getText().toString().length() == 0 || etfirstname.getText().toString().length() == 0 || companyname.getText().toString().length() == 0 || emails.getText().toString().length() == 0 || office.getText().toString().length() == 0 || locationSearch.getText().toString().isEmpty()) {
                    makeText(CheckoutPage.this, getString(R.string.enter_data_field), Toast.LENGTH_SHORT).show();
                } else {
                    // mPlayer2.stop();


                    /// Later work



                    /*
                     */


                    if (getIntent().hasExtra("fromFoodCart")) {
                        qtyList = getIntent().getStringExtra("qtyList");
                        priceList = getIntent().getStringExtra("priceList");
                        idList = getIntent().getStringExtra("idList");
                        cardPartyPaymentDialog(String.valueOf(Price), lastId);

                    } else {

                        userid = preferences.getString("userid", "");
                        checkAccountExist(ed_email, ed_phone);
                    }


                }
              /*  if (phones.getText().toString().length() == 0)
                    phones.setError("phone is required!");

                if (lastname.getText().toString().length() == 0)
                    lastname.setError("Last Name is required!");

                if (etfirstname.getText().toString().length() == 0)
                //    etfirstname.setError("First Name is required!");

                if (companyname.getText().toString().length() == 0)
                    companyname.setError("Company Name is required!");

                if (emails.getText().toString().length() == 0)
                    emails.setError("email is required!");
*/


            }
        });


        if (getIntent().hasExtra("fromFoodCart")) {
            subLL.setVisibility(View.GONE);
            userid = preferences.getString("userid", "");
            userCompanyName = preferences.getString("userCompanyName", "");
            userFirstName = preferences.getString("userFirstName", "");
            userLastName = preferences.getString("userLastName", "");
            userEmailId = preferences.getString("userEmailId", "");
            userFloor = preferences.getString("userFloor", "");
            userSimpleId = preferences.getString("userSimpleId", "");
            userOffice = preferences.getString("userOffice", "");
            userPhone = preferences.getString("userPhone", "");
            firstName = getIntent().getStringExtra("firstName");
            userId = getIntent().getStringExtra("userId");
            lastName = getIntent().getStringExtra("lastName");
            email = getIntent().getStringExtra("email");
            phone = getIntent().getStringExtra("phone");
            company = getIntent().getStringExtra("company");
            list = getIntent().getParcelableArrayListExtra("list");
            etfirstname.setText(firstName);
            lastname.setText(lastName);
            companyname.setText(company);
            office.setText(userOffice);
            floor.setText(userFloor);
            phones.setText(phone);
            emails.setText(email);
            packageSummary.setText(getString(R.string.party_box_summary));
            quantityTV.setText(getString(R.string.party_box_summary));
            noOfItem = getIntent().getStringExtra("noOfItem");
            priceValue = getIntent().getStringExtra("price");
            pack.setText(noOfItem);
            total.setText("﷼" + String.format("%.2f", Double.parseDouble(priceValue)));
        }


        if (getIntent().hasExtra("choosepersonCheck")) {
            titleMain = getIntent().getStringExtra("title");
            datesMain = getIntent().getStringExtra("dates");
            status = getIntent().getStringExtra("status");
            packageId = getIntent().getStringExtra("packageId");
            packageTitle = getIntent().getStringExtra("packageTitle");
            packageDuration = getIntent().getStringExtra("packageDuration");
            choosepersonCheck = getIntent().getStringExtra("choosepersonCheck");
            peopleMain = getIntent().getStringExtra("peopleMain");
            subscribeNumber = getIntent().getStringExtra("subscribeNumber");
            if (choosepersonCheck.equalsIgnoreCase("group")) {
                subLL.setVisibility(View.VISIBLE);
                Double totalPrice = Double.parseDouble(Price) * Integer.parseInt(subscribeNumber);
                total.setText(String.valueOf(totalPrice));
                subsTV.setText(subscribeNumber);
            } else {
                subLL.setVisibility(View.GONE);
                subsTV.setText(subscribeNumber);
            }
            if (getIntent().hasExtra("softDrinkCheck")) {
                softDrinkCheck = getIntent().getStringExtra("softDrinkCheck");
                drinkId = getIntent().getStringExtra("drinkId");
            }
        }


        promoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (promoET.getText().toString().isEmpty()) {
                    makeText(CheckoutPage.this, getString(R.string.enter_promo), Toast.LENGTH_SHORT).show();
                } else {
                    getPromoValidation();
                }

            }
        });
      /*  BigDecimal bigDecimal = new BigDecimal(2);

        PaymentRequest paymentReqObject = new PaymentRequest();
        paymentReqObject.setMerchantId("bm_parceloman_omr");
        paymentReqObject.setAccessToken("af4e6c4bdd383efc8dbc087c6cd0aaf9");
        PurchaseTotal purchaseTotal = new PurchaseTotal();
        purchaseTotal.setCurrency("USD");
        purchaseTotal.setGrandTotalAmount(bigDecimal);
        paymentReqObject.setPurchaseTotal(purchaseTotal);
        paymentReqObject.setMerchantReferenceCode(Long.toString(System.currentTimeMillis()));*/

       /*  String android_id = android.provider.Settings.Secure.getString(getContext().getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        Settings settings = new Settings(Settings.Environment.ENV_TEST,android_id,"terminalID1234","terminalID1234","bm_parceloman_omr");
        Manager manager = new Manager(settings);*/
       /* manager.performTransactionSearch("", "hfV6KX4QYuy7FZtrqBcHr2dYXytdEoQLTrV7kZlA0ReQdKzlfOz1/9YVwmsoQ9sqAJCxmYfv+cjPfnjvKDKTuJk3WiGpvSI+scG/8isQCuBbTp5JDTgDO7SB6awtH08I0uTqmBRAB00mdAjtM9BblThgG7NgvsbKf8HQq44VN9q17MtQhoD2AlMDI6QE0OlmNInozjtxWojGL9dTe+Szj69rjhYfdgQ/C0Ac8ybLldDr7/84jjWds08Nzyel/CMlGjUoFIZN13N7dXbpvpmxHpV2O/K5ShwNJnrX5/H+D8VeB+I8QmcJgffH/C1Lcd1CPvvCCvGO1qfW58m5lVj59w==",
                managerDelegate);*/


    }

    private void checkAccountExist(String emailValue, String phoneValue) {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final checkout task = new checkout();
        task.setEmail(emailValue);
        task.setPhone(phoneValue);
        task.setUserid(userid);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.checkAccountExist(task).enqueue(new Callback<PasswordChangeModel>() {
            @Override
            public void onResponse(Call<PasswordChangeModel> call, Response<PasswordChangeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Log.e("Response", response.message());
                    if (response.body().getStatus() == 200) {
                        //    Toast.makeText(CheckoutPage.this, "success", Toast.LENGTH_SHORT).show();

                        if (getIntent().hasExtra("fromFoodCart")) {
                            qtyList = getIntent().getStringExtra("qtyList");
                            priceList = getIntent().getStringExtra("priceList");
                            idList = getIntent().getStringExtra("idList");
                            cardPartyPaymentDialog(String.valueOf(Price), lastId);

                        } else {
                            Constants.hideKeyboard(CheckoutPage.this);


                            CheckOut(dialog);


                            // cardPaymentDialog(String.valueOf(Price), lastId);
                        }
                    } else if (response.body().getStatus() == 200) {
                        makeText(CheckoutPage.this, getString(R.string.your_email_account_string), Toast.LENGTH_SHORT).show();
                    } else if (response.body().getStatus() == 206) {
                        makeText(CheckoutPage.this, getString(R.string.your_phone_account_string), Toast.LENGTH_SHORT).show();
                    } else {
                        makeText(CheckoutPage.this, getString(R.string.your_email_account_string), Toast.LENGTH_SHORT).show();
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

                } else {
                    makeText(CheckoutPage.this, "Email or Phone number already exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordChangeModel> call, Throwable t) {
                dialog.dismiss();
                makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }

    private final ManagerDelegate managerDelegate = new ManagerDelegate() {
        @Override
        public void performPaymentDidFinish(PaymentResponse paymentResponse, PaymentError paymentError) {
            // Perform actions
            makeText(CheckoutPage.this, "" + paymentError.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void refundDidFinish(PaymentResponse paymentResponse, PaymentError paymentError) {
            makeText(CheckoutPage.this, "" + paymentError.getErrorMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void voidDidFinish(PaymentResponse paymentResponse, PaymentError paymentError) {
            makeText(CheckoutPage.this, "" + paymentError.getErrorMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void performTransactionSearchDidFinish(TransactionSearchResult transactionSearchResult, PaymentError paymentError) {

        }

        @Override
        public void getTransactionDetailDidFinish(Transaction transaction, PaymentError paymentError) {
            makeText(CheckoutPage.this, "" + paymentError.getErrorMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void sendReceiptDidFinish() {
            makeText(CheckoutPage.this, "payment data", Toast.LENGTH_SHORT).show();

        }
    };


    private void getPromoValidation() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage("Loding Please Wait");
        dialog.show();
        final PromoValidate task = new PromoValidate();
        if (getIntent().hasExtra("fromFoodCart")) {
            task.setCtype("Party");
        } else {
            task.setCtype("Subscription");
        }
        task.setCtype("Subscription");
        task.setTotal(Price);
        task.setPackageID(packageId);
        task.setPromocode(promoET.getText().toString().trim());
        //task.setLang("en");
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPromoValidate(task).enqueue(new Callback<PromoResponse>() {
            @Override
            public void onResponse(Call<PromoResponse> call, Response<PromoResponse> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (!response.body().getOffervalue().isEmpty()) {
                        offerValue = response.body().getOffervalue();
                        offerType = response.body().getOffertype();
                        String disPrice = response.body().getPayableamount();
                        if (!offerValue.isEmpty() || !Price.isEmpty()) {
                            double discountValue = Double.parseDouble(Price) - Double.parseDouble(offerValue);
                            total.setText("﷼" + discountValue);
                        }

                    } else {
                        makeText(CheckoutPage.this, getString(R.string.code_not_valid), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<PromoResponse> call, Throwable t) {
                dialog.dismiss();
                makeText(getApplicationContext(), getString(R.string.please_enter_valid_email), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void setAutoCompleteView() {
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            final PlaceArrayAdapter.PlaceAutocomplete item = mPlaceArrayAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i("Checkout", "Selected: " + item.description);
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            Log.i("Checkout", "Fetching details for ID: " + item.placeId);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.e("Checkout", "Place query did not complete. Error: " +
                        places.getStatus().toString());
                return;
            }
            // Selecting the first object buffer.
            final Place place = places.get(0);
            CharSequence attributions = places.getAttributions();


        }
    };


    private void partyCheckOut(final Dialog mainDialog, final String cardValue, final String cvcValue, final String monthValue, final String yearValue, final boolean saveChecked) {

        List<String> myList = new ArrayList<String>(Arrays.asList(idList.split(",")));
        List<String> priceArrayList = new ArrayList<String>(Arrays.asList(priceList.split(",")));
        List<String> qtyArrayList = new ArrayList<String>(Arrays.asList(qtyList.split(",")));
        Set<String> idSet = new HashSet<>();
        Set<String> priceSet = new HashSet<>();
        Set<String> qtySet = new HashSet<>();
        idSet.addAll(myList);
        priceSet.addAll(priceArrayList);
        priceArrayList.clear();
        qtySet.addAll(qtyArrayList);
        qtyArrayList.clear();
        for (int i = 0; i < list.size(); i++) {
            priceArrayList.add(list.get(i).getPrice());
            qtyArrayList.add(list.get(i).getCountValue());
        }
        String idString = TextUtils.join(", ", idSet);
        String priceString = TextUtils.join(", ", priceArrayList);
        String qtyString = TextUtils.join(", ", qtyArrayList);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage("Loding Please Wait");
        dialog.show();
        final checkout task = new checkout();
        task.setFirstname(ed_text);
        task.setLastname(ed_last);
        task.setEmail(ed_email);
        task.setPhone(ed_phone);
        task.setComments(comments.getText().toString().trim());
        task.setCompany(ed_company);
        task.setCtype("Party");
        task.setFloor(ed_floor);
        task.setUserid(Userid);
        task.setOffice(ed_office);
        task.setLatitude(latitudeSend);
        task.setLongitude(longitudeSend);
        task.setLocation(locationSend);
        if (getIntent().hasExtra("fromFoodCart")) {
            task.setTotal(priceValue);
        } else {
            task.setTotal(Price);
        }

        task.setIds(idString);
        task.setPartyids(idString);
        task.setPrices(priceString);
        task.setTotals(priceString);
        task.setTotal(priceValue);
        task.setQtys(qtyString);
        task.setFinaltotal(priceValue);
        task.setOffervalue(offerValue);
        task.setOffertype(offerType);
   /*     task.setCard_number(cardValue);
        task.setCvc(cvcValue);
        task.setCard_month(monthValue);
        task.setCard_year(yearValue);*/

        //task.setLang("en");

        Log.e("checkout Response", "First name " + ed_text + "   last name " + ed_last + "   Email " + ed_email + "     phone " + ed_phone + "  comments  "
                + comments.getText().toString() + "  company  " + ed_company + "  ctype  Subscription  " + "  duration  " + status + "   date  " + date + "  floor  " + ed_floor + "  office  " + ed_office + "  latitude  " + latitudeSend + " longitude  : " + longitudeSend + "  location : " + choosepersonCheck + " choose person  " + choosepersonCheck + "  person check  " + peopleMain + " sdate  "
                + date + "  total  " + Price + "  packageDuration  " + packageDuration + "  package name   :  " + titleMain + "  packageId  " + packageId + "  softDrinkCheck  " + softDrinkCheck);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPartyCheckOut(task).enqueue(new Callback<CheckoutModel>() {
            @Override
            public void onResponse(Call<CheckoutModel> call, Response<CheckoutModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    if (response.body().getStatus().equals("203")) {
                        makeText(getApplicationContext(), "Status 203", Toast.LENGTH_LONG).show();
                    } else if (response.body().getData().size() > 0) {
                        List<checkout> dataList = response.body().getData();

                        lastId = dataList.get(0).getLastid();
                        Price = dataList.get(0).getTotal();
                        paymentAmount = "2";

                        getCyberPayData(mainDialog, String.valueOf(Price), lastId, cardValue, cvcValue, monthValue, yearValue, saveChecked);
/*
                        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(Price)), "USD", "Payable Amount",
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

                    }


                    //Intent intent = new Intent(getApplicationContext(), Paynow.class);
                    //startActivity(intent); //EditText is not empty
                }
            }

            @Override
            public void onFailure(Call<CheckoutModel> call, Throwable t) {
                dialog.dismiss();
                makeText(getApplicationContext(), "Please enter Valid Email", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void CheckOut(final Dialog mainDialog) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final checkout task = new checkout();
        task.setUserid(Userid);
        task.setFirstname(ed_text);
        task.setLastname(ed_last);
        task.setEmail(ed_email);
       /* task.setCard_number(cardValue);
        task.setCvc(cvvValue);
        task.setCard_month(monthValue);
        task.setCard_year(yearValue);*/
        task.setPhone(ed_phone);
        task.setComments(comments.getText().toString().trim());
        task.setCompany(ed_company);
        task.setCtype("Subscription");
        if (status.equalsIgnoreCase("breakfast")) {
            task.setDuration("Breakfast");
        } else if (status.equalsIgnoreCase("lunch")) {
            task.setDuration("Lunch");
        } else if (status.equalsIgnoreCase("both")) {
            task.setDuration("Both");
        } else {
            task.setDuration("Breakfast");
        }
        task.setEdate(date);
        task.setFloor(ed_floor);
        task.setOffice(ed_office);
        task.setLatitude(latitudeSend);
        task.setLongitude(longitudeSend);
        task.setLocation(locationSend);
        task.setStype(choosepersonCheck);
        if (choosepersonCheck.equalsIgnoreCase("Group")) {
            task.setPeople(peopleMain);
        } else {
            task.setPeople("1");
        }

        task.setSdate(date);
        task.setTotal(Price);
        task.setPackageduration(packageDuration);
        task.setPackagename(titleMain);
        task.setPackageid(packageId);
        task.setOffertype(offerType);
        task.setOffervalue(offerValue);
        if (status.equalsIgnoreCase("breakfast")) {
            task.setSoftdrink("");
        } else {
            task.setSoftdrink(drinkId);
        }

        task.setLang(languageValue);

        Log.e("checkout Response", "First name " + ed_text + "   last name " + ed_last + "   Email " + ed_email + "     phone " + ed_phone + "  comments  "
                + comments.getText().toString() + "  company  " + ed_company + "  ctype  Subscription  " + "  duration  " + status + "   date  " + date + "  floor  " + ed_floor + "  office  " + ed_office + "  latitude  " + latitudeSend + " longitude  : " + longitudeSend + "  location : " + choosepersonCheck + " choose person  " + choosepersonCheck + "  person check  " + peopleMain + " sdate  "
                + date + "  total  " + Price + "  packageDuration  " + packageDuration + "  package name   :  " + titleMain + "  packageId  " + packageId + "  softDrinkCheck  " + softDrinkCheck);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetCheckOut(task).enqueue(new Callback<CheckoutModel>() {
            @Override
            public void onResponse(Call<CheckoutModel> call, Response<CheckoutModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    if (response.body().getStatus().equals("203")) {
                        makeText(getApplicationContext(), "Status 203", Toast.LENGTH_LONG).show();
                    } else if (response.body().getData().size() > 0) {
                        List<checkout> dataList = response.body().getData();

                        lastId = dataList.get(0).getLastid();
                        paymentAmount = "2";

                        checkoutDone = true;

                        cardPaymentDialog(String.valueOf(Price), lastId);


                        //  getCyberPayData(mainDialog, String.valueOf(Price), lastId, cardValue, cvvValue, monthValue, yearValue);


                        //paypal Integration

                       /*
                        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(Price)), "USD", "Payable Amount",
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


                    }

                    //Intent intent = new Intent(getApplicationContext(), Paynow.class);
                    //startActivity(intent); //EditText is not empty
                }
            }

            @Override
            public void onFailure(Call<CheckoutModel> call, Throwable t) {
                dialog.dismiss();
                makeText(getApplicationContext(), getString(R.string.please_enter_valid_email), Toast.LENGTH_LONG).show();
            }
        });
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
                        //   sendDataToApi("", accountNo, cvv, expMonth, paymentDetails, checkedSave);
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

                hitGatewayCancel();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }

        if (requestCode == REQUEST_SELECT_PLACE) {
            String confirm = String.valueOf(data);
            if (data != null) {
                Place place = PlacePicker.getPlace(data, this);
                if (place != null) {
                    lat = place.getLatLng().latitude;
                    lng = place.getLatLng().longitude;
                    latitudeSend = String.valueOf(lat);
                    longitudeSend = String.valueOf(lng);
                    locationSearch.setText(place.getAddress());
                    locationSend = String.valueOf(place.getAddress());
                    LatLng latLng = mMap.getCameraPosition().target;

                    try {

                        //  currentLocationMarker.setPosition(latLng);
                        if (currentLocationMarker == null) {
                            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(place.getLatLng()));


                        } else
                            MarkerAnimation.animateMarkerToGB(currentLocationMarker, place.getLatLng(), new LatLngInterpolator.Spherical());
                        Location targetLocation = new Location("");//provider name is unnecessary
                        targetLocation.setLatitude(lat);//your coords of course
                        targetLocation.setLongitude(lng);
                        animateCamera(targetLocation);
                           /* if (firstTimeFlag && mMap != null) {
                                Location targetLocation = new Location("");//provider name is unnecessary
                                Location targetLocation = new Location("");//provider name is unnecessary
                                targetLocation.setLatitude(lat);//your coords of course
                                targetLocation.setLongitude(lng);
                                animateCamera(targetLocation);
                                firstTimeFlag = false;
                            }*/
                    } catch (Exception ae) {
                        ae.printStackTrace();
                    }


                   /* MarkerOptions marker = new MarkerOptions().position(
                            place.getLatLng()).title("Hello Maps");
                    marker.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    googleMap.addMarker(marker);*/
                }
            }
        }

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
        task.setCheck_id(String.valueOf(lastId));
        task.setFoodtype("");
        task.setPaytype("");
        task.setLang(languageValue);

        if (Userid.isEmpty()) {
            task.setIs_logged("No");
        } else {
            task.setIs_logged("Yes");
        }
        Log.e("payment check", " check Id  : " + lastId + " food type  " + checkstatus);
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
                makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void sendDataToApi(String details, final String accountNo, final String cvv, final String expMonth, final String expYear, boolean checkedSave) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String checkstatus = preferences.getString("status", "");
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(String.valueOf(lastId));
        task.setFoodtype(checkstatus);
        task.setPaytype("");
        task.setLang(languageValue);

        Log.e("payment check", " check Id  : " + lastId + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalSuccessCheck(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {

                        addCardDetail(dialog, accountNo, cvv, expMonth, expYear, true);
                        dialogPaySuccess(expYear);

                        //  makeText(CheckoutPage.this, "Thank You! Congratulations! Your Order Was Completed Successfully.", Toast.LENGTH_SHORT).show();


                    }
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<paypalmodel> call, Throwable t) {
                dialog.dismiss();
                makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dialogPaySuccess(final String paymentDetails) {
        if (timer != null) {
            timer.cancel();
        }
        TextView emailTV, dialogTV, titleTV, closeBT;


        RelativeLayout relativeLayout1, relativeLayout2;
        dialog = new Dialog(CheckoutPage.this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pay_success);


        closeBT = dialog.findViewById(R.id.closeBT);
        dialogTV = dialog.findViewById(R.id.dialogTV);
        emailTV = dialog.findViewById(R.id.termCB);
        titleTV = dialog.findViewById(R.id.titleTV);

        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CheckoutPage.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");


        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(CheckoutPage.this.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(CheckoutPage.this.getAssets(), "arajozoor_regular.otf");
            //holder.title.setTypeface(custom_font_azab);


            dialogTV.setTypeface(custom_font_azab);
            titleTV.setTypeface(custom_font_azab);

            closeBT.setTypeface(custom_font_azab);
            emailTV.setTypeface(custom_font_azab);
        }
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (getIntent().hasExtra("fromFoodCart")) {
                        /*   Intent intent = new Intent(getApplicationContext(), MyHistory.class);
                           startActivity(intent);*/


                    startActivity(new Intent(CheckoutPage.this, MainActivity.class)
                            .putExtra("PaymentDetails", paymentDetails)
                            .putExtra("openHistory", "yes")
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .putExtra("PaymentAmount", paymentAmount));

                } else {

                    startActivity(new Intent(CheckoutPage.this, MainActivity.class)
                            .putExtra("PaymentDetails", paymentDetails)
                            .putExtra("PaymentAmount", paymentAmount));
                }

            }
        });
    }

    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(CheckoutPage.this);
                if (latLng != null) {
                    try {
                        //  currentLocationMarker.setPosition(latLng);
                        if (currentLocationMarker == null)
                            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng));
                        else
                            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());


                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker m) {
                                locationSearch.setText(locationSetValue);
                                locationSend = locationSetValue;
                                latitudeSend = latitudeSetValue;
                                longitudeSend = longitudeSetValue;
                                return true;
                            }
                        });

                    } catch (Exception ae) {
                        ae.printStackTrace();
                    }

                }

                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        String locality = addressList.get(0).getAddressLine(0);
                        String country = addressList.get(0).getCountryName();
                        try {
                            if (!locality.isEmpty() && !country.isEmpty()) {
                                if (searchCheck) {
                                    searchCheck = false;
                                    locationSetValue = searchPlace;
                                    longitudeSetValue = String.valueOf(latLng.longitude);
                                    latitudeSetValue = String.valueOf(latLng.latitude);
                                    //  locationSearch.setText(searchPlace);

                                    //commented13 dec
                                  /*  latitudeSend = String.valueOf(latLng.latitude);
                                    longitudeSend = String.valueOf(latLng.longitude);
                                    locationSend = searchPlace;*/

                                } else {
                                    //  locationSearch.setText(locality + "  " + country);


                                    //commented13 dec
                                  /*  locationSend = locality + " " + country;
                                    latitudeSend = String.valueOf(latLng.latitude);
                                    longitudeSend = String.valueOf(latLng.longitude);

*/
                                    locationSetValue = locality + " " + country;
                                    longitudeSetValue = String.valueOf(latLng.longitude);
                                    latitudeSetValue = String.valueOf(latLng.latitude);
                                }
                            }

                        } catch (Exception ae) {
                            ae.printStackTrace();
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


       /* mapRL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        mainSV.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        mainSV.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return mapRL.onTouchEvent(event);
            }
        });*/

       /* mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.setSnippet(String.valueOf(marker.getPosition().latitude));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));

            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

        });
*/
        mMap.setOnCameraIdleListener(onCameraIdleListener);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(currentLat, currentLong))
                .title("Set Location"));

        LatLng latLng = new LatLng(currentLat, currentLong);
    }


    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    String getCurrentLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        return "";
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLat = location.getLatitude();
        currentLong = location.getLongitude();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mPlaceArrayAdapter.setGoogleApiClient(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mPlaceArrayAdapter.setGoogleApiClient(null);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isGooglePlayServicesAvailable()) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            startCurrentLocationUpdates();
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CheckoutPage.this);
        String subscribeNow = preferences.getString(Constants.SUBSCRIBENOW, "no");
        Userid = preferences.getString("userid", "");
        if (subscribeNow.equals("yes")) {
            getProfileData(Userid);
        }


    }

    private void getProfileData(String userid) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final Myprofile task = new Myprofile();
        task.setUserid(Userid);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetProfile(task).enqueue(new Callback<MyprofileModel>() {
            @Override
            public void onResponse(Call<MyprofileModel> call, Response<MyprofileModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    etfirstname.setText(response.body().getData().get(0).getFirstname());
                    lastname.setText(response.body().getData().get(0).getLastname());
                    emails.setText(response.body().getData().get(0).getEmail());
                    phones.setText(response.body().getData().get(0).getPhone());
                    companyname.setText(response.body().getData().get(0).getCompany());
                    office.setText(response.body().getData().get(0).getOffice());
                    floor.setText(response.body().getData().get(0).getFloor());
                    //zip.setText(response.body().getData().get(0).getDob());
                    locationSearch.setText(response.body().getData().get(0).getLocation());
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude))).title("Saved Location"));


                }
            }

            @Override
            public void onFailure(Call<MyprofileModel> call, Throwable t) {
                dialog.dismiss();
                makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status)
            return true;
        else {
            if (googleApiAvailability.isUserResolvableError(status))
                makeText(this, "Please Install google play services to use this application", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void startCurrentLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                return;
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
    }

    private final LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult.getLastLocation() == null)
                return;
            currentLocation = locationResult.getLastLocation();
            if (firstTimeFlag && mMap != null) {
                animateCamera(currentLocation);
                firstTimeFlag = false;
            }
            // showMarker(currentLocation);
        }
    };

    private void animateCamera(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(16).build();
    }

    private void showMarker(@NonNull Location currentLocation) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        if (currentLocationMarker == null)
            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng));
        else
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());
    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.i("Selected", "Place Selected: " + place.getAddress());
        makeText(this, "" + place.getAddress(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Status status) {
        makeText(this, "" + status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeButton(String id, final int i, boolean checked, GetCardModel model) {
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


        // adapter.notifyDataSetChanged();

    }


    public interface LatLngInterpolator {
        LatLng interpolate(float fraction, LatLng a, LatLng b);

        class Spherical implements LatLngInterpolator {
            double fromLat = 0.0;
            double fromLng = 0.0;

            /* From github.com/googlemaps/android-maps-utils */
            @Override
            public LatLng interpolate(float fraction, LatLng from, LatLng to) {
                // http://en.wikipedia.org/wiki/Slerp
                fromLat = toRadians(from.latitude);
                fromLng = toRadians(from.longitude);
                double toLat = toRadians(to.latitude);
                double toLng = toRadians(to.longitude);
                double cosFromLat = cos(fromLat);
                double cosToLat = cos(toLat);
                // Computes Spherical interpolation coefficients.
                double angle = computeAngleBetween(fromLat, fromLng, toLat, toLng);
                double sinAngle = sin(angle);
                if (sinAngle < 1E-6) {
                    return from;
                }
                double a = sin((1 - fraction) * angle) / sinAngle;
                double b = sin(fraction * angle) / sinAngle;
                // Converts from polar to vector and interpolate.
                double x = a * cosFromLat * cos(fromLng) + b * cosToLat * cos(toLng);
                double y = a * cosFromLat * sin(fromLng) + b * cosToLat * sin(toLng);
                double z = a * sin(fromLat) + b * sin(toLat);
                // Converts interpolated vector back to polar.
                double lat = atan2(z, sqrt(x * x + y * y));
                double lng = atan2(y, x);
                return new LatLng(toDegrees(lat), toDegrees(lng));
            }

            private double computeAngleBetween(double format, double fromLng, double toLat, double toLng) {
                // Haversine's formula
                double dLat = fromLat - toLat;
                double dLng = fromLng - toLng;
                return 2 * asin(sqrt(pow(sin(dLat / 2), 2) +
                        cos(fromLat) * cos(toLat) * pow(sin(dLng / 2), 2)));
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fusedLocationProviderClient != null)
            fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
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

                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();
            }
        });


        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    timer.cancel();
                    if (checkoutDone) {
                        if (lastId != 0) {
                            hitCronJob(String.valueOf(lastId), "Subscription");

                        }
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
               // hitGatewayCancel();
                //finishAffinity();

                startActivity(new Intent(CheckoutPage.this, MainActivity.class)
                        .putExtra("PaymentDetails", "")
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .putExtra("PaymentAmount", paymentAmount));
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

        for (int i = thisYear; i <= thisYear + 10; i++) {
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
                Constants.hideKeyboard(CheckoutPage.this);
                new RackMonthPicker(CheckoutPage.this)
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
                Constants.hideKeyboard(CheckoutPage.this);
                new RackMonthPicker(CheckoutPage.this)
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


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CheckoutPage.this);
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
                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();


            }
        });
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardET.getText().toString().isEmpty()) {
                    makeText(CheckoutPage.this, getString(R.string.please_enter_card), Toast.LENGTH_SHORT).show();
                }/* else if (monthET.getText().toString().isEmpty()) {
                    Toast.makeText(CheckoutPage.this, getString(R.string.please_enter_month), Toast.LENGTH_SHORT).show();

                }*//* else if (yearET.getText().toString().isEmpty()) {
                    Toast.makeText(CheckoutPage.this, getString(R.string.please_enter_year), Toast.LENGTH_SHORT).show();

                } */ else if (cvvET.getText().toString().isEmpty()) {
                    makeText(CheckoutPage.this, getString(R.string.please_enter_cvv), Toast.LENGTH_SHORT).show();

                } else {

                    getCyberPayData(dialog, String.valueOf(Price), lastId, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString(), saveCB.isChecked());


                    //  CheckOut(dialog, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString());
                }

            }
        });
        dialog.show();


    }


    public void cardPartyPaymentDialog(final String priceValue, final int lastId) {

        Button closeBT, nextBT;
        final EditText monthET, cardET, yearET, cvvET;
        final TextView cardTV, cvvTV, minuteTV, secondTV;
        RecyclerView cardRV;
        ConstraintLayout cardCL;
        final CheckBox saveCB;
        TextView secureTV;
        dialog = new Dialog(this, android.R.style.Theme_Light);
        //dialog = new Dialog(this, R.style.RatingDialog2);
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
        cardRV = dialog.findViewById(R.id.cardRV);
        cardCL = dialog.findViewById(R.id.cardCL);
        minuteTV = dialog.findViewById(R.id.minuteTV);
        secondTV = dialog.findViewById(R.id.secondTV);
        saveCB = dialog.findViewById(R.id.saveCB);
        secureTV = dialog.findViewById(R.id.secureTV);
        cardCLView = cardCL;
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();

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
                //hitGatewayCancel();
                startActivity(new Intent(CheckoutPage.this, MainActivity.class)
                        .putExtra("PaymentDetails", "")
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .putExtra("PaymentAmount", paymentAmount));
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
                Constants.hideKeyboard(CheckoutPage.this);
                new RackMonthPicker(CheckoutPage.this)
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
                Constants.hideKeyboard(CheckoutPage.this);
                new RackMonthPicker(CheckoutPage.this)
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


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CheckoutPage.this);
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
            secureTV.setTypeface(custom_font_azab);
            saveCB.setTypeface(custom_font_azab);
        }


        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();

            }
        });
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardET.getText().toString().isEmpty()) {
                    makeText(CheckoutPage.this, getString(R.string.please_enter_card), Toast.LENGTH_SHORT).show();
                }/* else if (monthET.getText().toString().isEmpty()) {
                    Toast.makeText(CheckoutPage.this, getString(R.string.please_enter_month), Toast.LENGTH_SHORT).show();

                }*/ else if (cvvET.getText().toString().isEmpty()) {
                    makeText(CheckoutPage.this, getString(R.string.please_enter_cvv), Toast.LENGTH_SHORT).show();

                } else {

                 /*   List<String> myList = new ArrayList<String>(Arrays.asList(idList.split(",")));
                    List<String> priceArrayList = new ArrayList<String>(Arrays.asList(priceList.split(",")));
                    List<String> qtyArrayList = new ArrayList<String>(Arrays.asList(qtyList.split(",")));
                    Set<String> idSet = new HashSet<>();
                    Set<String> priceSet = new HashSet<>();
                    Set<String> qtySet = new HashSet<>();
                    idSet.addAll(myList);
                    priceSet.addAll(priceArrayList);
                    qtySet.addAll(qtyArrayList);
                    LinkedHashSet<String> hashSet = new LinkedHashSet<>(myList);



                    String idString = TextUtils.join(", ", hashSet);
                    String idsdString = TextUtils.join(", ", hashSet);
*/

                    partyCheckOut(dialog, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString(), saveCB.isChecked());
                    // CheckOut(dialog,cardET.getText().toString(), cvvET.getText().toString(), monthET.getText().toString(), yearET.getText().toString());
                }

            }
        });
        dialog.show();


    }

    private void getCyberPayData(Dialog mainDialog, String priceValue, int lastId, final String accountNo, final String cvv, final String expMonth, final String expYear, final boolean checkedSave) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final CyberData task = new CyberData();
        task.setAccountNumber(accountNo);
        task.setExpirationMonth(expMonth);
        task.setExpirationYear(expYear);
        task.setCvNumber(cvv);
        task.setFirstName(ed_text);
        task.setLastName(ed_last);
        task.setStreet1(locationSearch.getText().toString());
        task.setAmount(priceValue);
        task.setEmail(ed_email);
        task.setCheck_id(String.valueOf(lastId));

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
                        sendDataToApi("", accountNo, cvv, expMonth, expYear, checkedSave);

                    } else if (responseCode.equalsIgnoreCase("101")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Declined - The request is missing one or more fields", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("102")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Declined - One or more fields in the request contains invalid data.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("150")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Error - General CyberSource system failure.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("151")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Error - The request was received but there was a server timeout. This error does not include timeouts between the client and the server.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("152")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Error: The request was received, but a service did not finish running in time.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("204")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - Insufficient funds in the account.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("207")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - Issuing bank unavailable.'", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("208")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - Inactive card or card not authorized for card-not-present transactions.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("209")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - card verification number (CVV) did not match.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("210")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - The card has reached the credit limit.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("211")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - Invalid Card Verification Number (CVV).", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("221")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - The customer matched an entry on the processor negative file.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("222")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - customer account is frozen", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("231")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - Invalid account number", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("232")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - The card type is not accepted by the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("240")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Decline - The card type sent is invalid or does not correlate with the credit card number.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("250")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Error - The request was received, but there was a timeout at the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("480")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "The order is marked for review by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("481")) {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "The order has been rejected by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else {
                        hitGatewayCancel();
                        makeText(CheckoutPage.this, "Payment has been cancelled", Toast.LENGTH_SHORT).show();
                    }
                 /*   etfirstname.setText(response.body().getData().get(0).getFirstname());
                    lastname.setText(response.body().g=a().get(0).getCompany());
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
                makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showCards(String userId, final RecyclerView cardRV) {
        mainCardRV = cardRV;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.data_loading));
        progressDialog.show();

        final checkout task = new checkout();
        task.setUserid(userId);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.showCards(task).enqueue(new Callback<List<GetCardModel>>() {
            @Override
            public void onResponse(Call<List<GetCardModel>> call, Response<List<GetCardModel>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().size() > 0) {
                        listCards = new ArrayList<>();
                        listCards = response.body();
                        GetCardModel model = new GetCardModel();
                        model.setCard_number(Constants.NEWCARD);
                        listCards.add(model);
                        mainCardRV.setLayoutManager(new LinearLayoutManager(CheckoutPage.this));
                        adapter = new ShowCardAdapter(CheckoutPage.this, listCards);
                        mainCardRV.setAdapter(adapter);

                    } else {
                        GetCardModel model = new GetCardModel();
                        model.setCard_number(Constants.NEWCARD);
                        listCards.add(model);
                        mainCardRV.setLayoutManager(new LinearLayoutManager(CheckoutPage.this));
                        adapter = new ShowCardAdapter(CheckoutPage.this, listCards);
                        mainCardRV.setAdapter(adapter);
                        makeText(CheckoutPage.this, getString(R.string.no_card_to_show), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GetCardModel>> call, Throwable t) {
                progressDialog.dismiss();
                //   Toast.makeText(CheckoutPage.this, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

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
                partyCheckOut(dialog, model.getCard_number(), model.getCvc(), model.getCard_month(), model.getCard_year(), false);

            }

        }

        cardCLView.setVisibility(View.GONE);
        //Toast.makeText(this, "Another saved acard", Toast.LENGTH_SHORT).show();

        //  CheckOut(dialog, model.getCard_number(), model.getCvc(), model.getCard_month(), model.getCard_year());


    }


    private void addCardDetail(final Dialog mainDailog, final String cardValue, final String cvcValue, final String monthValue, final String yearValue, boolean isDefault) {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();

        final checkout task = new checkout();
        task.setCard_number(cardValue);
        task.setCard_month(monthValue);
        task.setCard_year(yearValue);
        task.setCvc(cvcValue);
        task.setUserid(Userid);

        task.setIsDefault(String.valueOf(1));

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.addCardApi(task).enqueue(new Callback<PasswordChangeModel>() {
            @Override
            public void onResponse(Call<PasswordChangeModel> call, Response<PasswordChangeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    mainDailog.dismiss();
                    // saveCardData(cardValue, monthValue, yearValue, cvcValue);
                    Toast.makeText(CheckoutPage.this, getString(R.string.card_added), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordChangeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveCardData(String cardValue, String monthValue, String yearValue, String cvv) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();

        try {
            editor.putString(Constants.CARD_VALUE, cardValue);
            editor.putString(Constants.CVC_VALUE, cvv);
            editor.putString(Constants.EXPIRY_MONTH, monthValue);
            editor.putString(Constants.EXPIRY_YEAR, yearValue);

        } catch (Exception ae) {
            ae.printStackTrace();
        }

        editor.apply();


    }


    @Override
    public void onBackPressed() {

       /* if(checkoutDone)
        {
            if(lastId!=0)
            {
                hitCronJob(String.valueOf(lastId),"Subscription");

            }
        }*/
        super.onBackPressed();
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


}


