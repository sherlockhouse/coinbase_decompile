package com.android.installreferrer.commons;

import android.util.Log;

public final class InstallReferrerCommons {
    public static void logVerbose(String tag, String msg) {
        if (Log.isLoggable(tag, 2)) {
            Log.v(tag, msg);
        }
    }

    public static void logWarn(String tag, String msg) {
        if (Log.isLoggable(tag, 5)) {
            Log.w(tag, msg);
        }
    }
}
