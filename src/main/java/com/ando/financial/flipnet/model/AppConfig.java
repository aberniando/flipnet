package com.ando.financial.flipnet.model;

import com.ando.financial.flipnet.etc.ConfigTypeEnum;

public class AppConfig {

    private String configType;
    private String value;
    private String instId;
    private String abilityCode;

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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
}
