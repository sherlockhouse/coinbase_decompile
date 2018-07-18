package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableInterval;

class ReadableIntervalConverter extends AbstractConverter implements DurationConverter, IntervalConverter, PeriodConverter {
    static final ReadableIntervalConverter INSTANCE = new ReadableIntervalConverter();

    protected ReadableIntervalConverter() {
    }

    public long getDurationMillis(Object obj) {
        return ((ReadableInterval) obj).toDurationMillis();
    }

    public void setInto(ReadWritablePeriod readWritablePeriod, Object obj, Chronology chronology) {
        ReadableInterval readableInterval = (ReadableInterval) obj;
        int[] iArr = (chronology != null ? chronology : DateTimeUtils.getIntervalChronology(readableInterval)).get(readWritablePeriod, readableInterval.getStartMillis(), readableInterval.getEndMillis());
        for (int i = 0; i < iArr.length; i++) {
            readWritablePeriod.setValue(i, iArr[i]);
        }
    }

    public Class<?> getSupportedType() {
        return ReadableInterval.class;
    }
}
