package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
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
import com.example.myapplication.Model.Retrofit.Parcel;
import com.example.myapplication.R;

import java.util.List;

public class ParcelC extends RecyclerView.Adapter<ParcelC.MovieViewHolder> {
    //private List<Result> dataList;

    private List<Parcel> list;
    private int rowLayout;
    private Context context;
    Dialog dialog;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {


        TextView toptitle;

        ImageView image;
        LinearLayout weekly;
        RecyclerView foodRV;


        public MovieViewHolder(View v) {
            super(v);

            toptitle =  v.findViewById(R.id.toptitle);
            // brakfastlunch = (TextView) v.findViewById(R.id.brakfastlunch);
            foodRV = v.findViewById(R.id.foodRV);
            // weekly=(LinearLayout)v.findViewById(R.id.weekly);

        }
    }

    public ParcelC(List<Parcel> list, int rowLayout, Context context) {
        this.list = list;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    @Override
    public ParcelC.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        Parcel parcel = list.get(position);
        holder.foodRV.setLayoutManager(new LinearLayoutManager(context));
        holder.foodRV.setAdapter(new ParcelMenuAdapter(context,parcel.getItems()));
        // holder.brakfast.setText(movies.get(position).getItems().get(position).getDescription());
        holder.toptitle.setText(list.get(position).getCategory());

        //holder.title.append(movies.get(position).getItems().get(i));


      /*  holder.title.setText(list.get(position).getItems().get(position).getTitle());
        Glide.with(context)
                .load(list.get(position).getItems().get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button cancel, send;
                TextView title, price, amintitle, close;
                final Spinner spine, spin;
                final EditText etdate;
                final ImageView images;
                RelativeLayout relativeLayout1, relativeLayout2;
                dialog = new Dialog(context);
                //  dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.party);
                images = (ImageView) dialog.findViewById(R.id.partyimage);
                title = (TextView) dialog.findViewById(R.id.titles);
                price = (TextView) dialog.findViewById(R.id.price);
                amintitle = (TextView) dialog.findViewById(R.id.maintitle);
                close = (TextView) dialog.findViewById(R.id.close);

                amintitle.setText(list.get(position).getItems().get(position).getTitle());
                title.setText(list.get(position).getItems().get(position).getDescription());
                price.setText(list.get(position).getItems().get(position).getPrice());
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                Glide.with(context)
                        .load(list.get(position).getItems().get(position).getImage())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(images);


                dialog.show();


            }
        });*/


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

