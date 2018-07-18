package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;

public class ConnectedAccountsPresenterModule {
    private final ConnectedAccountsScreen mScreen;

    public ConnectedAccountsPresenterModule(ConnectedAccountsScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public ConnectedAccountsScreen providesConnectedAccountsScreen() {
        return this.mScreen;
    }
}
