package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseInterval;

public final class Interval extends BaseInterval implements Serializable, ReadableInterval {
    public Interval(long j, long j2) {
        super(j, j2, null);
    }
}
