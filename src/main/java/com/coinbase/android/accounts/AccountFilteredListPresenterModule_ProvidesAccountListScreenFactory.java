package com.coinbase.android.accounts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountFilteredListPresenterModule_ProvidesAccountListScreenFactory implements Factory<AccountListScreen> {
    private final AccountFilteredListPresenterModule module;

    public AccountFilteredListPresenterModule_ProvidesAccountListScreenFactory(AccountFilteredListPresenterModule module) {
        this.module = module;
    }

    public AccountListScreen get() {
        return provideInstance(this.module);
    }

    public static AccountListScreen provideInstance(AccountFilteredListPresenterModule module) {
        return proxyProvidesAccountListScreen(module);
    }

    public static AccountFilteredListPresenterModule_ProvidesAccountListScreenFactory create(AccountFilteredListPresenterModule module) {
        return new AccountFilteredListPresenterModule_ProvidesAccountListScreenFactory(module);
    }

    public static AccountListScreen proxyProvidesAccountListScreen(AccountFilteredListPresenterModule instance) {
        return (AccountListScreen) Preconditions.checkNotNull(instance.providesAccountListScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
