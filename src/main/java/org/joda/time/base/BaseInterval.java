package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadableInterval;

public abstract class BaseInterval extends AbstractInterval implements Serializable, ReadableInterval {
    private volatile Chronology iChronology;
    private volatile long iEndMillis;
    private volatile long iStartMillis;

    protected BaseInterval(long j, long j2, Chronology chronology) {
        this.iChronology = DateTimeUtils.getChronology(chronology);
        checkInterval(j, j2);
        this.iStartMillis = j;
        this.iEndMillis = j2;
    }

    public Chronology getChronology() {
        return this.iChronology;
    }

    public long getStartMillis() {
        return this.iStartMillis;
    }

    public long getEndMillis() {
        return this.iEndMillis;
    }
}
