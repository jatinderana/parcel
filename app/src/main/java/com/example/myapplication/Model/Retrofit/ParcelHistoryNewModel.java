package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParcelHistoryNewModel {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private List<Data> data;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }


    public class Deliveryaddress {
        private String company;

        private String office;

        private String floor;

        private String location;

        private String latitude;

        private String longitude;

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCompany() {
            return this.company;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getOffice() {
            return this.office;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getFloor() {
            return this.floor;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLatitude() {
            return this.latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLongitude() {
            return this.longitude;
        }
    }

    public class Items {


        @SerializedName("title")
        @Expose
        private String title;


        @SerializedName("image")
        @Expose
        private String image;


        @SerializedName("qty")
        @Expose
        private String qty;


        @SerializedName("price")
        @Expose
        private String price;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage() {
            return this.image;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getQty() {
            return this.qty;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice() {
            return this.price;
        }
    }

    public class Data {

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
        private List<Deliveryaddress> deliveryaddress;


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
        private List<Items> items;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUserid() {
            return this.userid;
        }

        public void setFinaltotal(String finaltotal) {
            this.finaltotal = finaltotal;
        }

        public String getFinaltotal() {
            return this.finaltotal;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getOrderid() {
            return this.orderid;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public String getOrderdate() {
            return this.orderdate;
        }

        public void setDeliveryaddress(List<Deliveryaddress> deliveryaddress) {
            this.deliveryaddress = deliveryaddress;
        }

        public List<Deliveryaddress> getDeliveryaddress() {
            return this.deliveryaddress;
        }

        public void setProgresststamp(String progresststamp) {
            this.progresststamp = progresststamp;
        }

        public String getProgresststamp() {
            return this.progresststamp;
        }

        public void setReadytstamp(String readytstamp) {
            this.readytstamp = readytstamp;
        }

        public String getReadytstamp() {
            return this.readytstamp;
        }

        public void setPickedtstamp(String pickedtstamp) {
            this.pickedtstamp = pickedtstamp;
        }

        public String getPickedtstamp() {
            return this.pickedtstamp;
        }

        public void setDeliveredtstamp(String deliveredtstamp) {
            this.deliveredtstamp = deliveredtstamp;
        }

        public String getDeliveredtstamp() {
            return this.deliveredtstamp;
        }

        public void setCstatus(String cstatus) {
            this.cstatus = cstatus;
        }

        public String getCstatus() {
            return this.cstatus;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public List<Items> getItems() {
            return this.items;
        }
    }


}
