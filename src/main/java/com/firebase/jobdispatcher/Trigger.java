package com.firebase.jobdispatcher;

import com.firebase.jobdispatcher.JobTrigger.ContentUriTrigger;
import com.firebase.jobdispatcher.JobTrigger.ExecutionWindowTrigger;
import com.firebase.jobdispatcher.JobTrigger.ImmediateTrigger;
import java.util.List;

public final class Trigger {
    public static final ImmediateTrigger NOW = new ImmediateTrigger();

    public static ExecutionWindowTrigger executionWindow(int windowStart, int windowEnd) {
        if (windowStart < 0) {
            throw new IllegalArgumentException("Window start can't be less than 0");
        } else if (windowEnd >= windowStart) {
            return new ExecutionWindowTrigger(windowStart, windowEnd);
        } else {
            throw new IllegalArgumentException("Window end can't be less than window start");
        }
    }

    public static ContentUriTrigger contentUriTrigger(List<ObservedUri> uris) {
        if (uris != null && !uris.isEmpty()) {
            return new ContentUriTrigger(uris);
        }
        throw new IllegalArgumentException("Uris must not be null or empty.");
    }
}
