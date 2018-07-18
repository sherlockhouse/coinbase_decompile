package com.coinbase.android.accounts;

import com.coinbase.android.ui.ActionBarController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountTransactionsPresenterModule_ProvidesActionBarControllerFactory implements Factory<ActionBarController> {
    private final AccountTransactionsPresenterModule module;

    public AccountTransactionsPresenterModule_ProvidesActionBarControllerFactory(AccountTransactionsPresenterModule module) {
        this.module = module;
    }

    public ActionBarController get() {
        return provideInstance(this.module);
    }

    public static ActionBarController provideInstance(AccountTransactionsPresenterModule module) {
        return proxyProvidesActionBarController(module);
    }

    public static AccountTransactionsPresenterModule_ProvidesActionBarControllerFactory create(AccountTransactionsPresenterModule module) {
        return new AccountTransactionsPresenterModule_ProvidesActionBarControllerFactory(module);
    }

    public static ActionBarController proxyProvidesActionBarController(AccountTransactionsPresenterModule instance) {
        return (ActionBarController) Preconditions.checkNotNull(instance.providesActionBarController(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
