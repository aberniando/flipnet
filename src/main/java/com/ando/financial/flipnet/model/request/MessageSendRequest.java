package com.ando.financial.flipnet.model.request;

import com.ando.financial.flipnet.model.FlipnetSerial;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MessageSendRequest {

    private String instId;
    private String abilityCode;
    private ObjectNode message;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getAbilityCode() {
        return abilityCode;
    }

    public void setAbilityCode(String abilityCode) {
        this.abilityCode = abilityCode;
    }

    public ObjectNode getMessage() {
        return message;
    }

    public void setMessage(ObjectNode message) {
        this.message = message;
    }
}
