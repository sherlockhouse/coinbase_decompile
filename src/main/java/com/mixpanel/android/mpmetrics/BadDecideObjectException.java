package com.mixpanel.android.mpmetrics;

class BadDecideObjectException extends Exception {
    public BadDecideObjectException(String detailMessage) {
        super(detailMessage);
    }

    public BadDecideObjectException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
