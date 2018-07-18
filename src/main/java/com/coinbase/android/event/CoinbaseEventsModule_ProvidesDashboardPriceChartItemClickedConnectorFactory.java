package com.coinbase.android.event;

import com.coinbase.android.dashboard.DashboardPriceChartItemClickedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesDashboardPriceChartItemClickedConnectorFactory implements Factory<DashboardPriceChartItemClickedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesDashboardPriceChartItemClickedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public DashboardPriceChartItemClickedConnector get() {
        return provideInstance(this.module);
    }

    public static DashboardPriceChartItemClickedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesDashboardPriceChartItemClickedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesDashboardPriceChartItemClickedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesDashboardPriceChartItemClickedConnectorFactory(module);
    }

    public static DashboardPriceChartItemClickedConnector proxyProvidesDashboardPriceChartItemClickedConnector(CoinbaseEventsModule instance) {
        return (DashboardPriceChartItemClickedConnector) Preconditions.checkNotNull(instance.providesDashboardPriceChartItemClickedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
