package org.joda.time.chrono;

import org.joda.time.Chronology;

abstract class BasicGJChronology extends BasicChronology {
    private static final int[] MAX_DAYS_PER_MONTH_ARRAY = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final long[] MAX_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];
    private static final int[] MIN_DAYS_PER_MONTH_ARRAY = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final long[] MIN_TOTAL_MILLIS_BY_MONTH_ARRAY = new long[12];

    static {
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < 11; i++) {
            j2 += ((long) MIN_DAYS_PER_MONTH_ARRAY[i]) * 86400000;
            MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[i + 1] = j2;
            j += ((long) MAX_DAYS_PER_MONTH_ARRAY[i]) * 86400000;
            MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[i + 1] = j;
        }
    }

    BasicGJChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    int getMonthOfYear(long j, int i) {
        int yearMillis = (int) ((j - getYearMillis(i)) >> 10);
        if (isLeapYear(i)) {
            if (yearMillis < 15356250) {
                if (yearMillis < 7678125) {
                    if (yearMillis < 2615625) {
                        return 1;
                    }
                    return yearMillis < 5062500 ? 2 : 3;
                } else if (yearMillis < 10209375) {
                    return 4;
                } else {
                    return yearMillis < 12825000 ? 5 : 6;
                }
            } else if (yearMillis < 23118750) {
                if (yearMillis < 17971875) {
                    return 7;
                }
                return yearMillis < 20587500 ? 8 : 9;
            } else if (yearMillis < 25734375) {
                return 10;
            } else {
                return yearMillis < 28265625 ? 11 : 12;
            }
        } else if (yearMillis < 15271875) {
            if (yearMillis < 7593750) {
                if (yearMillis >= 2615625) {
                    return yearMillis < 4978125 ? 2 : 3;
                } else {
                    return 1;
                }
            } else if (yearMillis < 10125000) {
                return 4;
            } else {
                return yearMillis < 12740625 ? 5 : 6;
            }
        } else if (yearMillis < 23034375) {
            if (yearMillis < 17887500) {
                return 7;
            }
            return yearMillis < 20503125 ? 8 : 9;
        } else if (yearMillis < 25650000) {
            return 10;
        } else {
            return yearMillis < 28181250 ? 11 : 12;
        }
    }

    int getDaysInYearMonth(int i, int i2) {
        if (isLeapYear(i)) {
            return MAX_DAYS_PER_MONTH_ARRAY[i2 - 1];
        }
        return MIN_DAYS_PER_MONTH_ARRAY[i2 - 1];
    }

    int getDaysInMonthMax(int i) {
        return MAX_DAYS_PER_MONTH_ARRAY[i - 1];
    }

    int getDaysInMonthMaxForSet(long j, int i) {
        return (i > 28 || i < 1) ? getDaysInMonthMax(j) : 28;
    }

    long getTotalMillisByYearMonth(int i, int i2) {
        if (isLeapYear(i)) {
            return MAX_TOTAL_MILLIS_BY_MONTH_ARRAY[i2 - 1];
        }
        return MIN_TOTAL_MILLIS_BY_MONTH_ARRAY[i2 - 1];
    }

    long getYearDifference(long j, long j2) {
        long j3;
        int i;
        int year = getYear(j);
        int year2 = getYear(j2);
        long yearMillis = j - getYearMillis(year);
        long yearMillis2 = j2 - getYearMillis(year2);
        if (yearMillis2 >= 5097600000L) {
            if (isLeapYear(year2)) {
                if (!isLeapYear(year)) {
                    j3 = yearMillis;
                    yearMillis = yearMillis2 - 86400000;
                    i = year - year2;
                    if (j3 < yearMillis) {
                        i--;
                    }
                    return (long) i;
                }
            } else if (yearMillis >= 5097600000L && isLeapYear(year)) {
                j3 = yearMillis - 86400000;
                yearMillis = yearMillis2;
                i = year - year2;
                if (j3 < yearMillis) {
                    i--;
                }
                return (long) i;
            }
        }
        j3 = yearMillis;
        yearMillis = yearMillis2;
        i = year - year2;
        if (j3 < yearMillis) {
            i--;
        }
        return (long) i;
    }

    long setYear(long j, int i) {
        int year = getYear(j);
        int dayOfYear = getDayOfYear(j, year);
        int millisOfDay = getMillisOfDay(j);
        if (dayOfYear > 59) {
            if (isLeapYear(year)) {
                if (!isLeapYear(i)) {
                    dayOfYear--;
                }
            } else if (isLeapYear(i)) {
                dayOfYear++;
            }
        }
        return getYearMonthDayMillis(i, 1, dayOfYear) + ((long) millisOfDay);
    }
}
