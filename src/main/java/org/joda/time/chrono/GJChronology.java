package org.joda.time.chrono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.chrono.AssembledChronology.Fields;
import org.joda.time.field.BaseDateTimeField;
import org.joda.time.field.DecoratedDurationField;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public final class GJChronology extends AssembledChronology {
    static final Instant DEFAULT_CUTOVER = new Instant(-12219292800000L);
    private static final Map<DateTimeZone, ArrayList<GJChronology>> cCache = new HashMap();
    private Instant iCutoverInstant;
    private long iCutoverMillis;
    private long iGapDuration;
    private GregorianChronology iGregorianChronology;
    private JulianChronology iJulianChronology;

    private class CutoverField extends BaseDateTimeField {
        final boolean iConvertByWeekyear;
        final long iCutover;
        protected DurationField iDurationField;
        final DateTimeField iGregorianField;
        final DateTimeField iJulianField;
        protected DurationField iRangeDurationField;

        CutoverField(GJChronology gJChronology, DateTimeField dateTimeField, DateTimeField dateTimeField2, long j) {
            this(dateTimeField, dateTimeField2, j, false);
        }

        CutoverField(DateTimeField dateTimeField, DateTimeField dateTimeField2, long j, boolean z) {
            super(dateTimeField2.getType());
            this.iJulianField = dateTimeField;
            this.iGregorianField = dateTimeField2;
            this.iCutover = j;
            this.iConvertByWeekyear = z;
            this.iDurationField = dateTimeField2.getDurationField();
            DurationField rangeDurationField = dateTimeField2.getRangeDurationField();
            if (rangeDurationField == null) {
                rangeDurationField = dateTimeField.getRangeDurationField();
            }
            this.iRangeDurationField = rangeDurationField;
        }

        public int get(long j) {
            if (j >= this.iCutover) {
                return this.iGregorianField.get(j);
            }
            return this.iJulianField.get(j);
        }

        public String getAsText(long j, Locale locale) {
            if (j >= this.iCutover) {
                return this.iGregorianField.getAsText(j, locale);
            }
            return this.iJulianField.getAsText(j, locale);
        }

        public String getAsText(int i, Locale locale) {
            return this.iGregorianField.getAsText(i, locale);
        }

        public String getAsShortText(long j, Locale locale) {
            if (j >= this.iCutover) {
                return this.iGregorianField.getAsShortText(j, locale);
            }
            return this.iJulianField.getAsShortText(j, locale);
        }

        public String getAsShortText(int i, Locale locale) {
            return this.iGregorianField.getAsShortText(i, locale);
        }

        public long add(long j, int i) {
            return this.iGregorianField.add(j, i);
        }

        public long add(long j, long j2) {
            return this.iGregorianField.add(j, j2);
        }

        public int getDifference(long j, long j2) {
            return this.iGregorianField.getDifference(j, j2);
        }

        public long getDifferenceAsLong(long j, long j2) {
            return this.iGregorianField.getDifferenceAsLong(j, j2);
        }

        public long set(long j, int i) {
            long j2;
            if (j >= this.iCutover) {
                j2 = this.iGregorianField.set(j, i);
                if (j2 < this.iCutover) {
                    if (GJChronology.this.iGapDuration + j2 < this.iCutover) {
                        j2 = gregorianToJulian(j2);
                    }
                    if (get(j2) != i) {
                        throw new IllegalFieldValueException(this.iGregorianField.getType(), Integer.valueOf(i), null, null);
                    }
                }
            }
            j2 = this.iJulianField.set(j, i);
            if (j2 >= this.iCutover) {
                if (j2 - GJChronology.this.iGapDuration >= this.iCutover) {
                    j2 = julianToGregorian(j2);
                }
                if (get(j2) != i) {
                    throw new IllegalFieldValueException(this.iJulianField.getType(), Integer.valueOf(i), null, null);
                }
            }
            return j2;
        }

        public long set(long j, String str, Locale locale) {
            long j2;
            if (j >= this.iCutover) {
                j2 = this.iGregorianField.set(j, str, locale);
                if (j2 >= this.iCutover || GJChronology.this.iGapDuration + j2 >= this.iCutover) {
                    return j2;
                }
                return gregorianToJulian(j2);
            }
            j2 = this.iJulianField.set(j, str, locale);
            if (j2 < this.iCutover || j2 - GJChronology.this.iGapDuration < this.iCutover) {
                return j2;
            }
            return julianToGregorian(j2);
        }

        public DurationField getDurationField() {
            return this.iDurationField;
        }

        public DurationField getRangeDurationField() {
            return this.iRangeDurationField;
        }

        public boolean isLeap(long j) {
            if (j >= this.iCutover) {
                return this.iGregorianField.isLeap(j);
            }
            return this.iJulianField.isLeap(j);
        }

        public DurationField getLeapDurationField() {
            return this.iGregorianField.getLeapDurationField();
        }

        public int getMinimumValue() {
            return this.iJulianField.getMinimumValue();
        }

        public int getMinimumValue(ReadablePartial readablePartial) {
            return this.iJulianField.getMinimumValue(readablePartial);
        }

        public int getMinimumValue(ReadablePartial readablePartial, int[] iArr) {
            return this.iJulianField.getMinimumValue(readablePartial, iArr);
        }

        public int getMaximumValue() {
            return this.iGregorianField.getMaximumValue();
        }

        public int getMaximumValue(long j) {
            if (j >= this.iCutover) {
                return this.iGregorianField.getMaximumValue(j);
            }
            int maximumValue = this.iJulianField.getMaximumValue(j);
            if (this.iJulianField.set(j, maximumValue) >= this.iCutover) {
                return this.iJulianField.get(this.iJulianField.add(this.iCutover, -1));
            }
            return maximumValue;
        }

        public int getMaximumValue(ReadablePartial readablePartial) {
            return getMaximumValue(GJChronology.getInstanceUTC().set(readablePartial, 0));
        }

        public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
            Chronology instanceUTC = GJChronology.getInstanceUTC();
            int size = readablePartial.size();
            long j = 0;
            for (int i = 0; i < size; i++) {
                DateTimeField field = readablePartial.getFieldType(i).getField(instanceUTC);
                if (iArr[i] <= field.getMaximumValue(j)) {
                    j = field.set(j, iArr[i]);
                }
            }
            return getMaximumValue(j);
        }

        public long roundFloor(long j) {
            if (j < this.iCutover) {
                return this.iJulianField.roundFloor(j);
            }
            long roundFloor = this.iGregorianField.roundFloor(j);
            if (roundFloor >= this.iCutover || GJChronology.this.iGapDuration + roundFloor >= this.iCutover) {
                return roundFloor;
            }
            return gregorianToJulian(roundFloor);
        }

        public long roundCeiling(long j) {
            if (j >= this.iCutover) {
                return this.iGregorianField.roundCeiling(j);
            }
            long roundCeiling = this.iJulianField.roundCeiling(j);
            if (roundCeiling < this.iCutover || roundCeiling - GJChronology.this.iGapDuration < this.iCutover) {
                return roundCeiling;
            }
            return julianToGregorian(roundCeiling);
        }

        public int getMaximumTextLength(Locale locale) {
            return Math.max(this.iJulianField.getMaximumTextLength(locale), this.iGregorianField.getMaximumTextLength(locale));
        }

        protected long julianToGregorian(long j) {
            if (this.iConvertByWeekyear) {
                return GJChronology.this.julianToGregorianByWeekyear(j);
            }
            return GJChronology.this.julianToGregorianByYear(j);
        }

        protected long gregorianToJulian(long j) {
            if (this.iConvertByWeekyear) {
                return GJChronology.this.gregorianToJulianByWeekyear(j);
            }
            return GJChronology.this.gregorianToJulianByYear(j);
        }
    }

    private final class ImpreciseCutoverField extends CutoverField {
        ImpreciseCutoverField(GJChronology gJChronology, DateTimeField dateTimeField, DateTimeField dateTimeField2, long j) {
            this(dateTimeField, dateTimeField2, null, j, false);
        }

        ImpreciseCutoverField(GJChronology gJChronology, DateTimeField dateTimeField, DateTimeField dateTimeField2, DurationField durationField, long j) {
            this(dateTimeField, dateTimeField2, durationField, j, false);
        }

        ImpreciseCutoverField(DateTimeField dateTimeField, DateTimeField dateTimeField2, DurationField durationField, long j, boolean z) {
            super(dateTimeField, dateTimeField2, j, z);
            if (durationField == null) {
                durationField = new LinkedDurationField(this.iDurationField, this);
            }
            this.iDurationField = durationField;
        }

        public long add(long j, int i) {
            long add;
            if (j >= this.iCutover) {
                add = this.iGregorianField.add(j, i);
                if (add >= this.iCutover || GJChronology.this.iGapDuration + add >= this.iCutover) {
                    return add;
                }
                if (this.iConvertByWeekyear) {
                    if (GJChronology.this.iGregorianChronology.weekyear().get(add) <= 0) {
                        add = GJChronology.this.iGregorianChronology.weekyear().add(add, -1);
                    }
                } else if (GJChronology.this.iGregorianChronology.year().get(add) <= 0) {
                    add = GJChronology.this.iGregorianChronology.year().add(add, -1);
                }
                return gregorianToJulian(add);
            }
            add = this.iJulianField.add(j, i);
            if (add < this.iCutover || add - GJChronology.this.iGapDuration < this.iCutover) {
                return add;
            }
            return julianToGregorian(add);
        }

        public long add(long j, long j2) {
            long add;
            if (j >= this.iCutover) {
                add = this.iGregorianField.add(j, j2);
                if (add >= this.iCutover || GJChronology.this.iGapDuration + add >= this.iCutover) {
                    return add;
                }
                if (this.iConvertByWeekyear) {
                    if (GJChronology.this.iGregorianChronology.weekyear().get(add) <= 0) {
                        add = GJChronology.this.iGregorianChronology.weekyear().add(add, -1);
                    }
                } else if (GJChronology.this.iGregorianChronology.year().get(add) <= 0) {
                    add = GJChronology.this.iGregorianChronology.year().add(add, -1);
                }
                return gregorianToJulian(add);
            }
            add = this.iJulianField.add(j, j2);
            if (add < this.iCutover || add - GJChronology.this.iGapDuration < this.iCutover) {
                return add;
            }
            return julianToGregorian(add);
        }

        public int getDifference(long j, long j2) {
            if (j >= this.iCutover) {
                if (j2 >= this.iCutover) {
                    return this.iGregorianField.getDifference(j, j2);
                }
                return this.iJulianField.getDifference(gregorianToJulian(j), j2);
            } else if (j2 < this.iCutover) {
                return this.iJulianField.getDifference(j, j2);
            } else {
                return this.iGregorianField.getDifference(julianToGregorian(j), j2);
            }
        }

        public long getDifferenceAsLong(long j, long j2) {
            if (j >= this.iCutover) {
                if (j2 >= this.iCutover) {
                    return this.iGregorianField.getDifferenceAsLong(j, j2);
                }
                return this.iJulianField.getDifferenceAsLong(gregorianToJulian(j), j2);
            } else if (j2 < this.iCutover) {
                return this.iJulianField.getDifferenceAsLong(j, j2);
            } else {
                return this.iGregorianField.getDifferenceAsLong(julianToGregorian(j), j2);
            }
        }

        public int getMaximumValue(long j) {
            if (j >= this.iCutover) {
                return this.iGregorianField.getMaximumValue(j);
            }
            return this.iJulianField.getMaximumValue(j);
        }
    }

    private static class LinkedDurationField extends DecoratedDurationField {
        private final ImpreciseCutoverField iField;

        LinkedDurationField(DurationField durationField, ImpreciseCutoverField impreciseCutoverField) {
            super(durationField, durationField.getType());
            this.iField = impreciseCutoverField;
        }

        public long add(long j, int i) {
            return this.iField.add(j, i);
        }

        public long add(long j, long j2) {
            return this.iField.add(j, j2);
        }

        public int getDifference(long j, long j2) {
            return this.iField.getDifference(j, j2);
        }

        public long getDifferenceAsLong(long j, long j2) {
            return this.iField.getDifferenceAsLong(j, j2);
        }
    }

    private static long convertByYear(long j, Chronology chronology, Chronology chronology2) {
        return chronology2.getDateTimeMillis(chronology.year().get(j), chronology.monthOfYear().get(j), chronology.dayOfMonth().get(j), chronology.millisOfDay().get(j));
    }

    private static long convertByWeekyear(long j, Chronology chronology, Chronology chronology2) {
        return chronology2.millisOfDay().set(chronology2.dayOfWeek().set(chronology2.weekOfWeekyear().set(chronology2.weekyear().set(0, chronology.weekyear().get(j)), chronology.weekOfWeekyear().get(j)), chronology.dayOfWeek().get(j)), chronology.millisOfDay().get(j));
    }

    public static GJChronology getInstanceUTC() {
        return getInstance(DateTimeZone.UTC, DEFAULT_CUTOVER, 4);
    }

    public static GJChronology getInstance(DateTimeZone dateTimeZone, ReadableInstant readableInstant) {
        return getInstance(dateTimeZone, readableInstant, 4);
    }

    public static synchronized GJChronology getInstance(DateTimeZone dateTimeZone, ReadableInstant readableInstant, int i) {
        GJChronology gJChronology;
        synchronized (GJChronology.class) {
            DateTimeZone zone = DateTimeUtils.getZone(dateTimeZone);
            if (readableInstant == null) {
                ReadableInstant readableInstant2 = DEFAULT_CUTOVER;
            } else {
                Instant toInstant = readableInstant.toInstant();
                if (new LocalDate(toInstant.getMillis(), GregorianChronology.getInstance(zone)).getYear() <= 0) {
                    throw new IllegalArgumentException("Cutover too early. Must be on or after 0001-01-01.");
                }
                Object obj = toInstant;
            }
            synchronized (cCache) {
                ArrayList arrayList = (ArrayList) cCache.get(zone);
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                    cCache.put(zone, arrayList);
                } else {
                    int size = arrayList.size();
                    while (true) {
                        int i2 = size - 1;
                        if (i2 < 0) {
                            break;
                        }
                        gJChronology = (GJChronology) arrayList.get(i2);
                        if (i == gJChronology.getMinimumDaysInFirstWeek() && readableInstant2.equals(gJChronology.getGregorianCutover())) {
                            break;
                        }
                        size = i2;
                    }
                }
                if (zone == DateTimeZone.UTC) {
                    gJChronology = new GJChronology(JulianChronology.getInstance(zone, i), GregorianChronology.getInstance(zone, i), readableInstant2);
                } else {
                    Chronology instance = getInstance(DateTimeZone.UTC, readableInstant2, i);
                    gJChronology = new GJChronology(ZonedChronology.getInstance(instance, zone), instance.iJulianChronology, instance.iGregorianChronology, instance.iCutoverInstant);
                }
                arrayList.add(gJChronology);
            }
        }
        return gJChronology;
    }

    public static GJChronology getInstance(DateTimeZone dateTimeZone, long j, int i) {
        ReadableInstant readableInstant;
        if (j == DEFAULT_CUTOVER.getMillis()) {
            readableInstant = null;
        } else {
            readableInstant = new Instant(j);
        }
        return getInstance(dateTimeZone, readableInstant, i);
    }

    private GJChronology(JulianChronology julianChronology, GregorianChronology gregorianChronology, Instant instant) {
        super(null, new Object[]{julianChronology, gregorianChronology, instant});
    }

    private GJChronology(Chronology chronology, JulianChronology julianChronology, GregorianChronology gregorianChronology, Instant instant) {
        super(chronology, new Object[]{julianChronology, gregorianChronology, instant});
    }

    public DateTimeZone getZone() {
        Chronology base = getBase();
        if (base != null) {
            return base.getZone();
        }
        return DateTimeZone.UTC;
    }

    public Chronology withUTC() {
        return withZone(DateTimeZone.UTC);
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone, this.iCutoverInstant, getMinimumDaysInFirstWeek());
    }

    public long getDateTimeMillis(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        Chronology base = getBase();
        if (base != null) {
            return base.getDateTimeMillis(i, i2, i3, i4);
        }
        long dateTimeMillis = this.iGregorianChronology.getDateTimeMillis(i, i2, i3, i4);
        if (dateTimeMillis >= this.iCutoverMillis) {
            return dateTimeMillis;
        }
        dateTimeMillis = this.iJulianChronology.getDateTimeMillis(i, i2, i3, i4);
        if (dateTimeMillis < this.iCutoverMillis) {
            return dateTimeMillis;
        }
        throw new IllegalArgumentException("Specified date does not exist");
    }

    public long getDateTimeMillis(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        long dateTimeMillis;
        Chronology base = getBase();
        if (base != null) {
            return base.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
        }
        try {
            dateTimeMillis = this.iGregorianChronology.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
        } catch (IllegalFieldValueException e) {
            IllegalFieldValueException illegalFieldValueException = e;
            if (i2 == 2 && i3 == 29) {
                dateTimeMillis = this.iGregorianChronology.getDateTimeMillis(i, i2, 28, i4, i5, i6, i7);
                if (dateTimeMillis >= this.iCutoverMillis) {
                    throw illegalFieldValueException;
                }
            }
            throw illegalFieldValueException;
        }
        if (dateTimeMillis >= this.iCutoverMillis) {
            return dateTimeMillis;
        }
        dateTimeMillis = this.iJulianChronology.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7);
        if (dateTimeMillis < this.iCutoverMillis) {
            return dateTimeMillis;
        }
        throw new IllegalArgumentException("Specified date does not exist");
    }

    public Instant getGregorianCutover() {
        return this.iCutoverInstant;
    }

    public int getMinimumDaysInFirstWeek() {
        return this.iGregorianChronology.getMinimumDaysInFirstWeek();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GJChronology)) {
            return false;
        }
        GJChronology gJChronology = (GJChronology) obj;
        if (this.iCutoverMillis == gJChronology.iCutoverMillis && getMinimumDaysInFirstWeek() == gJChronology.getMinimumDaysInFirstWeek() && getZone().equals(gJChronology.getZone())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((("GJ".hashCode() * 11) + getZone().hashCode()) + getMinimumDaysInFirstWeek()) + this.iCutoverInstant.hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(60);
        stringBuffer.append("GJChronology");
        stringBuffer.append('[');
        stringBuffer.append(getZone().getID());
        if (this.iCutoverMillis != DEFAULT_CUTOVER.getMillis()) {
            DateTimeFormatter date;
            stringBuffer.append(",cutover=");
            if (withUTC().dayOfYear().remainder(this.iCutoverMillis) == 0) {
                date = ISODateTimeFormat.date();
            } else {
                date = ISODateTimeFormat.dateTime();
            }
            date.withChronology(withUTC()).printTo(stringBuffer, this.iCutoverMillis);
        }
        if (getMinimumDaysInFirstWeek() != 4) {
            stringBuffer.append(",mdfw=");
            stringBuffer.append(getMinimumDaysInFirstWeek());
        }
        stringBuffer.append(']');
        return stringBuffer.toString();
    }

    protected void assemble(Fields fields) {
        Object[] objArr = (Object[]) getParam();
        JulianChronology julianChronology = (JulianChronology) objArr[0];
        GregorianChronology gregorianChronology = (GregorianChronology) objArr[1];
        Instant instant = (Instant) objArr[2];
        this.iCutoverMillis = instant.getMillis();
        this.iJulianChronology = julianChronology;
        this.iGregorianChronology = gregorianChronology;
        this.iCutoverInstant = instant;
        if (getBase() == null) {
            if (julianChronology.getMinimumDaysInFirstWeek() != gregorianChronology.getMinimumDaysInFirstWeek()) {
                throw new IllegalArgumentException();
            }
            this.iGapDuration = this.iCutoverMillis - julianToGregorianByYear(this.iCutoverMillis);
            fields.copyFieldsFrom(gregorianChronology);
            if (gregorianChronology.millisOfDay().get(this.iCutoverMillis) == 0) {
                fields.millisOfSecond = new CutoverField(this, julianChronology.millisOfSecond(), fields.millisOfSecond, this.iCutoverMillis);
                fields.millisOfDay = new CutoverField(this, julianChronology.millisOfDay(), fields.millisOfDay, this.iCutoverMillis);
                fields.secondOfMinute = new CutoverField(this, julianChronology.secondOfMinute(), fields.secondOfMinute, this.iCutoverMillis);
                fields.secondOfDay = new CutoverField(this, julianChronology.secondOfDay(), fields.secondOfDay, this.iCutoverMillis);
                fields.minuteOfHour = new CutoverField(this, julianChronology.minuteOfHour(), fields.minuteOfHour, this.iCutoverMillis);
                fields.minuteOfDay = new CutoverField(this, julianChronology.minuteOfDay(), fields.minuteOfDay, this.iCutoverMillis);
                fields.hourOfDay = new CutoverField(this, julianChronology.hourOfDay(), fields.hourOfDay, this.iCutoverMillis);
                fields.hourOfHalfday = new CutoverField(this, julianChronology.hourOfHalfday(), fields.hourOfHalfday, this.iCutoverMillis);
                fields.clockhourOfDay = new CutoverField(this, julianChronology.clockhourOfDay(), fields.clockhourOfDay, this.iCutoverMillis);
                fields.clockhourOfHalfday = new CutoverField(this, julianChronology.clockhourOfHalfday(), fields.clockhourOfHalfday, this.iCutoverMillis);
                fields.halfdayOfDay = new CutoverField(this, julianChronology.halfdayOfDay(), fields.halfdayOfDay, this.iCutoverMillis);
            }
            fields.era = new CutoverField(this, julianChronology.era(), fields.era, this.iCutoverMillis);
            fields.dayOfYear = new CutoverField(this, julianChronology.dayOfYear(), fields.dayOfYear, gregorianChronology.year().roundCeiling(this.iCutoverMillis));
            fields.weekOfWeekyear = new CutoverField(julianChronology.weekOfWeekyear(), fields.weekOfWeekyear, gregorianChronology.weekyear().roundCeiling(this.iCutoverMillis), true);
            fields.year = new ImpreciseCutoverField(this, julianChronology.year(), fields.year, this.iCutoverMillis);
            fields.years = fields.year.getDurationField();
            fields.yearOfEra = new ImpreciseCutoverField(this, julianChronology.yearOfEra(), fields.yearOfEra, fields.years, this.iCutoverMillis);
            fields.yearOfCentury = new ImpreciseCutoverField(this, julianChronology.yearOfCentury(), fields.yearOfCentury, fields.years, this.iCutoverMillis);
            fields.centuryOfEra = new ImpreciseCutoverField(this, julianChronology.centuryOfEra(), fields.centuryOfEra, this.iCutoverMillis);
            fields.centuries = fields.centuryOfEra.getDurationField();
            fields.monthOfYear = new ImpreciseCutoverField(this, julianChronology.monthOfYear(), fields.monthOfYear, this.iCutoverMillis);
            fields.months = fields.monthOfYear.getDurationField();
            fields.weekyear = new ImpreciseCutoverField(julianChronology.weekyear(), fields.weekyear, null, this.iCutoverMillis, true);
            fields.weekyearOfCentury = new ImpreciseCutoverField(this, julianChronology.weekyearOfCentury(), fields.weekyearOfCentury, fields.weekyears, this.iCutoverMillis);
            fields.weekyears = fields.weekyear.getDurationField();
            DateTimeField cutoverField = new CutoverField(this, julianChronology.dayOfMonth(), fields.dayOfMonth, this.iCutoverMillis);
            cutoverField.iRangeDurationField = fields.months;
            fields.dayOfMonth = cutoverField;
        }
    }

    long julianToGregorianByYear(long j) {
        return convertByYear(j, this.iJulianChronology, this.iGregorianChronology);
    }

    long gregorianToJulianByYear(long j) {
        return convertByYear(j, this.iGregorianChronology, this.iJulianChronology);
    }

    long julianToGregorianByWeekyear(long j) {
        return convertByWeekyear(j, this.iJulianChronology, this.iGregorianChronology);
    }

    long gregorianToJulianByWeekyear(long j) {
        return convertByWeekyear(j, this.iGregorianChronology, this.iJulianChronology);
    }
}
