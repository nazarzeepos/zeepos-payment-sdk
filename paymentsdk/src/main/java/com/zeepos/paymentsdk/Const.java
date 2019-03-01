package com.zeepos.paymentsdk;

public class Const {
    public static final String TOKEN_SAMPLE = "5fcd3b8262c1618ce1280a415b444f0a8a298d06182e1461ff9c7f3f41c7b27b";
    public static final String MCH_ID_SAMPLE = "100663";
    public static final String PCKG8_KEY_SAMPLE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJvb3wm1Gt8wP0fY1sz9QCyVL7k6 Nw/zF7jYY0anslunGoHp1WiFPK+sat2HOZQfly42nkYsbF7VxmLmCY9VLmTqL/ L3I7lUt63X1KaXfjCzIYOOmW1xjwaoprYzZOEVUjGULF2Pc4Z5vIR6OuF4zl05CPElcP7QEhrRUC/ lzCjxAgMBAAECgYB/Jt9M8h0cHCZkHPkQf0NNm+A1QtMNBgtCV/ aV+9W8hBVSC0x5WslpoEYGuH025JbXsuNo6HD45XpV0MnXz2xqzcUXNJ5EYXgFSpX4OCm97kmTJ RYw0Dzg4VMov7v4T0EBZbQXeGl2jaTJ0usZKzuQKzDJk2bB+Jd2w5xRLdtBqQJBAMzYxwtyL1BGtM3 0Z6Xa4KqVG/ quzz0iA8TPh37560T9NfvYx9FuXkxRNUCM1P5iEiReAcxM6rbB2C5CDRB40UcCQQDCx3KVrsupz3eH BWDhmRgqc1PKTYL8u3Q5HcIu5z1aAJ0Qrq/kdFdh94HdlNMdhWBMRaJr/ q1NPP8BO1T0RhAHAkAZ7jcEE+g5WTn5+D5GmHZoIYfxK7/AFVY0y7BzOl+10/ fJcZ+Zu0bkkkMTcMBlkzSItf20RuhPSip7cJEGzMRxAkBNOca0khktupQpdHh0+b4bFp6iFxlYGvp5qtLS xdwNUzDy7E/QxE/ hB8D1mYwaqEcF9pXvO4p6lTSFyIBmWWEBAkBobn+9vCbUgdvvkbV62ivqI0SRPk9yLnxZ5XVhfTXfsl DfBPVJZgijmiybZylR/b06wbsn2SATalgqPXF5mCki";
    public static final String SIGN_TYPE_DEFAULT = "RSA";
    public static final String CURRENCY_DEFAULT = "IDR";
    public static String TRADE_TYPE_DEFAULT = TRADE_TYPE.QR_CODE;

    public static class PAY_TYPE {
        // customer scan qr url payment
        public static final String ORDER = "order";

        // merchant scan qr customer to pay
        public static final String PAY = "pay";
    }

    public static class TRADE_TYPE {
        public static final String QR_CODE = "QR_CODE";
        public static final String WAP = "WAP";
    }
}
