package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BasePeriod;

public final class Period extends BasePeriod implements Serializable, ReadablePeriod {
    public static final Period ZERO = new Period();

    public Period() {
        super(0, null, null);
    }

    public Period(long j) {
        super(j);
    }

    public Period(Object obj) {
        super(obj, null, null);
    }
}
