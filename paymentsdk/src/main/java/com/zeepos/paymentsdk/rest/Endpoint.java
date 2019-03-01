package com.zeepos.paymentsdk.rest;

public class Endpoint {
    public static final String ORDER = "order";
    public static final String PAY = "pay";
    public static final String QUERY = "query";
    public static final String NOTIFY = "notify_payment";

    //base
    public static final String PATH = "zeepos-payment-integration/api/alto/";
    public static final String DOMAIN = "http://35.240.211.193:3080/";

    public static String getBaseDomain(){
        return DOMAIN+PATH;
    }
}
