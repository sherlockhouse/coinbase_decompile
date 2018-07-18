package com.coinbase.android.logging;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.impl.CoinbaseLoggerAdapter;
import org.slf4j.impl.CoinbaseLoggerFactory;

public class CrashlyticsExceptionLogger implements HandledExceptionLogger {
    private static final Set<String> IGNORE_STACK_TRACES = new HashSet(Arrays.asList(IGNORE_STACK_TRACES_ARRAY));
    private static final String[] IGNORE_STACK_TRACES_ARRAY = new String[]{CoinbaseLoggerAdapter.class.getName(), CrashlyticsExceptionLogger.class.getName(), HandledException.class.getName(), CoinbaseLoggerFactory.class.getName()};

    private class HandledException extends Exception {
        private static final long serialVersionUID = 1;

        HandledException(String detailMessage) {
            super(detailMessage);
        }

        HandledException(String detailMessage, Throwable tr) {
            super(detailMessage, tr);
        }

        public StackTraceElement[] getStackTrace() {
            return CrashlyticsExceptionLogger.this.trimStackTrace(super.getStackTrace());
        }
    }

    public void e(String message, Throwable tr) {
        if (!Fabric.isInitialized()) {
            return;
        }
        if (tr == null) {
            Crashlytics.logException(new HandledException(message));
        } else {
            Crashlytics.logException(new HandledException(message, tr));
        }
    }

    private StackTraceElement[] trimStackTrace(StackTraceElement[] trace) {
        int i = 0;
        while (trace != null && i < trace.length) {
            if (IGNORE_STACK_TRACES.contains(trace[i].getClassName())) {
                i++;
            } else if (i == 0) {
                return trace;
            } else {
                StackTraceElement[] trimmed = new StackTraceElement[(trace.length - i)];
                System.arraycopy(trace, i, trimmed, 0, trimmed.length);
                return trimmed;
            }
        }
        return trace;
    }
}
