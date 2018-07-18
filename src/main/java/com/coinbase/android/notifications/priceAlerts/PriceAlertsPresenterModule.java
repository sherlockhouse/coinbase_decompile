package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.android.ControllerScope;

public class PriceAlertsPresenterModule {
    private final PriceAlertsScreen mScreen;

    public PriceAlertsPresenterModule(PriceAlertsScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    PriceAlertsScreen providesPriceAlertsScreen() {
        return this.mScreen;
    }
}
