package com.coinbase.android.accounts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountRefreshListPresenterModule_ProvidesAccountListScreenFactory implements Factory<AccountListScreen> {
    private final AccountRefreshListPresenterModule module;

    public AccountRefreshListPresenterModule_ProvidesAccountListScreenFactory(AccountRefreshListPresenterModule module) {
        this.module = module;
    }

    public AccountListScreen get() {
        return provideInstance(this.module);
    }

    public static AccountListScreen provideInstance(AccountRefreshListPresenterModule module) {
        return proxyProvidesAccountListScreen(module);
    }

    public static AccountRefreshListPresenterModule_ProvidesAccountListScreenFactory create(AccountRefreshListPresenterModule module) {
        return new AccountRefreshListPresenterModule_ProvidesAccountListScreenFactory(module);
    }

    public static AccountListScreen proxyProvidesAccountListScreen(AccountRefreshListPresenterModule instance) {
        return (AccountListScreen) Preconditions.checkNotNull(instance.providesAccountListScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
