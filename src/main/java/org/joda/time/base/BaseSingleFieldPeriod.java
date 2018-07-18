package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;

public abstract class BaseSingleFieldPeriod implements Serializable, Comparable<BaseSingleFieldPeriod>, ReadablePeriod {
    private volatile int iPeriod;

    public abstract DurationFieldType getFieldType();

    public abstract PeriodType getPeriodType();

    protected static int between(ReadableInstant readableInstant, ReadableInstant readableInstant2, DurationFieldType durationFieldType) {
        if (readableInstant != null && readableInstant2 != null) {
            return durationFieldType.getField(DateTimeUtils.getInstantChronology(readableInstant)).getDifference(readableInstant2.getMillis(), readableInstant.getMillis());
        }
        throw new IllegalArgumentException("ReadableInstant objects must not be null");
    }

    protected BaseSingleFieldPeriod(int i) {
        this.iPeriod = i;
    }

    protected int getValue() {
        return this.iPeriod;
    }

    public int size() {
        return 1;
    }

    public DurationFieldType getFieldType(int i) {
        if (i == 0) {
            return getFieldType();
        }
        throw new IndexOutOfBoundsException(String.valueOf(i));
    }

    public int getValue(int i) {
        if (i == 0) {
            return getValue();
        }
        throw new IndexOutOfBoundsException(String.valueOf(i));
    }

    public int get(DurationFieldType durationFieldType) {
        if (durationFieldType == getFieldType()) {
            return getValue();
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadablePeriod)) {
            return false;
        }
        ReadablePeriod readablePeriod = (ReadablePeriod) obj;
        if (readablePeriod.getPeriodType() == getPeriodType() && readablePeriod.getValue(0) == getValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((getValue() + 459) * 27) + getFieldType().hashCode();
    }

    public int compareTo(BaseSingleFieldPeriod baseSingleFieldPeriod) {
        if (baseSingleFieldPeriod.getClass() != getClass()) {
            throw new ClassCastException(getClass() + " cannot be compared to " + baseSingleFieldPeriod.getClass());
        }
        int value = baseSingleFieldPeriod.getValue();
        int value2 = getValue();
        if (value2 > value) {
            return 1;
        }
        if (value2 < value) {
            return -1;
        }
        return 0;
    }
}
