package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.FoodParcel.FoodParcelActivity;
import com.example.myapplication.Model.Retrofit.FoodData;
import com.example.myapplication.Model.Retrofit.Subscription;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HwAdapterSubcription extends BaseAdapter {
    private Activity context;

    private Calendar month;
    public GregorianCalendar pmonth;
    /**
     * calendar instance for previous month for getting complete view
     */
    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int mnthlength;
    String itemvalue, curentDateString;
    DateFormat df;
    JSONArray jbarrays = new JSONArray();
    private ArrayList<String> items;
    public static List<String> day_string;
    public ArrayList<HomeCollection> date_collection_arr;
    private List<Subscription> subscription;
    private int restDays = 0;
    private String gridvalue;
    private ListView listTeachers;
    private ArrayList<Dialogpojo> alCustom = new ArrayList<Dialogpojo>();
    private String Userid;
    private String resp = "";
    private Dialog dialogs;
    private String dateSelected = "";

    public HwAdapterSubcription(Activity context, GregorianCalendar monthCalendar, ArrayList<HomeCollection> date_collection_arr, List<Subscription> subscription, int restDays) {
        this.date_collection_arr = date_collection_arr;
        this.subscription = subscription;
        this.restDays = restDays;
        HwAdapterSubcription.day_string = new ArrayList<String>();
        Locale.setDefault(Locale.US);
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        this.context = context;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);

        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());
        refreshDays();

    }

    public int getCount() {
        return day_string.size();
    }

    public Object getItem(int position) {
        return day_string.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView dayView;
        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.cal_item, null);

        }

        dayView = (TextView) v.findViewById(R.id.date);
        String[] separatedTime = day_string.get(position).split("-");


        gridvalue = separatedTime[2].replaceFirst("^0*", "");
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            dayView.setTextColor(Color.parseColor("#A9A9A9"));
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            dayView.setTextColor(Color.parseColor("#A9A9A9"));
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {
            // setting curent month's days in blue color.
            dayView.setTextColor(Color.parseColor("#c4c0c0"));
        }


        if (day_string.get(position).equals(curentDateString)) {

            v.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            v.setBackgroundColor(Color.parseColor("#ffffff"));
        }


        dayView.setText(gridvalue);

        // create date string for comparison
        String date = day_string.get(position);

        if (date.length() == 1) {
            date = "0" + date;
        }
        String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }

        setEventView(v, position, dayView);

        return v;
    }

    public void refreshDays() {
        // clear items
        items.clear();
        day_string.clear();
        Locale.setDefault(Locale.US);
        pmonth = (GregorianCalendar) month.clone();
        // month start day. ie; sun, mon, etc
        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
        // finding number of weeks in current month.
        maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        // allocating maximum row number for the gridview.
        mnthlength = maxWeeknumber * 7;
        maxP = getMaxP(); // previous month maximum day 31,30....
        calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
        pmonthmaxset = (GregorianCalendar) pmonth.clone();

        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);


        for (int n = 0; n < mnthlength; n++) {

            itemvalue = df.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            day_string.add(itemvalue);

        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }


    public void setEventView(View v, int pos, TextView txt) {

        int len = HomeCollection.date_collection_arr.size();
        for (int i = 0; i < len; i++) {
            HomeCollection cal_obj = HomeCollection.date_collection_arr.get(i);
            //txt.setTextColor(Color.parseColor("#e8e3e3"));
            String date = cal_obj.date;
            int len1 = day_string.size();
            if (len1 > pos) {

                if (day_string.get(pos).equals(date)) {
                    if ((Integer.parseInt(gridvalue) > 1) && (pos < firstDay)) {


                    } else if ((Integer.parseInt(gridvalue) < 7) && (pos > 28)) {


                    } else {
                        if (cal_obj.freeze.equalsIgnoreCase("yes")) {
                            v.setBackgroundColor(Color.parseColor("#343434"));
                            v.setBackgroundResource(R.drawable.rounded_calender_green);
                            txt.setTextColor(Color.parseColor("#000000"));
                        } else {
                            v.setBackgroundColor(Color.parseColor("#343434"));
                            v.setBackgroundResource(R.drawable.rounded_calender);
                            txt.setTextColor(Color.parseColor("#000000"));
                        }

                    }

                }
            }
        }

    }

    public void getPositionList(String date, final Activity act) {
        dateSelected = date;
        int len = HomeCollection.date_collection_arr.size();
        jbarrays = new JSONArray();
        for (int j = 0; j < len; j++) {
            if (HomeCollection.date_collection_arr.get(j).date.equals(date)) {
                if (HomeCollection.date_collection_arr.get(j).freeze.equalsIgnoreCase("no") || HomeCollection.date_collection_arr.get(j).freeze.equalsIgnoreCase("")) {

                    HashMap<String, String> maplist = new HashMap<String, String>();
                    maplist.put("hnames", HomeCollection.date_collection_arr.get(j).name);
                    maplist.put("img", HomeCollection.date_collection_arr.get(j).image);
                    maplist.put("hsubject", HomeCollection.date_collection_arr.get(j).subject);
                    maplist.put("descript", HomeCollection.date_collection_arr.get(j).description);
                    JSONObject json1 = new JSONObject(maplist);
                    jbarrays.put(json1);
                } else if (HomeCollection.date_collection_arr.get(j).freeze.equalsIgnoreCase("yes")) {
                    Toast.makeText(act, act.getString(R.string.you_have_frozen), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(act, "Data is incomplete !", Toast.LENGTH_SHORT).show();
                }


            }
        }
        if (jbarrays.length() != 0) {

           /* dialogs = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);

            Window window = dialogs.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialogs.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogs.setContentView(R.layout.dialog_inform_subcription);
            listTeachers = dialogs.findViewById(R.id.list_teachers);
            ImageView imgCross = dialogs.findViewById(R.id.img_cross);
            imgCross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogs.dismiss();
                }
            });
            getFoodData(date);*/

            Intent intent = new Intent(context, FoodParcelActivity.class);
            intent.putExtra("dateSelected", dateSelected);
            intent.putExtra("date", date);
            intent.putExtra("restDays", String.valueOf(restDays));
            Bundle b = new Bundle();
            b.putString("Array", jbarrays.toString());
            intent.putExtras(b);
            intent.putParcelableArrayListExtra("subscription", (ArrayList<? extends Parcelable>) subscription);
            context.startActivity(intent);
        }

    }


    private void getFoodData(String date) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Userid = preferences.getString("userid", "");
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.data_loading));
        dialog.show();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = pref.getString(Constants.SETLANG, "en");
        final UserID task = new UserID();
        task.setUserid(Userid);
        task.setDate(date);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetFoodOfDay(task).enqueue(new Callback<FoodData>() {
            @Override
            public void onResponse(Call<FoodData> call, Response<FoodData> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    resp = response.body().toString();

                    if (!resp.isEmpty()) {
                        listTeachers.setAdapter(new DialogAdapterSubc(context, getMatchList(jbarrays + ""), response.body(), dateSelected, subscription, restDays));
                        dialogs.show();
                    }
                    //   List<Replace> menu = response.body();

                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FoodData> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<Dialogpojo> getMatchList(String detail) {
        try {
            JSONArray jsonArray = new JSONArray(detail);
            alCustom = new ArrayList<Dialogpojo>();
            alCustom.clear();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.optJSONObject(i);
                Dialogpojo pojo = new Dialogpojo();
                pojo.setTitles(jsonObject.optString("hnames"));
                pojo.setImage(jsonObject.optString("img"));
                pojo.setSubjects(jsonObject.optString("hsubject"));
                pojo.setDescripts(jsonObject.optString("descript"));

                alCustom.add(pojo);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (alCustom.size() > 1) {
            for (int i = alCustom.size() - 1; i > 0; i--) {
                alCustom.remove(i);
            }
        }
        return alCustom;
    }
}

