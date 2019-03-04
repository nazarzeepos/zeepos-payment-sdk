package com.zeepos.paymentsdk.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zeepos.paymentsdk.Callback;
import com.zeepos.paymentsdk.Const;
import com.zeepos.paymentsdk.Payment;
import com.zeepos.paymentsdk.R;
import com.zeepos.paymentsdk.model.Order;
import com.zeepos.paymentsdk.model.PaymentResult;
import com.zeepos.paymentsdk.model.Query;
import com.zeepos.paymentsdk.rest.RestResponse;
import com.zeepos.paymentsdk.util.QrcodeGenerator;

public class OrderTransaction extends Fragment {
    TextView tvTitle;
    LinearLayout waitingHolder;
    LinearLayout statusHolder;
    RelativeLayout showQrHolder;
    ImageView ivQrCode;
    Order order;
    public Callback<Order.Response> orderCallback;
    public Callback<Query.Response> resultCallback;
    public void setOrderCallback(Callback<Order.Response> orderCallback) {
        this.orderCallback = orderCallback;
    }

    public void setPaymentResultCallback(Callback<Query.Response> resultCallback){
        this.resultCallback = resultCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_transaction, container, false);
        initView(view);
        initBundle();
        initData();
        return view;
    }

    private void initView(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        waitingHolder = (LinearLayout) view.findViewById(R.id.waiting_holder);
        statusHolder = (LinearLayout) view.findViewById(R.id.status_holder);
        showQrHolder = (RelativeLayout) view.findViewById(R.id.show_qr) ;
        ivQrCode = (ImageView) view.findViewById(R.id.qr_code);
    }

    private void initBundle() {
        order = getArguments().getParcelable(Order.ORDER);
    }

    private void initData() {
        waitingHolder.setVisibility(View.VISIBLE);
        Payment.Do.order(Const.TOKEN_SAMPLE, order, new Callback<RestResponse<Order.Response>>() {
            @Override
            public void onSuccess(int code, RestResponse<Order.Response> body) {
                Order.Response response = new Gson().fromJson(body.getData(), Order.Response.class);
                ivQrCode.setImageBitmap(QrcodeGenerator.generateQRCodeImage(response.getUri()));
//                ivQrCode.setImageBitmap(QrcodeGenerator.generateQRCodeImage(body.getData().getUri()));
                orderCallback.onSuccess(code, response);
            }

            @Override
            public void onFailed(int code, String message) {
                if (code == 5513){
                    tvTitle.setText("");
                    statusHolder.setVisibility(View.VISIBLE);
                    waitingHolder.setVisibility(View.GONE);
                }
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                orderCallback.onFailed(code, message);
            }
        });
    }

    public void checkPayment(Query query) {
        Payment.Do.query(Const.TOKEN_SAMPLE, query, new Callback<RestResponse<Query.Response>>() {
            @Override
            public void onSuccess(int code, RestResponse<Query.Response> body) {
                Query.Response response = new Gson().fromJson(body.getData(), Query.Response.class);
                resultCallback.onSuccess(code,response);
                if (response.getTrade_status() == 1) {
                    tvTitle.setText("Transaction ID: "+ response.getTrade_no());
                    statusHolder.setVisibility(View.VISIBLE);
                    waitingHolder.setVisibility(View.GONE);
                }else
                    Toast.makeText(getActivity(),"Transaction not yet paid",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailed(int code, String message) {
                resultCallback.onFailed(code,message);
            }
        });
    }
}
