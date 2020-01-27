package com.example.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Model.Retrofit.ParcelAddModel;
import com.example.myapplication.R;

import java.util.List;


class PartyItemBoxAdapter extends RecyclerView.Adapter<PartyItemBoxAdapter.ViewHolder> {
    private final Context context;
    private final List<ParcelAddModel.Party> party;
    private int count = 0;


    public PartyItemBoxAdapter(Context context, List<ParcelAddModel.Party> party) {
        this.context = context;
        this.party = party;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.parcel_adapter_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final ParcelAddModel.Party model = party.get(i);
        Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.foodimage);
/*
        Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(viewHolder.foodimage);*/

        viewHolder.title.setText(model.getTitleEn());
        viewHolder.selectBT.setOnClickListener(new View.OnClickListener() {
            private boolean checkButton = false;

            @Override
            public void onClick(View v) {

                if (!checkButton) {
                    viewHolder.selectBT.setText("Selected");
                    checkButton = true;
                    count = count + 1;

                } else {
                    viewHolder.selectBT.setText("Select");
                    checkButton = false;
                    count = count - 1;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return party.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodimage;
        TextView title;
        TextView selectBT;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.foodimage);
            title = itemView.findViewById(R.id.title);
            selectBT = itemView.findViewById(R.id.selectBT);

        }
    }


    interface ParcelInterface {
        void buttonShowMethod(String id, String titleEn);

        void buttonHideMethod(String id, String titleEn);

        void removeBucket(String id, String titleEn);

        void addBucket(String id, String titleEn);
    }
}
