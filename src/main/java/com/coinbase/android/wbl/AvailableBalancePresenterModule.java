package com.coinbase.android.wbl;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.SyncAccountsTask;

public class AvailableBalancePresenterModule {
    private final AvailableBalanceScreen mScreen;

    public AvailableBalancePresenterModule(AvailableBalanceScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public AvailableBalanceScreen providesInvestmentTiersScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public SyncAccountsTask providesSyncAccountsTask(Application app) {
        return new SyncAccountsTask(app, null);
    }
}
