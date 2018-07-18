package com.worldpay.cse.exception;

import java.util.Set;

public class WPCSEInvalidCardData extends WPCSEException {
    private final Set<Integer> errorCodes;

    public WPCSEInvalidCardData(Set<Integer> errorCodes) {
        super("Invalid card data");
        this.errorCodes = errorCodes;
    }

    public Set<Integer> getErrorCodes() {
        return this.errorCodes;
    }
}
