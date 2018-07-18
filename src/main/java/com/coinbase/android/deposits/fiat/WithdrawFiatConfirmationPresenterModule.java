package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class WithdrawFiatConfirmationPresenterModule {
    private final ActionBarController mController;
    private final WithdrawFiatConfirmationScreen mWithdrawFiatConfirmationScreen;

    public WithdrawFiatConfirmationPresenterModule(WithdrawFiatConfirmationScreen withdrawFiatConfirmationScreen, ActionBarController controller) {
        this.mWithdrawFiatConfirmationScreen = withdrawFiatConfirmationScreen;
        this.mController = controller;
    }

    @ControllerScope
    public WithdrawFiatConfirmationScreen providesWithdrawFiatConfirmationScreen() {
        return this.mWithdrawFiatConfirmationScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
