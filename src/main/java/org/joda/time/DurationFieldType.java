package org.joda.time;

import java.io.Serializable;

public abstract class DurationFieldType implements Serializable {
    static final DurationFieldType CENTURIES_TYPE = new StandardDurationFieldType("centuries", (byte) 2);
    static final DurationFieldType DAYS_TYPE = new StandardDurationFieldType("days", (byte) 7);
    static final DurationFieldType ERAS_TYPE = new StandardDurationFieldType("eras", (byte) 1);
    static final DurationFieldType HALFDAYS_TYPE = new StandardDurationFieldType("halfdays", (byte) 8);
    static final DurationFieldType HOURS_TYPE = new StandardDurationFieldType("hours", (byte) 9);
    static final DurationFieldType MILLIS_TYPE = new StandardDurationFieldType("millis", (byte) 12);
    static final DurationFieldType MINUTES_TYPE = new StandardDurationFieldType("minutes", (byte) 10);
    static final DurationFieldType MONTHS_TYPE = new StandardDurationFieldType("months", (byte) 5);
    static final DurationFieldType SECONDS_TYPE = new StandardDurationFieldType("seconds", (byte) 11);
    static final DurationFieldType WEEKS_TYPE = new StandardDurationFieldType("weeks", (byte) 6);
    static final DurationFieldType WEEKYEARS_TYPE = new StandardDurationFieldType("weekyears", (byte) 3);
    static final DurationFieldType YEARS_TYPE = new StandardDurationFieldType("years", (byte) 4);
    private final String iName;

    private static class StandardDurationFieldType extends DurationFieldType {
        private final byte iOrdinal;

        StandardDurationFieldType(String str, byte b) {
            super(str);
            this.iOrdinal = b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StandardDurationFieldType)) {
                return false;
            }
            if (this.iOrdinal != ((StandardDurationFieldType) obj).iOrdinal) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1 << this.iOrdinal;
        }

        public DurationField getField(Chronology chronology) {
            Chronology chronology2 = DateTimeUtils.getChronology(chronology);
            switch (this.iOrdinal) {
                case (byte) 1:
                    return chronology2.eras();
                case (byte) 2:
                    return chronology2.centuries();
                case (byte) 3:
                    return chronology2.weekyears();
                case (byte) 4:
                    return chronology2.years();
                case (byte) 5:
                    return chronology2.months();
                case (byte) 6:
                    return chronology2.weeks();
                case (byte) 7:
                    return chronology2.days();
                case (byte) 8:
                    return chronology2.halfdays();
                case (byte) 9:
                    return chronology2.hours();
                case (byte) 10:
                    return chronology2.minutes();
                case (byte) 11:
                    return chronology2.seconds();
                case (byte) 12:
                    return chronology2.millis();
                default:
                    throw new InternalError();
            }
        }
    }

    public abstract DurationField getField(Chronology chronology);

    protected DurationFieldType(String str) {
        this.iName = str;
    }

    public static DurationFieldType millis() {
        return MILLIS_TYPE;
    }

    public static DurationFieldType seconds() {
        return SECONDS_TYPE;
    }

    public static DurationFieldType minutes() {
        return MINUTES_TYPE;
    }

    public static DurationFieldType hours() {
        return HOURS_TYPE;
    }

    public static DurationFieldType halfdays() {
        return HALFDAYS_TYPE;
    }

    public static DurationFieldType days() {
        return DAYS_TYPE;
    }

    public static DurationFieldType weeks() {
        return WEEKS_TYPE;
    }

    public static DurationFieldType weekyears() {
        return WEEKYEARS_TYPE;
    }

    public static DurationFieldType months() {
        return MONTHS_TYPE;
    }

    public static DurationFieldType years() {
        return YEARS_TYPE;
    }

    public static DurationFieldType centuries() {
        return CENTURIES_TYPE;
    }

    public static DurationFieldType eras() {
        return ERAS_TYPE;
    }

    public String getName() {
        return this.iName;
    }

    public String toString() {
        return getName();
    }
}
