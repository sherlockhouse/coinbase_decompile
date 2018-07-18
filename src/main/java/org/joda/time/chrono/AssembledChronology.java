package org.joda.time.chrono;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;

public abstract class AssembledChronology extends BaseChronology {
    private final Chronology iBase;
    private transient int iBaseFlags;
    private transient DurationField iCenturies;
    private transient DateTimeField iCenturyOfEra;
    private transient DateTimeField iClockhourOfDay;
    private transient DateTimeField iClockhourOfHalfday;
    private transient DateTimeField iDayOfMonth;
    private transient DateTimeField iDayOfWeek;
    private transient DateTimeField iDayOfYear;
    private transient DurationField iDays;
    private transient DateTimeField iEra;
    private transient DurationField iEras;
    private transient DateTimeField iHalfdayOfDay;
    private transient DurationField iHalfdays;
    private transient DateTimeField iHourOfDay;
    private transient DateTimeField iHourOfHalfday;
    private transient DurationField iHours;
    private transient DurationField iMillis;
    private transient DateTimeField iMillisOfDay;
    private transient DateTimeField iMillisOfSecond;
    private transient DateTimeField iMinuteOfDay;
    private transient DateTimeField iMinuteOfHour;
    private transient DurationField iMinutes;
    private transient DateTimeField iMonthOfYear;
    private transient DurationField iMonths;
    private final Object iParam;
    private transient DateTimeField iSecondOfDay;
    private transient DateTimeField iSecondOfMinute;
    private transient DurationField iSeconds;
    private transient DateTimeField iWeekOfWeekyear;
    private transient DurationField iWeeks;
    private transient DateTimeField iWeekyear;
    private transient DateTimeField iWeekyearOfCentury;
    private transient DurationField iWeekyears;
    private transient DateTimeField iYear;
    private transient DateTimeField iYearOfCentury;
    private transient DateTimeField iYearOfEra;
    private transient DurationField iYears;

    public static final class Fields {
        public DurationField centuries;
        public DateTimeField centuryOfEra;
        public DateTimeField clockhourOfDay;
        public DateTimeField clockhourOfHalfday;
        public DateTimeField dayOfMonth;
        public DateTimeField dayOfWeek;
        public DateTimeField dayOfYear;
        public DurationField days;
        public DateTimeField era;
        public DurationField eras;
        public DateTimeField halfdayOfDay;
        public DurationField halfdays;
        public DateTimeField hourOfDay;
        public DateTimeField hourOfHalfday;
        public DurationField hours;
        public DurationField millis;
        public DateTimeField millisOfDay;
        public DateTimeField millisOfSecond;
        public DateTimeField minuteOfDay;
        public DateTimeField minuteOfHour;
        public DurationField minutes;
        public DateTimeField monthOfYear;
        public DurationField months;
        public DateTimeField secondOfDay;
        public DateTimeField secondOfMinute;
        public DurationField seconds;
        public DateTimeField weekOfWeekyear;
        public DurationField weeks;
        public DateTimeField weekyear;
        public DateTimeField weekyearOfCentury;
        public DurationField weekyears;
        public DateTimeField year;
        public DateTimeField yearOfCentury;
        public DateTimeField yearOfEra;
        public DurationField years;

        Fields() {
        }

