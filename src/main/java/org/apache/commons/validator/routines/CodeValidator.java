package org.apache.commons.validator.routines;

import java.io.Serializable;
import org.apache.commons.validator.routines.checkdigit.CheckDigit;

public final class CodeValidator implements Serializable {
    private final CheckDigit checkdigit;
    private final int maxLength;
    private final int minLength;
    private final RegexValidator regexValidator;

    public CodeValidator(String regex, CheckDigit checkdigit) {
        this(regex, -1, -1, checkdigit);
    }

    public CodeValidator(String regex, int minLength, int maxLength, CheckDigit checkdigit) {
        if (regex == null || regex.length() <= 0) {
            this.regexValidator = null;
        } else {
            this.regexValidator = new RegexValidator(regex);
        }
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.checkdigit = checkdigit;
    }

    public CodeValidator(RegexValidator regexValidator, CheckDigit checkdigit) {
        this(regexValidator, -1, -1, checkdigit);
    }

    public CodeValidator(RegexValidator regexValidator, int minLength, int maxLength, CheckDigit checkdigit) {
        this.regexValidator = regexValidator;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.checkdigit = checkdigit;
    }

    public boolean isValid(String input) {
        return validate(input) != null;
    }

    public Object validate(String input) {
        if (input == null) {
            return null;
        }
        Object code = input.trim();
        if (code.length() == 0) {
            return null;
        }
        if (this.regexValidator != null) {
            code = this.regexValidator.validate(code);
            if (code == null) {
                return null;
            }
        }
        if ((this.minLength >= 0 && code.length() < this.minLength) || (this.maxLength >= 0 && code.length() > this.maxLength)) {
            return null;
        }
        if (this.checkdigit == null || this.checkdigit.isValid(code)) {
            return code;
        }
        return null;
    }
}
