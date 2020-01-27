package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveAddressModel {

    @SerializedName("userid")
    @Expose
    private String useriId;

    @SerializedName("cid")
    @Expose
    private String cid;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("office")
    @Expose
    private String office;

    @SerializedName("floor")
    @Expose
    private String floor;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("lang")
    @Expose
    private String lang;

    public String getUseriId() {
        return useriId;
    }

    public void setUseriId(String useriId) {
        this.useriId = useriId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}