package org.joda.time;

import java.util.Locale;

public abstract class DateTimeField {
    public abstract long add(long j, int i);

    public abstract long add(long j, long j2);

    public abstract int get(long j);

    public abstract String getAsShortText(int i, Locale locale);

    public abstract String getAsShortText(long j, Locale locale);

    public abstract String getAsShortText(ReadablePartial readablePartial, Locale locale);

    public abstract String getAsText(int i, Locale locale);

    public abstract String getAsText(long j, Locale locale);

    public abstract String getAsText(ReadablePartial readablePartial, Locale locale);

    public abstract int getDifference(long j, long j2);

    public abstract long getDifferenceAsLong(long j, long j2);

    public abstract DurationField getDurationField();

    public abstract DurationField getLeapDurationField();

    public abstract int getMaximumTextLength(Locale locale);

    public abstract int getMaximumValue();

    public abstract int getMaximumValue(long j);

    public abstract int getMaximumValue(ReadablePartial readablePartial);

    public abstract int getMaximumValue(ReadablePartial readablePartial, int[] iArr);

    public abstract int getMinimumValue();

    public abstract int getMinimumValue(ReadablePartial readablePartial);

    public abstract int getMinimumValue(ReadablePartial readablePartial, int[] iArr);

    public abstract String getName();

    public abstract DurationField getRangeDurationField();

    public abstract DateTimeFieldType getType();

    public abstract boolean isLeap(long j);

    public abstract boolean isSupported();

    public abstract long remainder(long j);

    public abstract long roundCeiling(long j);

    public abstract long roundFloor(long j);

    public abstract long roundHalfCeiling(long j);

    public abstract long roundHalfEven(long j);

    public abstract long roundHalfFloor(long j);

    public abstract long set(long j, int i);

    public abstract long set(long j, String str, Locale locale);
}
