package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class DividedDateTimeField extends DecoratedDateTimeField {
    final int iDivisor;
    final DurationField iDurationField;
    private final int iMax;
    private final int iMin;

    public DividedDateTimeField(DateTimeField dateTimeField, DateTimeFieldType dateTimeFieldType, int i) {
        super(dateTimeField, dateTimeFieldType);
        if (i < 2) {
            throw new IllegalArgumentException("The divisor must be at least 2");
        }
        DurationField durationField = dateTimeField.getDurationField();
        if (durationField == null) {
            this.iDurationField = null;
        } else {
            this.iDurationField = new ScaledDurationField(durationField, dateTimeFieldType.getDurationType(), i);
        }
        this.iDivisor = i;
        int minimumValue = dateTimeField.getMinimumValue();
        minimumValue = minimumValue >= 0 ? minimumValue / i : ((minimumValue + 1) / i) - 1;
        int maximumValue = dateTimeField.getMaximumValue();
        maximumValue = maximumValue >= 0 ? maximumValue / i : ((maximumValue + 1) / i) - 1;
        this.iMin = minimumValue;
        this.iMax = maximumValue;
    }

    public int get(long j) {
        int i = getWrappedField().get(j);
        if (i >= 0) {
            return i / this.iDivisor;
        }
        return ((i + 1) / this.iDivisor) - 1;
    }

    public long add(long j, int i) {
        return getWrappedField().add(j, this.iDivisor * i);
    }

    public long add(long j, long j2) {
        return getWrappedField().add(j, ((long) this.iDivisor) * j2);
    }

    public int getDifference(long j, long j2) {
        return getWrappedField().getDifference(j, j2) / this.iDivisor;
    }

    public long getDifferenceAsLong(long j, long j2) {
        return getWrappedField().getDifferenceAsLong(j, j2) / ((long) this.iDivisor);
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i, this.iMin, this.iMax);
        return getWrappedField().set(j, getRemainder(getWrappedField().get(j)) + (this.iDivisor * i));
    }

    public DurationField getDurationField() {
        return this.iDurationField;
    }

    public int getMinimumValue() {
        return this.iMin;
    }

    public int getMaximumValue() {
        return this.iMax;
    }

    public long roundFloor(long j) {
        DateTimeField wrappedField = getWrappedField();
        return wrappedField.roundFloor(wrappedField.set(j, get(j) * this.iDivisor));
    }

    public long remainder(long j) {
        return set(j, get(getWrappedField().remainder(j)));
    }

    private int getRemainder(int i) {
        if (i >= 0) {
            return i % this.iDivisor;
        }
        return (this.iDivisor - 1) + ((i + 1) % this.iDivisor);
    }
}
