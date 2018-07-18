package org.joda.time.base;

import org.joda.time.ReadableDuration;
import org.joda.time.format.FormatUtils;

public abstract class AbstractDuration implements ReadableDuration {
    protected AbstractDuration() {
    }

    public int compareTo(ReadableDuration readableDuration) {
        long millis = getMillis();
        long millis2 = readableDuration.getMillis();
        if (millis < millis2) {
            return -1;
        }
        if (millis > millis2) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadableDuration)) {
            return false;
        }
        if (getMillis() != ((ReadableDuration) obj).getMillis()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long millis = getMillis();
        return (int) (millis ^ (millis >>> 32));
    }

    public String toString() {
        long millis = getMillis();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PT");
        Object obj = millis < 0 ? 1 : null;
        FormatUtils.appendUnpaddedInteger(stringBuffer, millis);
        while (true) {
            if (stringBuffer.length() >= (obj != null ? 7 : 6)) {
                break;
            }
            stringBuffer.insert(obj != null ? 3 : 2, "0");
        }
        if ((millis / 1000) * 1000 == millis) {
            stringBuffer.setLength(stringBuffer.length() - 3);
        } else {
            stringBuffer.insert(stringBuffer.length() - 3, ".");
        }
        stringBuffer.append('S');
        return stringBuffer.toString();
    }
}
