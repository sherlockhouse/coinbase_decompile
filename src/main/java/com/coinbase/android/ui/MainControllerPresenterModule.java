package com.coinbase.android.ui;

import com.coinbase.android.ControllerScope;

public class MainControllerPresenterModule {
    private final MainScreen mMainScreen;

    MainControllerPresenterModule(MainScreen screen) {
        this.mMainScreen = screen;
    }

    @ControllerScope
    public MainScreen providesMainScreen() {
        return this.mMainScreen;
    }
}
