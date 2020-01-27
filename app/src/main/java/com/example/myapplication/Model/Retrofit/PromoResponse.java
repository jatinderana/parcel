
package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("payableamount")
    @Expose
    private String payableamount;
    @SerializedName("offertype")
    @Expose
    private String offertype;
    @SerializedName("offervalue")
    @Expose
    private String offervalue;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayableamount() {
        return payableamount;
    }

    public void setPayableamount(String payableamount) {
        this.payableamount = payableamount;
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
}
