package org.slf4j;

public interface Logger {
    void error(String str);

    void error(String str, Throwable th);

    String getName();
}
