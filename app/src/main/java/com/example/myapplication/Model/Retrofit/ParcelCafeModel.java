package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ParcelCafeModel {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Parcel> data = null;
    @SerializedName("restdates")
    @Expose
    private List<String> restdates = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Parcel> getData() {
        return data;
    }

    public void setData(List<Parcel> data) {
        this.data = data;
    }

    public List<String> getRestdates() {
        return restdates;
    }

    public void setRestdates(List<String> restdates) {
        this.restdates = restdates;
    }
}

