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
import com.example.myapplication.Retrofit.Log;

import java.util.List;


class ParcelBoxItemAdapter extends RecyclerView.Adapter<ParcelBoxItemAdapter.ViewHolder> {
    private final Context context;
    private final List<ParcelAddModel.Party> party;
    private int count = 0;
    PartyBoxAdapter listener;
    String dtime1;

    public ParcelBoxItemAdapter(Context context, List<ParcelAddModel.Party> party, PartyBoxAdapter adapter,String dtime) {
        this.context = context;
        this.party = party;
        listener = adapter;
        dtime1= dtime;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.parcel_adapter_box_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final int[] countAdd = {1};
        final int[] countLocal = {0};
        final ParcelAddModel.Party model = party.get(i);
        Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.foodimage);
    /*    Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(viewHolder.foodimage);
*/
        viewHolder.title.setText(model.getTitle());

        viewHolder.priceTV.setText(" ﷼ " + model.getPrice());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            viewHolder.title.setTypeface(custom_font_azab);
            viewHolder.selectBT.setTypeface(custom_font_azab);
            viewHolder.priceTV.setTypeface(custom_font_azab);
            viewHolder.priceTV.setText(context.getString(R.string.price_arabic) + " " + model.getPrice());
        }

        viewHolder.selectBT.setOnClickListener(new View.OnClickListener() {
            private boolean checkButton = false;

            @Override
            public void onClick(View v) {

                if (!checkButton) {
                    viewHolder.selectBT.setText(context.getString(R.string.added_to_bucket));
                    checkButton = true;
                    count = count + 1;

                    listener.addPrice(model.getId(), model.getTitle(), count,model.getPrice(),model.getImage(), countAdd[0], countLocal[0],dtime1);
                    listener.addBucket(model.getId(), model.getTitle(), count);
                } else {
                    viewHolder.selectBT.setText(context.getString(R.string.add_to_bucket));
                    checkButton = false;
                    count = count - 1;
                    listener.removeBucket(model.getId(), model.getTitle(), count);
                }
             /*   if (count == 4 || count == 6) {
                    listener.buttonShowMethod(model.getId(), model.getTitle());

                } else {
                    listener.buttonHideMethod(model.getId(), model.getTitle());
                }*/
            }
        });

        viewHolder.add_quantity.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                countLocal[0] = countLocal[0] +1;
                count = count + 1;
                viewHolder.quantity_text.setText(String.valueOf(countLocal[0]));
                listener.addPrice(model.getId(), model.getTitle(), count,model.getPrice(), model.getImage(), countAdd[0],countLocal[0],dtime1);
            }
        });


        viewHolder.remove_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countAdd[0] = countAdd[0] -1;
                if(count>0)
                {
                    if(countLocal[0]>0)
                    {
                        countLocal[0] = countLocal[0] -1;
                        count = count - 1;
                        viewHolder.quantity_text.setText(String.valueOf(countLocal[0]));
                        listener.removePrice(model.getId(), model.getTitle(), count,model.getPrice(),model.getImage(),countAdd[0],countLocal[0]);
                    }

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
        TextView priceTV,remove_quantity,quantity_text,add_quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.foodimage);
            title = itemView.findViewById(R.id.title);
            selectBT = itemView.findViewById(R.id.selectBT);
            priceTV = itemView.findViewById(R.id.priceTV);
            remove_quantity = itemView.findViewById(R.id.remove_quantity);
            quantity_text = itemView.findViewById(R.id.quantity_text);
            add_quantity = itemView.findViewById(R.id.add_quantity);

        }
    }


    interface ParcelInterface {
        void buttonShowMethod(String id, String titleEn);

        void buttonHideMethod(String id, String titleEn);

        void removeBucket(String id, String titleEn, int count);

        void addPrice(String id, String titleEn, int count,String price);

        void addBucket(String id, String titleEn, int count);
    }
}
