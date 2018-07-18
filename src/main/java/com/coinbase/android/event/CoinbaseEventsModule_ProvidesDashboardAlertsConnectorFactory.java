package com.coinbase.android.event;

import com.coinbase.android.dashboard.DashboardAlertsConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDashboardAlertsConnectorFactory implements Factory<DashboardAlertsConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDashboardAlertsConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DashboardAlertsConnector get() {
        return provideInstance(this.module);
    }

    public static DashboardAlertsConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDashboardAlertsConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDashboardAlertsConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDashboardAlertsConnectorFactory(module);
    }

    public static DashboardAlertsConnector proxyProvidesDashboardAlertsConnector(CoinbaseEventsModule instance) {
        return (DashboardAlertsConnector) Preconditions.checkNotNull(instance.providesDashboardAlertsConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
