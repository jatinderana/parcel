package com.example.myapplication.FoodCart;

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
import com.example.myapplication.Model.Retrofit.BucketAddModel;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit.Log;

import java.util.ArrayList;
import java.util.List;


class FoodCartAdapter extends RecyclerView.Adapter<FoodCartAdapter.ViewHolder> {
    private final Context context;
    private final List<BucketAddModel> list;
    CartInterface listener;

    public FoodCartAdapter(Context context, List<BucketAddModel> list) {
        this.context = context;
        this.list = list;
        listener = (FoodCartActivity) context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_cart_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        final BucketAddModel model = list.get(i);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            holder.title.setTypeface(custom_font_azab);
            holder.quantity_text.setTypeface(custom_font_azab);
            holder.itemTV.setTypeface(custom_font_azab);

        }

        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(i);
                notifyDataSetChanged();
                listener.changeViewValue(model.getPrice(), Integer.parseInt(model.getCountValue()), i);
            }
        });

        List<String> textValue = new ArrayList<>();
        holder.title.setText(model.getName());
        holder.quantity_text.setText("1");
        holder.quantity_text.setText(model.getCountValue());
        final int[] count = {1};
        count[0] = Integer.parseInt(model.getCountValue());
        final int[] finalCountValue = {0};
        // finalCountValue[0] = model.getCountValue();
      /*  for (int k = 0; k < model.getList().size() - 1; k++) {
            textValue.add(model.getList().get(k).getName());

        }*/
        holder.itemTV.setText("This Food Will Be Delivered After "+model.getDtime()+" Hours");
        Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foodimage);
      /*  Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.foodimage);
*/
        holder.add_quantity.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                count[0] = count[0] + 1;
                holder.quantity_text.setText(String.valueOf(count[0]));
                model.setCountValue(String.valueOf(count[0] + 1));
                listener.buttonAddMethod(model.getPrice(), Integer.parseInt(model.getCountValue()), i);
            }
        });
        holder.remove_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count[0] > 1) {
                    count[0] = count[0] - 1;
                    model.setCountValue(String.valueOf(count[0]));
                    holder.quantity_text.setText(String.valueOf(count[0]));
                    listener.buttonRemoveMethod(model.getPrice(), Integer.parseInt(model.getCountValue()), i);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodimage, deleteIV;
        TextView title, remove_quantity, quantity_text, add_quantity, itemTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.foodimage);
            title = itemView.findViewById(R.id.title);
            remove_quantity = itemView.findViewById(R.id.remove_quantity);
            quantity_text = itemView.findViewById(R.id.quantity_text);
            add_quantity = itemView.findViewById(R.id.add_quantity);
            itemTV = itemView.findViewById(R.id.itemTV);
            deleteIV = itemView.findViewById(R.id.deleteIV);
        }
    }


    public interface CartInterface {
        void buttonAddMethod(String price, int countValue, int position);

        void buttonRemoveMethod(String id, int countValue, int position);


        void changeViewValue(String id, int countValue, int position);

    }
}
