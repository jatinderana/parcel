package com.example.myapplication.ParcelGeathering;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.myapplication.Model.Retrofit.BucketAddModel;

import java.util.ArrayList;
import java.util.List;

public class FoodCartModel implements Parcelable {

    String id = "";
    String title = "";
    String image = "";
    String price = "";
    String dtime = "";
    int countValue = 1;
    List<BucketAddModel> list = new ArrayList<>();

    protected FoodCartModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        price = in.readString();
        dtime = in.readString();
        countValue = in.readInt();
        list = in.createTypedArrayList(BucketAddModel.CREATOR);
    }

    public static final Creator<FoodCartModel> CREATOR = new Creator<FoodCartModel>() {
        @Override
        public FoodCartModel createFromParcel(Parcel in) {
            return new FoodCartModel(in);
        }

        @Override
        public FoodCartModel[] newArray(int size) {
            return new FoodCartModel[size];
        }
    };

    public int getCountValue() {
        return countValue;
    }

    public void setCountValue(int countValue) {
        this.countValue = countValue;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public FoodCartModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BucketAddModel> getList() {
        return list;
    }

    public void setList(List<BucketAddModel> list) {
        this.list = list;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeString(dtime);
        dest.writeInt(countValue);
        dest.writeTypedList(list);
    }
}
