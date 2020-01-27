package com.example.myapplication.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.CalenderView;
import com.example.myapplication.Constants;
import com.example.myapplication.MainMenu;
import com.example.myapplication.Model.Retrofit.Datum;
import com.example.myapplication.Model.Retrofit.Drinks;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Model.Retrofit.softdrinkModel;
import com.example.myapplication.MyAccountHome;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

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


public class Packages extends RecyclerView.Adapter<Packages.MovieViewHolder> implements WheelPicker.OnItemSelectedListener {
    //private List<Result> dataList;

    private List<Datum> movies;
    private int rowLayout;
    private Context context;
    private Activity activity;
    String Title, date, date_time, checkstatus, titles, lunch, brakfast, both, duration, chooseperson, choose, Drinks;
    TextView etdate;
    Integer mYear, mMonth, mDay;
    Calendar c;
    TextView spine, spin, spinTitle;
    Dialog dialog;
    ArrayList<String> listDrinkId = new ArrayList<>();
    DatePickerDialog datePickerDialog;
    RecyclerView.Adapter dataAdapters;
    private List<Drinks> menu = new ArrayList<>();
    private TextView personText, groupText, drinksText;
    private boolean getData = false;
    private String drinkId = "";
    private boolean isactive = false;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title, chooseopackage;
        TextView brakfast;
        TextView titleTV;
        TextView lunch;
        TextView viewMore;
        TextView brakfastlunch;

        ImageView image;
        LinearLayout weekly;
        LinearLayout menuView;

