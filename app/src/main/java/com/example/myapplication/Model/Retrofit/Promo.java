
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("promocode")
    @Expose
    private String promocode;
    @SerializedName("offertype")
    @Expose
    private String offertype;
    @SerializedName("offervalue")
    @Expose
    private String offervalue;
    @SerializedName("package")
    @Expose
    private String _package;
    @SerializedName("usagelimit")
    @Expose
    private String usagelimit;
    @SerializedName("sdate")
    @Expose
    private String sdate;
    @SerializedName("edate")
    @Expose
    private String edate;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("rdate")
    @Expose
    private String rdate;
    @SerializedName("rtime")
    @Expose
    private String rtime;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("content_en")
    @Expose
    private String contentEn;
    @SerializedName("content_ar")
    @Expose
    private String contentAr;


    @SerializedName("lang")
    @Expose
    private String lang;


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getContentAr() {
        return contentAr;
    }

    public void setContentAr(String contentAr) {
        this.contentAr = contentAr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromocode() {
        return promocode;
    }

    public void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    public String getOffertype() {
        return offertype;
    }

    public void setOffertype(String offertype) {
        this.offertype = offertype;
    }

    public String getOffervalue() {
        return offervalue;
    }

    public void setOffervalue(String offervalue) {
        this.offervalue = offervalue;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getUsagelimit() {
        return usagelimit;
    }

    public void setUsagelimit(String usagelimit) {
        this.usagelimit = usagelimit;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
