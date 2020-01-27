package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.history;
import com.example.myapplication.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MovieViewHolder> {
    //private List<Result> dataList;

    private List<history> movies;
    private int rowLayout;
    private Context context;
    Dialog dialog;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title, week, weekly, orderid, group, subcriber;
        TextView brakfast;
        TextView toptitle;
        TextView brakfastlunch;
        TextView processTV;
        TextView orderID;
        TextView status;
        TextView drinkTV;
        ImageView image;

        LinearLayout mainLLView;

        public MovieViewHolder(View v) {
            super(v);
            subcriber = v.findViewById(R.id.subcriber);
            orderID = v.findViewById(R.id.orderID);
            week = v.findViewById(R.id.week);
            group = v.findViewById(R.id.group);
            brakfast = v.findViewById(R.id.both);
            toptitle = v.findViewById(R.id.datefrom);
            brakfastlunch = v.findViewById(R.id.dateto);
            image = v.findViewById(R.id.imagehistory);
            weekly = v.findViewById(R.id.orderdate);
            orderid = v.findViewById(R.id.orderid);
            status = v.findViewById(R.id.staus);
            processTV = v.findViewById(R.id.processTV);
            drinkTV = v.findViewById(R.id.drinkTV);
            mainLLView = v.findViewById(R.id.mainLLView);

        }
    }

    public HistoryAdapter(List<history> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public HistoryAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        View view;
        if (languageValue.equalsIgnoreCase("ar")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_rtl, parent, false);

        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        }
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        // holder.brakfast.setText(movies.get(position).getItems().get(position).getDescription());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");


        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            //holder.title.setTypeface(custom_font_azab);
            holder.week.setTypeface(custom_font_azab);
            holder.weekly.setTypeface(custom_font_azab);
            holder.orderid.setTypeface(custom_font_azab);
            holder.group.setTypeface(custom_font_azab);
            holder.subcriber.setTypeface(custom_font_azab);
            holder.brakfast.setTypeface(custom_font_azab);
            holder.toptitle.setTypeface(custom_font_azab);
            holder.brakfastlunch.setTypeface(custom_font_azab);
            holder.processTV.setTypeface(custom_font_azab);
            holder.orderID.setTypeface(custom_font_azab);
            holder.status.setTypeface(custom_font_azab);
            holder.drinkTV.setTypeface(custom_font_azab);

        }


        if (languageValue.equalsIgnoreCase("ar")) {
            holder.subcriber.setText(context.getString(R.string.subscribe_new) + " " + movies.get(position).getPeople());
            holder.group.setText(context.getString(R.string.group_string_ar));
            holder.orderID.setText(context.getString(R.string.order_id_ar));
            holder.toptitle.setText(context.getString(R.string.from_string_ar) + " " + movies.get(position).getSdate());
            holder.weekly.setText(context.getString(R.string.order_start_string_ar) + " " + movies.get(position).getOrderdate());
            holder.brakfastlunch.setText(context.getString(R.string.to_string_ar) + " " + movies.get(position).getEdate());

        } else {
            holder.subcriber.setText(context.getString(R.string.subscribe) + " " + movies.get(position).getPeople());
            holder.group.setText(context.getString(R.string.group_string));
            holder.orderID.setText(context.getString(R.string.order_id));
            holder.toptitle.setText(context.getString(R.string.from_string) + " " + movies.get(position).getSdate());
            holder.brakfastlunch.setText(context.getString(R.string.to_string) + " " + movies.get(position).getEdate());

            holder.weekly.setText(context.getString(R.string.order_start_string) + " " + movies.get(position).getOrderdate());
            holder.drinkTV.setText(context.getString(R.string.soft_drink) + " " + movies.get(position).getSoftdrink());

        }

        if (languageValue.equalsIgnoreCase("ar")) {
          /*  holder.mainLLView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            holder.brakfastlunch.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.brakfast.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.drinkTV.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.group.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.orderID.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.orderid.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.processTV.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.status.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.subcriber.setTextDirection(View.TEXT_DIRECTION_RTL);

*/
            holder.status.setText(movies.get(position).getTotal() + " OMR ");
        } else {
            holder.mainLLView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            holder.status.setText("OMR " + movies.get(position).getTotal());
        }


        holder.brakfast.setText(movies.get(position).getDuration());
        if (movies.get(position).getDuration().equals("Breakfast & Lunch")) {
            holder.brakfast.setText(context.getString(R.string.both_string));
        }


        holder.week.setText(movies.get(position).getPackagename());
        // holder.week.setText(movies.get(position).getStype());
        holder.orderid.setText(movies.get(position).getOrderid());
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
                ) .into(holder.image);*/
        holder.processTV.setText(movies.get(position).getStatus());

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

