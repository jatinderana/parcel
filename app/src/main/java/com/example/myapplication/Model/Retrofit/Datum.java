
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
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
    @SerializedName("replacecondition")
    @Expose
    private String replacecondition;
    @SerializedName("giftcondition")
    @Expose
    private String giftcondition;
    @SerializedName("rdate")
    @Expose
    private String rdate;
    @SerializedName("rtime")
    @Expose
    private String rtime;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("isactivepackage")
    @Expose
    private String isactivepackage;

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

    public String getReplacecondition() {
        return replacecondition;
    }

    public void setReplacecondition(String replacecondition) {
        this.replacecondition = replacecondition;
    }

    public String getGiftcondition() {
        return giftcondition;
    }

    public void setGiftcondition(String giftcondition) {
        this.giftcondition = giftcondition;
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

    public String getIsactivepackage() {
        return isactivepackage;
    }

    public void setIsactivepackage(String isactivepackage) {
        this.isactivepackage = isactivepackage;
    }

}
