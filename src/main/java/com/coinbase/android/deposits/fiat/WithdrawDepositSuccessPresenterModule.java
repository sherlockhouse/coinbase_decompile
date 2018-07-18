package com.coinbase.android.deposits.fiat;

import com.coinbase.android.ControllerScope;
import com.coinbase.android.ui.ActionBarController;

public class WithdrawDepositSuccessPresenterModule {
    private final ActionBarController mController;
    private final WithdrawDepositSuccessScreen mScreen;

    public WithdrawDepositSuccessPresenterModule(ActionBarController controller, WithdrawDepositSuccessScreen screen) {
        this.mController = controller;
        this.mScreen = screen;
    }

    @ControllerScope
    public WithdrawDepositSuccessScreen providesWithdrawDepositSuccessScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
