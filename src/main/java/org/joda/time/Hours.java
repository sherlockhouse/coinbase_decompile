package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Hours extends BaseSingleFieldPeriod {
    public static final Hours EIGHT = new Hours(8);
    public static final Hours FIVE = new Hours(5);
    public static final Hours FOUR = new Hours(4);
    public static final Hours MAX_VALUE = new Hours(Integer.MAX_VALUE);
    public static final Hours MIN_VALUE = new Hours(Integer.MIN_VALUE);
    public static final Hours ONE = new Hours(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.hours());
    public static final Hours SEVEN = new Hours(7);
    public static final Hours SIX = new Hours(6);
    public static final Hours THREE = new Hours(3);
    public static final Hours TWO = new Hours(2);
    public static final Hours ZERO = new Hours(0);

    public static Hours hours(int i) {
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
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
            case Integer.MAX_VALUE:
                return MAX_VALUE;
            default:
                return new Hours(i);
        }
    }

    private Hours(int i) {
        super(i);
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.hours();
    }

    public PeriodType getPeriodType() {
        return PeriodType.hours();
    }

    public String toString() {
        return "PT" + String.valueOf(getValue()) + "H";
    }
}
