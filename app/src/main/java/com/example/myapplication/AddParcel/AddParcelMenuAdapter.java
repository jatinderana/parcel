package com.example.myapplication.AddParcel;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Model.Retrofit.Item;
import com.example.myapplication.R;

import java.util.List;


class AddParcelMenuAdapter extends RecyclerView.Adapter<AddParcelMenuAdapter.ViewHolder> {
    private final Context context;
    private final List<Item> items;
    Dialog dialog;
    AddListener listener;
    private List<String> restDates;
    private String restDays;
    private String duration;
    int total = 0;

    public AddParcelMenuAdapter(Context context, List<Item> items, AddParcelCafe addParcelCafe, List<String> restDates, String restDays, String duration) {
        this.context = context;
        this.items = items;
        listener = addParcelCafe;
        this.restDates = restDates;
        this.restDays = restDays;
        this.duration = duration;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_parecel_menu_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        final int[] itemValue = {0};
        final int[] count = {0};
        final Item model = items.get(i);
        holder.title.setText(model.getTitle());
        holder.discription.setText(model.getPrice());
        holder.maintitles2.setText(duration);
        Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


      /*  Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);*/

        holder.addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemValue[0] = itemValue[0] + 1;
                holder.quantTV.setText(String.valueOf(itemValue[0]));
                listener.addValueMethod(itemValue[0]);
                listener.totalPriceMethod(holder.quantTV.getText().toString(), model.getPrice(), restDates.size(), restDays, false, model.getId(), itemValue[0], "no");
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    itemValue[0] = itemValue[0] + 1;
                    holder.quantTV.setText(String.valueOf(itemValue[0]));
                    listener.addValueMethod(itemValue[0]);
                    listener.totalPriceMethod(holder.quantTV.getText().toString(), model.getPrice(), restDates.size(), restDays, true, model.getId(), itemValue[0], "yes");
                } else {
                    if (itemValue[0] != 0) {
                        itemValue[0] = itemValue[0] - 1;
                        holder.quantTV.setText(String.valueOf(itemValue[0]));
                        listener.clearValueMethod();
                        listener.totalMinusPriceMethod(holder.quantTV.getText().toString(), model.getPrice(), restDates.size(), restDays, true, model.getId(), itemValue[0], "no");
                    }
                }

            }
        });

        holder.minusTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    if (itemValue[0] > 1) {
                        itemValue[0] = itemValue[0] - 1;
                        holder.quantTV.setText(String.valueOf(itemValue[0]));
                        listener.clearValueMethod();
                        listener.totalMinusPriceMethod(holder.quantTV.getText().toString(), model.getPrice(), restDates.size(), restDays, false, model.getId(), itemValue[0], "no");
                    }
                } else {
                    if (itemValue[0] != 0) {
                        itemValue[0] = itemValue[0] - 1;
                        holder.quantTV.setText(String.valueOf(itemValue[0]));
                        listener.clearValueMethod();
                        listener.totalMinusPriceMethod(holder.quantTV.getText().toString(), model.getPrice(), restDates.size(), restDays, false, model.getId(), itemValue[0], "no");
                    }
                }

            }
        });

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
                images = dialog.findViewById(R.id.partyimage);
                title = dialog.findViewById(R.id.titles);
                price = dialog.findViewById(R.id.price);
                amintitle = dialog.findViewById(R.id.maintitle);
                close = dialog.findViewById(R.id.close);
                amintitle.setText(model.getTitle());
                title.setText(model.getDescription());
                price.setText(model.getPrice());
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                Glide.with(context)
                        .load(model.getImage())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(images);


              /*  Glide.with(context)
                        .load(model.getImage())
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

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView discription;
        TextView title, quantTV;
        TextView brakfastlunch;
        TextView maintitles2;
        ImageView image, addTV, minusTV;
        CheckBox checkBox;

        public ViewHolder(@NonNull View v) {
            super(v);
            discription = v.findViewById(R.id.discription);
            title = v.findViewById(R.id.title);
            quantTV = v.findViewById(R.id.quantTV);
            // brakfastlunch = (TextView) v.findViewById(R.id.brakfastlunch);
            image = v.findViewById(R.id.foodimage);
            addTV = v.findViewById(R.id.addTV);
            minusTV = v.findViewById(R.id.minusTV);
            checkBox = v.findViewById(R.id.checkBox);
            maintitles2 = v.findViewById(R.id.maintitles2);
        }
    }

    interface AddListener {
        void addValueMethod(int total);

        void clearValueMethod();

        void totalPriceMethod(String text, String price, int size, String restDays, boolean fromCheckBox, String id, int count, String checkBoxVal);


        void totalMinusPriceMethod(String text, String price, int size, String restDays, boolean fromCheck, String id, int count, String checkBoxVal);
    }
}
