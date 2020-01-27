package com.example.myapplication.Model.Retrofit;

import android.os.Parcel;
import android.os.Parcelable;

public class BucketAddModel implements Parcelable {

    String Id;
    String name;
    String price;
    String image;
    String countValue;
    String dtime;

    public BucketAddModel(Parcel in) {
        Id = in.readString();
        name = in.readString();
        price = in.readString();
        image = in.readString();
        countValue = in.readString();
        dtime = in.readString();
    }

    public static final Creator<BucketAddModel> CREATOR = new Creator<BucketAddModel>() {
        @Override
        public BucketAddModel createFromParcel(Parcel in) {
            return new BucketAddModel(in);
        }

        @Override
        public BucketAddModel[] newArray(int size) {
            return new BucketAddModel[size];
        }
    };

    public BucketAddModel() {

    }

    public String getCountValue() {
        return countValue;
    }

    public void setCountValue(String countValue) {
        this.countValue = countValue;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(image);
        dest.writeString(countValue);
        dest.writeString(dtime);
    }
}
