package org.joda.time.base;

import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;

public abstract class AbstractDateTime extends AbstractInstant implements ReadableDateTime {
    protected AbstractDateTime() {
    }

    public int getYear() {
        return getChronology().year().get(getMillis());
    }

    public int getWeekyear() {
        return getChronology().weekyear().get(getMillis());
    }

    public String toString(String str) {
        if (str == null) {
            return toString();
        }
        return DateTimeFormat.forPattern(str).print((ReadableInstant) this);
    }
}
