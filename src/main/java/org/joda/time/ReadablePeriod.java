package org.joda.time;

public interface ReadablePeriod {
    int get(DurationFieldType durationFieldType);

    DurationFieldType getFieldType(int i);

    PeriodType getPeriodType();

    int getValue(int i);

    int size();
}
