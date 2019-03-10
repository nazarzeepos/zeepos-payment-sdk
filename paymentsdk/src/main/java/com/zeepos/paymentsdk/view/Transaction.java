package com.zeepos.paymentsdk.view;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.zeepos.paymentsdk.AltoPayListener;
import com.zeepos.paymentsdk.Callback;
import com.zeepos.paymentsdk.R;
import com.zeepos.paymentsdk.helper.FragmentHelper;
import com.zeepos.paymentsdk.model.Order;
import com.zeepos.paymentsdk.model.Query;
import com.zeepos.paymentsdk.util.QrcodeGenerator;

public class Transaction extends DialogFragment implements View.OnClickListener {
    ImageView btnClose;
    TextView tvTitle;
    Button btnSubmit;
    Button btnCheckStatus;
    FrameLayout container;
    View view;
    AltoPayListener altoPayListener;
    Query query = new Query();
    public String print_id;
    OrderTransaction orderTransaction = new OrderTransaction();
    CheckStatusTransaction checkStatusTransaction = new CheckStatusTransaction();


    String tradeNo = "";
    boolean paid = false;
    String message = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_transaction, container, false);
        FragmentHelper.getInstance().setFragmentManager(getFragmentManager());

        initView();
        initListener();
        startFragment();
        return view;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void setAltoPayListener(AltoPayListener altoPayListener){
        this.altoPayListener = altoPayListener;
    }
    private void initView() {
        container = (FrameLayout) view.findViewById(R.id.frame_content);
        btnClose = (ImageView) view.findViewById(R.id.iv_close);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnCheckStatus = (Button) view.findViewById(R.id.btn_check_status);

        print_id = getArguments().getString("print_id");
    }

    private void initListener() {
        btnClose.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnCheckStatus.setOnClickListener(this);
    }

    private void startFragment() {
        final Bundle bundle = getArguments();
        if (bundle.getParcelable(Order.ORDER) != null) {
            orderTransaction.setArguments(bundle);
            orderTransaction.setOrderCallback(new Callback<Order.Response>() {
                @Override
                public void onSuccess(int code, Order.Response body) {
                    Order order = bundle.getParcelable(Order.ORDER);
                    try {
                        Bitmap bitmap = QrcodeGenerator.encodeAsBitmap(body.getUri());
                        altoPayListener.onQrCode(bitmap);
                        tradeNo = body.getTrade_no();

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    query.getRequest()
                            .setApp_id(order.getRequest().getApp_id())
                            .setAccount_id(order.getRequest().getAccount_id())
                            .setOut_trade_no(order.getRequest().getOut_trade_no())
                            .setTrade_no(body.getTrade_no());
                }

                @Override
                public void onFailed(int code, String message) {
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }
            });
            orderTransaction.setPaymentResultCallback(new Callback<Query.Response>() {
                @Override
                public void onSuccess(int code, Query.Response body) {
                    paid = true;
                }

                @Override
                public void onFailed(int code, String message) {
                    Transaction.this.message = message;
                }
            });
            FragmentHelper.getInstance().replaceFragment(R.id.frame_content, orderTransaction, true, getChildFragmentManager().beginTransaction());
        }
    }


    @Override
    public void onClick(View v) {
        if (btnCheckStatus == v) {
            orderTransaction.checkPayment(query);
        }
        if (btnSubmit == v) {
            if  (TextUtils.isEmpty(tradeNo) && paid) {
                dismiss();
            }else
                Toast.makeText(getContext(),"Transaction not yet paid",Toast.LENGTH_LONG).show();
        }
        if (btnClose == v) {
            dismiss();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (TextUtils.isEmpty(tradeNo) && paid) {
            altoPayListener.onSuccess(tradeNo);
        } else {
            altoPayListener.onfailed(message);
        }
    }
}
