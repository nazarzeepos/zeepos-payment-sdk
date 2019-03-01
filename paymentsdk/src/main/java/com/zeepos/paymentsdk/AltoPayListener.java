package com.zeepos.paymentsdk;

public interface AltoPayListener {
    void onSuccess(String trade_no);

    void onfailed(String message);
}
