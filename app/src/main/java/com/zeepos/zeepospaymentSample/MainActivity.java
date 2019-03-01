package com.zeepos.zeepospaymentSample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zeepos.paymentsdk.AltoPay;
import com.zeepos.paymentsdk.AltoPayListener;
import com.zeepos.paymentsdk.Callback;
import com.zeepos.paymentsdk.Const;
import com.zeepos.paymentsdk.Payment;
import com.zeepos.paymentsdk.helper.FragmentHelper;
import com.zeepos.paymentsdk.model.Order;
import com.zeepos.paymentsdk.rest.RestResponse;
import com.zeepos.paymentsdk.util.PrinterHelper;
import com.zeepos.paymentsdk.view.Transaction;

import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements AltoPayListener {
    public static final String APP_ID = "2";
    public static final String ACCOUNT_ID = "429";
    public String print_id;
    public Button connectPrinter;
    public Button sample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectPrinter = (Button) findViewById(R.id.connect_printer);
        sample = (Button) findViewById(R.id.sample);

        connectPrinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PrinterConnectionDialog().show(getSupportFragmentManager(), "PrinterConnectionDialog");
            }
        });
        new SimpleReceiver<String>(this, "PRINTER") {
            @Override
            public void onReceive(String temp) {
                print_id = temp.substring(temp.length() - 17);
            }
        };

        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order();
                order.getRequest()
                        .setOut_trade_no("11111222")
                        .setAmount(2000)
                        .setOperator_id("11112")
                        .setSubject("transaction1")
                        .setApp_id(APP_ID)
                        .setAccount_id(ACCOUNT_ID)
                        .setTrade_type(Const.TRADE_TYPE.QR_CODE);

                AltoPay altoPay = new AltoPay(MainActivity.this)
                        .setOrder(order)
                        .setFragmentManager(getSupportFragmentManager())
                        .setAltoPayListener(MainActivity.this)
                        .setPrint_id(print_id);

                altoPay.launch();
            }
        });

    }

    @Override
    public void onSuccess(String trade_no) {

    }

    @Override
    public void onfailed(String message) {
    }
}
