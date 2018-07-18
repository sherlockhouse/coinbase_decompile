package com.coinbase.android.accounts;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.SyncAccountsTask;

public class AccountFilteredListPresenterModule {
    private final AccountListScreen mScreen;

    public AccountFilteredListPresenterModule(AccountListScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public AccountListScreen providesAccountListScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public SyncAccountsTask provideSyncAccountTask(Application context) {
        return new SyncAccountsTask(context, null);
    }
}
