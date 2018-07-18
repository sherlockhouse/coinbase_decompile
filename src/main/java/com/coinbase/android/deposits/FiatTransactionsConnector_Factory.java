package com.coinbase.android.deposits;

import dagger.internal.Factory;

public final class FiatTransactionsConnector_Factory implements Factory<FiatTransactionsConnector> {
    private static final FiatTransactionsConnector_Factory INSTANCE = new FiatTransactionsConnector_Factory();

    public FiatTransactionsConnector get() {
        return provideInstance();
    }

    public static FiatTransactionsConnector provideInstance() {
        return new FiatTransactionsConnector();
    }

    public static FiatTransactionsConnector_Factory create() {
        return INSTANCE;
    }

    public static FiatTransactionsConnector newFiatTransactionsConnector() {
        return new FiatTransactionsConnector();
    }
}
