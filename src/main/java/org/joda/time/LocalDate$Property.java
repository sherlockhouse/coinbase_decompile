package org.joda.time;

import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class LocalDate$Property extends AbstractReadableInstantFieldProperty {
    private transient DateTimeField iField;
    private transient LocalDate iInstant;

    LocalDate$Property(LocalDate localDate, DateTimeField dateTimeField) {
        this.iInstant = localDate;
        this.iField = dateTimeField;
    }

    public DateTimeField getField() {
        return this.iField;
    }

    protected long getMillis() {
        return this.iInstant.getLocalMillis();
    }

    protected Chronology getChronology() {
        return this.iInstant.getChronology();
    }
}
