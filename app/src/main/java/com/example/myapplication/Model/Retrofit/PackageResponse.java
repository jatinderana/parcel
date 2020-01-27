package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<DatumPackage> data = null;
    @SerializedName("priortime")
    @Expose
    private String priortime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DatumPackage> getData() {
        return data;
    }

    public void setData(List<DatumPackage> data) {
        this.data = data;
    }

    public String getPriortime() {
        return priortime;
    }

    public void setPriortime(String priortime) {
        this.priortime = priortime;
    }

    public class DatumPackage {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userid")
        @Expose
        private Object userid;
        @SerializedName("check_id")
        @Expose
        private String checkId;
        @SerializedName("pdate")
        @Expose
        private Object pdate;
        @SerializedName("bdayreplace")
        @Expose
        private String bdayreplace;
        @SerializedName("bdoubleqty")
        @Expose
        private String bdoubleqty;
        @SerializedName("bpaystatus")
        @Expose
        private String bpaystatus;
        @SerializedName("bparcelid")
        @Expose
        private String bparcelid;
        @SerializedName("rdate")
        @Expose
        private String rdate;
        @SerializedName("rtime")
        @Expose
        private String rtime;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("ldayreplace")
        @Expose
        private String ldayreplace;
        @SerializedName("ldoubleqty")
        @Expose
        private String ldoubleqty;
        @SerializedName("lpaystatus")
        @Expose
        private String lpaystatus;
        @SerializedName("lparcelid")
        @Expose
        private String lparcelid;
        @SerializedName("gift")
        @Expose
        private String gift;
        @SerializedName("uptype")
        @Expose
        private String uptype;
        @SerializedName("doubleprice")
        @Expose
        private String doubleprice;
        @SerializedName("foodtype")
        @Expose
        private String foodtype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getUserid() {
            return userid;
        }

        public void setUserid(Object userid) {
            this.userid = userid;
        }

        public String getCheckId() {
            return checkId;
        }

        public void setCheckId(String checkId) {
            this.checkId = checkId;
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

        public String getBpaystatus() {
            return bpaystatus;
        }

        public void setBpaystatus(String bpaystatus) {
            this.bpaystatus = bpaystatus;
        }

        public String getBparcelid() {
            return bparcelid;
        }

        public void setBparcelid(String bparcelid) {
            this.bparcelid = bparcelid;
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

        public String getLpaystatus() {
            return lpaystatus;
        }

        public void setLpaystatus(String lpaystatus) {
            this.lpaystatus = lpaystatus;
        }

        public String getLparcelid() {
            return lparcelid;
        }

        public void setLparcelid(String lparcelid) {
            this.lparcelid = lparcelid;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getUptype() {
            return uptype;
        }

        public void setUptype(String uptype) {
            this.uptype = uptype;
        }

        public String getDoubleprice() {
            return doubleprice;
        }

        public void setDoubleprice(String doubleprice) {
            this.doubleprice = doubleprice;
        }

        public String getFoodtype() {
            return foodtype;
        }

        public void setFoodtype(String foodtype) {
            this.foodtype = foodtype;
        }

    }
}