package com.firebase.jobdispatcher;

import android.os.Bundle;

public final class Job implements JobParameters {
    private final int[] constraints;
    private final Bundle extras;
    private final int lifetime;
    private final boolean recurring;
    private final boolean replaceCurrent;
    private final RetryStrategy retryStrategy;
    private final String service;
    private final String tag;
    private final JobTrigger trigger;

    public static final class Builder implements JobParameters {
        private int[] constraints;
        private Bundle extras;
        private int lifetime = 1;
        private boolean recurring = false;
        private boolean replaceCurrent = false;
        private RetryStrategy retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
        private String serviceClassName;
        private String tag;
        private JobTrigger trigger = Trigger.NOW;
        private final ValidationEnforcer validator;

        Builder(ValidationEnforcer validator) {
            this.validator = validator;
        }

        Builder(ValidationEnforcer validator, JobParameters job) {
            this.validator = validator;
            this.tag = job.getTag();
            this.serviceClassName = job.getService();
            this.trigger = job.getTrigger();
            this.recurring = job.isRecurring();
            this.lifetime = job.getLifetime();
            this.constraints = job.getConstraints();
            this.extras = job.getExtras();
            this.retryStrategy = job.getRetryStrategy();
        }

        public Builder setReplaceCurrent(boolean replaceCurrent) {
            this.replaceCurrent = replaceCurrent;
            return this;
        }

        public Job build() {
            this.validator.ensureValid(this);
            return new Job();
        }

        public String getService() {
            return this.serviceClassName;
        }

        public Builder setService(Class<? extends JobService> serviceClass) {
            this.serviceClassName = serviceClass == null ? null : serviceClass.getName();
            return this;
        }

        public String getTag() {
            return this.tag;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public JobTrigger getTrigger() {
            return this.trigger;
        }

        public Builder setTrigger(JobTrigger trigger) {
            this.trigger = trigger;
            return this;
        }

        public int getLifetime() {
            return this.lifetime;
        }

        public boolean isRecurring() {
            return this.recurring;
        }

        public Builder setRecurring(boolean recurring) {
            this.recurring = recurring;
            return this;
        }

        public int[] getConstraints() {
            return this.constraints == null ? new int[0] : this.constraints;
        }

        public Bundle getExtras() {
            return this.extras;
        }

        public Builder setExtras(Bundle extras) {
            this.extras = extras;
            return this;
        }

        public RetryStrategy getRetryStrategy() {
            return this.retryStrategy;
        }

        public boolean shouldReplaceCurrent() {
            return this.replaceCurrent;
        }
    }

    private Job(Builder builder) {
        this.service = builder.serviceClassName;
        this.extras = builder.extras;
        this.tag = builder.tag;
        this.trigger = builder.trigger;
        this.retryStrategy = builder.retryStrategy;
        this.lifetime = builder.lifetime;
        this.recurring = builder.recurring;
        this.constraints = builder.constraints != null ? builder.constraints : new int[0];
        this.replaceCurrent = builder.replaceCurrent;
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

    public String getService() {
        return this.service;
    }
}
