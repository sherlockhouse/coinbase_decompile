package com.coinbase.android.accounts;

import android.support.v7.app.AppCompatActivity;
import com.coinbase.android.splittesting.SplitTesting;
import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AccountTransactionsRouter_Factory implements Factory<AccountTransactionsRouter> {
    private final Provider<AppCompatActivity> activityProvider;
    private final Provider<ActionBarController> controllerProvider;
    private final Provider<SplitTesting> splitTestingProvider;

    public AccountTransactionsRouter_Factory(Provider<AppCompatActivity> activityProvider, Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider) {
        this.activityProvider = activityProvider;
        this.controllerProvider = controllerProvider;
        this.splitTestingProvider = splitTestingProvider;
    }

    public AccountTransactionsRouter get() {
        return provideInstance(this.activityProvider, this.controllerProvider, this.splitTestingProvider);
    }

    public static AccountTransactionsRouter provideInstance(Provider<AppCompatActivity> activityProvider, Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new AccountTransactionsRouter((AppCompatActivity) activityProvider.get(), (ActionBarController) controllerProvider.get(), (SplitTesting) splitTestingProvider.get());
    }

    public static AccountTransactionsRouter_Factory create(Provider<AppCompatActivity> activityProvider, Provider<ActionBarController> controllerProvider, Provider<SplitTesting> splitTestingProvider) {
        return new AccountTransactionsRouter_Factory(activityProvider, controllerProvider, splitTestingProvider);
    }

    public static AccountTransactionsRouter newAccountTransactionsRouter(AppCompatActivity activity, ActionBarController controller, SplitTesting splitTesting) {
        return new AccountTransactionsRouter(activity, controller, splitTesting);
    }
}
