package com.coinbase.android.accounts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountTransactionsPresenterModule_ProvideAccountDetailsScreenFactory implements Factory<AccountTransactionsScreen> {
    private final AccountTransactionsPresenterModule module;

    public AccountTransactionsPresenterModule_ProvideAccountDetailsScreenFactory(AccountTransactionsPresenterModule module) {
        this.module = module;
    }

    public AccountTransactionsScreen get() {
        return provideInstance(this.module);
    }

    public static AccountTransactionsScreen provideInstance(AccountTransactionsPresenterModule module) {
        return proxyProvideAccountDetailsScreen(module);
    }

    public static AccountTransactionsPresenterModule_ProvideAccountDetailsScreenFactory create(AccountTransactionsPresenterModule module) {
        return new AccountTransactionsPresenterModule_ProvideAccountDetailsScreenFactory(module);
    }

    public static AccountTransactionsScreen proxyProvideAccountDetailsScreen(AccountTransactionsPresenterModule instance) {
        return (AccountTransactionsScreen) Preconditions.checkNotNull(instance.provideAccountDetailsScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
