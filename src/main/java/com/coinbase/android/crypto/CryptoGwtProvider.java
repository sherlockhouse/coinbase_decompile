package com.coinbase.android.crypto;

import com.coinbase.android.crypto.Pbkdf2.HmacSHA1;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Collections;
import javax.crypto.SecretKeyFactorySpi;

public class CryptoGwtProvider extends Provider {
    public static final Provider INSTANCE = new CryptoGwtProvider();

    private CryptoGwtProvider() {
        super("CRYPTOGWT", 1.0d, "");
        putService(new SpiFactoryService(this, "SecretKeyFactory", "PBKDF2WithHmacSHA1", HmacSHA1.class.getName(), Collections.emptyList(), Collections.emptyMap(), new SpiFactory<SecretKeyFactorySpi>() {
            public SecretKeyFactorySpi create(Object constructorParam) throws NoSuchAlgorithmException {
                return new HmacSHA1();
            }
        }));
    }
}
