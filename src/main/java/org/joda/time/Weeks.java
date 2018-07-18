package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Weeks extends BaseSingleFieldPeriod {
    public static final Weeks MAX_VALUE = new Weeks(Integer.MAX_VALUE);
    public static final Weeks MIN_VALUE = new Weeks(Integer.MIN_VALUE);
    public static final Weeks ONE = new Weeks(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.weeks());
    public static final Weeks THREE = new Weeks(3);
    public static final Weeks TWO = new Weeks(2);
    public static final Weeks ZERO = new Weeks(0);

    public static Weeks weeks(int i) {
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
                return new Weeks(i);
        }
    }

    private Weeks(int i) {
        super(i);
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.weeks();
    }

    public PeriodType getPeriodType() {
        return PeriodType.weeks();
    }

    public String toString() {
        return "P" + String.valueOf(getValue()) + "W";
    }
}
