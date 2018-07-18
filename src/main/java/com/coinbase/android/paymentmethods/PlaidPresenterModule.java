package com.coinbase.android.paymentmethods;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class PlaidPresenterModule {
    private final PlaidScreen mScreen;

    public PlaidPresenterModule(PlaidScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public PlaidScreen providesPlaidScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mScreen.getController();
    }
}
