package com.coinbase.android.dashboard;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DashboardCurrencyPresenterModule_ProvideDashboardCurrencyPriceScreenFactory implements Factory<DashboardCurrencyScreen> {
    private final DashboardCurrencyPresenterModule module;

    public DashboardCurrencyPresenterModule_ProvideDashboardCurrencyPriceScreenFactory(DashboardCurrencyPresenterModule module) {
        this.module = module;
    }

    public DashboardCurrencyScreen get() {
        return provideInstance(this.module);
    }

    public static DashboardCurrencyScreen provideInstance(DashboardCurrencyPresenterModule module) {
        return proxyProvideDashboardCurrencyPriceScreen(module);
    }

    public static DashboardCurrencyPresenterModule_ProvideDashboardCurrencyPriceScreenFactory create(DashboardCurrencyPresenterModule module) {
        return new DashboardCurrencyPresenterModule_ProvideDashboardCurrencyPriceScreenFactory(module);
    }

    public static DashboardCurrencyScreen proxyProvideDashboardCurrencyPriceScreen(DashboardCurrencyPresenterModule instance) {
        return (DashboardCurrencyScreen) Preconditions.checkNotNull(instance.provideDashboardCurrencyPriceScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