        public MovieViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.titles);
            titleTV = v.findViewById(R.id.title);
            brakfast = v.findViewById(R.id.breakfast);
            lunch = v.findViewById(R.id.lunch);
            brakfastlunch = v.findViewById(R.id.brakfastlunch);
            image = v.findViewById(R.id.image);
            weekly = v.findViewById(R.id.weekly);
            menuView = v.findViewById(R.id.menuview);

            chooseopackage = v.findViewById(R.id.choosepackage);
            viewMore = v.findViewById(R.id.viewMore);

        }

    }

    public Packages(List<Datum> movies, int rowLayout, Context context) {

        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public Packages.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);

    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        titles = movies.get(position).getTitle();
        brakfast = movies.get(position).getBreakfast();
        lunch = movies.get(position).getLunch();
        both = movies.get(position).getBothh();
        duration = movies.get(position).getDuration();

        holder.title.setText(movies.get(position).getTitle());


        //     holder.brakfast.setText(movies.get(position).getBreakfast());
        holder.brakfast.setText(context.getString(R.string.breakfast_new) + " " + movies.get(position).getBreakfast() + " OMR");
        holder.lunch.setText(context.getString(R.string.lunch_new) + " " + movies.get(position).getLunch() + " OMR");
        holder.brakfastlunch.setText(context.getString(R.string.both_new) + " " + movies.get(position).getBothh() + " OMR");

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            holder.brakfast.setText("OMR " + movies.get(position).getBreakfast() + " " + context.getString(R.string.breakfast_ar_new));
            holder.lunch.setText("OMR " + movies.get(position).getLunch() + " " + context.getString(R.string.lunch_ar_new));
            holder.brakfastlunch.setText("OMR " + movies.get(position).getBothh() + " " + context.getString(R.string.both_ar_new));

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");


            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            holder.brakfast.setTypeface(custom_font_azab);
            holder.lunch.setTypeface(custom_font_azab);
            holder.brakfastlunch.setTypeface(custom_font_azab);
            holder.title.setTypeface(custom_font_azab);
            holder.chooseopackage.setTypeface(custom_font_azab);
            holder.viewMore.setTypeface(custom_font_azab);


            holder.brakfast.setTextSize(14);

            holder.lunch.setTextSize(14);
            holder.brakfastlunch.setTextSize(14);

            holder.chooseopackage.setTextSize(18);
            holder.viewMore.setTextSize(16);
            holder.brakfast.setTextSize(13);
            holder.lunch.setTextSize(13);
            holder.brakfastlunch.setTextSize(13);

        } else {
            holder.brakfast.setText(context.getString(R.string.breakfast_new) + " " + movies.get(position).getBreakfast() + " OMR");
            holder.lunch.setText(context.getString(R.string.lunch_new) + " " + movies.get(position).getLunch() + " OMR");
            holder.brakfastlunch.setText(context.getString(R.string.both_new) + " " + movies.get(position).getBothh() + " OMR");

        }

        if (movies.get(position).getIsactivepackage().equalsIgnoreCase("yes")) {
            holder.chooseopackage.setText(context.getString(R.string.active_package_string));
        }

        if (movies.get(position).getIsactivepackage().equalsIgnoreCase("yes")) {
            isactive = true;
        }

        Glide.with(context)
                .load(movies.get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

/*
        Glide.with(context)
                .load(movies.get(position).getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);*/

        holder.weekly.setOnClickListener(new View.OnClickListener() {
            private String dayOfWeeks;

            @Override
            public void onClick(View v) {
                if (isactive) {


                    if (movies.get(position).getIsactivepackage().equalsIgnoreCase("yes")) {
                        Intent intent = new Intent(context, MyAccountHome.class);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, context.getString(R.string.sorry_you_have_package), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    final int[] selectedYear = {currentYear};
                    titles = movies.get(position).getTitle();
                    String Finish = "done";
                    final Dialog dialog;
                    Button cancel, send;
                    final Button doneLeftBT;
                    final LinearLayout softdrink;
                    TextView title, drinks, startDateTV, drinkTV;
                    chooseperson = context.getString(R.string.individual_string);
                    choose = context.getString(R.string._2_person);
                    TextView emails;
                    RelativeLayout mainRL;
                    RadioButton radiobrakfast, radiolunch, radioboth;
                    RadioGroup radioGroup;
                    final WheelPicker wheelView, mainGroupWheel, drinkWheel;
                    dialog = new Dialog(context);
                    int typeSystemAlert = 0;
         /*       if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    typeSystemAlert = WindowManager.LayoutParams.TYPE_PHONE;
                } else
                    typeSystemAlert = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                dialog.getWindow().setType(typeSystemAlert);*/
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.buyitnow);
                    wheelView = dialog.findViewById(R.id.main_wheel_left);
                    mainGroupWheel = dialog.findViewById(R.id.main_group);
                    drinkTV = dialog.findViewById(R.id.drinkTV);
                    drinkWheel = dialog.findViewById(R.id.drink_wheel);
                    radioGroup = dialog.findViewById(R.id.radio_group);
                    mainRL = dialog.findViewById(R.id.mainRL);
                    radiobrakfast = dialog.findViewById(R.id.radiobreakfast);
                    radiolunch = dialog.findViewById(R.id.radiolunch);
                    radioboth = dialog.findViewById(R.id.radioboth);
                    cancel = dialog.findViewById(R.id.cancel_button);
                    send = dialog.findViewById(R.id.send_button);
                    doneLeftBT = dialog.findViewById(R.id.doneLeftBT);
                    title = dialog.findViewById(R.id.title);
                    drinks = dialog.findViewById(R.id.drinks);
                    spine = dialog.findViewById(R.id.spine);
                    startDateTV = dialog.findViewById(R.id.startDateTV);
                    spin = dialog.findViewById(R.id.spin);
                    spinTitle = dialog.findViewById(R.id.spinTitle);
                    emails = dialog.findViewById(R.id.emails);
                    etdate = dialog.findViewById(R.id.etdate);
                    String languageValue = preferences.getString(Constants.SETLANG, "en");
                    if (languageValue.equalsIgnoreCase("ar")) {
                        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
                        Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");

                        title.setTypeface(custom_font_azab);
                        cancel.setTypeface(custom_font_azab);
                        send.setTypeface(custom_font_azab);
                        spine.setTypeface(custom_font_azab);
                        spin.setTypeface(custom_font_azab);
                        emails.setTypeface(custom_font_azab);
                        radiobrakfast.setTypeface(custom_font_azab);
                        radiolunch.setTypeface(custom_font_azab);
                        radioboth.setTypeface(custom_font_azab);
                        startDateTV.setTypeface(custom_font_azab);
                        etdate.setTypeface(custom_font_azab);
                        drinkTV.setTypeface(custom_font_azab);
                        drinks.setTypeface(custom_font_azab);
                    }


                    personText = spin;
                    groupText = spine;
                    drinksText = drinks;
                    softdrink = dialog.findViewById(R.id.soft);
                    etdate = dialog.findViewById(R.id.etdate);
                    wheelView.setOnItemSelectedListener(Packages.this);
                    mainGroupWheel.setOnItemSelectedListener(Packages.this);
                    wheelView.setData(getList());
                    getDrinkData(drinks, drinkWheel);
                    mainGroupWheel.setData(getGroupList());

                    drinkWheel.setOnItemSelectedListener(Packages.this);

                    etdate.setText(date);
                    mainRL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            wheelView.setVisibility(View.GONE);
                            mainGroupWheel.setVisibility(View.GONE);
                            drinkWheel.setVisibility(View.GONE);
                        }
                    });
                    doneLeftBT.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            wheelView.setVisibility(View.GONE);

                        }
                    });
                    spine.setText(chooseperson);
                    spin.setText(choose);

                    drinks.setText(Drinks);

                    if (checkstatus == "lunch") {

                        radiolunch.setChecked(true);
                        softdrink.setVisibility(View.VISIBLE);
                    }
                    if (checkstatus == "both") {
                        radioboth.setChecked(true);
                        softdrink.setVisibility(View.VISIBLE);
                    } /*else {
                        radiobrakfast.setChecked(true);
                    }*/

                    if (Drinks == "Coca-cola") {
                        softdrink.setVisibility(View.VISIBLE);
                    }
                    if (chooseperson == context.getString(R.string.group_string)) {
                        spin.setVisibility(View.VISIBLE);
                        spinTitle.setVisibility(View.VISIBLE);
                    } else {
                        spin.setVisibility(View.GONE);
                        spinTitle.setVisibility(View.GONE);
                    }

                    radiobrakfast.setChecked(true);

                    if (radiobrakfast.isChecked()) {
                        checkstatus = "breakfast";
                        if (Drinks == "Coca-cola||Pepsi") {
                            softdrink.setVisibility(View.GONE);
                        }
                        softdrink.setVisibility(View.GONE);
                        SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = sendintent.edit();
                        editor.putString("status", checkstatus);
                        editor.apply();
                        // Toast.makeText(context, "breakfast is check", Toast.LENGTH_LONG).show();
                    }

                    radiobrakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkstatus = "breakfast";
                            softdrink.setVisibility(View.GONE);
                            SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sendintent.edit();
                            editor.putString("status", checkstatus);
                            editor.putString("title", titles);
                            editor.apply();
                            //  Toast.makeText(context, "breakfast is check", Toast.LENGTH_LONG).show();

                        }
                    });

                    radiolunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            checkstatus = "lunch";
                            softdrink.setVisibility(View.VISIBLE);
                            SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sendintent.edit();
                            editor.putString("status", checkstatus);
                            editor.putString("title", titles);
                            editor.apply();
                            // Toast.makeText(context, "lunch is check", Toast.LENGTH_LONG).show();

                        }
                    });

                    radioboth.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkstatus = "both";
                            softdrink.setVisibility(View.VISIBLE);
                            SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sendintent.edit();
                            editor.putString("status", checkstatus);
                            editor.putString("title", titles);
                            editor.apply();
                            //   Toast.makeText(context, "both is check", Toast.LENGTH_LONG).show();

                        }
                    });

                    SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = sendintent.edit();
                    editor.putString("title", titles);
                    editor.putString("dates", date);
                    editor.putString("status", checkstatus);
                    editor.putString("breakfast", brakfast);
                    editor.putString("lunch", lunch);
                    editor.putString("both", both);
                    editor.putString("time", duration);
                    editor.apply();

                    final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    Title = preferences.getString("title", "");

                    final Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.DATE, 1);
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
                    String strDate = "" + mdformat.format(calendar.getTime());
                    Date tomorrow = calendar.getTime();


                    //today Day
                    final Calendar calendarToday = Calendar.getInstance();
                    calendarToday.add(Calendar.DATE, 0);
                    SimpleDateFormat mdformatToday = new SimpleDateFormat("yyyy-MM-dd ");
                    String strDateToday = "" + mdformatToday.format(calendar.getTime());
                    Date todayDate = calendarToday.getTime();


                    date = mdformat.format(tomorrow);
                    SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                    String dayName = simpleDateFormat.format(tomorrow);
                    dayOfWeeks = dayName;

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c1 = Calendar.getInstance();
                    try {
                        c1.setTime(sdf.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
                    Date d = new Date();
                    String dayOfTheWeek = sdfDay.format(d);

                    String dateInString;
                    if (dayOfTheWeek.equalsIgnoreCase("Friday")) {
                        c1.add(Calendar.DATE, 1);
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date resultdate = new Date(c1.getTimeInMillis());
                        dateInString = sdf.format(resultdate);
                        etdate.setText(dateInString);
                    } /*else if (etdate.getText().toString().length() == 0) {
                        c1.add(Calendar.DATE,0);
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date resultdate = new Date(c1.getTimeInMillis());
                        dateInString = sdf.format(resultdate);
                        etdate.setText(dateInString);

                    }*/ else if (dayOfTheWeek.equalsIgnoreCase("Saturday")) {
                        c1.add(Calendar.DATE, 0);
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date resultdate = new Date(c1.getTimeInMillis());
                        dateInString = sdf.format(resultdate);
                        etdate.setText(dateInString);
                    } else {
                        if (dayOfWeeks.equalsIgnoreCase("Friday")) {
                            c1.add(Calendar.DATE, 2);
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date resultdate = new Date(c1.getTimeInMillis());
                            dateInString = sdf.format(resultdate);
                            etdate.setText(dateInString);
                        } else if (dayOfWeeks.equalsIgnoreCase("Saturday")) {
                            c1.add(Calendar.DATE, 1);
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date resultdate = new Date(c1.getTimeInMillis());
                            dateInString = sdf.format(resultdate);
                            etdate.setText(dateInString);
                        } else {
                            etdate.setText(date);
                        }

                    }


                    etdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String languageValue = preferences.getString(Constants.SETLANG, "en");
                            if (languageValue.equalsIgnoreCase("ar")) {
                                Locale myLocale = new Locale("ar");
                                Resources res = context.getResources();
                                DisplayMetrics dm = res.getDisplayMetrics();
                                Configuration conf = res.getConfiguration();
                                conf.locale = myLocale;
                                res.updateConfiguration(conf, dm);
                            } else {
                                Locale myLocale = new Locale("en");
                                Resources res = context.getResources();
                                DisplayMetrics dm = res.getDisplayMetrics();
                                Configuration conf = res.getConfiguration();
                                conf.locale = myLocale;
                                res.updateConfiguration(conf, dm);
                            }
                           /* Locale locale = context.getResources().getConfiguration().locale;
                            Locale.setDefault(Locale.US);*/
                            //  showDialogPicker();
                            // datePicker();

                            c = Calendar.getInstance();
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);

                            //R.style.AppDialogTheme,
                            datePickerDialog = new DatePickerDialog(context,

                                    new DatePickerDialog.OnDateSetListener() {


                                        @Override
                                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                            Calendar c = Calendar.getInstance();
                                            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                                            String dayOfWeeks = "";
                                            selectedYear[0] = year;
                                            SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                                            try {
                                                Date myDate = inFormat.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                                                String dayName = simpleDateFormat.format(myDate);
                                                dayOfWeeks = dayName;
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                            if (dayOfWeeks.equalsIgnoreCase("Friday")) {
                                                // dpDialog.setTitle("Sorry Sundays wont be accepted");
                                                // dpDialog.setMessage("not selected");
                                                // dpDialog.show();
                                                //datePicker.setBackgroundColor(000000);
                                                date_time = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth + 2);
                                                date = date_time;
                                                etdate.setText(date);
                                                Toast.makeText(context, context.getString(R.string.sorry_donot_serve), Toast.LENGTH_LONG).show();
                                            } else if (dayOfWeeks.equalsIgnoreCase("Saturday")) {
                                                // dpDialog.setTitle("Sorry Sundays wont be accepted");
                                                // dpDialog.setMessage("not selected");
                                                // dpDialog.show();
                                                //datePicker.setBackgroundColor(000000);
                                                date_time = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth + 1);
                                                date = date_time;
                                                etdate.setText(date);
                                                Toast.makeText(context, context.getString(R.string.sorry_donot_sat), Toast.LENGTH_LONG).show();
                                            } else {
                                                date_time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                                date = date_time;

                                                etdate.setText(date);
                                                //Intent intent= new Intent(CalenderView.this,CheckoutPage.class);
                                                // startActivity(intent);
                                                //*************Call Time Picker Here ********************
                                                // tiemPicker();
                                                //     Toast.makeText(context, "" + date, Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    }, mYear, mMonth, mDay + 1);

                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DAY_OF_YEAR, 1);
                            datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                            int typeSystemAlert = 0;
                     /*   if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                            typeSystemAlert = WindowManager.LayoutParams.TYPE_PHONE;
                        } else
                            typeSystemAlert = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

                        datePickerDialog.getWindow().setType(typeSystemAlert);*/

                            datePickerDialog.show();


                        }
                        // etdate.setText(date);

                    });

                    title.setText(movies.get(position).getTitle());
                    spin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (wheelView.getVisibility() == View.VISIBLE) {
                                wheelView.setVisibility(View.GONE);
                            } else {
                                if (mainGroupWheel.getVisibility() == View.VISIBLE) {
                                    mainGroupWheel.setVisibility(View.GONE);
                                }
                                wheelView.setVisibility(View.VISIBLE);
                            }

                     /*   Button cancel, send;
                        TextView title;
                        final Spinner spine, spin;
                        final EditText etdate;
                        RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;
                        dialog.setContentView(R.layout.choosegroup);


                        relativeLayout1 = (RelativeLayout) dialog.findViewById(R.id.rel_title);
                        relativeLayout2 = (RelativeLayout) dialog.findViewById(R.id.rel_title2);
                        relativeLayout3 = (RelativeLayout) dialog.findViewById(R.id.rel_title3);
                        relativeLayout4 = (RelativeLayout) dialog.findViewById(R.id.rel_title4);
                        relativeLayout1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                choose = "2 Person";
                                Buyitnow(wheelView);
                                dialog.dismiss();

                            }
                        });
                        relativeLayout2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                choose = "4 Person";
                                Buyitnow(wheelView);
                                dialog.dismiss();
                                // setLocale("ar");


                            }
                        });

                        relativeLayout3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                choose = "6 Person";
                                Buyitnow(wheelView);
                                dialog.dismiss();

                            }
                        });
                        relativeLayout4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                choose = "8 Person";
                                Buyitnow(wheelView);
                                dialog.dismiss();
                                // setLocale("ar");


                            }
                        });
*/
                            //dialog.show();


                        }
                    });
                    spine.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mainGroupWheel.getVisibility() == View.VISIBLE) {
                                mainGroupWheel.setVisibility(View.GONE);
                            } else {
                                if (wheelView.getVisibility() == View.VISIBLE) {
                                    wheelView.setVisibility(View.GONE);
                                    doneLeftBT.setVisibility(View.GONE);
                                }
                                if (drinkWheel.getVisibility() == View.VISIBLE) {
                                    drinkWheel.setVisibility(View.GONE);
                                }
                                mainGroupWheel.setVisibility(View.VISIBLE);
                            }

                    /*    Button cancel, send;
                        TextView title;
                        final Spinner spine, spin;
                        final EditText etdate;
                        RelativeLayout relativeLayout1, relativeLayout2;

                     dialog.setContentView(R.layout.choosetype);

                        relativeLayout1 = (RelativeLayout) dialog.findViewById(R.id.tv_title);
                        relativeLayout2 = (RelativeLayout) dialog.findViewById(R.id.tv_title2);
                        relativeLayout1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                chooseperson = "Individual";
                                Buyitnow(wheelView);
                                dialog.dismiss();

                            }
                        });
                        relativeLayout2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                chooseperson = "Group";
                                Buyitnow(wheelView);
                                dialog.dismiss();
                                // setLocale("ar");


                            }
                        });*/

                            //  dialog.show();


                        }
                    });

                    drinks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (drinkWheel.getVisibility() == View.VISIBLE) {
                                drinkWheel.setVisibility(View.GONE);
                            } else {
                                if (wheelView.getVisibility() == View.VISIBLE) {
                                    wheelView.setVisibility(View.GONE);
                                    doneLeftBT.setVisibility(View.GONE);
                                }
                                if (mainGroupWheel.getVisibility() == View.VISIBLE) {
                                    mainGroupWheel.setVisibility(View.GONE);
                                }
                                if (getData) {
                                    drinkWheel.setVisibility(View.VISIBLE);
                                }

                            }


