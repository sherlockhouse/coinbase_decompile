package com.coinbase.android.paymentmethods.card;

import com.coinbase.android.ApplicationScope;
import com.worldpay.cse.WPCardData;
import com.worldpay.cse.WorldpayCSE;
import com.worldpay.cse.exception.WPCSEInvalidCardData;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScope
public class WorldPayValidator {
    private static final Set<Integer> CREDIT_CARD_CVC_ERRORS = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(201)}));
    private static final Set<Integer> CREDIT_CARD_NUMBER_ERRORS = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(101), Integer.valueOf(102), Integer.valueOf(103)}));
    private static final Set<Integer> EXPIRY_MONTH_ERRORS = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(301), Integer.valueOf(302), Integer.valueOf(303), Integer.valueOf(306)}));
    private static final Set<Integer> EXPIRY_YEAR_ERRORS = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(304), Integer.valueOf(305), Integer.valueOf(306)}));
    private static final Set<Integer> NAME_IS_INVALID_ERRORS = new HashSet(Arrays.asList(new Integer[]{Integer.valueOf(401), Integer.valueOf(402)}));
    public static final String WORLDPAY_PUBLIC_KEY = "2#10001#98b30187cc2b58d4519c56cff6716f6849fb4c94a341be94ba7944e0248d70ea326ff5be1b3f87662b872675bc5e3057771e7887ee160d5ea972064862d665495f19cd946d3c8bd312fc4fe803215934057facf2ef41ba2d732b697280ba42cf6cc1e3fc9fee2a3147af27c53d36f6b1a0b4c42ee20c86d223c3400c932d26305764a9a02667598d007e387601fbb123d37d01a048bffc165c5df7ec003aac65dab7b3aeb0a13e5360ccaf6b63bc2f1783542397388df855ade0c29432856e8a4d51448c3f77add9017aea2e288ede9f347a61516394c6ddc35de66bdc56705ce0c5f004ddde56a4202eb2502592882be60738fa1e211fc28734fabbe9d76a45";

    public boolean isValidCardNumber(String cardNumber) {
        WPCardData wpCardData = new WPCardData();
        wpCardData.setCardNumber(cardNumber);
        return isValid(wpCardData, CREDIT_CARD_NUMBER_ERRORS);
    }

    public boolean isValidCvc(String cardCvc) {
        WPCardData wpCardData = new WPCardData();
        wpCardData.setCvc(cardCvc);
        return isValid(wpCardData, CREDIT_CARD_CVC_ERRORS);
    }

    public boolean isValidName(String fullName) {
        WPCardData wpCardData = new WPCardData();
        wpCardData.setCardHolderName(fullName);
        return isValid(wpCardData, NAME_IS_INVALID_ERRORS);
    }

    public boolean isValidMonth(String month) {
        WPCardData wpCardData = new WPCardData();
        wpCardData.setExpiryMonth(month);
        return isValid(wpCardData, EXPIRY_MONTH_ERRORS);
    }

    public boolean isValidYear(String year) {
        WPCardData wpCardData = new WPCardData();
        wpCardData.setExpiryYear(year);
        return isValid(wpCardData, EXPIRY_YEAR_ERRORS);
    }

    public boolean isCardNumberError(WPCSEInvalidCardData e) {
        return containsErrorCodes(e, CREDIT_CARD_NUMBER_ERRORS);
    }

    public boolean isCvcError(WPCSEInvalidCardData e) {
        return containsErrorCodes(e, CREDIT_CARD_CVC_ERRORS);
    }

    public boolean isNameError(WPCSEInvalidCardData e) {
        return containsErrorCodes(e, NAME_IS_INVALID_ERRORS);
    }

    public boolean isExpiryDateError(WPCSEInvalidCardData e) {
        return containsErrorCodes(e, EXPIRY_MONTH_ERRORS) || containsErrorCodes(e, EXPIRY_YEAR_ERRORS);
    }

    private boolean isValid(WPCardData cardData, Set<Integer> expectCodes) {
        Set codes = WorldpayCSE.validate(cardData);
        if (codes == null || codes.isEmpty() || !containsErrorCodes(codes, (Set) expectCodes)) {
            return true;
        }
        return false;
    }

    private boolean containsErrorCodes(WPCSEInvalidCardData e, Set<Integer> expectedCodes) {
        Set errorCodes = e.getErrorCodes();
        if (errorCodes == null) {
            return false;
        }
        return containsErrorCodes(errorCodes, (Set) expectedCodes);
    }

    private boolean containsErrorCodes(Set<Integer> errorCodes, Set<Integer> expectedCodes) {
        for (Integer i : expectedCodes) {
            if (errorCodes.contains(i)) {
                return true;
            }
        }
        return false;
    }
}
