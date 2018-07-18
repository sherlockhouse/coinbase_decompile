package org.spongycastle.crypto;

public class CryptoException extends Exception {
    private Throwable cause;

    public CryptoException(String message) {
        super(message);
    }

    public Throwable getCause() {
        return this.cause;
    }
}
