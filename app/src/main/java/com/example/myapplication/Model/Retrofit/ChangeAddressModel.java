package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeAddressModel {
    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("addressid")
    @Expose
    private String addressId;

    @SerializedName("ctypeid")
    @Expose
    private String ctypeId;


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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCtypeId() {
        return ctypeId;
    }

    public void setCtypeId(String ctypeId) {
        this.ctypeId = ctypeId;
    }
}