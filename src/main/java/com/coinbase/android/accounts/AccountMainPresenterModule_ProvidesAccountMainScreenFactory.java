package com.coinbase.android.accounts;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AccountMainPresenterModule_ProvidesAccountMainScreenFactory implements Factory<AccountMainScreen> {
    private final AccountMainPresenterModule module;

    public AccountMainPresenterModule_ProvidesAccountMainScreenFactory(AccountMainPresenterModule module) {
        this.module = module;
    }

    public AccountMainScreen get() {
        return provideInstance(this.module);
    }

    public static AccountMainScreen provideInstance(AccountMainPresenterModule module) {
        return proxyProvidesAccountMainScreen(module);
    }

    public static AccountMainPresenterModule_ProvidesAccountMainScreenFactory create(AccountMainPresenterModule module) {
        return new AccountMainPresenterModule_ProvidesAccountMainScreenFactory(module);
    }

    public static AccountMainScreen proxyProvidesAccountMainScreen(AccountMainPresenterModule instance) {
        return (AccountMainScreen) Preconditions.checkNotNull(instance.providesAccountMainScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
