package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.BucketAddModel;
import com.example.myapplication.Model.Retrofit.ParcelAddModel;
import com.example.myapplication.ParcelGeathering.ParcelBoxActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class PartyBoxAdapter extends RecyclerView.Adapter<PartyBoxAdapter.MovieViewHolder> implements ParcelBoxAdapter.ParcelInterface {
    //private List<Result> dataList;

    private List<ParcelAddModel> movies;
    private int rowLayout;
    private Context context;
    Dialog dialog;
    Integer count = 0;
    static TextView bucketBTView;
    private boolean hideButton = false;
    private List<BucketAddModel> bucketList = new ArrayList<>();
    ParcelInterface listener;
    private int countValue = 0;
    private int countLocal = 0;
    private String itemName = "";

    private String itemPrice = "";
    private String itemId = "";
    private String itemImage = "";

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title;
        TextView qunitity, bucketBT;
        ImageView image;
        LinearLayout weekly;
        LinearLayout menuView;
        RecyclerView menuRV;


        public MovieViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            image = v.findViewById(R.id.foodimage);
            bucketBT = v.findViewById(R.id.bucketBT);
            menuRV = v.findViewById(R.id.menuRV);

            //menuView=(LinearLayout)v.findViewById(R.id.menuview);

        }
    }

    public PartyBoxAdapter(List<ParcelAddModel> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        listener = (ParcelBoxActivity) context;
    }

    @Override
    public PartyBoxAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        dialog = new Dialog(context);
        bucketBTView = holder.bucketBT;
        final ParcelAddModel model = movies.get(position);

        holder.title.setText(movies.get(position).getTitle());
        Glide.with(context)
                .load(movies.get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


       /* Glide.with(context)
                .load(movies.get(position).getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);*/

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            holder.title.setTypeface(custom_font_azab);
            holder.bucketBT.setTypeface(custom_font_azab);
        }


        ParcelBoxItemAdapter adapter = new ParcelBoxItemAdapter(context, model.getParty(), PartyBoxAdapter.this, movies.get(position).getDtime());
        holder.menuRV.setLayoutManager(new LinearLayoutManager(context));
        holder.menuRV.setAdapter(adapter);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button cancel, send;
                TextView title, amintitle, close;
                final Spinner spine, spin;
                final EditText etdate;
                final ImageView images;
                RelativeLayout relativeLayout1, relativeLayout2;
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_party_box);
                images = dialog.findViewById(R.id.partyimage);
                title = dialog.findViewById(R.id.titles);
                amintitle = dialog.findViewById(R.id.maintitle);
                close = dialog.findViewById(R.id.close);

                amintitle.setText(movies.get(position).getTitle());
                title.setText(movies.get(position).getDescription());
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Glide.with(context)
                        .load(movies.get(position).getImage())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(images);


              /*  Glide.with(context)
                        .load(movies.get(position).getImage())
                        .thumbnail(0.5f)
                        .transition(withCrossFade())
                        .apply(new RequestOptions().override(100, 100)
                                .placeholder(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background).centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                        ).into(images);
*/
                if (languageValue.equalsIgnoreCase("ar")) {
                    Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
                    Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
                    title.setTypeface(custom_font_azab);
                    amintitle.setTypeface(custom_font_azab);
                    close.setTypeface(custom_font_azab);
                }
                dialog.show();

            }
        });

        holder.bucketBT.setOnClickListener(new View.OnClickListener() {
            private ParcelBoxAdapter adapter;

            @Override
            public void onClick(View v) {
                bucketList = new ArrayList<>();
                itemId = model.getId();
                itemImage = model.getImage();
                itemName = model.getTitle();

                Button cancel, send;
                TextView titleTV, selectBT, titleBelowTV, nextBT, closeBT;
                final EditText etdate;
                RecyclerView parcelRV;
                CardView mainCV;
                RelativeLayout relativeLayout1, relativeLayout2;

                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.parcel_box_item);
                titleTV = dialog.findViewById(R.id.titleTV);
                nextBT = dialog.findViewById(R.id.nextBT);
                mainCV = dialog.findViewById(R.id.mainCV);
                closeBT = dialog.findViewById(R.id.closeBT);
                bucketBTView = nextBT;
                titleBelowTV = dialog.findViewById(R.id.titleBelowTV);
                parcelRV = dialog.findViewById(R.id.parcelRV);
                adapter = new ParcelBoxAdapter(context, model.getParty(), PartyBoxAdapter.this);
                parcelRV.setLayoutManager(new LinearLayoutManager(context));
                parcelRV.setAdapter(adapter);
                Resources res = context.getResources();

                titleTV.setText(context.getResources().getString(R.string.choose_upto_4_options_a_total_of_24_pcs_in_a_platter, model.get4price()));
                titleBelowTV.setText(context.getResources().getString(R.string.choose_upto_6_options_a_total_of_36_pcs_in_a_platter, model.get6price()));
             /*   selectBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
*/

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                String languageValue = preferences.getString(Constants.SETLANG, "en");
                if (languageValue.equalsIgnoreCase("ar")) {
                    Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
                    Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
                    nextBT.setTypeface(custom_font_azab);
                    closeBT.setTypeface(custom_font_azab);
                    titleTV.setTypeface(custom_font_azab);
                    titleBelowTV.setTypeface(custom_font_azab);
                }


                closeBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                if (languageValue.equalsIgnoreCase("ar")) {
                    mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                } else {
                    mainCV.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                }

                nextBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!hideButton) {
                            dialog.cancel();
                            if (countValue == 4) {
                                listener.buttonAddMethod(bucketList, countValue, model.get4price(), itemId, itemName, itemImage);
                            } else if (countValue == 6) {
                                listener.buttonAddMethod(bucketList, countValue, model.get6price(), itemId, itemName, itemImage);
                            }

                        }
                    }
                });
                dialog.show();

            }
        });
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void buttonShowMethod(String id, String titleEn) {
        hideButton = false;
        bucketBTView.setTextColor(ContextCompat.getColor(context, R.color.white));

    }

    @Override
    public void buttonHideMethod(String id, String titleEn) {
        hideButton = true;
        bucketBTView.setTextColor(ContextCompat.getColor(context, R.color.grey_button));

    }

    @Override
    public void removeBucket(String id, String titleEn, int count) {
        BucketAddModel model = new BucketAddModel();
        model.setId(id);
        model.setName(titleEn);
        bucketList.remove(model);
        countValue = count;
    }


    @Override
    public void addBucket(String id, String titleEn, int count) {
        BucketAddModel model = new BucketAddModel();
        model.setId(id);
        model.setName(titleEn);
        bucketList.add(model);
        countValue = count;

    }

    @Override
    public void addPrice(String id, String titleEn, int count, String price, String image, int countAdd, int countReal,String dtime) {
        countValue = count;
        countLocal = countAdd;
       /* if (bucketList.size() > 0) {
            for (int i = 0; i < bucketList.size(); i++) {
                if (!id.equalsIgnoreCase(bucketList.get(i).getId())) {
                    BucketAddModel model = new BucketAddModel();
                    model.setId(id);
                    model.setName(titleEn);
                    model.setPrice(price);
                    model.setImage(image);
                    model.setCountValue("1");
                    bucketList.add(model);
                } else {

                    BucketAddModel model1 = new BucketAddModel();
                    model1.setCountValue(String.valueOf(countLocal));
                    model1.setPrice(price);
                    model1.setImage(image);
                    model1.setName(titleEn);
                    model1.setId(id);

                    int index = bucketList.indexOf(model1);
                    countLocal = countLocal + 1;
                    BucketAddModel model = new BucketAddModel();
                    model.setCountValue(String.valueOf(countLocal));
                    model.setPrice(price);
                    model.setImage(image);
                    model.setName(titleEn);
                    model.setId(id);
                    bucketList.set(index + 1, model);
                }

            }
        } else {
            countLocal = 1;
            BucketAddModel model = new BucketAddModel();
            model.setId(id);
            model.setName(titleEn);
            model.setPrice(price);
            model.setCountValue(String.valueOf(countLocal));
            model.setImage(image);
            bucketList.add(model);
        }




*/

       if(countReal>1)
       {
           BucketAddModel model1 = new BucketAddModel();
           model1.setId(id);
           model1.setName(titleEn);
           model1.setPrice(price);
           model1.setCountValue(String.valueOf(countReal-1));
           model1.setImage(image);
           model1.setDtime(dtime);

           int position = -1;
           for (int i = 0; i < bucketList.size(); i++) {
               if (bucketList.get(i).getId().equalsIgnoreCase(id)) {
                   position = i;
                   bucketList.remove(i);
                   // break;  // uncomment to get the first instance
               }
           }
           int index = bucketList.indexOf(model1);

          // bucketList.remove(model1);

       }

        countLocal = 1;
        BucketAddModel model = new BucketAddModel();
        model.setId(id);
        model.setName(titleEn);
        model.setPrice(price);
        model.setCountValue(String.valueOf(countReal));
        model.setImage(image);
        model.setDtime(dtime);
        bucketList.add(model);
        itemId = model.getId();
        listener.buttonAddMethod(bucketList, countValue, price, itemId, itemName, itemImage);


    }


    @Override
    public void removePrice(String id, String titleEn, int count, String price, String image, int countAdd, int countReal) {

      /*  if (countAdd <= 0) {
            countValue = count;
            BucketAddModel model = new BucketAddModel();
            model.setId(id);
            model.setName(titleEn);
            model.setImage(image);
            model.setPrice(price);
            bucketList.remove(model);
        } else {



            BucketAddModel model1 = new BucketAddModel();
            model1.setId(id);
            model1.setName(titleEn);
            model1.setImage(image);
            model1.setPrice(price);

            int index = bucketList.indexOf(model1);

            countValue = count;
            BucketAddModel model = new BucketAddModel();
            model.setId(id);
            model.setName(titleEn);
            model.setImage(image);
            model.setPrice(price);
            model.setCountValue(String.valueOf(countAdd));
            bucketList.set(index+1,model);
        }*/
      if(countReal>=0)
      {
          if(countReal==0)
          {
              BucketAddModel model = new BucketAddModel();
              model.setId(id);
              model.setName(titleEn);
              model.setImage(image);
              model.setPrice(price);
              model.setCountValue(String.valueOf(countReal+1));

              int position = -1;
              for (int i = 0; i < bucketList.size(); i++) {
                  if (bucketList.get(i).getId().equalsIgnoreCase(id)) {
                      position = i;
                      bucketList.remove(i);
                      // break;  // uncomment to get the first instance
                  }
              }
            //  bucketList.remove(model);
             /* int index = bucketList.indexOf(model);
              bucketList.remove(index);*/
          }
          else
          {

              BucketAddModel model1 = new BucketAddModel();
              model1.setId(id);
              model1.setName(titleEn);
              model1.setImage(image);
              model1.setPrice(price);
              model1.setCountValue(String.valueOf(countReal+1));
              int index = bucketList.indexOf(model1);

              BucketAddModel model = new BucketAddModel();
              model.setId(id);
              model.setName(titleEn);
              model.setImage(image);
              model.setPrice(price);
              model.setCountValue(String.valueOf(countReal));
              bucketList.set(index+1,model);
          }
      }

        listener.buttonRemovePriceMethod(bucketList, countValue, price, itemId, itemName, itemImage);
    }


    public interface ParcelInterface {
        void buttonAddMethod(List<BucketAddModel> bucketList, int countValue, String price, String itemId, String itemName, String itemImage);

        void buttonRemoveMethod(String id, String titleEn);


        void buttonRemovePriceMethod(List<BucketAddModel> bucketList, int countValue, String price, String itemId, String itemName, String itemImage);

    }
}

