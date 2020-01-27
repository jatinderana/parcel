package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
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
import com.example.myapplication.Model.Retrofit.Replace;
import com.example.myapplication.R;

import java.util.List;

public class ReplaceItemAdapter extends RecyclerView.Adapter<ReplaceItemAdapter.MovieViewHolder> {
    //private List<Result> dataList;

    private List<Replace> movies;
    private int rowLayout;
    private Context context;
    private String duration;
    Dialog dialog;
    private String menuId="";


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title;
        TextView brakfast;
        TextView price;
        TextView title2;
        TextView toptitle;
        TextView breakfastId;
        ImageView image, image2;
        LinearLayout weekly;
        LinearLayout menuView;


        public MovieViewHolder(View v) {
            super(v);
            title =  v.findViewById(R.id.tv_name);
            image =  v.findViewById(R.id.imageview);
            breakfastId =  v.findViewById(R.id.breakfastId);

            //menuView=(LinearLayout)v.findViewById(R.id.menuview);

        }
    }

    public ReplaceItemAdapter(List<Replace> movies, int rowLayout, Context context, String duration) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

        this.duration = duration;
    }

    @Override
    public ReplaceItemAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        final Replace data = movies.get(position);
        dialog = new Dialog(context);
        // holder.toptitle.setText("Exciting Meal on "+movies.get(position).getDate());
        if(duration.equalsIgnoreCase("breakfast"))
        {
            holder.title.setText(movies.get(position).getBdescription());
            holder.breakfastId.setText(context.getString(R.string.breakfast));
            Glide.with(context)
                    .load(movies.get(position).getBimage())
                    .crossFade()
                    .thumbnail(0.5f)
                    .override(200, 200)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
/*
            Glide.with(context)
                    .load(movies.get(position).getBimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(holder.image);*/

        }else
        {
            holder.title.setText(movies.get(position).getLdescription());
            holder.breakfastId.setText(context.getString(R.string.lunch));
            Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .crossFade()
                    .thumbnail(0.5f)
                    .override(200, 200)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
/*
            Glide.with(context)
                    .load(movies.get(position).getLimage())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(holder.image);*/
        }


        if (data.getCheck()) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_light));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInitialData(movies,data,position);
                setMenuId(data.getId());
                if (data.getCheck()) {
                  data.setCheck(false);
                } else {
                    data.setCheck(true);
                }
                movies.set(position,data);
                notifyDataSetChanged();
            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");




        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            holder.title.setTypeface(custom_font_azab);
            holder. breakfastId.setTypeface(custom_font_azab);
            Drawable img = context.getResources().getDrawable( R.drawable.ic_done_black_24dp );
            img.setBounds( 0, 0, 60, 60 );
            holder.breakfastId.setCompoundDrawables( null, null, img, null );

        }
    }

    public void setMenuId(String id) {
        menuId = id;

    }

    public String getMenuId()
    {
        return menuId;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void setInitialData(List<Replace> movies, Replace data, int position)
    {

        for(int i=0;i< movies.size();i++)
        {
            Replace replace = movies.get(i);
            replace.setCheck(false);
            movies.set(i,replace);
        }
    }
}

