package com.coinbase.android.confirmation;

public enum ItemType {
    DETAIL(0),
    FEE(1),
    TOTAL(2),
    PAYMENT_METHOD(3);
    
    private int mValue;

    private ItemType(int value) {
        this.mValue = value;
    }

    int getValue() {
        return this.mValue;
    }
}
