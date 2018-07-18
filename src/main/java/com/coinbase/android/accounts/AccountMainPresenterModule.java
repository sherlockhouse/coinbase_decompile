package com.coinbase.android.accounts;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.SyncAccountsTask;

public class AccountMainPresenterModule {
    private final AccountMainScreen mScreen;

    public AccountMainPresenterModule(AccountMainScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public AccountMainScreen providesAccountMainScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public SyncAccountsTask provideSyncAccountTask(Application context) {
        return new SyncAccountsTask(context, null);
    }
}
