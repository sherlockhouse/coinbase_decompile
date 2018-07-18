package com.coinbase.android.crypto;

import com.coinbase.android.Log;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Pbkdf2 extends SecretKeyFactorySpi {
    static final /* synthetic */ boolean $assertionsDisabled = (!Pbkdf2.class.desiredAssertionStatus());
    private String algorithm;
    private String prfAlgorithm;

    public static class HmacSHA1 extends Pbkdf2 {
        protected HmacSHA1() throws NoSuchAlgorithmException {
            super("PBKDF2WithHmacSHA1", "HmacSHA1");
        }
    }

    private static class Pbkdf2State {
        byte[] block;
        final int c;
        int currentIteration = 0;
        final int hLen;
        int i = 1;
        final int l;
        final byte[] output;
        final Mac prf;
        final byte[] salt;
        public byte[] u;

        Pbkdf2State(Mac prf, byte[] salt, int c, int bitsToGenerate) {
            this.prf = prf;
            this.hLen = prf.getMacLength();
            this.c = c;
            this.salt = salt;
            int dkLen = bitsToGenerate / 8;
            this.l = ((this.hLen + dkLen) - 1) / this.hLen;
            this.output = new byte[dkLen];
        }
    }

    protected Pbkdf2(String algorithm, String prf) throws NoSuchAlgorithmException {
        this.algorithm = algorithm;
        this.prfAlgorithm = prf;
    }

    byte[] generate(char[] password, byte[] salt, int c, int bitsToGenerate) {
        Pbkdf2State state = initState(password, salt, c, bitsToGenerate);
        while (state.i <= state.l) {
            f(state, c);
            state.i++;
        }
        return state.output;
    }

    private Pbkdf2State initState(char[] password, byte[] salt, int c, int bitsToGenerate) {
        Mac prf = null;
        SecretKeySpec key = new SecretKeySpec(ByteArrayUtils.toBytes(password), this.prfAlgorithm);
        try {
            prf = Mac.getInstance(this.prfAlgorithm);
            prf.init(key);
        } catch (GeneralSecurityException e) {
            Log.d("Coinbase", "Unexpected exception: " + e);
        }
        if ($assertionsDisabled || prf != null) {
            return new Pbkdf2State(prf, salt, c, bitsToGenerate);
        }
        throw new AssertionError();
    }

    private void f(Pbkdf2State state, int chunkSize) {
        Mac prf = state.prf;
        int c = state.c;
        int i = state.i;
        int offset = (i - 1) * state.hLen;
        if (isFirstIteration(state)) {
            initF(state, prf, i);
        }
        int chunk = 0;
        while (!isFComplete(state, c) && chunk < chunkSize) {
            state.u = prf.doFinal(state.u);
            ByteArrayUtils.xor(state.block, 0, state.u, 0, state.u.length);
            chunk++;
            state.currentIteration++;
        }
        if (isFComplete(state, c)) {
            finalizeF(state, offset);
        }
    }

    private void finalizeF(Pbkdf2State state, int offset) {
        System.arraycopy(state.block, 0, state.output, offset, Math.min(state.block.length, state.output.length - offset));
        state.currentIteration = 0;
    }

    private void initF(Pbkdf2State state, Mac prf, int i) {
        state.block = new byte[prf.getMacLength()];
        prf.update(state.salt);
        prf.update(ByteArrayUtils.toBytes(i));
        state.u = prf.doFinal();
        System.arraycopy(state.u, 0, state.block, 0, state.u.length);
        state.currentIteration = 2;
    }

    private boolean isFComplete(Pbkdf2State state, int c) {
        return state.currentIteration > c;
    }

    private boolean isFirstIteration(Pbkdf2State state) {
        return state.currentIteration == 0;
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
        PBEKeySpec pbeKeySpec = (PBEKeySpec) keySpec;
        return new PBESecretKeyImpl(this.algorithm, pbeKeySpec, generate(pbeKeySpec.getPassword(), pbeKeySpec.getSalt(), pbeKeySpec.getIterationCount(), pbeKeySpec.getKeyLength()));
    }

    protected KeySpec engineGetKeySpec(SecretKey secretKey, Class aClass) throws InvalidKeySpecException {
        throw new UnsupportedOperationException();
    }

    protected SecretKey engineTranslateKey(SecretKey secretKey) throws InvalidKeyException {
        throw new UnsupportedOperationException();
    }
}
