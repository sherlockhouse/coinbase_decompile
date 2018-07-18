package com.coinbase.android.event;

import com.coinbase.android.dashboard.DashboardBalanceUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDashboardBalanceUpdatedConnectorFactory implements Factory<DashboardBalanceUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDashboardBalanceUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DashboardBalanceUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static DashboardBalanceUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDashboardBalanceUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDashboardBalanceUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDashboardBalanceUpdatedConnectorFactory(module);
    }

    public static DashboardBalanceUpdatedConnector proxyProvidesDashboardBalanceUpdatedConnector(CoinbaseEventsModule instance) {
        return (DashboardBalanceUpdatedConnector) Preconditions.checkNotNull(instance.providesDashboardBalanceUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
