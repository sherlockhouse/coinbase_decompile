package org.joda.time.base;

import org.joda.time.ReadableInterval;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public abstract class AbstractInterval implements ReadableInterval {
    protected AbstractInterval() {
    }

    protected void checkInterval(long j, long j2) {
        if (j2 < j) {
            throw new IllegalArgumentException("The end instant must be greater or equal to the start");
        }
    }

    public long toDurationMillis() {
        return FieldUtils.safeAdd(getEndMillis(), -getStartMillis());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadableInterval)) {
            return false;
        }
        ReadableInterval readableInterval = (ReadableInterval) obj;
        if (getStartMillis() == readableInterval.getStartMillis() && getEndMillis() == readableInterval.getEndMillis() && FieldUtils.equals(getChronology(), readableInterval.getChronology())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long startMillis = getStartMillis();
        long endMillis = getEndMillis();
        return ((((((int) (startMillis ^ (startMillis >>> 32))) + 3007) * 31) + ((int) (endMillis ^ (endMillis >>> 32)))) * 31) + getChronology().hashCode();
    }

    public String toString() {
        DateTimeFormatter withChronology = ISODateTimeFormat.dateTime().withChronology(getChronology());
        StringBuffer stringBuffer = new StringBuffer(48);
        withChronology.printTo(stringBuffer, getStartMillis());
        stringBuffer.append('/');
        withChronology.printTo(stringBuffer, getEndMillis());
        return stringBuffer.toString();
    }
}
