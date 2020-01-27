package com.example.myapplication.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.CalenderView;
import com.example.myapplication.Constants;
import com.example.myapplication.MainMenu;
import com.example.myapplication.Model.Retrofit.Datum;
import com.example.myapplication.MyAccountHome;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainPackage extends RecyclerView.Adapter<MainPackage.MovieViewHolder> {
    //private List<Result> dataList;

    private List<Datum> movies;
    private int rowLayout;
    private Context context;
    String Title, date, date_time, checkstatus, titles, lunch, brakfast, both, duration, chooseperson, choose, Drinks;
    TextView etdate;
    Integer mYear, mMonth, mDay;
    Calendar c;
    TextView spine, spin;
    Dialog dialog;
    DatePickerDialog datePickerDialog;
    RecyclerView.Adapter dataAdapters;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView viewMore;
        TextView moviesLayout;
        TextView title, choosepackage;
        TextView brakfast;
        TextView lunch;
        TextView brakfastlunch;
        ImageView image;
        LinearLayout weekly;
        LinearLayout menuView;


        public MovieViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.titles);
            brakfast = v.findViewById(R.id.breakfast);
            lunch = v.findViewById(R.id.lunch);
            brakfastlunch = v.findViewById(R.id.brakfastlunch);
            image = v.findViewById(R.id.image);
            weekly = v.findViewById(R.id.weekly);
            menuView = v.findViewById(R.id.menuview);
            choosepackage = v.findViewById(R.id.choosepackage);
            viewMore = v.findViewById(R.id.viewMore);

        }
    }

    public MainPackage(List<Datum> movies, int rowLayout, Context context) {

        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public MainPackage.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);

    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String subscribeNow = preferences.getString(Constants.SUBSCRIBENOW, "no");
        // if (subscribeNow.equals("no")) {
        if (movies.get(position).getIsactivepackage().equals("Yes")) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            String languageValue = pref.getString(Constants.SETLANG, "en");
            if (languageValue.equalsIgnoreCase("ar")) {
                holder.choosepackage.setText(context.getString(R.string.active_package_string_ar));

                Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
                holder.brakfast.setTypeface(custom_font_azab);
                holder.brakfast.setTextSize(13);
                holder.lunch.setTextSize(13);
                holder.brakfastlunch.setTextSize(13);
                holder.lunch.setTypeface(custom_font_azab);
                holder.brakfastlunch.setTypeface(custom_font_azab);
                holder.title.setTypeface(custom_font_azab);
                holder.choosepackage.setTypeface(custom_font_azab);
                holder.viewMore.setTypeface(custom_font_azab);

            } else {
                holder.choosepackage.setText(context.getString(R.string.active_package_string));
                Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/chauregular.ttf");
               /* Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "fonts/ptsansregular.ttf");
                holder.brakfast.setTypeface(custom_font_azab);
                holder.lunch.setTypeface(custom_font_azab);
                holder.brakfastlunch.setTypeface(custom_font_azab);
                holder.title.setTypeface(custom_font_azab);
                holder.choosepackage.setTypeface(custom_font_azab);
                holder.viewMore.setTypeface(custom_font_azab);*/
            }

            titles = movies.get(position).getTitle();
            brakfast = movies.get(position).getBreakfast();
            lunch = movies.get(position).getLunch();
            both = movies.get(position).getBothh();


            duration = movies.get(position).getDuration();
            holder.title.setText(movies.get(position).getTitle());
            holder.brakfast.setText(movies.get(position).getBreakfast());
            holder.lunch.setText(movies.get(position).getLunch());
            holder.brakfastlunch.setText(movies.get(position).getBothh());

            holder.brakfast.setText(context.getString(R.string.breakfast_new) + " " + movies.get(position).getBreakfast() + " OMR");
            holder.lunch.setText(context.getString(R.string.lunch_new) + " " + movies.get(position).getLunch() + " OMR");
            holder.brakfastlunch.setText(context.getString(R.string.both_new) + " " + movies.get(position).getBothh() + " OMR");


            if (languageValue.equalsIgnoreCase("ar")) {
                holder.brakfast.setText("OMR " + movies.get(position).getBreakfast() + " " + context.getString(R.string.breakfast_ar_new));
                holder.lunch.setText("OMR " + movies.get(position).getLunch() + " " + context.getString(R.string.lunch_ar_new));
                holder.brakfastlunch.setText("OMR " + movies.get(position).getBothh() + " " + context.getString(R.string.both_ar_new));
                holder.brakfast.setTextSize(13);
                holder.lunch.setTextSize(13);
                holder.brakfastlunch.setTextSize(13);

                holder.choosepackage.setText(context.getString(R.string.active_package_string_ar));

                Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
                holder.brakfast.setTypeface(custom_font_azab);
                holder.lunch.setTypeface(custom_font_azab);
                holder.brakfastlunch.setTypeface(custom_font_azab);
                holder.title.setTypeface(custom_font_azab);
                holder.choosepackage.setTypeface(custom_font_azab);
                holder.viewMore.setTypeface(custom_font_azab);


                holder.brakfast.setTextSize(14);

                holder.lunch.setTextSize(14);
                holder.brakfastlunch.setTextSize(14);

                holder.choosepackage.setTextSize(16);
                holder.viewMore.setTextSize(16);
            } else {
                holder.brakfast.setText(context.getString(R.string.breakfast_new) + " " + movies.get(position).getBreakfast() + " OMR");
                holder.lunch.setText(context.getString(R.string.lunch_new) + " " + movies.get(position).getLunch() + " OMR");
                holder.brakfastlunch.setText(context.getString(R.string.both_new) + " " + movies.get(position).getBothh() + " OMR");

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
                    ).into(holder.image);
