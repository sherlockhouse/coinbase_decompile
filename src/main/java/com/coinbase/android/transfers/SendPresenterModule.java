package com.coinbase.android.transfers;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.SyncAccountsTask;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import com.coinbase.android.wbl.LimitExceededTrackingContext;

public class SendPresenterModule {
    private final AvailableBalanceAppBarScreen mAppBarScreen;
    private final ActionBarController mController;
    private final SendScreen mScreen;

    public SendPresenterModule(SendScreen screen, ActionBarController controller, AvailableBalanceAppBarScreen appBarScreen) {
        this.mScreen = screen;
        this.mController = controller;
        this.mAppBarScreen = appBarScreen;
    }

    @ControllerScope
    public SendScreen providesSendScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
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
        return LimitExceededTrackingContext.SEND;
    }
}
