package org.joda.time;

public interface ReadableInterval {
    Chronology getChronology();

    long getEndMillis();

    long getStartMillis();

    long toDurationMillis();
}
