
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Canupgrade {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_en")
    @Expose
    private String titleEN;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("breakfast")
    @Expose
    private String breakfast;
    @SerializedName("lunch")
    @Expose
    private String lunch;
    @SerializedName("bothh")
    @Expose
    private String bothh;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("rdate")
    @Expose
    private String rdate;
    @SerializedName("rtime")
    @Expose
    private String rtime;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;


    @SerializedName("lang")
    @Expose
    private String lang;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getBothh() {
        return bothh;
    }

    public void setBothh(String bothh) {
        this.bothh = bothh;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }
}
