package org.joda.time.field;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;

public abstract class AbstractReadableInstantFieldProperty implements Serializable {
    public abstract DateTimeField getField();

    protected abstract long getMillis();

    public DateTimeFieldType getFieldType() {
        return getField().getType();
    }

    public String getName() {
        return getField().getName();
    }

    protected Chronology getChronology() {
        throw new UnsupportedOperationException("The method getChronology() was added in v1.4 and needs to be implemented by subclasses of AbstractReadableInstantFieldProperty");
    }

    public int get() {
        return getField().get(getMillis());
    }

    public String getAsText(Locale locale) {
        return getField().getAsText(getMillis(), locale);
    }

    public String getAsShortText(Locale locale) {
        return getField().getAsShortText(getMillis(), locale);
    }

    public int getMinimumValueOverall() {
        return getField().getMinimumValue();
    }

    public int getMaximumValueOverall() {
        return getField().getMaximumValue();
    }

    public int getMaximumTextLength(Locale locale) {
        return getField().getMaximumTextLength(locale);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractReadableInstantFieldProperty)) {
            return false;
        }
        AbstractReadableInstantFieldProperty abstractReadableInstantFieldProperty = (AbstractReadableInstantFieldProperty) obj;
        if (get() == abstractReadableInstantFieldProperty.get() && getFieldType().equals(abstractReadableInstantFieldProperty.getFieldType()) && FieldUtils.equals(getChronology(), abstractReadableInstantFieldProperty.getChronology())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((get() * 17) + getFieldType().hashCode()) + getChronology().hashCode();
    }

    public String toString() {
        return "Property[" + getName() + "]";
    }
}
