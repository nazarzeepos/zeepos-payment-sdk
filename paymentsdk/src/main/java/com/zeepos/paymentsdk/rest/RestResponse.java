package com.zeepos.paymentsdk.rest;

public class RestResponse<D> {
    private int code;
    private String data;
    private String sign;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    private String err;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
