package org.joda.time;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.ISODateTimeFormat;

public final class LocalDate extends BaseLocal implements Serializable, ReadablePartial {
    private static final Set<DurationFieldType> DATE_DURATION_TYPES = new HashSet();
    private final Chronology iChronology;
    private volatile transient int iHash;
    private final long iLocalMillis;

    static {
        DATE_DURATION_TYPES.add(DurationFieldType.days());
        DATE_DURATION_TYPES.add(DurationFieldType.weeks());
        DATE_DURATION_TYPES.add(DurationFieldType.months());
        DATE_DURATION_TYPES.add(DurationFieldType.weekyears());
        DATE_DURATION_TYPES.add(DurationFieldType.years());
        DATE_DURATION_TYPES.add(DurationFieldType.centuries());
        DATE_DURATION_TYPES.add(DurationFieldType.eras());
    }

    public LocalDate() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public LocalDate(long j) {
        this(j, ISOChronology.getInstance());
    }

    public LocalDate(long j, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        long millisKeepLocal = chronology2.getZone().getMillisKeepLocal(DateTimeZone.UTC, j);
        chronology2 = chronology2.withUTC();
        this.iLocalMillis = chronology2.dayOfMonth().roundFloor(millisKeepLocal);
        this.iChronology = chronology2;
    }

    public LocalDate(int i, int i2, int i3) {
        this(i, i2, i3, ISOChronology.getInstanceUTC());
    }

    public LocalDate(int i, int i2, int i3, Chronology chronology) {
        Chronology withUTC = DateTimeUtils.getChronology(chronology).withUTC();
        long dateTimeMillis = withUTC.getDateTimeMillis(i, i2, i3, 0);
        this.iChronology = withUTC;
        this.iLocalMillis = dateTimeMillis;
    }

    public int size() {
        return 3;
    }

    protected DateTimeField getField(int i, Chronology chronology) {
        switch (i) {
            case 0:
                return chronology.year();
            case 1:
                return chronology.monthOfYear();
            case 2:
                return chronology.dayOfMonth();
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
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    public int get(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        } else if (isSupported(dateTimeFieldType)) {
            return dateTimeFieldType.getField(getChronology()).get(getLocalMillis());
        } else {
            throw new IllegalArgumentException("Field '" + dateTimeFieldType + "' is not supported");
        }
    }

    public boolean isSupported(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            return false;
        }
        DurationFieldType durationType = dateTimeFieldType.getDurationType();
        if (DATE_DURATION_TYPES.contains(durationType) || durationType.getField(getChronology()).getUnitMillis() >= getChronology().days().getUnitMillis()) {
            return dateTimeFieldType.getField(getChronology()).isSupported();
        }
        return false;
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
        if (obj instanceof LocalDate) {
            LocalDate localDate = (LocalDate) obj;
            if (this.iChronology.equals(localDate.iChronology)) {
                return this.iLocalMillis == localDate.iLocalMillis;
            }
        }
        return super.equals(obj);
    }

    public int hashCode() {
        int i = this.iHash;
        if (i != 0) {
            return i;
        }
        i = super.hashCode();
        this.iHash = i;
        return i;
    }

    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (readablePartial instanceof LocalDate) {
            LocalDate localDate = (LocalDate) readablePartial;
            if (this.iChronology.equals(localDate.iChronology)) {
                int i = this.iLocalMillis < localDate.iLocalMillis ? -1 : this.iLocalMillis == localDate.iLocalMillis ? 0 : 1;
                return i;
            }
        }
        return super.compareTo(readablePartial);
    }

    @Deprecated
    public DateMidnight toDateMidnight() {
        return toDateMidnight(null);
    }

    @Deprecated
    public DateMidnight toDateMidnight(DateTimeZone dateTimeZone) {
        return new DateMidnight(getYear(), getMonthOfYear(), getDayOfMonth(), getChronology().withZone(DateTimeUtils.getZone(dateTimeZone)));
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

    public Property year() {
        return new Property(this, getChronology().year());
    }

    public Property monthOfYear() {
        return new Property(this, getChronology().monthOfYear());
    }

    public Property dayOfMonth() {
        return new Property(this, getChronology().dayOfMonth());
    }

    public String toString() {
        return ISODateTimeFormat.date().print(this);
    }
}
