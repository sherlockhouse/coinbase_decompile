package io.fabric.sdk.android;

public interface Logger {
    void d(String str, String str2);

    void d(String str, String str2, Throwable th);

    void e(String str, String str2);

    void e(String str, String str2, Throwable th);

    void i(String str, String str2);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void v(String str, String str2);

    void w(String str, String str2);

    void w(String str, String str2, Throwable th);
}
