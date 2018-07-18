package com.worldpay.cse.exception;

public class WPCSEInvalidPublicKey extends WPCSEException {
    public WPCSEInvalidPublicKey() {
        super("Invalid public key");
    }

    public WPCSEInvalidPublicKey(Throwable cause) {
        super("Invalid public key", cause);
    }
}
