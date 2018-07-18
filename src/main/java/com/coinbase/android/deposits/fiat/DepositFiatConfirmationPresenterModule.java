package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class DepositFiatConfirmationPresenterModule {
    private final ActionBarController mController;
    private final DepositFiatConfirmationScreen mDepositFiatConfirmationScreen;

    public DepositFiatConfirmationPresenterModule(DepositFiatConfirmationScreen depositFiatConfirmationScreen, ActionBarController controller) {
        this.mDepositFiatConfirmationScreen = depositFiatConfirmationScreen;
        this.mController = controller;
    }

    @ControllerScope
    public DepositFiatConfirmationScreen providesDepositFiatConfirmationScreen() {
        return this.mDepositFiatConfirmationScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
