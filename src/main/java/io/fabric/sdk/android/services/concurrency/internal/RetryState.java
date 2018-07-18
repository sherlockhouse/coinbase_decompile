package io.fabric.sdk.android.services.concurrency.internal;

public class RetryState {
    private final Backoff backoff;
    private final int retryCount;
    private final RetryPolicy retryPolicy;

    public RetryState(Backoff backoff, RetryPolicy retryPolicy) {
        this(0, backoff, retryPolicy);
    }

    public RetryState(int retryCount, Backoff backoff, RetryPolicy retryPolicy) {
        this.retryCount = retryCount;
        this.backoff = backoff;
        this.retryPolicy = retryPolicy;
    }

    public long getRetryDelay() {
        return this.backoff.getDelayMillis(this.retryCount);
    }

    public RetryState nextRetryState() {
        return new RetryState(this.retryCount + 1, this.backoff, this.retryPolicy);
    }

    public RetryState initialRetryState() {
        return new RetryState(this.backoff, this.retryPolicy);
    }
}
