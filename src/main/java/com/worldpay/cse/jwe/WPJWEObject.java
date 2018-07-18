package com.worldpay.cse.jwe;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.worldpay.cse.exception.WPCSEException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.util.encoders.UrlBase64;

public class WPJWEObject {
    private static final Character DOT = Character.valueOf(NumericKeypadConnector.DOT);
    private byte[] authTag;
    private byte[] cipherText;
    private byte[] encryptedKey;
    private WPJWEHeader header;
    private byte[] iv;
    private Key key;
    private String payload;

    public WPJWEObject(WPJWEHeader header, String payload) {
        this.header = header;
        this.payload = payload;
    }

    public void encrypt() throws WPCSEException {
        try {
            this.iv = WPKeyGen.generateKey(96);
            byte[] aad = base64URLEncode(this.header.toString().getBytes()).getBytes("ASCII");
            byte[] cKey = WPKeyGen.generateKey(256);
            WPEncrypter cEncrypter = new WPAESEncrypter(new SecretKeySpec(cKey, "AES"), this.iv, aad);
            this.encryptedKey = new WPRSAEncrypter(this.key).encrypt(cKey);
            byte[] cipher = cEncrypter.encrypt(this.payload.getBytes());
            int tagPos = cipher.length - 16;
            this.cipherText = new byte[tagPos];
            this.authTag = new byte[16];
            System.arraycopy(cipher, 0, this.cipherText, 0, this.cipherText.length);
            System.arraycopy(cipher, tagPos, this.authTag, 0, this.authTag.length);
        } catch (Exception e) {
            throw new WPCSEException(e.getMessage(), e);
        }
    }

    private String base64URLEncode(byte[] bytes) throws UnsupportedEncodingException {
        String string = new String(UrlBase64.encode(bytes));
        int index = string.indexOf(DOT.charValue());
        if (index > 0) {
            return string.substring(0, index);
        }
        return string;
    }

    public String serialize() throws WPCSEException {
        try {
            StringBuilder sb = new StringBuilder(base64URLEncode(this.header.toString().getBytes()));
            sb.append(DOT);
            sb.append(base64URLEncode(this.encryptedKey));
            sb.append(DOT);
            sb.append(base64URLEncode(this.iv));
            sb.append(DOT);
            sb.append(base64URLEncode(this.cipherText));
            sb.append(DOT);
            sb.append(base64URLEncode(this.authTag));
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new WPCSEException("Unsupported encoding exception", e);
        }
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
