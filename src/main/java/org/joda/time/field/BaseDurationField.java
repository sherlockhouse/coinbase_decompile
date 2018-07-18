package org.joda.time.field;

import java.io.Serializable;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public abstract class BaseDurationField extends DurationField implements Serializable {
    private final DurationFieldType iType;

    protected BaseDurationField(DurationFieldType durationFieldType) {
        if (durationFieldType == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
        this.iType = durationFieldType;
    }

    public final DurationFieldType getType() {
        return this.iType;
    }

    public final String getName() {
        return this.iType.getName();
    }

    public final boolean isSupported() {
        return true;
    }

    public int getDifference(long j, long j2) {
        return FieldUtils.safeToInt(getDifferenceAsLong(j, j2));
    }

    public int compareTo(DurationField durationField) {
        long unitMillis = durationField.getUnitMillis();
        long unitMillis2 = getUnitMillis();
        if (unitMillis2 == unitMillis) {
            return 0;
        }
        if (unitMillis2 < unitMillis) {
            return -1;
        }
        return 1;
    }

    public String toString() {
        return "DurationField[" + getName() + ']';
    }
}
