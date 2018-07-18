package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Years extends BaseSingleFieldPeriod {
    public static final Years MAX_VALUE = new Years(Integer.MAX_VALUE);
    public static final Years MIN_VALUE = new Years(Integer.MIN_VALUE);
    public static final Years ONE = new Years(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.years());
    public static final Years THREE = new Years(3);
    public static final Years TWO = new Years(2);
    public static final Years ZERO = new Years(0);

    public static Years years(int i) {
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
                return new Years(i);
        }
    }

    private Years(int i) {
        super(i);
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.years();
    }

    public PeriodType getPeriodType() {
        return PeriodType.years();
    }

    public String toString() {
        return "P" + String.valueOf(getValue()) + "Y";
    }
}
