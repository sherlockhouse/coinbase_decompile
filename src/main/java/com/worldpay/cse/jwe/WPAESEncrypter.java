package com.worldpay.cse.jwe;

import com.worldpay.cse.exception.WPCSEException;
import java.security.Key;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.engines.AESFastEngine;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;

class WPAESEncrypter implements WPEncrypter {
    private byte[] aad;
    private byte[] iv;
    private Key key;

    public WPAESEncrypter(Key key, byte[] iv, byte[] aad) {
        this.iv = (byte[]) iv.clone();
        this.key = key;
        this.aad = (byte[]) aad.clone();
    }

    public byte[] encrypt(byte[] data) {
        AEADParameters parameters = new AEADParameters(new KeyParameter(this.key.getEncoded()), 128, this.iv, this.aad);
        GCMBlockCipher gcmEngine = new GCMBlockCipher(new AESFastEngine());
        gcmEngine.init(true, parameters);
        byte[] cipherText = new byte[gcmEngine.getOutputSize(data.length)];
        try {
            gcmEngine.doFinal(cipherText, gcmEngine.processBytes(data, 0, data.length, cipherText, 0));
            return cipherText;
        } catch (InvalidCipherTextException e) {
            throw new WPCSEException(e.getLocalizedMessage(), e);
        }
    }
}
