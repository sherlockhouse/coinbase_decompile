package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseDateTime;
import org.joda.time.field.AbstractReadableInstantFieldProperty;
import org.joda.time.format.ISODateTimeFormat;

public class MutableDateTime extends BaseDateTime implements Serializable, Cloneable, ReadWritableDateTime {
    private DateTimeField iRoundingField;
    private int iRoundingMode;

    public static final class Property extends AbstractReadableInstantFieldProperty {
        private DateTimeField iField;
        private MutableDateTime iInstant;

        Property(MutableDateTime mutableDateTime, DateTimeField dateTimeField) {
            this.iInstant = mutableDateTime;
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

        public MutableDateTime set(int i) {
            this.iInstant.setMillis(getField().set(this.iInstant.getMillis(), i));
            return this.iInstant;
        }
    }

    public MutableDateTime(long j, DateTimeZone dateTimeZone) {
        super(j, dateTimeZone);
    }

    public MutableDateTime(long j, Chronology chronology) {
        super(j, chronology);
    }

    public void setMillis(long j) {
        switch (this.iRoundingMode) {
            case 1:
                j = this.iRoundingField.roundFloor(j);
                break;
            case 2:
                j = this.iRoundingField.roundCeiling(j);
                break;
            case 3:
                j = this.iRoundingField.roundHalfFloor(j);
                break;
            case 4:
                j = this.iRoundingField.roundHalfCeiling(j);
                break;
            case 5:
                j = this.iRoundingField.roundHalfEven(j);
                break;
        }
        super.setMillis(j);
    }

    public void setChronology(Chronology chronology) {
        super.setChronology(chronology);
    }

    public void setZoneRetainFields(DateTimeZone dateTimeZone) {
        DateTimeZone zone = DateTimeUtils.getZone(dateTimeZone);
        DateTimeZone zone2 = DateTimeUtils.getZone(getZone());
        if (zone != zone2) {
            long millisKeepLocal = zone2.getMillisKeepLocal(zone, getMillis());
            setChronology(getChronology().withZone(zone));
            setMillis(millisKeepLocal);
        }
    }

    public Property property(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("The DateTimeFieldType must not be null");
        }
        DateTimeField field = dateTimeFieldType.getField(getChronology());
        if (field.isSupported()) {
            return new Property(this, field);
        }
        throw new IllegalArgumentException("Field '" + dateTimeFieldType + "' is not supported");
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError("Clone error");
        }
    }

    public String toString() {
        return ISODateTimeFormat.dateTime().print((ReadableInstant) this);
    }
}
