package org.joda.money;

public final class MoneyUtils {
    static void checkNotNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }
}
