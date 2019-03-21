package com.zeepos.paymentsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.GsonBuilder;
import com.zeepos.paymentsdk.Const;
import com.zeepos.paymentsdk.listener.ModelListener;


public class Order extends BaseModel implements ModelListener, Parcelable {
    public static final String ORDER = "order";
    Request request = new Request();
    Response response = new Response();

    public class Request implements Parcelable {
        //request params;
        private String mch_id;
        private String app_id;
        private String account_id;
        private String out_trade_no;
        private String subject;
        private int amount;
        private String currency = Const.CURRENCY_DEFAULT;
        private String operator_id;
        private String notify_url;
        private String trade_type = Const.TRADE_TYPE_DEFAULT;
        private String h_url = "";
        private String r_url = "";

        public final Creator<Request> CREATOR = new Creator<Request>() {
            @Override
            public Request createFromParcel(Parcel in) {
                return new Request(in);
            }

            @Override
            public Request[] newArray(int size) {
                return new Request[size];
            }
        };

        public String getMch_id() {
            return mch_id;
        }

        public Request setMch_id(String mch_id) {
            this.mch_id = mch_id;
            return this;
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

        public String getSubject() {
            return subject;
        }

        public Request setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public int getAmount() {
            return amount;
        }

        public Request setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public String getCurrency() {
            return currency;
        }

        public Request setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public String getOperator_id() {
            return operator_id;
        }

        public Request setOperator_id(String operator_id) {
            this.operator_id = operator_id;
            return this;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public Request setNotify_url(String notify_url) {
            this.notify_url = notify_url;
            return this;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public Request setTrade_type(String trade_type) {
            this.trade_type = trade_type;
            return this;
        }

        public String getH_url() {
            return h_url;
        }

        public Request setH_url(String h_url) {
            this.h_url = h_url;
            return this;
        }

        public String getR_url() {
            return r_url;
        }

        public Request setR_url(String r_url) {
            this.r_url = r_url;
            return this;
        }

        public Request() {
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.mch_id);
            dest.writeString(this.app_id);
            dest.writeString(this.account_id);
            dest.writeString(this.out_trade_no);
            dest.writeString(this.subject);
            dest.writeInt(this.amount);
            dest.writeString(this.currency);
            dest.writeString(this.operator_id);
            dest.writeString(this.notify_url);
            dest.writeString(this.trade_type);
            dest.writeString(this.h_url);
            dest.writeString(this.r_url);
        }

        protected Request(Parcel in) {
            this.mch_id = in.readString();
            this.app_id = in.readString();
            this.account_id = in.readString();
            this.out_trade_no = in.readString();
            this.subject = in.readString();
            this.amount = in.readInt();
            this.currency = in.readString();
            this.operator_id = in.readString();
            this.notify_url = in.readString();
            this.trade_type = in.readString();
            this.h_url = in.readString();
            this.r_url = in.readString();
        }
//
//        public transient final Creator<Request> CREATOR = new Creator<Request>() {
//            @Override
//            public Request createFromParcel(Parcel source) {
//                return new Request(source);
//            }
//
//            @Override
//            public Request[] newArray(int size) {
//                return new Request[size];
//            }
//        };
    }

    public class Response implements Parcelable {
        private String trade_no;
        private String uri;

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.trade_no);
            dest.writeString(this.uri);
        }

        public Response() {
        }

        protected Response(Parcel in) {
            this.trade_no = in.readString();
            this.uri = in.readString();
        }

        public transient final Creator<Response> CREATOR = new Creator<Response>() {
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


    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

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

    @Override
    public String getRequestJson() {
        return new GsonBuilder().disableHtmlEscaping().serializeNulls().create().toJson(request, Request.class);
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

    public Order() {
    }

    protected Order(Parcel in) {
        this.request = in.readParcelable(Request.class.getClassLoader());
        this.response = in.readParcelable(Response.class.getClassLoader());
    }

//    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
//        @Override
//        public Order createFromParcel(Parcel source) {
//            return new Order(source);
//        }
//
//        @Override
//        public Order[] newArray(int size) {
//            return new Order[size];
//        }
//    };
}
