package org.apache.http.util;

public final class TextUtils {
    public static boolean isEmpty(CharSequence s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return false;
    }
}
