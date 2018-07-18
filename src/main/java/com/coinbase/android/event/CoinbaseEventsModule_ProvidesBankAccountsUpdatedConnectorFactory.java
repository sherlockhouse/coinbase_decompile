package com.coinbase.android.event;

import com.coinbase.android.paymentmethods.BankAccountsUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesBankAccountsUpdatedConnectorFactory implements Factory<BankAccountsUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesBankAccountsUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public BankAccountsUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static BankAccountsUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesBankAccountsUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesBankAccountsUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesBankAccountsUpdatedConnectorFactory(module);
    }

    public static BankAccountsUpdatedConnector proxyProvidesBankAccountsUpdatedConnector(CoinbaseEventsModule instance) {
        return (BankAccountsUpdatedConnector) Preconditions.checkNotNull(instance.providesBankAccountsUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
