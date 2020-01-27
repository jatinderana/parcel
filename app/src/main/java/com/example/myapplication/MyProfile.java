package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CardScreen.CardActivity;
import com.example.myapplication.Checkout.PlaceArrayAdapter;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.Model.Retrofit.Myprofile;
import com.example.myapplication.Model.Retrofit.MyprofileModel;
import com.example.myapplication.Model.Retrofit.PasswordChangeModel;
import com.example.myapplication.Model.Retrofit.checkout;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.example.myapplication.Views.WorkaroundMapFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
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

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    EditText etuserid, etfirstname, lastname, emails, phone, companyname, office, floor, zip, delivery;
    Button btnnext;
    TextView changepassword, back, titlemenu, addCard, showCards;
    private Marker currentLocationMarker;
    private GoogleMap mMap;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    private AutoCompleteTextView mAutocompleteTextView;
    Location mLastLocation;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    Marker mCurrLocationMarker;
    private static final int REQUEST_SELECT_PLACE = 1000;
    EditText locationSearch;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    SupportMapFragment mapFragment;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    String firstname, lastnamee, email, phoneno, company, officeno, floorno, dob, formtype, Userid;
    private PlaceAutocompleteFragment autocompleteFragment;
    ScrollView mainSV;
    private double currentLat = 30.6581;
    private double currentLong = 76.8355;
    private double lat = 0.00;
    private double lng = 0.00;
    private String savedLatitude = "30.6581";
    private String savedLongitude = "76.8355";
    private String savedLocation = "Zirakpur";
    TextInputLayout text_input;
    TextInputLayout name_text_input;
    TextInputLayout name_text_inputs;
    TextInputLayout name_text_inputss;
    TextInputLayout name_text_inputsss;
    TextInputLayout name_text_inputssss;
    TextInputLayout name_text_inputsssss;
    TextInputLayout name_text_inputssssss;
    TextInputLayout name_text_inputsssssss;
    TextInputLayout floorLayout;
    CardView mainCV;
    private Dialog dialog;
    int isDefault = 0;
    private String locationSetValue = "oman";
    String latitudeSetValue = "21.4735";
    String longitudeSetValue = "55.9754";
    private String locationSend = "oman";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        etuserid = findViewById(R.id.etuserid);
        etfirstname = findViewById(R.id.etfirstname);
        lastname = findViewById(R.id.lastname);
        emails = findViewById(R.id.emails);
        phone = findViewById(R.id.phone);
        companyname = findViewById(R.id.companyname);
        office = findViewById(R.id.office);
        floor = findViewById(R.id.floor);
        zip = findViewById(R.id.zip);
        mainCV = findViewById(R.id.mainCV);
        locationSearch = findViewById(R.id.delivery);
        delivery = findViewById(R.id.delivery);
        btnnext = findViewById(R.id.btnnext);
        titlemenu = findViewById(R.id.titlemenu);
        back = findViewById(R.id.back);
        mainSV = findViewById(R.id.mainSV);
        floorLayout = findViewById(R.id.floorLayout);
        addCard = findViewById(R.id.addCard);
        showCards = findViewById(R.id.showCards);


        text_input = findViewById(R.id.text_input);
        name_text_input = findViewById(R.id.name_text_input);
        name_text_inputs = findViewById(R.id.name_text_inputs);
        name_text_inputss = findViewById(R.id.name_text_inputss);
        name_text_inputsss = findViewById(R.id.name_text_inputsss);
        name_text_inputssss = findViewById(R.id.name_text_inputssss);
        name_text_inputsssss = findViewById(R.id.name_text_inputsssss);
        name_text_inputsssss = findViewById(R.id.name_text_inputsssss);
        name_text_inputssssss = findViewById(R.id.name_text_inputssssss);
        name_text_inputssssss = findViewById(R.id.name_text_inputssssss);
        name_text_inputsssssss = findViewById(R.id.name_text_inputsssssss);


        changepassword = findViewById(R.id.changepassword);
        firstname = etfirstname.getText().toString().trim();
        lastnamee = lastname.getText().toString().trim();
        email = emails.getText().toString().trim();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Userid = preferences.getString("userid", "");

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                intent.putExtra("userId", etuserid.getText().toString().trim());
                startActivity(intent);
            }
        });

        formtype = "Profile";
        if (getIntent().hasExtra("savedLongitude")) {
            savedLongitude = getIntent().getStringExtra("savedLongitude");
            savedLatitude = getIntent().getStringExtra("savedLatitude");
            //condition lat long
            try {
                lat = Double.parseDouble(savedLatitude);
                lng = Double.parseDouble(savedLongitude);
            } catch (Exception e) {
                e.getMessage();
            }

        }

        showCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCards(Userid);

            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setText("");
        autocompleteFragment.setHint("");
        mAutocompleteTextView = findViewById(R.id.autoCompleteTextView);
        mAutocompleteTextView.setThreshold(3);
        setAutoCompleteView();
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
                            .build(MyProfile.this);
                    startActivityForResult(intent, REQUEST_SELECT_PLACE);
                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.rideCar_mapView);
        mapFragment.getMapAsync(this);

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
                        .target(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude)))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

           /*     mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(currentLat, currentLong))
                        .draggable(true)
                        .title("Set Location"));*/
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 100, null);
            }
        });

        zip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(zip);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(MyProfile.this);
                dialog.setCancelable(false);
                dialog.setMessage(getString(R.string.data_loading));
                dialog.show();


                final Myprofile task = new Myprofile();

                task.setUserid(etuserid.getText().toString().trim());
                task.setFirstname(etfirstname.getText().toString().trim());
                task.setLastname(lastname.getText().toString().trim());
                task.setEmail(emails.getText().toString().trim());
                task.setPhone(phone.getText().toString().trim());
                task.setCompany(companyname.getText().toString().trim());
                task.setOffice(office.getText().toString().trim());
                task.setFloor(floor.getText().toString().trim());
                task.setDob(zip.getText().toString().trim());
                task.setFormtype("Profile");
                task.setLocation(delivery.getText().toString().trim());
                task.setLatitude(String.valueOf(lat));
                task.setLongitude(String.valueOf(lng));


                final ApiServices userService = ApiUrl.createService(ApiServices.class,
                        "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
                userService.UpdateProfile(task).enqueue(new Callback<MyprofileModel>() {
                    @Override
                    public void onResponse(Call<MyprofileModel> call, Response<MyprofileModel> response) {
                        if (response.isSuccessful()) {
                            dialog.dismiss();

                            Toast.makeText(getApplicationContext(), getString(R.string.profile_updated_success), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<MyprofileModel> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProfile.super.onBackPressed();
            }
        });


        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCardDialog();
            }
        });


        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            etfirstname.setTypeface(custom_font_azab);
            lastname.setTypeface(custom_font_azab);
            emails.setTypeface(custom_font_azab);
            phone.setTypeface(custom_font_azab);
            companyname.setTypeface(custom_font_azab);
            office.setTypeface(custom_font_azab);
            floor.setTypeface(custom_font_azab);
            locationSearch.setTypeface(custom_font_azab);
            changepassword.setTypeface(custom_font_azab);
            btnnext.setTypeface(custom_font_azab);


            text_input.setTypeface(custom_font_azab);
            name_text_input.setTypeface(custom_font_azab);
            name_text_inputs.setTypeface(custom_font_azab);
            name_text_inputss.setTypeface(custom_font_azab);
            name_text_inputsss.setTypeface(custom_font_azab);
            name_text_inputssss.setTypeface(custom_font_azab);
            name_text_inputsssss.setTypeface(custom_font_azab);
            name_text_inputssssss.setTypeface(custom_font_azab);
            name_text_inputsssssss.setTypeface(custom_font_azab);
            floorLayout.setTypeface(custom_font_azab);
            delivery.setTypeface(custom_font_azab);
            addCard.setTypeface(custom_font_azab);
            showCards.setTypeface(custom_font_azab);


        }


        if (languageValue.equalsIgnoreCase("ar")) {
            mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            etfirstname.setTextDirection(View.TEXT_DIRECTION_RTL);
            lastname.setTextDirection(View.TEXT_DIRECTION_RTL);
            emails.setTextDirection(View.TEXT_DIRECTION_RTL);
            phone.setTextDirection(View.TEXT_DIRECTION_RTL);
            companyname.setTextDirection(View.TEXT_DIRECTION_RTL);
            office.setTextDirection(View.TEXT_DIRECTION_RTL);
            floor.setTextDirection(View.TEXT_DIRECTION_RTL);
            locationSearch.setTextDirection(View.TEXT_DIRECTION_RTL);
            etuserid.setTextDirection(View.TEXT_DIRECTION_RTL);


        } else {
            mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        configureCameraIdle(savedLatitude, savedLongitude, savedLocation);
    }

    private void showCardDialog() {

        Button closeBT, nextBT;
        final EditText monthET, cardET, yearET, cvvET;
        TextView cardTV, cvvTV;
        dialog = new Dialog(this, android.R.style.Theme_Light);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_card_dialog);
        cardTV = dialog.findViewById(R.id.cardTV);
        cardET = dialog.findViewById(R.id.cardET);
        monthET = dialog.findViewById(R.id.monthET);
        yearET = dialog.findViewById(R.id.yearET);
        cvvET = dialog.findViewById(R.id.cvvET);
        cvvTV = dialog.findViewById(R.id.cvvTV);
        closeBT = dialog.findViewById(R.id.closeBT);


        nextBT = dialog.findViewById(R.id.nextBT);
        CheckBox defaultCB = dialog.findViewById(R.id.defaultCB);
        defaultCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    isDefault = 1;
                } else {
                    isDefault = 0;
                }
            }
        });
       /* ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = thisYear; i <= thisYear + 50; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        final Spinner spinYear = dialog.findViewById(R.id.yearspin);
        final Spinner monthSpin = dialog.findViewById(R.id.monthSpin);
        spinYear.setAdapter(adapter);*/

        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

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
        monthSpin.setAdapter(adapterCurrent);
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

       /* spinYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinYear.getSelectedItem().equals(thisYear)) {
                    monthSpin.setAdapter(adapterCurrent);

                } else {
                    monthSpin.setAdapter(adapterMonth);
                }
            }
        });
*/

        monthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyboard(MyProfile.this);
                new RackMonthPicker(MyProfile.this)
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
                Constants.hideKeyboard(MyProfile.this);
                new RackMonthPicker(MyProfile.this)
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


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyProfile.this);
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

            cardET.setTextSize(13);
            monthET.setTextSize(13);
            yearET.setTextSize(13);
            cvvET.setTextSize(13);
            defaultCB.setTypeface(custom_font_azab);
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
                Constants.hideKeyboard(MyProfile.this);
                if (cardET.getText().toString().isEmpty()) {
                    Toast.makeText(MyProfile.this, getString(R.string.please_enter_card), Toast.LENGTH_SHORT).show();
                }/* else if (monthET.getText().toString().isEmpty()) {
                    Toast.makeText(MyProfile.this, getString(R.string.please_enter_month), Toast.LENGTH_SHORT).show();

                }*/ /*else if (yearET.getText().toString().isEmpty()) {
                    Toast.makeText(MyProfile.this, getString(R.string.please_enter_year), Toast.LENGTH_SHORT).show();

                } */ else if (cvvET.getText().toString().isEmpty()) {
                    Toast.makeText(MyProfile.this, getString(R.string.please_enter_cvv), Toast.LENGTH_SHORT).show();

                } else {
                    addCardDetail(dialog, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString(), isDefault);
                }

            }
        });
        dialog.show();
    }

    private void addCardDetail(final Dialog mainDailog, final String cardValue, final String cvcValue, final String monthValue, final String yearValue, int isDefault) {

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
        task.setIsDefault(String.valueOf(isDefault));

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.addCardApi(task).enqueue(new Callback<PasswordChangeModel>() {
            @Override
            public void onResponse(Call<PasswordChangeModel> call, Response<PasswordChangeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    mainDailog.dismiss();
                    saveCardData(cardValue, monthValue, yearValue, cvcValue);
                    Toast.makeText(MyProfile.this, getString(R.string.card_added), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordChangeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showCards(final String userId) {

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
                        Intent intent = new Intent(MyProfile.this, CardActivity.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MyProfile.this, "No cards to show", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GetCardModel>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void configureCameraIdle(final String savedLatitude, final String savedLongitude, String location) {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng latLng = mMap.getCameraPosition().target;

                //    latLng = new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude));
//                if(savedLatitude.isEmpty() || savedLatitude.equals("0.00"))
//                {
//                    latLng  =  mMap.getCameraPosition().target;
//                }else
//                {
//                    latLng = new LatLng(Double.parseDouble(savedLatitude),Double.parseDouble(savedLongitude));
//                }

                Geocoder geocoder = new Geocoder(MyProfile.this);

                if (latLng != null) {
                    lat = latLng.latitude;
                    lng = latLng.longitude;
                    try {
                        //  currentLocationMarker.setPosition(latLng);
                        if (currentLocationMarker == null)
                            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng));
                        else {
                            latLng = mMap.getCameraPosition().target;
                            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new CheckoutPage.LatLngInterpolator.Spherical());
                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker m) {
                                    locationSearch.setText(locationSetValue);
                                    locationSend = locationSetValue;
                                    lat = Double.parseDouble(latitudeSetValue);
                                    lng = Double.parseDouble(longitudeSetValue);

                                    return true;
                                }
                            });

                        }

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
                                locationSetValue = locality + "  " + country;

                                // delivery.setText(locality + "  " + country);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
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

        mMap.setOnCameraIdleListener(onCameraIdleListener);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude)))
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

    public void searchLocation(View view) {
        EditText locationSearch = findViewById(R.id.delivery);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            Toast.makeText(getApplicationContext(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
        }
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

    @Override
    protected void onResume() {
        super.onResume();
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
                    etuserid.setText(response.body().getData().get(0).getUserid());
                    etfirstname.setText(response.body().getData().get(0).getFirstname());
                    lastname.setText(response.body().getData().get(0).getLastname());
                    emails.setText(response.body().getData().get(0).getEmail());
                    phone.setText(response.body().getData().get(0).getPhone());
                    companyname.setText(response.body().getData().get(0).getCompany());
                    office.setText(response.body().getData().get(0).getOffice());
                    floor.setText(response.body().getData().get(0).getFloor());
                    zip.setText(response.body().getData().get(0).getDob());
                    delivery.setText(response.body().getData().get(0).getLocation());
                    savedLocation = response.body().getData().get(0).getLocation();
                    savedLatitude = response.body().getData().get(0).getLatitude();
                    savedLongitude = response.body().getData().get(0).getLongitude();
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude))).title("Saved Location"));


                }
            }

            @Override
            public void onFailure(Call<MyprofileModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setAutoCompleteView() {
        mAutocompleteTextView.setOnItemClickListener(mAutocompleteClickListener);
        mPlaceArrayAdapter = new PlaceArrayAdapter(this, android.R.layout.simple_list_item_1,
                BOUNDS_MOUNTAIN_VIEW, null);
        mAutocompleteTextView.setAdapter(mPlaceArrayAdapter);
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
        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Place place = PlaceAutocomplete.getPlace(this, data);
        //citys.setText(place.getName());

        if (requestCode == REQUEST_SELECT_PLACE) {
            String confirm = String.valueOf(data);
            if (data != null) {
                Place place = PlacePicker.getPlace(data, this);
                if (place != null) {
                    lat = place.getLatLng().latitude;
                    lng = place.getLatLng().longitude;
                    locationSetValue = String.valueOf(place.getAddress());
                    locationSearch.setText(place.getAddress());
                    LatLng latLng = mMap.getCameraPosition().target;

                    try {

                        //  currentLocationMarker.setPosition(latLng);
                        if (currentLocationMarker == null)
                            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(place.getLatLng()));
                        else
                            MarkerAnimation.animateMarkerToGB(currentLocationMarker, place.getLatLng(), new CheckoutPage.LatLngInterpolator.Spherical());
                        Location targetLocation = new Location("");//provider name is unnecessary
                        targetLocation.setLatitude(lat);//your coords of course
                        targetLocation.setLongitude(lng);
                        animateCamera(targetLocation);
                           /* if (firstTimeFlag && mMap != null) {
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

    private void animateCamera(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(16).build();
    }

    private void showDatePickerDialog(final EditText spine) {
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
              /*  TextView datePickerValueTextView = (TextView)findViewById(R.id.datePickerValue);
                datePickerValueTextView.setText(strBuf.toString());*/
            }
        };

        long startDateLong = 0, endDateLong = 0;
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        try {

            Date date = df.parse(formattedDate);

            endDateLong = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Get current year, month and day.
        Calendar now = Calendar.getInstance();
        int year = now.get(java.util.Calendar.YEAR);
        int month = now.get(java.util.Calendar.MONTH);
        int day = now.get(java.util.Calendar.DAY_OF_MONTH);

        // Create the new DatePickerDialog instance.
        DatePickerDialog datePickerDialog = new DatePickerDialog(MyProfile.this, onDateSetListener, year, month, day);

        datePickerDialog.setTitle("Please select date.");
        datePickerDialog.getDatePicker().setMaxDate(endDateLong);
        // Popup the dialog.
        datePickerDialog.show();
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

}



