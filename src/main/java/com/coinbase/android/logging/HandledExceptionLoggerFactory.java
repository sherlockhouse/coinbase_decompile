package com.coinbase.android.logging;

public class HandledExceptionLoggerFactory {
    private static final HandledExceptionLogger sLogger = new CrashlyticsExceptionLogger();

    public static HandledExceptionLogger get() {
        return sLogger;
    }
}
