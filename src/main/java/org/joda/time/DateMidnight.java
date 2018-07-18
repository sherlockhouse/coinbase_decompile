package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseDateTime;

@Deprecated
public final class DateMidnight extends BaseDateTime implements Serializable, ReadableDateTime {
    public DateMidnight(long j) {
        super(j);
    }

    public DateMidnight(int i, int i2, int i3) {
        super(i, i2, i3, 0, 0, 0, 0);
    }

    public DateMidnight(int i, int i2, int i3, Chronology chronology) {
        super(i, i2, i3, 0, 0, 0, 0, chronology);
    }

    protected long checkInstant(long j, Chronology chronology) {
        return chronology.dayOfMonth().roundFloor(j);
    }

    public Property year() {
        return new Property(this, getChronology().year());
    }

    public Property monthOfYear() {
        return new Property(this, getChronology().monthOfYear());
    }

    public Property dayOfMonth() {
        return new Property(this, getChronology().dayOfMonth());
    }
}
