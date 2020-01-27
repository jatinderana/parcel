package com.example.myapplication.AddParcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParcelResponseModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
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
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("rdate")
        @Expose
        private String rdate;
        @SerializedName("rtime")
        @Expose
        private String rtime;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
}
