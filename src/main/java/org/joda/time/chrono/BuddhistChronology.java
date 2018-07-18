package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology.Fields;
import org.joda.time.field.DelegatedDateTimeField;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.OffsetDateTimeField;
import org.joda.time.field.RemainderDateTimeField;
import org.joda.time.field.SkipUndoDateTimeField;

public final class BuddhistChronology extends AssembledChronology {
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("BE");
    private static final BuddhistChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final Map<DateTimeZone, BuddhistChronology> cCache = new HashMap();

    public static synchronized BuddhistChronology getInstance(DateTimeZone dateTimeZone) {
        BuddhistChronology buddhistChronology;
        synchronized (BuddhistChronology.class) {
            if (dateTimeZone == null) {
                dateTimeZone = DateTimeZone.getDefault();
            }
            synchronized (cCache) {
                buddhistChronology = (BuddhistChronology) cCache.get(dateTimeZone);
                if (buddhistChronology == null) {
                    Chronology buddhistChronology2 = new BuddhistChronology(GJChronology.getInstance(dateTimeZone, null), null);
                    BuddhistChronology buddhistChronology3 = new BuddhistChronology(LimitChronology.getInstance(buddhistChronology2, new DateTime(1, 1, 1, 0, 0, 0, 0, buddhistChronology2), null), "");
                    cCache.put(dateTimeZone, buddhistChronology3);
                    buddhistChronology = buddhistChronology3;
                }
            }
        }
        return buddhistChronology;
    }

    private BuddhistChronology(Chronology chronology, Object obj) {
        super(chronology, obj);
    }

    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BuddhistChronology)) {
            return false;
        }
        return getZone().equals(((BuddhistChronology) obj).getZone());
    }

    public int hashCode() {
        return ("Buddhist".hashCode() * 11) + getZone().hashCode();
    }

    public String toString() {
        String str = "BuddhistChronology";
        DateTimeZone zone = getZone();
        if (zone != null) {
            return str + '[' + zone.getID() + ']';
        }
        return str;
    }

    protected void assemble(Fields fields) {
        if (getParam() == null) {
            fields.year = new OffsetDateTimeField(new SkipUndoDateTimeField(this, fields.year), 543);
            DateTimeField dateTimeField = fields.yearOfEra;
            fields.yearOfEra = new DelegatedDateTimeField(fields.year, DateTimeFieldType.yearOfEra());
            fields.weekyear = new OffsetDateTimeField(new SkipUndoDateTimeField(this, fields.weekyear), 543);
            fields.centuryOfEra = new DividedDateTimeField(new OffsetDateTimeField(fields.yearOfEra, 99), DateTimeFieldType.centuryOfEra(), 100);
            fields.yearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField((DividedDateTimeField) fields.centuryOfEra), DateTimeFieldType.yearOfCentury(), 1);
            fields.weekyearOfCentury = new OffsetDateTimeField(new RemainderDateTimeField(fields.weekyear, DateTimeFieldType.weekyearOfCentury(), 100), DateTimeFieldType.weekyearOfCentury(), 1);
            fields.era = ERA_FIELD;
        }
    }
}
