package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Seconds extends BaseSingleFieldPeriod {
    public static final Seconds MAX_VALUE = new Seconds(Integer.MAX_VALUE);
    public static final Seconds MIN_VALUE = new Seconds(Integer.MIN_VALUE);
    public static final Seconds ONE = new Seconds(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.seconds());
    public static final Seconds THREE = new Seconds(3);
    public static final Seconds TWO = new Seconds(2);
    public static final Seconds ZERO = new Seconds(0);

    public static Seconds seconds(int i) {
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
                return new Seconds(i);
        }
    }

    private Seconds(int i) {
        super(i);
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.seconds();
    }

    public PeriodType getPeriodType() {
        return PeriodType.seconds();
    }

    public String toString() {
        return "PT" + String.valueOf(getValue()) + "S";
    }
}
