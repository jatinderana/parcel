package com.example.myapplication.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.CalenderView;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.Retrofit.Drinks;
import com.example.myapplication.Model.Retrofit.softDrinks;
import com.example.myapplication.R;

import java.util.List;

import static java.security.AccessController.getContext;

public class SoftDrinkAdapter extends RecyclerView.Adapter<SoftDrinkAdapter.MovieViewHolder> {

    //private List<Result> dataList;

    private List<Drinks> movies;
    private int rowLayout;
    private Context context;


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
              title = (TextView) v.findViewById(R.id.tv_date);
            // brakfast = (TextView) v.findViewById(R.id.discription);
            // price = (TextView) v.findViewById(R.id.price);
            //  title2 = (TextView) v.findViewById(R.id.title2);
            // image2=(ImageView)v.findViewById(R.id.foodimage2);
            // toptitle=(TextView) v.findViewById(R.id.toptitle);
            //menuView=(LinearLayout)v.findViewById(R.id.menuview);

        }
    }

    public SoftDrinkAdapter(List<Drinks> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public SoftDrinkAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {


        // holder.toptitle.setText("Exciting Meal on "+movies.get(position).getDate());

         holder.title.setText(movies.get(position).getTitle());
        //holder.price.setText(movies.get(position).getPrice());
        // holder.brakfastlunch.setText(movies.get(position).getBothh());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //   Intent intent1 = new Intent(context, CalenderView.class);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("title", movies.get(position).getTitle());
                editor.apply();
               // ((MainActivity)context).finish();
               // ((Activity)holder.title.getContext()).finish();
                //context.startActivity(intent1);
                // intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // context.startActivity(intent1);


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
}

