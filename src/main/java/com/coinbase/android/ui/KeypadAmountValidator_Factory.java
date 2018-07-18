package com.coinbase.android.ui;

import dagger.internal.Factory;

public final class KeypadAmountValidator_Factory implements Factory<KeypadAmountValidator> {
    private static final KeypadAmountValidator_Factory INSTANCE = new KeypadAmountValidator_Factory();

    public KeypadAmountValidator get() {
        return provideInstance();
    }

    public static KeypadAmountValidator provideInstance() {
        return new KeypadAmountValidator();
    }

    public static KeypadAmountValidator_Factory create() {
        return INSTANCE;
    }

    public static KeypadAmountValidator newKeypadAmountValidator() {
        return new KeypadAmountValidator();
    }
}
