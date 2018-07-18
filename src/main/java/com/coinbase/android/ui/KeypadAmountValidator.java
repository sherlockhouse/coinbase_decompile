package com.coinbase.android.ui;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.NumericKeypadConnector.KeypadType;

@ControllerScope
public class KeypadAmountValidator {
    private static final int MAX_WHOLE_NUMBER_LENGTH = 7;

    public boolean validateAmount(KeypadType keypadType, char keyStroke, String text, int maxFractionDigits) {
        if (text == null || maxFractionDigits < 0) {
            return false;
        }
        switch (keypadType) {
            case DIGIT:
                return validateNumericType(keyStroke, text, maxFractionDigits);
            case DOT:
                return validateDecimalPoint(text);
            case DELETE:
            case LONG_DELETE:
                return validateDelete(text);
            default:
                return false;
        }
    }

    private boolean validateNumericType(char keyStroke, String text, int maxFractionDigits) {
        if (!Character.isDigit(keyStroke)) {
            return false;
        }
        if (isDotPresent(text)) {
            if (text.length() - text.indexOf(46) > maxFractionDigits) {
                return false;
            }
        } else if (text.length() >= 7) {
            return false;
        } else {
            if (keyStroke == NumericKeypadConnector.ZERO && text.length() > 0 && parsedDoubleValue(text) == 0.0d) {
                return false;
            }
        }
        return true;
    }

    private boolean validateDecimalPoint(String text) {
        if (text == null || isDotPresent(text)) {
            return false;
        }
        return true;
    }

    private boolean validateDelete(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        return true;
    }

    private boolean isDotPresent(String text) {
        if (text != null && text.indexOf(46) >= 0) {
            return true;
        }
        return false;
    }

    private double parsedDoubleValue(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0d;
        }
    }
}
