package com.firebase.jobdispatcher;

public final class RetryStrategy {
    public static final RetryStrategy DEFAULT_EXPONENTIAL = new RetryStrategy(1, 30, 3600);
    public static final RetryStrategy DEFAULT_LINEAR = new RetryStrategy(2, 30, 3600);
    private final int initialBackoff;
    private final int maximumBackoff;
    private final int policy;

    static final class Builder {
        private final ValidationEnforcer validator;

        Builder(ValidationEnforcer validator) {
            this.validator = validator;
        }
    }

    RetryStrategy(int policy, int initialBackoff, int maximumBackoff) {
        this.policy = policy;
        this.initialBackoff = initialBackoff;
        this.maximumBackoff = maximumBackoff;
    }

    public int getPolicy() {
        return this.policy;
    }

    public int getInitialBackoff() {
        return this.initialBackoff;
    }

    public int getMaximumBackoff() {
        return this.maximumBackoff;
    }
}
