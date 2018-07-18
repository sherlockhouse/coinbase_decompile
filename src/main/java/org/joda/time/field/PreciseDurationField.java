package org.joda.time.field;

import org.joda.time.DurationFieldType;

public class PreciseDurationField extends BaseDurationField {
    private final long iUnitMillis;

    public PreciseDurationField(DurationFieldType durationFieldType, long j) {
        super(durationFieldType);
        this.iUnitMillis = j;
    }

    public final boolean isPrecise() {
        return true;
    }

    public final long getUnitMillis() {
        return this.iUnitMillis;
    }

    public long add(long j, int i) {
        return FieldUtils.safeAdd(j, ((long) i) * this.iUnitMillis);
    }

    public long add(long j, long j2) {
        return FieldUtils.safeAdd(j, FieldUtils.safeMultiply(j2, this.iUnitMillis));
    }

    public long getDifferenceAsLong(long j, long j2) {
        return FieldUtils.safeSubtract(j, j2) / this.iUnitMillis;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PreciseDurationField)) {
            return false;
        }
        PreciseDurationField preciseDurationField = (PreciseDurationField) obj;
        if (getType() == preciseDurationField.getType() && this.iUnitMillis == preciseDurationField.iUnitMillis) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.iUnitMillis;
        return ((int) (j ^ (j >>> 32))) + getType().hashCode();
    }
}
