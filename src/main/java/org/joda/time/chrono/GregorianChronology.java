package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology.Fields;

public final class GregorianChronology extends BasicGJChronology {
    private static final GregorianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final Map<DateTimeZone, GregorianChronology[]> cCache = new HashMap();

    public static GregorianChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone, int i) {
        GregorianChronology gregorianChronology;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        synchronized (cCache) {
            GregorianChronology[] gregorianChronologyArr = (GregorianChronology[]) cCache.get(dateTimeZone);
            if (gregorianChronologyArr == null) {
                gregorianChronologyArr = new GregorianChronology[7];
                cCache.put(dateTimeZone, gregorianChronologyArr);
            }
            GregorianChronology[] gregorianChronologyArr2 = gregorianChronologyArr;
            try {
                gregorianChronology = gregorianChronologyArr2[i - 1];
                if (gregorianChronology == null) {
                    if (dateTimeZone == DateTimeZone.UTC) {
                        gregorianChronology = new GregorianChronology(null, null, i);
                    } else {
                        gregorianChronology = new GregorianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                    }
                    gregorianChronologyArr2[i - 1] = gregorianChronology;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid min days in first week: " + i);
            }
        }
        return gregorianChronology;
    }

    private GregorianChronology(Chronology chronology, Object obj, int i) {
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

    protected void assemble(Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
        }
    }

    boolean isLeapYear(int i) {
        return (i & 3) == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    long calculateFirstDayOfYearMillis(int i) {
        int i2 = i / 100;
        if (i < 0) {
            i2 = (((i2 + 3) >> 2) + (((i + 3) >> 2) - i2)) - 1;
        } else {
            i2 = (i2 >> 2) + ((i >> 2) - i2);
            if (isLeapYear(i)) {
                i2--;
            }
        }
        return (((long) (i2 - 719527)) + (((long) i) * 365)) * 86400000;
    }

    int getMinYear() {
        return -292275054;
    }

    int getMaxYear() {
        return 292278993;
    }

    long getAverageMillisPerYear() {
        return 31556952000L;
    }

    long getAverageMillisPerYearDividedByTwo() {
        return 15778476000L;
    }

    long getAverageMillisPerMonth() {
        return 2629746000L;
    }

    long getApproxMillisAtEpochDividedByTwo() {
        return 31083597720000L;
    }
}
