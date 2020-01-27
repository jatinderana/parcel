package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.MainMenuModel;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.Log;

import java.util.List;


public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MovieViewHolder> {
    //private List<Result> dataList;

    private List<MainMenuModel> movies;
    private int rowLayout;
    private Context context;
    private String duration;
    Dialog dialog;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title;
        TextView brakfast;
        TextView price;
        TextView lunch1;
        TextView toptitle;
        TextView maintitles;
        TextView maintitles2;
        TextView title2;
        ImageView image, image2;
        LinearLayout weekly;
        LinearLayout menuView;


        public MovieViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            brakfast = v.findViewById(R.id.discription);
            lunch1 = v.findViewById(R.id.discription2);
            price = v.findViewById(R.id.price);
            title2 = v.findViewById(R.id.title2);
            image = v.findViewById(R.id.foodimage);
            image2 = v.findViewById(R.id.foodimage2);
            toptitle = v.findViewById(R.id.toptitle);
            maintitles = v.findViewById(R.id.maintitles);
            maintitles2 = v.findViewById(R.id.maintitles2);
            //weekly=(LinearLayout)v.findViewById(R.id.weekly);
            //menuView=(LinearLayout)v.findViewById(R.id.menuview);

        }
    }

    public MainMenuAdapter(List<MainMenuModel> movies, int rowLayout, Context context, String duration) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.duration = duration;
    }

    @Override
    public MainMenuAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");

        dialog = new Dialog(context);
        Log.e("movies---","movies---"+movies.get(position).getBdescription());
        //   if (duration.equalsIgnoreCase(context.getString(R.string.breakfast_string))) {
        if (duration.equalsIgnoreCase(context.getString(R.string.breakfast))) {
            holder.title.setText(movies.get(position).getBdescription());
           // holder.title2.setText(movies.get(position).getLdescription());
            // holder.price.setText(movies.get(position).getPrice());
            // holder.brakfastlunch.setText(movies.get(position).getBothh());
            Glide.with(context)
                    .load(movies.get(position).getBimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
          /*  Glide.with(context)
                    .load(movies.get(position).getBimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(holder.image);*/
           /* Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image2);*/
           /* Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(holder.image2);*/

            if (languageValue.equalsIgnoreCase("ar")) {
                Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
                Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
                holder.maintitles2.setTypeface(custom_font_azab);
                if (duration.equalsIgnoreCase(context.getString(R.string.breakfast_ar))) {
                    holder.maintitles.setText(context.getString(R.string.breakfast_ar));
                } else {
                    holder.maintitles.setText(context.getString(R.string.lunch_ar));
                }

                holder.maintitles.setTextSize(16);
                holder.title.setTextSize(16);

                holder.maintitles.setTypeface(custom_font_azab);
                holder.title.setTypeface(custom_font_azab);
                holder.title2.setTypeface(custom_font_azab);

            }

        } else {
            holder.title.setText(movies.get(position).getLdescription());
            holder.title2.setText(movies.get(position).getLdescription());
            // holder.price.setText(movies.get(position).getPrice());
            // holder.brakfastlunch.setText(movies.get(position).getBothh());
            Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
         /*   Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(holder.image);*/


   /*         Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(holder.image2);*/
            Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image2);
            holder.maintitles.setText(context.getString(R.string.lunch));
            if (languageValue.equalsIgnoreCase("ar")) {
                Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
                Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
                holder.maintitles2.setTypeface(custom_font_azab);
                holder.maintitles.setText(context.getString(R.string.lunch));


                if (duration.equalsIgnoreCase(context.getString(R.string.breakfast_ar))) {
                    holder.maintitles.setText(context.getString(R.string.breakfast_ar));

                    holder.title.setText(movies.get(position).getBdescription());
                    holder.title2.setText(movies.get(position).getBdescription());
                    // holder.price.setText(movies.get(position).getPrice());
                    // holder.brakfastlunch.setText(movies.get(position).getBothh());
                    Glide.with(context)
                            .load(movies.get(position).getBimage())
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.image);

                 /*   Glide.with(context)
                            .load(movies.get(position).getBimage())
                            .thumbnail(0.5f)
                            .transition(withCrossFade())
                            .apply(new RequestOptions().override(100, 100)
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_background).centerCrop()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                            ).into(holder.image);
*/

                    Glide.with(context)
                            .load(movies.get(position).getBimage())
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.image2);
                  /*  Glide.with(context)
                            .load(movies.get(position).getBimage())
                            .thumbnail(0.5f)
                            .transition(withCrossFade())
                            .apply(new RequestOptions().override(100, 100)
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_background).centerCrop()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                            ).into(holder.image2);
*/

                } else {
                    holder.maintitles.setText(context.getString(R.string.lunch_ar));
                    holder.title.setText(movies.get(position).getLdescription());
                    holder.title2.setText(movies.get(position).getLdescription());
                    // holder.price.setText(movies.get(position).getPrice());
                    // holder.brakfastlunch.setText(movies.get(position).getBothh());
                    Glide.with(context)
                            .load(movies.get(position).getLimage())
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.image);

                  /*  Glide.with(context)
                            .load(movies.get(position).getLimage())
                            .thumbnail(0.5f)
                            .transition(withCrossFade())
                            .apply(new RequestOptions().override(100, 100)
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_background).centerCrop()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                            ).into(holder.image);*/

                    Glide.with(context)
                            .load(movies.get(position).getLimage())
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.image2);

                    /*Glide.with(context)
                            .load(movies.get(position).getLimage())
                            .thumbnail(0.5f)
                            .transition(withCrossFade())
                            .apply(new RequestOptions().override(100, 100)
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .error(R.drawable.ic_launcher_background).centerCrop()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                            ).into(holder.image2);*/
                }

/*

                Glide.with(context)
                        .load(movies.get(position).getLimage())
                        .thumbnail(0.5f)
                        .transition(withCrossFade())
                        .apply(new RequestOptions().override(100, 100)
                                .placeholder(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background).centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                        ).into(holder.image2);
*/

                holder.maintitles.setTypeface(custom_font_azab);
                holder.title.setTypeface(custom_font_azab);
                holder.title2.setTypeface(custom_font_azab);
                holder.title.setTextDirection(View.TEXT_DIRECTION_RTL);
                holder.title.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                holder.title.setGravity(Gravity.START);
                holder.maintitles.setTextDirection(View.TEXT_DIRECTION_RTL);
                holder.maintitles.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

                Drawable img = context.getResources().getDrawable( R.drawable.ic_done_black_24dp );
                img.setBounds( 0, 0, 60, 60 );
                holder.maintitles.setCompoundDrawables( null, null, img, null );

            }
        }

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

