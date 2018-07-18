package com.coinbase.android.buysell;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class BuyPresenterModule {
    private final ActionBarController mController;
    private final BuyScreen mScreen;

    public BuyPresenterModule(BuyScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public BuyScreen providesBuyScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
