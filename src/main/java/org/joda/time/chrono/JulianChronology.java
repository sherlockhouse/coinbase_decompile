package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.chrono.AssembledChronology.Fields;
import org.joda.time.field.SkipDateTimeField;

public final class JulianChronology extends BasicGJChronology {
    private static final JulianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final Map<DateTimeZone, JulianChronology[]> cCache = new HashMap();

    static int adjustYearForSet(int i) {
        if (i > 0) {
            return i;
        }
        if (i != 0) {
            return i + 1;
        }
        throw new IllegalFieldValueException(DateTimeFieldType.year(), Integer.valueOf(i), null, null);
    }

    public static JulianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static JulianChronology getInstance(DateTimeZone dateTimeZone, int i) {
        JulianChronology julianChronology;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        synchronized (cCache) {
            JulianChronology[] julianChronologyArr = (JulianChronology[]) cCache.get(dateTimeZone);
            if (julianChronologyArr == null) {
                julianChronologyArr = new JulianChronology[7];
                cCache.put(dateTimeZone, julianChronologyArr);
            }
            JulianChronology[] julianChronologyArr2 = julianChronologyArr;
            try {
                julianChronology = julianChronologyArr2[i - 1];
                if (julianChronology == null) {
                    if (dateTimeZone == DateTimeZone.UTC) {
                        julianChronology = new JulianChronology(null, null, i);
                    } else {
                        julianChronology = new JulianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                    }
                    julianChronologyArr2[i - 1] = julianChronology;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid min days in first week: " + i);
            }
        }
        return julianChronology;
    }

    JulianChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone);
    }

    long getDateMidnightMillis(int i, int i2, int i3) throws IllegalArgumentException {
        return super.getDateMidnightMillis(adjustYearForSet(i), i2, i3);
    }

    boolean isLeapYear(int i) {
        return (i & 3) == 0;
    }

    long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1968;
        if (i3 <= 0) {
            i2 = (i3 + 3) >> 2;
        } else {
            i2 = i3 >> 2;
            if (!isLeapYear(i)) {
                i2++;
            }
        }
        return ((((long) i2) + (((long) i3) * 365)) * 86400000) - 62035200000L;
    }

    int getMinYear() {
        return -292269054;
    }

    int getMaxYear() {
        return 292272992;
    }

    long getAverageMillisPerYear() {
        return 31557600000L;
    }

    long getAverageMillisPerYearDividedByTwo() {
        return 15778800000L;
    }

    long getAverageMillisPerMonth() {
        return 2629800000L;
    }

    long getApproxMillisAtEpochDividedByTwo() {
        return 31083663600000L;
    }

    protected void assemble(Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
        }
    }
}
