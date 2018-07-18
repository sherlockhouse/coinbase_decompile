package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BasePartial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class YearMonth extends BasePartial implements Serializable, ReadablePartial {
    private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[]{DateTimeFieldType.year(), DateTimeFieldType.monthOfYear()};

    public static YearMonth parse(String str) {
        return parse(str, ISODateTimeFormat.localDateParser());
    }

    public static YearMonth parse(String str, DateTimeFormatter dateTimeFormatter) {
        LocalDate parseLocalDate = dateTimeFormatter.parseLocalDate(str);
        return new YearMonth(parseLocalDate.getYear(), parseLocalDate.getMonthOfYear());
    }

    public YearMonth(int i, int i2) {
        this(i, i2, null);
    }

    public YearMonth(int i, int i2, Chronology chronology) {
        super(new int[]{i, i2}, chronology);
    }

    public int size() {
        return 2;
    }

    protected DateTimeField getField(int i, Chronology chronology) {
        switch (i) {
            case 0:
                return chronology.year();
            case 1:
                return chronology.monthOfYear();
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    public DateTimeFieldType getFieldType(int i) {
        return FIELD_TYPES[i];
    }

    public String toString() {
        return ISODateTimeFormat.yearMonth().print((ReadablePartial) this);
    }
}
