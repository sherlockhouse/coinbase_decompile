package com.firebase.jobdispatcher;

import android.os.Bundle;

final class JobInvocation implements JobParameters {
    private final int[] constraints;
    private final Bundle extras;
    private final int lifetime;
    private final boolean recurring;
    private final boolean replaceCurrent;
    private final RetryStrategy retryStrategy;
    private final String service;
    private final String tag;
    private final JobTrigger trigger;
    private final TriggerReason triggerReason;

    static final class Builder {
        private int[] constraints;
        private final Bundle extras = new Bundle();
        private int lifetime;
        private boolean recurring;
        private boolean replaceCurrent;
        private RetryStrategy retryStrategy;
        private String service;
        private String tag;
        private JobTrigger trigger;
        private TriggerReason triggerReason;

        Builder() {
        }

        JobInvocation build() {
            if (this.tag != null && this.service != null && this.trigger != null) {
                return new JobInvocation();
            }
            throw new IllegalArgumentException("Required fields were not populated.");
        }

        public Builder setTag(String mTag) {
            this.tag = mTag;
            return this;
        }

        public Builder setService(String mService) {
            this.service = mService;
            return this;
        }

        public Builder setTrigger(JobTrigger mTrigger) {
            this.trigger = mTrigger;
            return this;
        }

        public Builder setRecurring(boolean mRecurring) {
            this.recurring = mRecurring;
            return this;
        }

        public Builder setLifetime(int mLifetime) {
            this.lifetime = mLifetime;
            return this;
        }

        public Builder setConstraints(int[] mConstraints) {
            this.constraints = mConstraints;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                this.extras.putAll(bundle);
            }
            return this;
        }

        public Builder setRetryStrategy(RetryStrategy mRetryStrategy) {
            this.retryStrategy = mRetryStrategy;
            return this;
        }

        public Builder setReplaceCurrent(boolean mReplaceCurrent) {
            this.replaceCurrent = mReplaceCurrent;
            return this;
        }

        public Builder setTriggerReason(TriggerReason triggerReason) {
            this.triggerReason = triggerReason;
            return this;
        }
    }

    private JobInvocation(Builder builder) {
        this.tag = builder.tag;
        this.service = builder.service;
        this.trigger = builder.trigger;
        this.retryStrategy = builder.retryStrategy;
        this.recurring = builder.recurring;
        this.lifetime = builder.lifetime;
        this.constraints = builder.constraints;
        this.extras = builder.extras;
        this.replaceCurrent = builder.replaceCurrent;
        this.triggerReason = builder.triggerReason;
    }

    public String getService() {
        return this.service;
    }

    public String getTag() {
        return this.tag;
    }

    public JobTrigger getTrigger() {
        return this.trigger;
    }

    public int getLifetime() {
        return this.lifetime;
    }

    public boolean isRecurring() {
        return this.recurring;
    }

    public int[] getConstraints() {
        return this.constraints;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public RetryStrategy getRetryStrategy() {
        return this.retryStrategy;
    }

    public boolean shouldReplaceCurrent() {
        return this.replaceCurrent;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }
        JobInvocation jobInvocation = (JobInvocation) o;
        if (this.tag.equals(jobInvocation.tag) && this.service.equals(jobInvocation.service)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.tag.hashCode() * 31) + this.service.hashCode();
    }
}
