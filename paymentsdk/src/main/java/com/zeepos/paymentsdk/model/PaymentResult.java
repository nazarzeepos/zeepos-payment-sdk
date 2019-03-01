package com.zeepos.paymentsdk.model;

import com.google.gson.Gson;
import com.zeepos.paymentsdk.listener.ModelListener;

public class PaymentResult extends BaseModel implements ModelListener {
    Request request = new Request();
    Response response = new Response();

    public class Request {
        private String mch_id;
        private String out_trade_no;
        private String trade_no;
        private String amount;
        private String currency;
        private String operator_id;
        private String trade_status;
        private String paytype;
        private String finish_time;

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

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
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

        public String getTrade_status() {
            return trade_status;
        }

        public void setTrade_status(String trade_status) {
            this.trade_status = trade_status;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }
    }

    public class Response {
    }


    @Override
    public String getRequestJson() {
        return new Gson().toJson(request);
    }
}
