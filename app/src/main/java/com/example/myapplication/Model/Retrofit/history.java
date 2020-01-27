
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class history {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("packageid")
    @Expose
    private String packageid;
    @SerializedName("packagename")
    @Expose
    private String packagename;
    @SerializedName("packageduration")
    @Expose
    private String packageduration;
    @SerializedName("stype")
    @Expose
    private String stype;
    @SerializedName("people")
    @Expose
    private String people;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("sdate")
    @Expose
    private String sdate;
    @SerializedName("edate")
    @Expose
    private String edate;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("upgrade")
    @Expose
    private String upgrade;
    @SerializedName("freeze")
    @Expose
    private String freeze;
    @SerializedName("freezedates")
    @Expose
    private String freezedates;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("orderdate")
    @Expose
    private String orderdate;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("softdrink")
    @Expose
    private String softdrink;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPackageid() {
        return packageid;
    }

    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getPackageduration() {
        return packageduration;
    }

    public void setPackageduration(String packageduration) {
        this.packageduration = packageduration;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getFreezedates() {
        return freezedates;
    }

    public void setFreezedates(String freezedates) {
        this.freezedates = freezedates;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSoftdrink() {
        return softdrink;
    }

    public void setSoftdrink(String softdrink) {
        this.softdrink = softdrink;
    }
}
