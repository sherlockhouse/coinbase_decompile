package com.coinbase.android.logging;

public interface HandledExceptionLogger {
    void e(String str, Throwable th);
}
