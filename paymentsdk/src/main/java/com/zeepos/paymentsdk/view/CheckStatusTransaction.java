package com.zeepos.paymentsdk.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zeepos.paymentsdk.Callback;
import com.zeepos.paymentsdk.R;
import com.zeepos.paymentsdk.model.Order;
import com.zeepos.paymentsdk.model.PaymentResult;

public class CheckStatusTransaction extends Fragment {
    TextView tvTitle;
    RelativeLayout progressHolder;
    ImageView ivQrCode;
    Order order;
    public Callback<PaymentResult.Response> resultCallback;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_status,container,false);
        initView(view);
        initBundle();
        initListener();
        initData(view);
        return view;
    }

    private void initView(View view) {
    }

    private void initBundle() {
    }

    private void initListener() {
    }
    private void initData(View view) {
    }



    public void setResultCallback(Callback<PaymentResult.Response> resultCallback) {
        this.resultCallback = resultCallback;
    }
}
