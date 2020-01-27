package com.example.myapplication.Model.Retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreezePackageModel {

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("formtype")
    @Expose
    private String formtype;

    @SerializedName("subscriptionid")
    @Expose
    private String subscriptionid;
    @SerializedName("freezesdate")
    @Expose
    private String freezesdate;
    @SerializedName("freezeedate")
    @Expose
    private String freezeedate;

    @SerializedName("packageid")
    @Expose
    private String packageid;

    @SerializedName("packageprice")
    @Expose
    private String packageprice;


    @SerializedName("lang")
    @Expose
    private String lang;


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(String subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public String getFreezesdate() {
        return freezesdate;
    }

    public void setFreezesdate(String freezesdate) {
        this.freezesdate = freezesdate;
    }

    public String getFreezeedate() {
        return freezeedate;
    }

    public void setFreezeedate(String freezeedate) {
        this.freezeedate = freezeedate;
    }

    public String getPackageid() {
        return packageid;
    }

    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    public String getPackageprice() {
        return packageprice;
    }

    public void setPackageprice(String packageprice) {
        this.packageprice = packageprice;
    }
}
