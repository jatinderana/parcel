package com.example.myapplication.ParcelHisotry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParcelHistoryModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<ParcelHistory> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ParcelHistory> getData() {
        return data;
    }

    public void setData(List<ParcelHistory> data) {
        this.data = data;
    }

    public class ParcelHistory {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("finaltotal")
        @Expose
        private String finaltotal;
        @SerializedName("orderid")
        @Expose
        private String orderid;
        @SerializedName("orderdate")
        @Expose
        private String orderdate;
        @SerializedName("deliveryaddress")
        @Expose
        private List<Deliveryaddress> deliveryaddress = null;
        @SerializedName("progresststamp")
        @Expose
        private String progresststamp;
        @SerializedName("readytstamp")
        @Expose
        private String readytstamp;
        @SerializedName("pickedtstamp")
        @Expose
        private String pickedtstamp;
        @SerializedName("deliveredtstamp")
        @Expose
        private String deliveredtstamp;
        @SerializedName("cstatus")
        @Expose
        private String cstatus;
        @SerializedName("items")
        @Expose
        private List<Item> items = null;

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

        public String getFinaltotal() {
            return finaltotal;
        }

        public void setFinaltotal(String finaltotal) {
            this.finaltotal = finaltotal;
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

        public List<Deliveryaddress> getDeliveryaddress() {
            return deliveryaddress;
        }

        public void setDeliveryaddress(List<Deliveryaddress> deliveryaddress) {
            this.deliveryaddress = deliveryaddress;
        }

        public String getProgresststamp() {
            return progresststamp;
        }

        public void setProgresststamp(String progresststamp) {
            this.progresststamp = progresststamp;
        }

        public String getReadytstamp() {
            return readytstamp;
        }

        public void setReadytstamp(String readytstamp) {
            this.readytstamp = readytstamp;
        }

        public String getPickedtstamp() {
            return pickedtstamp;
        }

        public void setPickedtstamp(String pickedtstamp) {
            this.pickedtstamp = pickedtstamp;
        }

        public String getDeliveredtstamp() {
            return deliveredtstamp;
        }

        public void setDeliveredtstamp(String deliveredtstamp) {
            this.deliveredtstamp = deliveredtstamp;
        }

        public String getCstatus() {
            return cstatus;
        }

        public void setCstatus(String cstatus) {
            this.cstatus = cstatus;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

    }


    public class Deliveryaddress {

        @SerializedName("company")
        @Expose
        private String company = "";
        @SerializedName("office")
        @Expose
        private String office = "";
        @SerializedName("floor")
        @Expose
        private String floor = "";
        @SerializedName("location")
        @Expose
        private String location = "";
        @SerializedName("latitude")
        @Expose
        private String latitude= "";
        @SerializedName("longitude")
        @Expose
        private String longitude;

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

    }


    public class Item {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("options")
        @Expose
        private List<Option> options = null;

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

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }

    }

    public class Option {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;

        @SerializedName("price")
        @Expose
        private String price;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
