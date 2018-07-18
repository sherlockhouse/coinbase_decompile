package com.worldpay.cse;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class WPCardValidator {
    WPCardValidator() {
    }

    public Set<Integer> validateCardData(WPCardData cardData) {
        Set<Integer> errorsCodes = new HashSet();
        checkErrorCode(validateCardNumber(cardData.getCardNumber()), errorsCodes);
        checkErrorCode(validateCvc(cardData.getCvc()), errorsCodes);
        checkErrorCode(validateMonth(cardData.getExpiryMonth()), errorsCodes);
        checkErrorCode(validateYear(cardData.getExpiryYear()), errorsCodes);
        checkErrorCode(validateDate(cardData.getExpiryMonth(), cardData.getExpiryYear()), errorsCodes);
        checkErrorCode(validateCardHolderName(cardData.getCardHolderName()), errorsCodes);
        return errorsCodes;
    }

    private int validateMonth(String expiryMonth) {
        int validNumber = validateValue(expiryMonth, "^[0-9]{2}$");
        if (validNumber != 0) {
            return validNumber + 300;
        }
        int exMonth = Integer.parseInt(expiryMonth);
        if (exMonth < 1 || exMonth > 12) {
            return 303;
        }
        return 0;
    }

    private int validateYear(String expiryYear) {
        int validNumber = validateValue(expiryYear, "^[0-9]{4}$");
        if (validNumber != 0) {
            return validNumber + 303;
        }
        return 0;
    }

    private int validateDate(String expiryMonth, String expiryYear) {
        if (validateMonth(expiryMonth) == 0 && validateYear(expiryYear) == 0 && !isFutureDate(Integer.parseInt(expiryMonth), Integer.parseInt(expiryYear))) {
            return 306;
        }
        return 0;
    }

    private int validateValue(String value, String pattern) {
        if (isEmpty(value) || isEmpty(value.trim())) {
            return 1;
        }
        if (evaluateRegex(value, pattern)) {
            return 0;
        }
        return 2;
    }

    private boolean isFutureDate(int expiryMonth, int expiryYear) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(expiryYear, expiryMonth - 1, 0);
        return calendar.getTime().after(today);
    }

    private int validateCardHolderName(String cardHolderName) {
        int valid = validateValue(cardHolderName, "^.{1,30}$");
        if (valid != 0) {
            return valid + 400;
        }
        return 0;
    }

    private int validateCvc(String cvc) {
        if (isEmpty(cvc) || evaluateRegex(cvc, "^[0-9]{3,4}$")) {
            return 0;
        }
        return 201;
    }

    private int validateCardNumber(String cardNumber) {
        int valid = validateValue(cardNumber, "[0-9]{12,20}");
        if (valid != 0) {
            return valid + 100;
        }
        if (validateLuhn(cardNumber)) {
            return 0;
        }
        return 103;
    }

    public boolean validateLuhn(String value) {
        int nCheck = 0;
        boolean alternate = false;
        for (int i = value.length() - 1; i >= 0; i--) {
            int nDigit = Integer.parseInt(String.valueOf(value.charAt(i)));
            if (alternate) {
                nDigit *= 2;
                if (nDigit > 9) {
                    nDigit -= 9;
                }
            }
            nCheck += nDigit;
            alternate = !alternate;
        }
        if (nCheck % 10 == 0) {
            return true;
        }
        return false;
    }

    private boolean checkErrorCode(int errorCode, Set<Integer> errorsCodes) {
        if (errorCode == 0) {
            return true;
        }
        errorsCodes.add(Integer.valueOf(errorCode));
        return false;
    }

    private boolean evaluateRegex(String data, String re) {
        return Pattern.matches(re, data);
    }

    private boolean isEmpty(String data) {
        return data == null || "".equals(data) || evaluateRegex(data, "!/[^\\s]/");
    }
}
