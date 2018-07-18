package com.firebase.jobdispatcher;

import android.net.Uri;
import android.os.Bundle;
import com.coinbase.api.internal.ApiConstants;
import com.firebase.jobdispatcher.JobTrigger.ContentUriTrigger;
import com.firebase.jobdispatcher.JobTrigger.ExecutionWindowTrigger;

final class GooglePlayJobWriter {
    private final JobCoder jobCoder = new JobCoder("com.firebase.jobdispatcher.", false);

    GooglePlayJobWriter() {
    }

    private static void writeExecutionWindowTriggerToBundle(JobParameters job, Bundle b, ExecutionWindowTrigger trigger) {
        b.putInt("trigger_type", 1);
        if (job.isRecurring()) {
            b.putLong(ApiConstants.PERIOD, (long) trigger.getWindowEnd());
            b.putLong("period_flex", (long) (trigger.getWindowEnd() - trigger.getWindowStart()));
            return;
        }
        b.putLong("window_start", (long) trigger.getWindowStart());
        b.putLong("window_end", (long) trigger.getWindowEnd());
    }

    private static void writeImmediateTriggerToBundle(Bundle b) {
        b.putInt("trigger_type", 2);
        b.putLong("window_start", 0);
        b.putLong("window_end", 1);
    }

    private static void writeContentUriTriggerToBundle(Bundle data, ContentUriTrigger uriTrigger) {
        data.putInt("trigger_type", 3);
        int size = uriTrigger.getUris().size();
        int[] flagsArray = new int[size];
        Uri[] uriArray = new Uri[size];
        for (int i = 0; i < size; i++) {
            ObservedUri uri = (ObservedUri) uriTrigger.getUris().get(i);
            flagsArray[i] = uri.getFlags();
            uriArray[i] = uri.getUri();
        }
        data.putIntArray("content_uri_flags_array", flagsArray);
        data.putParcelableArray("content_uri_array", uriArray);
    }

    public Bundle writeToBundle(JobParameters job, Bundle b) {
        b.putString("tag", job.getTag());
        b.putBoolean("update_current", job.shouldReplaceCurrent());
        b.putBoolean("persisted", job.getLifetime() == 2);
        b.putString("service", GooglePlayReceiver.class.getName());
        writeTriggerToBundle(job, b);
        writeConstraintsToBundle(job, b);
        writeRetryStrategyToBundle(job, b);
        Bundle extras = job.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        b.putBundle("extras", this.jobCoder.encode(job, extras));
        return b;
    }

    private static void writeRetryStrategyToBundle(JobParameters job, Bundle b) {
        RetryStrategy strategy = job.getRetryStrategy();
        Bundle rb = new Bundle();
        rb.putInt("retry_policy", convertRetryPolicyToLegacyVersion(strategy.getPolicy()));
        rb.putInt("initial_backoff_seconds", strategy.getInitialBackoff());
        rb.putInt("maximum_backoff_seconds", strategy.getMaximumBackoff());
        b.putBundle("retryStrategy", rb);
    }

    private static int convertRetryPolicyToLegacyVersion(int policy) {
        switch (policy) {
            case 2:
                return 1;
            default:
                return 0;
        }
    }

    private static void writeTriggerToBundle(JobParameters job, Bundle b) {
        JobTrigger trigger = job.getTrigger();
        if (trigger == Trigger.NOW) {
            writeImmediateTriggerToBundle(b);
        } else if (trigger instanceof ExecutionWindowTrigger) {
            writeExecutionWindowTriggerToBundle(job, b, (ExecutionWindowTrigger) trigger);
        } else if (trigger instanceof ContentUriTrigger) {
            writeContentUriTriggerToBundle(b, (ContentUriTrigger) trigger);
        } else {
            throw new IllegalArgumentException("Unknown trigger: " + trigger.getClass());
        }
    }

    private static void writeConstraintsToBundle(JobParameters job, Bundle b) {
        boolean z = true;
        int c = Constraint.compact(job.getConstraints());
        b.putBoolean("requiresCharging", (c & 4) == 4);
        String str = "requiresIdle";
        if ((c & 8) != 8) {
            z = false;
        }
        b.putBoolean(str, z);
        b.putInt("requiredNetwork", convertConstraintsToLegacyNetConstant(c));
    }

    private static int convertConstraintsToLegacyNetConstant(int constraintMap) {
        int reqNet = 2;
        if ((constraintMap & 2) == 2) {
            reqNet = 0;
        }
        if ((constraintMap & 1) == 1) {
            return 1;
        }
        return reqNet;
    }
}
