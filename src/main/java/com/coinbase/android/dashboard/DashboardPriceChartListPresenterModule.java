package com.coinbase.android.dashboard;

import com.coinbase.android.ControllerScope;

public class DashboardPriceChartListPresenterModule {
    private final DashboardPriceChartListScreen mScreen;

    public DashboardPriceChartListPresenterModule(DashboardPriceChartListScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public DashboardPriceChartListScreen providesDashboardPriceChartListScreen() {
        return this.mScreen;
    }
}
