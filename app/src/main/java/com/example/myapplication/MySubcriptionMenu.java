package com.example.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ReplaceItemAdapter;
import com.example.myapplication.Model.Retrofit.PackageResponse;
import com.example.myapplication.Model.Retrofit.Replace;
import com.example.myapplication.Model.Retrofit.ReplaceModel;
import com.example.myapplication.Model.Retrofit.TermsConditionModel;
import com.example.myapplication.Model.Retrofit.UpdatePackagePojo;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySubcriptionMenu extends AppCompatActivity {
    RecyclerView parcelmenu;
    TextView back, titlemenu, replaceBT;
    String Duration;
    LinearLayout scrollView;

    RelativeLayout btnRL;
    private String replaceLimit = "0";
    private String Userid = "";
    private String resp = "";
    private String selectedData = "", duration = "";
    private ReplaceItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        parcelmenu = findViewById(R.id.parcelmenu);
        btnRL = findViewById(R.id.btnRL);
        back = findViewById(R.id.back);
        scrollView = findViewById(R.id.scrollView);
        titlemenu = findViewById(R.id.titlemenu);
        replaceBT = findViewById(R.id.replaceBT);
        final TextView cancleButton = findViewById(R.id.closeBT);
        parcelmenu.setNestedScrollingEnabled(false);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Duration = preferences.getString("time", "");
        //   Toast.makeText(getApplicationContext(), "" + Duration, Toast.LENGTH_LONG).show();
        ParcelCafe();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySubcriptionMenu.super.onBackPressed();
            }
        });
        if (getIntent().hasExtra("replaceLimit")) {
            replaceLimit = getIntent().getStringExtra("replaceLimit");
            selectedData = getIntent().getStringExtra("selectedData");
            if (getIntent().hasExtra("duration"))
                duration = getIntent().getStringExtra("duration");
        }
        replaceBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menuId = adapter.getMenuId();
                if (menuId.isEmpty()) {
                    Toast.makeText(MySubcriptionMenu.this, "Please select menu to replace", Toast.LENGTH_SHORT).show();
                } else {
                    if (replaceLimit.equalsIgnoreCase("0")) {
                        Toast.makeText(MySubcriptionMenu.this, getString(R.string.no_of_replace_attempts), Toast.LENGTH_SHORT).show();

                    } else {
                            showSureDialog(menuId);


                    }

                }

            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySubcriptionMenu.super.onBackPressed();
            }
        });

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);
            replaceBT.setTypeface(custom_font_azab);
            cancleButton.setTypeface(custom_font_azab);
        }


        if (languageValue.equalsIgnoreCase("ar")) {
            btnRL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            scrollView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            btnRL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            scrollView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }


    }

    private void ParcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MySubcriptionMenu.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setLang(languageValue);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetRelace(task).enqueue(new Callback<ReplaceModel>() {
            @Override
            public void onResponse(Call<ReplaceModel> call, Response<ReplaceModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<Replace> menu = response.body().getData();

                    GridLayoutManager latest = new GridLayoutManager(getApplicationContext(), 1);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    parcelmenu.setItemAnimator(new DefaultItemAnimator());
                    parcelmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    adapter = new ReplaceItemAdapter(menu, R.layout.row_addapt_subcription, getApplicationContext(),duration);
                    parcelmenu.setAdapter(adapter);

                    btnRL.setVisibility(View.VISIBLE);
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReplaceModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showSureDialog(final String menuId) {
        final Dialog dialog = new Dialog(this, R.style.mytheme);
        dialog.setContentView(R.layout.layout_sure_dialog);
        dialog.setTitle("");
        final TextView dialogButton = dialog.findViewById(R.id.nextBT);
        final CheckBox termCB = dialog.findViewById(R.id.termCB);
        TextView titleTV = dialog.findViewById(R.id.titleTV);
        final TextView agreeTV = dialog.findViewById(R.id.agreeTV);
        final TextView cancleButton = dialog.findViewById(R.id.closeBT);
        if (termCB.isChecked()) {

        } else {
            dialogButton.setAlpha(0.4f);
        }

        termCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogButton.setAlpha(1f);
                } else {
                    dialogButton.setAlpha(0.4f);
                }

            }
        });

        agreeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTerms();
               // startActivity(new Intent(MySubcriptionMenu.this, TermsConditions.class));
            }
        });


        titleTV.setText(getString(R.string.your_food)+" " + replaceLimit+" " + getString(R.string.time_subscription));

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (replaceLimit.equalsIgnoreCase("0")) {
                    Toast.makeText(MySubcriptionMenu.this, getString(R.string.no_of_replace_attempts), Toast.LENGTH_SHORT).show();

                } else {
                    if (termCB.isChecked()) {
                        updatePackage(menuId);
                        dialog.dismiss();
                    }

                }


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

    private void getTerms() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("terms");
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final TermsConditionModel task = new TermsConditionModel();
        task.setContent("terms");
        task.setLang(languageValue);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.Getterms(task).enqueue(new Callback<TermsConditionModel>() {
            @Override
            public void onResponse(Call<TermsConditionModel> call, Response<TermsConditionModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    String Details = response.body().getContent();


                    Intent intent = new Intent(getApplicationContext(), TermsConditions.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("details", Details);
                    editor.apply();
                    //  intent.putExtra("countrycodes",movies.get(position).getCode());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<TermsConditionModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updatePackage(String menuId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Userid = preferences.getString("userid", "");
        final UpdatePackagePojo task = new UpdatePackagePojo();
        task.setUserid(Userid);
        task.setPdate(selectedData);
        task.setUptype("Replace");
        if (duration.equalsIgnoreCase("breakfast")) {
            task.setBdayreplace(menuId);
            task.setBdoubleqty("");
            task.setLdayreplace("");
            task.setLdoubleqty("");
        } else {
            task.setBdayreplace("");
            task.setBdoubleqty("");
            task.setLdayreplace(menuId);
            task.setLdoubleqty("");
        }
        task.setFoodtype(duration);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.UpdatePackage(task).enqueue(new Callback<PackageResponse>() {
            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse> response) {
                if (response.isSuccessful()) {
                    resp = response.body().toString();
                    Toast.makeText(MySubcriptionMenu.this, "Food Replaced Successfully!", Toast.LENGTH_SHORT).show();
                    MySubcriptionMenu.this.finish();

                    //   List<Replace> menu = response.body();

                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {
                Toast.makeText(MySubcriptionMenu.this, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Restart(Context ctx) {
        //geting activty from context
        MyAccountHome a = (MyAccountHome) ctx;
        //forcing activity to recrete
        a.recreate();
    }
}
