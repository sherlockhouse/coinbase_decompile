package org.joda.time.chrono;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.field.ImpreciseDateTimeField;

final class BasicWeekyearDateTimeField extends ImpreciseDateTimeField {
    private final BasicChronology iChronology;

    BasicWeekyearDateTimeField(BasicChronology basicChronology) {
        super(DateTimeFieldType.weekyear(), basicChronology.getAverageMillisPerYear());
        this.iChronology = basicChronology;
    }

    public int get(long j) {
        return this.iChronology.getWeekyear(j);
    }

    public long add(long j, int i) {
        return i == 0 ? j : set(j, get(j) + i);
    }

    public long add(long j, long j2) {
        return add(j, FieldUtils.safeToInt(j2));
    }

    public long getDifferenceAsLong(long j, long j2) {
        if (j < j2) {
            return (long) (-getDifference(j2, j));
        }
        long j3;
        int i = get(j);
        int i2 = get(j2);
        long remainder = remainder(j);
        long remainder2 = remainder(j2);
        if (remainder2 < 31449600000L || this.iChronology.getWeeksInYear(i) > 52) {
            j3 = remainder2;
        } else {
            j3 = remainder2 - 604800000;
        }
        int i3 = i - i2;
        if (remainder < j3) {
            i3--;
        }
        return (long) i3;
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, Math.abs(i), this.iChronology.getMinYear(), this.iChronology.getMaxYear());
        int i2 = get(j);
        if (i2 == i) {
            return j;
        }
        int dayOfWeek = this.iChronology.getDayOfWeek(j);
        int weeksInYear = this.iChronology.getWeeksInYear(i2);
        i2 = this.iChronology.getWeeksInYear(i);
        if (i2 >= weeksInYear) {
            i2 = weeksInYear;
        }
        weeksInYear = this.iChronology.getWeekOfWeekyear(j);
        if (weeksInYear <= i2) {
            i2 = weeksInYear;
        }
        long year = this.iChronology.setYear(j, i);
        weeksInYear = get(year);
        if (weeksInYear < i) {
            year += 604800000;
        } else if (weeksInYear > i) {
            year -= 604800000;
        }
        return this.iChronology.dayOfWeek().set((((long) (i2 - this.iChronology.getWeekOfWeekyear(year))) * 604800000) + year, dayOfWeek);
    }

    public DurationField getRangeDurationField() {
        return null;
    }

    public boolean isLeap(long j) {
        return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(j)) > 52;
    }

    public DurationField getLeapDurationField() {
        return this.iChronology.weeks();
    }

    public int getMinimumValue() {
        return this.iChronology.getMinYear();
    }

    public int getMaximumValue() {
        return this.iChronology.getMaxYear();
    }

    public long roundFloor(long j) {
        long roundFloor = this.iChronology.weekOfWeekyear().roundFloor(j);
        int weekOfWeekyear = this.iChronology.getWeekOfWeekyear(roundFloor);
        if (weekOfWeekyear > 1) {
            return roundFloor - (((long) (weekOfWeekyear - 1)) * 604800000);
        }
        return roundFloor;
    }

    public long remainder(long j) {
        return j - roundFloor(j);
    }
}
