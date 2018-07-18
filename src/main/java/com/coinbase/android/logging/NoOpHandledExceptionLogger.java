package com.coinbase.android.logging;

public class NoOpHandledExceptionLogger implements HandledExceptionLogger {
    public void e(String message, Throwable tr) {
    }
}
