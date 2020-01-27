package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;


class DialogAdaptorStudent extends BaseAdapter {
    Activity activity;

    private Activity context;
    private ArrayList<Dialogpojo> alCustom;
    private String sturl, checkstatus;
    Uri myUri;


    public DialogAdaptorStudent(Activity context, ArrayList<Dialogpojo> alCustom) {
        this.context = context;
        this.alCustom = alCustom;

    }

    @Override
    public int getCount() {
        return alCustom.size();

    }

    @Override
    public Object getItem(int i) {
        return alCustom.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.row_addapt, null, true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        // Title = preferences.getString("title", "");
        // duration = preferences.getString("time", "");
        // newdate = preferences.getString("dates", "");
        checkstatus = preferences.getString("status", "");
        // maintitle = preferences.getString("breakfast", "");
        // Toast.makeText(getApplicationContext(),"Status is"+checkstatus,Toast.LENGTH_LONG).show();


        TextView tvTitle = listViewItem.findViewById(R.id.tv_name);
        TextView lunchTV = listViewItem.findViewById(R.id.lunchTV);
        TextView title = listViewItem.findViewById(R.id.title);
        TextView tvTitle1 = listViewItem.findViewById(R.id.tv_name1);
        TextView tvSubject = listViewItem.findViewById(R.id.tv_type);
        TextView tvDuedate = listViewItem.findViewById(R.id.tv_desc);
        TextView tvDescription = listViewItem.findViewById(R.id.tv_class);
        ImageView image = listViewItem.findViewById(R.id.imageview);
        ImageView image1 = listViewItem.findViewById(R.id.imageview1);
        LinearLayout both = listViewItem.findViewById(R.id.bothlayout);
        View views = listViewItem.findViewById(R.id.view);

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");

            tvTitle.setTypeface(custom_font_azab);
            lunchTV.setTypeface(custom_font_azab);
            title.setTypeface(custom_font_azab);
            tvTitle1.setTypeface(custom_font_azab);
            tvSubject.setTypeface(custom_font_azab);


            Drawable img = context.getResources().getDrawable(R.drawable.ic_done_black_24dp);
            img.setBounds(0, 0, 60, 60);
            title.setCompoundDrawables(null, null, img, null);
            lunchTV.setCompoundDrawables(null, null, img, null);
           /* holder.title.setGravity(Gravity.START);
            holder.title.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.title.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            holder.title2.setGravity(Gravity.START);
            holder.title2.setTextDirection(View.TEXT_DIRECTION_RTL);
            holder.title2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);*/
        }


        // myUri = Uri.parse(alCustom.get(position).getImage());

        Glide.with(context)
                .load(alCustom.get(position).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
        /*Glide.with(context)
                .load(alCustom.get(position).getImage())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(image);
*/
        Glide.with(context)
                .load(alCustom.get(position).getDescripts())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image1);
/*
        Glide.with(context)
                .load(alCustom.get(position).getDescripts())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .apply(new RequestOptions().override(100, 100)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                ).into(image1);
*/

        //Toast.makeText(context,"images"+alCustom.get(position).getImage(),Toast.LENGTH_LONG).show();

        tvTitle.setText("" + alCustom.get(position).getTitles());
        tvTitle1.setText("" + alCustom.get(position).getSubjects());
        tvSubject.setText("Subject : " + alCustom.get(position).getSubjects());
        tvDuedate.setText("Due Date : " + alCustom.get(position).getDuedates());
        tvDescription.setText("Description : " + alCustom.get(position).getDescripts());
        if (checkstatus.equalsIgnoreCase(context.getString(R.string.lunch))) {
            title.setText(context.getString(R.string.lunch));
        }


        if (alCustom.get(position).getSubjects() == "null") {
            both.setVisibility(View.GONE);
            views.setVisibility(View.GONE);
        }


        return listViewItem;
    }

}

