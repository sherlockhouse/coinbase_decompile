package com.coinbase.android.buysell;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import com.coinbase.android.wbl.LimitExceededTrackingContext;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorControllerRouter;
import com.coinbase.android.wbl.WithdrawalBasedLimitsErrorRouter;

public class SellPresenterModule {
    private final AvailableBalanceAppBarScreen mAppBarScreen;
    private final ActionBarController mController;
    private final SellScreen mScreen;

    public SellPresenterModule(SellScreen screen, ActionBarController controller, AvailableBalanceAppBarScreen appBarScreen) {
        this.mScreen = screen;
        this.mController = controller;
        this.mAppBarScreen = appBarScreen;
    }

    @ControllerScope
    public SellScreen providesSellScreen() {
        return this.mScreen;
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
        return this.mAppBarScreen;
    }

    @ControllerScope
    public SyncAccountsTask providesSyncAccountsTask(Application app) {
        return new SyncAccountsTask(app, null);
    }

    @ControllerScope
    LimitExceededTrackingContext providesSendExceededTrackingContext() {
        return LimitExceededTrackingContext.SELL;
    }
}
