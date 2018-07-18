package com.fasterxml.jackson.core.util;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.fasterxml.jackson.core.Version;
import java.util.regex.Pattern;

public class VersionUtil {
    private static final Pattern V_SEP = Pattern.compile("[-_./;:]");

    public static Version parseVersion(String s, String groupId, String artifactId) {
        String str = null;
        int i = 0;
        if (s != null) {
            s = s.trim();
            if (s.length() > 0) {
                int parseVersionPart;
                String[] parts = V_SEP.split(s);
                int parseVersionPart2 = parseVersionPart(parts[0]);
                if (parts.length > 1) {
                    parseVersionPart = parseVersionPart(parts[1]);
                } else {
                    parseVersionPart = 0;
                }
                if (parts.length > 2) {
                    i = parseVersionPart(parts[2]);
                }
                if (parts.length > 3) {
                    str = parts[3];
                }
                return new Version(parseVersionPart2, parseVersionPart, i, str, groupId, artifactId);
            }
        }
        return null;
    }

    protected static int parseVersionPart(String s) {
        int number = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c > '9' || c < NumericKeypadConnector.ZERO) {
                break;
            }
            number = (number * 10) + (c - 48);
        }
        return number;
    }

    public static final void throwInternal() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }
}
