package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class ScaledDurationField extends DecoratedDurationField {
    private final int iScalar;

    public ScaledDurationField(DurationField durationField, DurationFieldType durationFieldType, int i) {
        super(durationField, durationFieldType);
        if (i == 0 || i == 1) {
            throw new IllegalArgumentException("The scalar must not be 0 or 1");
        }
        this.iScalar = i;
    }

    public long add(long j, int i) {
        return getWrappedField().add(j, ((long) i) * ((long) this.iScalar));
    }

    public long add(long j, long j2) {
        return getWrappedField().add(j, FieldUtils.safeMultiply(j2, this.iScalar));
    }

    public int getDifference(long j, long j2) {
        return getWrappedField().getDifference(j, j2) / this.iScalar;
    }

    public long getDifferenceAsLong(long j, long j2) {
        return getWrappedField().getDifferenceAsLong(j, j2) / ((long) this.iScalar);
    }

    public long getUnitMillis() {
        return getWrappedField().getUnitMillis() * ((long) this.iScalar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScaledDurationField)) {
            return false;
        }
        ScaledDurationField scaledDurationField = (ScaledDurationField) obj;
        if (getWrappedField().equals(scaledDurationField.getWrappedField()) && getType() == scaledDurationField.getType() && this.iScalar == scaledDurationField.iScalar) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = (long) this.iScalar;
        return (((int) (j ^ (j >>> 32))) + getType().hashCode()) + getWrappedField().hashCode();
    }
}
