package com.coinbase.android.dashboard;

import com.coinbase.android.ControllerScope;

@ControllerScope
public interface DashboardCurrencyControllerSubcomponent {
    void inject(DashboardCurrencyController dashboardCurrencyController);
}
