package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.GiftFriend.GiftFriendActivity;
import com.example.myapplication.Model.Retrofit.FoodData;
import com.example.myapplication.Model.Retrofit.Subscription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DialogAdapterSubc extends BaseAdapter {
    Activity activity;

    private Activity context;
    private ArrayList<Dialogpojo> alCustom;
    private FoodData resp;
    private String dateSelected;
    private List<Subscription> subscription;
    private int restDays;
    private String sturl;
    Uri myUri;
    Dialog dialog;
    private DialogSubcAdapter adapter;
    private String dayNameAfter = "";


    public DialogAdapterSubc(Activity context, ArrayList<Dialogpojo> alCustom, FoodData resp, String dateSelected, List<Subscription> subscription, int restDays) {
        this.context = context;
        this.alCustom = alCustom;
        this.resp = resp;
        this.dateSelected = dateSelected;
        this.subscription = subscription;
        this.restDays = restDays;
    }

    @Override
    public int getCount() {
        return alCustom.size();

    }

    @Override
    public Object getItem(int i) {
        return alCustom.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.mysubcriptionmenu_layout, null, true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        TextView tvTitle = listViewItem.findViewById(R.id.tv_name);


        TextView gift_to = listViewItem.findViewById(R.id.gift_to);
        TextView foodGifted = listViewItem.findViewById(R.id.foodGifted);
        TextView foodTV = listViewItem.findViewById(R.id.foodTV);


        TextView replaced = listViewItem.findViewById(R.id.replaced);
        LinearLayout replaceLL = listViewItem.findViewById(R.id.replaceLL);

        RecyclerView foodRV = listViewItem.findViewById(R.id.foodRV);
        RelativeLayout mainRL = listViewItem.findViewById(R.id.mainRL);

        LinearLayout giftLL = listViewItem.findViewById(R.id.giftLL);

        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            tvTitle.setTypeface(custom_font_azab);
            tvTitle.setTextSize(24);
            gift_to.setTypeface(custom_font_azab);
            foodGifted.setTypeface(custom_font_azab);
            foodTV.setTypeface(custom_font_azab);
            replaced.setTypeface(custom_font_azab);
        }


        if (languageValue.equalsIgnoreCase("ar")) {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        } else {
            mainRL.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }



        if (resp.getGiftdetails().size() > 0) {
            foodGifted.setVisibility(View.VISIBLE);
            giftLL.setVisibility(View.GONE);
        } else {
            foodGifted.setVisibility(View.GONE);
            giftLL.setVisibility(View.VISIBLE);
        }
        if(resp.getGiftlimit().equals("0"))
        {
            giftLL.setVisibility(View.GONE);
        }

        if (languageValue.equalsIgnoreCase("ar")) {
            foodGifted.setText(context.getString(R.string.this_food_has_been_gifted_ar));
            gift_to.setText(context.getString(R.string.gift_to_friend_ar));

        } else {
            gift_to.setText(context.getString(R.string.gift_to_friend));
            foodGifted.setText(context.getString(R.string.this_food_has_been_gifted));
        }


        String date_after = Constants.formateDateFromstring("yyyy-MM-dd", "dd MMMM yyyy", dateSelected);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateSelected);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
            String dayName = simpleDateFormat.format(date);
            dayNameAfter = dayName;
        } catch (ParseException e) {
            e.printStackTrace();
        }

         if (!dateSelected.isEmpty()) {
            if (languageValue.equalsIgnoreCase("ar")) {
                foodTV.setText(context.getString(R.string.food_of_string_ar) + " "+dayNameAfter+" "+ date_after);
            } else {
                foodTV.setText(context.getString(R.string.food_of_string) + ""  + " "+dayNameAfter+" "+ date_after);
            }

        }

   /*  TextView tvTitle1=(TextView)listViewItem.findViewById(R.id.tv_name1);
        TextView tvSubject=(TextView)listViewItem.findViewById(R.id.tv_type);
        TextView tvDuedate=(TextView)listViewItem.findViewById(R.id.tv_desc);
        TextView tvDescription=(TextView)listViewItem.findViewById(R.id.tv_class);*/
        ImageView image = listViewItem.findViewById(R.id.imageview);
        if (resp.getData().size() > 0) {
            adapter = new DialogSubcAdapter(context, resp.getData(), resp, R.layout.subscription_item, dateSelected, subscription, resp.getUptdata(), restDays);
            foodRV.setLayoutManager(new LinearLayoutManager(context));
            foodRV.setAdapter(adapter);
        }
        // myUri = Uri.parse(alCustom.get(position).getImage());

        Glide.with(context)
                .load(alCustom.get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);


      /*  Glide.with(context)
                .load(alCustom.get(position).getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(image);*/

        //Toast.makeText(context,"images"+alCustom.get(position).getImage(),Toast.LENGTH_LONG).show();

        //    tvTitle.setText(""+alCustom.get(position).getTitles());
        //   tvTitle1.setText(""+alCustom.get(position).getSubjects());
        // tvSubject.setText("Subject : "+alCustom.get(position).getSubjects());
        // tvDuedate.setText("Due Date : "+alCustom.get(position).getDuedates());
        // tvDescription.setText("Description : "+alCustom.get(position).getDescripts());
        replaced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context, MySubcriptionMenu.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("selectedData", dateSelected);
                intent1.putExtra("replaceLimit", resp.getReplacelimit());
                context.startActivity(intent1);

                /*final RecyclerView parcelmenu;
                TextView back,titlemenu;
                String Duration;
                dialog = new Dialog(context);
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menudialog);

                parcelmenu=(RecyclerView)dialog.findViewById(R.id.parcelmenu);
                back=(TextView)dialog.findViewById(R.id.back);
                titlemenu=(TextView)dialog.findViewById(R.id.titlemenu);
                parcelmenu.setNestedScrollingEnabled(false);


                //final ProgressDialog dialog = new ProgressDialog(context);
               // dialog.setCancelable(false);
               // dialog.setMessage("loading");
               // dialog.show();


                final ApiServices userService = ApiUrl.createService(ApiServices.class,
                        "ParcelAPILogin", "f3373fea717313e72ed0f1ab0eac7ca5");
                userService.GetRelace().enqueue(new Callback<ReplaceModel>() {
                    @Override
                    public void onResponse(Call<ReplaceModel> call, Response<ReplaceModel> response) {
                        if (response.isSuccessful()) {
                           // dialog.dismiss();
                            List<Replace> menu=response.body().getData();

                            GridLayoutManager latest = new GridLayoutManager(context,1);
                            latest.setOrientation(GridLayoutManager.VERTICAL);
                            parcelmenu.setItemAnimator(new DefaultItemAnimator());
                            parcelmenu.setLayoutManager(latest);
                            // recyclerView.setNestedScrollingEnabled(false);
                            parcelmenu.setAdapter(new ReplaceItemAdapter(menu, R.layout.row_addapt, context));


                            // Toast.makeText(getApplicationContext(),"Checking succuess bloack"+parcel.get(0).getItems().get(0).getImage(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReplaceModel> call, Throwable t) {
                      //  dialog.dismiss();
                        Toast.makeText(context,"Failure"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

                dialog.show();
            }*/
            }
        });

        replaceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context, MySubcriptionMenu.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("selectedData", dateSelected);
                intent1.putExtra("replaceLimit", resp.getReplacelimit());
                context.startActivity(intent1);


            }
        });


        giftLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, GiftFriendActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("selectedData", dateSelected);
                intent1.putExtra("replaceLimit", resp.getReplacelimit());
                context.startActivity(intent1);
            }
        });


       /* if(alCustom.get(position).getSubjects()=="null"){
          //  both.setVisibility(View.GONE);
            views.setVisibility(View.GONE);
        }*/

        if(!resp.getCanuptbreakfast().equalsIgnoreCase("yes") || !resp.getCanuptlunch().equalsIgnoreCase("yes")  )
        {
            giftLL.setOnClickListener(null);
        }
        return listViewItem;
    }


}

