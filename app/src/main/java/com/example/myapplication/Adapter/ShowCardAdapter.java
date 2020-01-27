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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CheckoutPage;
import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.R;

import java.util.List;

public class ShowCardAdapter extends RecyclerView.Adapter<ShowCardAdapter.ViewHolder> {
    private final Context context;
    private final List<GetCardModel> list;
    LayoutInflater inflater;
    InterfaceCard interfaceCard;


    public ShowCardAdapter(Context context, List<GetCardModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        interfaceCard = (InterfaceCard) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.checkout_card_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final GetCardModel model = list.get(i);
        if (model.getCard_number().equalsIgnoreCase(Constants.NEWCARD)) {
            viewHolder.cardNumber.setText(context.getString(R.string.pay_new_card));
            viewHolder.expiryTV.setText("");
        } else {
            viewHolder.cardNumber.setText(model.getCard_number());
            viewHolder.expiryTV.setText(model.getCard_month() + "/" + model.getCard_year());
        }
        if(model.isOnChecked())
        {
            viewHolder.cardRB.setChecked(true);
        }

        else
        {
            viewHolder.cardRB.setChecked(false);
        }

        if(model.isOnChecked())
        {
            if(model.getCard_number().equalsIgnoreCase(Constants.NEWCARD))
            {
                interfaceCard.showView(model.getId(), i, true,model);
            }
        }
      /*  viewHolder.cardRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.cardRB.isChecked()) {
                    interfaceCard.changeButton(model.getId(), i, false,model);
                    Toast.makeText(context, "checked click", Toast.LENGTH_SHORT).show();
                } else {
                    interfaceCard.changeButton(model.getId(), i, true,model);
                    Toast.makeText(context, "unchecked click", Toast.LENGTH_SHORT).show();
                }
            }
        });



*/
        viewHolder.cardRB.setOnCheckedChangeListener(null);
        viewHolder.cardRB.setSelected(model.isOnChecked());
        viewHolder.cardRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    /*for (int j = 0; j < list.size(); j++) {
                        list.get(j).setOnChecked(false);
                    }

                    model.setOnChecked(true);
                    list.set(i,model);*/
                  interfaceCard.changeButton(model.getId(), i, true,model);

                    if(model.getCard_number().equalsIgnoreCase(Constants.NEWCARD))
                    {
                        interfaceCard.showView(model.getId(), i, true,model);
                    }
                    else
                    {
                        interfaceCard.hideView(model.getId(), i, true,model);
                    }
                }else {
                  interfaceCard.changeButton(model.getId(), i, false,model);
                    interfaceCard.hideView(model.getId(), i, true,model);
                    /*for (int j = 0; j < list.size(); j++) {
                        list.get(j).setOnChecked(true);
                    }

                    model.setOnChecked(false);
                    list.set(i,model);*/

                }
             //   notifyDataSetChanged();
            }
        });
        viewHolder.cardRB.setChecked(model.isOnChecked());



     /*   viewHolder.cardRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    model.setOnChecked(false);

                   // interfaceCard.changeButton(model.getId(), i, false,model);
                } else {
                   // interfaceCard.changeButton(model.getId(), i, true,model);
                    model.setOnChecked(true);
                }
                notifyDataSetChanged();
            }
        });*/

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);


        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            viewHolder.cardNumber.setTypeface(custom_font_azab);
            viewHolder.expiryTV.setTypeface(custom_font_azab);


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardNumber, expiryTV;
        CheckBox cardRB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardRB = itemView.findViewById(R.id.cardRB);
            cardNumber = itemView.findViewById(R.id.cardNumber);
            expiryTV = itemView.findViewById(R.id.expiryTV);
        }
    }


    public interface InterfaceCard {
        void changeButton(String id, int i, boolean checked, GetCardModel model);
        void showView(String id, int i, boolean checked, GetCardModel model);
        void hideView(String id, int i, boolean checked, GetCardModel model);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