/*
                        Button cancel, send;
                        final TextView title;
                        final Spinner spine, spin;
                        final EditText etdate;
                        RelativeLayout relativeLayout1, relativeLayout2;
                        final RecyclerView parcelmenu;
                        final TextView tvtitle1, titlemenu, tvtitle3, tvtitle4;


                        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.drinks);

                        //  parcelmenu=(RecyclerView)dialog.findViewById(R.id.parcelmenu);
                        // back=(TextView)dialog.findViewById(R.id.back);
                        titlemenu = (TextView) dialog.findViewById(R.id.tv_date);
                        tvtitle1 = (TextView) dialog.findViewById(R.id.tv_title1);
                        tvtitle3 = (TextView) dialog.findViewById(R.id.tv_title3);
                        tvtitle4 = (TextView) dialog.findViewById(R.id.tv_title4);

                        //parcelmenu.setNestedScrollingEnabled(false);


                        // final ProgressDialog dialog = new ProgressDialog(context);
                        // dialog.setCancelable(false);
                        //dialog.setMessage("Loding");
                        //dialog.show();
                        final UserID task = new UserID();
                        task.setLang("en");
                        titlemenu.setText(menu.get(0).getTitle());
                        tvtitle1.setText(menu.get(1).getTitle());
                        tvtitle3.setText(menu.get(2).getTitle());
                        tvtitle4.setText(menu.get(3).getTitle());

                        titlemenu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Drinks = menu.get(0).getTitle();
                                Buyitnow(wheelView);
                                dialog.dismiss();
                            }
                        });

                        tvtitle1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Drinks = menu.get(1).getTitle();
                                Buyitnow(wheelView);
                                dialog.dismiss();
                            }
                        });

                        tvtitle3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Drinks = menu.get(2).getTitle();
                                Buyitnow(wheelView);
                                dialog.dismiss();
                            }
                        });

                        tvtitle4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Drinks = menu.get(3).getTitle();
                                Buyitnow(wheelView);
                                dialog.dismiss();
                            }
                        });

                        *//*final ApiServices userService = ApiUrl.createService(ApiServices.class,
                                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
                        userService.GetSoftdrink(task).enqueue(new Callback<softdrinkModel>() {
                            @Override
                            public void onResponse(Call<softdrinkModel> call, Response<softdrinkModel> response) {
                                if (response.isSuccessful()) {
                                    // dialog.dismiss();
                                    final List<Drinks> menu = response.body().getData();


                                    // GridLayoutManager latest = new GridLayoutManager(context,1);

                                    // latest.setOrientation(GridLayoutManager.VERTICAL);
                                    //parcelmenu.setItemAnimator(new DefaultItemAnimator());
                                    // parcelmenu.setLayoutManager(latest);
                                    // recyclerView.setNestedScrollingEnabled(false);
                                    // parcelmenu.setAdapter(new SoftDrinkAdapter(menu, R.layout.drinks, context));


                                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<softdrinkModel> call, Throwable t) {
                                //dialog.dismiss();
                                Toast.makeText(context, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
*//*
                        dialog.show();*/

                        }

                /*relativeLayout1=(RelativeLayout) dialog.findViewById(R.id.tv_title);
                relativeLayout2=(RelativeLayout) dialog.findViewById(R.id.tv_title2);
                relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks="Coca-Cola";
                        Buyitnow();
                        dialog.dismiss();

                    }
                });
                relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks="Pepsi";
                        Buyitnow();
                        dialog.dismiss();
                        // setLocale("ar");



                    }
                });*/


                    });

                   /* // Spinner Drop down elements
                    List<String> categories = new ArrayList<String>();
                    categories.add("Individual ");
                    categories.add("Group Order");

                    List<String> list = new ArrayList<String>();
                    list.add("2 ");
                    list.add("4");
                    list.add("6");
                    list.add("8");

                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context.getApplicationContext(), R.layout.spinner_item, categories);
                    //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spine.setAdapter(dataAdapter);
                    spine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                            if (position ==1) {
                                spin.setVisibility(View.VISIBLE);
                            }

                            if (position ==0) {
                                spin.setVisibility(View.GONE);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


// Creating adapter for spinner


                    ArrayAdapter<String> dataAdapters = new ArrayAdapter<String>(context,  R.layout.spinner_item, list);


                     dataAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    spin.setAdapter(dataAdapters);
                    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });*/


                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });

                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final int thisYear = Calendar.getInstance().get(Calendar.YEAR);

                            Date c = Calendar.getInstance().getTime();
                            System.out.println("Current time => " + c);

                            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                            String formattedDate = df.format(c);


                            if (etdate.getText().toString().length() == 0) {
                                etdate.setText(date);
                                Toast.makeText(context, "Please Choose date first", Toast.LENGTH_LONG).show();
                            } else if (selectedYear[0] < thisYear) {
                                Toast.makeText(context, "Selected year should not be earlier than Current year", Toast.LENGTH_LONG).show();

                            } else {
                                dialog.dismiss();
                                Intent intent1 = new Intent(context, CalenderView.class);
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("title", movies.get(position).getTitle());
                                editor.putString("dates", date);
                                editor.putString("status", checkstatus);
                                editor.putString("choosepersonCheck", chooseperson);
                                editor.putString("breakfast", movies.get(position).getBreakfast());
                                editor.putString("lunch", movies.get(position).getLunch());
                                editor.putString("both", movies.get(position).getBothh());
                                editor.putString("time", movies.get(position).getDuration());
                                editor.apply();
                                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent1.putExtra("title", movies.get(position).getTitle());
                                intent1.putExtra("dates", date);
                                intent1.putExtra("status", checkstatus);
                                intent1.putExtra("packageId", movies.get(position).getId());
                                intent1.putExtra("packageTitle", movies.get(position).getTitle());
                                intent1.putExtra("packageDuration", movies.get(position).getDuration());
                                intent1.putExtra("choosepersonCheck", chooseperson);
                                intent1.putExtra("peopleMain", spin.getText().toString().trim());
                                intent1.putExtra("drinkId", drinkId);
                                if (checkstatus.equalsIgnoreCase("lunch") || checkstatus.equalsIgnoreCase("both")) {
                                    intent1.putExtra("softDrinkCheck", drinksText.getText().toString().trim());
                                }
                                context.startActivity(intent1);
                            }

                            // datePicker();

                            // dialog.dismiss();

                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                   /* Intent intent1=new Intent(context, CalenderView.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("title",Finish);
                    editor.putString("title",movies.get(position).getTitle());
                    editor.putString("breakfast",movies.get(position).getBreakfast());
                    editor.putString("lunch",movies.get(position).getLunch());
                    editor.putString("both",movies.get(position).getBothh());
                    editor.putString("time",movies.get(position).getDuration());
                    editor.apply();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(intent1);*/

                }


            }
        });


        holder.menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("title", movies.get(position).getTitle());
                editor.putString("breakfast", movies.get(position).getBreakfast());
                editor.putString("lunch", movies.get(position).getLunch());
                editor.putString("both", movies.get(position).getBothh());
                editor.putString("time", movies.get(position).getDuration());
                editor.apply();
                Intent menu = new Intent(context, MainMenu.class);
                menu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(menu);
            }
        });

    }

    public void Buyitnow(final WheelPicker wheekPicker) {
        String Finish = "done";
        final Dialog dialog;
        Button cancel, send;
        TextView title, drinks;
        final LinearLayout softdrink;

        RadioButton radiobrakfast, radiolunch, radioboth;
        RadioGroup radioGroup;
        RelativeLayout mainRL;
        final WheelPicker wheelView, mainGroupWheel, drinkWheel;
        dialog = new Dialog(context);
        int typeSystemAlert = 0;
       /* if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            typeSystemAlert = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        } else
            typeSystemAlert = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        dialog.getWindow().setType(typeSystemAlert);*/
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.buyitnow);


        radioGroup = dialog.findViewById(R.id.radio_group);
        mainRL = dialog.findViewById(R.id.mainRL);
        radiobrakfast = dialog.findViewById(R.id.radiobreakfast);
        radiolunch = dialog.findViewById(R.id.radiolunch);
        radioboth = dialog.findViewById(R.id.radioboth);
        cancel = dialog.findViewById(R.id.cancel_button);
        send = dialog.findViewById(R.id.send_button);
        title = dialog.findViewById(R.id.title);
        drinks = dialog.findViewById(R.id.drinks);
        spine = dialog.findViewById(R.id.spine);
        spin = dialog.findViewById(R.id.spin);
        spinTitle = dialog.findViewById(R.id.spinTitle);

        personText = spin;
        groupText = spine;
        drinksText = drinks;
        softdrink = dialog.findViewById(R.id.soft);
        etdate = dialog.findViewById(R.id.etdate);
        wheelView = dialog.findViewById(R.id.main_wheel_left);
        mainGroupWheel = dialog.findViewById(R.id.main_group);
        drinkWheel = dialog.findViewById(R.id.drink_wheel);
        wheelView.setOnItemSelectedListener(this);
        wheelView.setData(getList());
        mainGroupWheel.setOnItemSelectedListener(this);
        mainGroupWheel.setData(getGroupList());
        drinkWheel.setOnItemSelectedListener(this);
        drinkWheel.setData(getDrinkList());
        etdate.setText(date);

        drinks.setText(Drinks);
        spine.setText(chooseperson);
        spin.setText(choose);
        mainRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wheelView.setVisibility(View.GONE);
                mainGroupWheel.setVisibility(View.GONE);
                drinkWheel.setVisibility(View.GONE);
            }
        });

        if (checkstatus == "lunch") {
            radiolunch.setChecked(true);
            softdrink.setVisibility(View.VISIBLE);
        }
        if (checkstatus == "both") {
            radioboth.setChecked(true);
            softdrink.setVisibility(View.VISIBLE);
        }
        /*else {
            radiobrakfast.setChecked(true);
        }*/
        if (Drinks == "Coca-cola") {
            softdrink.setVisibility(View.VISIBLE);
        }

        if (chooseperson == context.getString(R.string.group_string)) {
            spin.setVisibility(View.VISIBLE);
            spinTitle.setVisibility(View.VISIBLE);
        } else {
            spin.setVisibility(View.GONE);
            spinTitle.setVisibility(View.GONE);
        }
        if (radiobrakfast.isChecked()) {
            checkstatus = "breakfast";
            if (Drinks == "Coca-cola||Pepsi") {
                softdrink.setVisibility(View.GONE);
            }
            softdrink.setVisibility(View.GONE);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("status", checkstatus);
            editor.apply();
            Toast.makeText(context, "breakfast is check", Toast.LENGTH_LONG).show();
        }

        radiobrakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                softdrink.setVisibility(View.GONE);
                checkstatus = "breakfast";
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("status", checkstatus);
                editor.putString("title", titles);
                editor.apply();
                Toast.makeText(context, "breakfast is check", Toast.LENGTH_LONG).show();

            }
        });

        radiolunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkstatus = "lunch";
                softdrink.setVisibility(View.VISIBLE);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("status", checkstatus);
                editor.putString("title", titles);
                editor.apply();

                Toast.makeText(context, "lunch is check", Toast.LENGTH_LONG).show();

            }
        });

        radioboth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkstatus = "both";
                softdrink.setVisibility(View.VISIBLE);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("status", checkstatus);
                editor.putString("title", titles);
                editor.apply();

                Toast.makeText(context, "both is check", Toast.LENGTH_LONG).show();

            }
        });

        SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sendintent.edit();
        editor.putString("title", titles);
        editor.putString("dates", date);
        editor.putString("status", checkstatus);
        editor.putString("breakfast", brakfast);
        editor.putString("lunch", lunch);
        editor.putString("both", both);
        editor.putString("time", duration);
        editor.apply();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Title = preferences.getString("title", "");


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = "" + mdformat.format(calendar.getTime());
        Date tomorrow = calendar.getTime();
        date = mdformat.format(tomorrow);
        etdate.setText(date);
        if (etdate.getText().toString().length() == 0) {
            etdate.setText(date);

        }
        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showDialogPicker();
                c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(context, R.style.AppTheme,

                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar c = Calendar.getInstance();
                                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                                String dayOfWeeks = "";
                                SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                                try {
                                    Date myDate = inFormat.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                                    String dayName = simpleDateFormat.format(myDate);
                                    dayOfWeeks = dayName;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (dayOfWeeks.equalsIgnoreCase("Friday")) {
                                    // dpDialog.setTitle("Sorry Sundays wont be accepted");
                                    // dpDialog.setMessage("not selected");
                                    // dpDialog.show();
                                    //datePicker.setBackgroundColor(000000);
                                    date_time = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth + 2);
                                    date = date_time;
                                    etdate.setText(date);
                                    Toast.makeText(context, context.getString(R.string.we_dont_serve_on_fri), Toast.LENGTH_LONG).show();
                                } else if (dayOfWeeks.equalsIgnoreCase("Saturday")) {
                                    // dpDialog.setTitle("Sorry Sundays wont be accepted");
                                    // dpDialog.setMessage("not selected");
                                    // dpDialog.show();
                                    //datePicker.setBackgroundColor(000000);
                                    date_time = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth + 1);
                                    date = date_time;
                                    etdate.setText(date);
                                    Toast.makeText(context, context.getString(R.string.we_dont_serve_on_sat), Toast.LENGTH_LONG).show();
                                } else {
                                    date_time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                    date = date_time;
                                    etdate.setText(date);
                                    //Intent intent= new Intent(CalenderView.this,CheckoutPage.class);
                                    // startActivity(intent);
                                    //*************Call Time Picker Here ********************
                                    // tiemPicker();
                                    Toast.makeText(context, "" + date, Toast.LENGTH_LONG).show();
                                }


                            }
                        }, mYear, mMonth, mDay);


                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                int typeSystemAlert = 0;
                /*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    typeSystemAlert = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                } else
                    typeSystemAlert = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                dialog.getWindow().setType(typeSystemAlert);
                datePickerDialog.getWindow().setType(typeSystemAlert);*/
                datePickerDialog.show();
            }
            // etdate.setText(date);

        });

        title.setText(titles);

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wheelView.getVisibility() == View.VISIBLE) {
                    wheelView.setVisibility(View.GONE);
                } else {
                    if (mainGroupWheel.getVisibility() == View.VISIBLE) {
                        mainGroupWheel.setVisibility(View.GONE);
                    }
                    if (drinkWheel.getVisibility() == View.VISIBLE) {
                        drinkWheel.setVisibility(View.GONE);
                    }
                    wheelView.setVisibility(View.VISIBLE);
                }


              /*  Button cancel, send;
                TextView title;
                final Spinner spine, spin;
                final EditText etdate;
                RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;
                dialog.setContentView(R.layout.choosegroup);

                relativeLayout1 = (RelativeLayout) dialog.findViewById(R.id.rel_title);
                relativeLayout2 = (RelativeLayout) dialog.findViewById(R.id.rel_title2);
                relativeLayout3 = (RelativeLayout) dialog.findViewById(R.id.rel_title3);
                relativeLayout4 = (RelativeLayout) dialog.findViewById(R.id.rel_title4);


                relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "2 Person";
                        Buyitnow(wheelView);
                        dialog.dismiss();

                    }
                });
                relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "4 Person";
                        Buyitnow(wheelView);
                        dialog.dismiss();
                        // setLocale("ar");


                    }
                });

                relativeLayout3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "6 Person";
                        Buyitnow(wheelView);
                        dialog.dismiss();

                    }
                });
                relativeLayout4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "8 Person";
                        Buyitnow(wheelView);
                        dialog.dismiss();
                        // setLocale("ar");


                    }
                });
*/
                //  dialog.show();


            }
        });
        spine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainGroupWheel.getVisibility() == View.VISIBLE) {
                    mainGroupWheel.setVisibility(View.GONE);
                } else {
                    if (wheelView.getVisibility() == View.VISIBLE) {
                        wheelView.setVisibility(View.GONE);
                    }
                    if (drinkWheel.getVisibility() == View.VISIBLE) {
                        drinkWheel.setVisibility(View.GONE);
                    }
                    mainGroupWheel.setVisibility(View.VISIBLE);
                }

               /* Button cancel, send;
                TextView title;
                final Spinner spine, spin;
                final EditText etdate;
                RelativeLayout relativeLayout1, relativeLayout2;


             //   dialog.setContentView(R.layout.choosetype);

                relativeLayout1 = (RelativeLayout) dialog.findViewById(R.id.tv_title);
                relativeLayout2 = (RelativeLayout) dialog.findViewById(R.id.tv_title2);
                relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseperson = "Individual";
                        Buyitnow(wheelView);
                        dialog.dismiss();

                    }
                });
                relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseperson = "Group";
                        Buyitnow(wheelView);
                        dialog.dismiss();
                        // setLocale("ar");


                    }
                });
*/
                //dialog.show();


            }
        });

        softdrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drinkWheel.getVisibility() == View.VISIBLE) {
                    drinkWheel.setVisibility(View.GONE);
                } else {
                    if (wheelView.getVisibility() == View.VISIBLE) {
                        wheelView.setVisibility(View.GONE);
                    }
                    if (mainGroupWheel.getVisibility() == View.VISIBLE) {
                        mainGroupWheel.setVisibility(View.GONE);
                    }
                    drinkWheel.setVisibility(View.VISIBLE);
                }


               /* Button cancel, send;
                final TextView title;
                final Spinner spine, spin;
                final EditText etdate;
                RelativeLayout relativeLayout1, relativeLayout2;
                final RecyclerView parcelmenu;
                final TextView tvtitle1, titlemenu, tvtitle3, tvtitle4;


                // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.drinks);

                //  parcelmenu=(RecyclerView)dialog.findViewById(R.id.parcelmenu);
                // back=(TextView)dialog.findViewById(R.id.back);
                titlemenu = (TextView) dialog.findViewById(R.id.tv_date);
                tvtitle1 = (TextView) dialog.findViewById(R.id.tv_title1);
                tvtitle3 = (TextView) dialog.findViewById(R.id.tv_title3);
                tvtitle4 = (TextView) dialog.findViewById(R.id.tv_title4);

                //parcelmenu.setNestedScrollingEnabled(false);


                // final ProgressDialog dialog = new ProgressDialog(context);
                // dialog.setCancelable(false);
                //dialog.setMessage("Loding");
                //dialog.show();
                final UserID task = new UserID();
                task.setLang("en");
                titlemenu.setText(menu.get(0).getTitle());
                tvtitle1.setText(menu.get(1).getTitle());
                tvtitle3.setText(menu.get(2).getTitle());
                tvtitle4.setText(menu.get(3).getTitle());

                titlemenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks = menu.get(0).getTitle();
                        Buyitnow(wheelView);
                        dialog.dismiss();
                    }
                });

                tvtitle1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks = menu.get(1).getTitle();
                        Buyitnow(wheelView);
                        dialog.dismiss();
                    }
                });

                tvtitle3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks = menu.get(2).getTitle();
                        Buyitnow(wheelView);
                        dialog.dismiss();
                    }
                });

                tvtitle4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks = menu.get(3).getTitle();
                        Buyitnow(wheelView);
                        dialog.dismiss();
                    }
                });


                dialog.show();
*/
            }
        });


                   /* // Spinner Drop down elements
                    List<String> categories = new ArrayList<String>();
                    categories.add("Individual ");
                    categories.add("Group Order");

                    List<String> list = new ArrayList<String>();
                    list.add("2 ");
                    list.add("4");
                    list.add("6");
                    list.add("8");

                    // Creating adapter for spinner
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context.getApplicationContext(), R.layout.spinner_item, categories);
                    //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // attaching data adapter to spinner
                    spine.setAdapter(dataAdapter);
                    spine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


RF
                            if (position ==1) {
                                spin.setVisibility(View.VISIBLE);
                            }

                            if (position ==0) {
                                spin.setVisibility(View.GONE);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


// Creating adapter for spinner


                    ArrayAdapter<String> dataAdapters = new ArrayAdapter<String>(context,  R.layout.spinner_item, list);


                     dataAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    spin.setAdapter(dataAdapters);
                    spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });*/


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etdate.getText().toString().length() == 0) {
                    etdate.setText(date);

                    Toast.makeText(context, "Please Choose date first", Toast.LENGTH_LONG).show();
                } else {


                    dialog.dismiss();
                    Intent intent1 = new Intent(context, CalenderView.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("title", titles);
                    editor.putString("dates", date);
                    editor.putString("status", checkstatus);
                    editor.putString("breakfast", brakfast);
                    editor.putString("lunch", lunch);
                    editor.putString("both", both);
                    editor.putString("drink", Drinks);
                    editor.putString("time", duration);
                    editor.putString("choose1", chooseperson);
                    editor.putString("choose2", choose);
                    editor.apply();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }

                // datePicker();

                // dialog.dismiss();

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

                   /* Intent intent1=new Intent(context, CalenderView.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("title",Finish);
                    editor.putString("title",movies.get(position).getTitle());
                    editor.putString("breakfast",movies.get(position).getBreakfast());
                    editor.putString("lunch",movies.get(position).getLunch());
                    editor.putString("both",movies.get(position).getBothh());
                    editor.putString("time",movies.get(position).getDuration());
                    editor.apply();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(intent1);*/

    }

    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(context.getString(R.string._2_person));
        list.add(context.getString(R.string._4_person));
        list.add(context.getString(R.string._6_person));
        list.add(context.getString(R.string._8_person));
        return list;
    }

    private ArrayList<String> getGroupList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(context.getString(R.string.individual_string));
        list.add(context.getString(R.string.group_string));
        return list;

    }

    private ArrayList<String> getDrinkList() {
        ArrayList<String> list = new ArrayList<>();
        listDrinkId = new ArrayList<>();
        for (int i = 0; i < menu.size(); i++) {
            list.add(menu.get(i).getTitle());
            listDrinkId.add(menu.get(i).getId());
        }

        return list;
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void showDialogPicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.setMinimalDaysInFirstWeek(27);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, 1);


            }
        };
