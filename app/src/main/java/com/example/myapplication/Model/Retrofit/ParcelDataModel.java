
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParcelDataModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ParcelAddModel> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ParcelAddModel> getData() {
        return data;
    }

    public void setData(List<ParcelAddModel> data) {
        this.data = data;
    }

}
