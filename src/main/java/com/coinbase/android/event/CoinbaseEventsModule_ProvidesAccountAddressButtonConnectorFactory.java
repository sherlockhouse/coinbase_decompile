package com.coinbase.android.event;

import com.coinbase.android.accounts.AccountCryptoAddressButtonConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAccountAddressButtonConnectorFactory implements Factory<AccountCryptoAddressButtonConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAccountAddressButtonConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AccountCryptoAddressButtonConnector get() {
        return provideInstance(this.module);
    }

    public static AccountCryptoAddressButtonConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAccountAddressButtonConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAccountAddressButtonConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAccountAddressButtonConnectorFactory(module);
    }

    public static AccountCryptoAddressButtonConnector proxyProvidesAccountAddressButtonConnector(CoinbaseEventsModule instance) {
        return (AccountCryptoAddressButtonConnector) Preconditions.checkNotNull(instance.providesAccountAddressButtonConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
