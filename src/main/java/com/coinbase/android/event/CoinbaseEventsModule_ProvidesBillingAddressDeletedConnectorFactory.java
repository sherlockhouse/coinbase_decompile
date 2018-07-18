package com.coinbase.android.event;

import com.coinbase.android.billing.BillingAddressDeletedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesBillingAddressDeletedConnectorFactory implements Factory<BillingAddressDeletedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesBillingAddressDeletedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public BillingAddressDeletedConnector get() {
        return provideInstance(this.module);
    }

    public static BillingAddressDeletedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesBillingAddressDeletedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesBillingAddressDeletedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesBillingAddressDeletedConnectorFactory(module);
    }

    public static BillingAddressDeletedConnector proxyProvidesBillingAddressDeletedConnector(CoinbaseEventsModule instance) {
        return (BillingAddressDeletedConnector) Preconditions.checkNotNull(instance.providesBillingAddressDeletedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
