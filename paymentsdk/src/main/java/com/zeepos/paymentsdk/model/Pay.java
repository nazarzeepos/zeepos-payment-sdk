package com.zeepos.paymentsdk.model;

import com.google.gson.Gson;
import com.zeepos.paymentsdk.listener.ModelListener;

public class Pay extends BaseModel implements ModelListener {

    Request request = new Request();
    Response response = new Response();
    public class Request{
        //request params;
        private String mch_id;
        private String out_trade_no;
        private String subject;
        private String amount;
        private String currency;
        private String operator_id;
        private String notify_url;
        private String auth_code;

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getOperator_id() {
            return operator_id;
        }

        public void setOperator_id(String operator_id) {
            this.operator_id = operator_id;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public String getAuth_code() {
            return auth_code;
        }

        public void setAuth_code(String auth_code) {
            this.auth_code = auth_code;
        }
    }

    public class Response{
        private String trade_no;

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }
    }



    @Override
    public String getRequestJson() {
        return new Gson().toJson(request);
    }
}
