package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.chrono.AssembledChronology.Fields;
import org.joda.time.field.DecoratedDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class LimitChronology extends AssembledChronology {
    final DateTime iLowerLimit;
    final DateTime iUpperLimit;
    private transient LimitChronology iWithUTC;

    private class LimitDateTimeField extends DecoratedDateTimeField {
        private final DurationField iDurationField;
        private final DurationField iLeapDurationField;
        private final DurationField iRangeDurationField;

        LimitDateTimeField(DateTimeField dateTimeField, DurationField durationField, DurationField durationField2, DurationField durationField3) {
            super(dateTimeField, dateTimeField.getType());
            this.iDurationField = durationField;
            this.iRangeDurationField = durationField2;
            this.iLeapDurationField = durationField3;
        }

        public int get(long j) {
            LimitChronology.this.checkLimits(j, null);
            return getWrappedField().get(j);
        }

        public String getAsText(long j, Locale locale) {
            LimitChronology.this.checkLimits(j, null);
            return getWrappedField().getAsText(j, locale);
        }

        public String getAsShortText(long j, Locale locale) {
            LimitChronology.this.checkLimits(j, null);
            return getWrappedField().getAsShortText(j, locale);
        }

        public long add(long j, int i) {
            LimitChronology.this.checkLimits(j, null);
            long add = getWrappedField().add(j, i);
            LimitChronology.this.checkLimits(add, "resulting");
            return add;
        }

        public long add(long j, long j2) {
            LimitChronology.this.checkLimits(j, null);
            long add = getWrappedField().add(j, j2);
            LimitChronology.this.checkLimits(add, "resulting");
            return add;
        }

        public int getDifference(long j, long j2) {
            LimitChronology.this.checkLimits(j, "minuend");
            LimitChronology.this.checkLimits(j2, "subtrahend");
            return getWrappedField().getDifference(j, j2);
        }

        public long getDifferenceAsLong(long j, long j2) {
            LimitChronology.this.checkLimits(j, "minuend");
            LimitChronology.this.checkLimits(j2, "subtrahend");
            return getWrappedField().getDifferenceAsLong(j, j2);
        }

        public long set(long j, int i) {
            LimitChronology.this.checkLimits(j, null);
            long j2 = getWrappedField().set(j, i);
            LimitChronology.this.checkLimits(j2, "resulting");
            return j2;
        }

        public long set(long j, String str, Locale locale) {
            LimitChronology.this.checkLimits(j, null);
            long j2 = getWrappedField().set(j, str, locale);
            LimitChronology.this.checkLimits(j2, "resulting");
            return j2;
        }

        public final DurationField getDurationField() {
            return this.iDurationField;
        }

        public final DurationField getRangeDurationField() {
            return this.iRangeDurationField;
        }

        public boolean isLeap(long j) {
            LimitChronology.this.checkLimits(j, null);
            return getWrappedField().isLeap(j);
        }

        public final DurationField getLeapDurationField() {
            return this.iLeapDurationField;
        }

        public long roundFloor(long j) {
            LimitChronology.this.checkLimits(j, null);
            long roundFloor = getWrappedField().roundFloor(j);
            LimitChronology.this.checkLimits(roundFloor, "resulting");
            return roundFloor;
        }

        public long roundCeiling(long j) {
            LimitChronology.this.checkLimits(j, null);
            long roundCeiling = getWrappedField().roundCeiling(j);
            LimitChronology.this.checkLimits(roundCeiling, "resulting");
            return roundCeiling;
        }

        public long roundHalfFloor(long j) {
            LimitChronology.this.checkLimits(j, null);
            long roundHalfFloor = getWrappedField().roundHalfFloor(j);
            LimitChronology.this.checkLimits(roundHalfFloor, "resulting");
            return roundHalfFloor;
        }

        public long roundHalfCeiling(long j) {
            LimitChronology.this.checkLimits(j, null);
            long roundHalfCeiling = getWrappedField().roundHalfCeiling(j);
            LimitChronology.this.checkLimits(roundHalfCeiling, "resulting");
            return roundHalfCeiling;
        }

        public long roundHalfEven(long j) {
            LimitChronology.this.checkLimits(j, null);
            long roundHalfEven = getWrappedField().roundHalfEven(j);
            LimitChronology.this.checkLimits(roundHalfEven, "resulting");
            return roundHalfEven;
        }

        public long remainder(long j) {
            LimitChronology.this.checkLimits(j, null);
            long remainder = getWrappedField().remainder(j);
            LimitChronology.this.checkLimits(remainder, "resulting");
            return remainder;
        }

        public int getMaximumValue(long j) {
            LimitChronology.this.checkLimits(j, null);
            return getWrappedField().getMaximumValue(j);
        }

        public int getMaximumTextLength(Locale locale) {
            return getWrappedField().getMaximumTextLength(locale);
        }
    }

    private class LimitDurationField extends DecoratedDurationField {
        LimitDurationField(DurationField durationField) {
            super(durationField, durationField.getType());
        }

        public long add(long j, int i) {
            LimitChronology.this.checkLimits(j, null);
            long add = getWrappedField().add(j, i);
            LimitChronology.this.checkLimits(add, "resulting");
            return add;
        }

        public long add(long j, long j2) {
            LimitChronology.this.checkLimits(j, null);
            long add = getWrappedField().add(j, j2);
            LimitChronology.this.checkLimits(add, "resulting");
            return add;
        }

        public int getDifference(long j, long j2) {
            LimitChronology.this.checkLimits(j, "minuend");
            LimitChronology.this.checkLimits(j2, "subtrahend");
            return getWrappedField().getDifference(j, j2);
        }

        public long getDifferenceAsLong(long j, long j2) {
            LimitChronology.this.checkLimits(j, "minuend");
            LimitChronology.this.checkLimits(j2, "subtrahend");
            return getWrappedField().getDifferenceAsLong(j, j2);
        }
    }

    private class LimitException extends IllegalArgumentException {
        private final boolean iIsLow;

        LimitException(String str, boolean z) {
            super(str);
            this.iIsLow = z;
        }

        public String getMessage() {
            StringBuffer stringBuffer = new StringBuffer(85);
            stringBuffer.append("The");
            String message = super.getMessage();
            if (message != null) {
                stringBuffer.append(' ');
                stringBuffer.append(message);
            }
            stringBuffer.append(" instant is ");
            DateTimeFormatter withChronology = ISODateTimeFormat.dateTime().withChronology(LimitChronology.this.getBase());
            if (this.iIsLow) {
                stringBuffer.append("below the supported minimum of ");
                withChronology.printTo(stringBuffer, LimitChronology.this.getLowerLimit().getMillis());
            } else {
                stringBuffer.append("above the supported maximum of ");
                withChronology.printTo(stringBuffer, LimitChronology.this.getUpperLimit().getMillis());
            }
            stringBuffer.append(" (");
            stringBuffer.append(LimitChronology.this.getBase());
            stringBuffer.append(')');
            return stringBuffer.toString();
        }

        public String toString() {
            return "IllegalArgumentException: " + getMessage();
        }
    }

    public static LimitChronology getInstance(Chronology chronology, ReadableDateTime readableDateTime, ReadableDateTime readableDateTime2) {
        ReadableInstant readableInstant = null;
        if (chronology == null) {
            throw new IllegalArgumentException("Must supply a chronology");
        }
        ReadableDateTime toDateTime = readableDateTime == null ? null : readableDateTime.toDateTime();
        if (readableDateTime2 != null) {
            readableInstant = readableDateTime2.toDateTime();
        }
        if (toDateTime == null || readableInstant == null || toDateTime.isBefore(readableInstant)) {
            return new LimitChronology(chronology, (DateTime) toDateTime, (DateTime) readableInstant);
        }
        throw new IllegalArgumentException("The lower limit must be come before than the upper limit");
    }

    private LimitChronology(Chronology chronology, DateTime dateTime, DateTime dateTime2) {
        super(chronology, null);
        this.iLowerLimit = dateTime;
        this.iUpperLimit = dateTime2;
    }

    public DateTime getLowerLimit() {
        return this.iLowerLimit;
    }

    public DateTime getUpperLimit() {
        return this.iUpperLimit;
    }

    public Chronology withUTC() {
        return withZone(DateTimeZone.UTC);
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        if (dateTimeZone == getZone()) {
            return this;
        }
        if (dateTimeZone == DateTimeZone.UTC && this.iWithUTC != null) {
            return this.iWithUTC;
        }
        ReadableDateTime readableDateTime = this.iLowerLimit;
        if (readableDateTime != null) {
            MutableDateTime toMutableDateTime = readableDateTime.toMutableDateTime();
            toMutableDateTime.setZoneRetainFields(dateTimeZone);
            readableDateTime = toMutableDateTime.toDateTime();
        }
        ReadableDateTime readableDateTime2 = this.iUpperLimit;
        if (readableDateTime2 != null) {
            MutableDateTime toMutableDateTime2 = readableDateTime2.toMutableDateTime();
            toMutableDateTime2.setZoneRetainFields(dateTimeZone);
            readableDateTime2 = toMutableDateTime2.toDateTime();
        }
        Chronology instance = getInstance(getBase().withZone(dateTimeZone), readableDateTime, readableDateTime2);
        if (dateTimeZone == DateTimeZone.UTC) {
            this.iWithUTC = instance;
        }
        return instance;
    }

    public long getDateTimeMillis(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        long dateTimeMillis = getBase().getDateTimeMillis(i, i2, i3, i4);
        checkLimits(dateTimeMillis, "resulting");
        return dateTimeMillis;
    }

    public long getDateTimeMillis(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        long dateTimeMillis = getBase().getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
        checkLimits(dateTimeMillis, "resulting");
        return dateTimeMillis;
    }

    public long getDateTimeMillis(long j, int i, int i2, int i3, int i4) throws IllegalArgumentException {
        checkLimits(j, null);
        long dateTimeMillis = getBase().getDateTimeMillis(j, i, i2, i3, i4);
        checkLimits(dateTimeMillis, "resulting");
        return dateTimeMillis;
    }

    protected void assemble(Fields fields) {
        HashMap hashMap = new HashMap();
        fields.eras = convertField(fields.eras, hashMap);
        fields.centuries = convertField(fields.centuries, hashMap);
        fields.years = convertField(fields.years, hashMap);
        fields.months = convertField(fields.months, hashMap);
        fields.weekyears = convertField(fields.weekyears, hashMap);
        fields.weeks = convertField(fields.weeks, hashMap);
        fields.days = convertField(fields.days, hashMap);
        fields.halfdays = convertField(fields.halfdays, hashMap);
        fields.hours = convertField(fields.hours, hashMap);
        fields.minutes = convertField(fields.minutes, hashMap);
        fields.seconds = convertField(fields.seconds, hashMap);
        fields.millis = convertField(fields.millis, hashMap);
        fields.year = convertField(fields.year, hashMap);
        fields.yearOfEra = convertField(fields.yearOfEra, hashMap);
        fields.yearOfCentury = convertField(fields.yearOfCentury, hashMap);
        fields.centuryOfEra = convertField(fields.centuryOfEra, hashMap);
        fields.era = convertField(fields.era, hashMap);
        fields.dayOfWeek = convertField(fields.dayOfWeek, hashMap);
        fields.dayOfMonth = convertField(fields.dayOfMonth, hashMap);
        fields.dayOfYear = convertField(fields.dayOfYear, hashMap);
        fields.monthOfYear = convertField(fields.monthOfYear, hashMap);
        fields.weekOfWeekyear = convertField(fields.weekOfWeekyear, hashMap);
        fields.weekyear = convertField(fields.weekyear, hashMap);
        fields.weekyearOfCentury = convertField(fields.weekyearOfCentury, hashMap);
        fields.millisOfSecond = convertField(fields.millisOfSecond, hashMap);
        fields.millisOfDay = convertField(fields.millisOfDay, hashMap);
        fields.secondOfMinute = convertField(fields.secondOfMinute, hashMap);
        fields.secondOfDay = convertField(fields.secondOfDay, hashMap);
        fields.minuteOfHour = convertField(fields.minuteOfHour, hashMap);
        fields.minuteOfDay = convertField(fields.minuteOfDay, hashMap);
        fields.hourOfDay = convertField(fields.hourOfDay, hashMap);
        fields.hourOfHalfday = convertField(fields.hourOfHalfday, hashMap);
        fields.clockhourOfDay = convertField(fields.clockhourOfDay, hashMap);
        fields.clockhourOfHalfday = convertField(fields.clockhourOfHalfday, hashMap);
        fields.halfdayOfDay = convertField(fields.halfdayOfDay, hashMap);
    }

    private DurationField convertField(DurationField durationField, HashMap<Object, Object> hashMap) {
        if (durationField == null || !durationField.isSupported()) {
            return durationField;
        }
        if (hashMap.containsKey(durationField)) {
            return (DurationField) hashMap.get(durationField);
        }
        DurationField limitDurationField = new LimitDurationField(durationField);
        hashMap.put(durationField, limitDurationField);
        return limitDurationField;
    }

    private DateTimeField convertField(DateTimeField dateTimeField, HashMap<Object, Object> hashMap) {
        if (dateTimeField == null || !dateTimeField.isSupported()) {
            return dateTimeField;
        }
        if (hashMap.containsKey(dateTimeField)) {
            return (DateTimeField) hashMap.get(dateTimeField);
        }
        DateTimeField limitDateTimeField = new LimitDateTimeField(dateTimeField, convertField(dateTimeField.getDurationField(), (HashMap) hashMap), convertField(dateTimeField.getRangeDurationField(), (HashMap) hashMap), convertField(dateTimeField.getLeapDurationField(), (HashMap) hashMap));
        hashMap.put(dateTimeField, limitDateTimeField);
        return limitDateTimeField;
    }

    void checkLimits(long j, String str) {
        DateTime dateTime = this.iLowerLimit;
        if (dateTime == null || j >= dateTime.getMillis()) {
            dateTime = this.iUpperLimit;
            if (dateTime != null && j >= dateTime.getMillis()) {
                throw new LimitException(str, false);
            }
            return;
        }
        throw new LimitException(str, true);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LimitChronology)) {
            return false;
        }
        LimitChronology limitChronology = (LimitChronology) obj;
        if (getBase().equals(limitChronology.getBase()) && FieldUtils.equals(getLowerLimit(), limitChronology.getLowerLimit()) && FieldUtils.equals(getUpperLimit(), limitChronology.getUpperLimit())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (getLowerLimit() != null) {
            hashCode = getLowerLimit().hashCode();
        } else {
            hashCode = 0;
        }
        hashCode += 317351877;
        if (getUpperLimit() != null) {
            i = getUpperLimit().hashCode();
        }
        return (hashCode + i) + (getBase().hashCode() * 7);
    }

    public String toString() {
        return "LimitChronology[" + getBase().toString() + ", " + (getLowerLimit() == null ? "NoLimit" : getLowerLimit().toString()) + ", " + (getUpperLimit() == null ? "NoLimit" : getUpperLimit().toString()) + ']';
    }
}
