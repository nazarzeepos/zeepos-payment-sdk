package com.zeepos.paymentsdk;

import android.graphics.Bitmap;

public interface AltoPayListener {
    /*
    * the bitmap qr code needs to be shown to the user for payment
    * u can show it using UI screen or print it
    * */
    void onQrCode(Bitmap bmp);

    void onSuccess(String trade_no);

    void onfailed(String message);
}
