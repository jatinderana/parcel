package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Retrofit.PassValue;
import com.example.myapplication.Model.Retrofit.homemenu;
import com.example.myapplication.Model.Retrofit.homemenumodel;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderView extends AppCompatActivity {
    public GregorianCalendar cal_month, cal_month_copy;
    private HwAdapter hwAdapter;
    private TextView tv_month, title, back;
    LinearLayout linearLayout,headerLL;
    String Title, getdiscription, checkstatus;
    Dialog dialog;
    String date_time, date, titledate, titledate1, maintitle1, maintitle, titledate2, maintitle2, titledate3,
            maintitle3, titledate4, titledate5, maintitle4, checkdate, duration, imageboth, imageboth1, imageboth2, imageboth3, imageboth4, imageboth5,
            imageboth6, imageboth7, imageboth8, imageboth9, imageboth10, imageboth11, imageboth12, imageboth13, imageboth14, imageboth15, imageboth16,
            imageboth17, imageboth18, imageboth19, imageboth20, imageboth21, imageboth22, imageboth23, imageboth24, imageboth25, imageboth26, imageboth27, imageboth28,
            imageboth29, imageboth30, imageboth31, imageboth32, imageboth33, imageboth34, imageboth35, imageboth36, imageboth37, imageboth38, imageboth39, imageboth40,
            images1, images2, images3, images4, images5, newdate, titlemain, titlemain1, titlemain2, titlemain3,
            titlemain4;

    String weekstitle1, weekstitle2, weekstitle3, weekstitle4, weekstitle5, weeksimage1, weeksimage2, weeksimage3, weeksimage4, weeksimage5,
            weeksdate1, weeksdate2, weeksdate3, weeksdate4, weeksdate5, weektitlemain1, weektitlemain2, weektitlemain3, weektitlemain4, weektitlemain5,
            getWeektitlemain6, getWeektitlemain7, getWeektitlemain8, getWeektitlemain9, getWeektitlemain10;


    String monthtitle1, monthtitle2, monthtitle3, monthtitle4, monthtitle5, monthtitle6, monthtitle7, monthtitle8, monthtitle9, monthtitle10, monthtitle11, monthtitle12,
            monthtitle13, monthtitle14, monthtitle15, monthtitle16, monthtitle17, monthtitle18, monthtitle19, monthtitle20, monthtitle21, monthtitle22, monthtitle23, monthtitle24, monthtitle25,
            monthtitle26, monthtitle27, monthtitle28, monthtitle29, monthtitle30, monthtitle31, monthtitle32, monthtitle33, monthtitle34, monthtitle35, monthtitle36, monthtitle37, monthtitle38,
            monthtitle39, monthtitle40, monthtitle41, monthtitle42, monthtitle43, monthtitle44, monthtitle45, monthtitle46, monthtitle47, monthtitle48, monthtitle49, monthtitle50,
            monthimage1, monthimage2, monthimage3, monthimage4, monthimage5, monthimage6, monthimage7, monthimage8, monthimage9, monthimage10,
            monthimage11, monthimage12, monthimage13, monthimage14, monthimage15, monthimage16, monthimage17, monthimage18, monthimage19,
            monthimage20, monthimage21, monthimage22, monthimage23, monthimage24, monthimage25, monthimage26, monthimage27, monthimage28, monthimage29, monthimage30,
            monthimage31, monthimage32, monthimage33, monthimage34, monthimage35, monthimage36, monthimage37, monthimage38, monthimage39, monthimage40, monthimage41, monthimage42, monthimage43, monthimage44,
            monthimage45, monthimage46, monthimage47, monthimage48, monthimage49, monthimage50,
            monthdate1, monthdate2, monthdate3, monthdate4, monthdate5, monthdate6, monthdate7, monthdate8, monthdate9, monthdate10,
            monthdate11, monthdate12, monthdate13, monthdate14, monthdate15, monthdate16, monthdate17, monthdate18, monthdate19, monthdate20, monthdate21,
            monthdate22, monthdate23, monthdate24, monthdate25, monthdate26, monthdate27, monthdate28, monthdate29, monthdate30, monthdate31, monthdate32,
            monthdate33, monthdate34, monthdate35, monthdate36, monthdate37, monthdate38, monthdate39, monthdate40, monthdate41, monthdate42, monthdate43, monthdate44, monthdate45, monthdate46,
            monthdate47, monthdate48, monthdate49, monthdate50, monthtitlemain1, monthtitlemain2,
            monthtitlemain3, monthtitlemain4, monthtitlemain5, monthtitlemain6, monthtitlemain7, monthtitlemain8, monthtitlemain9, monthtitlemain10, monthtitlemain11, monthtitlemain12,
            monthtitlemain13, monthtitlemain14, monthtitlemain15, monthtitlemain16, monthtitlemain17, monthtitlemain18, monthtitlemain19, monthtitlemain20, monthtitlemain21, monthtitlemain22, monthtitlemain23, monthtitlemain24, monthtitlemain25, monthtitlemain26, monthtitlemain27,
            monthtitlemain28, monthtitlemain29, monthtitlemain30, monthtitlemain31, monthtitlemain32, monthtitlemain33, monthtitlemain34, monthtitlemain35, monthtitlemain36, monthtitlemain37, monthtitlemain38, monthtitlemain39, monthtitlemain40, monthtitlemain41, monthtitlemain42, monthtitlemain43, monthtitlemain44, monthtitlemain45, monthtitlemain46, monthtitlemain47, monthtitlemain48, monthtitlemain49, monthtitlemain50;


    Integer mYear, mMonth, mDay;
    TextView etdate,nextBT,titlemenu,tap;
    DatePickerDialog datePickerDialog;
    DatePicker datePicker;
    Calendar c;
    homemenu home;
    private String titleMain = "";
    private String drinkId = "";
    private String datesMain = "";
    private String status = "";
    private String packageId = "";
    private String packageTitle = "";
    private String packageDuration = "";
    private String choosepersonCheck = "";
    private String softDrinkCheck = "";
    private String peopleMain = "";
    private String subscribeNumber = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);
        back = findViewById(R.id.back);
        titlemenu = findViewById(R.id.titlemenu);
        nextBT = findViewById(R.id.nextBT);
        tap = findViewById(R.id.tap);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        tv_month = findViewById(R.id.tv_month);
        title = findViewById(R.id.title);
        headerLL = findViewById(R.id.headerLL);
        // maintitle="this is the your food list";


        linearLayout = findViewById(R.id.ok);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Title = preferences.getString("title", "");
        duration = preferences.getString("time", "");
        newdate = preferences.getString("dates", "");
        checkstatus = preferences.getString("status", "");
        // maintitle = preferences.getString("breakfast", "");
        //    Toast.makeText(getApplicationContext(),"Status is"+checkstatus,Toast.LENGTH_LONG).show();


        // Buyitnow();
        // ParcelCafe();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalenderView.super.onBackPressed();
            }
        });


        if (getIntent().hasExtra("choosepersonCheck")) {
            titleMain = getIntent().getStringExtra("title");
            datesMain = getIntent().getStringExtra("dates");
            status = getIntent().getStringExtra("status");
            packageId = getIntent().getStringExtra("packageId");
            packageTitle = getIntent().getStringExtra("packageTitle");
            packageDuration = getIntent().getStringExtra("packageDuration");
            choosepersonCheck = getIntent().getStringExtra("choosepersonCheck");
            peopleMain = getIntent().getStringExtra("peopleMain");
            drinkId = getIntent().getStringExtra("drinkId");
            subscribeNumber  = peopleMain.replaceAll("[^0-9]", "");
            if (getIntent().hasExtra("softDrinkCheck")) {
                softDrinkCheck = getIntent().getStringExtra("softDrinkCheck");
            }
        }


        if (Title.equals(getString(R.string.weekly_string))) {
            ParcelCafe();
            //    Toast.makeText(getApplicationContext(),"1 weeks",Toast.LENGTH_LONG).show();
        }

        if (Title.equals(getString(R.string._2_weeks))) {
            weeks();
            //    Toast.makeText(getApplicationContext(),"2 weeks",Toast.LENGTH_LONG).show();
        }
        if (Title.equals(getString(R.string.month_string))) {
            Monthlys();
            //    Toast.makeText(getApplicationContext(),"months",Toast.LENGTH_LONG).show();
        }

        if (Title.equals(getString(R.string._3_month))) {

            Months();

            //   Toast.makeText(getApplicationContext(),"3 months",Toast.LENGTH_LONG).show();
        }

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

        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        hwAdapter = new HwAdapter(this, cal_month, HomeCollection.date_collection_arr);


        title.setText(Title);

        //set font


        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(getAssets(), "arajozoor_regular.otf");
            titlemenu.setTypeface(custom_font);
            titlemenu.setTextSize(24);
            title.setTypeface(custom_font_azab);
            tv_month.setTypeface(custom_font_azab);
            tap.setTypeface(custom_font_azab);
            nextBT.setTypeface(custom_font_azab);
        }
        if (languageValue.equalsIgnoreCase("ar")) {
            headerLL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            headerLL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }


        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title", titleMain);
                intent.putExtra("dates", datesMain);
                intent.putExtra("status", status);
                intent.putExtra("packageId", packageId);
                intent.putExtra("packageTitle", packageTitle);
                intent.putExtra("packageDuration", packageDuration);
                intent.putExtra("choosepersonCheck", choosepersonCheck);
                intent.putExtra("subscribeNumber", subscribeNumber);
                intent.putExtra("softDrinkCheck", softDrinkCheck);
                intent.putExtra("peopleMain", peopleMain);
                intent.putExtra("drinkId", drinkId);
                startActivity(intent);
            }
        });

        ImageButton previous = findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2017) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(CalenderView.this, getString(R.string.event_details_string), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CalenderView.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
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
                String selectedGridDate = HwAdapter.day_string.get(position);
                ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, CalenderView.this);
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
    }


    ////////////////////////////////////////////////////////////

    private void ParcelCafe() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final PassValue task = new PassValue();
        task.setSdate(newdate);
        task.setDuration(duration);
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetHomeMenu(task).enqueue(new Callback<homemenumodel>() {
            @Override
            public void onResponse(Call<homemenumodel> call, Response<homemenumodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<homemenu> menu = response.body().getData();

                  /*  for (int i = 0; i < menu.size(); i++) {
                        maintitle = response.body().getData().get(i).getMenu().getBdescription();
                        titledate = response.body().getData().get(i).getDate();
                        images1=response.body().getData().get(i).getMenu().getBimage();
                    }*/


                    // System.out.println([i]);
                    // System.out.println(arr2[i]);

                    //  for(int i=0; i<=menu.get().getMenu().getBdescription(); i++) {
                    if (checkstatus.equals("breakfast")) {


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

                    if (checkstatus.equals("lunch")) {


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

                    if (checkstatus.equals("both")) {


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

                    //   Toast.makeText(getApplicationContext(), "Response" + maintitle + titledate, Toast.LENGTH_LONG).show();

                    HomeCollection.date_collection_arr = new ArrayList<HomeCollection>();

                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, imageboth, images1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate1, maintitle1, titlemain1, imageboth1, images2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate2, maintitle2, titlemain2, imageboth2, images3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate3, maintitle3, titlemain3, imageboth3, images4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate4, maintitle4, titlemain4, imageboth4, images5, ""));
                    cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
                    cal_month_copy = (GregorianCalendar) cal_month.clone();
                    hwAdapter = new HwAdapter(CalenderView.this, cal_month, HomeCollection.date_collection_arr);

                    tv_month = findViewById(R.id.tv_month);
                    title = findViewById(R.id.title);
                    title.setText(Title);
                    linearLayout = findViewById(R.id.ok);
                  //  tv_month.setText(android.text.format.DateFormat.format("dd-MMM-yyyy", cal_month));
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                            intent.putExtra("title", titleMain);
                            intent.putExtra("dates", datesMain);
                            intent.putExtra("status", status);
                            intent.putExtra("packageId", packageId);
                            intent.putExtra("packageTitle", packageTitle);
                            intent.putExtra("packageDuration", packageDuration);
                            intent.putExtra("choosepersonCheck", choosepersonCheck);
                            intent.putExtra("softDrinkCheck", softDrinkCheck);
                            intent.putExtra("subscribeNumber", subscribeNumber);
                            intent.putExtra("peopleMain", peopleMain);
                            intent.putExtra("drinkId", drinkId);
                            startActivity(intent);
                        }
                    });

                    ImageButton previous = findViewById(R.id.ib_prev);
                    previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2017) {
                                cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(CalenderView.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(CalenderView.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
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
                            String selectedGridDate = HwAdapter.day_string.get(position);
                            ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, CalenderView.this);
                        }

                    });
                }

            }

            @Override
            public void onFailure(Call<homemenumodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void weeks() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        final PassValue task = new PassValue();
        task.setSdate(newdate);
        task.setLang(languageValue);
        task.setDuration("10");

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetHomeMenu(task).enqueue(new Callback<homemenumodel>() {
            @Override
            public void onResponse(Call<homemenumodel> call, Response<homemenumodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<homemenu> menu = response.body().getData();


                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Title = preferences.getString("title", "");
                    duration = preferences.getString("time", "");
                    newdate = preferences.getString("dates", "");
                    checkstatus = preferences.getString("status", "");

                  /*  for (int i = 0; i < menu.size(); i++) {
                        maintitle = response.body().getData().get(i).getMenu().getBdescription();
                        titledate = response.body().getData().get(i).getDate();
                        images1=response.body().getData().get(i).getMenu().getBimage();
                    }*/


                    // System.out.println([i]);
                    // System.out.println(arr2[i]);

                    //  for(int i=0; i<=menu.get().getMenu().getBdescription(); i++) {
                    if (checkstatus.equals("breakfast")) {


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


                    }

                    if (checkstatus.equals("lunch")) {


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

                    }

                    if (checkstatus.equals("both")) {


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
                        imageboth5 = response.body().getData().get(5).getMenu().getLimage();
                        weektitlemain1 = response.body().getData().get(5).getMenu().getLdescription();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        weeksdate2 = response.body().getData().get(6).getDate();
                        weeksimage2 = response.body().getData().get(6).getMenu().getBimage();
                        imageboth6 = response.body().getData().get(6).getMenu().getLimage();
                        weektitlemain2 = response.body().getData().get(6).getMenu().getLdescription();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        weeksdate3 = response.body().getData().get(7).getDate();
                        weeksimage3 = response.body().getData().get(7).getMenu().getBimage();
                        imageboth7 = response.body().getData().get(7).getMenu().getLimage();
                        weektitlemain3 = response.body().getData().get(5).getMenu().getLdescription();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        weeksdate4 = response.body().getData().get(8).getDate();
                        weeksimage4 = response.body().getData().get(8).getMenu().getBimage();
                        imageboth8 = response.body().getData().get(8).getMenu().getLimage();
                        weektitlemain4 = response.body().getData().get(8).getMenu().getLdescription();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        weeksdate5 = response.body().getData().get(9).getDate();
                        weeksimage5 = response.body().getData().get(9).getMenu().getBimage();
                        imageboth9 = response.body().getData().get(9).getMenu().getLimage();
                        weektitlemain5 = response.body().getData().get(9).getMenu().getLdescription();

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

                        weekstitle1 = response.body().getData().get(5).getMenu().getBdescription();
                        weeksdate1 = response.body().getData().get(5).getDate();
                        weeksimage1=response.body().getData().get(5).getMenu().getBimage();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        weeksdate2 = response.body().getData().get(6).getDate();
                        weeksimage2=response.body().getData().get(6).getMenu().getBimage();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        weeksdate3 = response.body().getData().get(7).getDate();
                        weeksimage3=response.body().getData().get(7).getMenu().getBimage();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        weeksdate4 = response.body().getData().get(8).getDate();
                        weeksimage4=response.body().getData().get(8).getMenu().getBimage();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        weeksdate5 = response.body().getData().get(9).getDate();
                        weeksimage5=response.body().getData().get(9).getMenu().getBimage();




                    }*/

                    HomeCollection.date_collection_arr = new ArrayList<HomeCollection>();

                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, imageboth, images1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate1, maintitle1, titlemain1, imageboth1, images2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate2, maintitle2, titlemain2, imageboth2, images3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate3, maintitle3, titlemain3, imageboth3, images4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate4, maintitle4, titlemain4, imageboth4, images5, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate1, weekstitle1, weektitlemain1, imageboth5, weeksimage1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate2, weekstitle2, weektitlemain2, imageboth6, weeksimage2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate3, weekstitle3, weektitlemain3, imageboth7, weeksimage3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate4, weekstitle4, weektitlemain4, imageboth8, weeksimage4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate5, weekstitle5, weektitlemain5, imageboth9, weeksimage5, ""));
                    cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
                    cal_month_copy = (GregorianCalendar) cal_month.clone();
                    hwAdapter = new HwAdapter(CalenderView.this, cal_month, HomeCollection.date_collection_arr);

                    tv_month = findViewById(R.id.tv_month);
                    title = findViewById(R.id.title);
                    title.setText(Title);
                    linearLayout = findViewById(R.id.ok);
                    tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                            intent.putExtra("title", titleMain);
                            intent.putExtra("dates", datesMain);
                            intent.putExtra("status", status);
                            intent.putExtra("packageId", packageId);
                            intent.putExtra("packageTitle", packageTitle);
                            intent.putExtra("packageDuration", packageDuration);
                            intent.putExtra("choosepersonCheck", choosepersonCheck);
                            intent.putExtra("softDrinkCheck", softDrinkCheck);
                            intent.putExtra("subscribeNumber", subscribeNumber);
                            intent.putExtra("peopleMain", peopleMain);
                            intent.putExtra("drinkId", drinkId);
                            startActivity(intent);
                        }
                    });

                    ImageButton previous = findViewById(R.id.ib_prev);
                    previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2017) {
                                //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(CalenderView.this, getString(R.string.event_details_string), Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(CalenderView.this, getString(R.string.event_details_string), Toast.LENGTH_SHORT).show();
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
                            String selectedGridDate = HwAdapter.day_string.get(position);
                            ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, CalenderView.this);
                        }

                    });
                }

            }

            @Override
            public void onFailure(Call<homemenumodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Months() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final PassValue task = new PassValue();
        task.setSdate(newdate);
        task.setDuration("60");
        task.setLang(languageValue);

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetHomeMenu(task).enqueue(new Callback<homemenumodel>() {
            @Override
            public void onResponse(Call<homemenumodel> call, Response<homemenumodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<homemenu> menu = response.body().getData();


                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Title = preferences.getString("title", "");
                    duration = preferences.getString("time", "");
                    newdate = preferences.getString("dates", "");
                    checkstatus = preferences.getString("status", "");

                  /*  for (int i = 0; i < menu.size(); i++) {
                        maintitle = response.body().getData().get(i).getMenu().getBdescription();
                        titledate = response.body().getData().get(i).getDate();
                        images1=response.body().getData().get(i).getMenu().getBimage();
                    }*/


                    // System.out.println([i]);
                    // System.out.println(arr2[i]);

                    //  for(int i=0; i<=menu.get().getMenu().getBdescription(); i++) {
                    if (checkstatus.equals("breakfast")) {


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

                        monthtitle31 = response.body().getData().get(40).getMenu().getBdescription();
                        monthdate31 = response.body().getData().get(40).getDate();
                        monthimage31 = response.body().getData().get(40).getMenu().getBimage();

                        monthtitle32 = response.body().getData().get(41).getMenu().getBdescription();
                        monthdate32 = response.body().getData().get(41).getDate();
                        monthimage32 = response.body().getData().get(41).getMenu().getBimage();

                        monthtitle33 = response.body().getData().get(42).getMenu().getBdescription();
                        monthdate33 = response.body().getData().get(42).getDate();
                        monthimage33 = response.body().getData().get(42).getMenu().getBimage();

                        monthtitle34 = response.body().getData().get(43).getMenu().getBdescription();
                        monthdate34 = response.body().getData().get(43).getDate();
                        monthimage34 = response.body().getData().get(43).getMenu().getBimage();

                        monthtitle35 = response.body().getData().get(44).getMenu().getBdescription();
                        monthdate35 = response.body().getData().get(44).getDate();
                        monthimage35 = response.body().getData().get(44).getMenu().getBimage();

                        monthtitle36 = response.body().getData().get(45).getMenu().getBdescription();
                        monthdate36 = response.body().getData().get(45).getDate();
                        monthimage36 = response.body().getData().get(45).getMenu().getBimage();

                        monthtitle37 = response.body().getData().get(46).getMenu().getBdescription();
                        monthdate37 = response.body().getData().get(46).getDate();
                        monthimage37 = response.body().getData().get(46).getMenu().getBimage();

                        monthtitle38 = response.body().getData().get(47).getMenu().getBdescription();
                        monthdate38 = response.body().getData().get(47).getDate();
                        monthimage38 = response.body().getData().get(47).getMenu().getBimage();

                        monthtitle39 = response.body().getData().get(48).getMenu().getBdescription();
                        monthdate39 = response.body().getData().get(48).getDate();
                        monthimage39 = response.body().getData().get(48).getMenu().getBimage();

                        monthtitle40 = response.body().getData().get(49).getMenu().getBdescription();
                        monthdate40 = response.body().getData().get(49).getDate();
                        monthimage40 = response.body().getData().get(49).getMenu().getBimage();

                        monthtitle41 = response.body().getData().get(50).getMenu().getBdescription();
                        monthdate41 = response.body().getData().get(50).getDate();
                        monthimage41 = response.body().getData().get(50).getMenu().getBimage();

                        monthtitle42 = response.body().getData().get(51).getMenu().getBdescription();
                        monthdate42 = response.body().getData().get(51).getDate();
                        monthimage42 = response.body().getData().get(51).getMenu().getBimage();

                        monthtitle43 = response.body().getData().get(52).getMenu().getBdescription();
                        monthdate43 = response.body().getData().get(52).getDate();
                        monthimage43 = response.body().getData().get(52).getMenu().getBimage();

                        monthtitle44 = response.body().getData().get(53).getMenu().getBdescription();
                        monthdate44 = response.body().getData().get(53).getDate();
                        monthimage44 = response.body().getData().get(53).getMenu().getBimage();

                        monthtitle45 = response.body().getData().get(54).getMenu().getBdescription();
                        monthdate45 = response.body().getData().get(54).getDate();
                        monthimage45 = response.body().getData().get(54).getMenu().getBimage();

                        monthtitle46 = response.body().getData().get(55).getMenu().getBdescription();
                        monthdate46 = response.body().getData().get(55).getDate();
                        monthimage46 = response.body().getData().get(55).getMenu().getBimage();

                        monthtitle47 = response.body().getData().get(56).getMenu().getBdescription();
                        monthdate47 = response.body().getData().get(56).getDate();
                        monthimage47 = response.body().getData().get(56).getMenu().getBimage();

                        monthtitle48 = response.body().getData().get(57).getMenu().getBdescription();
                        monthdate48 = response.body().getData().get(57).getDate();
                        monthimage48 = response.body().getData().get(57).getMenu().getBimage();

                        monthtitle49 = response.body().getData().get(58).getMenu().getBdescription();
                        monthdate49 = response.body().getData().get(58).getDate();
                        monthimage49 = response.body().getData().get(58).getMenu().getBimage();

                        monthtitle50 = response.body().getData().get(59).getMenu().getBdescription();
                        monthdate50 = response.body().getData().get(59).getDate();
                        monthimage50 = response.body().getData().get(59).getMenu().getBimage();


                    }

                    if (checkstatus.equals("lunch")) {


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

                        monthtitle30 = response.body().getData().get(39).getMenu().getLdescription();
                        monthdate30 = response.body().getData().get(39).getDate();
                        monthimage30 = response.body().getData().get(39).getMenu().getLimage();

                        monthtitle31 = response.body().getData().get(40).getMenu().getLdescription();
                        monthdate31 = response.body().getData().get(40).getDate();
                        monthimage31 = response.body().getData().get(40).getMenu().getLimage();

                        monthtitle32 = response.body().getData().get(41).getMenu().getLdescription();
                        monthdate32 = response.body().getData().get(41).getDate();
                        monthimage32 = response.body().getData().get(41).getMenu().getLimage();

                        monthtitle33 = response.body().getData().get(42).getMenu().getLdescription();
                        monthdate33 = response.body().getData().get(42).getDate();
                        monthimage33 = response.body().getData().get(42).getMenu().getLimage();

                        monthtitle34 = response.body().getData().get(43).getMenu().getLdescription();
                        monthdate34 = response.body().getData().get(43).getDate();
                        monthimage34 = response.body().getData().get(43).getMenu().getLimage();

                        monthtitle35 = response.body().getData().get(44).getMenu().getLdescription();
                        monthdate35 = response.body().getData().get(44).getDate();
                        monthimage35 = response.body().getData().get(44).getMenu().getLimage();

                        monthtitle36 = response.body().getData().get(45).getMenu().getLdescription();
                        monthdate36 = response.body().getData().get(45).getDate();
                        monthimage36 = response.body().getData().get(45).getMenu().getLimage();

                        monthtitle37 = response.body().getData().get(46).getMenu().getLdescription();
                        monthdate37 = response.body().getData().get(46).getDate();
                        monthimage37 = response.body().getData().get(46).getMenu().getLimage();

                        monthtitle38 = response.body().getData().get(47).getMenu().getLdescription();
                        monthdate38 = response.body().getData().get(47).getDate();
                        monthimage38 = response.body().getData().get(47).getMenu().getLimage();

                        monthtitle39 = response.body().getData().get(48).getMenu().getLdescription();
                        monthdate39 = response.body().getData().get(48).getDate();
                        monthimage39 = response.body().getData().get(48).getMenu().getLimage();

                        monthtitle40 = response.body().getData().get(49).getMenu().getLdescription();
                        monthdate40 = response.body().getData().get(49).getDate();
                        monthimage40 = response.body().getData().get(49).getMenu().getLimage();

                        monthtitle41 = response.body().getData().get(50).getMenu().getLdescription();
                        monthdate41 = response.body().getData().get(50).getDate();
                        monthimage41 = response.body().getData().get(50).getMenu().getLimage();

                        monthtitle42 = response.body().getData().get(51).getMenu().getLdescription();
                        monthdate42 = response.body().getData().get(51).getDate();
                        monthimage42 = response.body().getData().get(51).getMenu().getLimage();

                        monthtitle43 = response.body().getData().get(52).getMenu().getLdescription();
                        monthdate43 = response.body().getData().get(52).getDate();
                        monthimage43 = response.body().getData().get(52).getMenu().getLimage();

                        monthtitle44 = response.body().getData().get(53).getMenu().getLdescription();
                        monthdate44 = response.body().getData().get(53).getDate();
                        monthimage44 = response.body().getData().get(53).getMenu().getLimage();

                        monthtitle45 = response.body().getData().get(54).getMenu().getLdescription();
                        monthdate45 = response.body().getData().get(54).getDate();
                        monthimage45 = response.body().getData().get(54).getMenu().getLimage();

                        monthtitle46 = response.body().getData().get(55).getMenu().getLdescription();
                        monthdate46 = response.body().getData().get(55).getDate();
                        monthimage46 = response.body().getData().get(55).getMenu().getLimage();

                        monthtitle47 = response.body().getData().get(56).getMenu().getLdescription();
                        monthdate47 = response.body().getData().get(56).getDate();
                        monthimage47 = response.body().getData().get(56).getMenu().getLimage();

                        monthtitle48 = response.body().getData().get(57).getMenu().getLdescription();
                        monthdate48 = response.body().getData().get(57).getDate();
                        monthimage48 = response.body().getData().get(57).getMenu().getLimage();

                        monthtitle49 = response.body().getData().get(58).getMenu().getLdescription();
                        monthdate49 = response.body().getData().get(58).getDate();
                        monthimage49 = response.body().getData().get(58).getMenu().getLimage();

                        monthtitle50 = response.body().getData().get(59).getMenu().getLdescription();
                        monthdate50 = response.body().getData().get(59).getDate();
                        monthimage50 = response.body().getData().get(59).getMenu().getLimage();


                    }

                    if (checkstatus.equals("both")) {


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
                        imageboth5 = response.body().getData().get(5).getMenu().getLimage();
                        weektitlemain1 = response.body().getData().get(5).getMenu().getLdescription();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        weeksdate2 = response.body().getData().get(6).getDate();
                        weeksimage2 = response.body().getData().get(6).getMenu().getBimage();
                        imageboth6 = response.body().getData().get(6).getMenu().getLimage();
                        weektitlemain2 = response.body().getData().get(6).getMenu().getLdescription();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        weeksdate3 = response.body().getData().get(7).getDate();
                        weeksimage3 = response.body().getData().get(7).getMenu().getBimage();
                        imageboth7 = response.body().getData().get(7).getMenu().getLimage();
                        weektitlemain3 = response.body().getData().get(7).getMenu().getLdescription();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        weeksdate4 = response.body().getData().get(8).getDate();
                        weeksimage4 = response.body().getData().get(8).getMenu().getBimage();
                        imageboth8 = response.body().getData().get(8).getMenu().getLimage();
                        weektitlemain4 = response.body().getData().get(8).getMenu().getLdescription();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        weeksdate5 = response.body().getData().get(9).getDate();
                        weeksimage5 = response.body().getData().get(9).getMenu().getBimage();
                        imageboth9 = response.body().getData().get(9).getMenu().getLimage();
                        weektitlemain5 = response.body().getData().get(9).getMenu().getLdescription();

                        monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                        monthdate1 = response.body().getData().get(10).getDate();
                        monthimage1 = response.body().getData().get(10).getMenu().getBimage();
                        imageboth10 = response.body().getData().get(10).getMenu().getLimage();
                        monthtitlemain1 = response.body().getData().get(10).getMenu().getLdescription();


                        monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                        monthdate2 = response.body().getData().get(11).getDate();
                        monthimage2 = response.body().getData().get(11).getMenu().getBimage();
                        imageboth11 = response.body().getData().get(11).getMenu().getLimage();
                        monthtitlemain2 = response.body().getData().get(11).getMenu().getLdescription();

                        monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                        monthdate3 = response.body().getData().get(12).getDate();
                        monthimage3 = response.body().getData().get(12).getMenu().getBimage();
                        imageboth12 = response.body().getData().get(12).getMenu().getLimage();
                        monthtitlemain3 = response.body().getData().get(12).getMenu().getLdescription();

                        monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                        monthdate4 = response.body().getData().get(13).getDate();
                        monthimage4 = response.body().getData().get(13).getMenu().getBimage();
                        imageboth13 = response.body().getData().get(13).getMenu().getLimage();
                        monthtitlemain4 = response.body().getData().get(13).getMenu().getLdescription();

                        monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                        monthdate5 = response.body().getData().get(14).getDate();
                        monthimage5 = response.body().getData().get(14).getMenu().getBimage();
                        imageboth14 = response.body().getData().get(14).getMenu().getLimage();
                        monthtitlemain5 = response.body().getData().get(14).getMenu().getLdescription();

                        monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                        monthdate6 = response.body().getData().get(15).getDate();
                        monthimage6 = response.body().getData().get(15).getMenu().getBimage();
                        imageboth15 = response.body().getData().get(15).getMenu().getLimage();
                        monthtitlemain6 = response.body().getData().get(15).getMenu().getLdescription();


                        monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                        monthdate7 = response.body().getData().get(16).getDate();
                        monthimage7 = response.body().getData().get(16).getMenu().getBimage();
                        imageboth16 = response.body().getData().get(16).getMenu().getLimage();
                        monthtitlemain7 = response.body().getData().get(16).getMenu().getLdescription();

                        monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                        monthdate8 = response.body().getData().get(17).getDate();
                        monthimage8 = response.body().getData().get(17).getMenu().getBimage();
                        imageboth17 = response.body().getData().get(17).getMenu().getLimage();
                        monthtitlemain8 = response.body().getData().get(17).getMenu().getLdescription();

                        monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                        monthdate9 = response.body().getData().get(18).getDate();
                        monthimage9 = response.body().getData().get(18).getMenu().getBimage();
                        imageboth18 = response.body().getData().get(18).getMenu().getLimage();
                        monthtitlemain9 = response.body().getData().get(18).getMenu().getLdescription();

                        monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                        monthdate10 = response.body().getData().get(19).getDate();
                        monthimage10 = response.body().getData().get(19).getMenu().getBimage();
                        imageboth19 = response.body().getData().get(19).getMenu().getLimage();
                        monthtitlemain10 = response.body().getData().get(19).getMenu().getLdescription();

                        monthtitle11 = response.body().getData().get(20).getMenu().getBdescription();
                        monthdate11 = response.body().getData().get(20).getDate();
                        monthimage11 = response.body().getData().get(20).getMenu().getBimage();
                        imageboth20 = response.body().getData().get(20).getMenu().getLimage();
                        monthtitlemain11 = response.body().getData().get(20).getMenu().getLdescription();


                        monthtitle12 = response.body().getData().get(21).getMenu().getBdescription();
                        monthdate12 = response.body().getData().get(21).getDate();
                        monthimage12 = response.body().getData().get(21).getMenu().getBimage();
                        imageboth21 = response.body().getData().get(21).getMenu().getLimage();
                        monthtitlemain12 = response.body().getData().get(21).getMenu().getLdescription();

                        monthtitle13 = response.body().getData().get(22).getMenu().getBdescription();
                        monthdate13 = response.body().getData().get(22).getDate();
                        monthimage13 = response.body().getData().get(22).getMenu().getBimage();
                        imageboth22 = response.body().getData().get(22).getMenu().getLimage();
                        monthtitlemain13 = response.body().getData().get(22).getMenu().getLdescription();

                        monthtitle14 = response.body().getData().get(23).getMenu().getBdescription();
                        monthdate14 = response.body().getData().get(23).getDate();
                        monthimage14 = response.body().getData().get(23).getMenu().getBimage();
                        imageboth23 = response.body().getData().get(23).getMenu().getLimage();
                        monthtitlemain14 = response.body().getData().get(23).getMenu().getLdescription();


                        monthtitle15 = response.body().getData().get(24).getMenu().getBdescription();
                        monthdate15 = response.body().getData().get(24).getDate();
                        monthimage15 = response.body().getData().get(24).getMenu().getBimage();
                        imageboth24 = response.body().getData().get(24).getMenu().getLimage();
                        monthtitlemain15 = response.body().getData().get(24).getMenu().getLdescription();

                        monthtitle16 = response.body().getData().get(25).getMenu().getBdescription();
                        monthdate16 = response.body().getData().get(25).getDate();
                        monthimage16 = response.body().getData().get(25).getMenu().getBimage();
                        imageboth25 = response.body().getData().get(25).getMenu().getLimage();
                        monthtitlemain16 = response.body().getData().get(25).getMenu().getLdescription();


                        monthtitle17 = response.body().getData().get(26).getMenu().getBdescription();
                        monthdate17 = response.body().getData().get(26).getDate();
                        monthimage17 = response.body().getData().get(26).getMenu().getBimage();
                        imageboth26 = response.body().getData().get(26).getMenu().getLimage();
                        monthtitlemain17 = response.body().getData().get(26).getMenu().getLdescription();

                        monthtitle18 = response.body().getData().get(27).getMenu().getBdescription();
                        monthdate18 = response.body().getData().get(27).getDate();
                        monthimage18 = response.body().getData().get(27).getMenu().getBimage();
                        imageboth27 = response.body().getData().get(27).getMenu().getLimage();
                        monthtitlemain18 = response.body().getData().get(27).getMenu().getLdescription();

                        monthtitle19 = response.body().getData().get(28).getMenu().getBdescription();
                        monthdate19 = response.body().getData().get(28).getDate();
                        monthimage19 = response.body().getData().get(28).getMenu().getBimage();
                        imageboth28 = response.body().getData().get(28).getMenu().getLimage();
                        monthtitlemain19 = response.body().getData().get(28).getMenu().getLdescription();

                        monthtitle20 = response.body().getData().get(29).getMenu().getBdescription();
                        monthdate20 = response.body().getData().get(29).getDate();
                        monthimage20 = response.body().getData().get(29).getMenu().getBimage();
                        imageboth29 = response.body().getData().get(29).getMenu().getLimage();
                        monthtitlemain20 = response.body().getData().get(29).getMenu().getLdescription();

                        monthtitle21 = response.body().getData().get(30).getMenu().getBdescription();
                        monthdate21 = response.body().getData().get(30).getDate();
                        monthimage21 = response.body().getData().get(30).getMenu().getBimage();
                        imageboth30 = response.body().getData().get(30).getMenu().getLimage();
                        monthtitlemain21 = response.body().getData().get(30).getMenu().getLdescription();


                        monthtitle22 = response.body().getData().get(31).getMenu().getBdescription();
                        monthdate22 = response.body().getData().get(31).getDate();
                        monthimage22 = response.body().getData().get(31).getMenu().getBimage();
                        imageboth31 = response.body().getData().get(31).getMenu().getLimage();
                        monthtitlemain22 = response.body().getData().get(31).getMenu().getLdescription();

                        monthtitle23 = response.body().getData().get(32).getMenu().getBdescription();
                        monthdate23 = response.body().getData().get(32).getDate();
                        monthimage23 = response.body().getData().get(32).getMenu().getBimage();
                        imageboth32 = response.body().getData().get(32).getMenu().getLimage();
                        monthtitlemain23 = response.body().getData().get(32).getMenu().getLdescription();

                        monthtitle24 = response.body().getData().get(33).getMenu().getBdescription();
                        monthdate24 = response.body().getData().get(33).getDate();
                        monthimage24 = response.body().getData().get(33).getMenu().getBimage();
                        imageboth33 = response.body().getData().get(33).getMenu().getLimage();
                        monthtitlemain24 = response.body().getData().get(33).getMenu().getLdescription();

                        monthtitle25 = response.body().getData().get(34).getMenu().getBdescription();
                        monthdate25 = response.body().getData().get(34).getDate();
                        monthimage25 = response.body().getData().get(34).getMenu().getBimage();
                        imageboth34 = response.body().getData().get(34).getMenu().getLimage();
                        monthtitlemain25 = response.body().getData().get(34).getMenu().getLdescription();

                        monthtitle26 = response.body().getData().get(35).getMenu().getBdescription();
                        monthdate26 = response.body().getData().get(35).getDate();
                        monthimage26 = response.body().getData().get(35).getMenu().getBimage();
                        imageboth35 = response.body().getData().get(35).getMenu().getLimage();
                        monthtitlemain26 = response.body().getData().get(35).getMenu().getLdescription();


                        monthtitle27 = response.body().getData().get(36).getMenu().getBdescription();
                        monthdate27 = response.body().getData().get(36).getDate();
                        monthimage27 = response.body().getData().get(36).getMenu().getBimage();
                        imageboth36 = response.body().getData().get(36).getMenu().getLimage();
                        monthtitlemain27 = response.body().getData().get(36).getMenu().getLdescription();

                        monthtitle28 = response.body().getData().get(37).getMenu().getBdescription();
                        monthdate28 = response.body().getData().get(37).getDate();
                        monthimage8 = response.body().getData().get(37).getMenu().getBimage();
                        imageboth37 = response.body().getData().get(37).getMenu().getLimage();
                        monthtitlemain28 = response.body().getData().get(37).getMenu().getLdescription();

                        monthtitle29 = response.body().getData().get(38).getMenu().getBdescription();
                        monthdate29 = response.body().getData().get(38).getDate();
                        monthimage29 = response.body().getData().get(38).getMenu().getBimage();
                        imageboth38 = response.body().getData().get(38).getMenu().getLimage();
                        monthtitlemain29 = response.body().getData().get(38).getMenu().getLdescription();

                        monthtitle30 = response.body().getData().get(39).getMenu().getBdescription();
                        monthdate30 = response.body().getData().get(39).getDate();
                        monthimage30 = response.body().getData().get(39).getMenu().getBimage();
                        imageboth39 = response.body().getData().get(39).getMenu().getLimage();
                        monthtitlemain30 = response.body().getData().get(39).getMenu().getLdescription();


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

                        weekstitle1 = response.body().getData().get(5).getMenu().getBdescription();
                        weeksdate1 = response.body().getData().get(5).getDate();
                        weeksimage1=response.body().getData().get(5).getMenu().getBimage();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        weeksdate2 = response.body().getData().get(6).getDate();
                        weeksimage2=response.body().getData().get(6).getMenu().getBimage();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        weeksdate3 = response.body().getData().get(7).getDate();
                        weeksimage3=response.body().getData().get(7).getMenu().getBimage();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        weeksdate4 = response.body().getData().get(8).getDate();
                        weeksimage4=response.body().getData().get(8).getMenu().getBimage();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        weeksdate5 = response.body().getData().get(9).getDate();
                        weeksimage5=response.body().getData().get(9).getMenu().getBimage();

                        monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                        monthdate1 = response.body().getData().get(10).getDate();
                        monthimage1=response.body().getData().get(10).getMenu().getBimage();


                        monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                        monthdate2 = response.body().getData().get(11).getDate();
                        monthimage2=response.body().getData().get(11).getMenu().getBimage();

                        monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                        monthdate3 = response.body().getData().get(12).getDate();
                        monthimage3=response.body().getData().get(12).getMenu().getBimage();

                        monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                        monthdate4 = response.body().getData().get(13).getDate();
                        monthimage4=response.body().getData().get(13).getMenu().getBimage();

                        monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                        monthdate5 = response.body().getData().get(14).getDate();
                        monthimage5=response.body().getData().get(14).getMenu().getBimage();

                        monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                        monthdate6 = response.body().getData().get(15).getDate();
                        monthimage6=response.body().getData().get(16).getMenu().getBimage();


                        monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                        monthdate7 = response.body().getData().get(16).getDate();
                        monthimage7=response.body().getData().get(16).getMenu().getBimage();

                        monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                        monthdate8 = response.body().getData().get(17).getDate();
                        monthimage8=response.body().getData().get(17).getMenu().getBimage();

                        monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                        monthdate9 = response.body().getData().get(18).getDate();
                        monthimage9=response.body().getData().get(18).getMenu().getBimage();

                        monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                        monthdate10 = response.body().getData().get(19).getDate();
                        monthimage10=response.body().getData().get(19).getMenu().getBimage();

                        maintitle = response.body().getData().get(0).getMenu().getBdescription();
                        monthdate11 = response.body().getData().get(20).getDate();
                        images1=response.body().getData().get(0).getMenu().getBimage();


                        maintitle1 = response.body().getData().get(1).getMenu().getBdescription();
                        monthdate12 = response.body().getData().get(21).getDate();
                        images2=response.body().getData().get(1).getMenu().getBimage();

                        maintitle2 = response.body().getData().get(2).getMenu().getBdescription();
                        monthdate13 = response.body().getData().get(22).getDate();
                        images3=response.body().getData().get(2).getMenu().getBimage();

                        maintitle3 = response.body().getData().get(3).getMenu().getBdescription();
                        monthdate14 = response.body().getData().get(23).getDate();
                        images4=response.body().getData().get(3).getMenu().getBimage();


                        maintitle4 = response.body().getData().get(4).getMenu().getBdescription();
                        monthdate15 = response.body().getData().get(24).getDate();
                        images5=response.body().getData().get(4).getMenu().getBimage();

                        weekstitle1 = response.body().getData().get(5).getMenu().getBdescription();
                        monthdate16 = response.body().getData().get(25).getDate();
                        weeksimage1=response.body().getData().get(5).getMenu().getBimage();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        monthdate17 = response.body().getData().get(26).getDate();
                        weeksimage2=response.body().getData().get(6).getMenu().getBimage();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        monthdate18 = response.body().getData().get(27).getDate();
                        weeksimage3=response.body().getData().get(7).getMenu().getBimage();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        monthdate19 = response.body().getData().get(28).getDate();
                        weeksimage4=response.body().getData().get(8).getMenu().getBimage();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        monthdate20 = response.body().getData().get(29).getDate();
                        weeksimage5=response.body().getData().get(9).getMenu().getBimage();

                        monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                        monthdate1 = response.body().getData().get(10).getDate();
                        monthimage1=response.body().getData().get(10).getMenu().getBimage();


                        monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                        monthdate2 = response.body().getData().get(11).getDate();
                        monthimage2=response.body().getData().get(11).getMenu().getBimage();

                        monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                        monthdate3 = response.body().getData().get(12).getDate();
                        monthimage3=response.body().getData().get(12).getMenu().getBimage();

                        monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                        monthdate4 = response.body().getData().get(13).getDate();
                        monthimage4=response.body().getData().get(13).getMenu().getBimage();

                        monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                        monthdate5 = response.body().getData().get(14).getDate();
                        monthimage5=response.body().getData().get(14).getMenu().getBimage();

                        monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                        monthdate6 = response.body().getData().get(15).getDate();
                        monthimage6=response.body().getData().get(16).getMenu().getBimage();


                        monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                        monthdate7 = response.body().getData().get(16).getDate();
                        monthimage7=response.body().getData().get(16).getMenu().getBimage();

                        monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                        monthdate8 = response.body().getData().get(17).getDate();
                        monthimage8=response.body().getData().get(17).getMenu().getBimage();

                        monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                        monthdate9 = response.body().getData().get(18).getDate();
                        monthimage9=response.body().getData().get(18).getMenu().getBimage();

                        monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                        monthdate10 = response.body().getData().get(19).getDate();
                        monthimage10=response.body().getData().get(19).getMenu().getBimage();
                        maintitle = response.body().getData().get(0).getMenu().getBdescription();
                        monthdate11 = response.body().getData().get(20).getDate();
                        images1=response.body().getData().get(0).getMenu().getBimage();


                        maintitle1 = response.body().getData().get(1).getMenu().getBdescription();
                        monthdate12 = response.body().getData().get(21).getDate();
                        images2=response.body().getData().get(1).getMenu().getBimage();

                        maintitle2 = response.body().getData().get(2).getMenu().getBdescription();
                        monthdate13 = response.body().getData().get(22).getDate();
                        images3=response.body().getData().get(2).getMenu().getBimage();

                        maintitle3 = response.body().getData().get(3).getMenu().getBdescription();
                        monthdate14 = response.body().getData().get(23).getDate();
                        images4=response.body().getData().get(3).getMenu().getBimage();


                        maintitle4 = response.body().getData().get(4).getMenu().getBdescription();
                        monthdate15 = response.body().getData().get(24).getDate();
                        images5=response.body().getData().get(4).getMenu().getBimage();

                        weekstitle1 = response.body().getData().get(5).getMenu().getBdescription();
                        monthdate16 = response.body().getData().get(25).getDate();
                        weeksimage1=response.body().getData().get(5).getMenu().getBimage();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        monthdate17 = response.body().getData().get(26).getDate();
                        weeksimage2=response.body().getData().get(6).getMenu().getBimage();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        monthdate18 = response.body().getData().get(27).getDate();
                        weeksimage3=response.body().getData().get(7).getMenu().getBimage();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        monthdate19 = response.body().getData().get(28).getDate();
                        weeksimage4=response.body().getData().get(8).getMenu().getBimage();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        monthdate20 = response.body().getData().get(29).getDate();
                        weeksimage5=response.body().getData().get(9).getMenu().getBimage();

                        monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                        monthdate21 = response.body().getData().get(30).getDate();
                        monthimage1=response.body().getData().get(10).getMenu().getBimage();


                        monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                        monthdate22 = response.body().getData().get(31).getDate();
                        monthimage2=response.body().getData().get(11).getMenu().getBimage();

                        monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                        monthdate23 = response.body().getData().get(32).getDate();
                        monthimage3=response.body().getData().get(12).getMenu().getBimage();

                        monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                        monthdate24 = response.body().getData().get(33).getDate();
                        monthimage4=response.body().getData().get(13).getMenu().getBimage();

                        monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                        monthdate25 = response.body().getData().get(34).getDate();
                        monthimage5=response.body().getData().get(14).getMenu().getBimage();

                        monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                        monthdate26 = response.body().getData().get(35).getDate();
                        monthimage6=response.body().getData().get(16).getMenu().getBimage();


                        monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                        monthdate27 = response.body().getData().get(36).getDate();
                        monthimage7=response.body().getData().get(16).getMenu().getBimage();

                        monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                        monthdate28 = response.body().getData().get(37).getDate();
                        monthimage8=response.body().getData().get(17).getMenu().getBimage();

                        monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                        monthdate29 = response.body().getData().get(38).getDate();
                        monthimage9=response.body().getData().get(18).getMenu().getBimage();

                        monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                        monthdate30 = response.body().getData().get(29).getDate();
                        monthimage10=response.body().getData().get(19).getMenu().getBimage();



                    }*/

                    HomeCollection.date_collection_arr = new ArrayList<HomeCollection>();

                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, imageboth, images1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate1, maintitle1, titlemain1, imageboth1, images2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate2, maintitle2, titlemain2, imageboth2, images3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate3, maintitle3, titlemain3, imageboth3, images4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate4, maintitle4, titlemain4, imageboth4, images5, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate1, weekstitle1, weektitlemain1, imageboth5, weeksimage1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate2, weekstitle2, weektitlemain2, imageboth6, weeksimage2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate3, weekstitle3, weektitlemain3, imageboth7, weeksimage3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate4, weekstitle4, weektitlemain4, imageboth8, weeksimage4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate5, weekstitle5, weektitlemain5, imageboth9, weeksimage5, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate1, monthtitle1, monthtitlemain1, imageboth10, monthimage1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate2, monthtitle2, monthtitlemain2, imageboth11, monthimage2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate3, monthtitle3, monthtitlemain3, imageboth12, monthimage3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate4, monthtitle4, monthtitlemain4, imageboth13, monthimage4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate5, monthtitle5, monthtitlemain5, imageboth14, monthimage5, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate6, monthtitle6, monthtitlemain6, imageboth15, monthimage6, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate7, monthtitle7, monthtitlemain7, imageboth16, monthimage7, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate8, monthtitle8, monthtitlemain8, imageboth17, monthimage8, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate9, monthtitle9, monthtitlemain9, imageboth18, monthimage9, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate10, monthtitle10, monthtitlemain10, imageboth19, monthimage10, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate11, monthtitle11, monthtitlemain11, imageboth20, monthimage11, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate12, monthtitle12, monthtitlemain12, imageboth22, monthimage12, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate13, monthtitle13, monthtitlemain13, imageboth23, monthimage13, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate14, monthtitle14, monthtitlemain14, imageboth24, monthimage14, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate15, monthtitle15, monthtitlemain15, imageboth25, monthimage15, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate16, monthtitle16, monthtitlemain16, imageboth26, monthimage16, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate17, monthtitle17, monthtitlemain17, imageboth27, monthimage17, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate18, monthtitle18, monthtitlemain18, imageboth28, monthimage18, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate19, monthtitle19, monthtitlemain19, imageboth29, monthimage19, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate20, monthtitle20, monthtitlemain20, imageboth30, monthimage20, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate21, monthtitle21, monthtitlemain21, imageboth31, monthimage21, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate22, monthtitle22, monthtitlemain22, imageboth32, monthimage22, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate23, monthtitle23, monthtitlemain23, imageboth33, monthimage23, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate24, monthtitle24, monthtitlemain24, imageboth34, monthimage24, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate25, monthtitle25, monthtitlemain25, imageboth35, monthimage25, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate26, monthtitle26, monthtitlemain26, imageboth36, monthimage26, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate27, monthtitle27, monthtitlemain27, imageboth38, monthimage27, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate28, monthtitle28, monthtitlemain28, imageboth39, monthimage28, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate29, monthtitle29, monthtitlemain29, imageboth40, monthimage29, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate30, monthtitle30, monthtitlemain30, monthtitlemain10, monthimage30, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate31, monthtitle31, monthtitlemain30, monthtitlemain10, monthimage31, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate32, monthtitle32, monthtitlemain30, monthtitlemain10, monthimage32, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate33, monthtitle33, monthtitlemain30, monthtitlemain10, monthimage33, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate34, monthtitle34, monthtitlemain30, monthtitlemain10, monthimage34, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate35, monthtitle35, monthtitlemain30, monthtitlemain10, monthimage35, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate36, monthtitle36, monthtitlemain30, monthtitlemain10, monthimage36, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate37, monthtitle37, monthtitlemain30, monthtitlemain10, monthimage37, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate38, monthtitle38, monthtitlemain30, monthtitlemain10, monthimage38, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate39, monthtitle39, monthtitlemain30, monthtitlemain10, monthimage39, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate40, monthtitle40, monthtitlemain30, monthtitlemain10, monthimage40, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate41, monthtitle41, monthtitlemain30, monthtitlemain10, monthimage41, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate42, monthtitle42, monthtitlemain30, monthtitlemain10, monthimage42, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate43, monthtitle43, monthtitlemain30, monthtitlemain10, monthimage43, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate44, monthtitle44, monthtitlemain30, monthtitlemain10, monthimage44, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate45, monthtitle45, monthtitlemain30, monthtitlemain10, monthimage45, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate46, monthtitle46, monthtitlemain30, monthtitlemain10, monthimage46, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate47, monthtitle47, monthtitlemain30, monthtitlemain10, monthimage47, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate48, monthtitle48, monthtitlemain30, monthtitlemain10, monthimage48, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate49, monthtitle49, monthtitlemain30, monthtitlemain10, monthimage49, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate50, monthtitle50, monthtitlemain30, monthtitlemain10, monthimage50, ""));
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    //
                    // HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate31, monthtitle10, monthtitlemain30, monthtitlemain10,monthimage10));


                    cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
                    cal_month_copy = (GregorianCalendar) cal_month.clone();
                    hwAdapter = new HwAdapter(CalenderView.this, cal_month, HomeCollection.date_collection_arr);

                    tv_month = findViewById(R.id.tv_month);
                    title = findViewById(R.id.title);
                    title.setText(Title);
                    linearLayout = findViewById(R.id.ok);
                    tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                            intent.putExtra("title", titleMain);
                            intent.putExtra("dates", datesMain);
                            intent.putExtra("status", status);
                            intent.putExtra("packageId", packageId);
                            intent.putExtra("packageTitle", packageTitle);
                            intent.putExtra("packageDuration", packageDuration);
                            intent.putExtra("choosepersonCheck", choosepersonCheck);
                            intent.putExtra("subscribeNumber", subscribeNumber);
                            intent.putExtra("softDrinkCheck", softDrinkCheck);
                            intent.putExtra("peopleMain", peopleMain);
                            intent.putExtra("drinkId", drinkId);
                            startActivity(intent);
                        }
                    });

                    ImageButton previous = findViewById(R.id.ib_prev);
                    previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2017) {
                                //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(CalenderView.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(CalenderView.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
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
                            String selectedGridDate = HwAdapter.day_string.get(position);
                            ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, CalenderView.this);
                        }

                    });
                }

            }

            @Override
            public void onFailure(Call<homemenumodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void Monthlys() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage(getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final PassValue task = new PassValue();
        task.setSdate(newdate);
        task.setDuration("20");
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetHomeMenu(task).enqueue(new Callback<homemenumodel>() {
            @Override
            public void onResponse(Call<homemenumodel> call, Response<homemenumodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    List<homemenu> menu = response.body().getData();


                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Title = preferences.getString("title", "");
                    duration = preferences.getString("time", "");
                    newdate = preferences.getString("dates", "");
                    checkstatus = preferences.getString("status", "");

                  /*  for (int i = 0; i < menu.size(); i++) {
                        maintitle = response.body().getData().get(i).getMenu().getBdescription();
                        titledate = response.body().getData().get(i).getDate();
                        images1=response.body().getData().get(i).getMenu().getBimage();
                    }*/


                    // System.out.println([i]);
                    // System.out.println(arr2[i]);

                    //  for(int i=0; i<=menu.get().getMenu().getBdescription(); i++) {
                    if (checkstatus.equals("breakfast")) {


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


                    }

                    if (checkstatus.equals("lunch")) {


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

                        monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                        monthdate4 = response.body().getData().get(13).getDate();
                        monthimage4 = response.body().getData().get(13).getMenu().getLimage();

                        monthtitle5 = response.body().getData().get(14).getMenu().getLdescription();
                        monthdate5 = response.body().getData().get(14).getDate();
                        monthimage5 = response.body().getData().get(14).getMenu().getLimage();

                        monthtitle6 = response.body().getData().get(15).getMenu().getLdescription();
                        monthdate6 = response.body().getData().get(15).getDate();
                        monthimage6 = response.body().getData().get(15).getMenu().getLimage();


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


                    }

                    if (checkstatus.equals("both")) {


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
                        imageboth5 = response.body().getData().get(5).getMenu().getLimage();
                        weektitlemain1 = response.body().getData().get(5).getMenu().getLdescription();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        weeksdate2 = response.body().getData().get(6).getDate();
                        weeksimage2 = response.body().getData().get(6).getMenu().getBimage();
                        imageboth6 = response.body().getData().get(6).getMenu().getLimage();
                        weektitlemain2 = response.body().getData().get(6).getMenu().getLdescription();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        weeksdate3 = response.body().getData().get(7).getDate();
                        weeksimage3 = response.body().getData().get(7).getMenu().getBimage();
                        imageboth7 = response.body().getData().get(7).getMenu().getLimage();
                        weektitlemain3 = response.body().getData().get(7).getMenu().getLdescription();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        weeksdate4 = response.body().getData().get(8).getDate();
                        weeksimage4 = response.body().getData().get(8).getMenu().getBimage();
                        imageboth8 = response.body().getData().get(8).getMenu().getLimage();
                        weektitlemain4 = response.body().getData().get(8).getMenu().getLdescription();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        weeksdate5 = response.body().getData().get(9).getDate();
                        weeksimage5 = response.body().getData().get(9).getMenu().getBimage();
                        imageboth9 = response.body().getData().get(9).getMenu().getLimage();
                        weektitlemain5 = response.body().getData().get(9).getMenu().getLdescription();

                        monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                        monthdate1 = response.body().getData().get(10).getDate();
                        monthimage1 = response.body().getData().get(10).getMenu().getBimage();
                        imageboth10 = response.body().getData().get(10).getMenu().getLimage();
                        monthtitlemain1 = response.body().getData().get(10).getMenu().getLdescription();


                        monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                        monthdate2 = response.body().getData().get(11).getDate();
                        monthimage2 = response.body().getData().get(11).getMenu().getBimage();
                        imageboth11 = response.body().getData().get(11).getMenu().getLimage();
                        monthtitlemain2 = response.body().getData().get(11).getMenu().getLdescription();

                        monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                        monthdate3 = response.body().getData().get(12).getDate();
                        monthimage3 = response.body().getData().get(12).getMenu().getBimage();
                        imageboth12 = response.body().getData().get(12).getMenu().getLimage();
                        monthtitlemain3 = response.body().getData().get(12).getMenu().getLdescription();

                        monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                        monthdate4 = response.body().getData().get(13).getDate();
                        monthimage4 = response.body().getData().get(13).getMenu().getBimage();
                        imageboth13 = response.body().getData().get(13).getMenu().getLimage();
                        monthtitlemain4 = response.body().getData().get(13).getMenu().getLdescription();

                        monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                        monthdate5 = response.body().getData().get(14).getDate();
                        monthimage5 = response.body().getData().get(14).getMenu().getBimage();
                        imageboth14 = response.body().getData().get(14).getMenu().getLimage();
                        monthtitlemain5 = response.body().getData().get(14).getMenu().getLdescription();

                        monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                        monthdate6 = response.body().getData().get(15).getDate();
                        monthimage6 = response.body().getData().get(15).getMenu().getBimage();
                        imageboth15 = response.body().getData().get(15).getMenu().getLimage();
                        monthtitlemain6 = response.body().getData().get(15).getMenu().getLdescription();


                        monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                        monthdate7 = response.body().getData().get(16).getDate();
                        monthimage7 = response.body().getData().get(16).getMenu().getBimage();
                        imageboth16 = response.body().getData().get(16).getMenu().getLimage();
                        monthtitlemain7 = response.body().getData().get(16).getMenu().getLdescription();

                        monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                        monthdate8 = response.body().getData().get(17).getDate();
                        monthimage8 = response.body().getData().get(17).getMenu().getBimage();
                        imageboth17 = response.body().getData().get(17).getMenu().getLimage();
                        monthtitlemain8 = response.body().getData().get(17).getMenu().getLdescription();

                        monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                        monthdate9 = response.body().getData().get(18).getDate();
                        monthimage9 = response.body().getData().get(18).getMenu().getBimage();
                        imageboth18 = response.body().getData().get(18).getMenu().getLimage();
                        monthtitlemain9 = response.body().getData().get(18).getMenu().getLdescription();

                        monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                        monthdate10 = response.body().getData().get(19).getDate();
                        monthimage10 = response.body().getData().get(19).getMenu().getBimage();
                        imageboth19 = response.body().getData().get(19).getMenu().getLimage();
                        monthtitlemain10 = response.body().getData().get(19).getMenu().getLdescription();


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

                        weekstitle1 = response.body().getData().get(5).getMenu().getBdescription();
                        weeksdate1 = response.body().getData().get(5).getDate();
                        weeksimage1=response.body().getData().get(5).getMenu().getBimage();


                        weekstitle2 = response.body().getData().get(6).getMenu().getBdescription();
                        weeksdate2 = response.body().getData().get(6).getDate();
                        weeksimage2=response.body().getData().get(6).getMenu().getBimage();

                        weekstitle3 = response.body().getData().get(7).getMenu().getBdescription();
                        weeksdate3 = response.body().getData().get(7).getDate();
                        weeksimage3=response.body().getData().get(7).getMenu().getBimage();

                        weekstitle4 = response.body().getData().get(8).getMenu().getBdescription();
                        weeksdate4 = response.body().getData().get(8).getDate();
                        weeksimage4=response.body().getData().get(8).getMenu().getBimage();

                        weekstitle5 = response.body().getData().get(9).getMenu().getBdescription();
                        weeksdate5 = response.body().getData().get(9).getDate();
                        weeksimage5=response.body().getData().get(9).getMenu().getBimage();

                        monthtitle1 = response.body().getData().get(10).getMenu().getBdescription();
                        monthdate1 = response.body().getData().get(10).getDate();
                        monthimage1=response.body().getData().get(10).getMenu().getBimage();


                        monthtitle2 = response.body().getData().get(11).getMenu().getBdescription();
                        monthdate2 = response.body().getData().get(11).getDate();
                        monthimage2=response.body().getData().get(11).getMenu().getBimage();

                        monthtitle3 = response.body().getData().get(12).getMenu().getBdescription();
                        monthdate3 = response.body().getData().get(12).getDate();
                        monthimage3=response.body().getData().get(12).getMenu().getBimage();

                        monthtitle4 = response.body().getData().get(13).getMenu().getBdescription();
                        monthdate4 = response.body().getData().get(13).getDate();
                        monthimage4=response.body().getData().get(13).getMenu().getBimage();

                        monthtitle5 = response.body().getData().get(14).getMenu().getBdescription();
                        monthdate5 = response.body().getData().get(14).getDate();
                        monthimage5=response.body().getData().get(14).getMenu().getBimage();

                        monthtitle6 = response.body().getData().get(15).getMenu().getBdescription();
                        monthdate6 = response.body().getData().get(15).getDate();
                        monthimage6=response.body().getData().get(16).getMenu().getBimage();


                        monthtitle7 = response.body().getData().get(16).getMenu().getBdescription();
                        monthdate7 = response.body().getData().get(16).getDate();
                        monthimage7=response.body().getData().get(16).getMenu().getBimage();

                        monthtitle8 = response.body().getData().get(17).getMenu().getBdescription();
                        monthdate8 = response.body().getData().get(17).getDate();
                        monthimage8=response.body().getData().get(17).getMenu().getBimage();

                        monthtitle9 = response.body().getData().get(18).getMenu().getBdescription();
                        monthdate9 = response.body().getData().get(18).getDate();
                        monthimage9=response.body().getData().get(18).getMenu().getBimage();

                        monthtitle10 = response.body().getData().get(19).getMenu().getBdescription();
                        monthdate10 = response.body().getData().get(19).getDate();
                        monthimage10=response.body().getData().get(19).getMenu().getBimage();


                    }*/

                    HomeCollection.date_collection_arr = new ArrayList<HomeCollection>();

                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate, maintitle, titlemain, imageboth, images1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate1, maintitle1, titlemain1, imageboth1, images2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate2, maintitle2, titlemain2, imageboth2, images3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate3, maintitle3, titlemain3, imageboth3, images4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + titledate4, maintitle4, titlemain4, imageboth4, images5, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate1, weekstitle1, weektitlemain1, imageboth5, weeksimage1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate2, weekstitle2, weektitlemain2, imageboth6, weeksimage2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate3, weekstitle3, weektitlemain3, imageboth7, weeksimage3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate4, weekstitle4, weektitlemain4, imageboth8, weeksimage4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + weeksdate5, weekstitle5, weektitlemain5, imageboth9, weeksimage5, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate1, monthtitle1, monthtitlemain6, imageboth10, monthimage1, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate2, monthtitle2, monthtitlemain7, imageboth11, monthimage2, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate3, monthtitle3, monthtitlemain8, imageboth12, monthimage3, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate4, monthtitle4, monthtitlemain9, imageboth13, monthimage4, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate5, monthtitle5, monthtitlemain10, imageboth14, monthimage5, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate6, monthtitle6, monthtitlemain11, imageboth15, monthimage6, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate7, monthtitle7, monthtitlemain12, imageboth16, monthimage7, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate8, monthtitle8, monthtitlemain13, imageboth17, monthimage8, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate9, monthtitle9, monthtitlemain14, imageboth18, monthimage9, ""));
                    HomeCollection.date_collection_arr.add(new HomeCollection("" + monthdate10, monthtitle10, monthtitlemain15, imageboth19, monthimage10, ""));

                    cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
                    cal_month_copy = (GregorianCalendar) cal_month.clone();
                    hwAdapter = new HwAdapter(CalenderView.this, cal_month, HomeCollection.date_collection_arr);

                    tv_month = findViewById(R.id.tv_month);
                    title = findViewById(R.id.title);
                    title.setText(Title);
                    linearLayout = findViewById(R.id.ok);
                    tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), CheckoutPage.class);
                            intent.putExtra("title", titleMain);
                            intent.putExtra("dates", datesMain);
                            intent.putExtra("status", status);
                            intent.putExtra("packageId", packageId);
                            intent.putExtra("packageTitle", packageTitle);
                            intent.putExtra("packageDuration", packageDuration);
                            intent.putExtra("choosepersonCheck", choosepersonCheck);
                            intent.putExtra("subscribeNumber", subscribeNumber);
                            intent.putExtra("softDrinkCheck", softDrinkCheck);
                            intent.putExtra("peopleMain", peopleMain);
                            intent.putExtra("drinkId", drinkId);
                            startActivity(intent);
                        }
                    });

                    ImageButton previous = findViewById(R.id.ib_prev);
                    previous.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cal_month.get(GregorianCalendar.MONTH) == 4 && cal_month.get(GregorianCalendar.YEAR) == 2019) {
                                //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(CalenderView.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
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
                           /* if (cal_month.get(GregorianCalendar.MONTH) == 6 && cal_month.get(GregorianCalendar.YEAR) == 2019) {
                                //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                                Toast.makeText(CalenderView.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                            } else {
                                setNextMonth();
                                refreshCalendar();
                            }*/
                            setNextMonth();
                            refreshCalendar();
                        }
                    });
                    GridView gridview = findViewById(R.id.gv_calendar);
                    gridview.setAdapter(hwAdapter);
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            String selectedGridDate = HwAdapter.day_string.get(position);
                            ((HwAdapter) parent.getAdapter()).getPositionList(selectedGridDate, CalenderView.this);
                        }

                    });
                }

            }

            @Override
            public void onFailure(Call<homemenumodel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
