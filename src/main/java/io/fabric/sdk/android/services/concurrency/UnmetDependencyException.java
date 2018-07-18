package io.fabric.sdk.android.services.concurrency;

public class UnmetDependencyException extends RuntimeException {
    public UnmetDependencyException(String detailMessage) {
        super(detailMessage);
    }
}
