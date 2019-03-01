package com.zeepos.paymentsdk.rest;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RestCallback<T extends RestResponse> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            if (response.body().getCode() != 0) {
                onFailed(response.body().getCode(), response.body().getErr());
            } else
                onSuccess(response.code(), response.body());
        } else {
            try {
                JSONObject body = new JSONObject(response.errorBody().toString());
                onFailed(Integer.parseInt(body.getString("err_code")), body.getString("err_msg"));
            } catch (JSONException e) {
                e.printStackTrace();
                onFailed(response.code(), "data corrupt");
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailed(600, t.getMessage());
        t.printStackTrace();
    }


    public abstract void onSuccess(int code, T body);

    public abstract void onFailed(int code, String message);


}
