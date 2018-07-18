package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicWeekOfWeekyearDateTimeField extends PreciseDurationDateTimeField {
    private final BasicChronology iChronology;

    BasicWeekOfWeekyearDateTimeField(BasicChronology basicChronology, DurationField durationField) {
        super(DateTimeFieldType.weekOfWeekyear(), durationField);
        this.iChronology = basicChronology;
    }

    public int get(long j) {
        return this.iChronology.getWeekOfWeekyear(j);
    }

    public DurationField getRangeDurationField() {
        return this.iChronology.weekyears();
    }

    public long roundFloor(long j) {
        return super.roundFloor(j + 259200000) - 259200000;
    }

    public long roundCeiling(long j) {
        return super.roundCeiling(j + 259200000) - 259200000;
    }

    public long remainder(long j) {
        return super.remainder(259200000 + j);
    }

    public int getMinimumValue() {
        return 1;
    }

    public int getMaximumValue() {
        return 53;
    }

    public int getMaximumValue(long j) {
        return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(j));
    }

    public int getMaximumValue(ReadablePartial readablePartial) {
        if (!readablePartial.isSupported(DateTimeFieldType.weekyear())) {
            return 53;
        }
        return this.iChronology.getWeeksInYear(readablePartial.get(DateTimeFieldType.weekyear()));
    }

    public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
        int size = readablePartial.size();
        for (int i = 0; i < size; i++) {
            if (readablePartial.getFieldType(i) == DateTimeFieldType.weekyear()) {
                return this.iChronology.getWeeksInYear(iArr[i]);
            }
        }
        return 53;
    }

    protected int getMaximumValueForSet(long j, int i) {
        return i > 52 ? getMaximumValue(j) : 52;
    }
}
