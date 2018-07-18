package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class RemainderDateTimeField extends DecoratedDateTimeField {
    final int iDivisor;
    final DurationField iRangeField;

    public RemainderDateTimeField(DateTimeField dateTimeField, DateTimeFieldType dateTimeFieldType, int i) {
        super(dateTimeField, dateTimeFieldType);
        if (i < 2) {
            throw new IllegalArgumentException("The divisor must be at least 2");
        }
        DurationField durationField = dateTimeField.getDurationField();
        if (durationField == null) {
            this.iRangeField = null;
        } else {
            this.iRangeField = new ScaledDurationField(durationField, dateTimeFieldType.getRangeDurationType(), i);
        }
        this.iDivisor = i;
    }

    public RemainderDateTimeField(DividedDateTimeField dividedDateTimeField) {
        this(dividedDateTimeField, dividedDateTimeField.getType());
    }

    public RemainderDateTimeField(DividedDateTimeField dividedDateTimeField, DateTimeFieldType dateTimeFieldType) {
        super(dividedDateTimeField.getWrappedField(), dateTimeFieldType);
        this.iDivisor = dividedDateTimeField.iDivisor;
        this.iRangeField = dividedDateTimeField.iDurationField;
    }

    public int get(long j) {
        int i = getWrappedField().get(j);
        if (i >= 0) {
            return i % this.iDivisor;
        }
        return ((i + 1) % this.iDivisor) + (this.iDivisor - 1);
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i, 0, this.iDivisor - 1);
        return getWrappedField().set(j, (getDivided(getWrappedField().get(j)) * this.iDivisor) + i);
    }

    public DurationField getRangeDurationField() {
        return this.iRangeField;
    }

    public int getMinimumValue() {
        return 0;
    }

    public int getMaximumValue() {
        return this.iDivisor - 1;
    }

    public long roundFloor(long j) {
        return getWrappedField().roundFloor(j);
    }

    public long roundCeiling(long j) {
        return getWrappedField().roundCeiling(j);
    }

    public long roundHalfFloor(long j) {
        return getWrappedField().roundHalfFloor(j);
    }

    public long roundHalfCeiling(long j) {
        return getWrappedField().roundHalfCeiling(j);
    }

    public long roundHalfEven(long j) {
        return getWrappedField().roundHalfEven(j);
    }

    public long remainder(long j) {
        return getWrappedField().remainder(j);
    }

    private int getDivided(int i) {
        if (i >= 0) {
            return i / this.iDivisor;
        }
        return ((i + 1) / this.iDivisor) - 1;
    }
}