        public void copyFieldsFrom(Chronology chronology) {
            DurationField millis = chronology.millis();
            if (isSupported(millis)) {
                this.millis = millis;
            }
            millis = chronology.seconds();
            if (isSupported(millis)) {
                this.seconds = millis;
            }
            millis = chronology.minutes();
            if (isSupported(millis)) {
                this.minutes = millis;
            }
            millis = chronology.hours();
            if (isSupported(millis)) {
                this.hours = millis;
            }
            millis = chronology.halfdays();
            if (isSupported(millis)) {
                this.halfdays = millis;
            }
            millis = chronology.days();
            if (isSupported(millis)) {
                this.days = millis;
            }
            millis = chronology.weeks();
            if (isSupported(millis)) {
                this.weeks = millis;
            }
            millis = chronology.weekyears();
            if (isSupported(millis)) {
                this.weekyears = millis;
            }
            millis = chronology.months();
            if (isSupported(millis)) {
                this.months = millis;
            }
            millis = chronology.years();
            if (isSupported(millis)) {
                this.years = millis;
            }
            millis = chronology.centuries();
            if (isSupported(millis)) {
                this.centuries = millis;
            }
            millis = chronology.eras();
            if (isSupported(millis)) {
                this.eras = millis;
            }
            DateTimeField millisOfSecond = chronology.millisOfSecond();
            if (isSupported(millisOfSecond)) {
                this.millisOfSecond = millisOfSecond;
            }
            millisOfSecond = chronology.millisOfDay();
            if (isSupported(millisOfSecond)) {
                this.millisOfDay = millisOfSecond;
            }
            millisOfSecond = chronology.secondOfMinute();
            if (isSupported(millisOfSecond)) {
                this.secondOfMinute = millisOfSecond;
            }
            millisOfSecond = chronology.secondOfDay();
            if (isSupported(millisOfSecond)) {
                this.secondOfDay = millisOfSecond;
            }
            millisOfSecond = chronology.minuteOfHour();
            if (isSupported(millisOfSecond)) {
                this.minuteOfHour = millisOfSecond;
            }
            millisOfSecond = chronology.minuteOfDay();
            if (isSupported(millisOfSecond)) {
                this.minuteOfDay = millisOfSecond;
            }
            millisOfSecond = chronology.hourOfDay();
            if (isSupported(millisOfSecond)) {
                this.hourOfDay = millisOfSecond;
            }
            millisOfSecond = chronology.clockhourOfDay();
            if (isSupported(millisOfSecond)) {
                this.clockhourOfDay = millisOfSecond;
            }
            millisOfSecond = chronology.hourOfHalfday();
            if (isSupported(millisOfSecond)) {
                this.hourOfHalfday = millisOfSecond;
            }
            millisOfSecond = chronology.clockhourOfHalfday();
            if (isSupported(millisOfSecond)) {
                this.clockhourOfHalfday = millisOfSecond;
            }
            millisOfSecond = chronology.halfdayOfDay();
            if (isSupported(millisOfSecond)) {
                this.halfdayOfDay = millisOfSecond;
            }
            millisOfSecond = chronology.dayOfWeek();
            if (isSupported(millisOfSecond)) {
                this.dayOfWeek = millisOfSecond;
            }
            millisOfSecond = chronology.dayOfMonth();
            if (isSupported(millisOfSecond)) {
                this.dayOfMonth = millisOfSecond;
            }
            millisOfSecond = chronology.dayOfYear();
            if (isSupported(millisOfSecond)) {
                this.dayOfYear = millisOfSecond;
            }
            millisOfSecond = chronology.weekOfWeekyear();
            if (isSupported(millisOfSecond)) {
                this.weekOfWeekyear = millisOfSecond;
            }
            millisOfSecond = chronology.weekyear();
            if (isSupported(millisOfSecond)) {
                this.weekyear = millisOfSecond;
            }
            millisOfSecond = chronology.weekyearOfCentury();
            if (isSupported(millisOfSecond)) {
                this.weekyearOfCentury = millisOfSecond;
            }
            millisOfSecond = chronology.monthOfYear();
            if (isSupported(millisOfSecond)) {
                this.monthOfYear = millisOfSecond;
            }
            millisOfSecond = chronology.year();
            if (isSupported(millisOfSecond)) {
                this.year = millisOfSecond;
            }
            millisOfSecond = chronology.yearOfEra();
            if (isSupported(millisOfSecond)) {
                this.yearOfEra = millisOfSecond;
            }
            millisOfSecond = chronology.yearOfCentury();
            if (isSupported(millisOfSecond)) {
                this.yearOfCentury = millisOfSecond;
            }
            millisOfSecond = chronology.centuryOfEra();
            if (isSupported(millisOfSecond)) {
                this.centuryOfEra = millisOfSecond;
            }
            millisOfSecond = chronology.era();
            if (isSupported(millisOfSecond)) {
                this.era = millisOfSecond;
            }
        }

