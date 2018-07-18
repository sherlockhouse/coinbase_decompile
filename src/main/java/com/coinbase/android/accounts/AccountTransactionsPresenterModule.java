package com.coinbase.android.accounts;

import android.app.Application;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.task.FetchAccountTask;
import com.coinbase.android.ui.ActionBarController;

public class AccountTransactionsPresenterModule {
    private final ActionBarController mController;
    private final AccountTransactionsScreen mScreen;

    public AccountTransactionsPresenterModule(AccountTransactionsScreen screen, ActionBarController controller) {
        this.mScreen = screen;
        this.mController = controller;
    }

    @ControllerScope
    public AccountTransactionsScreen provideAccountDetailsScreen() {
        return this.mScreen;
    }

    @ControllerScope
    public FetchAccountTask provideFetchAccountTask(Application context) {
        return new FetchAccountTask(context);
    }

    @ControllerScope
    public ActionBarController providesActionBarController() {
        return this.mController;
    }
}
