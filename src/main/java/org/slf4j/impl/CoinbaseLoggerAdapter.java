package org.slf4j.impl;

import android.util.Log;
import com.coinbase.android.logging.HandledExceptionLogger;
import com.coinbase.android.utils.CryptoUri;
import org.slf4j.helpers.MarkerIgnoringBase;

public final class CoinbaseLoggerAdapter extends MarkerIgnoringBase {
    private final String mCallingClassName;
    private final HandledExceptionLogger mHandledExceptionLogger;

    public CoinbaseLoggerAdapter(String callingClassName, HandledExceptionLogger handledExceptionLogger) {
        this.mCallingClassName = callingClassName;
        this.mHandledExceptionLogger = handledExceptionLogger;
    }

    public boolean isErrorEnabled() {
        return isLoggingEnabled(6);
    }

    public void error(String msg) {
        if (isErrorEnabled()) {
            log(6, this.mCallingClassName, getLineNumber(), msg, null);
        }
    }

    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            log(6, this.mCallingClassName, getLineNumber(), msg, t);
        }
    }

    boolean isLoggingEnabled(int level) {
        return level == 6;
    }

    private void log(int level, String fileName, int lineNumber, String message, Throwable tr) {
        try {
            if (isLoggingEnabled(level)) {
                StringBuilder messageBuffer = new StringBuilder(message);
                if (tr != null) {
                    messageBuffer.append(": ").append(Log.getStackTraceString(tr));
                }
                Log.println(level, "Coinbase:" + fileName + CryptoUri.URI_SCHEME_DELIMETER + lineNumber, messageBuffer.toString());
                if (level == 6) {
                    this.mHandledExceptionLogger.e(message, tr);
                }
            }
        } catch (Exception e) {
            if (isLoggingEnabled(level)) {
                e.printStackTrace();
            }
        }
    }

    int getLineNumber() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (this.mCallingClassName.contains(element.getClassName())) {
                return element.getLineNumber();
            }
        }
        return 0;
    }
}
