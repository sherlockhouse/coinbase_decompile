package org.joda.time;

import org.joda.time.format.DateTimeFormat;

public class IllegalInstantException extends IllegalArgumentException {
    public IllegalInstantException(String str) {
        super(str);
    }

    public IllegalInstantException(long j, String str) {
        super(createMessage(j, str));
    }

    private static String createMessage(long j, String str) {
        return "Illegal instant due to time zone offset transition (daylight savings time 'gap'): " + DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(j)) + (str != null ? " (" + str + ")" : "");
    }
}
