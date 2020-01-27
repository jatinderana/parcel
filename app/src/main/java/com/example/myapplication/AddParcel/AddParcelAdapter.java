package com.example.myapplication.AddParcel;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Model.Retrofit.Parcel;
import com.example.myapplication.R;

import java.util.List;

public class AddParcelAdapter extends RecyclerView.Adapter<AddParcelAdapter.MovieViewHolder> {
    //private List<Result> dataList;

    private List<Parcel> list;
    private int rowLayout;
    private AddParcelCafe addParcelCafe;
    private Context context;
    private List<String> restDates;
    private String restDays;
    private String duration;
    Dialog dialog;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView toptitle;

        ImageView image;
        LinearLayout weekly;
        RecyclerView foodRV;


        public MovieViewHolder(View v) {
            super(v);

            toptitle = (TextView) v.findViewById(R.id.toptitle);
            // brakfastlunch = (TextView) v.findViewById(R.id.brakfastlunch);
            foodRV = v.findViewById(R.id.foodRV);
            // weekly=(LinearLayout)v.findViewById(R.id.weekly);

        }
    }

    public AddParcelAdapter(List<Parcel> list, int rowLayout, AddParcelCafe addParcelCafe, Context context, List<String> restDates, String restDays, String duration) {
        this.list = list;
        this.rowLayout = rowLayout;
        this.addParcelCafe = addParcelCafe;
        this.context = context;
        this.restDates = restDates;
        this.restDays = restDays;
        this.duration = duration;
    }

    @Override
    public AddParcelAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        Parcel parcel = list.get(position);
        holder.foodRV.setLayoutManager(new LinearLayoutManager(context));
        holder.foodRV.setAdapter(new AddParcelMenuAdapter(context, parcel.getItems(), addParcelCafe, restDates,restDays,duration));
        // holder.brakfast.setText(movies.get(position).getItems().get(position).getDescription());
        holder.toptitle.setText(list.get(position).getCategory());

        //holder.title.append(movies.get(position).getItems().get(i));

    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