// calendar.add(Calendar.DAY_OF_MONTH, 1);
//calendar.set(2016,11,23);
// new   DatePickerDialog(LoginSuccess.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        DatePickerDialog dpDialog = new DatePickerDialog(context, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
// dpDialog.updateDate(2016,11,23);

        DatePicker datePicker = dpDialog.getDatePicker();
//datePicker.updateDate(2016,11,23);
// Date newDate = calendar.getTime();
//dpDialog.getDatePicker().setMinDate(newDate.getTime());
//datePicker.init();

        if (calendar.get(Calendar.DAY_OF_MONTH) == Calendar.SUNDAY) {
            dpDialog.setTitle(context.getString(R.string.sorry_sunday_not_accept));
            dpDialog.setMessage("not selected");
            dpDialog.show();
            //datePicker.setBackgroundColor(000000);
            //  Color.parseColor("#000000");
        } else {
            long now = System.currentTimeMillis();

            datePicker.setFirstDayOfWeek(Calendar.MONDAY);
            datePicker.setMinDate(now + (1000 * 60 * 60 * 24));
            datePicker.setMaxDate(calendar.getTimeInMillis());
            dpDialog.show();
        }


    }

    void getDrinkData(final TextView drinks, final WheelPicker drinkWheel) {
        getData = false;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = pref.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setLang(languageValue);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetSoftdrink(task).enqueue(new Callback<softdrinkModel>() {
            @Override
            public void onResponse(Call<softdrinkModel> call, Response<softdrinkModel> response) {
                if (response.isSuccessful()) {
                    // dialog.dismiss();
                    menu = response.body().getData();
                    if (menu.size() > 0) {
                        getData = true;
                        Drinks = menu.get(0).getTitle();
                        drinks.setText(Drinks);
                        try {
                            drinkId = listDrinkId.get(0);
                        } catch (Exception ae) {
                            ae.printStackTrace();
                        }

                        drinkWheel.setData(getDrinkList());
                    }


                    // GridLayoutManager latest = new GridLayoutManager(context,1);

                    // latest.setOrientation(GridLayoutManager.VERTICAL);
                    //parcelmenu.setItemAnimator(new DefaultItemAnimator());
                    // parcelmenu.setLayoutManager(latest);
                    // recyclerView.setNestedScrollingEnabled(false);
                    // parcelmenu.setAdapter(new SoftDrinkAdapter(menu, R.layout.drinks, context));


                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<softdrinkModel> call, Throwable t) {
                //dialog.dismiss();
                Toast.makeText(context, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    void spinnerData() {

    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {

        if (picker.getId() == R.id.main_wheel_left) {
            choose = data.toString();
            personText.setText(data.toString());
            picker.setVisibility(View.GONE);
        } else if (picker.getId() == R.id.drink_wheel) {
            Drinks = data.toString();
            drinkId = listDrinkId.get(position);
            drinksText.setText(data.toString());
            picker.setVisibility(View.GONE);
        } else if (picker.getId() == R.id.main_group) {
            chooseperson = data.toString();
            groupText.setText(data.toString());
            if (chooseperson == context.getString(R.string.group_string)) {
                spin.setVisibility(View.VISIBLE);
                spinTitle.setVisibility(View.VISIBLE);
            } else {
                spin.setVisibility(View.GONE);
                spinTitle.setVisibility(View.GONE);
            }
            picker.setVisibility(View.GONE);
        }


    }


}
