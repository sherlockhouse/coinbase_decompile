package org.joda.time;

import java.io.Serializable;

public abstract class DateTimeFieldType implements Serializable {
    private static final DateTimeFieldType CENTURY_OF_ERA_TYPE = new StandardDateTimeFieldType("centuryOfEra", (byte) 3, DurationFieldType.centuries(), DurationFieldType.eras());
    private static final DateTimeFieldType CLOCKHOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("clockhourOfDay", (byte) 16, DurationFieldType.hours(), DurationFieldType.days());
    private static final DateTimeFieldType CLOCKHOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("clockhourOfHalfday", (byte) 15, DurationFieldType.hours(), DurationFieldType.halfdays());
    private static final DateTimeFieldType DAY_OF_MONTH_TYPE = new StandardDateTimeFieldType("dayOfMonth", (byte) 8, DurationFieldType.days(), DurationFieldType.months());
    private static final DateTimeFieldType DAY_OF_WEEK_TYPE = new StandardDateTimeFieldType("dayOfWeek", (byte) 12, DurationFieldType.days(), DurationFieldType.weeks());
    private static final DateTimeFieldType DAY_OF_YEAR_TYPE = new StandardDateTimeFieldType("dayOfYear", (byte) 6, DurationFieldType.days(), DurationFieldType.years());
    private static final DateTimeFieldType ERA_TYPE = new StandardDateTimeFieldType("era", (byte) 1, DurationFieldType.eras(), null);
    private static final DateTimeFieldType HALFDAY_OF_DAY_TYPE = new StandardDateTimeFieldType("halfdayOfDay", (byte) 13, DurationFieldType.halfdays(), DurationFieldType.days());
    private static final DateTimeFieldType HOUR_OF_DAY_TYPE = new StandardDateTimeFieldType("hourOfDay", (byte) 17, DurationFieldType.hours(), DurationFieldType.days());
    private static final DateTimeFieldType HOUR_OF_HALFDAY_TYPE = new StandardDateTimeFieldType("hourOfHalfday", (byte) 14, DurationFieldType.hours(), DurationFieldType.halfdays());
    private static final DateTimeFieldType MILLIS_OF_DAY_TYPE = new StandardDateTimeFieldType("millisOfDay", (byte) 22, DurationFieldType.millis(), DurationFieldType.days());
    private static final DateTimeFieldType MILLIS_OF_SECOND_TYPE = new StandardDateTimeFieldType("millisOfSecond", (byte) 23, DurationFieldType.millis(), DurationFieldType.seconds());
    private static final DateTimeFieldType MINUTE_OF_DAY_TYPE = new StandardDateTimeFieldType("minuteOfDay", (byte) 18, DurationFieldType.minutes(), DurationFieldType.days());
    private static final DateTimeFieldType MINUTE_OF_HOUR_TYPE = new StandardDateTimeFieldType("minuteOfHour", (byte) 19, DurationFieldType.minutes(), DurationFieldType.hours());
    private static final DateTimeFieldType MONTH_OF_YEAR_TYPE = new StandardDateTimeFieldType("monthOfYear", (byte) 7, DurationFieldType.months(), DurationFieldType.years());
    private static final DateTimeFieldType SECOND_OF_DAY_TYPE = new StandardDateTimeFieldType("secondOfDay", (byte) 20, DurationFieldType.seconds(), DurationFieldType.days());
    private static final DateTimeFieldType SECOND_OF_MINUTE_TYPE = new StandardDateTimeFieldType("secondOfMinute", (byte) 21, DurationFieldType.seconds(), DurationFieldType.minutes());
    private static final DateTimeFieldType WEEKYEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("weekyearOfCentury", (byte) 9, DurationFieldType.weekyears(), DurationFieldType.centuries());
    private static final DateTimeFieldType WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekyear", (byte) 10, DurationFieldType.weekyears(), null);
    private static final DateTimeFieldType WEEK_OF_WEEKYEAR_TYPE = new StandardDateTimeFieldType("weekOfWeekyear", (byte) 11, DurationFieldType.weeks(), DurationFieldType.weekyears());
    private static final DateTimeFieldType YEAR_OF_CENTURY_TYPE = new StandardDateTimeFieldType("yearOfCentury", (byte) 4, DurationFieldType.years(), DurationFieldType.centuries());
    private static final DateTimeFieldType YEAR_OF_ERA_TYPE = new StandardDateTimeFieldType("yearOfEra", (byte) 2, DurationFieldType.years(), DurationFieldType.eras());
    private static final DateTimeFieldType YEAR_TYPE = new StandardDateTimeFieldType("year", (byte) 5, DurationFieldType.years(), null);
    private final String iName;

    private static class StandardDateTimeFieldType extends DateTimeFieldType {
        private final byte iOrdinal;
        private final transient DurationFieldType iRangeType;
        private final transient DurationFieldType iUnitType;

        StandardDateTimeFieldType(String str, byte b, DurationFieldType durationFieldType, DurationFieldType durationFieldType2) {
            super(str);
            this.iOrdinal = b;
            this.iUnitType = durationFieldType;
            this.iRangeType = durationFieldType2;
        }

