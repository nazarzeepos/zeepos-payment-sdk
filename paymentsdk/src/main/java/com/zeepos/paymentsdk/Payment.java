package com.zeepos.paymentsdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeepos.paymentsdk.model.Order;
import com.zeepos.paymentsdk.model.Pay;
import com.zeepos.paymentsdk.model.PaymentResult;
import com.zeepos.paymentsdk.model.Query;
import com.zeepos.paymentsdk.rest.RestCallback;
import com.zeepos.paymentsdk.rest.RestConnect;
import com.zeepos.paymentsdk.rest.RestResponse;
import com.zeepos.paymentsdk.rest.RestService;
import com.zeepos.paymentsdk.util.Sign;

import retrofit2.Call;

public class Payment {
    public static class Do {
        public static void order(String token, Order data, final Callback<RestResponse<Order.Response>> callback) {
            RestConnect connect = RestService.getInstance().getConnections();
            final Call<RestResponse<Order.Response>> call = connect.order(token,
                    data.getRequest());
            call.enqueue(new RestCallback<RestResponse<Order.Response>>() {
                @Override
                public void onSuccess(int code, RestResponse<Order.Response> body) {
                    callback.onSuccess(code, body);
                }

                @Override
                public void onFailed(int code, String message) {
                    callback.onFailed(code, message);
                }
            });
        }


        public static void pay(String token, Pay data, final Callback<RestResponse<Pay.Response>> callback) {
            RestConnect connect = RestService.getInstance().getConnections();
            final Call<RestResponse<Pay.Response>> call = connect.pay(token,
                    data.getRequestJson());
            call.enqueue(new RestCallback<RestResponse<Pay.Response>>() {
                @Override
                public void onSuccess(int code, RestResponse<Pay.Response> body) {
                    callback.onSuccess(code, body);
                }

                @Override
                public void onFailed(int code, String message) {
                    callback.onFailed(code, message);
                }
            });
        }

        public static void query(String token, Query data, final Callback<RestResponse<Query.Response>> callback) {
            RestConnect connect = RestService.getInstance().getConnections();
            final Call<RestResponse<Query.Response>> call = connect.query(token,
                    data.getRequest());
            call.enqueue(new RestCallback<RestResponse<Query.Response>>() {
                @Override
                public void onSuccess(int code, RestResponse<Query.Response> body) {
                    callback.onSuccess(code, body);
                }

                @Override
                public void onFailed(int code, String message) {
                    callback.onFailed(code, message);
                }
            });
        }


        public static void notify(String token, PaymentResult data, final Callback<RestResponse<PaymentResult.Response>> callback) {
            RestConnect connect = RestService.getInstance().getConnections();
            final Call<RestResponse<PaymentResult.Response>> call = connect.notify(token,
                    data.getRequestJson());
            call.enqueue(new RestCallback<RestResponse<PaymentResult.Response>>() {
                @Override
                public void onSuccess(int code, RestResponse<PaymentResult.Response> body) {
                    callback.onSuccess(code, body);
                }

                @Override
                public void onFailed(int code, String message) {
                    callback.onFailed(code, message);
                }
            });
        }

    }


}
