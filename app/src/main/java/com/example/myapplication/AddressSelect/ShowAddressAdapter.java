package com.example.myapplication.AddressSelect;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.GetAddressModel;
import com.example.myapplication.R;

import java.util.List;

class ShowAddressAdapter extends RecyclerView.Adapter<ShowAddressAdapter.MyViewHolder> {
    private final SelectAddressActivity selectAddressActivity;
    private final List<GetAddressModel.DataAddress> data;
    private String addressLimit;
    ShowAddressListener listener;

    public ShowAddressAdapter(SelectAddressActivity selectAddressActivity, List<GetAddressModel.DataAddress> data, String addressLimit) {
        this.selectAddressActivity = selectAddressActivity;
        this.data = data;
        this.addressLimit = addressLimit;
        listener = selectAddressActivity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_address_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final GetAddressModel.DataAddress model = data.get(i);
        if (model.getIsdefault().equalsIgnoreCase("yes")) {
            myViewHolder.btnnext.setText(selectAddressActivity.getString(R.string.selected_new));
        }
        myViewHolder.locatioinTV.setText(model.getLocation());
        myViewHolder.comapnyTV.setText(selectAddressActivity.getString(R.string.company_name_string) +" " + model.getCompany());
        myViewHolder.officeTV.setText(selectAddressActivity.getString(R.string.office_no_string)+"  " + model.getOffice());
        myViewHolder.floorTV.setText(selectAddressActivity.getString(R.string.floor_no_tring) +"  "+ model.getFloor());
        final int addressInt = Integer.parseInt(addressLimit);
        myViewHolder.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressInt > 0) {
                    if (model.getIsdefault().equalsIgnoreCase("no")) {
                        listener.onShowaddress(model.getId());
                    }
                } else {
                    Toast.makeText(selectAddressActivity, selectAddressActivity.getString(R.string.no_of_attempts), Toast.LENGTH_SHORT).show();
                }
            }
        });



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(selectAddressActivity);
        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if(languageValue.equalsIgnoreCase("ar"))
        {
            Typeface custom_font = Typeface.createFromAsset(selectAddressActivity.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(selectAddressActivity.getAssets(), "arajozoor_regular.otf");
            myViewHolder.locatioinTV.setTypeface(custom_font_azab);
            myViewHolder.comapnyTV.setTypeface(custom_font_azab);
            myViewHolder.floorTV.setTypeface(custom_font_azab);
            myViewHolder.btnnext.setTypeface(custom_font_azab);
            myViewHolder.officeTV.setTypeface(custom_font_azab);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mapIV;
        TextView locatioinTV, comapnyTV, officeTV, floorTV;
        Button btnnext;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mapIV = itemView.findViewById(R.id.mapIV);
            locatioinTV = itemView.findViewById(R.id.locatioinTV);
            comapnyTV = itemView.findViewById(R.id.comapnyTV);
            officeTV = itemView.findViewById(R.id.officeTV);
            floorTV = itemView.findViewById(R.id.floorTV);
            btnnext = itemView.findViewById(R.id.btnnext);
        }
    }

    interface ShowAddressListener {
        void onShowaddress(String id);
    }
}
