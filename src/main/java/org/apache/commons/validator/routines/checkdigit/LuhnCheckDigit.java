package org.apache.commons.validator.routines.checkdigit;

public final class LuhnCheckDigit extends ModulusCheckDigit {
    public static final CheckDigit LUHN_CHECK_DIGIT = new LuhnCheckDigit();
    private static final int[] POSITION_WEIGHT = new int[]{2, 1};

    public LuhnCheckDigit() {
        super(10);
    }

    protected int weightedValue(int charValue, int leftPos, int rightPos) {
        int weightedValue = charValue * POSITION_WEIGHT[rightPos % 2];
        return weightedValue > 9 ? weightedValue - 9 : weightedValue;
    }
}