        private static boolean isSupported(DurationField durationField) {
            return durationField == null ? false : durationField.isSupported();
        }

        private static boolean isSupported(DateTimeField dateTimeField) {
            return dateTimeField == null ? false : dateTimeField.isSupported();
        }
    }

    protected abstract void assemble(Fields fields);

    protected AssembledChronology(Chronology chronology, Object obj) {
        this.iBase = chronology;
        this.iParam = obj;
        setFields();
    }

    public DateTimeZone getZone() {
        Chronology chronology = this.iBase;
        if (chronology != null) {
            return chronology.getZone();
        }
        return null;
    }

    public long getDateTimeMillis(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        Chronology chronology = this.iBase;
        if (chronology == null || (this.iBaseFlags & 6) != 6) {
            return super.getDateTimeMillis(i, i2, i3, i4);
        }
        return chronology.getDateTimeMillis(i, i2, i3, i4);
    }

    public long getDateTimeMillis(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        Chronology chronology = this.iBase;
        if (chronology == null || (this.iBaseFlags & 5) != 5) {
            return super.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
        }
        return chronology.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
    }

    public long getDateTimeMillis(long j, int i, int i2, int i3, int i4) throws IllegalArgumentException {
        Chronology chronology = this.iBase;
        if (chronology == null || (this.iBaseFlags & 1) != 1) {
            return super.getDateTimeMillis(j, i, i2, i3, i4);
        }
        return chronology.getDateTimeMillis(j, i, i2, i3, i4);
    }

    public final DurationField millis() {
        return this.iMillis;
    }

    public final DateTimeField millisOfSecond() {
        return this.iMillisOfSecond;
    }

    public final DateTimeField millisOfDay() {
        return this.iMillisOfDay;
    }

    public final DurationField seconds() {
        return this.iSeconds;
    }

    public final DateTimeField secondOfMinute() {
        return this.iSecondOfMinute;
    }

    public final DateTimeField secondOfDay() {
        return this.iSecondOfDay;
    }

    public final DurationField minutes() {
        return this.iMinutes;
    }

    public final DateTimeField minuteOfHour() {
        return this.iMinuteOfHour;
    }

    public final DateTimeField minuteOfDay() {
        return this.iMinuteOfDay;
    }

    public final DurationField hours() {
        return this.iHours;
    }

    public final DateTimeField hourOfDay() {
        return this.iHourOfDay;
    }

    public final DateTimeField clockhourOfDay() {
        return this.iClockhourOfDay;
    }

    public final DurationField halfdays() {
        return this.iHalfdays;
    }

    public final DateTimeField hourOfHalfday() {
        return this.iHourOfHalfday;
    }

    public final DateTimeField clockhourOfHalfday() {
        return this.iClockhourOfHalfday;
    }

    public final DateTimeField halfdayOfDay() {
        return this.iHalfdayOfDay;
    }

    public final DurationField days() {
        return this.iDays;
    }

    public final DateTimeField dayOfWeek() {
        return this.iDayOfWeek;
    }

    public final DateTimeField dayOfMonth() {
        return this.iDayOfMonth;
    }

    public final DateTimeField dayOfYear() {
        return this.iDayOfYear;
    }

    public final DurationField weeks() {
        return this.iWeeks;
    }

    public final DateTimeField weekOfWeekyear() {
        return this.iWeekOfWeekyear;
    }

    public final DurationField weekyears() {
        return this.iWeekyears;
    }

    public final DateTimeField weekyear() {
        return this.iWeekyear;
    }

