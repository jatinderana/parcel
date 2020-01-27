
package com.example.myapplication.Model.Retrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubcribeModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("addresslimit")
    @Expose
    private String addressLimit;
    @SerializedName("data")
    @Expose
    private List<subcribe> data = null;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("check_id")
    @Expose
    private String check_id;
    @SerializedName("subscription")
    @Expose
    private List<Subscription> subscription = null;
    @SerializedName("canupgrade")
    @Expose
    private List<Canupgrade> canupgrade = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<subcribe> getData() {
        return data;
    }

    public void setData(List<subcribe> data) {
        this.data = data;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Subscription> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<Subscription> subscription) {
        this.subscription = subscription;
    }

    public List<Canupgrade> getCanupgrade() {
        return canupgrade;
    }

    public void setCanupgrade(List<Canupgrade> canupgrade) {
        this.canupgrade = canupgrade;
    }

    public String getAddressLimit() {
        return addressLimit;
    }

    public void setAddressLimit(String addressLimit) {
        this.addressLimit = addressLimit;
    }

    public String getCheck_id() {
        return check_id;
    }

    public void setCheck_id(String check_id) {
        this.check_id = check_id;
    }
}
