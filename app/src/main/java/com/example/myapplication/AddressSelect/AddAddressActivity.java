package com.example.myapplication.AddressSelect;

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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Checkout.PlaceArrayAdapter;
import com.example.myapplication.CheckoutPage;
import com.example.myapplication.Constants;
import com.example.myapplication.MarkerAnimation;
import com.example.myapplication.Model.Retrofit.SaveAddressModel;
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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {
    private AutoCompleteTextView mAutocompleteTextView;
    private PlaceAutocompleteFragment autocompleteFragment;
    private PlaceArrayAdapter mPlaceArrayAdapter;
    GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private double currentLat = 30.6581;
    private double currentLong = 76.8355;
    ScrollView mainSV;

    private String searchPlace = "";
    private boolean searchCheck = false;
    private Marker currentLocationMarker;
    EditText locationSearch;
    EditText companyname, office, floor, homeTE;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    private TextView back, btnSave,btnClose;
    private TextView titlemenu;
    private static final int REQUEST_SELECT_PLACE = 1000;
    private double lat = 0.00;
    private double lng = 0.00;
    private String subscriptionId = "";
    private String userId = "";
    TextInputLayout name_text_inputssss;
    TextInputLayout name_text_inputsssss;
    TextInputLayout name_text_inputsss;
    TextInputLayout floorLayout;

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
        setContentView(R.layout.activity_add_address);
        mainSV = findViewById(R.id.mainSV);
        titlemenu = findViewById(R.id.titlemenu);
        btnSave = findViewById(R.id.btnSave);
        back = findViewById(R.id.back);
        companyname = findViewById(R.id.companyname);
        homeTE = findViewById(R.id.homeTE);
        office = findViewById(R.id.office);
        floor = findViewById(R.id.floor);
        btnClose = findViewById(R.id.btnClose);
        locationSearch = findViewById(R.id.delivery);

        if (getIntent().hasExtra("subscriptionId")) {
            subscriptionId = getIntent().getStringExtra("subscriptionId");
            userId = getIntent().getStringExtra("userId");
        }
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mAutocompleteTextView = findViewById(R.id.autoCompleteTextView);
        mAutocompleteTextView.setThreshold(3);
        setAutoCompleteView();
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setText("");
        autocompleteFragment.setHint("");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAddressActivity.super.onBackPressed();
            }
        });
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
                            .build(AddAddressActivity.this);
                    startActivityForResult(intent, REQUEST_SELECT_PLACE);
                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });

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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (companyname.getText().toString().length() == 0 || office.getText().toString().length() == 0 || floor.getText().toString().length() == 0) {
                    Toast.makeText(AddAddressActivity.this, "Please Enter data in all fields", Toast.LENGTH_SHORT).show();
                } else {

                    CheckOut();

                }


            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AddAddressActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            companyname.setTypeface(custom_font_azab);
            office.setTypeface(custom_font_azab);
            floor.setTypeface(custom_font_azab);
            homeTE.setTypeface(custom_font_azab);
            companyname.setTypeface(custom_font_azab);
            office.setTypeface(custom_font_azab);
            floor.setTypeface(custom_font_azab);
            locationSearch.setTypeface(custom_font_azab);
            btnSave.setTypeface(custom_font_azab);
            btnClose.setTypeface(custom_font_azab);
        }




        floorLayout = findViewById(R.id.floorLayout);
        name_text_inputsss = findViewById(R.id.name_text_inputsss);
        name_text_inputssss = findViewById(R.id.name_text_inputssss);
        name_text_inputsssss = findViewById(R.id.name_text_inputsssss);

        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");

            name_text_inputsss.setTypeface(custom_font_azab);
            name_text_inputssss.setTypeface(custom_font_azab);
            name_text_inputsssss.setTypeface(custom_font_azab);
            floorLayout.setTypeface(custom_font_azab);
        }

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAddressActivity.super.onBackPressed();
            }
        });

        }

    private void CheckOut() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.data_loading));
        dialog.show();
        final SaveAddressModel task = new SaveAddressModel();
        task.setUseriId(userId);
        task.setCid("");
        task.setCompany(companyname.getText().toString());
        task.setOffice(office.getText().toString());
        task.setFloor(floor.getText().toString());
        task.setLocation(locationSearch.getText().toString());
        task.setLatitude(latitudeSend);
        task.setLongitude(longitudeSend);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(AddAddressActivity.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.SaveAddress(task).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    AddAddressActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(AddAddressActivity.this);
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
                            if (!locality.isEmpty() && !country.isEmpty()) {
                                if (searchCheck) {
                                    searchCheck = false;
                                  //  locationSearch.setText(searchPlace);
                                    locationSetValue = searchPlace;
                                    longitudeSetValue = String.valueOf(latLng.longitude);
                                    latitudeSetValue = String.valueOf(latLng.latitude);
                                } else {
                                   // locationSearch.setText(locality + "  " + country);
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

}
