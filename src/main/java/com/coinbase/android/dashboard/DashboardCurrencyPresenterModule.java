package com.coinbase.android.dashboard;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class DashboardCurrencyPresenterModule {
    private final ActionBarController mController;
    private final DashboardCurrencyScreen mScreen;

    public DashboardCurrencyPresenterModule(DashboardCurrencyScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public DashboardCurrencyScreen provideDashboardCurrencyPriceScreen() {
        return this.mScreen;
    }

    @ControllerScope
    ActionBarController providesActionBarController() {
        return this.mController;
    }
}
