package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;

public final class SkipDateTimeField extends DelegatedDateTimeField {
    private final Chronology iChronology;
    private transient int iMinValue;
    private final int iSkip;

    public SkipDateTimeField(Chronology chronology, DateTimeField dateTimeField) {
        this(chronology, dateTimeField, 0);
    }

    public SkipDateTimeField(Chronology chronology, DateTimeField dateTimeField, int i) {
        super(dateTimeField);
        this.iChronology = chronology;
        int minimumValue = super.getMinimumValue();
        if (minimumValue < i) {
            this.iMinValue = minimumValue - 1;
        } else if (minimumValue == i) {
            this.iMinValue = i + 1;
        } else {
            this.iMinValue = minimumValue;
        }
        this.iSkip = i;
    }

    public int get(long j) {
        int i = super.get(j);
        if (i <= this.iSkip) {
            return i - 1;
        }
        return i;
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i, this.iMinValue, getMaximumValue());
        if (i <= this.iSkip) {
            if (i == this.iSkip) {
                throw new IllegalFieldValueException(DateTimeFieldType.year(), Integer.valueOf(i), null, null);
            }
            i++;
        }
        return super.set(j, i);
    }

    public int getMinimumValue() {
        return this.iMinValue;
    }
}
