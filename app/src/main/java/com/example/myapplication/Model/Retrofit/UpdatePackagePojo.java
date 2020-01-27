package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePackagePojo {
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
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
    @SerializedName("pdate")
    @Expose
    private Object pdate;
    @SerializedName("bdayreplace")
    @Expose
    private String bdayreplace;
    @SerializedName("bdoubleqty")
    @Expose
    private String bdoubleqty;
    @SerializedName("bparcelid")
    @Expose
    private String bparcelid;
    @SerializedName("ldayreplace")
    @Expose
    private String ldayreplace;
    @SerializedName("ldoubleqty")
    @Expose
    private String ldoubleqty;

    @SerializedName("uptype")
    @Expose
    private String uptype;
    @SerializedName("foodtype")
    @Expose
    private String foodtype;

    @SerializedName("lang")
    @Expose
    private String lang;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Object getPdate() {
        return pdate;
    }

    public void setPdate(Object pdate) {
        this.pdate = pdate;
    }

    public String getBdayreplace() {
        return bdayreplace;
    }

    public void setBdayreplace(String bdayreplace) {
        this.bdayreplace = bdayreplace;
    }

    public String getBdoubleqty() {
        return bdoubleqty;
    }

    public void setBdoubleqty(String bdoubleqty) {
        this.bdoubleqty = bdoubleqty;
    }


    public String getBparcelid() {
        return bparcelid;
    }

    public void setBparcelid(String bparcelid) {
        this.bparcelid = bparcelid;
    }

    public String getLdayreplace() {
        return ldayreplace;
    }

    public void setLdayreplace(String ldayreplace) {
        this.ldayreplace = ldayreplace;
    }

    public String getLdoubleqty() {
        return ldoubleqty;
    }

    public void setLdoubleqty(String ldoubleqty) {
        this.ldoubleqty = ldoubleqty;
    }


    public String getUptype() {
        return uptype;
    }

    public void setUptype(String uptype) {
        this.uptype = uptype;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
