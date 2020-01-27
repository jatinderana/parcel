package com.example.myapplication.FaqPage;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Constants;
import com.example.myapplication.Model.Retrofit.ParcelAddModel;
import com.example.myapplication.R;
import com.example.myapplication.Views.DropDownAnim;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {
    private final FaqActivtiy context;
    private final List<ParcelAddModel> list;
    private final SparseBooleanArray mCollapsedStatus;

    public FaqAdapter(FaqActivtiy context, List<ParcelAddModel> body) {
        this.context = context;
        this.list = body;
        mCollapsedStatus = new SparseBooleanArray();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.faq_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        ParcelAddModel model = list.get(i);
        viewHolder.questionTV.setText(model.getTitle());
        viewHolder.answerTV.setText(Html.fromHtml(model.getDescription()));
       /* viewHolder.expandText.setText(model.getTitle());

        viewHolder.expandText.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                Toast.makeText(context, isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });*/

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String languageValue = preferences.getString(Constants.SETLANG, "en");
        if (languageValue.equalsIgnoreCase("ar")) {
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/jameel_nori_kashida.ttf");
            Typeface custom_font_azab = Typeface.createFromAsset(context.getAssets(), "arajozoor_regular.otf");
            viewHolder.questionTV.setTypeface(custom_font_azab);
            viewHolder.answerTV.setTextSize(24);

            /*first.setTextSize(20);
            last.setTextSize(20);
            input_email.setTextSize(20);
            input_phone.setTextSize(20);
            subject.setTextSize(20);
            messages.setTextSize(20);*/
        }

       viewHolder.mainRL.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (viewHolder.answerTV.getVisibility() == View.VISIBLE) {
                  // collapse(viewHolder.answerTV);
                   viewHolder.answerTV.setVisibility(View.GONE);
               } else if (viewHolder.answerTV.getVisibility() == View.GONE) {
               //    expand(viewHolder.answerTV);
                   viewHolder.answerTV.setVisibility(View.VISIBLE);
               }
           }
       });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionTV;
        TextView answerTV;
        ExpandableTextView expandText;
        TextView expandable_text;
        RelativeLayout mainRL;
        AppCompatImageButton expand_collapse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            answerTV = itemView.findViewById(R.id.answerTV);
            questionTV = itemView.findViewById(R.id.questionTV);
            expandText = itemView.findViewById(R.id.expand_text_view);
            expandable_text = itemView.findViewById(R.id.expandable_text);
            expand_collapse = itemView.findViewById(R.id.expand_collapse);
            mainRL = itemView.findViewById(R.id.mainRL);
        }
    }

    public void expand(final View v) {
        final int sourceHeight = v.getLayoutParams().height;
        final int targetHeight = context.getResources().getDimensionPixelSize(R.dimen.notification_height);//v.getMeasuredHeight();
        DropDownAnim a = new DropDownAnim(v, sourceHeight, targetHeight, true);
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Your code on end of animation
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.setVisibility(View.INVISIBLE);
        v.startAnimation(a);
    }

    public void collapse(final View v) {
        final int sourceHeight = v.getLayoutParams().height;
        final int targetHeight = v.getMeasuredHeight();
        DropDownAnim a = new DropDownAnim(v, sourceHeight, targetHeight, false);
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Your code on end of animation
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(a);
    }


}
