package com.coinbase;

import android.util.Base64;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.io.IOUtils;

public class CallbackVerifierImpl implements CallbackVerifier {
    private static PublicKey _publicKey = null;

    private static synchronized PublicKey getPublicKey() {
        PublicKey publicKey;
        synchronized (CallbackVerifierImpl.class) {
            if (_publicKey == null) {
                try {
                    _publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(IOUtils.toByteArray(CallbackVerifierImpl.class.getResourceAsStream("/com/coinbase/api/coinbase-callback.pub.der"))));
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex2) {
                    throw new RuntimeException(ex2);
                } catch (InvalidKeySpecException ex3) {
                    throw new RuntimeException(ex3);
                }
            }
            publicKey = _publicKey;
        }
        return publicKey;
    }

    public boolean verifyCallback(String body, String signature) {
        boolean z = false;
        try {
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(getPublicKey());
            sig.update(body.getBytes());
            z = sig.verify(Base64.decode(signature, 0));
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        } catch (InvalidKeyException ex2) {
            throw new RuntimeException(ex2);
        } catch (SignatureException e) {
        }
        return z;
    }
}
