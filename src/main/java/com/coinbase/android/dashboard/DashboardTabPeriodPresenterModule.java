package com.coinbase.android.dashboard;

import com.coinbase.android.ControllerScope;

public class DashboardTabPeriodPresenterModule {
    private final DashboardTabPeriodScreen mScreen;

    public DashboardTabPeriodPresenterModule(DashboardTabPeriodScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public DashboardTabPeriodScreen providesDashboardTabPeriodScreen() {
        return this.mScreen;
    }
}
