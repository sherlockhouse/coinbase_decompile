package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.ISODateTimeFormat;

public final class LocalDateTime extends BaseLocal implements Serializable, ReadablePartial {
    private final Chronology iChronology;
    private final long iLocalMillis;

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private transient DateTimeField iField;
        private transient LocalDateTime iInstant;

        Property(LocalDateTime localDateTime, DateTimeField dateTimeField) {
            this.iInstant = localDateTime;
            this.iField = dateTimeField;
        }

        public DateTimeField getField() {
            return this.iField;
        }

        protected long getMillis() {
            return this.iInstant.getLocalMillis();
        }

        protected Chronology getChronology() {
            return this.iInstant.getChronology();
        }
    }

    public LocalDateTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public LocalDateTime(DateTimeZone dateTimeZone) {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(dateTimeZone));
    }

    public LocalDateTime(long j) {
        this(j, ISOChronology.getInstance());
    }

    public LocalDateTime(long j, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iLocalMillis = chronology2.getZone().getMillisKeepLocal(DateTimeZone.UTC, j);
        this.iChronology = chronology2.withUTC();
    }

    public LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this(i, i2, i3, i4, i5, i6, i7, ISOChronology.getInstanceUTC());
    }

    public LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, Chronology chronology) {
        Chronology withUTC = DateTimeUtils.getChronology(chronology).withUTC();
        long dateTimeMillis = withUTC.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
        this.iChronology = withUTC;
        this.iLocalMillis = dateTimeMillis;
    }

    public int size() {
        return 4;
    }

    protected DateTimeField getField(int i, Chronology chronology) {
        switch (i) {
            case 0:
                return chronology.year();
            case 1:
                return chronology.monthOfYear();
            case 2:
                return chronology.dayOfMonth();
            case 3:
                return chronology.millisOfDay();
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    public int getValue(int i) {
        switch (i) {
            case 0:
                return getChronology().year().get(getLocalMillis());
            case 1:
                return getChronology().monthOfYear().get(getLocalMillis());
            case 2:
                return getChronology().dayOfMonth().get(getLocalMillis());
            case 3:
                return getChronology().millisOfDay().get(getLocalMillis());
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    public int get(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return dateTimeFieldType.getField(getChronology()).get(getLocalMillis());
        }
        throw new IllegalArgumentException("The DateTimeFieldType must not be null");
    }

    public boolean isSupported(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            return false;
        }
        return dateTimeFieldType.getField(getChronology()).isSupported();
    }

    protected long getLocalMillis() {
        return this.iLocalMillis;
    }

    public Chronology getChronology() {
        return this.iChronology;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) obj;
            if (this.iChronology.equals(localDateTime.iChronology)) {
                return this.iLocalMillis == localDateTime.iLocalMillis;
            }
        }
        return super.equals(obj);
    }

    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (readablePartial instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) readablePartial;
            if (this.iChronology.equals(localDateTime.iChronology)) {
                int i = this.iLocalMillis < localDateTime.iLocalMillis ? -1 : this.iLocalMillis == localDateTime.iLocalMillis ? 0 : 1;
                return i;
            }
        }
        return super.compareTo(readablePartial);
    }

    public DateTime toDateTime() {
        return toDateTime((DateTimeZone) null);
    }

    public DateTime toDateTime(DateTimeZone dateTimeZone) {
        return new DateTime(getYear(), getMonthOfYear(), getDayOfMonth(), getHourOfDay(), getMinuteOfHour(), getSecondOfMinute(), getMillisOfSecond(), this.iChronology.withZone(DateTimeUtils.getZone(dateTimeZone)));
    }

    public LocalDate toLocalDate() {
        return new LocalDate(getLocalMillis(), getChronology());
    }

    public LocalTime toLocalTime() {
        return new LocalTime(getLocalMillis(), getChronology());
    }

    public int getYear() {
        return getChronology().year().get(getLocalMillis());
    }

    public int getMonthOfYear() {
        return getChronology().monthOfYear().get(getLocalMillis());
    }

    public int getDayOfMonth() {
        return getChronology().dayOfMonth().get(getLocalMillis());
    }

    public int getHourOfDay() {
        return getChronology().hourOfDay().get(getLocalMillis());
    }

    public int getMinuteOfHour() {
        return getChronology().minuteOfHour().get(getLocalMillis());
    }

    public int getSecondOfMinute() {
        return getChronology().secondOfMinute().get(getLocalMillis());
    }

    public int getMillisOfSecond() {
        return getChronology().millisOfSecond().get(getLocalMillis());
    }

    public Property year() {
        return new Property(this, getChronology().year());
    }

    public Property monthOfYear() {
        return new Property(this, getChronology().monthOfYear());
    }

    public Property dayOfMonth() {
        return new Property(this, getChronology().dayOfMonth());
    }

    public Property hourOfDay() {
        return new Property(this, getChronology().hourOfDay());
    }

    public Property minuteOfHour() {
        return new Property(this, getChronology().minuteOfHour());
    }

    public Property secondOfMinute() {
        return new Property(this, getChronology().secondOfMinute());
    }

    public Property millisOfSecond() {
        return new Property(this, getChronology().millisOfSecond());
    }

    public String toString() {
        return ISODateTimeFormat.dateTime().print((ReadablePartial) this);
    }
}
