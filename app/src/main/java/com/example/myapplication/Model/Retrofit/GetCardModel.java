package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCardModel {

    @SerializedName("cvc")
    @Expose
    private String cvc;

    @SerializedName("rtime")
    @Expose
    private String rtime;

    @SerializedName("card_number")
    @Expose
    private String card_number;

    @SerializedName("rdate")
    @Expose
    private String rdate;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("card_month")
    @Expose
    private String card_month;

    @SerializedName("card_year")
    @Expose
    private String card_year;

    @SerializedName("is_default")
    @Expose
    private String is_default;

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("onChecked")
    @Expose
    private boolean onChecked = false;


    public boolean isOnChecked() {
        return onChecked;
    }

    public void setOnChecked(boolean onChecked) {
        this.onChecked = onChecked;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCard_month() {
        return card_month;
    }

    public void setCard_month(String card_month) {
        this.card_month = card_month;
    }

    public String getCard_year() {
        return card_year;
    }

    public void setCard_year(String card_year) {
        this.card_year = card_year;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
