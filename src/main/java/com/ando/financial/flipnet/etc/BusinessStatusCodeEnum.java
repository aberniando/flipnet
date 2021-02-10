package com.ando.financial.flipnet.etc;

public enum BusinessStatusCodeEnum {

    SUCCESS("SU"),
    FAILED("FA"),
    PENDING("PE"),
    UNKNOWN("UN"),
    RECEIVE_SUCCESS("RS"),
    TIMEOUT("TO");

    private String code;

    BusinessStatusCodeEnum(String code) {
        this.code = code;
    }

    public static BusinessStatusCodeEnum getByCode(String code) {
        for (BusinessStatusCodeEnum businessStatusCodeEnum : values()) {
            if (businessStatusCodeEnum.getCode().equals(code)) {
                return businessStatusCodeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
