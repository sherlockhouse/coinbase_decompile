package com.coinbase.android.dashboard;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DashboardPriceChartListPresenterModule_ProvidesDashboardPriceChartListScreenFactory implements Factory<DashboardPriceChartListScreen> {
    private final DashboardPriceChartListPresenterModule module;

    public DashboardPriceChartListPresenterModule_ProvidesDashboardPriceChartListScreenFactory(DashboardPriceChartListPresenterModule module) {
        this.module = module;
    }

    public DashboardPriceChartListScreen get() {
        return provideInstance(this.module);
    }

    public static DashboardPriceChartListScreen provideInstance(DashboardPriceChartListPresenterModule module) {
        return proxyProvidesDashboardPriceChartListScreen(module);
    }

    public static DashboardPriceChartListPresenterModule_ProvidesDashboardPriceChartListScreenFactory create(DashboardPriceChartListPresenterModule module) {
        return new DashboardPriceChartListPresenterModule_ProvidesDashboardPriceChartListScreenFactory(module);
    }

    public static DashboardPriceChartListScreen proxyProvidesDashboardPriceChartListScreen(DashboardPriceChartListPresenterModule instance) {
        return (DashboardPriceChartListScreen) Preconditions.checkNotNull(instance.providesDashboardPriceChartListScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
