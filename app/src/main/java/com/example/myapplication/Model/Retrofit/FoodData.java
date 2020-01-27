package com.example.myapplication.Model.Retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodData implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("uptdata")
    @Expose
    private List<Uptdatum> uptdata = null;
    @SerializedName("bparcelarr")
    @Expose
    private List<Object> bparcelarr = null;
    @SerializedName("lparcelarr")
    @Expose
    private List<Object> lparcelarr = null;
    @SerializedName("giftdetails")
    @Expose
    private List<GiftDetail> giftdetails = null;
    @SerializedName("packageinfo")
    @Expose
    private List<Packageinfo> packageinfo = null;
    @SerializedName("replacelimit")
    @Expose
    private String replacelimit;
    @SerializedName("giftlimit")
    @Expose
    private String giftlimit;
    @SerializedName("canaddparcelcafe")
    @Expose
    private String canaddparcelcafe;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("priortime")
    @Expose
    private String priortime;
    @SerializedName("todaydate")
    @Expose
    private String todaydate;
    @SerializedName("canuptbreakfast")
    @Expose
    private String canuptbreakfast;
    @SerializedName("canuptlunch")
    @Expose
    private String canuptlunch;
    @SerializedName("softdrink")
    @Expose
    private String softdrink;


    @SerializedName("lang")
    @Expose
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSoftdrink() {
        return softdrink;
    }

    public void setSoftdrink(String softdrink) {
        this.softdrink = softdrink;
    }

    public FoodData(Parcel in) {
        status = in.readString();
        duration = in.readString();
        replacelimit = in.readString();
        giftlimit = in.readString();
        canaddparcelcafe = in.readString();
        priortime = in.readString();
        todaydate = in.readString();
        canuptbreakfast = in.readString();
        canuptlunch = in.readString();
    }

    public static final Creator<FoodData> CREATOR = new Creator<FoodData>() {
        @Override
        public FoodData createFromParcel(Parcel in) {
            return new FoodData(in);
        }

        @Override
        public FoodData[] newArray(int size) {
            return new FoodData[size];
        }
    };

    public FoodData() {

    }

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Uptdatum> getUptdata() {
        return uptdata;
    }

    public void setUptdata(List<Uptdatum> uptdata) {
        this.uptdata = uptdata;
    }

    public List<Object> getBparcelarr() {
        return bparcelarr;
    }

    public void setBparcelarr(List<Object> bparcelarr) {
        this.bparcelarr = bparcelarr;
    }

    public List<Object> getLparcelarr() {
        return lparcelarr;
    }

    public void setLparcelarr(List<Object> lparcelarr) {
        this.lparcelarr = lparcelarr;
    }

