package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PeriodConverter;

public abstract class BasePeriod extends AbstractPeriod implements Serializable, ReadablePeriod {
    private static final ReadablePeriod DUMMY_PERIOD = new AbstractPeriod() {
        public int getValue(int i) {
            return 0;
        }

        public PeriodType getPeriodType() {
            return PeriodType.time();
        }
    };
    private final PeriodType iType;
    private final int[] iValues;

    protected BasePeriod(long j) {
        this.iType = PeriodType.standard();
        Object obj = ISOChronology.getInstanceUTC().get(DUMMY_PERIOD, j);
        this.iValues = new int[8];
        System.arraycopy(obj, 0, this.iValues, 4, 4);
    }

    protected BasePeriod(long j, PeriodType periodType, Chronology chronology) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iType = checkPeriodType;
        this.iValues = chronology2.get((ReadablePeriod) this, j);
    }

    protected BasePeriod(Object obj, PeriodType periodType, Chronology chronology) {
        PeriodConverter periodConverter = ConverterManager.getInstance().getPeriodConverter(obj);
        if (periodType == null) {
            periodType = periodConverter.getPeriodType(obj);
        }
        PeriodType checkPeriodType = checkPeriodType(periodType);
        this.iType = checkPeriodType;
        if (this instanceof ReadWritablePeriod) {
            this.iValues = new int[size()];
            periodConverter.setInto((ReadWritablePeriod) this, obj, DateTimeUtils.getChronology(chronology));
            return;
        }
        this.iValues = new MutablePeriod(obj, checkPeriodType, chronology).getValues();
    }

    protected PeriodType checkPeriodType(PeriodType periodType) {
        return DateTimeUtils.getPeriodType(periodType);
    }

    public PeriodType getPeriodType() {
        return this.iType;
    }

    public int getValue(int i) {
        return this.iValues[i];
    }

    private void checkAndUpdate(DurationFieldType durationFieldType, int[] iArr, int i) {
        int indexOf = indexOf(durationFieldType);
        if (indexOf != -1) {
            iArr[indexOf] = i;
        } else if (i != 0) {
            throw new IllegalArgumentException("Period does not support field '" + durationFieldType.getName() + "'");
        }
    }

    protected void setPeriod(ReadablePeriod readablePeriod) {
        if (readablePeriod == null) {
            setValues(new int[size()]);
        } else {
            setPeriodInternal(readablePeriod);
        }
    }

    private void setPeriodInternal(ReadablePeriod readablePeriod) {
        int[] iArr = new int[size()];
        int size = readablePeriod.size();
        for (int i = 0; i < size; i++) {
            checkAndUpdate(readablePeriod.getFieldType(i), iArr, readablePeriod.getValue(i));
        }
        setValues(iArr);
    }

    protected void setField(DurationFieldType durationFieldType, int i) {
        setFieldInto(this.iValues, durationFieldType, i);
    }

    protected void setFieldInto(int[] iArr, DurationFieldType durationFieldType, int i) {
        int indexOf = indexOf(durationFieldType);
        if (indexOf != -1) {
            iArr[indexOf] = i;
        } else if (i != 0 || durationFieldType == null) {
            throw new IllegalArgumentException("Period does not support field '" + durationFieldType + "'");
        }
    }

    protected void setValue(int i, int i2) {
        this.iValues[i] = i2;
    }

    protected void setValues(int[] iArr) {
        System.arraycopy(iArr, 0, this.iValues, 0, this.iValues.length);
    }
}
