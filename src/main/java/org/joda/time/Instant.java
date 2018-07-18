package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;

public final class Instant extends AbstractInstant implements Serializable, ReadableInstant {
    private final long iMillis;

    public Instant() {
        this.iMillis = DateTimeUtils.currentTimeMillis();
    }

    public Instant(long j) {
        this.iMillis = j;
    }

    public Instant(Object obj) {
        this.iMillis = ConverterManager.getInstance().getInstantConverter(obj).getInstantMillis(obj, ISOChronology.getInstanceUTC());
    }

    public Instant toInstant() {
        return this;
    }

    public long getMillis() {
        return this.iMillis;
    }

    public Chronology getChronology() {
        return ISOChronology.getInstanceUTC();
    }

    public DateTime toDateTime() {
        return new DateTime(getMillis(), ISOChronology.getInstance());
    }

    public MutableDateTime toMutableDateTime() {
        return new MutableDateTime(getMillis(), ISOChronology.getInstance());
    }
}