public List<GiftDetail> getGiftdetails() {
        return giftdetails;
    }

    public void setGiftdetails(List<GiftDetail> giftdetails) {
        this.giftdetails = giftdetails;
    }

    public List<Packageinfo> getPackageinfo() {
        return packageinfo;
    }

    public void setPackageinfo(List<Packageinfo> packageinfo) {
        this.packageinfo = packageinfo;
    }

    public String getReplacelimit() {
        return replacelimit;
    }

    public void setReplacelimit(String replacelimit) {
        this.replacelimit = replacelimit;
    }

    public String getGiftlimit() {
        return giftlimit;
    }

    public void setGiftlimit(String giftlimit) {
        this.giftlimit = giftlimit;
    }

    public String getCanaddparcelcafe() {
        return canaddparcelcafe;
    }

    public void setCanaddparcelcafe(String canaddparcelcafe) {
        this.canaddparcelcafe = canaddparcelcafe;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public String getPriortime() {
        return priortime;
    }

    public void setPriortime(String priortime) {
        this.priortime = priortime;
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate;
    }

    public String getCanuptbreakfast() {
        return canuptbreakfast;
    }

    public void setCanuptbreakfast(String canuptbreakfast) {
        this.canuptbreakfast = canuptbreakfast;
    }

    public String getCanuptlunch() {
        return canuptlunch;
    }

    public void setCanuptlunch(String canuptlunch) {
        this.canuptlunch = canuptlunch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(duration);
        dest.writeString(replacelimit);
        dest.writeString(giftlimit);
        dest.writeString(canaddparcelcafe);
        dest.writeString(priortime);
        dest.writeString(todaydate);
        dest.writeString(canuptbreakfast);
        dest.writeString(canuptlunch);
    }


    public class Packageinfo {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title_en")
        @Expose
        private String titleEn;
        @SerializedName("title_ar")
        @Expose
        private String titleAr;
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
        @SerializedName("deliverycondition")
        @Expose
        private String deliverycondition;
        @SerializedName("rdate")
        @Expose
        private String rdate;
        @SerializedName("rtime")
        @Expose
        private String rtime;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("title")
        @Expose
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitleEn() {
            return titleEn;
        }

        public void setTitleEn(String titleEn) {
            this.titleEn = titleEn;
        }

        public String getTitleAr() {
            return titleAr;
        }

        public void setTitleAr(String titleAr) {
            this.titleAr = titleAr;
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

        public String getDeliverycondition() {
            return deliverycondition;
        }

        public void setDeliverycondition(String deliverycondition) {
            this.deliverycondition = deliverycondition;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    public static class Uptdatum {

        @SerializedName("userid")
        @Expose
        private String userid;
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
        @SerializedName("bdoubleqty_temp")
        @Expose
        private String bdoubleqtyTemp;
        @SerializedName("ldoubleqty_temp")
        @Expose
        private String ldoubleqtyTemp;
        @SerializedName("bpaystatus")
        @Expose
        private String bpaystatus;
        @SerializedName("bparcelid")
        @Expose
        private String bparcelid;
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

        public String getBdoubleqtyTemp() {
            return bdoubleqtyTemp;
        }

        public void setBdoubleqtyTemp(String bdoubleqtyTemp) {
            this.bdoubleqtyTemp = bdoubleqtyTemp;
        }

        public String getLdoubleqtyTemp() {
            return ldoubleqtyTemp;
        }

        public void setLdoubleqtyTemp(String ldoubleqtyTemp) {
            this.ldoubleqtyTemp = ldoubleqtyTemp;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
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

    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("day")
        @Expose
        private String day;
        @SerializedName("bdescription_en")
        @Expose
        private String bdescriptionEn;
        @SerializedName("bdescription_ar")
        @Expose
        private String bdescriptionAr;
        @SerializedName("bimage")
        @Expose
        private String bimage;
        @SerializedName("bprice")
        @Expose
        private String bprice;
        @SerializedName("ldescription_en")
        @Expose
        private String ldescriptionEn;
        @SerializedName("ldescription_ar")
        @Expose
        private String ldescriptionAr;
        @SerializedName("limage")
        @Expose
        private String limage;
        @SerializedName("lprice")
        @Expose
        private String lprice;
        @SerializedName("bdescription")
        @Expose
        private String bdescription;
        @SerializedName("ldescription")
        @Expose
        private String ldescription;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getBdescriptionEn() {
            return bdescriptionEn;
        }

        public void setBdescriptionEn(String bdescriptionEn) {
            this.bdescriptionEn = bdescriptionEn;
        }

        public String getBdescriptionAr() {
            return bdescriptionAr;
        }

        public void setBdescriptionAr(String bdescriptionAr) {
            this.bdescriptionAr = bdescriptionAr;
        }

        public String getBimage() {
            return bimage;
        }

        public void setBimage(String bimage) {
            this.bimage = bimage;
        }

        public String getBprice() {
            return bprice;
        }

        public void setBprice(String bprice) {
            this.bprice = bprice;
        }

        public String getLdescriptionEn() {
            return ldescriptionEn;
        }

        public void setLdescriptionEn(String ldescriptionEn) {
            this.ldescriptionEn = ldescriptionEn;
        }

        public String getLdescriptionAr() {
            return ldescriptionAr;
        }

        public void setLdescriptionAr(String ldescriptionAr) {
            this.ldescriptionAr = ldescriptionAr;
        }

        public String getLimage() {
            return limage;
        }

        public void setLimage(String limage) {
            this.limage = limage;
        }

        public String getLprice() {
            return lprice;
        }

        public void setLprice(String lprice) {
            this.lprice = lprice;
        }

        public String getBdescription() {
            return bdescription;
        }

        public void setBdescription(String bdescription) {
            this.bdescription = bdescription;
        }

        public String getLdescription() {
            return ldescription;
        }

        public void setLdescription(String ldescription) {
            this.ldescription = ldescription;
        }

    }
}
