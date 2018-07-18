package com.coinbase.android.pricechart;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PriceChartPresenterModule_ProvidesPriceChartLayoutScreenFactory implements Factory<PriceChartScreen> {
    private final PriceChartPresenterModule module;

    public PriceChartPresenterModule_ProvidesPriceChartLayoutScreenFactory(PriceChartPresenterModule module) {
        this.module = module;
    }

    public PriceChartScreen get() {
        return provideInstance(this.module);
    }

    public static PriceChartScreen provideInstance(PriceChartPresenterModule module) {
        return proxyProvidesPriceChartLayoutScreen(module);
    }

    public static PriceChartPresenterModule_ProvidesPriceChartLayoutScreenFactory create(PriceChartPresenterModule module) {
        return new PriceChartPresenterModule_ProvidesPriceChartLayoutScreenFactory(module);
    }

    public static PriceChartScreen proxyProvidesPriceChartLayoutScreen(PriceChartPresenterModule instance) {
        return (PriceChartScreen) Preconditions.checkNotNull(instance.providesPriceChartLayoutScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
