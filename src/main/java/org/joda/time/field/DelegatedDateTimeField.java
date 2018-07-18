package org.joda.time.field;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;

public class DelegatedDateTimeField extends DateTimeField implements Serializable {
    private final DateTimeField iField;
    private final DateTimeFieldType iType;

    public DelegatedDateTimeField(DateTimeField dateTimeField) {
        this(dateTimeField, null);
    }

    public DelegatedDateTimeField(DateTimeField dateTimeField, DateTimeFieldType dateTimeFieldType) {
        if (dateTimeField == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        this.iField = dateTimeField;
        if (dateTimeFieldType == null) {
            dateTimeFieldType = dateTimeField.getType();
        }
        this.iType = dateTimeFieldType;
    }

    public DateTimeFieldType getType() {
        return this.iType;
    }

    public String getName() {
        return this.iType.getName();
    }

    public boolean isSupported() {
        return this.iField.isSupported();
    }

    public int get(long j) {
        return this.iField.get(j);
    }

    public String getAsText(long j, Locale locale) {
        return this.iField.getAsText(j, locale);
    }

    public String getAsText(ReadablePartial readablePartial, Locale locale) {
        return this.iField.getAsText(readablePartial, locale);
    }

    public String getAsText(int i, Locale locale) {
        return this.iField.getAsText(i, locale);
    }

    public String getAsShortText(long j, Locale locale) {
        return this.iField.getAsShortText(j, locale);
    }

    public String getAsShortText(ReadablePartial readablePartial, Locale locale) {
        return this.iField.getAsShortText(readablePartial, locale);
    }

    public String getAsShortText(int i, Locale locale) {
        return this.iField.getAsShortText(i, locale);
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

    public long set(long j, int i) {
        return this.iField.set(j, i);
    }

    public long set(long j, String str, Locale locale) {
        return this.iField.set(j, str, locale);
    }

    public DurationField getDurationField() {
        return this.iField.getDurationField();
    }

    public DurationField getRangeDurationField() {
        return this.iField.getRangeDurationField();
    }

    public boolean isLeap(long j) {
        return this.iField.isLeap(j);
    }

    public DurationField getLeapDurationField() {
        return this.iField.getLeapDurationField();
    }

    public int getMinimumValue() {
        return this.iField.getMinimumValue();
    }

    public int getMinimumValue(ReadablePartial readablePartial) {
        return this.iField.getMinimumValue(readablePartial);
    }

    public int getMinimumValue(ReadablePartial readablePartial, int[] iArr) {
        return this.iField.getMinimumValue(readablePartial, iArr);
    }

    public int getMaximumValue() {
        return this.iField.getMaximumValue();
    }

    public int getMaximumValue(long j) {
        return this.iField.getMaximumValue(j);
    }

    public int getMaximumValue(ReadablePartial readablePartial) {
        return this.iField.getMaximumValue(readablePartial);
    }

    public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
        return this.iField.getMaximumValue(readablePartial, iArr);
    }

    public int getMaximumTextLength(Locale locale) {
        return this.iField.getMaximumTextLength(locale);
    }

    public long roundFloor(long j) {
        return this.iField.roundFloor(j);
    }

    public long roundCeiling(long j) {
        return this.iField.roundCeiling(j);
    }

    public long roundHalfFloor(long j) {
        return this.iField.roundHalfFloor(j);
    }

    public long roundHalfCeiling(long j) {
        return this.iField.roundHalfCeiling(j);
    }

    public long roundHalfEven(long j) {
        return this.iField.roundHalfEven(j);
    }

    public long remainder(long j) {
        return this.iField.remainder(j);
    }

    public String toString() {
        return "DateTimeField[" + getName() + ']';
    }
}
