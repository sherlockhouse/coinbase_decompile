package com.coinbase.android.signin;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IntroPriceChartPresenterModule_ProvidesPriceChartScreenFactory implements Factory<IntroPriceChartScreen> {
    private final IntroPriceChartPresenterModule module;

    public IntroPriceChartPresenterModule_ProvidesPriceChartScreenFactory(IntroPriceChartPresenterModule module) {
        this.module = module;
    }

    public IntroPriceChartScreen get() {
        return provideInstance(this.module);
    }

    public static IntroPriceChartScreen provideInstance(IntroPriceChartPresenterModule module) {
        return proxyProvidesPriceChartScreen(module);
    }

    public static IntroPriceChartPresenterModule_ProvidesPriceChartScreenFactory create(IntroPriceChartPresenterModule module) {
        return new IntroPriceChartPresenterModule_ProvidesPriceChartScreenFactory(module);
    }

    public static IntroPriceChartScreen proxyProvidesPriceChartScreen(IntroPriceChartPresenterModule instance) {
        return (IntroPriceChartScreen) Preconditions.checkNotNull(instance.providesPriceChartScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
