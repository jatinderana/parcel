package com.example.myapplication.TrackPackage;

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
import com.example.myapplication.Model.Retrofit.ParcelHistoryNewModel;
import com.example.myapplication.R;

import java.util.List;


class FoodTrackingAdapter extends RecyclerView.Adapter<FoodTrackingAdapter.ViewHolder> {
    private final Context context;
    private final List<ParcelHistoryNewModel.Items> items;
    private String finalTotal;

    public FoodTrackingAdapter(Context context, List<ParcelHistoryNewModel.Items> items, String finalTotal) {
        this.context = context;
        this.items = items;
        this.finalTotal = finalTotal;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_track_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ParcelHistoryNewModel.Items model = items.get(i);
        viewHolder.titleValue.setText(model.getQty());
        viewHolder.totalValue.setText(model.getPrice());
        viewHolder.topTitle.setText(model.getTitle());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");

            viewHolder.titleValue.setTypeface(custom_font_azab);
            viewHolder.totalValue.setTypeface(custom_font_azab);
            viewHolder.topTitle.setTypeface(custom_font_azab);
            viewHolder.description.setTypeface(custom_font_azab);
            viewHolder.title.setTypeface(custom_font_azab);
            viewHolder.total.setTypeface(custom_font_azab);
        }

        Glide.with(context)
                .load(model.getImage())
                .placeholder(R.color.white)
                .thumbnail(0.5f)
                .override(200, 200)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.foodImage);

       /* Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(viewHolder.foodImage);*/
        //  viewHolder.totalValue.setText(model.getQty());
      /*  if (model.getOptions().size() > 0) {

            String listString = "";

            for (ParcelHistoryModel.Option s : model.getOptions())
            {
                listString += s.getTitle() + ", ";
            }


            Glide.with(context)
                    .load(model.getOptions().get(0).getImage())
                    .placeholder(R.color.white)
                    .thumbnail(0.5f)
                    .override(200, 200)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(viewHolder.foodImage);

            System.out.println(listString);
            viewHolder.description.setText(listString);
        }*/
        viewHolder.description.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView title;
        TextView titleValue;
        TextView total;
        TextView totalValue;
        TextView description;
        TextView topTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.foodImage);
            title = itemView.findViewById(R.id.title);
            titleValue = itemView.findViewById(R.id.titleValue);
            total = itemView.findViewById(R.id.total);
            totalValue = itemView.findViewById(R.id.totalValue);
            description = itemView.findViewById(R.id.description);
            topTitle = itemView.findViewById(R.id.topTitle);

        }
    }
}
