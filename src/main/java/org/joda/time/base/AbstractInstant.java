package org.joda.time.base;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISODateTimeFormat;

public abstract class AbstractInstant implements ReadableInstant {
    protected AbstractInstant() {
    }

    public DateTimeZone getZone() {
        return getChronology().getZone();
    }

    public Instant toInstant() {
        return new Instant(getMillis());
    }

    public DateTime toDateTime() {
        return new DateTime(getMillis(), getZone());
    }

    public MutableDateTime toMutableDateTime() {
        return new MutableDateTime(getMillis(), getZone());
    }

    public Date toDate() {
        return new Date(getMillis());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadableInstant)) {
            return false;
        }
        ReadableInstant readableInstant = (ReadableInstant) obj;
        if (getMillis() == readableInstant.getMillis() && FieldUtils.equals(getChronology(), readableInstant.getChronology())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((int) (getMillis() ^ (getMillis() >>> 32))) + getChronology().hashCode();
    }

    public int compareTo(ReadableInstant readableInstant) {
        if (this == readableInstant) {
            return 0;
        }
        long millis = readableInstant.getMillis();
        long millis2 = getMillis();
        if (millis2 == millis) {
            return 0;
        }
        if (millis2 < millis) {
            return -1;
        }
        return 1;
    }

    public boolean isBefore(long j) {
        return getMillis() < j;
    }

    public boolean isBefore(ReadableInstant readableInstant) {
        return isBefore(DateTimeUtils.getInstantMillis(readableInstant));
    }

    public String toString() {
        return ISODateTimeFormat.dateTime().print((ReadableInstant) this);
    }
}
