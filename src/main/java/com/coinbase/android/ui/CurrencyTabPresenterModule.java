package com.coinbase.android.ui;

import com.coinbase.android.ControllerScope;

public class CurrencyTabPresenterModule {
    private final CurrencyTabScreen mScreen;

    public CurrencyTabPresenterModule(CurrencyTabScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public CurrencyTabScreen providesCurrencyTabScreen() {
        return this.mScreen;
    }
}
