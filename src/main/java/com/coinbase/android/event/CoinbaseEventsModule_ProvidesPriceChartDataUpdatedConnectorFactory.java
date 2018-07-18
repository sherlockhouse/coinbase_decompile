package com.coinbase.android.event;

import com.coinbase.android.pricechart.PriceChartDataUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPriceChartDataUpdatedConnectorFactory implements Factory<PriceChartDataUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPriceChartDataUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PriceChartDataUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static PriceChartDataUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPriceChartDataUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPriceChartDataUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPriceChartDataUpdatedConnectorFactory(module);
    }

    public static PriceChartDataUpdatedConnector proxyProvidesPriceChartDataUpdatedConnector(CoinbaseEventsModule instance) {
        return (PriceChartDataUpdatedConnector) Preconditions.checkNotNull(instance.providesPriceChartDataUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
