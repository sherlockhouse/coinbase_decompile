package com.coinbase.android.utils;

import dagger.internal.Factory;

public final class CurrencyUtils_Factory implements Factory<CurrencyUtils> {
    private static final CurrencyUtils_Factory INSTANCE = new CurrencyUtils_Factory();

    public CurrencyUtils get() {
        return provideInstance();
    }

    public static CurrencyUtils provideInstance() {
        return new CurrencyUtils();
    }

    public static CurrencyUtils_Factory create() {
        return INSTANCE;
    }

    public static CurrencyUtils newCurrencyUtils() {
        return new CurrencyUtils();
    }
}
