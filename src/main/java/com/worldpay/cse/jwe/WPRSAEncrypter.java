package com.worldpay.cse.jwe;

import com.worldpay.cse.exception.WPCSEException;
import java.security.Key;
import javax.crypto.Cipher;

class WPRSAEncrypter implements WPEncrypter {
    private Key key;

    public WPRSAEncrypter(Key key) {
        this.key = key;
    }

    public byte[] encrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, this.key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new WPCSEException(e.getMessage(), e);
        }
    }
}
