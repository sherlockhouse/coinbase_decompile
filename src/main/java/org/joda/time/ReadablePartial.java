package org.joda.time;

public interface ReadablePartial extends Comparable<ReadablePartial> {
    int get(DateTimeFieldType dateTimeFieldType);

    Chronology getChronology();

    DateTimeField getField(int i);

    DateTimeFieldType getFieldType(int i);

    int getValue(int i);

    boolean isSupported(DateTimeFieldType dateTimeFieldType);

    int size();
}
