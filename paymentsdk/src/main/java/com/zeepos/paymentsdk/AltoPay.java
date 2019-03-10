package com.zeepos.paymentsdk;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.zeepos.paymentsdk.helper.FragmentHelper;
import com.zeepos.paymentsdk.model.Order;
import com.zeepos.paymentsdk.model.Pay;
import com.zeepos.paymentsdk.view.Transaction;

public class AltoPay {
    Context context;
    Order order;
    Pay pay;
    AltoPayListener altoPayListener;
    private String print_id = "";

    public AltoPay(Context context) {
        this.context = context;
    }
    private Order getOrder() {
        return order;
    }

    public AltoPay setOrder(Order order) {
        this.order = order;
        return this;
    }

    private Pay getPay() {
        return pay;
    }

    public AltoPay setPay(Pay pay) {
        this.pay = pay;
        return this;
    }

    public AltoPay setFragmentManager(FragmentManager fragmentManager){
        FragmentHelper.getInstance().setFragmentManager(fragmentManager);
        return this;
    }

    public AltoPay setAltoPayListener(AltoPayListener altoPayListener) {
        this.altoPayListener = altoPayListener;
        return this;
    }

    public String getPrint_id() {
        return print_id;
    }

    public AltoPay setPrint_id(String print_id) {
        this.print_id = print_id;
        return this;
    }

    public void launch(){
        if (order != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable(Order.ORDER,order);
            bundle.putString("print_id",print_id);
            Transaction transaction = new Transaction();
            transaction.setArguments(bundle);
            transaction.setAltoPayListener(altoPayListener);
            FragmentHelper.getInstance().showDialog(transaction,bundle,"order");
        }
        if (pay != null){
            //flow scan QR customer to merchant
        }
    }

}
