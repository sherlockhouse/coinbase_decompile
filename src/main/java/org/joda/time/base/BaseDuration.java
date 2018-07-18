package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.ReadableDuration;
import org.joda.time.convert.ConverterManager;

public abstract class BaseDuration extends AbstractDuration implements Serializable, ReadableDuration {
    private volatile long iMillis;

    protected BaseDuration(long j) {
        this.iMillis = j;
    }

    protected BaseDuration(Object obj) {
        this.iMillis = ConverterManager.getInstance().getDurationConverter(obj).getDurationMillis(obj);
    }

    public long getMillis() {
        return this.iMillis;
    }
}
