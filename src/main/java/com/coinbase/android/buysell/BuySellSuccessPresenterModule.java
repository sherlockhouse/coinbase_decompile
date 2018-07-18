package com.coinbase.android.buysell;

import com.coinbase.android.ControllerScope;

public class BuySellSuccessPresenterModule {
    private final BuySellSuccessScreen mScreen;

    public BuySellSuccessPresenterModule(BuySellSuccessScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public BuySellSuccessScreen providesBuySellSuccessScreen() {
        return this.mScreen;
    }
}
