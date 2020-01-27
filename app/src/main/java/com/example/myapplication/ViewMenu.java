package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.MainMenuAdapter;
import com.example.myapplication.Model.Retrofit.MainMenuModel;
import com.example.myapplication.Model.Retrofit.MenuModel;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMenu extends AppCompatActivity {
    RecyclerView parcelmenu;
    TextView back, titlemenu;
    private String languageValue = "en";
    TabLayout tabLayout;
    RelativeLayout tabBar;
    ViewPager viewPager;
    private List<MainMenuModel> mainList = new ArrayList<>();
    private List<MainMenuModel> breakfastlist = new ArrayList<>();
    private List<MainMenuModel> lunchlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view);
        parcelmenu = findViewById(R.id.parcelmenu);
        back = findViewById(R.id.back);
        titlemenu = findViewById(R.id.titlemenu);
        tabBar = findViewById(R.id.tabBar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);


        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.breakfast)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.lunch)));



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        parcelmenu.setNestedScrollingEnabled(false);
        ViewMenu();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewMenu.super.onBackPressed();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ViewMenu.this);
        String languageValue = preferences.getString(Constants.SETLANG, "en");


        if (languageValue.equalsIgnoreCase("ar")) {
            changeTabsFont();
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font_azab);
            titlemenu.setTextSize(24);

        }

        if (languageValue.equalsIgnoreCase("ar")) {
            tabBar.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            tabBar.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }





        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().toString().equalsIgnoreCase(getString(R.string.breakfast))) {
                    parcelmenu.setAdapter(new MainMenuAdapter(breakfastlist, R.layout.main_menu_item_new, getApplicationContext(), getString(R.string.breakfast)));

                } else {
                    parcelmenu.setAdapter(new MainMenuAdapter(lunchlist, R.layout.main_menu_item_new, getApplicationContext(), getString(R.string.lunch)));

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void ViewMenu() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        languageValue = preferences.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setLang(languageValue);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetMenu(task).enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    List<MainMenuModel> parcel = response.body().getData();

                    mainList = parcel;

                    breakfastlist = mainList.subList(0,response.body().getBreakfast());
                    lunchlist = mainList.subList(response.body().getBreakfast(),response.body().getBreakfast()+response.body().getLunch());

                    GridLayoutManager latest = new GridLayoutManager(getApplicationContext(), 1);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    parcelmenu.setItemAnimator(new DefaultItemAnimator());
                    parcelmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    parcelmenu.setAdapter(new MainMenuAdapter(breakfastlist, R.layout.main_menu_item_new, getApplicationContext(), getString(R.string.breakfast)));

                }
            }

            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(custom_font_azab, Typeface.NORMAL);
                }
            }
        }
    }
}