*/


            holder.weekly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent send = new Intent(context, MyAccountHome.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("title", movies.get(position).getTitle());
                    editor.putString("breakfast", movies.get(position).getBreakfast());
                    editor.putString("lunch", movies.get(position).getLunch());
                    editor.putString("both", movies.get(position).getBothh());
                    editor.putString("time", movies.get(position).getDuration());
                    editor.apply();
                    send.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(send);

                }
            });

            /*holder.weekly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Finish = "done";
                    final Dialog dialog;
                    Button cancel, send;
                    final LinearLayout softDrinks;
                    TextView title, drinks;
                    chooseperson = "Indivisual";
                    choose = "2 Person";

                    RadioButton radiobrakfast, radiolunch, radioboth;
                    RadioGroup radioGroup;

                    dialog = new Dialog(context);
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.buyitnow);


                    radioGroup = (RadioGroup) dialog.findViewById(R.id.radio_group);
                    radiobrakfast = (RadioButton) dialog.findViewById(R.id.radiobreakfast);
                    radiolunch = (RadioButton) dialog.findViewById(R.id.radiolunch);
                    radioboth = (RadioButton) dialog.findViewById(R.id.radioboth);
                    cancel = (Button) dialog.findViewById(R.id.cancel_button);
                    send = (Button) dialog.findViewById(R.id.send_button);
                    title = (TextView) dialog.findViewById(R.id.title);
                    drinks = (TextView) dialog.findViewById(R.id.drinks);
                    spine = (TextView) dialog.findViewById(R.id.spine);
                    spin = (TextView) dialog.findViewById(R.id.spin);
                    softDrinks = (LinearLayout) dialog.findViewById(R.id.soft);
                    etdate = (TextView) dialog.findViewById(R.id.etdate);
                    etdate.setText(date);
                    drinks.setText(Drinks);
                    spine.setText(chooseperson);
                    spin.setText(choose);
                    if (checkstatus == "lunch") {
                        radiolunch.setChecked(true);
                    }
                    if (checkstatus == "both") {
                        radioboth.setChecked(true);
                    } else {
                        radiobrakfast.setChecked(true);
                    }

                    if (Drinks == "Coca-cola") {
                        softDrinks.setVisibility(View.VISIBLE);
                    }
                    if (chooseperson == "Group") {
                        spin.setVisibility(View.VISIBLE);
                    } else {
                        spin.setVisibility(View.GONE);
                    }

                    radiobrakfast.setChecked(true);

                    if (radiobrakfast.isChecked()) {
                        checkstatus = "breakfast";
                        if (Drinks == "Coca-cola||Pepsi") {
                            softDrinks.setVisibility(View.VISIBLE);
                        }
                        SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = sendintent.edit();
                        editor.putString("status", checkstatus);
                        editor.apply();

                        Toast.makeText(context, "breakfast is check", Toast.LENGTH_LONG).show();
                    }

                    radiobrakfast.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkstatus = "breakfast";
                            softDrinks.setVisibility(View.GONE);
                            SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sendintent.edit();
                            editor.putString("status", checkstatus);
                            editor.apply();
                            Toast.makeText(context, "breakfast is check", Toast.LENGTH_LONG).show();

                        }
                    });

                    radiolunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkstatus = "lunch";
                            softDrinks.setVisibility(View.VISIBLE);
                            SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sendintent.edit();
                            editor.putString("status", checkstatus);
                            editor.apply();


                            Toast.makeText(context, "lunch is check", Toast.LENGTH_LONG).show();

                        }
                    });

                    radioboth.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkstatus = "both";

                            softDrinks.setVisibility(View.VISIBLE);
                            SharedPreferences sendintent = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sendintent.edit();
                            editor.putString("status", checkstatus);
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

                    final Calendar calendar = Calendar.getInstance();
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
                            // datePicker();
                            c = Calendar.getInstance();
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);


                            datePickerDialog = new DatePickerDialog(context, R.style.AppTheme,

                                    new DatePickerDialog.OnDateSetListener() {


                                        @Override
                                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                            date_time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                            date = date_time;

                                            etdate.setText(date);
                                            //Intent intent= new Intent(CalenderView.this,CheckoutPage.class);
                                            // startActivity(intent);
                                            //*************Call Time Picker Here ********************
                                            // tiemPicker();
                                            Toast.makeText(context, "" + date, Toast.LENGTH_LONG).show();

                                        }
                                    }, mYear, mMonth, mDay);


                            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                            datePickerDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            datePickerDialog.show();
                        }
                        // etdate.setText(date);

                    });

                    title.setText(movies.get(position).getTitle());
                    spin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button cancel, send;
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
                                    Buyitnow();
                                    dialog.dismiss();

                                }
                            });
                            relativeLayout2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    choose = "4 Person";
                                    Buyitnow();
                                    dialog.dismiss();
                                    // setLocale("ar");


                                }
                            });

                            relativeLayout3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    choose = "6 Person";
                                    Buyitnow();
                                    dialog.dismiss();

                                }
                            });
                            relativeLayout4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    choose = "8 Person";
                                    Buyitnow();
                                    dialog.dismiss();
                                    // setLocale("ar");


                                }
                            });

                            dialog.show();


                        }
                    });
                    spine.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Button cancel, send;
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
                                    chooseperson = "Indivisual";
                                    Buyitnow();
                                    dialog.dismiss();

                                }
                            });
                            relativeLayout2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    chooseperson = "Group";
                                    Buyitnow();
                                    dialog.dismiss();
                                    // setLocale("ar");


                                }
                            });

                            dialog.show();


                        }
                    });

                    softDrinks.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button cancel, send;
                            TextView title;
                            final Spinner spine, spin;
                            final EditText etdate;
                            RelativeLayout relativeLayout1, relativeLayout2;

                            dialog.setContentView(R.layout.activity_softdrinks);

                            relativeLayout1 = (RelativeLayout) dialog.findViewById(R.id.tv_title);
                            relativeLayout2 = (RelativeLayout) dialog.findViewById(R.id.tv_title2);
                            relativeLayout1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Drinks = "Coca-Cola";
                                    Buyitnow();
                                    dialog.dismiss();

                                }
                            });
                            relativeLayout2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Drinks = "Pepsi";
                                    Buyitnow();
                                    dialog.dismiss();
                                    // setLocale("ar");


                                }
                            });

                            dialog.show();


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


                   /* cancel.setOnClickListener(new View.OnClickListener() {
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
                                editor.putString("title", movies.get(position).getTitle());
                                editor.putString("dates", date);
                                editor.putString("status", checkstatus);
                                editor.putString("breakfast", movies.get(position).getBreakfast());
                                editor.putString("lunch", movies.get(position).getLunch());
                                editor.putString("both", movies.get(position).getBothh());
                                editor.putString("time", movies.get(position).getDuration());
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

            //}
            //});
        }
        //   }


        //subscription New

      /*  if (subscribeNow.equals("yes")) {
            titles = movies.get(position).getTitle();
            brakfast = movies.get(position).getBreakfast();
            lunch = movies.get(position).getLunch();
            both = movies.get(position).getBothh();
            duration = movies.get(position).getDuration();
            holder.title.setText(movies.get(position).getTitle());
            holder.brakfast.setText(movies.get(position).getBreakfast());
            holder.lunch.setText(movies.get(position).getLunch());
            holder.brakfastlunch.setText(movies.get(position).getBothh());
            Glide.with(context)
                    .load(movies.get(position).getImage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);


            holder.weekly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent send = new Intent(context, MyAccountHome.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("title", movies.get(position).getTitle());
                    editor.putString("breakfast", movies.get(position).getBreakfast());
                    editor.putString("lunch", movies.get(position).getLunch());
                    editor.putString("both", movies.get(position).getBothh());
                    editor.putString("time", movies.get(position).getDuration());
                    editor.apply();
                    send.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(send);

                }
            });
        }
*/
        if (movies.get(position).getIsactivepackage().equals("No")) {
            titles = movies.get(position).getTitle();
            brakfast = movies.get(position).getBreakfast();
            lunch = movies.get(position).getLunch();
            both = movies.get(position).getBothh();
            duration = movies.get(position).getDuration();
            holder.title.setText(movies.get(position).getTitle());
            holder.brakfast.setText(movies.get(position).getBreakfast());
            holder.lunch.setText(movies.get(position).getLunch());
            holder.brakfastlunch.setText(movies.get(position).getBothh());
            String languageValue = preferences.getString(Constants.SETLANG, "en");
            if (languageValue.equalsIgnoreCase("ar")) {
                holder.brakfast.setText("OMR " + movies.get(position).getBreakfast() + " " + context.getString(R.string.breakfast_ar));
                holder.lunch.setText("OMR " + movies.get(position).getLunch() + " " + context.getString(R.string.lunch_ar));
                holder.brakfastlunch.setText("OMR " + movies.get(position).getBothh() + " " + context.getString(R.string.both_ar));


                holder.choosepackage.setText(context.getString(R.string.active_package_string_ar));

                Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
                holder.brakfast.setTypeface(custom_font_azab);
                holder.lunch.setTypeface(custom_font_azab);
                holder.brakfastlunch.setTypeface(custom_font_azab);
                holder.title.setTypeface(custom_font_azab);
                holder.choosepackage.setTypeface(custom_font_azab);
                holder.viewMore.setTypeface(custom_font_azab);


            } else {
                holder.brakfast.setText(context.getString(R.string.breakfast) + " " + movies.get(position).getBreakfast() + " OMR");
                holder.lunch.setText(context.getString(R.string.lunch) + " " + movies.get(position).getLunch() + " OMR");
                holder.brakfastlunch.setText(context.getString(R.string.both) + " " + movies.get(position).getBothh() + " OMR");

            }

            Glide.with(context)
                    .load(movies.get(position).getImage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);

          /*  Glide.with(context)
                    .load(movies.get(position).getImage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(holder.image);

*/
            holder.weekly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String languageValue = preferences.getString(Constants.SETLANG, "en");
                    if (languageValue.equalsIgnoreCase("ar")) {
                        Toast.makeText(context, context.getString(R.string.sorry_already_have_ar), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(context, context.getString(R.string.sorry_already_have), Toast.LENGTH_LONG).show();

                    }

                }
            });
        }

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

    public void Buyitnow() {
        String Finish = "done";
        final Dialog dialog;
        Button cancel, send;
        TextView title, drinks;
        final LinearLayout softdrink;

        RadioButton radiobrakfast, radiolunch, radioboth;
        RadioGroup radioGroup;

        dialog = new Dialog(context);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.buyitnow);


        radioGroup = dialog.findViewById(R.id.radio_group);
        radiobrakfast = dialog.findViewById(R.id.radiobreakfast);
        radiolunch = dialog.findViewById(R.id.radiolunch);
        radioboth = dialog.findViewById(R.id.radioboth);
        cancel = dialog.findViewById(R.id.cancel_button);
        send = dialog.findViewById(R.id.send_button);
        title = dialog.findViewById(R.id.title);
        drinks = dialog.findViewById(R.id.drinks);
        spine = dialog.findViewById(R.id.spine);
        spin = dialog.findViewById(R.id.spin);
        softdrink = dialog.findViewById(R.id.soft);
        etdate = dialog.findViewById(R.id.etdate);
        etdate.setText(date);
        drinks.setText(Drinks);
        spine.setText(chooseperson);
        spin.setText(choose);
        if (checkstatus == "lunch") {
            radiolunch.setChecked(true);
        }
        if (checkstatus == "both") {
            radioboth.setChecked(true);
        } else {
            radiobrakfast.setChecked(true);
        }
        if (Drinks == context.getString(R.string.cola_string)) {
            softdrink.setVisibility(View.VISIBLE);
        }

        if (chooseperson == "Group") {
            spin.setVisibility(View.VISIBLE);
        } else {
            spin.setVisibility(View.GONE);
        }
        if (radiobrakfast.isChecked()) {
            checkstatus = "breakfast";
            //  if (Drinks == "Coca-cola||Pepsi") {
            if (Drinks == context.getString(R.string.pepsi_string)) {
                softdrink.setVisibility(View.VISIBLE);
            }
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
                // datePicker();
                c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(context, R.style.AppTheme,

                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date_time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                date = date_time;
                                etdate.setText(date);
                                //Intent intent= new Intent(CalenderView.this,CheckoutPage.class);
                                // startActivity(intent);
                                //*************Call Time Picker Here ********************
                                // tiemPicker();
                                Toast.makeText(context, "" + date, Toast.LENGTH_LONG).show();
                            }
                        }, mYear, mMonth, mDay);


                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                datePickerDialog.show();
            }
            // etdate.setText(date);

        });

        title.setText(titles);

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button cancel, send;
                TextView title;
                final Spinner spine, spin;
                final EditText etdate;
                RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;
                dialog.setContentView(R.layout.choosegroup);

                relativeLayout1 = dialog.findViewById(R.id.rel_title);
                relativeLayout2 = dialog.findViewById(R.id.rel_title2);
                relativeLayout3 = dialog.findViewById(R.id.rel_title3);
                relativeLayout4 = dialog.findViewById(R.id.rel_title4);
                relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "2 Person";
                        Buyitnow();
                        dialog.dismiss();

                    }
                });
                relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "4 Person";
                        Buyitnow();
                        dialog.dismiss();
                        // setLocale("ar");


                    }
                });

                relativeLayout3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "6 Person";
                        Buyitnow();
                        dialog.dismiss();

                    }
                });
                relativeLayout4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choose = "8 Person";
                        Buyitnow();
                        dialog.dismiss();
                        // setLocale("ar");
                    }
                });

                dialog.show();


            }
        });
        spine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button cancel, send;
                TextView title;
                final Spinner spine, spin;
                final EditText etdate;
                RelativeLayout relativeLayout1, relativeLayout2;

                dialog.setContentView(R.layout.choosetype);

                relativeLayout1 = dialog.findViewById(R.id.tv_title);
                relativeLayout2 = dialog.findViewById(R.id.tv_title2);
                relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseperson = "Indivisual";
                        Buyitnow();
                        dialog.dismiss();

                    }
                });
                relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseperson = "Group";
                        Buyitnow();
                        dialog.dismiss();
                        // setLocale("ar");


                    }
                });

                dialog.show();


            }
        });

        softdrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button cancel, send;
                TextView title;
                final Spinner spine, spin;
                final EditText etdate;
                RelativeLayout relativeLayout1, relativeLayout2;

                dialog.setContentView(R.layout.activity_softdrinks);

                relativeLayout1 = dialog.findViewById(R.id.tv_title);
                relativeLayout2 = dialog.findViewById(R.id.tv_title2);
                relativeLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks = context.getString(R.string.cola_string);
                        Buyitnow();
                        dialog.dismiss();

                    }
                });
                relativeLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Drinks = context.getString(R.string.pepsi_string);
                        Buyitnow();
                        dialog.dismiss();
                        // setLocale("ar");


                    }
                });

                dialog.show();


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


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

