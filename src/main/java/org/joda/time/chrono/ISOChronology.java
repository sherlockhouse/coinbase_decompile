package org.joda.time.chrono;

import java.util.HashMap;
import java.util.Map;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology.Fields;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.RemainderDateTimeField;

public final class ISOChronology extends AssembledChronology {
    private static final ISOChronology INSTANCE_UTC = new ISOChronology(GregorianChronology.getInstanceUTC());
    private static final Map<DateTimeZone, ISOChronology> cCache = new HashMap();
    private static final ISOChronology[] cFastCache = new ISOChronology[64];

    static {
        cCache.put(DateTimeZone.UTC, INSTANCE_UTC);
    }

    public static ISOChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static ISOChronology getInstance() {
        return getInstance(DateTimeZone.getDefault());
    }

    public static ISOChronology getInstance(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        int identityHashCode = System.identityHashCode(dateTimeZone) & 63;
        ISOChronology iSOChronology = cFastCache[identityHashCode];
        if (iSOChronology == null || iSOChronology.getZone() != dateTimeZone) {
            synchronized (cCache) {
                iSOChronology = (ISOChronology) cCache.get(dateTimeZone);
                if (iSOChronology == null) {
                    iSOChronology = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, dateTimeZone));
                    cCache.put(dateTimeZone, iSOChronology);
                }
            }
            cFastCache[identityHashCode] = iSOChronology;
        }
        return iSOChronology;
    }

    private ISOChronology(Chronology chronology) {
        super(chronology, null);
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

    public String toString() {
        String str = "ISOChronology";
        DateTimeZone zone = getZone();
        if (zone != null) {
            return str + '[' + zone.getID() + ']';
        }
        return str;
    }

    protected void assemble(Fields fields) {
        if (getBase().getZone() == DateTimeZone.UTC) {
            fields.centuryOfEra = new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.centuryOfEra(), 100);
            fields.yearOfCentury = new RemainderDateTimeField((DividedDateTimeField) fields.centuryOfEra, DateTimeFieldType.yearOfCentury());
            fields.weekyearOfCentury = new RemainderDateTimeField((DividedDateTimeField) fields.centuryOfEra, DateTimeFieldType.weekyearOfCentury());
            fields.centuries = fields.centuryOfEra.getDurationField();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ISOChronology)) {
            return false;
        }
        return getZone().equals(((ISOChronology) obj).getZone());
    }

    public int hashCode() {
        return ("ISO".hashCode() * 11) + getZone().hashCode();
    }
}
