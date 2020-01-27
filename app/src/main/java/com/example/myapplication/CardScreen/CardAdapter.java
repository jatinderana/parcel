package com.example.myapplication.CardScreen;

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

import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.R;

import java.util.List;

class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private final CardActivity context;
    private final List<GetCardModel> list;
    LayoutInflater inflater;
    InterfaceCard interfaceCard;


    public CardAdapter(CardActivity context, List<GetCardModel> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        interfaceCard = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.card_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final GetCardModel model = list.get(i);
        viewHolder.cardNumber.setText(model.getCard_number());
        viewHolder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceCard.deleteCard(model.getId(), i);
            }
        });
        if (model.getIs_default().equalsIgnoreCase("1")) {
            viewHolder.defaultTV.setVisibility(View.VISIBLE);
            viewHolder.deleteIV.setVisibility(View.GONE);
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);


        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            viewHolder.defaultTV.setTypeface(custom_font_azab);


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardIV, deleteIV;
        TextView cardNumber, defaultTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardIV = itemView.findViewById(R.id.cardIV);
            deleteIV = itemView.findViewById(R.id.deleteIV);
            cardNumber = itemView.findViewById(R.id.cardNumber);
            defaultTV = itemView.findViewById(R.id.defaultTV);
        }
    }


    interface InterfaceCard {
        void deleteCard(String id, int i);
    }
}
