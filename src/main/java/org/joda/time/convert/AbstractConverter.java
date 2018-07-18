package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.PeriodType;
import org.joda.time.chrono.ISOChronology;

public abstract class AbstractConverter implements Converter {
    protected AbstractConverter() {
    }

    public long getInstantMillis(Object obj, Chronology chronology) {
        return DateTimeUtils.currentTimeMillis();
    }

    public Chronology getChronology(Object obj, DateTimeZone dateTimeZone) {
        return ISOChronology.getInstance(dateTimeZone);
    }

    public Chronology getChronology(Object obj, Chronology chronology) {
        return DateTimeUtils.getChronology(chronology);
    }

    public PeriodType getPeriodType(Object obj) {
        return PeriodType.standard();
    }

    public String toString() {
        return "Converter[" + (getSupportedType() == null ? "null" : getSupportedType().getName()) + "]";
    }
}
