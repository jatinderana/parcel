package com.example.myapplication.GiftFriend;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.AddressSelect.AddAddressActivity;
import com.example.myapplication.Checkout.PlaceArrayAdapter;
import com.example.myapplication.CheckoutPage;
import com.example.myapplication.Constants;
import com.example.myapplication.MarkerAnimation;
import com.example.myapplication.Model.Retrofit.PackageResponse;
import com.example.myapplication.Model.Retrofit.UpdatePackagePojo;
import com.example.myapplication.R;
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

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftFriendActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {

    GoogleApiClient mGoogleApiClient;
    SupportMapFragment mapFragment;
    ScrollView mainSV;
    CardView mainCV;
    private GoogleMap mMap;
    private boolean searchCheck = false;
    private Marker currentLocationMarker;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private String searchPlace = "";
    EditText locationSearch, etfirstname, lastname, emails, phone, companyname, home_location, floor;
    TextView back;
    TextView titlemenu;
    private double currentLat = 30.6581;
    private double currentLong = 76.8355;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    private AutoCompleteTextView mAutocompleteTextView;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    private PlaceAutocompleteFragment autocompleteFragment;
    private static final int REQUEST_SELECT_PLACE = 1001;
    private double lat = 0.00;
    private double lng = 0.00;
    private String Userid = "";
    Button btnnext;
    private String selectedData = "";

    TextInputLayout name_text_input;
    TextInputLayout name_text_inputs;
    TextInputLayout name_text_inputss;
    TextInputLayout name_text_inputsss;
    TextInputLayout name_text_inputssss;
    TextInputLayout name_text_inputsssss;
    TextInputLayout floorLayout;
    TextInputLayout name_text_inputssssss;
    TextInputLayout name_text_inputsssssss;
    String locationSend = "Zirakpur";
    String latitudeSend = "30.6581";
    String longitudeSend = "76.8355";
    String locationSetValue = "Zirakpur";
    String latitudeSetValue = "30.6581";
    String longitudeSetValue = "76.8355";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_gift_friend);
        mainSV = findViewById(R.id.mainSV);
        if (getIntent().hasExtra("selectedData")) {
            selectedData = getIntent().getStringExtra("selectedData");
        }
        locationSearch =  findViewById(R.id.delivery);
        etfirstname =  findViewById(R.id.etfirstname);
        phone = findViewById(R.id.phone);
        lastname =  findViewById(R.id.lastname);
        companyname =  findViewById(R.id.companyname);
        home_location =  findViewById(R.id.home_location);
        floor =  findViewById(R.id.floor);
        back =  findViewById(R.id.back);
        titlemenu =  findViewById(R.id.titlemenu);
        emails =  findViewById(R.id.emails);
        mainCV =  findViewById(R.id.mainCV);

        btnnext = findViewById(R.id.btnnext);


        name_text_input = findViewById(R.id.name_text_input);
        name_text_inputs = findViewById(R.id.name_text_inputs);
        name_text_inputss = findViewById(R.id.name_text_inputss);
        name_text_inputsss = findViewById(R.id.name_text_inputsss);
        name_text_inputssss = findViewById(R.id.name_text_inputssss);
        name_text_inputsssss = findViewById(R.id.name_text_inputsssss);
        name_text_inputssssss = findViewById(R.id.name_text_inputssssss);
        name_text_inputsssssss = findViewById(R.id.name_text_inputsssssss);
        floorLayout = findViewById(R.id.floorLayout);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mapFragment = (WorkaroundMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.address_mapView);
        mapFragment.getMapAsync(this);
        mAutocompleteTextView =  findViewById(R.id.autoCompleteTextView);
        mAutocompleteTextView.setThreshold(3);
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        ((View) findViewById(R.id.place_autocomplete_search_button)).setVisibility(View.GONE);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setText("");
        autocompleteFragment.setHint("");
        setAutoCompleteView();
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
        configureCameraIdle();

        ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.address_mapView)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mainSV.requestDisallowInterceptTouchEvent(true);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftFriendActivity.super.onBackPressed();
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
                            .build(GiftFriendActivity.this);
                    startActivityForResult(intent, REQUEST_SELECT_PLACE);
                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etfirstname.getText().toString().isEmpty() || lastname.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || emails.getText().toString().isEmpty()) {
                    Toast.makeText(GiftFriendActivity.this, getString(R.string.please_fill_all_detail), Toast.LENGTH_SHORT).show();
                } else {
                    updatePackage();
                }
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(GiftFriendActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            companyname.setTypeface(custom_font_azab);
            emails.setTypeface(custom_font_azab);
            floor.setTypeface(custom_font_azab);
            phone.setTypeface(custom_font_azab);
            companyname.setTypeface(custom_font_azab);
            floor.setTypeface(custom_font_azab);
            locationSearch.setTypeface(custom_font_azab);
            etfirstname.setTypeface(custom_font_azab);
            lastname.setTypeface(custom_font_azab);
            home_location.setTypeface(custom_font_azab);
            floor.setTypeface(custom_font_azab);
            btnnext.setTypeface(custom_font_azab);


            name_text_input.setTypeface(custom_font_azab);
            name_text_inputs.setTypeface(custom_font_azab);
            name_text_inputss.setTypeface(custom_font_azab);
            name_text_inputsss.setTypeface(custom_font_azab);
            name_text_inputssss.setTypeface(custom_font_azab);
            name_text_inputsssss.setTypeface(custom_font_azab);
            floorLayout.setTypeface(custom_font_azab);
            name_text_inputssssss.setTypeface(custom_font_azab);
            name_text_inputsssssss.setTypeface(custom_font_azab);

        }


        if (languageValue.equalsIgnoreCase("ar")) {
            mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            etfirstname.setTextDirection(View.TEXT_DIRECTION_RTL);
            lastname.setTextDirection(View.TEXT_DIRECTION_RTL);
            emails.setTextDirection(View.TEXT_DIRECTION_RTL);
            phone.setTextDirection(View.TEXT_DIRECTION_RTL);
            companyname.setTextDirection(View.TEXT_DIRECTION_RTL);
            home_location.setTextDirection(View.TEXT_DIRECTION_RTL);
            floor.setTextDirection(View.TEXT_DIRECTION_RTL);
            locationSearch.setTextDirection(View.TEXT_DIRECTION_RTL);

        } else {
            mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

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

    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(GiftFriendActivity.this);
                if (latLng != null) {
                    try {
                        //  currentLocationMarker.setPosition(latLng);
                        if (currentLocationMarker == null)
                            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng));
                        else
                            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new CheckoutPage.LatLngInterpolator.Spherical());


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
                            lng = latLng.longitude;
                            lat = latLng.latitude;
                           /* latitudeSend = String.valueOf(latLng.latitude);
                            longitudeSend = String.valueOf(latLng.longitude);*/
                            if (!locality.isEmpty() && !country.isEmpty()) {
                                if (searchCheck) {
                                    searchCheck = false;
                                   // locationSearch.setText(searchPlace);
                                    locationSetValue = searchPlace;
                                    longitudeSetValue = String.valueOf(latLng.longitude);
                                    latitudeSetValue = String.valueOf(latLng.latitude);
                                } else {
                                  //  locationSearch.setText(locality + "  " + country);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnCameraIdleListener(onCameraIdleListener);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(currentLat, currentLong))
                .title("Set Location"));

        LatLng latLng = new LatLng(currentLat, currentLong);
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
                    locationSearch.setText(place.getAddress());
                    LatLng latLng = mMap.getCameraPosition().target;
                    latitudeSend = String.valueOf(lat);
                    longitudeSend = String.valueOf(lng);
                    try {

                        if (currentLocationMarker == null)
                            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(place.getLatLng()));
                        else
                            MarkerAnimation.animateMarkerToGB(currentLocationMarker, place.getLatLng(), new CheckoutPage.LatLngInterpolator.Spherical());
                        Location targetLocation = new Location("");//provider name is unnecessary
                        targetLocation.setLatitude(lat);//your coords of course
                        targetLocation.setLongitude(lng);
                        animateCamera(targetLocation);

                    } catch (Exception ae) {
                        ae.printStackTrace();
                    }

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

    private void updatePackage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Userid = preferences.getString("userid", "");
        final UpdatePackagePojo task = new UpdatePackagePojo();
        task.setUserid(Userid);
        task.setUptype("Gift");
        task.setPdate(selectedData);
        task.setFirstname(etfirstname.getText().toString().trim());
        task.setLastname(lastname.getText().toString().trim());
        task.setCompany(companyname.getText().toString().trim());
        task.setFloor(floor.getText().toString().trim());
        task.setOffice(home_location.getText().toString().trim());
        task.setPhone(phone.getText().toString().trim());
        task.setEmail(emails.getText().toString().trim());
        task.setLocation(locationSearch.getText().toString().trim());
        task.setLatitude(latitudeSend);
        task.setLongitude(longitudeSend);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.UpdatePackage(task).enqueue(new Callback<PackageResponse>() {
            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse> response) {
                if (response.isSuccessful()) {
                    GiftFriendActivity.this.finish();
                    Toast.makeText(GiftFriendActivity.this, getString(R.string.email_has_been_sent_string), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {
                Toast.makeText(GiftFriendActivity.this, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
