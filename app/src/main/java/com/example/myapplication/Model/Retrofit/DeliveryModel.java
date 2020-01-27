package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryModel {

    @SerializedName("userid")
    @Expose
    private String useriId;


    @SerializedName("subscriptionid")
    @Expose
    private String subscriptionid;


    @SerializedName("lang")
    @Expose
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUseriId() {
        return useriId;
    }

    public void setUseriId(String useriId) {
        this.useriId = useriId;
    }

    public String getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(String subscriptionid) {
        this.subscriptionid = subscriptionid;
    }
}