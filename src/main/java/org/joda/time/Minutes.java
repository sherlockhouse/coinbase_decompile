package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Minutes extends BaseSingleFieldPeriod {
    public static final Minutes MAX_VALUE = new Minutes(Integer.MAX_VALUE);
    public static final Minutes MIN_VALUE = new Minutes(Integer.MIN_VALUE);
    public static final Minutes ONE = new Minutes(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.minutes());
    public static final Minutes THREE = new Minutes(3);
    public static final Minutes TWO = new Minutes(2);
    public static final Minutes ZERO = new Minutes(0);

    public static Minutes minutes(int i) {
        switch (i) {
            case Integer.MIN_VALUE:
                return MIN_VALUE;
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case Integer.MAX_VALUE:
                return MAX_VALUE;
            default:
                return new Minutes(i);
        }
    }

    private Minutes(int i) {
        super(i);
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.minutes();
    }

    public PeriodType getPeriodType() {
        return PeriodType.minutes();
    }

    public String toString() {
        return "PT" + String.valueOf(getValue()) + "M";
    }
}
