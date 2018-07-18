package org.joda.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.joda.time.base.BasePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

public final class MonthDay extends BasePartial implements Serializable, ReadablePartial {
    private static final DateTimeFieldType[] FIELD_TYPES = new DateTimeFieldType[]{DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()};
    private static final DateTimeFormatter PARSER = new DateTimeFormatterBuilder().appendOptional(ISODateTimeFormat.localDateParser().getParser()).appendOptional(DateTimeFormat.forPattern("--MM-dd").getParser()).toFormatter();

    public static MonthDay parse(String str) {
        return parse(str, PARSER);
    }

    public static MonthDay parse(String str, DateTimeFormatter dateTimeFormatter) {
        LocalDate parseLocalDate = dateTimeFormatter.parseLocalDate(str);
        return new MonthDay(parseLocalDate.getMonthOfYear(), parseLocalDate.getDayOfMonth());
    }

    public MonthDay(int i, int i2) {
        this(i, i2, null);
    }

    public MonthDay(int i, int i2, Chronology chronology) {
        super(new int[]{i, i2}, chronology);
    }

    public int size() {
        return 2;
    }

    protected DateTimeField getField(int i, Chronology chronology) {
        switch (i) {
            case 0:
                return chronology.monthOfYear();
            case 1:
                return chronology.dayOfMonth();
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    public DateTimeFieldType getFieldType(int i) {
        return FIELD_TYPES[i];
    }

    public String toString() {
        Collection arrayList = new ArrayList();
        arrayList.add(DateTimeFieldType.monthOfYear());
        arrayList.add(DateTimeFieldType.dayOfMonth());
        return ISODateTimeFormat.forFields(arrayList, true, true).print((ReadablePartial) this);
    }
}
