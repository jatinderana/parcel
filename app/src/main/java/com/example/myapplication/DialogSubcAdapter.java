package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Adapter.ShowCardAdapter;
import com.example.myapplication.AddParcel.AddParcelCafe;
import com.example.myapplication.FoodParcel.FoodParcelActivity;
import com.example.myapplication.Model.Retrofit.CheckoutModel;
import com.example.myapplication.Model.Retrofit.CyberData;
import com.example.myapplication.Model.Retrofit.CyberResponse;
import com.example.myapplication.Model.Retrofit.FoodData;
import com.example.myapplication.Model.Retrofit.GatewayModel;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.Model.Retrofit.PackageResponse;
import com.example.myapplication.Model.Retrofit.PasswordChangeModel;
import com.example.myapplication.Model.Retrofit.PaypalCancel;
import com.example.myapplication.Model.Retrofit.Subscription;
import com.example.myapplication.Model.Retrofit.TermsConditionModel;
import com.example.myapplication.Model.Retrofit.UpdatePackagePojo;
import com.example.myapplication.Model.Retrofit.checkout;
import com.example.myapplication.Model.Retrofit.paypalmodel;
import com.example.myapplication.Retrofit.ApiServices;
import com.example.myapplication.Retrofit.ApiUrl;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.makeText;


public class DialogSubcAdapter extends RecyclerView.Adapter<DialogSubcAdapter.MyHolder>  {
    private final Context context;
    private final List<FoodData.Datum> data;
    private FoodData resp;
    private int subscription_item;
    private String dateSelected;
    private List<Subscription> subscription;
    private List<FoodData.Uptdatum> uptdata;
    DialogInterface listener;
    private int restDays;
    private String breakfastPrice = "1";
    private String lunchPrice = "1";
    private String durationValue = "1";


