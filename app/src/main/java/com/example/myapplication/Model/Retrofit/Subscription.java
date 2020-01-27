
package com.example.myapplication.Model.Retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscription implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("packageid")
    @Expose
    private String packageid;
    @SerializedName("packagename")
    @Expose
    private String packagename;
    @SerializedName("packageduration")
    @Expose
    private String packageduration;
    @SerializedName("stype")
    @Expose
    private String stype;
    @SerializedName("people")
    @Expose
    private String people;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("sdate")
    @Expose
    private String sdate;
    @SerializedName("edate")
    @Expose
    private String edate;
    @SerializedName("check_id")
    @Expose
    private String check_id;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("upgrade")
    @Expose
    private String upgrade;
    @SerializedName("freeze")
    @Expose
    private String freeze;
    @SerializedName("freezedates")
    @Expose
    private String freezedates;

    protected Subscription(Parcel in) {
        id = in.readString();
        userid = in.readString();
        packageid = in.readString();
        packagename = in.readString();
        packageduration = in.readString();
        stype = in.readString();
        people = in.readString();
        duration = in.readString();
        sdate = in.readString();
        edate = in.readString();
        total = in.readString();
        upgrade = in.readString();
        freeze = in.readString();
        freezedates = in.readString();
        check_id = in.readString();
    }

    public static final Creator<Subscription> CREATOR = new Creator<Subscription>() {
        @Override
        public Subscription createFromParcel(Parcel in) {
            return new Subscription(in);
        }

        @Override
        public Subscription[] newArray(int size) {
            return new Subscription[size];
        }
    };

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

    public String getPackageid() {
        return packageid;
    }

    public void setPackageid(String packageid) {
        this.packageid = packageid;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getPackageduration() {
        return packageduration;
    }

    public void setPackageduration(String packageduration) {
        this.packageduration = packageduration;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getFreezedates() {
        return freezedates;
    }

    public void setFreezedates(String freezedates) {
        this.freezedates = freezedates;
    }


    public String getCheck_id() {
        return check_id;
    }

    public void setCheck_id(String check_id) {
        this.check_id = check_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userid);
        dest.writeString(packageid);
        dest.writeString(packagename);
        dest.writeString(packageduration);
        dest.writeString(stype);
        dest.writeString(people);
        dest.writeString(duration);
        dest.writeString(sdate);
        dest.writeString(edate);
        dest.writeString(total);
        dest.writeString(upgrade);
        dest.writeString(freeze);
        dest.writeString(freezedates);
        dest.writeString(check_id);
    }
}
