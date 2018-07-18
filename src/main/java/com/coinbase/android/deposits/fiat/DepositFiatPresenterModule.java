package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class DepositFiatPresenterModule {
    private final ActionBarController mController;
    private final DepositFiatScreen mDepositFiatScreen;

    public DepositFiatPresenterModule(DepositFiatScreen depositFiatScreen, ActionBarController controller) {
        this.mDepositFiatScreen = depositFiatScreen;
        this.mController = controller;
    }

    @ControllerScope
    public DepositFiatScreen providesDepositFiatScreen() {
        return this.mDepositFiatScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
