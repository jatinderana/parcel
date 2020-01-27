package com.example.myapplication.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.CalenderView;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Retrofit.Datum;
import com.example.myapplication.Model.Retrofit.PartyBoxModel;
import com.example.myapplication.Model.Retrofit.PartyModel;
import com.example.myapplication.Model.Retrofit.Promo;
import com.example.myapplication.Model.Retrofit.homemenu;
import com.example.myapplication.ParcelCafe;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.MovieViewHolder> {
    //private List<Result> dataList;

    private List<Promo> movies;
    private int rowLayout;
    private Context context;
    Dialog dialog;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title;
        TextView brakfast;
        TextView price;
        TextView title2;
        TextView toptitle;
        ImageView image,image2;
        LinearLayout weekly;
        LinearLayout menuView;


        public MovieViewHolder(View v) {
            super(v);
          //  title = (TextView) v.findViewById(R.id.title);
           // brakfast = (TextView) v.findViewById(R.id.discription);
           // price = (TextView) v.findViewById(R.id.price);
          //  title2 = (TextView) v.findViewById(R.id.title2);
            image=(ImageView)v.findViewById(R.id.imageView);
           // image2=(ImageView)v.findViewById(R.id.foodimage2);
           // toptitle=(TextView) v.findViewById(R.id.toptitle);
            //menuView=(LinearLayout)v.findViewById(R.id.menuview);

        }
    }

    public PromoAdapter(List<Promo> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public PromoAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {


        dialog = new Dialog(context);
       // holder.toptitle.setText("Exciting Meal on "+movies.get(position).getDate());
        //holder.title.setText(movies.get(position).getMenu().getBdescription());
       // holder.title2.setText(movies.get(position).getMenu().getLdescription());
        //holder.price.setText(movies.get(position).getPrice());
        // holder.brakfastlunch.setText(movies.get(position).getBothh());
        Glide.with(context)
                .load(movies.get(position).getMobile())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
/*

        Glide.with(context)
                .load(movies.get(position).getMobile())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);
*/




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

