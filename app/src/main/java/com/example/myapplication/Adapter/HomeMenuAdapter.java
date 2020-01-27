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
import com.example.myapplication.Model.Retrofit.homemenu;
import com.example.myapplication.R;

import java.util.List;


public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.MovieViewHolder> {
    //private List<Result> dataList;

    private List<homemenu> movies;
    private int rowLayout;
    private Context context;
    Dialog dialog;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title;
        TextView brakfast;
        TextView price;
        TextView title2;
        TextView maintitles;
        TextView toptitle;
        TextView maintitles2;
        ImageView image, image2;
        LinearLayout weekly;
        LinearLayout menuView;


        public MovieViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            brakfast = v.findViewById(R.id.discription);
            price = v.findViewById(R.id.price);
            title2 = v.findViewById(R.id.title2);
            image = v.findViewById(R.id.foodimage);
            image2 = v.findViewById(R.id.foodimage2);
            toptitle = v.findViewById(R.id.toptitle);
            maintitles = v.findViewById(R.id.maintitles);
            maintitles2 = v.findViewById(R.id.maintitles2);
            //menuView=(LinearLayout)v.findViewById(R.id.menuview);

        }
    }

    public HomeMenuAdapter(List<homemenu> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public HomeMenuAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String language = preferences.getString(Constants.SETLANG, "en");
        dialog = new Dialog(context);
        if (language.equalsIgnoreCase("ar")) {
            String dateCheck = movies.get(position).getDate();
            String date_after = Constants.formateDateFromstring("yyyy-MM-dd", "dd MMMM yyyy", dateCheck);
            holder.toptitle.setText(context.getString(R.string.exciting_meal_string_ar) + " " + date_after);

            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            holder.toptitle.setTypeface(custom_font_azab);
            holder.title.setTypeface(custom_font_azab);
            holder.maintitles.setTypeface(custom_font_azab);
            holder.title2.setTypeface(custom_font_azab);
            holder.maintitles2.setTypeface(custom_font_azab);

            Drawable img = context.getResources().getDrawable(R.drawable.ic_done_black_24dp);
            img.setBounds(0, 0, 60, 60);
            holder.maintitles.setCompoundDrawables(null, null, img, null);
            holder.maintitles2.setCompoundDrawables(null, null, img, null);
            holder.title.setGravity(Gravity.START);
            holder.title.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.title.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            holder.title2.setGravity(Gravity.START);
            holder.title2.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.title2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            String dateCheck = movies.get(position).getDate();
            String date_after = Constants.formateDateFromstring("yyyy-MM-dd", "dd MMMM yyyy", dateCheck);
            holder.toptitle.setText(" " + context.getString(R.string.exciting_meal_string) + " " + date_after + " ");
        }
        holder.title.setText(movies.get(position).getMenu().getBdescription());
        holder.title2.setText(movies.get(position).getMenu().getLdescription());
        //holder.price.setText(movies.get(position).getPrice());
        // holder.brakfastlunch.setText(movies.get(position).getBothh());
        Glide.with(context)
                .load(movies.get(position).getMenu().getBimage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
      /*  Glide.with(context)
                .load(movies.get(position).getMenu().getBimage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);*/
        // holder.brakfastlunch.setText(movies.get(position).getBothh());
        Glide.with(context)
                .load(movies.get(position).getMenu().getLimage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image2);
     /*   Glide.with(context)
                .load(movies.get(position).getMenu().getLimage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);*/


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

