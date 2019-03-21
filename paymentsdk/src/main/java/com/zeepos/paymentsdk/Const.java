package com.zeepos.paymentsdk;

public class Const {
    public static final String TOKEN_SAMPLE = "5fcd3b8262c1618ce1280a415b444f0a8a298d06182e1461ff9c7f3f41c7b27b";
    public static final String MCH_ID_SAMPLE = "100663";
    public static final String PCKG8_KEY_SAMPLE = "MIICWwIBAAKBgQCb298JtRrfMD9H2NbM/ UAslS+5OjcP8xe42GNGp7JbpxqB6dVohTyvrGrdhzmUH5cuNp5GLGxe1cZi5gmPVS5k6i/ y9yO5VLet19Sml34wsyGDjpltcY8GqKa2M2ThFVIxlCxdj3OGebyEejrheM5dOQjxJXD+0BIa0VAv5cw o8QIDAQABAoGAfybfTPIdHBwmZBz5EH9DTZvgNULTDQYLQlf2lfvVvIQVUgtMeVrJaaBGBrh9NuSW 17LjaOhw+OV6VdDJ189sas3FFzSeRGF4BUqV+Dgpve5JkyUWMNA84OFTKL+7+E9BAWW0F3hpdo 2kydLrGSs7kCswyZNmwfiXdsOcUS3bQakCQQDM2McLci9QRrTN9Gel2uCqlRv6rs89IgPEz4d++etE/ TX72MfRbl5MUTVAjNT+YhIkXgHMTOq2wdguQg0QeNFHAkEAwsdyla7Lqc93hwVg4ZkYKnNTyk2C/ Lt0OR3CLuc9WgCdEK6v5HRXYfeB3ZTTHYVgTEWia/6tTTz/ ATtU9EYQBwJAGe43BBPoOVk5+fg+Rph2aCGH8Su/ wBVWNMuwczpftdP3yXGfmbtG5JJDE3DAZZM0iLX9tEboT0oqe3CRBszEcQJATTnGtJIZLbqUKXR4dP m+GxaeohcZWBr6earS0sXcDVMw8uxP0MRP4QfA9ZmMGqhHBfaV7zuKepU0hciAZllhAQJAaG5/vbwm1IHb75G1etor6iNEkT5Pci58WeV1YX0137JQ3wT1SWYIo5osm2cpUf29OsG7J9kgE2pYKj1xeZ gpIg==";
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
