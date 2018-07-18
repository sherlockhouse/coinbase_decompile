package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableDateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

public abstract class BaseDateTime extends AbstractDateTime implements Serializable, ReadableDateTime {
    private volatile Chronology iChronology;
    private volatile long iMillis;

    public BaseDateTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public BaseDateTime(long j) {
        this(j, ISOChronology.getInstance());
    }

    public BaseDateTime(long j, DateTimeZone dateTimeZone) {
        this(j, ISOChronology.getInstance(dateTimeZone));
    }

    public BaseDateTime(long j, Chronology chronology) {
        this.iChronology = checkChronology(chronology);
        this.iMillis = checkInstant(j, this.iChronology);
    }

    public BaseDateTime(Object obj, DateTimeZone dateTimeZone) {
        InstantConverter instantConverter = ConverterManager.getInstance().getInstantConverter(obj);
        Chronology checkChronology = checkChronology(instantConverter.getChronology(obj, dateTimeZone));
        this.iChronology = checkChronology;
        this.iMillis = checkInstant(instantConverter.getInstantMillis(obj, checkChronology), checkChronology);
    }

    public BaseDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this(i, i2, i3, i4, i5, i6, i7, ISOChronology.getInstance());
    }

    public BaseDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, Chronology chronology) {
        this.iChronology = checkChronology(chronology);
        this.iMillis = checkInstant(this.iChronology.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7), this.iChronology);
    }

    protected Chronology checkChronology(Chronology chronology) {
        return DateTimeUtils.getChronology(chronology);
    }

    protected long checkInstant(long j, Chronology chronology) {
        return j;
    }

    public long getMillis() {
        return this.iMillis;
    }

    public Chronology getChronology() {
        return this.iChronology;
    }

    protected void setMillis(long j) {
        this.iMillis = checkInstant(j, this.iChronology);
    }

    protected void setChronology(Chronology chronology) {
        this.iChronology = checkChronology(chronology);
    }
}
