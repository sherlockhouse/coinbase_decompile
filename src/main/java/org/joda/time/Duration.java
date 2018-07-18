package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseDuration;

public final class Duration extends BaseDuration implements Serializable, ReadableDuration {
    public static final Duration ZERO = new Duration(0);

    public Duration(long j) {
        super(j);
    }

    public Duration(Object obj) {
        super(obj);
    }
}
