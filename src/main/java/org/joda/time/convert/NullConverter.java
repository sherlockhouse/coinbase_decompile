package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.Period;
import org.joda.time.ReadWritablePeriod;

class NullConverter extends AbstractConverter implements DurationConverter, InstantConverter, IntervalConverter, PartialConverter, PeriodConverter {
    static final NullConverter INSTANCE = new NullConverter();

    protected NullConverter() {
    }

    public long getDurationMillis(Object obj) {
        return 0;
    }

    public void setInto(ReadWritablePeriod readWritablePeriod, Object obj, Chronology chronology) {
        readWritablePeriod.setPeriod((Period) null);
    }

    public Class<?> getSupportedType() {
        return null;
    }
}
