package org.joda.time;

import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class DateMidnight$Property extends AbstractReadableInstantFieldProperty {
    private DateTimeField iField;
    private DateMidnight iInstant;

    DateMidnight$Property(DateMidnight dateMidnight, DateTimeField dateTimeField) {
        this.iInstant = dateMidnight;
        this.iField = dateTimeField;
    }

    public DateTimeField getField() {
        return this.iField;
    }

    protected long getMillis() {
        return this.iInstant.getMillis();
    }

    protected Chronology getChronology() {
        return this.iInstant.getChronology();
    }
}
