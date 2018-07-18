package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class DateTime extends BaseDateTime implements Serializable, ReadableDateTime {
    public static DateTime now() {
        return new DateTime();
    }

    public static DateTime parse(String str) {
        return parse(str, ISODateTimeFormat.dateTimeParser().withOffsetParsed());
    }

    public static DateTime parse(String str, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.parseDateTime(str);
    }

    public DateTime(long j, DateTimeZone dateTimeZone) {
        super(j, dateTimeZone);
    }

    public DateTime(long j, Chronology chronology) {
        super(j, chronology);
    }

    public DateTime(Object obj, DateTimeZone dateTimeZone) {
        super(obj, dateTimeZone);
    }

    public DateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, Chronology chronology) {
        super(i, i2, i3, i4, i5, i6, i7, chronology);
    }

    public DateTime toDateTime() {
        return this;
    }

    public DateTime withChronology(Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        return chronology2 == getChronology() ? this : new DateTime(getMillis(), chronology2);
    }

    public DateTime withZone(DateTimeZone dateTimeZone) {
        return withChronology(getChronology().withZone(dateTimeZone));
    }
}