    public final DateTimeField weekyearOfCentury() {
        return this.iWeekyearOfCentury;
    }

    public final DurationField months() {
        return this.iMonths;
    }

    public final DateTimeField monthOfYear() {
        return this.iMonthOfYear;
    }

    public final DurationField years() {
        return this.iYears;
    }

    public final DateTimeField year() {
        return this.iYear;
    }

    public final DateTimeField yearOfEra() {
        return this.iYearOfEra;
    }

    public final DateTimeField yearOfCentury() {
        return this.iYearOfCentury;
    }

    public final DurationField centuries() {
        return this.iCenturies;
    }

    public final DateTimeField centuryOfEra() {
        return this.iCenturyOfEra;
    }

    public final DurationField eras() {
        return this.iEras;
    }

    public final DateTimeField era() {
        return this.iEra;
    }

    protected final Chronology getBase() {
        return this.iBase;
    }

    protected final Object getParam() {
        return this.iParam;
    }

    private void setFields() {
        int i = 0;
        Fields fields = new Fields();
        if (this.iBase != null) {
            fields.copyFieldsFrom(this.iBase);
        }
        assemble(fields);
        DurationField durationField = fields.millis;
        if (durationField == null) {
            durationField = super.millis();
        }
        this.iMillis = durationField;
        durationField = fields.seconds;
        if (durationField == null) {
            durationField = super.seconds();
        }
        this.iSeconds = durationField;
        durationField = fields.minutes;
        if (durationField == null) {
            durationField = super.minutes();
        }
        this.iMinutes = durationField;
        durationField = fields.hours;
        if (durationField == null) {
            durationField = super.hours();
        }
        this.iHours = durationField;
        durationField = fields.halfdays;
        if (durationField == null) {
            durationField = super.halfdays();
        }
        this.iHalfdays = durationField;
        durationField = fields.days;
        if (durationField == null) {
            durationField = super.days();
        }
        this.iDays = durationField;
        durationField = fields.weeks;
        if (durationField == null) {
            durationField = super.weeks();
        }
        this.iWeeks = durationField;
        durationField = fields.weekyears;
        if (durationField == null) {
            durationField = super.weekyears();
        }
        this.iWeekyears = durationField;
        durationField = fields.months;
        if (durationField == null) {
            durationField = super.months();
        }
        this.iMonths = durationField;
        durationField = fields.years;
        if (durationField == null) {
            durationField = super.years();
        }
        this.iYears = durationField;
        durationField = fields.centuries;
        if (durationField == null) {
            durationField = super.centuries();
        }
        this.iCenturies = durationField;
        durationField = fields.eras;
        if (durationField == null) {
            durationField = super.eras();
        }
        this.iEras = durationField;
        DateTimeField dateTimeField = fields.millisOfSecond;
        if (dateTimeField == null) {
            dateTimeField = super.millisOfSecond();
        }
        this.iMillisOfSecond = dateTimeField;
        dateTimeField = fields.millisOfDay;
        if (dateTimeField == null) {
            dateTimeField = super.millisOfDay();
        }
        this.iMillisOfDay = dateTimeField;
        dateTimeField = fields.secondOfMinute;
        if (dateTimeField == null) {
            dateTimeField = super.secondOfMinute();
        }
        this.iSecondOfMinute = dateTimeField;
        dateTimeField = fields.secondOfDay;
        if (dateTimeField == null) {
            dateTimeField = super.secondOfDay();
        }
        this.iSecondOfDay = dateTimeField;
        dateTimeField = fields.minuteOfHour;
        if (dateTimeField == null) {
            dateTimeField = super.minuteOfHour();
        }
        this.iMinuteOfHour = dateTimeField;
        dateTimeField = fields.minuteOfDay;
        if (dateTimeField == null) {
            dateTimeField = super.minuteOfDay();
        }
        this.iMinuteOfDay = dateTimeField;
        dateTimeField = fields.hourOfDay;
        if (dateTimeField == null) {
            dateTimeField = super.hourOfDay();
        }
        this.iHourOfDay = dateTimeField;
        dateTimeField = fields.clockhourOfDay;
        if (dateTimeField == null) {
            dateTimeField = super.clockhourOfDay();
        }
        this.iClockhourOfDay = dateTimeField;
        dateTimeField = fields.hourOfHalfday;
        if (dateTimeField == null) {
            dateTimeField = super.hourOfHalfday();
        }
        this.iHourOfHalfday = dateTimeField;
        dateTimeField = fields.clockhourOfHalfday;
        if (dateTimeField == null) {
            dateTimeField = super.clockhourOfHalfday();
        }
        this.iClockhourOfHalfday = dateTimeField;
        dateTimeField = fields.halfdayOfDay;
        if (dateTimeField == null) {
            dateTimeField = super.halfdayOfDay();
        }
        this.iHalfdayOfDay = dateTimeField;
        dateTimeField = fields.dayOfWeek;
        if (dateTimeField == null) {
            dateTimeField = super.dayOfWeek();
        }
        this.iDayOfWeek = dateTimeField;
        dateTimeField = fields.dayOfMonth;
        if (dateTimeField == null) {
            dateTimeField = super.dayOfMonth();
        }
        this.iDayOfMonth = dateTimeField;
        dateTimeField = fields.dayOfYear;
        if (dateTimeField == null) {
            dateTimeField = super.dayOfYear();
        }
        this.iDayOfYear = dateTimeField;
        dateTimeField = fields.weekOfWeekyear;
        if (dateTimeField == null) {
            dateTimeField = super.weekOfWeekyear();
        }
        this.iWeekOfWeekyear = dateTimeField;
        dateTimeField = fields.weekyear;
        if (dateTimeField == null) {
            dateTimeField = super.weekyear();
        }
        this.iWeekyear = dateTimeField;
        dateTimeField = fields.weekyearOfCentury;
        if (dateTimeField == null) {
            dateTimeField = super.weekyearOfCentury();
        }
        this.iWeekyearOfCentury = dateTimeField;
        dateTimeField = fields.monthOfYear;
        if (dateTimeField == null) {
            dateTimeField = super.monthOfYear();
        }
        this.iMonthOfYear = dateTimeField;
        dateTimeField = fields.year;
        if (dateTimeField == null) {
            dateTimeField = super.year();
        }
        this.iYear = dateTimeField;
        dateTimeField = fields.yearOfEra;
        if (dateTimeField == null) {
            dateTimeField = super.yearOfEra();
        }
        this.iYearOfEra = dateTimeField;
        dateTimeField = fields.yearOfCentury;
        if (dateTimeField == null) {
            dateTimeField = super.yearOfCentury();
        }
        this.iYearOfCentury = dateTimeField;
        dateTimeField = fields.centuryOfEra;
        if (dateTimeField == null) {
            dateTimeField = super.centuryOfEra();
        }
        this.iCenturyOfEra = dateTimeField;
        dateTimeField = fields.era;
        if (dateTimeField == null) {
            dateTimeField = super.era();
        }
        this.iEra = dateTimeField;
        if (this.iBase != null) {
            int i2 = (this.iHourOfDay == this.iBase.hourOfDay() && this.iMinuteOfHour == this.iBase.minuteOfHour() && this.iSecondOfMinute == this.iBase.secondOfMinute() && this.iMillisOfSecond == this.iBase.millisOfSecond()) ? 1 : 0;
            i2 |= this.iMillisOfDay == this.iBase.millisOfDay() ? 2 : 0;
            if (this.iYear == this.iBase.year() && this.iMonthOfYear == this.iBase.monthOfYear() && this.iDayOfMonth == this.iBase.dayOfMonth()) {
                i = 4;
            }
            i |= i2;
        }
        this.iBaseFlags = i;
    }
}
