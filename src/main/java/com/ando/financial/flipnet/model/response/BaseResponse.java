package com.ando.financial.flipnet.model.response;

public class BaseResponse {

    private boolean success = true;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
