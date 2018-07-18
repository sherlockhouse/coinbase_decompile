package com.coinbase.android.event;

import com.coinbase.android.dashboard.DashboardRefreshConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDashboardRefreshConnectorFactory implements Factory<DashboardRefreshConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDashboardRefreshConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DashboardRefreshConnector get() {
        return provideInstance(this.module);
    }

    public static DashboardRefreshConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDashboardRefreshConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDashboardRefreshConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDashboardRefreshConnectorFactory(module);
    }

    public static DashboardRefreshConnector proxyProvidesDashboardRefreshConnector(CoinbaseEventsModule instance) {
        return (DashboardRefreshConnector) Preconditions.checkNotNull(instance.providesDashboardRefreshConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
