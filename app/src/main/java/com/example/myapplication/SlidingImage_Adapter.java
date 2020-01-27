package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.Model.Retrofit.Promo;
import com.example.myapplication.PromoCode.PromoCodeActivity;

import java.util.ArrayList;


/**
 * Created by Parsania Hardik on 23/04/2016.
 */
public class SlidingImage_Adapter extends PagerAdapter {


    //  private ArrayList<ImageModel> imageModelArrayList;
    private ArrayList<Promo> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<Promo> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout
                .findViewById(R.id.image);

        if(imageModelArrayList.get(position).getMobile().isEmpty())
        {
            Glide.with(context)
                    .load(imageModelArrayList.get(position).getWebsite())
                    .placeholder(R.color.white)
                    .into(imageView);

           /* Glide.with(context)
                    .load(imageModelArrayList.get(position).getWebsite())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(imageView);*/

        }else
        {
            Glide.with(context)
                    .load(imageModelArrayList.get(position).getMobile())
                    .placeholder(R.color.white)
                    .into(imageView);
          /*  Glide.with(context)
                    .load(imageModelArrayList.get(position).getMobile())
                    .thumbnail(0.5f)
                    .transition(withCrossFade())
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    ).into(imageView);*/
        }


        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imageModelArrayList.isEmpty())
                {if(imageModelArrayList.get(position).getOffervalue()!=null)
                {

                    if (!imageModelArrayList.get(position).getOffervalue().isEmpty()) {
//                        Intent intent = new Intent(context, PromoCodeActivity.class);
//                        intent.putExtra("image", imageModelArrayList.get(position).getMobile());
//                        intent.putExtra("content", imageModelArrayList.get(position).getContentEn());
//                        intent.putExtra("contentAr", imageModelArrayList.get(position).getContentAr());
//                        context.startActivity(intent);
                    }
                }

                }

            }
        });
        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}