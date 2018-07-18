package org.joda.time;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PeriodType implements Serializable {
    static int DAY_INDEX = 3;
    static int HOUR_INDEX = 4;
    static int MILLI_INDEX = 7;
    static int MINUTE_INDEX = 5;
    static int MONTH_INDEX = 1;
    static int SECOND_INDEX = 6;
    static int WEEK_INDEX = 2;
    static int YEAR_INDEX = 0;
    private static PeriodType cDays;
    private static PeriodType cHours;
    private static PeriodType cMinutes;
    private static PeriodType cMonths;
    private static PeriodType cSeconds;
    private static PeriodType cStandard;
    private static PeriodType cTime;
    private static final Map<PeriodType, Object> cTypes = new HashMap(32);
    private static PeriodType cWeeks;
    private static PeriodType cYears;
    private final int[] iIndices;
    private final String iName;
    private final DurationFieldType[] iTypes;

    public static PeriodType standard() {
        PeriodType periodType = cStandard;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Standard", new DurationFieldType[]{DurationFieldType.years(), DurationFieldType.months(), DurationFieldType.weeks(), DurationFieldType.days(), DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        cStandard = periodType;
        return periodType;
    }

    public static PeriodType time() {
        PeriodType periodType = cTime;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Time", new DurationFieldType[]{DurationFieldType.hours(), DurationFieldType.minutes(), DurationFieldType.seconds(), DurationFieldType.millis()}, new int[]{-1, -1, -1, -1, 0, 1, 2, 3});
        cTime = periodType;
        return periodType;
    }

    public static PeriodType years() {
        PeriodType periodType = cYears;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Years", new DurationFieldType[]{DurationFieldType.years()}, new int[]{0, -1, -1, -1, -1, -1, -1, -1});
        cYears = periodType;
        return periodType;
    }

    public static PeriodType months() {
        PeriodType periodType = cMonths;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Months", new DurationFieldType[]{DurationFieldType.months()}, new int[]{-1, 0, -1, -1, -1, -1, -1, -1});
        cMonths = periodType;
        return periodType;
    }

    public static PeriodType weeks() {
        PeriodType periodType = cWeeks;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Weeks", new DurationFieldType[]{DurationFieldType.weeks()}, new int[]{-1, -1, 0, -1, -1, -1, -1, -1});
        cWeeks = periodType;
        return periodType;
    }

    public static PeriodType days() {
        PeriodType periodType = cDays;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Days", new DurationFieldType[]{DurationFieldType.days()}, new int[]{-1, -1, -1, 0, -1, -1, -1, -1});
        cDays = periodType;
        return periodType;
    }

    public static PeriodType hours() {
        PeriodType periodType = cHours;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Hours", new DurationFieldType[]{DurationFieldType.hours()}, new int[]{-1, -1, -1, -1, 0, -1, -1, -1});
        cHours = periodType;
        return periodType;
    }

    public static PeriodType minutes() {
        PeriodType periodType = cMinutes;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Minutes", new DurationFieldType[]{DurationFieldType.minutes()}, new int[]{-1, -1, -1, -1, -1, 0, -1, -1});
        cMinutes = periodType;
        return periodType;
    }

    public static PeriodType seconds() {
        PeriodType periodType = cSeconds;
        if (periodType != null) {
            return periodType;
        }
        periodType = new PeriodType("Seconds", new DurationFieldType[]{DurationFieldType.seconds()}, new int[]{-1, -1, -1, -1, -1, -1, 0, -1});
        cSeconds = periodType;
        return periodType;
    }

    protected PeriodType(String str, DurationFieldType[] durationFieldTypeArr, int[] iArr) {
        this.iName = str;
        this.iTypes = durationFieldTypeArr;
        this.iIndices = iArr;
    }

    public String getName() {
        return this.iName;
    }

    public int size() {
        return this.iTypes.length;
    }

    public DurationFieldType getFieldType(int i) {
        return this.iTypes[i];
    }

    public boolean isSupported(DurationFieldType durationFieldType) {
        return indexOf(durationFieldType) >= 0;
    }

    public int indexOf(DurationFieldType durationFieldType) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.iTypes[i] == durationFieldType) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return "PeriodType[" + getName() + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PeriodType)) {
            return false;
        }
        return Arrays.equals(this.iTypes, ((PeriodType) obj).iTypes);
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (i < this.iTypes.length) {
            i2 += this.iTypes[i].hashCode();
            i++;
        }
        return i2;
    }
}
