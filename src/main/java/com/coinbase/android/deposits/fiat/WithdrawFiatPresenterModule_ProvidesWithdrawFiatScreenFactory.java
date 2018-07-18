package com.coinbase.android.deposits.fiat;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawFiatPresenterModule_ProvidesWithdrawFiatScreenFactory implements Factory<WithdrawFiatScreen> {
    private final WithdrawFiatPresenterModule module;

    public WithdrawFiatPresenterModule_ProvidesWithdrawFiatScreenFactory(WithdrawFiatPresenterModule module) {
        this.module = module;
    }

    public WithdrawFiatScreen get() {
        return provideInstance(this.module);
    }

    public static WithdrawFiatScreen provideInstance(WithdrawFiatPresenterModule module) {
        return proxyProvidesWithdrawFiatScreen(module);
    }

    public static WithdrawFiatPresenterModule_ProvidesWithdrawFiatScreenFactory create(WithdrawFiatPresenterModule module) {
        return new WithdrawFiatPresenterModule_ProvidesWithdrawFiatScreenFactory(module);
    }

    public static WithdrawFiatScreen proxyProvidesWithdrawFiatScreen(WithdrawFiatPresenterModule instance) {
        return (WithdrawFiatScreen) Preconditions.checkNotNull(instance.providesWithdrawFiatScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
