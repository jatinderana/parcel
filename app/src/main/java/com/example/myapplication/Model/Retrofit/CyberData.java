package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CyberData {

    @SerializedName("expirationYear")
    @Expose
    private String expirationYear;


    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("check_id")
    @Expose
    private String check_id;

    @SerializedName("street1")
    @Expose
    private String street1;

    @SerializedName("expirationMonth")
    @Expose
    private String expirationMonth;

    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;

    @SerializedName("cvNumber")
    @Expose
    private String cvNumber;

    @SerializedName("email")
    @Expose
    private String email;

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCheck_id() {
        return check_id;
    }

    public void setCheck_id(String check_id) {
        this.check_id = check_id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCvNumber() {
        return cvNumber;
    }

    public void setCvNumber(String cvNumber) {
        this.cvNumber = cvNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClassPojo [expirationYear = " + expirationYear + ", firstName = " + firstName + ", lastName = " + lastName + ", amount = " + amount + ", check_id = " + check_id + ", street1 = " + street1 + ", expirationMonth = " + expirationMonth + ", accountNumber = " + accountNumber + ", cvNumber = " + cvNumber + ", email = " + email + "]";
    }
}
