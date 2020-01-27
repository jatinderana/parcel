package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GatewayModel {
@SerializedName("userid")
@Expose
private String userid;

    @SerializedName("check_id")
    @Expose
    private String check_id;
    @SerializedName("lang")
    @Expose
    private String lang;

    public String getCheck_id() {
        return check_id;
    }

    public void setCheck_id(String check_id) {
        this.check_id = check_id;
    }

    @SerializedName("foodtype")
    @Expose
    private String foodtype;
    @SerializedName("is_logged")
    @Expose
    private String is_logged;

    public String getIs_logged() {
        return is_logged;
    }

    public void setIs_logged(String is_logged) {
        this.is_logged = is_logged;
    }

    @SerializedName("paytype")
    @Expose
    private String paytype;

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

        public String getUserid() {
            return userid;
        }
        public void setUserid(String userid) {
            this.userid = userid;
        }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}