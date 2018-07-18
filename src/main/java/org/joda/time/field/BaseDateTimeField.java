package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;

public abstract class BaseDateTimeField extends DateTimeField {
    private final DateTimeFieldType iType;

    public abstract int get(long j);

    public abstract DurationField getDurationField();

    public abstract int getMaximumValue();

    public abstract int getMinimumValue();

    public abstract long roundFloor(long j);

    public abstract long set(long j, int i);

    protected BaseDateTimeField(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The type must not be null");
        }
        this.iType = dateTimeFieldType;
    }

    public final DateTimeFieldType getType() {
        return this.iType;
    }

    public final String getName() {
        return this.iType.getName();
    }

    public final boolean isSupported() {
        return true;
    }

    public String getAsText(long j, Locale locale) {
        return getAsText(get(j), locale);
    }

    public String getAsText(ReadablePartial readablePartial, int i, Locale locale) {
        return getAsText(i, locale);
    }

    public final String getAsText(ReadablePartial readablePartial, Locale locale) {
        return getAsText(readablePartial, readablePartial.get(getType()), locale);
    }

    public String getAsText(int i, Locale locale) {
        return Integer.toString(i);
    }

    public String getAsShortText(long j, Locale locale) {
        return getAsShortText(get(j), locale);
    }

    public String getAsShortText(ReadablePartial readablePartial, int i, Locale locale) {
        return getAsShortText(i, locale);
    }

    public final String getAsShortText(ReadablePartial readablePartial, Locale locale) {
        return getAsShortText(readablePartial, readablePartial.get(getType()), locale);
    }

    public String getAsShortText(int i, Locale locale) {
        return getAsText(i, locale);
    }

    public long add(long j, int i) {
        return getDurationField().add(j, i);
    }

    public long add(long j, long j2) {
        return getDurationField().add(j, j2);
    }

    public int getDifference(long j, long j2) {
        return getDurationField().getDifference(j, j2);
    }

    public long getDifferenceAsLong(long j, long j2) {
        return getDurationField().getDifferenceAsLong(j, j2);
    }

    public long set(long j, String str, Locale locale) {
        return set(j, convertText(str, locale));
    }

    protected int convertText(String str, Locale locale) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalFieldValueException(getType(), str);
        }
    }

    public boolean isLeap(long j) {
        return false;
    }

    public DurationField getLeapDurationField() {
        return null;
    }

    public int getMinimumValue(ReadablePartial readablePartial) {
        return getMinimumValue();
    }

    public int getMinimumValue(ReadablePartial readablePartial, int[] iArr) {
        return getMinimumValue(readablePartial);
    }

    public int getMaximumValue(long j) {
        return getMaximumValue();
    }

    public int getMaximumValue(ReadablePartial readablePartial) {
        return getMaximumValue();
    }

    public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
        return getMaximumValue(readablePartial);
    }

    public int getMaximumTextLength(Locale locale) {
        int maximumValue = getMaximumValue();
        if (maximumValue >= 0) {
            if (maximumValue < 10) {
                return 1;
            }
            if (maximumValue < 100) {
                return 2;
            }
            if (maximumValue < 1000) {
                return 3;
            }
        }
        return Integer.toString(maximumValue).length();
    }

    public long roundCeiling(long j) {
        long roundFloor = roundFloor(j);
        if (roundFloor != j) {
            return add(roundFloor, 1);
        }
        return j;
    }

    public long roundHalfFloor(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        return j - roundFloor <= roundCeiling - j ? roundFloor : roundCeiling;
    }

    public long roundHalfCeiling(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        return roundCeiling - j <= j - roundFloor ? roundCeiling : roundFloor;
    }

    public long roundHalfEven(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        long j2 = j - roundFloor;
        long j3 = roundCeiling - j;
        if (j2 < j3) {
            return roundFloor;
        }
        if (j3 < j2) {
            return roundCeiling;
        }
        if ((get(roundCeiling) & 1) == 0) {
            return roundCeiling;
        }
        return roundFloor;
    }

    public long remainder(long j) {
        return j - roundFloor(j);
    }

    public String toString() {
        return "DateTimeField[" + getName() + ']';
    }
}
