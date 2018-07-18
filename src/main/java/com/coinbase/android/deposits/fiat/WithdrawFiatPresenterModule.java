package com.coinbase.android.deposits.fiat;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import com.coinbase.android.wbl.LimitExceededTrackingContext;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;

public class WithdrawFiatPresenterModule {
    private final AvailableBalanceAppBarScreen mAvailableBalanceAppBarScreen;
    private final ActionBarController mController;
    private final WithdrawFiatScreen mWithdrawFiatScreen;

    public WithdrawFiatPresenterModule(WithdrawFiatScreen withdrawFiatScreen, ActionBarController controller, AvailableBalanceAppBarScreen availableBalanceAppBarScreen) {
        this.mWithdrawFiatScreen = withdrawFiatScreen;
        this.mController = controller;
        this.mAvailableBalanceAppBarScreen = availableBalanceAppBarScreen;
    }

    @ControllerScope
    public WithdrawFiatScreen providesWithdrawFiatScreen() {
        return this.mWithdrawFiatScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }

    @ControllerScope
    public WithdrawalBasedLimitsErrorRouter providesWithdrawalBasedLimitsErrorRouter(WithdrawalBasedLimitsErrorControllerRouter router) {
        return router;
    }

    @ControllerScope
    public AvailableBalanceAppBarScreen providesAvailableBalanceAppBarScreen() {
        return this.mAvailableBalanceAppBarScreen;
    }

    @ControllerScope
    public SyncAccountsTask providesSyncAccountsTask(Application app) {
        return new SyncAccountsTask(app, null);
    }

    @ControllerScope
    LimitExceededTrackingContext providesSendExceededTrackingContext() {
        return LimitExceededTrackingContext.WITHDRAW;
    }
}
