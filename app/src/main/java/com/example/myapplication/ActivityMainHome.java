package com.example.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.FlipperAdapter;
import com.example.myapplication.Adapter.MainPackage;
import com.example.myapplication.Adapter.Packages;
import com.example.myapplication.FaqPage.FaqActivtiy;
import com.example.myapplication.Model.Retrofit.Datum;
import com.example.myapplication.Model.Retrofit.PackageModel;
import com.example.myapplication.Model.Retrofit.Promo;
import com.example.myapplication.Model.Retrofit.PromoModel;
import com.example.myapplication.Model.Retrofit.TermsConditionModel;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Model.Retrofit.history;
import com.example.myapplication.ParcelGeathering.ParcelBoxActivity;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.example.myapplication.Views.CustomTypefaceSpan;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMainHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CheckBox weeklycheck;
    LinearLayout weekly, weeks, month, moths, menus, menuview;
    Dialog dialog;
    TextView nametitle, textView, titleTB;
    String Title, date_time, date, Userid, name, email;
    Integer mYear, mMonth, mDay;
    ViewPager viewPager;
    RecyclerView foodmenu;
    Locale myLocale;
    String currentLanguage = "en", currentLang;
    String Menus = "gone";
    private AdapterViewFlipper adapterViewFlipper;
    String DialogCheck;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.banner1, R.drawable.banner2
    };
    private String languageValue = "en";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Spinner spinner = (Spinner) findViewById(R.id.spine);
        weeklycheck = findViewById(R.id.weeklycheck);
        weekly = findViewById(R.id.weekly);
        weeks = findViewById(R.id.weeks);
        month = findViewById(R.id.month);
        moths = findViewById(R.id.months);
        menus = findViewById(R.id.menus);
        menuview = findViewById(R.id.menuview);
        foodmenu = findViewById(R.id.foodmenu);
        titleTB = findViewById(R.id.titleTB);
        Toolbar toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        slider();

        //adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.adapterViewFlipper);
        /////////////Hit Package Api Here///////////

        //slider();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        DialogCheck = preferences.getString("title", "");
        Userid = preferences.getString("userid", "");
        name = preferences.getString("name", "");
        email = preferences.getString("email", "");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String subscribeNow = pref.getString(Constants.SUBSCRIBENOW, "no");
        if (DialogCheck == "title") {
            //   Buyitnow();
        } else if (subscribeNow.equals("yes") && DialogCheck != "title") {
            //    Buyitnow();
        } else {
            Package();
        }

        setSupportActionBar(toolbar);
        Package();
        FloatingActionButton fab = findViewById(R.id.fab);

        menuview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenu();
            }
        });


        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = "Weekly";
                String Price = "31";
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("title", Title);
                editor.putString("price", Price);
                editor.apply();

                Buyitnow();

            }
        });

        weeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = "2 Weeks";
                String Price = "60";
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("title", Title);
                editor.putString("price", Price);
                editor.apply();
                Buyitnow();
            }
        });


        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = "Monthly";
                String Price = "180";
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("title", Title);
                editor.putString("price", Price);
                editor.apply();
                Buyitnow();
            }
        });

        moths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = "2 Months";
                String Price = "360";
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("title", Title);
                editor.putString("price", Price);
                editor.apply();
                Buyitnow();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menus.setVisibility(View.VISIBLE);
                menus.postDelayed(new Runnable() {
                    public void run() {
                        menus.setVisibility(View.GONE);
                    }
                }, 2000);
                // Snackbar.make(view, "Hello Rajeev How can i Help You!!", Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        nametitle = header.findViewById(R.id.nametitle);
        textView = header.findViewById(R.id.textView);
        textView.setVisibility(View.VISIBLE);
        nametitle.setText(name);
        textView.setText(email);
        Menu menu = navigationView.getMenu();
        MenuItem target = menu.findItem(R.id.login);
        target.setVisible(true);
        target.setTitle(getString(R.string.login_string));


        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titleTB.setTypeface(custom_font_azab);
            titleTB.setTextSize(24);
            nametitle.setTypeface(custom_font_azab);
            textView.setTypeface(custom_font_azab);
        }


        if (languageValue.equalsIgnoreCase("ar")) {
            Menu m = navigationView.getMenu();
            for (int i = 0; i < m.size(); i++) {
                MenuItem mi = m.getItem(i);

                //for aapplying a font to subMenu ...
                SubMenu subMenu = mi.getSubMenu();
                if (subMenu != null && subMenu.size() > 0) {
                    for (int j = 0; j < subMenu.size(); j++) {
                        MenuItem subMenuItem = subMenu.getItem(j);
                        applyFontToMenuItem(subMenuItem);
                    }
                }


                applyFontToMenuItem(mi);
            }


        }


    }


    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    private void init(ArrayList<Promo> heros) {

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(ActivityMainHome.this, heros));

        CirclePageIndicator indicator = findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = heros.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            new AlertDialog.Builder(ActivityMainHome.this)
                    .setTitle(getString(R.string.app_name))
                    .setMessage(getString(R.string.want_to_exit))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(ActivityMainHome.this);
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.logo)
                    .show();

            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainhome, menu);
        MenuItem register = menu.findItem(R.id.mainprofile);

        if (Userid.equals("")) {
            register.setVisible(false);
        } else {
            register.setVisible(true);
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            Intent intent = new Intent(getApplicationContext(), ActivityMainHome.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.history) {

            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setMessage("terms");
            dialog.show();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            languageValue = preferences.getString(Constants.SETLANG, "en");

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
        } else if (id == R.id.privacy) {

            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setMessage("privacy");
            dialog.show();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            languageValue = preferences.getString(Constants.SETLANG, "en");
            final TermsConditionModel task = new TermsConditionModel();
            task.setContent("privacy");
            task.setLang(languageValue);
            final ApiServices userService = ApiUrl.createService(ApiServices.class,
                    "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
            userService.Getterms(task).enqueue(new Callback<TermsConditionModel>() {
                @Override
                public void onResponse(Call<TermsConditionModel> call, Response<TermsConditionModel> response) {
                    if (response.isSuccessful()) {
                        dialog.dismiss();
                        String Details = response.body().getContent();


                        Intent intent = new Intent(getApplicationContext(), PrivacyPolicy.class);
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
        } else if (id == R.id.packag) {
            Intent Home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(Home);
        } else if (id == R.id.parcel) {


            Intent intent = new Intent(getApplicationContext(), ParcelCafe.class);
            startActivity(intent);

        }/* else if (id == R.id.faq) {


            Intent intent = new Intent(getApplicationContext(), FaqActivtiy.class);
            intent.putExtra("pageFrom", "home");
            startActivity(intent);

        }*/ else if (id == R.id.menu) {
            Intent intent = new Intent(getApplicationContext(), ViewMenu.class);
            startActivity(intent);

        } else if (id == R.id.contact) {
            Intent contactus = new Intent(getApplicationContext(), ContactUs.class);
            startActivity(contactus);

        } else if (id == R.id.login) {
            SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.party) {
          /*  Intent party = new Intent(getApplicationContext(), PartyBox.class);
            startActivity(party);
*/

            Intent intent = new Intent(getApplicationContext(), ParcelBoxActivity.class);
            intent.putExtra("userId", Userid);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    public void showmenu() {
        TextView cancel;


        dialog.setContentView(R.layout.showmenu);
        cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        dialog.show();


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
                        Toast.makeText(getApplicationContext(), getString(R.string.you_have_choose) + date_time, Toast.LENGTH_LONG).show();
                        date = date_time;
                        Intent intent = new Intent(ActivityMainHome.this, CheckoutPage.class);
                        startActivity(intent);
                        //*************Call Time Picker Here ********************
                        // tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    private void Package() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        languageValue = preferences.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setUserid(Userid);
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPackage(task).enqueue(new Callback<PackageModel>() {
            @Override
            public void onResponse(Call<PackageModel> call, Response<PackageModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<Datum> packages = response.body().getData();
                    String details = packages.get(0).getTitle();

                  // GridLayoutManager latest = new GridLayoutManager(getApplicationContext(), 2);
                   LinearLayoutManager latest = new LinearLayoutManager(ActivityMainHome.this);

                    latest.setOrientation(GridLayoutManager.VERTICAL);
                    foodmenu.setItemAnimator(new DefaultItemAnimator());
                    foodmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityMainHome.this);
                    String subscribeNow = preferences.getString(Constants.SUBSCRIBENOW, "no");
                    String userid = preferences.getString("userid", "");

                    if (subscribeNow.equals("yes")) {
                        foodmenu.setAdapter(new Packages(packages, R.layout.menuslayouts, ActivityMainHome.this));
                    } else {
                        if (userid.isEmpty()) {
                            foodmenu.setAdapter(new Packages(packages, R.layout.menuslayouts, ActivityMainHome.this));

                        } else {
                            foodmenu.setAdapter(new MainPackage(packages, R.layout.menuslayouts, getApplicationContext()));

                        }

                    }
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+details,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PackageModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void slider() {

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final history task = new history();
        task.setLang(languageValue);
        userService.GetPromo(task).enqueue(new Callback<PromoModel>() {
            @Override
            public void onResponse(Call<PromoModel> call, Response<PromoModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    //List<Promo> packages= response.body().getData();

                    ArrayList<Promo> heros = (ArrayList<Promo>) response.body().getData();

                    //creating adapter object
                    FlipperAdapter adapter = new FlipperAdapter(getApplicationContext(), heros);

                    //adding it to adapterview flipper
                  /*  adapterViewFlipper.setAdapter(adapter);
                    adapterViewFlipper.setFlipInterval(2000);
                    adapterViewFlipper.startFlipping();*/

                    init(heros);

                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+details,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PromoModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
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
            Toast.makeText(ActivityMainHome.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

}
