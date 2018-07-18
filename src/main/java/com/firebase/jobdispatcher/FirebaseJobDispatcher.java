package com.firebase.jobdispatcher;

import com.firebase.jobdispatcher.Job.Builder;

public final class FirebaseJobDispatcher {
    private final Driver driver;
    private final Builder retryStrategyBuilder = new Builder(this.validator);
    private final ValidationEnforcer validator;

    public FirebaseJobDispatcher(Driver driver) {
        this.driver = driver;
        this.validator = new ValidationEnforcer(driver.getValidator());
    }

    public int schedule(Job job) {
        if (this.driver.isAvailable()) {
            return this.driver.schedule(job);
        }
        return 2;
    }

    public Builder newJobBuilder() {
        return new Builder(this.validator);
    }
}
