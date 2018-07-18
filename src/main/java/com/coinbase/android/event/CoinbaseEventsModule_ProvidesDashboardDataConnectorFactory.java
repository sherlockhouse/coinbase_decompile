package com.coinbase.android.event;

import com.coinbase.android.dashboard.DashboardVerificationConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDashboardDataConnectorFactory implements Factory<DashboardVerificationConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDashboardDataConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DashboardVerificationConnector get() {
        return provideInstance(this.module);
    }

    public static DashboardVerificationConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDashboardDataConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDashboardDataConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDashboardDataConnectorFactory(module);
    }

    public static DashboardVerificationConnector proxyProvidesDashboardDataConnector(CoinbaseEventsModule instance) {
        return (DashboardVerificationConnector) Preconditions.checkNotNull(instance.providesDashboardDataConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
