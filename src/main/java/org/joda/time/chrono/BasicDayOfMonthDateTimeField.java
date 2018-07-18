package org.joda.time.chrono;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicDayOfMonthDateTimeField extends PreciseDurationDateTimeField {
    private final BasicChronology iChronology;

    BasicDayOfMonthDateTimeField(BasicChronology basicChronology, DurationField durationField) {
        super(DateTimeFieldType.dayOfMonth(), durationField);
        this.iChronology = basicChronology;
    }

    public int get(long j) {
        return this.iChronology.getDayOfMonth(j);
    }

    public DurationField getRangeDurationField() {
        return this.iChronology.months();
    }

    public int getMinimumValue() {
        return 1;
    }

    public int getMaximumValue() {
        return this.iChronology.getDaysInMonthMax();
    }

    public int getMaximumValue(long j) {
        return this.iChronology.getDaysInMonthMax(j);
    }

    public int getMaximumValue(ReadablePartial readablePartial) {
        if (!readablePartial.isSupported(DateTimeFieldType.monthOfYear())) {
            return getMaximumValue();
        }
        int i = readablePartial.get(DateTimeFieldType.monthOfYear());
        if (!readablePartial.isSupported(DateTimeFieldType.year())) {
            return this.iChronology.getDaysInMonthMax(i);
        }
        return this.iChronology.getDaysInYearMonth(readablePartial.get(DateTimeFieldType.year()), i);
    }

    public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
        int i = 0;
        int size = readablePartial.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (readablePartial.getFieldType(i2) == DateTimeFieldType.monthOfYear()) {
                i2 = iArr[i2];
                while (i < size) {
                    if (readablePartial.getFieldType(i) == DateTimeFieldType.year()) {
                        return this.iChronology.getDaysInYearMonth(iArr[i], i2);
                    }
                    i++;
                }
                return this.iChronology.getDaysInMonthMax(i2);
            }
        }
        return getMaximumValue();
    }

    protected int getMaximumValueForSet(long j, int i) {
        return this.iChronology.getDaysInMonthMaxForSet(j, i);
    }
}
