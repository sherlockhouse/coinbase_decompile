package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BasePeriod;

public class MutablePeriod extends BasePeriod implements Serializable, Cloneable, ReadWritablePeriod {
    public MutablePeriod() {
        super(0, null, null);
    }

    public MutablePeriod(long j, PeriodType periodType) {
        super(j, periodType, null);
    }

    public MutablePeriod(Object obj, PeriodType periodType, Chronology chronology) {
        super(obj, periodType, chronology);
    }

    public void clear() {
        super.setValues(new int[size()]);
    }

    public void setValue(int i, int i2) {
        super.setValue(i, i2);
    }

    public void setPeriod(ReadablePeriod readablePeriod) {
        super.setPeriod(readablePeriod);
    }

    public void setYears(int i) {
        super.setField(DurationFieldType.years(), i);
    }

    public void setMonths(int i) {
        super.setField(DurationFieldType.months(), i);
    }

    public void setWeeks(int i) {
        super.setField(DurationFieldType.weeks(), i);
    }

    public void setDays(int i) {
        super.setField(DurationFieldType.days(), i);
    }

    public void setHours(int i) {
        super.setField(DurationFieldType.hours(), i);
    }

    public void setMinutes(int i) {
        super.setField(DurationFieldType.minutes(), i);
    }

    public void setSeconds(int i) {
        super.setField(DurationFieldType.seconds(), i);
    }

    public void setMillis(int i) {
        super.setField(DurationFieldType.millis(), i);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError("Clone error");
        }
    }
}
