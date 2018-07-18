package com.coinbase.android.event;

import com.coinbase.android.transactions.TransactionDetailButtonConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesTransactionDetailButtonConnectorFactory implements Factory<TransactionDetailButtonConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesTransactionDetailButtonConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public TransactionDetailButtonConnector get() {
        return provideInstance(this.module);
    }

    public static TransactionDetailButtonConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesTransactionDetailButtonConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesTransactionDetailButtonConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesTransactionDetailButtonConnectorFactory(module);
    }

    public static TransactionDetailButtonConnector proxyProvidesTransactionDetailButtonConnector(CoinbaseEventsModule instance) {
        return (TransactionDetailButtonConnector) Preconditions.checkNotNull(instance.providesTransactionDetailButtonConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
