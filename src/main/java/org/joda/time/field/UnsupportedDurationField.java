package org.joda.time.field;

import java.io.Serializable;
import java.util.HashMap;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public final class UnsupportedDurationField extends DurationField implements Serializable {
    private static HashMap<DurationFieldType, UnsupportedDurationField> cCache;
    private final DurationFieldType iType;

    public static synchronized UnsupportedDurationField getInstance(DurationFieldType durationFieldType) {
        UnsupportedDurationField unsupportedDurationField;
        synchronized (UnsupportedDurationField.class) {
            if (cCache == null) {
                cCache = new HashMap(7);
                unsupportedDurationField = null;
            } else {
                unsupportedDurationField = (UnsupportedDurationField) cCache.get(durationFieldType);
            }
            if (unsupportedDurationField == null) {
                unsupportedDurationField = new UnsupportedDurationField(durationFieldType);
                cCache.put(durationFieldType, unsupportedDurationField);
            }
        }
        return unsupportedDurationField;
    }

    private UnsupportedDurationField(DurationFieldType durationFieldType) {
        this.iType = durationFieldType;
    }

    public final DurationFieldType getType() {
        return this.iType;
    }

    public String getName() {
        return this.iType.getName();
    }

    public boolean isSupported() {
        return false;
    }

    public boolean isPrecise() {
        return true;
    }

    public long add(long j, int i) {
        throw unsupported();
    }

    public long add(long j, long j2) {
        throw unsupported();
    }

    public int getDifference(long j, long j2) {
        throw unsupported();
    }

    public long getDifferenceAsLong(long j, long j2) {
        throw unsupported();
    }

    public long getUnitMillis() {
        return 0;
    }

    public int compareTo(DurationField durationField) {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnsupportedDurationField)) {
            return false;
        }
        UnsupportedDurationField unsupportedDurationField = (UnsupportedDurationField) obj;
        if (unsupportedDurationField.getName() != null) {
            return unsupportedDurationField.getName().equals(getName());
        }
        if (getName() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public String toString() {
        return "UnsupportedDurationField[" + getName() + ']';
    }

    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException(this.iType + " field is unsupported");
    }
}
