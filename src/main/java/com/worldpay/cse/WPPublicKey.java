package com.worldpay.cse;

import com.worldpay.cse.exception.WPCSEInvalidPublicKey;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class WPPublicKey {
    private RSAPublicKey key = null;
    private String keySeqNo = null;

    public static WPPublicKey parseKey(String plainKey) throws WPCSEInvalidPublicKey {
        Exception e;
        try {
            String[] components = plainKey.split("#");
            if (components.length < 3) {
                throw new WPCSEInvalidPublicKey();
            }
            RSAPublicKey key = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(components[2], 16), new BigInteger(components[1], 16)));
            String keySeqNo = components[0];
            if (!"".equals(keySeqNo)) {
                return new WPPublicKey(key, keySeqNo);
            }
            throw new WPCSEInvalidPublicKey();
        } catch (InvalidKeySpecException e2) {
            e = e2;
            throw new WPCSEInvalidPublicKey(e);
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            throw new WPCSEInvalidPublicKey(e);
        } catch (NumberFormatException e4) {
            e = e4;
            throw new WPCSEInvalidPublicKey(e);
        }
    }

    public WPPublicKey(RSAPublicKey key, String keySeqNo) {
        this.key = key;
        this.keySeqNo = keySeqNo;
    }

    public RSAPublicKey getKey() {
        return this.key;
    }

    public String getKeySeqNo() {
        return this.keySeqNo;
    }

    public String toString() {
        return this.keySeqNo + "#" + this.key.getPublicExponent().toString(16) + "#" + this.key.getModulus().toString(16);
    }
}
