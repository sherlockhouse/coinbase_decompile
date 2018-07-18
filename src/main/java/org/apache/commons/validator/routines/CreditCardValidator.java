package org.apache.commons.validator.routines;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

public class CreditCardValidator implements Serializable {
    public static final CodeValidator AMEX_VALIDATOR = new CodeValidator("^(3[47]\\d{13})$", LUHN_VALIDATOR);
    public static final CodeValidator DINERS_VALIDATOR = new CodeValidator("^(30[0-5]\\d{11}|3095\\d{10}|36\\d{12}|3[8-9]\\d{12})$", LUHN_VALIDATOR);
    private static final RegexValidator DISCOVER_REGEX = new RegexValidator(new String[]{"^(6011\\d{12})$", "^(64[4-9]\\d{13})$", "^(65\\d{14})$"});
    public static final CodeValidator DISCOVER_VALIDATOR = new CodeValidator(DISCOVER_REGEX, LUHN_VALIDATOR);
    private static final CheckDigit LUHN_VALIDATOR = LuhnCheckDigit.LUHN_CHECK_DIGIT;
    private static final RegexValidator MASTERCARD_REGEX = new RegexValidator(new String[]{"^(5[1-5]\\d{14})$", "^(2221\\d{12})$", "^(222[2-9]\\d{12})$", "^(22[3-9]\\d{13})$", "^(2[3-6]\\d{14})$", "^(27[01]\\d{13})$", "^(2720\\d{12})$"});
    public static final CodeValidator MASTERCARD_VALIDATOR = new CodeValidator(MASTERCARD_REGEX, LUHN_VALIDATOR);
    @Deprecated
    public static final CodeValidator MASTERCARD_VALIDATOR_PRE_OCT2016 = new CodeValidator("^(5[1-5]\\d{14})$", LUHN_VALIDATOR);
    public static final CodeValidator VISA_VALIDATOR = new CodeValidator("^(4)(\\d{12}|\\d{15})$", LUHN_VALIDATOR);
    public static final CodeValidator VPAY_VALIDATOR = new CodeValidator("^(4)(\\d{12,18})$", LUHN_VALIDATOR);
    private final List<CodeValidator> cardTypes;

    public CreditCardValidator() {
        this(15);
    }

    public CreditCardValidator(long options) {
        this.cardTypes = new ArrayList();
        if (isOn(options, 2)) {
            this.cardTypes.add(VISA_VALIDATOR);
        }
        if (isOn(options, 32)) {
            this.cardTypes.add(VPAY_VALIDATOR);
        }
        if (isOn(options, 1)) {
            this.cardTypes.add(AMEX_VALIDATOR);
        }
        if (isOn(options, 4)) {
            this.cardTypes.add(MASTERCARD_VALIDATOR);
        }
        if (isOn(options, 64)) {
            this.cardTypes.add(MASTERCARD_VALIDATOR_PRE_OCT2016);
        }
        if (isOn(options, 8)) {
            this.cardTypes.add(DISCOVER_VALIDATOR);
        }
        if (isOn(options, 16)) {
            this.cardTypes.add(DINERS_VALIDATOR);
        }
    }

    public boolean isValid(String card) {
        if (card == null || card.length() == 0) {
            return false;
        }
        for (CodeValidator cardType : this.cardTypes) {
            if (cardType.isValid(card)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOn(long options, long flag) {
        return (options & flag) > 0;
    }
}
