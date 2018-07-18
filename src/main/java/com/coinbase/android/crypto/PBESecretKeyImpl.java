package com.coinbase.android.crypto;

import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;

public class PBESecretKeyImpl implements PBEKey {
    private static final long serialVersionUID = -7980717779970757743L;
    private String algorithm;
    private byte[] key;
    private PBEKeySpec spec;

    public PBESecretKeyImpl(String algorithm, PBEKeySpec spec, byte[] key) {
        this.algorithm = algorithm;
        this.spec = spec;
        this.key = key;
    }

    public int getIterationCount() {
        return this.spec.getIterationCount();
    }

    public char[] getPassword() {
        return this.spec.getPassword();
    }

    public byte[] getSalt() {
        return this.spec.getSalt();
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public byte[] getEncoded() {
        return this.key;
    }

    public String getFormat() {
        return "RAW";
    }
}
