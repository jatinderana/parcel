package com.example.myapplication.ParcelHisotry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.ParcelHistoryNewModel;
import com.example.myapplication.R;
import com.example.myapplication.TrackPackage.FoodTrackingActivity;

import java.util.List;

class ParcelHistoryAdapter extends RecyclerView.Adapter<ParcelHistoryAdapter.ViewHolder> {
    private final ParcelBoxHistoryActivity context;
    private final List<ParcelHistoryNewModel.Data> historyList;

    public ParcelHistoryAdapter(ParcelBoxHistoryActivity context, List<ParcelHistoryNewModel.Data> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.parcel_history_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final ParcelHistoryNewModel.Data model = historyList.get(i);
        holder.orderIDTV.setText(model.getOrderid());
        holder.OrderDateTV.setText(model.getOrderdate());
        holder.amountValue.setText(model.getFinaltotal());
        holder.statusValue.setText(model.getCstatus());


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");

            holder.orderTV.setTypeface(custom_font_azab);
            holder.orderDate.setTypeface(custom_font_azab);
            holder.companyName.setTypeface(custom_font_azab);
            holder.companyTV.setTypeface(custom_font_azab);
            holder.deliveryTV.setTypeface(custom_font_azab);
            holder.floorNoTV.setTypeface(custom_font_azab);
            holder.deliveryAddress.setTypeface(custom_font_azab);
            holder.floorTV.setTypeface(custom_font_azab);
            holder.amountTV.setTypeface(custom_font_azab);
            holder.statusTV.setTypeface(custom_font_azab);
            holder.orderIDTV.setTypeface(custom_font_azab);
            holder.OrderDateTV.setTypeface(custom_font_azab);
            holder.amountValue.setTypeface(custom_font_azab);
            holder.actionTV.setTypeface(custom_font_azab);
            holder.statusValue.setTypeface(custom_font_azab);
        }

        if (model.getDeliveryaddress().size() > 0) {
            if (!model.getDeliveryaddress().contains(null)) {
                if (model.getDeliveryaddress().get(0).getCompany() != null) {
                    holder.companyTV.setText(model.getDeliveryaddress().get(0).getCompany());
                }
                if (model.getDeliveryaddress().get(0).getLocation() != null) {
                    holder.deliveryTV.setText(model.getDeliveryaddress().get(0).getLocation());
                }
                if (model.getDeliveryaddress().get(0).getFloor() != null) {
                    holder.floorNoTV.setText(model.getDeliveryaddress().get(0).getFloor());
                }

            }

        }

        holder.actionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodTrackingActivity.class);
                intent.putExtra("orderId", model.getOrderid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderIDTV;
        TextView OrderDateTV;
        TextView companyTV;
        TextView deliveryTV;
        TextView floorNoTV;
        TextView amountValue;
        TextView statusValue;
        TextView actionTV;
        TextView orderTV;
        TextView orderDate;
        TextView companyName;
        TextView deliveryAddress;
        TextView floorTV;
        TextView amountTV;
        TextView statusTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statusValue = itemView.findViewById(R.id.statusValue);
            amountValue = itemView.findViewById(R.id.amountValue);
            orderTV = itemView.findViewById(R.id.orderTV);
            floorNoTV = itemView.findViewById(R.id.floorNoTV);
            deliveryTV = itemView.findViewById(R.id.deliveryTV);
            companyTV = itemView.findViewById(R.id.companyTV);
            OrderDateTV = itemView.findViewById(R.id.OrderDateTV);
            orderIDTV = itemView.findViewById(R.id.orderIDTV);
            actionTV = itemView.findViewById(R.id.actionTV);

            orderDate = itemView.findViewById(R.id.orderDate);
            companyName = itemView.findViewById(R.id.companyName);
            deliveryAddress = itemView.findViewById(R.id.deliveryAddress);
            floorTV = itemView.findViewById(R.id.floorTV);
            amountTV = itemView.findViewById(R.id.amountTV);
            statusTV = itemView.findViewById(R.id.statusTV);

        }
    }
}
