package com.coinbase.android.event;

import com.coinbase.android.accounts.AccountCryptoAddressUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesAccountCryptoAddressUpdatedConnectorFactory implements Factory<AccountCryptoAddressUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesAccountCryptoAddressUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public AccountCryptoAddressUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static AccountCryptoAddressUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesAccountCryptoAddressUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesAccountCryptoAddressUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesAccountCryptoAddressUpdatedConnectorFactory(module);
    }

    public static AccountCryptoAddressUpdatedConnector proxyProvidesAccountCryptoAddressUpdatedConnector(CoinbaseEventsModule instance) {
        return (AccountCryptoAddressUpdatedConnector) Preconditions.checkNotNull(instance.providesAccountCryptoAddressUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