    public DialogSubcAdapter(Context context, List<FoodData.Datum> data, FoodData resp, int subscription_item, String dateSelected, List<Subscription> subscription, List<FoodData.Uptdatum> uptdata, int restDays) {
        this.context = context;
        this.data = data;
        this.resp = resp;
        this.subscription_item = subscription_item;
        this.dateSelected = dateSelected;
        this.subscription = subscription;
        this.uptdata = uptdata;
        listener = (FoodParcelActivity) context;
        this.restDays = restDays;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(subscription_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        FoodData.Uptdatum updateData = null;
        List<FoodData.Packageinfo> packageinfo = new ArrayList<>();
        packageinfo = resp.getPackageinfo();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {


            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");

            myHolder.replaced.setTypeface(custom_font_azab);
            myHolder.tv_name.setTypeface(custom_font_azab);
            myHolder.tvNameLunch.setTypeface(custom_font_azab);
            myHolder.doubleTTV.setTypeface(custom_font_azab);
            myHolder.parcelLTV.setTypeface(custom_font_azab);
            myHolder.replaceLTV.setTypeface(custom_font_azab);
            myHolder.doubleTLTV.setTypeface(custom_font_azab);
            myHolder.doubleTV.setTypeface(custom_font_azab);
            myHolder.parcelCafeLunch.setTypeface(custom_font_azab);
            myHolder.parcelCafe.setTypeface(custom_font_azab);
            myHolder.mealTV.setTypeface(custom_font_azab);
            myHolder.replacedLunch.setTypeface(custom_font_azab);
            myHolder.doubleTVLunch.setTypeface(custom_font_azab);

        }

        final FoodData.Datum foodData = data.get(i);
        if (packageinfo.size() > 0) {
            breakfastPrice = packageinfo.get(0).getBreakfast();
            lunchPrice = packageinfo.get(0).getLunch();
            durationValue = packageinfo.get(0).getDuration();
        }

        if (uptdata.size() == data.size()) {
            updateData = uptdata.get(i);
        } else {
            if (uptdata.size() > 0) {
                updateData = uptdata.get(0);
            }
        }
        if (myHolder.languageValue.equalsIgnoreCase("ar")) {
            myHolder.replaced.setText(context.getString(R.string.replace_string_ar));
            myHolder.doubleTV.setText(context.getString(R.string.double_meal_string_ar));
            myHolder.parcelCafe.setText(context.getString(R.string.parcel_cafe_string));
            myHolder.mealTV.setText(context.getString(R.string.breakfast_string_ar));
            myHolder.doubleTVLunch.setText(context.getString(R.string.double_meal_string_ar));
            myHolder.parcelCafeLunch.setText(context.getString(R.string.parcel_cafe_string_ar));
            myHolder.replacedLunch.setText(context.getString(R.string.replace_string_ar));


            myHolder.replaceLTV.setText(context.getString(R.string.replaced_string));
            myHolder.doubleTLTV.setText(context.getString(R.string.replaced_string));
        } else {
            myHolder.replaced.setText(context.getString(R.string.replace_string));
            myHolder.doubleTV.setText(context.getString(R.string.double_meal_string));
            myHolder.parcelCafe.setText(context.getString(R.string.add_parcel_cafe));
            myHolder.mealTV.setText(context.getString(R.string.breakfast_string));
            myHolder.replacedLunch.setText(context.getString(R.string.replace_string));

            myHolder.doubleTVLunch.setText(context.getString(R.string.double_meal_string));
            myHolder.parcelCafeLunch.setText(context.getString(R.string.add_parcel_cafe));


        }

        if (resp.getGiftdetails().size() > 0) {
            myHolder.parcelLunch.setAlpha(0.4f);
            myHolder.doubleLunch.setAlpha(0.4f);
            myHolder.replaceLunch.setAlpha(0.4f);
            myHolder.replaceBIV.setAlpha(0.4f);
            myHolder.doubleBIV.setAlpha(0.4f);
            myHolder.parcelBIV.setAlpha(0.4f);

            myHolder.doubleLL.setOnClickListener(null);
            myHolder.replaceLL.setOnClickListener(null);
            myHolder.parcelLL.setOnClickListener(null);
            myHolder.doubleLL.setClickable(false);
            myHolder.replaceLL.setClickable(false);
            myHolder.parcelLL.setClickable(false);
            myHolder.replaced.setClickable(false);

            myHolder.doubleLLLunch.setOnClickListener(null);
            myHolder.replaceLLLunch.setOnClickListener(null);
            myHolder.parcelLLLunch.setOnClickListener(null);
        }


        if (resp.getDuration().equalsIgnoreCase(context.getString(R.string.breakfast))) {
            myHolder.cardBreakfast.setVisibility(View.VISIBLE);
            myHolder.cardLunch.setVisibility(View.GONE);
            Glide.with(context)
                    .load(foodData.getBimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(myHolder.imageView);
/*
            Glide.with(context)
                    .load(foodData.getBimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(myHolder.imageView);*/

            if (myHolder.languageValue.equalsIgnoreCase("ar")) {
                myHolder.tv_name.setText(foodData.getBdescription().trim());
            } else {
                myHolder.tv_name.setText(foodData.getBdescription().trim());
            }

/*

            if (!foodData.getLdescription().equals("")) {
                myHolder.cardLunch.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(foodData.getLimage())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(myHolder.imageviewLunch);

                myHolder.tvNameLunch.setText(foodData.getLdescription());
            }
*/


        } else if (resp.getDuration().equalsIgnoreCase("lunch")) {
            myHolder.cardBreakfast.setVisibility(View.GONE);
            myHolder.cardLunch.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(foodData.getLimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(myHolder.imageviewLunch);
/*
            Glide.with(context)
                    .load(foodData.getLimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(myHolder.imageviewLunch);*/

            if (myHolder.languageValue.equalsIgnoreCase("ar")) {
                myHolder.tvNameLunch.setText(foodData.getLdescription().trim());
            } else {
                myHolder.tvNameLunch.setText(foodData.getLdescription().trim());
            }

        } else if (resp.getDuration().equalsIgnoreCase("both")) {
            myHolder.cardBreakfast.setVisibility(View.VISIBLE);
            myHolder.cardLunch.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(foodData.getLimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(myHolder.imageviewLunch);
/*
            Glide.with(context)
                    .load(foodData.getLimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(myHolder.imageviewLunch);*/

            if (myHolder.languageValue.equalsIgnoreCase("ar")) {
                myHolder.tvNameLunch.setText(foodData.getLdescription().trim());
            } else {
                myHolder.tvNameLunch.setText(foodData.getLdescription().trim());
            }
            Glide.with(context)
                    .load(foodData.getBimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(myHolder.imageView);

         /*   Glide.with(context)
                    .load(foodData.getBimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(myHolder.imageView);*/
            if (myHolder.languageValue.equalsIgnoreCase("ar")) {
                myHolder.tv_name.setText(foodData.getBdescription().trim());
            } else {
                myHolder.tv_name.setText(foodData.getBdescription().trim());
            }

        }

        if (resp.getGiftdetails().size() == 0) {
            myHolder.replaced.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(context, MySubcriptionMenu.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("replaceLimit", resp.getReplacelimit());
                    intent1.putExtra("selectedData", dateSelected);
                    intent1.putExtra("duration", "Breakfast");
                    context.startActivity(intent1);
                }
            });

            myHolder.replaceLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent(context, MySubcriptionMenu.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("replaceLimit", resp.getReplacelimit());
                    intent1.putExtra("selectedData", dateSelected);
                    intent1.putExtra("duration", "Breakfast");
                    context.startActivity(intent1);
                }
            });

            myHolder.replaceLLLunch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent(context, MySubcriptionMenu.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("replaceLimit", resp.getReplacelimit());
                    intent1.putExtra("selectedData", dateSelected);
                    intent1.putExtra("duration", "Lunch");
                    context.startActivity(intent1);
                }
            });
            final FoodData.Uptdatum finalUpdateData1 = updateData;
            myHolder.doubleLLLunch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSureDialog(subscription, "Lunch", foodData.getId(), finalUpdateData1);

                }
            });
            final FoodData.Uptdatum finalUpdateData = updateData;
            myHolder.doubleLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showSureDialog(subscription, "Breakfast", foodData.getId(), finalUpdateData);

                }
            });
            myHolder.parcelLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(context, AddParcelCafe.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("replaceLimit", resp.getReplacelimit());
                    intent1.putExtra("selectedData", dateSelected);
                    intent1.putExtra("restDays", String.valueOf(restDays));
                    intent1.putExtra("duration", "Breakfast");
                    context.startActivity(intent1);
                }
            });

            myHolder.parcelLLLunch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(context, AddParcelCafe.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.putExtra("replaceLimit", resp.getReplacelimit());
                    intent1.putExtra("selectedData", dateSelected);
                    intent1.putExtra("restDays", restDays);
                    intent1.putExtra("duration", "Lunch");
                    context.startActivity(intent1);
                }
            });


        }


        if (updateData.getBdayreplace().isEmpty() || updateData.getBdayreplace().equals("0")) {
            myHolder.replaceRL.setVisibility(View.INVISIBLE);
        } else {
            myHolder.replaceRL.setVisibility(View.VISIBLE);
        }
        if (updateData.getBparcelid().isEmpty() || updateData.getBparcelid().equals("0")) {
            myHolder.parcelRL.setVisibility(View.INVISIBLE);
        } else {
            myHolder.parcelRL.setVisibility(View.VISIBLE);
        }
        if (resp.getCanuptbreakfast().isEmpty() || resp.getCanuptbreakfast().equalsIgnoreCase("yes")) {
            /*myHolder.parcelLunch.setAlpha(0.4f);
            myHolder.doubleLunch.setAlpha(0.4f);
            myHolder.replaceLunch.setAlpha(0.4f);
            myHolder.replaceBIV.setAlpha(0.4f);
            myHolder.doubleBIV.setAlpha(0.4f);
            myHolder.parcelBIV.setAlpha(0.4f);*/
        } else {

            myHolder.replaceBIV.setAlpha(0.4f);
            myHolder.doubleBIV.setAlpha(0.4f);
            myHolder.parcelBIV.setAlpha(0.4f);
            myHolder.replaceBIV.setClickable(false);
            myHolder.doubleBIV.setClickable(false);
            myHolder.parcelBIV.setClickable(false);
            myHolder.doubleLL.setClickable(false);
            myHolder.replaceLL.setClickable(false);
            myHolder.parcelLL.setClickable(false);
            myHolder.doubleLL.setClickable(false);
            myHolder.replaceLL.setClickable(false);
            myHolder.parcelLL.setClickable(false);
            myHolder.replaced.setClickable(false);

            myHolder.replaceBIV.setOnClickListener(null);
            myHolder.doubleBIV.setOnClickListener(null);
            myHolder.parcelBIV.setOnClickListener(null);

            myHolder.doubleLL.setOnClickListener(null);
            myHolder.replaceLL.setOnClickListener(null);
            myHolder.parcelLL.setOnClickListener(null);


        }
        if (updateData.getBparcelid().isEmpty() || updateData.getBparcelid().equals("0")) {
            myHolder.parcelRL.setVisibility(View.INVISIBLE);
        } else {
            myHolder.parcelRL.setVisibility(View.VISIBLE);
        }

        try {
           /* if (updateData.getBdoubleqtyTemp().isEmpty() || updateData.getBdoubleqtyTemp().equals("0")) {
                myHolder.quantityRL.setVisibility(View.INVISIBLE);
            } else {
                myHolder.quantityRL.setVisibility(View.VISIBLE);
                myHolder.doubleTTV.setText(context.getString(R.string.qaunt_String) + updateData.getBdoubleqty());
            }
            */

            if (updateData.getBdoubleqty().isEmpty() || updateData.getBdoubleqty().equalsIgnoreCase("0")) {
                myHolder.quantityRL.setVisibility(View.INVISIBLE);
            } else {
                myHolder.quantityRL.setVisibility(View.VISIBLE);
                myHolder.doubleTTV.setText(context.getString(R.string.qaunt_String) + updateData.getBdoubleqty());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (updateData.getLdayreplace().isEmpty() || updateData.getLdayreplace().equals("0")) {
            myHolder.replaceLRL.setVisibility(View.INVISIBLE);
        } else {
            myHolder.replaceLRL.setVisibility(View.VISIBLE);
        }
        if (updateData.getLparcelid().isEmpty() || updateData.getLdayreplace().equals("0")) {
            myHolder.parcelLRL.setVisibility(View.INVISIBLE);
        } else {
            myHolder.parcelLRL.setVisibility(View.VISIBLE);
        }


        if (resp.getCanuptlunch().isEmpty() || resp.getCanuptlunch().equalsIgnoreCase("yes")) {
       /*     myHolder.replaceLRL.setVisibility(View.INVISIBLE);
            myHolder.parcelLRL.setVisibility(View.INVISIBLE);
            myHolder.quantityLRL.setVisibility(View.VISIBLE);*/
        } else {
            myHolder.parcelLunch.setAlpha(0.4f);
            myHolder.doubleLunch.setAlpha(0.4f);
            myHolder.replaceLunch.setAlpha(0.4f);
            myHolder.parcelLunch.setOnClickListener(null);
            myHolder.doubleLunch.setOnClickListener(null);
            myHolder.replaceLunch.setOnClickListener(null);
            myHolder.parcelLunch.setClickable(false);
            myHolder.doubleLunch.setClickable(false);
            myHolder.replaceLunch.setClickable(false);

            myHolder.doubleLLLunch.setClickable(false);
            myHolder.replaceLLLunch.setClickable(false);
            myHolder.parcelLLLunch.setClickable(false);
            myHolder.doubleLLLunch.setOnClickListener(null);
            myHolder.replaceLLLunch.setOnClickListener(null);
            myHolder.parcelLLLunch.setOnClickListener(null);
        }
        try {
           /* if (updateData.getLdoubleqtyTemp().isEmpty() || updateData.getLdoubleqtyTemp().equals("0")) {
                myHolder.quantityLRL.setVisibility(View.INVISIBLE);
            } else {
                myHolder.quantityLRL.setVisibility(View.VISIBLE);
                if (myHolder.languageValue.equalsIgnoreCase("ar")) {
                    myHolder.doubleTLTV.setText(context.getString(R.string.qaunt_String_ar) + updateData.getLdoubleqtyTemp());
                } else {
                    myHolder.doubleTLTV.setText(context.getString(R.string.qaunt_String) + updateData.getLdoubleqtyTemp());
                }

            }*/

            if (updateData.getLdoubleqty().isEmpty() || updateData.getLdoubleqty().equalsIgnoreCase("0")) {
                myHolder.quantityLRL.setVisibility(View.INVISIBLE);
            } else {
                myHolder.quantityLRL.setVisibility(View.VISIBLE);
                if (myHolder.languageValue.equalsIgnoreCase("ar")) {
                    myHolder.doubleTLTV.setText(context.getString(R.string.qaunt_String_ar) + updateData.getLdoubleqty());
                } else {
                    myHolder.doubleTLTV.setText(context.getString(R.string.qaunt_String) + updateData.getLdoubleqty());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* if (updateData.getLdayreplace().isEmpty()) {
            myHolder.quantityRL.setVisibility(View.INVISIBLE);
        } else {
            myHolder.quantityRL.setVisibility(View.VISIBLE);
        }*/


        if (resp.getSoftdrink() != null) {
            if (!resp.getSoftdrink().isEmpty()) {
                if (myHolder.languageValue.equalsIgnoreCase("ar")) {
                    myHolder.tvNameLunch.setText(foodData.getLdescription().trim() + " " + " ( "+resp.getSoftdrink().trim()+" )");
                } else {
                    myHolder.tvNameLunch.setText(foodData.getLdescription().trim() + " " + " ( "+resp.getSoftdrink().trim()+" )");
                }

            }
        }

    }

    private void sendDoubleApi(final String duration, String id, final FoodData.Uptdatum finalUpdateData1) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String Userid = preferences.getString("userid", "");
        final UpdatePackagePojo task = new UpdatePackagePojo();
        task.setUserid(Userid);
        task.setPdate(dateSelected);
        task.setUptype("Double");
        if (duration.equalsIgnoreCase("breakfast")) {
            task.setBdayreplace("");
            task.setBdoubleqty(id);
            task.setLdayreplace("");
            task.setLdoubleqty("");
        } else {
            task.setBdayreplace("");
            task.setBdoubleqty("");
            task.setLdayreplace("");
            task.setLdoubleqty(id);
        }
        task.setFoodtype(duration);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.UpdatePackage(task).enqueue(new Callback<PackageResponse>() {
            private String resposeData;

            @Override
            public void onResponse(Call<PackageResponse> call, Response<PackageResponse> response) {
                if (response.isSuccessful()) {
                    resposeData = response.body().toString();

                    if (response.body().getData().size() > 0) {
                        listener.paypalListener(Double.parseDouble(response.body().getData().get(0).getDoubleprice()), "", response.body().getData().get(0).getCheckId(), response.body().getData().get(0).getFoodtype(), finalUpdateData1);
                    }

                    //   List<Replace> menu = response.body();

                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PackageResponse> call, Throwable t) {
                Toast.makeText(context, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageviewLunch;
        TextView replaced, tv_name, tvNameLunch, doubleTTV, parcelLTV, replaceLTV, doubleTLTV, doubleTV, parcelCafeLunch, parcelCafe, mealTV, replacedLunch, doubleTVLunch;
        LinearLayout replaceLL, replaceLLLunch, doubleLL, doubleLLLunch, parcelLL, parcelLLLunch;
        CardView cardBreakfast, cardLunch;
        RelativeLayout parcelRL, quantityRL, replaceRL, replaceLRL, quantityLRL, parcelLRL;
        String languageValue;
        ImageView parcelBIV, doubleBIV, replaceBIV, parcelLunch, doubleLunch, replaceLunch;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            replaced = itemView.findViewById(R.id.replaced);
            tv_name = itemView.findViewById(R.id.tv_name);
            replaceLL = itemView.findViewById(R.id.replaceLL);
            replaceLLLunch = itemView.findViewById(R.id.replaceLLLunch);
            cardBreakfast = itemView.findViewById(R.id.cardBreakfast);
            cardLunch = itemView.findViewById(R.id.cardLunch);
            imageviewLunch = itemView.findViewById(R.id.imageviewLunch);
            tvNameLunch = itemView.findViewById(R.id.tv_nameLunch);
            doubleLL = itemView.findViewById(R.id.doubleLL);
            parcelLL = itemView.findViewById(R.id.parcelLL);
            parcelLLLunch = itemView.findViewById(R.id.parcelLLLunch);
            doubleLLLunch = itemView.findViewById(R.id.doubleLLLunch);
            parcelRL = itemView.findViewById(R.id.parcelRL);
            quantityRL = itemView.findViewById(R.id.quantityRL);
            replaceRL = itemView.findViewById(R.id.replaceRL);
            replaceLRL = itemView.findViewById(R.id.replaceLRL);
            quantityLRL = itemView.findViewById(R.id.quantityLRL);
            parcelLRL = itemView.findViewById(R.id.parcelLRL);
            doubleTTV = itemView.findViewById(R.id.doubleTTV);
            doubleTLTV = itemView.findViewById(R.id.doubleTLTV);
            parcelBIV = itemView.findViewById(R.id.parcelBIV);
            doubleBIV = itemView.findViewById(R.id.doubleBIV);
            replaceBIV = itemView.findViewById(R.id.replaceBIV);
            parcelLunch = itemView.findViewById(R.id.parcelLunch);
            doubleLunch = itemView.findViewById(R.id.doubleLunch);
            replaceLunch = itemView.findViewById(R.id.replaceLunch);
            doubleTV = itemView.findViewById(R.id.doubleTV);
            mealTV = itemView.findViewById(R.id.mealTV);
            parcelCafe = itemView.findViewById(R.id.parcelCafe);
            replacedLunch = itemView.findViewById(R.id.replacedLunch);
            doubleTVLunch = itemView.findViewById(R.id.doubleTVLunch);
            parcelCafeLunch = itemView.findViewById(R.id.parcelCafeLunch);
            replaceLTV = itemView.findViewById(R.id.replaceLTV);
            parcelLTV = itemView.findViewById(R.id.parcelLTV);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            languageValue = preferences.getString(Constants.SETLANG, "en");

        }
    }

    public static String lastId = "0";
    private void showSureDialog(List<Subscription> subscription, final String duration, final String foodId, final FoodData.Uptdatum finalUpdateData1) {
        String id = "0";
      /*  if(response.body().getData().size()>0)
        {
            id = response.body().getData().get(0).getId();
        }*/
        Double price = 0.00;

        Double durationPrice = 0.00;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        Double totalPrice = 0.00;
        if (subscription.size() > 0) {
            price = Double.valueOf(subscription.get(0).getTotal());
            lastId = subscription.get(0).getCheck_id();
            durationPrice = Double.valueOf(subscription.get(0).getPackageduration());
            totalPrice = price / durationPrice;
        }
        final Dialog dialog = new Dialog(context, R.style.mytheme);
        dialog.setContentView(R.layout.layout_qty_dialog);
        dialog.setTitle("");
        final TextView dialogButton = dialog.findViewById(R.id.nextBT);
        final LinearLayout mainLL = dialog.findViewById(R.id.mainLL);
        final CheckBox termCB = dialog.findViewById(R.id.termCB);
        final TextView agreeTV = dialog.findViewById(R.id.agreeTV);
        final TextView cancleButton = dialog.findViewById(R.id.closeBT);
        final EditText quantID = dialog.findViewById(R.id.quantID);
        final TextView titleTV = dialog.findViewById(R.id.titleTV);
        final TextView dialogTV = dialog.findViewById(R.id.dialogTV);

        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            dialogTV.setTypeface(custom_font_azab);
            quantID.setTypeface(custom_font_azab);
            titleTV.setTypeface(custom_font_azab);
            termCB.setTypeface(custom_font_azab);
            dialogButton.setTypeface(custom_font_azab);
            agreeTV.setTypeface(custom_font_azab);

        }

        if (languageValue.equalsIgnoreCase("ar")) {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            mainLL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        if (!termCB.isChecked()) {
            dialogButton.setAlpha(0.4f);
        }


        final Double finalTotalPrice = totalPrice;
        final Double[] quantPrice = {0.0};

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
            }
        });

        quantID.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() != 0) {
                    Double titlePrice = finalTotalPrice * Integer.parseInt(s.toString());
                    if (duration.equalsIgnoreCase("lunch")) {
                        Double totalBreakPrice = (Double.parseDouble(lunchPrice) / Integer.parseInt(durationValue)) * Integer.parseInt(s.toString());
                        titleTV.setText("OMR " + String.format("%.2f", totalBreakPrice));
                        quantPrice[0] = totalBreakPrice;
                    } else {
                        Double totalBreakPrice = (Double.parseDouble(breakfastPrice) / Integer.parseInt(durationValue)) * Integer.parseInt(s.toString());
                        titleTV.setText("OMR " + String.format("%.2f", totalBreakPrice));
                        quantPrice[0] = totalBreakPrice;
                    }

                }

            }
        });

        // if button is clicked, close the custom dialog
        final String finalLastId = lastId;
        final String finalId = id;
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!quantID.getText().toString().isEmpty()) {
                    if (!quantID.getText().toString().equalsIgnoreCase("0") || !quantID.getText().toString().equalsIgnoreCase("0")) {
                        if (termCB.isChecked()) {
                            dialog.dismiss();
                           // sendDoubleApi(duration, quantID.getText().toString(), finalUpdateData1);
                            cardPaymentDialog(String.valueOf(quantPrice[0]), Integer.parseInt(lastId));

                        }
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





    public interface DialogInterface {
        void paypalListener(Double aDouble, String lastId, String finalId, String foodtype, FoodData.Uptdatum finalUpdateData1);
    }


   // private void getCyberPayData(String priceValue, String lastId) {




   /*     final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.data_loading));
        dialog.show();

        final CyberData task = new CyberData();
        task.setAccountNumber(cardValue);
        task.setExpirationMonth(expiryMonth);
        task.setExpirationYear(expiryYear);
        task.setCvNumber(cvcValue);
        task.setFirstName(userFirstName);
        task.setLastName(userLastName);
        task.setStreet1(userCompanyName);
        task.setAmount(priceValue);
        task.setEmail(userEmailId);
        task.setCheck_id(String.valueOf(lastId));


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.getCyberPay(task).enqueue(new Callback<CyberResponse>() {
            @Override
            public void onResponse(Call<CyberResponse> call, Response<CyberResponse> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    //  Toast.makeText(context, "" + response.body().getReasonCode(), Toast.LENGTH_SHORT).show();

                    if (response.body().getReasonCode().equalsIgnoreCase("100")) {
                       // sendDataToApi("");
                    } else {
                      //  hitGatewayCancel(context);
                    }
                 *//*   etfirstname.setText(response.body().getData().get(0).getFirstname());
                    lastname.setText(response.body().getData().get(0).getLastname());
                    emails.setText(response.body().getData().get(0).getEmail());
                    phones.setText(response.body().getData().get(0).getPhone());
                    companyname.setText(response.body().getData().get(0).getCompany());
                    office.setText(response.body().getData().get(0).getOffice());
                    floor.setText(response.body().getData().get(0).getFloor());
                    //zip.setText(response.body().getData().get(0).getDob());
                    locationSearch.setText(response.body().getData().get(0).getLocation());
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude))).title("Saved Location"));
*//*

                }
            }

            @Override
            public void onFailure(Call<CyberResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });*/
   // }

    private void getTerms() {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("terms");
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
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


                    Intent intent = new Intent(context, TermsConditions.class);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("details", Details);
                    editor.apply();
                    //  intent.putExtra("countrycodes",movies.get(position).getCode());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<TermsConditionModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }
///////////////////////////////////////////

    public static Dialog dialog;
    public static boolean checkoutDone = true;
    public static CountDownTimer timer = null;
    public int counter;
    public static String Userid = "";
    public static ConstraintLayout cardCLView;
    public static String Price="";
    public static CheckBox saveCB;
    public void cardPaymentDialog(final String priceValue, final int lastId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Price = priceValue;
        Userid = preferences.getString("userid", "");

        Button closeBT, nextBT;
        final EditText monthET, cardET, yearET, cvvET;
        final TextView cardTV, cvvTV, minuteTV, secondTV, secureTV;
        ImageView timeIV;
        ConstraintLayout cardCL;
        RecyclerView cardRV;

        dialog = new Dialog(context, android.R.style.Theme_Light);
        //  dialog = new Dialog(this, R.style.RatingDialog2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.card_payment_dialog);
        cardTV = dialog.findViewById(R.id.cardTV);
        cardET = dialog.findViewById(R.id.cardET);
        monthET = dialog.findViewById(R.id.monthET);
        yearET = dialog.findViewById(R.id.yearET);
        cvvET = dialog.findViewById(R.id.cvvET);
        cvvTV = dialog.findViewById(R.id.cvvTV);
        closeBT = dialog.findViewById(R.id.closeBT);
        nextBT = dialog.findViewById(R.id.nextBT);
        timeIV = dialog.findViewById(R.id.timeIV);
        minuteTV = dialog.findViewById(R.id.minuteTV);
        secondTV = dialog.findViewById(R.id.secondTV);
        cardCL = dialog.findViewById(R.id.cardCL);
        cardRV = dialog.findViewById(R.id.cardRV);
        saveCB = dialog.findViewById(R.id.saveCB);
        secureTV = dialog.findViewById(R.id.secureTV);
        cardCL.setVisibility(View.VISIBLE);
        cardCLView = cardCL;
        cardRV.setVisibility(View.GONE);
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();
            }
        });


        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(android.content.DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    timer.cancel();
                    if (checkoutDone) {
                        if (lastId != 0) {
                            hitCronJob(String.valueOf(lastId), "Subscription");

                        }
                    }
                    dialog.dismiss();
                }
                return false;
            }
        });

        timer = new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {

                minuteTV.setText("" + String.format("%d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                secondTV.setText("" + String.format(" %d",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                counter++;
            }

            public void onFinish() {
                hitGatewayCancel(context);
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }.start();

        Glide.with(context)
                .load(R.drawable.timer)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(timeIV);

       /* Glide.with(this)
                .load(R.drawable.timer)
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(timeIV);
*/
        ArrayList<String> years = new ArrayList<String>();
        ArrayList<String> monthsList = new ArrayList<String>();
        ArrayList<String> monthIntList = new ArrayList<String>();
        ArrayList<String> monthCurrentList = new ArrayList<String>();

        if (!Userid.isEmpty()) {
            showCards(Userid, cardRV);
        }

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

        for (int i = thisYear; i <= thisYear + 10; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, years);
        final ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, monthIntList);
        final ArrayAdapter<String> adapterCurrent = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, monthCurrentList);

        final Spinner spinYear = dialog.findViewById(R.id.yearspin);
        final Spinner monthSpin = dialog.findViewById(R.id.monthSpin);
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


        monthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyboard((Activity) context);
                new RackMonthPicker(context)
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
                Constants.hideKeyboard((Activity) context);
                new RackMonthPicker(context)
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


         preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");

            cardTV.setTypeface(custom_font_azab);
            cardET.setTypeface(custom_font_azab);
            monthET.setTypeface(custom_font_azab);
            yearET.setTypeface(custom_font_azab);
            cvvTV.setTypeface(custom_font_azab);
            cvvET.setTypeface(custom_font_azab);
            closeBT.setTypeface(custom_font_azab);
            nextBT.setTypeface(custom_font_azab);
            saveCB.setTypeface(custom_font_azab);
            secureTV.setTypeface(custom_font_azab);
        }


        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();


            }
        });
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardET.getText().toString().isEmpty()) {
                    makeText(context, context.getString(R.string.please_enter_card), Toast.LENGTH_SHORT).show();
                }/* else if (monthET.getText().toString().isEmpty()) {
                    Toast.makeText(context, getString(R.string.please_enter_month), Toast.LENGTH_SHORT).show();

                }*//* else if (yearET.getText().toString().isEmpty()) {
                    Toast.makeText(context, getString(R.string.please_enter_year), Toast.LENGTH_SHORT).show();

                } */ else if (cvvET.getText().toString().isEmpty()) {
                    makeText(context, context.getString(R.string.please_enter_cvv), Toast.LENGTH_SHORT).show();

                } else {

                    getCyberPayData(context,dialog, String.valueOf(priceValue), lastId, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString(), saveCB.isChecked());


                    //  CheckOut(dialog, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString());
                }

            }
        });
        dialog.show();


    }

    private void hitCronJob(String checkId, final String payType) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String checkstatus = preferences.getString("status", "");
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final PaypalCancel task = new PaypalCancel();
        task.setCheckId(String.valueOf(checkId));
        task.setPaytype(payType);
        task.setLang(languageValue);

        Log.e("payment check", " check Id  : " + checkId + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.manualCronJob(task).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                makeText(context, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public static void hitGatewayCancel(final Context context) {

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.data_loading));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String checkstatus = preferences.getString("status", "");
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(String.valueOf(lastId));
        task.setFoodtype("");
        task.setPaytype("");
        task.setLang(languageValue);

        if (Userid.isEmpty()) {
            task.setIs_logged("No");
        } else {
            task.setIs_logged("Yes");
        }
        Log.e("payment check", " check Id  : " + lastId + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalCancel(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {

                     /*   startActivity(new Intent(context, MainActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));*/
                    }
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<paypalmodel> call, Throwable t) {
                dialog.dismiss();
                makeText(context, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public static void getCyberPayData( final Context context, Dialog mainDialog, String priceValue, int lastId, final String accountNo, final String cvv, final String expMonth, final String expYear, final boolean checkedSave) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.data_loading));
        dialog.show();
        SharedPreferences  preferences = PreferenceManager.getDefaultSharedPreferences(context);

        final CyberData task = new CyberData();
        task.setAccountNumber(accountNo);
        task.setExpirationMonth(expMonth);
        task.setExpirationYear(expYear);
        task.setCvNumber(cvv);

        
        task.setFirstName(preferences.getString("userFirstName", ""));
        task.setLastName(preferences.getString("userLastName", ""));
        task.setStreet1(preferences.getString("userFloor", ""));
        task.setAmount(priceValue);
        task.setEmail(preferences.getString("userEmailId", ""));
        task.setCheck_id(String.valueOf(lastId));

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.getCyberPay(task).enqueue(new Callback<CyberResponse>() {
            @Override
            public void onResponse(Call<CyberResponse> call, Response<CyberResponse> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    //  Toast.makeText(context, "" + response.body().getReasonCode(), Toast.LENGTH_SHORT).show();
                    String responseCode = response.body().getReasonCode();
                    if (response.body().getReasonCode().equalsIgnoreCase("100")) {
                        sendDataToApi(context,"", accountNo, cvv, expMonth, expYear, checkedSave);

                    } else if (responseCode.equalsIgnoreCase("101")) {
                        hitGatewayCancel(context);
                        makeText(context, "Declined - The request is missing one or more fields", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("102")) {
                        hitGatewayCancel(context);
                        makeText(context, "Declined - One or more fields in the request contains invalid data.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("150")) {
                        hitGatewayCancel(context);
                        makeText(context, "Error - General CyberSource system failure.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("151")) {
                        hitGatewayCancel(context);
                        makeText(context, "Error - The request was received but there was a server timeout. This error does not include timeouts between the client and the server.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("152")) {
                        hitGatewayCancel(context);
                        makeText(context, "Error: The request was received, but a service did not finish running in time.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("204")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - Insufficient funds in the account.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("207")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - Issuing bank unavailable.'", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("208")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - Inactive card or card not authorized for card-not-present transactions.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("209")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - card verification number (CVV) did not match.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("210")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - The card has reached the credit limit.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("211")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - Invalid Card Verification Number (CVV).", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("221")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - The customer matched an entry on the processor negative file.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("222")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - customer account is frozen", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("231")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - Invalid account number", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("232")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - The card type is not accepted by the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("240")) {
                        hitGatewayCancel(context);
                        makeText(context, "Decline - The card type sent is invalid or does not correlate with the credit card number.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("250")) {
                        hitGatewayCancel(context);
                        makeText(context, "Error - The request was received, but there was a timeout at the payment processor.", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("480")) {
                        hitGatewayCancel(context);
                        makeText(context, "The order is marked for review by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else if (responseCode.equalsIgnoreCase("481")) {
                        hitGatewayCancel(context);
                        makeText(context, "The order has been rejected by Decision Manager", Toast.LENGTH_SHORT).show();
                    } else {
                        hitGatewayCancel(context);
                        makeText(context, "Payment has been cancelled", Toast.LENGTH_SHORT).show();
                    }
                 /*   etfirstname.setText(response.body().getData().get(0).getFirstname());
                    lastname.setText(response.body().g=a().get(0).getCompany());
                    office.setText(response.body().getData().get(0).getOffice());
                    floor.setText(response.body().getData().get(0).getFloor());
                    //zip.setText(response.body().getData().get(0).getDob());
                    locationSearch.setText(response.body().getData().get(0).getLocation());
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(savedLatitude), Double.parseDouble(savedLongitude))).title("Saved Location"));
*/

                }
            }

            @Override
            public void onFailure(Call<CyberResponse> call, Throwable t) {
                dialog.dismiss();
                makeText(context, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });
    }


    public static void sendDataToApi(final Context context,String details, final String accountNo, final String cvv, final String expMonth, final String expYear, boolean checkedSave) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.menu));
        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String checkstatus = preferences.getString("status", "");
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        final GatewayModel task = new GatewayModel();
        task.setCheck_id(String.valueOf(lastId));
        task.setFoodtype(checkstatus);
        task.setPaytype("");
        task.setLang(languageValue);

        Log.e("payment check", " check Id  : " + lastId + " food type  " + checkstatus);
        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.GetPaypalSuccessCheck(task).enqueue(new Callback<paypalmodel>() {
            @Override
            public void onResponse(Call<paypalmodel> call, Response<paypalmodel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    if (response.body().getStatus().equals("200")) {

                        addCardDetail(context,dialog, accountNo, cvv, expMonth, expYear, true);
                        dialogPaySuccess(context,expYear);

                        //  makeText(context, "Thank You! Congratulations! Your Order Was Completed Successfully.", Toast.LENGTH_SHORT).show();


                    }
                    // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<paypalmodel> call, Throwable t) {
                dialog.dismiss();
                makeText(context, "Failure" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void addCardDetail(final Context context,final Dialog mainDailog, final String cardValue, final String cvcValue, final String monthValue, final String yearValue, boolean isDefault) {

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage(context.getString(R.string.data_loading));
        dialog.show();

        final checkout task = new checkout();
        task.setCard_number(cardValue);
        task.setCard_month(monthValue);
        task.setCard_year(yearValue);
        task.setCvc(cvcValue);
        task.setUserid(Userid);

        task.setIsDefault(String.valueOf(1));

        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.addCardApi(task).enqueue(new Callback<PasswordChangeModel>() {
            @Override
            public void onResponse(Call<PasswordChangeModel> call, Response<PasswordChangeModel> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    mainDailog.dismiss();
                    // saveCardData(cardValue, monthValue, yearValue, cvcValue);
                    Toast.makeText(context, context.getString(R.string.card_added), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasswordChangeModel> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(context, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }

    public static void dialogPaySuccess(final Context context,final String paymentDetails) {
        if (timer != null) {
            timer.cancel();
        }
        TextView emailTV, dialogTV, titleTV, closeBT;


        RelativeLayout relativeLayout1, relativeLayout2;
        dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pay_success);


        closeBT = dialog.findViewById(R.id.closeBT);
        dialogTV = dialog.findViewById(R.id.dialogTV);
        emailTV = dialog.findViewById(R.id.termCB);
        titleTV = dialog.findViewById(R.id.titleTV);

        dialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");


        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            //holder.title.setTypeface(custom_font_azab);


            dialogTV.setTypeface(custom_font_azab);
            titleTV.setTypeface(custom_font_azab);

            closeBT.setTypeface(custom_font_azab);
            emailTV.setTypeface(custom_font_azab);
        }
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                context.startActivity(new Intent(context, MainActivity.class));
            }
        });
    }

    public void cardPartyPaymentDialog(final String priceValue, final int lastId) {

        Button closeBT, nextBT;
        final EditText monthET, cardET, yearET, cvvET;
        final TextView cardTV, cvvTV, minuteTV, secondTV;
        RecyclerView cardRV;
        ConstraintLayout cardCL;
        final CheckBox saveCB;
        TextView secureTV;
        dialog = new Dialog(context, android.R.style.Theme_Light);
        //dialog = new Dialog(this, R.style.RatingDialog2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.card_payment_dialog);
        cardTV = dialog.findViewById(R.id.cardTV);
        cardET = dialog.findViewById(R.id.cardET);
        monthET = dialog.findViewById(R.id.monthET);
        yearET = dialog.findViewById(R.id.yearET);
        cvvET = dialog.findViewById(R.id.cvvET);
        cvvTV = dialog.findViewById(R.id.cvvTV);
        closeBT = dialog.findViewById(R.id.closeBT);
        nextBT = dialog.findViewById(R.id.nextBT);
        cardRV = dialog.findViewById(R.id.cardRV);
        cardCL = dialog.findViewById(R.id.cardCL);
        minuteTV = dialog.findViewById(R.id.minuteTV);
        secondTV = dialog.findViewById(R.id.secondTV);
        saveCB = dialog.findViewById(R.id.saveCB);
        secureTV = dialog.findViewById(R.id.secureTV);
        cardCLView = cardCL;
        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();

            }
        });

        timer = new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {

                minuteTV.setText("" + String.format("%d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                secondTV.setText("" + String.format(" %d",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                counter++;
            }

            public void onFinish() {
                hitGatewayCancel(context);
                context.startActivity(new Intent(context, MainActivity.class)
                        .putExtra("PaymentDetails", "")
                        .putExtra("PaymentAmount", priceValue));
            }
        }.start();
        ArrayList<String> years = new ArrayList<String>();
        ArrayList<String> monthsList = new ArrayList<String>();
        ArrayList<String> monthIntList = new ArrayList<String>();
        ArrayList<String> monthCurrentList = new ArrayList<String>();


        if (!Userid.isEmpty()) {
            showCards(Userid, cardRV);
        }

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, years);
        final ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, monthIntList);
        final ArrayAdapter<String> adapterCurrent = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, monthCurrentList);

        final Spinner spinYear = dialog.findViewById(R.id.yearspin);
        final Spinner monthSpin = dialog.findViewById(R.id.monthSpin);
        monthSpin.setAdapter(adapterCurrent);
        spinYear.setAdapter(adapter);

       /* spinYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(spinYear.getSelectedItem().equals(thisYear))
                {
                    monthSpin.setAdapter(adapterCurrent);

                }else
                {
                    monthSpin.setAdapter(adapterMonth);
                }
            }
        });*/

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
    /*    spinYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinYear.getSelectedItem().equals(thisYear))
                {
                    monthSpin.setAdapter(adapterCurrent);

                }else
                {
                    monthSpin.setAdapter(adapterMonth);
                }
            }
        });

*/

        monthET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyboard((Activity) context);
                new RackMonthPicker(context)
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
                Constants.hideKeyboard((Activity) context);
                new RackMonthPicker(context)
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


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");

            cardTV.setTypeface(custom_font_azab);
            cardET.setTypeface(custom_font_azab);
            monthET.setTypeface(custom_font_azab);
            yearET.setTypeface(custom_font_azab);
            cvvTV.setTypeface(custom_font_azab);
            cvvET.setTypeface(custom_font_azab);
            closeBT.setTypeface(custom_font_azab);
            nextBT.setTypeface(custom_font_azab);
            secureTV.setTypeface(custom_font_azab);
            saveCB.setTypeface(custom_font_azab);
        }


        closeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (checkoutDone) {
                    if (lastId != 0) {
                        hitCronJob(String.valueOf(lastId), "Subscription");

                    }
                }

                timer.cancel();

            }
        });
        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardET.getText().toString().isEmpty()) {
                    makeText(context, context.getString(R.string.please_enter_card), Toast.LENGTH_SHORT).show();
                }/* else if (monthET.getText().toString().isEmpty()) {
                    Toast.makeText(context, getString(R.string.please_enter_month), Toast.LENGTH_SHORT).show();

                }*/ else if (cvvET.getText().toString().isEmpty()) {
                    makeText(context, context.getString(R.string.please_enter_cvv), Toast.LENGTH_SHORT).show();

                } else {

                 /*   List<String> myList = new ArrayList<String>(Arrays.asList(idList.split(",")));
                    List<String> priceArrayList = new ArrayList<String>(Arrays.asList(priceList.split(",")));
                    List<String> qtyArrayList = new ArrayList<String>(Arrays.asList(qtyList.split(",")));
                    Set<String> idSet = new HashSet<>();
                    Set<String> priceSet = new HashSet<>();
                    Set<String> qtySet = new HashSet<>();
                    idSet.addAll(myList);
                    priceSet.addAll(priceArrayList);
                    qtySet.addAll(qtyArrayList);
                    LinkedHashSet<String> hashSet = new LinkedHashSet<>(myList);



                    String idString = TextUtils.join(", ", hashSet);
                    String idsdString = TextUtils.join(", ", hashSet);
*/

                   // partyCheckOut(dialog, cardET.getText().toString(), cvvET.getText().toString(), monthSpin.getSelectedItem().toString(), spinYear.getSelectedItem().toString(), saveCB.isChecked());
                    // CheckOut(dialog,cardET.getText().toString(), cvvET.getText().toString(), monthET.getText().toString(), yearET.getText().toString());
                }

            }
        });
        dialog.show();


    }

    public static RecyclerView mainCardRV;
    public static List<GetCardModel> listCards = new ArrayList<>();
    public static ShowCardAdapter adapter;

    private void  showCards(String userId, final RecyclerView cardRV) {
        mainCardRV = cardRV;
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.data_loading));
        progressDialog.show();

        final checkout task = new checkout();
        task.setUserid(userId);


        final ApiServices userService = ApiUrl.createService(ApiServices.class,
                "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
        userService.showCards(task).enqueue(new Callback<List<GetCardModel>>() {
            @Override
            public void onResponse(Call<List<GetCardModel>> call, Response<List<GetCardModel>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().size() > 0) {
                        listCards = new ArrayList<>();
                        listCards = response.body();
                        GetCardModel model = new GetCardModel();
                        model.setCard_number(Constants.NEWCARD);
                        listCards.add(model);
                        mainCardRV.setLayoutManager(new LinearLayoutManager(context));
                        adapter = new ShowCardAdapter(context, listCards);
                        mainCardRV.setAdapter(adapter);
                        mainCardRV.setVisibility(View.VISIBLE);

                    } else {
                        GetCardModel model = new GetCardModel();
                        model.setCard_number(Constants.NEWCARD);
                        listCards.add(model);
                        mainCardRV.setLayoutManager(new LinearLayoutManager(context));
                        adapter = new ShowCardAdapter(context, listCards);
                        mainCardRV.setAdapter(adapter);
                        makeText(context, context.getString(R.string.no_card_to_show), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<GetCardModel>> call, Throwable t) {
                progressDialog.dismiss();
                //   Toast.makeText(context, "Failure Response", Toast.LENGTH_LONG).show();
            }
        });

    }


}
