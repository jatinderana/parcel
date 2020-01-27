package com.example.myapplication.Model.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CyberResponse {


    @SerializedName("velocityInfoCode")
    @Expose
    private String velocityInfoCode;

    @SerializedName("cavvResponseCodeRaw")
    @Expose
    private String cavvResponseCodeRaw;

    @SerializedName("authorizationCode")
    @Expose
    private String authorizationCode;

    @SerializedName("hostSeverity")
    @Expose
    private String hostSeverity;

    @SerializedName("cvCode")
    @Expose
    private String cvCode;

    @SerializedName("afsFactorCode")
    @Expose
    private String afsFactorCode;

    @SerializedName("casePriority")
    @Expose
    private String casePriority;

    @SerializedName("cvCodeRaw")
    @Expose
    private String cvCodeRaw;

    @SerializedName("merchantAdviceCodeRaw")
    @Expose
    private String merchantAdviceCodeRaw;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("reconciliationID")
    @Expose
    private String reconciliationID;

    @SerializedName("reasonCode")
    @Expose
    private String reasonCode;

    @SerializedName("avsCodeRaw")
    @Expose
    private String avsCodeRaw;

    @SerializedName("activeProfileReply")
    @Expose
    private String activeProfileReply;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("decision")
    @Expose
    private String decision;

    @SerializedName("merchantReferenceCode")
    @Expose
    private String merchantReferenceCode;

    @SerializedName("avsCode")
    @Expose
    private String avsCode;

    @SerializedName("consumerLocalTime")
    @Expose
    private String consumerLocalTime;

    @SerializedName("scoreModelUsed")
    @Expose
    private String scoreModelUsed;

    @SerializedName("paymentNetworkTransactionID")
    @Expose
    private String paymentNetworkTransactionID;

    @SerializedName("afsResult")
    @Expose
    private String afsResult;

    @SerializedName("suspiciousInfoCode")
    @Expose
    private String suspiciousInfoCode;

    @SerializedName("merchantAdviceCode")
    @Expose
    private String merchantAdviceCode;

    @SerializedName("cavvResponseCode")
    @Expose
    private String cavvResponseCode;

    @SerializedName("requestID")
    @Expose
    private String requestID;

    @SerializedName("authorizedDateTime")
    @Expose
    private String authorizedDateTime;

    @SerializedName("requestToken")
    @Expose
    private String requestToken;

    @SerializedName("addressInfoCode")
    @Expose
    private String addressInfoCode;

    @SerializedName("processorResponse")
    @Expose
    private String processorResponse;


    public String getVelocityInfoCode() {
        return velocityInfoCode;
    }

    public void setVelocityInfoCode(String velocityInfoCode) {
        this.velocityInfoCode = velocityInfoCode;
    }

    public String getCavvResponseCodeRaw() {
        return cavvResponseCodeRaw;
    }

    public void setCavvResponseCodeRaw(String cavvResponseCodeRaw) {
        this.cavvResponseCodeRaw = cavvResponseCodeRaw;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getHostSeverity() {
        return hostSeverity;
    }

    public void setHostSeverity(String hostSeverity) {
        this.hostSeverity = hostSeverity;
    }

    public String getCvCode() {
        return cvCode;
    }

    public void setCvCode(String cvCode) {
        this.cvCode = cvCode;
    }

    public String getAfsFactorCode() {
        return afsFactorCode;
    }

    public void setAfsFactorCode(String afsFactorCode) {
        this.afsFactorCode = afsFactorCode;
    }

    public String getCasePriority() {
        return casePriority;
    }

    public void setCasePriority(String casePriority) {
        this.casePriority = casePriority;
    }

    public String getCvCodeRaw() {
        return cvCodeRaw;
    }

    public void setCvCodeRaw(String cvCodeRaw) {
        this.cvCodeRaw = cvCodeRaw;
    }

    public String getMerchantAdviceCodeRaw() {
        return merchantAdviceCodeRaw;
    }

    public void setMerchantAdviceCodeRaw(String merchantAdviceCodeRaw) {
        this.merchantAdviceCodeRaw = merchantAdviceCodeRaw;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReconciliationID() {
        return reconciliationID;
    }

    public void setReconciliationID(String reconciliationID) {
        this.reconciliationID = reconciliationID;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getAvsCodeRaw() {
        return avsCodeRaw;
    }

    public void setAvsCodeRaw(String avsCodeRaw) {
        this.avsCodeRaw = avsCodeRaw;
    }

    public String getActiveProfileReply() {
        return activeProfileReply;
    }

    public void setActiveProfileReply(String activeProfileReply) {
        this.activeProfileReply = activeProfileReply;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getMerchantReferenceCode() {
        return merchantReferenceCode;
    }

    public void setMerchantReferenceCode(String merchantReferenceCode) {
        this.merchantReferenceCode = merchantReferenceCode;
    }

    public String getAvsCode() {
        return avsCode;
    }

    public void setAvsCode(String avsCode) {
        this.avsCode = avsCode;
    }

    public String getConsumerLocalTime() {
        return consumerLocalTime;
    }

    public void setConsumerLocalTime(String consumerLocalTime) {
        this.consumerLocalTime = consumerLocalTime;
    }

    public String getScoreModelUsed() {
        return scoreModelUsed;
    }

    public void setScoreModelUsed(String scoreModelUsed) {
        this.scoreModelUsed = scoreModelUsed;
    }

    public String getPaymentNetworkTransactionID() {
        return paymentNetworkTransactionID;
    }

    public void setPaymentNetworkTransactionID(String paymentNetworkTransactionID) {
        this.paymentNetworkTransactionID = paymentNetworkTransactionID;
    }

    public String getAfsResult() {
        return afsResult;
    }

    public void setAfsResult(String afsResult) {
        this.afsResult = afsResult;
    }

    public String getSuspiciousInfoCode() {
        return suspiciousInfoCode;
    }

    public void setSuspiciousInfoCode(String suspiciousInfoCode) {
        this.suspiciousInfoCode = suspiciousInfoCode;
    }

    public String getMerchantAdviceCode() {
        return merchantAdviceCode;
    }

    public void setMerchantAdviceCode(String merchantAdviceCode) {
        this.merchantAdviceCode = merchantAdviceCode;
    }

    public String getCavvResponseCode() {
        return cavvResponseCode;
    }

    public void setCavvResponseCode(String cavvResponseCode) {
        this.cavvResponseCode = cavvResponseCode;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getAuthorizedDateTime() {
        return authorizedDateTime;
    }

    public void setAuthorizedDateTime(String authorizedDateTime) {
        this.authorizedDateTime = authorizedDateTime;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getAddressInfoCode() {
        return addressInfoCode;
    }

    public void setAddressInfoCode(String addressInfoCode) {
        this.addressInfoCode = addressInfoCode;
    }

    public String getProcessorResponse() {
        return processorResponse;
    }

    public void setProcessorResponse(String processorResponse) {
        this.processorResponse = processorResponse;
    }

    @Override
    public String toString() {
        return "ClassPojo [velocityInfoCode = " + velocityInfoCode + ", cavvResponseCodeRaw = " + cavvResponseCodeRaw + ", authorizationCode = " + authorizationCode + ", hostSeverity = " + hostSeverity + ", cvCode = " + cvCode + ", afsFactorCode = " + afsFactorCode + ", casePriority = " + casePriority + ", cvCodeRaw = " + cvCodeRaw + ", merchantAdviceCodeRaw = " + merchantAdviceCodeRaw + ", currency = " + currency + ", reconciliationID = " + reconciliationID + ", reasonCode = " + reasonCode + ", avsCodeRaw = " + avsCodeRaw + ", activeProfileReply = " + activeProfileReply + ", amount = " + amount + ", decision = " + decision + ", merchantReferenceCode = " + merchantReferenceCode + ", avsCode = " + avsCode + ", consumerLocalTime = " + consumerLocalTime + ", scoreModelUsed = " + scoreModelUsed + ", paymentNetworkTransactionID = " + paymentNetworkTransactionID + ", afsResult = " + afsResult + ", suspiciousInfoCode = " + suspiciousInfoCode + ", merchantAdviceCode = " + merchantAdviceCode + ", cavvResponseCode = " + cavvResponseCode + ", requestID = " + requestID + ", authorizedDateTime = " + authorizedDateTime + ", requestToken = " + requestToken + ", addressInfoCode = " + addressInfoCode + ", processorResponse = " + processorResponse + "]";
    }

}
