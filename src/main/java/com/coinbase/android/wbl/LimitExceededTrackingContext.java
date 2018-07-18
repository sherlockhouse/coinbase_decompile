package com.coinbase.android.wbl;

public enum LimitExceededTrackingContext {
    SEND(0),
    SELL(1),
    WITHDRAW(2),
    UNKNOWN(3);
    
    private final int mValue;

    private LimitExceededTrackingContext(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static LimitExceededTrackingContext from(int value) {
        for (LimitExceededTrackingContext context : values()) {
            if (context.getValue() == value) {
                return context;
            }
        }
        return UNKNOWN;
    }
}
