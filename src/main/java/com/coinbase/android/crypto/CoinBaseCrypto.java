package com.coinbase.android.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class CoinBaseCrypto {
    private static boolean injected;

    public static synchronized void getGWTProvider() {
        synchronized (CoinBaseCrypto.class) {
            if (!injected) {
                Security.insertProviderAt(CryptoGwtProvider.INSTANCE, 0);
                injected = true;
            }
        }
    }

    public static SecretKeyFactory getKeyFactory() {
        SecretKeyFactory instance;
        try {
            instance = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            getGWTProvider();
            try {
                instance = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
        return instance;
    }

    public static byte[] getKey(char[] password, byte[] salt, int numOfIterations, int keyLength) {
        try {
            return getKeyFactory().generateSecret(new PBEKeySpec(password, salt, numOfIterations, keyLength)).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }
}
