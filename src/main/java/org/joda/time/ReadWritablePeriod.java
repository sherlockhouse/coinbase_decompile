package org.joda.time;

public interface ReadWritablePeriod extends ReadablePeriod {
    void clear();

    void setDays(int i);

    void setHours(int i);

    void setMillis(int i);

    void setMinutes(int i);

    void setMonths(int i);

    void setPeriod(ReadablePeriod readablePeriod);

    void setSeconds(int i);

    void setValue(int i, int i2);

    void setWeeks(int i);

    void setYears(int i);
}
