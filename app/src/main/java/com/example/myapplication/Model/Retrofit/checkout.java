
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class checkout {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ids")
    @Expose
    private String ids;
    @SerializedName("partyids")
    @Expose
    private String partyids;
    @SerializedName("prices")
    @Expose
    private String prices;
    @SerializedName("qtys")
    @Expose
    private String qtys;
    @SerializedName("totals")
    @Expose
    private String totals;
    @SerializedName("finaltotal")
    @Expose
    private String finaltotal;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("office")
    @Expose
    private String office;
    @SerializedName("floor")
    @Expose
    private String floor;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
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
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("ctype")
    @Expose
    private String ctype;
    @SerializedName("offertype")
    @Expose
    private String offertype;
    @SerializedName("offervalue")
    @Expose
    private String offervalue;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("lastid")
    @Expose
    private Integer lastid;
    @SerializedName("softdrink")
    @Expose
    private String softdrink;

    @SerializedName("card_number")
    @Expose
    private String card_number;

    @SerializedName("card_month")
    @Expose
    private String card_month;

    @SerializedName("card_year")
    @Expose
    private String card_year;

    @SerializedName("cvc")
    @Expose
    private String cvc;

    @SerializedName("is_default")
    @Expose
    private String isDefault;

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_month() {
        return card_month;
    }

    public void setCard_month(String card_month) {
        this.card_month = card_month;
    }

    public String getCard_year() {
        return card_year;
    }

    public void setCard_year(String card_year) {
        this.card_year = card_year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getLastid() {
        return lastid;
    }

    public void setLastid(Integer lastid) {
        this.lastid = lastid;
    }

    public String getSoftdrink() {
        return softdrink;
    }

    public void setSoftdrink(String softdrink) {
        this.softdrink = softdrink;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getPartyids() {
        return partyids;
    }

    public void setPartyids(String partyids) {
        this.partyids = partyids;
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

    public String getFinaltotal() {
        return finaltotal;
    }

    public void setFinaltotal(String finaltotal) {
        this.finaltotal = finaltotal;
    }
}