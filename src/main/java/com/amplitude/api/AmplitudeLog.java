package com.amplitude.api;

import android.util.Log;

public class AmplitudeLog {
    protected static AmplitudeLog instance = new AmplitudeLog();
    private volatile boolean enableLogging = true;
    private volatile int logLevel = 4;

    public static AmplitudeLog getLogger() {
        return instance;
    }

    private AmplitudeLog() {
    }

    int d(String tag, String msg) {
        if (!this.enableLogging || this.logLevel > 3) {
            return 0;
        }
        return Log.d(tag, msg);
    }

    int e(String tag, String msg) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        return Log.e(tag, msg);
    }

    int e(String tag, String msg, Throwable tr) {
        if (!this.enableLogging || this.logLevel > 6) {
            return 0;
        }
        return Log.e(tag, msg, tr);
    }

    int i(String tag, String msg) {
        if (!this.enableLogging || this.logLevel > 4) {
            return 0;
        }
        return Log.i(tag, msg);
    }

    int w(String tag, String msg) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return Log.w(tag, msg);
    }

    int w(String tag, Throwable tr) {
        if (!this.enableLogging || this.logLevel > 5) {
            return 0;
        }
        return Log.w(tag, tr);
    }
}
