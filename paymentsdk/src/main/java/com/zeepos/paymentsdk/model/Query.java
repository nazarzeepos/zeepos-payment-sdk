package com.zeepos.paymentsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.zeepos.paymentsdk.listener.ModelListener;

public class Query extends BaseModel implements ModelListener, Parcelable {

    public static final String QUERY = "query";

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    private Request request = new Request();
    private Response response = new Response();
    public class Request implements Parcelable {
        //request params;
        private String app_id;
        private String account_id;
        private String out_trade_no;
        private String trade_no;

        public Request() {
        }

        public String getApp_id() {
            return app_id;
        }

        public Request setApp_id(String app_id) {
            this.app_id = app_id;
            return this;
        }

        public String getAccount_id() {
            return account_id;
        }

        public Request setAccount_id(String account_id) {
            this.account_id = account_id;
            return this;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public Request setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
            return this;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public Request setTrade_no(String trade_no) {
            this.trade_no = trade_no;
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.app_id);
            dest.writeString(this.account_id);
            dest.writeString(this.out_trade_no);
            dest.writeString(this.trade_no);
        }

        protected Request(Parcel in) {
            this.app_id = in.readString();
            this.account_id = in.readString();
            this.out_trade_no = in.readString();
            this.trade_no = in.readString();
        }

        public  final Creator<Request> CREATOR = new Creator<Request>() {
            @Override
            public Request createFromParcel(Parcel source) {
                return new Request(source);
            }

            @Override
            public Request[] newArray(int size) {
                return new Request[size];
            }
        };
    }

    public class Response implements Parcelable {
        private String mch_id;
        private String out_trade_no;
        private String trade_no;
        private int amount;
        private String currency;
        private String operator_id;
        private int trade_status;
        private int paytype;
        private long finish_time;



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

        public int getTrade_status() {
            return trade_status;
        }


        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setTrade_status(int trade_status) {
            this.trade_status = trade_status;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public long getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(long finish_time) {
            this.finish_time = finish_time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.mch_id);
            dest.writeString(this.out_trade_no);
            dest.writeString(this.trade_no);
            dest.writeInt(this.amount);
            dest.writeString(this.currency);
            dest.writeString(this.operator_id);
            dest.writeInt(this.trade_status);
            dest.writeInt(this.paytype);
            dest.writeLong(this.finish_time);
        }

        public Response() {
        }

        protected Response(Parcel in) {
            this.mch_id = in.readString();
            this.out_trade_no = in.readString();
            this.trade_no = in.readString();
            this.amount = in.readInt();
            this.currency = in.readString();
            this.operator_id = in.readString();
            this.trade_status = in.readInt();
            this.paytype = in.readInt();
            this.finish_time = in.readLong();
        }

        public final Creator<Response> CREATOR = new Creator<Response>() {
            @Override
            public Response createFromParcel(Parcel source) {
                return new Response(source);
            }

            @Override
            public Response[] newArray(int size) {
                return new Response[size];
            }
        };
    }

    @Override
    public String getRequestJson() {
        return new Gson().toJson(request);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.request, flags);
        dest.writeParcelable(this.response, flags);
    }

    public Query() {
    }

    protected Query(Parcel in) {
        this.request = in.readParcelable(Request.class.getClassLoader());
        this.response = in.readParcelable(Response.class.getClassLoader());
    }

    public static final Parcelable.Creator<Query> CREATOR = new Parcelable.Creator<Query>() {
        @Override
        public Query createFromParcel(Parcel source) {
            return new Query(source);
        }

        @Override
        public Query[] newArray(int size) {
            return new Query[size];
        }
    };
}
