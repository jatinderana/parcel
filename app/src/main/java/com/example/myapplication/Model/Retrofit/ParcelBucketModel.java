
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParcelBucketModel {

    @SerializedName("userid")
    @Expose
    private String userid;

    @SerializedName("pdate")
    @Expose
    private String pdate;

    @SerializedName("foodtype")
    @Expose
    private String foodtype;

    @SerializedName("ids")
    @Expose
    private String ids;

    @SerializedName("prices")
    @Expose
    private String prices;

    @SerializedName("qtys")
    @Expose
    private String qtys;

    @SerializedName("totals")
    @Expose
    private String totals;

    @SerializedName("daily")
    @Expose
    private String daily;

    @SerializedName("restdays")
    @Expose
    private String restdays;

    @SerializedName("finaltotal")
    @Expose
    private String finaltotal;


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

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getQtys() {
        return qtys;
    }

    public void setQtys(String qtys) {
        this.qtys = qtys;
    }

    public String getTotals() {
        return totals;
    }

    public void setTotals(String totals) {
        this.totals = totals;
    }

    public String getDaily() {
        return daily;
    }

    public void setDaily(String daily) {
        this.daily = daily;
    }

    public String getRestdays() {
        return restdays;
    }

    public void setRestdays(String restdays) {
        this.restdays = restdays;
    }

    public String getFinaltotal() {
        return finaltotal;
    }

    public void setFinaltotal(String finaltotal) {
        this.finaltotal = finaltotal;
    }
}
