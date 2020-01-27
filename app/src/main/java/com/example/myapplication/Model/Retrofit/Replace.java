
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Replace {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("bdescription")
    @Expose
    private String bdescription;
    @SerializedName("bimage")
    @Expose
    private String bimage;
    @SerializedName("bprice")
    @Expose
    private String bprice;
    @SerializedName("ldescription")
    @Expose
    private String ldescription;
    @SerializedName("limage")
    @Expose
    private String limage;
    @SerializedName("check")
    @Expose
    private Boolean check = false;
    @SerializedName("lprice")
    @Expose
    private String lprice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBdescription() {
        return bdescription;
    }

    public void setBdescription(String bdescription) {
        this.bdescription = bdescription;
    }

    public String getBimage() {
        return bimage;
    }

    public void setBimage(String bimage) {
        this.bimage = bimage;
    }

    public String getBprice() {
        return bprice;
    }

    public void setBprice(String bprice) {
        this.bprice = bprice;
    }

    public String getLdescription() {
        return ldescription;
    }

    public void setLdescription(String ldescription) {
        this.ldescription = ldescription;
    }

    public String getLimage() {
        return limage;
    }

    public void setLimage(String limage) {
        this.limage = limage;
    }

    public String getLprice() {
        return lprice;
    }

    public void setLprice(String lprice) {
        this.lprice = lprice;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}
