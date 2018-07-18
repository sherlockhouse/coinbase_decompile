package org.joda.time.field;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;

public final class SkipUndoDateTimeField extends DelegatedDateTimeField {
    private final Chronology iChronology;
    private transient int iMinValue;
    private final int iSkip;

    public SkipUndoDateTimeField(Chronology chronology, DateTimeField dateTimeField) {
        this(chronology, dateTimeField, 0);
    }

    public SkipUndoDateTimeField(Chronology chronology, DateTimeField dateTimeField, int i) {
        super(dateTimeField);
        this.iChronology = chronology;
        int minimumValue = super.getMinimumValue();
        if (minimumValue < i) {
            this.iMinValue = minimumValue + 1;
        } else if (minimumValue == i + 1) {
            this.iMinValue = i;
        } else {
            this.iMinValue = minimumValue;
        }
        this.iSkip = i;
    }

    public int get(long j) {
        int i = super.get(j);
        if (i < this.iSkip) {
            return i + 1;
        }
        return i;
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i, this.iMinValue, getMaximumValue());
        if (i <= this.iSkip) {
            i--;
        }
        return super.set(j, i);
    }

    public int getMinimumValue() {
        return this.iMinValue;
    }
}
