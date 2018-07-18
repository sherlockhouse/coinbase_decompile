package com.worldpay.cse.exception;

public class WPCSEException extends RuntimeException {
    public WPCSEException(String displayMessage) {
        super(displayMessage);
    }

    public WPCSEException(String displayMessage, Throwable cause) {
        super(displayMessage, cause);
    }
}
