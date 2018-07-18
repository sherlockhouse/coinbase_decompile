package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.ISOChronology;

class ReadableInstantConverter extends AbstractConverter implements InstantConverter, PartialConverter {
    static final ReadableInstantConverter INSTANCE = new ReadableInstantConverter();

    protected ReadableInstantConverter() {
    }

    public Chronology getChronology(Object obj, DateTimeZone dateTimeZone) {
        Chronology chronology = ((ReadableInstant) obj).getChronology();
        if (chronology == null) {
            return ISOChronology.getInstance(dateTimeZone);
        }
        if (chronology.getZone() == dateTimeZone) {
            return chronology;
        }
        chronology = chronology.withZone(dateTimeZone);
        if (chronology == null) {
            return ISOChronology.getInstance(dateTimeZone);
        }
        return chronology;
    }

    public Chronology getChronology(Object obj, Chronology chronology) {
        if (chronology == null) {
            return DateTimeUtils.getChronology(((ReadableInstant) obj).getChronology());
        }
        return chronology;
    }

    public long getInstantMillis(Object obj, Chronology chronology) {
        return ((ReadableInstant) obj).getMillis();
    }

    public Class<?> getSupportedType() {
        return ReadableInstant.class;
    }
}
