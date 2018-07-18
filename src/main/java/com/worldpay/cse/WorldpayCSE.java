package com.worldpay.cse;

import com.worldpay.cse.exception.WPCSEException;
import com.worldpay.cse.exception.WPCSEInvalidCardData;
import com.worldpay.cse.exception.WPCSEInvalidPublicKey;
import com.worldpay.cse.jwe.WPJWEHeader;
import com.worldpay.cse.jwe.WPJWEObject;
import java.util.Set;
import java.util.logging.Logger;

public class WorldpayCSE {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private WPPublicKey publicKey;

    public void setPublicKey(String publicKey) throws WPCSEInvalidPublicKey {
        this.publicKey = WPPublicKey.parseKey(publicKey);
    }

    public String encrypt(WPCardData cardData) throws WPCSEException {
        Set<Integer> errors = new WPCardValidator().validateCardData(cardData);
        if (!errors.isEmpty()) {
            throw new WPCSEInvalidCardData(errors);
        } else if (this.publicKey != null) {
            return performEncryption(cardData.toString());
        } else {
            throw new WPCSEException("Public key not set");
        }
    }

    public static Set<Integer> validate(WPCardData data) {
        return new WPCardValidator().validateCardData(data);
    }

    private String performEncryption(String data) {
        WPJWEHeader header = new WPJWEHeader();
        header.setAlgorithm("RSA1_5");
        header.setEncryption("A256GCM");
        header.setKid(this.publicKey.getKeySeqNo());
        header.setApiVersion("1.0");
        header.setLibVersion("1.0.2");
        header.setChannel("android");
        WPJWEObject jweObject = new WPJWEObject(header, data);
        jweObject.setKey(this.publicKey.getKey());
        jweObject.encrypt();
        return jweObject.serialize();
    }
}
