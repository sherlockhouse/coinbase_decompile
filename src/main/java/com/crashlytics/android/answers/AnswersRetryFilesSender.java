package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.concurrency.internal.DefaultRetryPolicy;
import io.fabric.sdk.android.services.concurrency.internal.ExponentialBackoff;
import io.fabric.sdk.android.services.concurrency.internal.RetryState;
import io.fabric.sdk.android.services.events.FilesSender;
import java.io.File;
import java.util.List;

class AnswersRetryFilesSender implements FilesSender {
    private final SessionAnalyticsFilesSender filesSender;
    private final RetryManager retryManager;

    public static AnswersRetryFilesSender build(SessionAnalyticsFilesSender filesSender) {
        return new AnswersRetryFilesSender(filesSender, new RetryManager(new RetryState(new RandomBackoff(new ExponentialBackoff(1000, 8), 0.1d), new DefaultRetryPolicy(5))));
    }

    AnswersRetryFilesSender(SessionAnalyticsFilesSender filesSender, RetryManager retryManager) {
        this.filesSender = filesSender;
        this.retryManager = retryManager;
    }

    public boolean send(List<File> files) {
        long currentNanoTime = System.nanoTime();
        if (!this.retryManager.canRetry(currentNanoTime)) {
            return false;
        }
        if (this.filesSender.send(files)) {
            this.retryManager.reset();
            return true;
        }
        this.retryManager.recordRetry(currentNanoTime);
        return false;
    }
}