        public DurationFieldType getDurationType() {
            return this.iUnitType;
        }

        public DurationFieldType getRangeDurationType() {
            return this.iRangeType;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StandardDateTimeFieldType)) {
                return false;
            }
            if (this.iOrdinal != ((StandardDateTimeFieldType) obj).iOrdinal) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1 << this.iOrdinal;
        }

        public DateTimeField getField(Chronology chronology) {
            Chronology chronology2 = DateTimeUtils.getChronology(chronology);
            switch (this.iOrdinal) {
                case (byte) 1:
                    return chronology2.era();
                case (byte) 2:
                    return chronology2.yearOfEra();
                case (byte) 3:
                    return chronology2.centuryOfEra();
                case (byte) 4:
                    return chronology2.yearOfCentury();
                case (byte) 5:
                    return chronology2.year();
                case (byte) 6:
                    return chronology2.dayOfYear();
                case (byte) 7:
                    return chronology2.monthOfYear();
                case (byte) 8:
                    return chronology2.dayOfMonth();
                case (byte) 9:
                    return chronology2.weekyearOfCentury();
                case (byte) 10:
                    return chronology2.weekyear();
                case (byte) 11:
                    return chronology2.weekOfWeekyear();
                case (byte) 12:
                    return chronology2.dayOfWeek();
                case (byte) 13:
                    return chronology2.halfdayOfDay();
                case (byte) 14:
                    return chronology2.hourOfHalfday();
                case (byte) 15:
                    return chronology2.clockhourOfHalfday();
                case (byte) 16:
                    return chronology2.clockhourOfDay();
                case (byte) 17:
                    return chronology2.hourOfDay();
                case (byte) 18:
                    return chronology2.minuteOfDay();
                case (byte) 19:
                    return chronology2.minuteOfHour();
                case (byte) 20:
                    return chronology2.secondOfDay();
                case (byte) 21:
                    return chronology2.secondOfMinute();
                case (byte) 22:
                    return chronology2.millisOfDay();
                case (byte) 23:
                    return chronology2.millisOfSecond();
                default:
                    throw new InternalError();
            }
        }
    }

    public abstract DurationFieldType getDurationType();

    public abstract DateTimeField getField(Chronology chronology);

    public abstract DurationFieldType getRangeDurationType();

    protected DateTimeFieldType(String str) {
        this.iName = str;
    }

    public static DateTimeFieldType millisOfSecond() {
        return MILLIS_OF_SECOND_TYPE;
    }

    public static DateTimeFieldType millisOfDay() {
        return MILLIS_OF_DAY_TYPE;
    }

    public static DateTimeFieldType secondOfMinute() {
        return SECOND_OF_MINUTE_TYPE;
    }

    public static DateTimeFieldType secondOfDay() {
        return SECOND_OF_DAY_TYPE;
    }

    public static DateTimeFieldType minuteOfHour() {
        return MINUTE_OF_HOUR_TYPE;
    }

    public static DateTimeFieldType minuteOfDay() {
        return MINUTE_OF_DAY_TYPE;
    }

    public static DateTimeFieldType hourOfDay() {
        return HOUR_OF_DAY_TYPE;
    }

    public static DateTimeFieldType clockhourOfDay() {
        return CLOCKHOUR_OF_DAY_TYPE;
    }

    public static DateTimeFieldType hourOfHalfday() {
        return HOUR_OF_HALFDAY_TYPE;
    }

    public static DateTimeFieldType clockhourOfHalfday() {
        return CLOCKHOUR_OF_HALFDAY_TYPE;
    }

    public static DateTimeFieldType halfdayOfDay() {
        return HALFDAY_OF_DAY_TYPE;
    }

    public static DateTimeFieldType dayOfWeek() {
        return DAY_OF_WEEK_TYPE;
    }

    public static DateTimeFieldType dayOfMonth() {
        return DAY_OF_MONTH_TYPE;
    }

    public static DateTimeFieldType dayOfYear() {
        return DAY_OF_YEAR_TYPE;
    }

    public static DateTimeFieldType weekOfWeekyear() {
        return WEEK_OF_WEEKYEAR_TYPE;
    }

    public static DateTimeFieldType weekyear() {
        return WEEKYEAR_TYPE;
    }

    public static DateTimeFieldType weekyearOfCentury() {
        return WEEKYEAR_OF_CENTURY_TYPE;
    }

    public static DateTimeFieldType monthOfYear() {
        return MONTH_OF_YEAR_TYPE;
    }

    public static DateTimeFieldType year() {
        return YEAR_TYPE;
    }

    public static DateTimeFieldType yearOfEra() {
        return YEAR_OF_ERA_TYPE;
    }

    public static DateTimeFieldType yearOfCentury() {
        return YEAR_OF_CENTURY_TYPE;
    }

    public static DateTimeFieldType centuryOfEra() {
        return CENTURY_OF_ERA_TYPE;
    }

    public static DateTimeFieldType era() {
        return ERA_TYPE;
    }

    public String getName() {
        return this.iName;
    }

    public String toString() {
        return getName();
    }
}
