package com.ando.financial.flipnet.model.response;

import com.ando.financial.flipnet.etc.BusinessStatusCodeEnum;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

public class MessageSendResponse {

    private ObjectNode message;
    private ObjectNode origRequestMessage;
    private String transmissionResult;
    private String transactionResult;

    public ObjectNode getMessage() {
        return message;
    }

    public void setMessage(ObjectNode message) {
        this.message = message;
    }

    public ObjectNode getRequestMessage() {
        return origRequestMessage;
    }

    public void setRequestMessage(ObjectNode origRequestMessage) {
        this.origRequestMessage = origRequestMessage;
    }

    public String getTransmissionResult() {
        return transmissionResult;
    }

    public void setTransmissionResult(String transmissionResult) {
        this.transmissionResult = transmissionResult;
    }

    public String getTransactionResult() {
        return transactionResult;
    }

    public void setTransactionResult(String transactionResult) {
        this.transactionResult = transactionResult;
    }
}
