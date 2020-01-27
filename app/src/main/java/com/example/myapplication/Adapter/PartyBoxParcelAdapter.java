package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
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
import com.example.myapplication.Model.Retrofit.BucketAddModel;
import com.example.myapplication.Model.Retrofit.ParcelAddModel;
import com.example.myapplication.ParcelGeathering.ParcelBoxActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class PartyBoxParcelAdapter extends RecyclerView.Adapter<PartyBoxParcelAdapter.MovieViewHolder> implements ParcelBoxAdapter.ParcelInterface {
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

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView moviesLayout;
        TextView title;
        TextView qunitity, bucketBT;
        ImageView image;
        LinearLayout weekly;
        LinearLayout menuView;


        public MovieViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            image = v.findViewById(R.id.foodimage);
            bucketBT = v.findViewById(R.id.bucketBT);

            //menuView=(LinearLayout)v.findViewById(R.id.menuview);

        }
    }

    public PartyBoxParcelAdapter(List<ParcelAddModel> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

        listener = (ParcelBoxActivity) context;
    }

    @Override
    public PartyBoxParcelAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
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

      /*  Glide.with(context)
                .load(movies.get(position).getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);
*/

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
/*

                Glide.with(context)
                        .load(movies.get(position).getImage())
                        .thumbnail(0.5f)
                        .transition(withCrossFade())
                        .apply(new RequestOptions().override(100, 100)
                                .placeholder(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background).centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                        ).into(images);
*/

                dialog.show();

            }
        });

        holder.bucketBT.setOnClickListener(new View.OnClickListener() {
            private ParcelBoxAdapter adapter;

            @Override
            public void onClick(View v) {

                Button cancel, send;
                TextView titleTV, selectBT, titleBelowTV, nextBT, closeBT;
                final EditText etdate;
                RecyclerView parcelRV;
                RelativeLayout relativeLayout1, relativeLayout2;

                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.parcel_box_item);
                titleTV = dialog.findViewById(R.id.titleTV);
                nextBT = dialog.findViewById(R.id.nextBT);
                closeBT = dialog.findViewById(R.id.closeBT);
                bucketBTView = nextBT;
                titleBelowTV = dialog.findViewById(R.id.titleBelowTV);
                parcelRV = dialog.findViewById(R.id.parcelRV);
                //  adapter = new ParcelBoxAdapter(context, model.getParty(), PartyBoxParcelAdapter.this);
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


                closeBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                nextBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!hideButton) {
                            listener.buttonAddMethod(model.getId(), model.getTitleEn(), bucketList);
                            dialog.cancel();

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
        BucketAddModel model = new BucketAddModel();
        model.setId(id);
        model.setName(titleEn);
        bucketList.remove(model);
    }

    @Override
    public void removeBucket(String id, String titleEn, int count) {

    }

    @Override
    public void addBucket(String id, String titleEn, int count) {
        BucketAddModel model = new BucketAddModel();
        model.setId(id);
        model.setName(titleEn);
        bucketList.add(model);
    }

    @Override
    public void addPrice(String id, String titleEn, int count, String price, String image, int countAdd, int countReal , String dtime) {



    }

    @Override
    public void removePrice(String id, String titleEn, int count, String price, String image, int countAdd, int countReal) {

    }


    public interface ParcelInterface {
        void buttonAddMethod(String id, String titleEn, List<BucketAddModel> list);

        void buttonRemoveMethod(String id, String titleEn);

    }
}

