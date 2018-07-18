package org.joda.time;

public abstract class DurationField implements Comparable<DurationField> {
    public abstract long add(long j, int i);

    public abstract long add(long j, long j2);

    public abstract int getDifference(long j, long j2);

    public abstract long getDifferenceAsLong(long j, long j2);

    public abstract DurationFieldType getType();

    public abstract long getUnitMillis();

    public abstract boolean isPrecise();

    public abstract boolean isSupported();
}
