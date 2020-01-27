package com.example.myapplication.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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

class ParcelMenuAdapter extends RecyclerView.Adapter<ParcelMenuAdapter.ViewHolder> {
    private final Context context;
    private final List<Item> items;
    Dialog dialog;

    public ParcelMenuAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.parecel_menu_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final Item model = items.get(i);
        holder.title.setText(model.getTitle());
        holder.discription.setText(model.getDescription());
        Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

/*
        Glide.with(context)
                .load(model.getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(holder.image);*/

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
                images =  dialog.findViewById(R.id.partyimage);
                title =  dialog.findViewById(R.id.titles);
                price =  dialog.findViewById(R.id.price);
                amintitle =  dialog.findViewById(R.id.maintitle);
                close =  dialog.findViewById(R.id.close);

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

             /*   Glide.with(context)
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
        TextView title;
        TextView brakfastlunch;
        ImageView image;

        public ViewHolder(@NonNull View v) {
            super(v);
            discription =  v.findViewById(R.id.discription);
            title =  v.findViewById(R.id.title);
            // brakfastlunch =  v.findViewById(R.id.brakfastlunch);
            image =  v.findViewById(R.id.foodimage);
        }
    }
}
