package com.zeepos.paymentsdk.util;


import android.util.Base64;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

public class Sign{
    /*
     * Generated a signed String
     * @param text : string to sign
     * @param strPrivateKey : private key (String format)
     */
    public static String getDigitalSignature(String text, String strPrivateKey)  {

        try {

            // Get private key from String
            PrivateKey pk = loadPrivateKey(strPrivateKey);
            // text to bytes
            byte[] data = text.getBytes("UTF8");

            // signature
            Signature sig = Signature.getInstance("MD5WithRSA");
            sig.initSign(pk);
            sig.update(data);
            byte[] signatureBytes = sig.sign();
            return Base64.encodeToString(signatureBytes,Base64.DEFAULT);

        }catch(Exception e){
            return null;
        }
    }

    private static PrivateKey loadPrivateKey(String key64) throws GeneralSecurityException {
        byte[] clear = Base64.decode(key64, Base64.DEFAULT);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance("MD5WithRSA");
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }
}
