package org.joda.time;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.joda.time.base.BaseLocal;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.ISODateTimeFormat;

public final class LocalTime extends BaseLocal implements Serializable, ReadablePartial {
    public static final LocalTime MIDNIGHT = new LocalTime(0, 0, 0, 0);
    private static final Set<DurationFieldType> TIME_DURATION_TYPES = new HashSet();
    private final Chronology iChronology;
    private final long iLocalMillis;

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private transient DateTimeField iField;
        private transient LocalTime iInstant;

        Property(LocalTime localTime, DateTimeField dateTimeField) {
            this.iInstant = localTime;
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

    static {
        TIME_DURATION_TYPES.add(DurationFieldType.millis());
        TIME_DURATION_TYPES.add(DurationFieldType.seconds());
        TIME_DURATION_TYPES.add(DurationFieldType.minutes());
        TIME_DURATION_TYPES.add(DurationFieldType.hours());
    }

    public LocalTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public LocalTime(long j) {
        this(j, ISOChronology.getInstance());
    }

    public LocalTime(long j, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        long millisKeepLocal = chronology2.getZone().getMillisKeepLocal(DateTimeZone.UTC, j);
        chronology2 = chronology2.withUTC();
        this.iLocalMillis = (long) chronology2.millisOfDay().get(millisKeepLocal);
        this.iChronology = chronology2;
    }

    public LocalTime(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, ISOChronology.getInstanceUTC());
    }

    public LocalTime(int i, int i2, int i3, int i4, Chronology chronology) {
        Chronology withUTC = DateTimeUtils.getChronology(chronology).withUTC();
        long dateTimeMillis = withUTC.getDateTimeMillis(0, i, i2, i3, i4);
        this.iChronology = withUTC;
        this.iLocalMillis = dateTimeMillis;
    }

    public int size() {
        return 4;
    }

    protected DateTimeField getField(int i, Chronology chronology) {
        switch (i) {
            case 0:
                return chronology.hourOfDay();
            case 1:
                return chronology.minuteOfHour();
            case 2:
                return chronology.secondOfMinute();
            case 3:
                return chronology.millisOfSecond();
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    public int getValue(int i) {
        switch (i) {
            case 0:
                return getChronology().hourOfDay().get(getLocalMillis());
            case 1:
                return getChronology().minuteOfHour().get(getLocalMillis());
            case 2:
                return getChronology().secondOfMinute().get(getLocalMillis());
            case 3:
                return getChronology().millisOfSecond().get(getLocalMillis());
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
        if (dateTimeFieldType == null || !isSupported(dateTimeFieldType.getDurationType())) {
            return false;
        }
        DurationFieldType rangeDurationType = dateTimeFieldType.getRangeDurationType();
        if (isSupported(rangeDurationType) || rangeDurationType == DurationFieldType.days()) {
            return true;
        }
        return false;
    }

    public boolean isSupported(DurationFieldType durationFieldType) {
        if (durationFieldType == null) {
            return false;
        }
        DurationField field = durationFieldType.getField(getChronology());
        if (TIME_DURATION_TYPES.contains(durationFieldType) || field.getUnitMillis() < getChronology().days().getUnitMillis()) {
            return field.isSupported();
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
        if (obj instanceof LocalTime) {
            LocalTime localTime = (LocalTime) obj;
            if (this.iChronology.equals(localTime.iChronology)) {
                return this.iLocalMillis == localTime.iLocalMillis;
            }
        }
        return super.equals(obj);
    }

    public int compareTo(ReadablePartial readablePartial) {
        if (this == readablePartial) {
            return 0;
        }
        if (readablePartial instanceof LocalTime) {
            LocalTime localTime = (LocalTime) readablePartial;
            if (this.iChronology.equals(localTime.iChronology)) {
                int i = this.iLocalMillis < localTime.iLocalMillis ? -1 : this.iLocalMillis == localTime.iLocalMillis ? 0 : 1;
                return i;
            }
        }
        return super.compareTo(readablePartial);
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
        return ISODateTimeFormat.time().print((ReadablePartial) this);
    }
}
