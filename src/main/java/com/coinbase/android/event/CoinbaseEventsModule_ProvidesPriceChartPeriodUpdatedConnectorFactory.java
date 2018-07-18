package com.coinbase.android.event;

import com.coinbase.android.pricechart.PriceChartPeriodUpdatedConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoinbaseEventsModule_ProvidesPriceChartPeriodUpdatedConnectorFactory implements Factory<PriceChartPeriodUpdatedConnector> {
    private final CoinbaseEventsModule module;

    public CoinbaseEventsModule_ProvidesPriceChartPeriodUpdatedConnectorFactory(CoinbaseEventsModule module) {
        this.module = module;
    }

    public PriceChartPeriodUpdatedConnector get() {
        return provideInstance(this.module);
    }

    public static PriceChartPeriodUpdatedConnector provideInstance(CoinbaseEventsModule module) {
        return proxyProvidesPriceChartPeriodUpdatedConnector(module);
    }

    public static CoinbaseEventsModule_ProvidesPriceChartPeriodUpdatedConnectorFactory create(CoinbaseEventsModule module) {
        return new CoinbaseEventsModule_ProvidesPriceChartPeriodUpdatedConnectorFactory(module);
    }

    public static PriceChartPeriodUpdatedConnector proxyProvidesPriceChartPeriodUpdatedConnector(CoinbaseEventsModule instance) {
        return (PriceChartPeriodUpdatedConnector) Preconditions.checkNotNull(instance.providesPriceChartPeriodUpdatedConnector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
