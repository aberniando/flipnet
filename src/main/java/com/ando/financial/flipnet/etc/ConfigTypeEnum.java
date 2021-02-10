package com.ando.financial.flipnet.etc;

public enum ConfigTypeEnum {

    COMMUNICATION("communication");

    private String code;

    ConfigTypeEnum(String code) {
        this.code = code;
    }

    public static ConfigTypeEnum getByCode(String code) {
        for (ConfigTypeEnum configTypeEnum : values()) {
            if (configTypeEnum.getCode().equals(code)) {
                return configTypeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }


}
