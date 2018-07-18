package org.joda.time;

import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Months extends BaseSingleFieldPeriod {
    public static final Months EIGHT = new Months(8);
    public static final Months ELEVEN = new Months(11);
    public static final Months FIVE = new Months(5);
    public static final Months FOUR = new Months(4);
    public static final Months MAX_VALUE = new Months(Integer.MAX_VALUE);
    public static final Months MIN_VALUE = new Months(Integer.MIN_VALUE);
    public static final Months NINE = new Months(9);
    public static final Months ONE = new Months(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.months());
    public static final Months SEVEN = new Months(7);
    public static final Months SIX = new Months(6);
    public static final Months TEN = new Months(10);
    public static final Months THREE = new Months(3);
    public static final Months TWELVE = new Months(12);
    public static final Months TWO = new Months(2);
    public static final Months ZERO = new Months(0);

    public static Months months(int i) {
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
            case 9:
                return NINE;
            case 10:
                return TEN;
            case 11:
                return ELEVEN;
            case 12:
                return TWELVE;
            case Integer.MAX_VALUE:
                return MAX_VALUE;
            default:
                return new Months(i);
        }
    }

    private Months(int i) {
        super(i);
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.months();
    }

    public PeriodType getPeriodType() {
        return PeriodType.months();
    }

    public String toString() {
        return "P" + String.valueOf(getValue()) + "M";
    }
}
