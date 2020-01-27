package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.ParcelAddModel;
import com.example.myapplication.R;

import java.util.List;


class ParcelBoxAdapter extends RecyclerView.Adapter<ParcelBoxAdapter.ViewHolder> {
    private final Context context;
    private final List<ParcelAddModel.Party> party;
    private int count = 0;
    PartyBoxAdapter listener;

    public ParcelBoxAdapter(Context context, List<ParcelAddModel.Party> party, PartyBoxAdapter adapter) {
        this.context = context;
        this.party = party;
        listener =  adapter;
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


   /*     Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(viewHolder.foodimage);*/

        viewHolder.title.setText(model.getTitle());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            viewHolder.title.setTypeface(custom_font_azab);
            viewHolder.selectBT.setTypeface(custom_font_azab);
        }

        viewHolder.selectBT.setOnClickListener(new View.OnClickListener() {
            private boolean checkButton = false;

            @Override
            public void onClick(View v) {

                if (!checkButton) {
                    viewHolder.selectBT.setText(context.getString(R.string.seleted_string));
                    checkButton = true;
                    count = count + 1;
                    listener.addBucket(model.getId(), model.getTitle(),count);
                } else {
                    viewHolder.selectBT.setText(context.getString(R.string.select_string));
                    checkButton = false;
                    count = count - 1;
                    listener.removeBucket(model.getId(), model.getTitle(),count);
                }
                if (count == 4 || count == 6) {
                    listener.buttonShowMethod(model.getId(), model.getTitle());

                } else {
                    listener.buttonHideMethod(model.getId(), model.getTitle());
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

        void removeBucket(String id, String titleEn, int count);

        void addBucket(String id, String titleEn, int count);

        void addPrice(String id, String titleEn, int count, String price, String image, int countAdd, int countReal,String dtime);

        void removePrice(String id, String titleEn, int count, String price, String image, int countAdd, int countReal);
    }
}
