package com.firebase.jobdispatcher;

import java.util.List;

public class JobTrigger {

    public static final class ContentUriTrigger extends JobTrigger {
        private final List<ObservedUri> uris;

        ContentUriTrigger(List<ObservedUri> uris) {
            this.uris = uris;
        }

        public List<ObservedUri> getUris() {
            return this.uris;
        }
    }

    public static final class ExecutionWindowTrigger extends JobTrigger {
        private final int windowEnd;
        private final int windowStart;

        ExecutionWindowTrigger(int windowStart, int windowEnd) {
            this.windowStart = windowStart;
            this.windowEnd = windowEnd;
        }

        public int getWindowStart() {
            return this.windowStart;
        }

        public int getWindowEnd() {
            return this.windowEnd;
        }
    }

    public static final class ImmediateTrigger extends JobTrigger {
        ImmediateTrigger() {
        }
    }
}
