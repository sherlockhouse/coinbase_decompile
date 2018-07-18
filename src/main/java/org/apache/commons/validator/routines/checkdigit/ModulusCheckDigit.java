package org.apache.commons.validator.routines.checkdigit;

import java.io.Serializable;

public abstract class ModulusCheckDigit implements Serializable, CheckDigit {
    private final int modulus;

    protected abstract int weightedValue(int i, int i2, int i3) throws CheckDigitException;

    public ModulusCheckDigit(int modulus) {
        this.modulus = modulus;
    }

    public boolean isValid(String code) {
        boolean z = true;
        if (code == null || code.length() == 0) {
            return false;
        }
        try {
            if (calculateModulus(code, true) != 0) {
                z = false;
            }
            return z;
        } catch (CheckDigitException e) {
            return false;
        }
    }

    protected int calculateModulus(String code, boolean includesCheckDigit) throws CheckDigitException {
        int total = 0;
        for (int i = 0; i < code.length(); i++) {
            int leftPos = i + 1;
            int rightPos = (code.length() + (includesCheckDigit ? 0 : 1)) - i;
            total += weightedValue(toInt(code.charAt(i), leftPos, rightPos), leftPos, rightPos);
        }
        if (total != 0) {
            return total % this.modulus;
        }
        throw new CheckDigitException("Invalid code, sum is zero");
    }

    protected int toInt(char character, int leftPos, int rightPos) throws CheckDigitException {
        if (Character.isDigit(character)) {
            return Character.getNumericValue(character);
        }
        throw new CheckDigitException("Invalid Character[" + leftPos + "] = '" + character + "'");
    }
}
