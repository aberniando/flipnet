package com.ando.financial.flipnet.model;

import com.ando.financial.flipnet.etc.BusinessStatusCodeEnum;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;

public class FlipnetSerial {

    private long id;
    private Date gmtCreated;
    private Date gmtModified;
    private String instId;
    private long referenceId;
    private String operator;
    private String abilityCode;
    private int exchangeAmount;
    private String requestMessage;
    private String responseMessage;
    private String receipt;
    private String merchantId;
    private String timeServed;
    private String externalSerialNo;
    private String status;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(long referenceId) {
        this.referenceId = referenceId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAbilityCode() {
        return abilityCode;
    }

    public void setAbilityCode(String abilityCode) {
        this.abilityCode = abilityCode;
    }

    public int getExchangeAmount() {
        return exchangeAmount;
    }

    public void setExchangeAmount(int exchangeAmount) {
        this.exchangeAmount = exchangeAmount;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTimeServed() {
        return timeServed;
    }

    public void setTimeServed(String timeServed) {
        this.timeServed = timeServed;
    }

    public String getExternalSerialNo() {
        return externalSerialNo;
    }

    public void setExternalSerialNo(String externalSerialNo) {
        this.externalSerialNo = externalSerialNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
