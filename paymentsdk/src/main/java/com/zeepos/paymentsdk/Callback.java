package com.zeepos.paymentsdk;

public interface Callback<T>  {
    void onSuccess(int code,T body);
    void onFailed(int code,String message);
}
