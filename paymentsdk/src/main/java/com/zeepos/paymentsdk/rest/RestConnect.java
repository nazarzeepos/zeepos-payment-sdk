package com.zeepos.paymentsdk.rest;


import com.zeepos.paymentsdk.model.Order;
import com.zeepos.paymentsdk.model.Pay;
import com.zeepos.paymentsdk.model.PaymentResult;
import com.zeepos.paymentsdk.model.Query;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RestConnect {
    @Headers({"Content-Type: application/json"})
    @POST(Endpoint.ORDER)
    Call<RestResponse<Order.Response>> order(@Header("token") String token,
                                             @Body Order.Request data);

    @Headers({"Accept: application/json"})
    @POST(Endpoint.PAY)
    Call<RestResponse<Pay.Response>> pay(@Header("token") String token,
                                         @Body String data);

    @Headers({"Accept: application/json"})
    @POST(Endpoint.QUERY)
    Call<RestResponse<Query.Response>> query(@Header("token") String token,
                                             @Body Query.Request data);

    @Headers({"Accept: application/json"})
    @POST(Endpoint.PAY)
    Call<RestResponse<PaymentResult.Response>> notify(@Header("token") String token,
                                                      @Body String data);
}
