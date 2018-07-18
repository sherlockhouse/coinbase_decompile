package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public abstract class ImpreciseDateTimeField extends BaseDateTimeField {
    private final DurationField iDurationField;
    final long iUnitMillis;

    private final class LinkedDurationField extends BaseDurationField {
        LinkedDurationField(DurationFieldType durationFieldType) {
            super(durationFieldType);
        }

        public boolean isPrecise() {
            return false;
        }

        public long getUnitMillis() {
            return ImpreciseDateTimeField.this.iUnitMillis;
        }

        public long add(long j, int i) {
            return ImpreciseDateTimeField.this.add(j, i);
        }

        public long add(long j, long j2) {
            return ImpreciseDateTimeField.this.add(j, j2);
        }

        public int getDifference(long j, long j2) {
            return ImpreciseDateTimeField.this.getDifference(j, j2);
        }

        public long getDifferenceAsLong(long j, long j2) {
            return ImpreciseDateTimeField.this.getDifferenceAsLong(j, j2);
        }
    }

    public abstract long add(long j, int i);

    public abstract long add(long j, long j2);

    public ImpreciseDateTimeField(DateTimeFieldType dateTimeFieldType, long j) {
        super(dateTimeFieldType);
        this.iUnitMillis = j;
        this.iDurationField = new LinkedDurationField(dateTimeFieldType.getDurationType());
    }

    public int getDifference(long j, long j2) {
        return FieldUtils.safeToInt(getDifferenceAsLong(j, j2));
    }

    public long getDifferenceAsLong(long j, long j2) {
        if (j < j2) {
            return -getDifferenceAsLong(j2, j);
        }
        long j3 = (j - j2) / this.iUnitMillis;
        if (add(j2, j3) < j) {
            do {
                j3++;
            } while (add(j2, j3) <= j);
            return j3 - 1;
        } else if (add(j2, j3) <= j) {
            return j3;
        } else {
            do {
                j3--;
            } while (add(j2, j3) > j);
            return j3;
        }
    }

    public final DurationField getDurationField() {
        return this.iDurationField;
    }
}
