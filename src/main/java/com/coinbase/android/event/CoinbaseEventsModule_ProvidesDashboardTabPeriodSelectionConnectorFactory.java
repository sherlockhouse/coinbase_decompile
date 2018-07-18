package com.coinbase.android.event;

import com.coinbase.android.dashboard.DashboardTabPeriodSelectionConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDashboardTabPeriodSelectionConnectorFactory implements Factory<DashboardTabPeriodSelectionConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDashboardTabPeriodSelectionConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DashboardTabPeriodSelectionConnector get() {
        return provideInstance(this.module);
    }

    public static DashboardTabPeriodSelectionConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDashboardTabPeriodSelectionConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDashboardTabPeriodSelectionConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDashboardTabPeriodSelectionConnectorFactory(module);
    }

    public static DashboardTabPeriodSelectionConnector proxyProvidesDashboardTabPeriodSelectionConnector(CoinbaseEventsModule instance) {
        return (DashboardTabPeriodSelectionConnector) Preconditions.checkNotNull(instance.providesDashboardTabPeriodSelectionConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
