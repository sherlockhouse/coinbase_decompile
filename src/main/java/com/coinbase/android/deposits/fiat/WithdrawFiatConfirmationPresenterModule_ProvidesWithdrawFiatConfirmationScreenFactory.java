package com.coinbase.android.deposits.fiat;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawFiatConfirmationPresenterModule_ProvidesWithdrawFiatConfirmationScreenFactory implements Factory<WithdrawFiatConfirmationScreen> {
    private final WithdrawFiatConfirmationPresenterModule module;

    public WithdrawFiatConfirmationPresenterModule_ProvidesWithdrawFiatConfirmationScreenFactory(WithdrawFiatConfirmationPresenterModule module) {
        this.module = module;
    }

    public WithdrawFiatConfirmationScreen get() {
        return provideInstance(this.module);
    }

    public static WithdrawFiatConfirmationScreen provideInstance(WithdrawFiatConfirmationPresenterModule module) {
        return proxyProvidesWithdrawFiatConfirmationScreen(module);
    }

    public static WithdrawFiatConfirmationPresenterModule_ProvidesWithdrawFiatConfirmationScreenFactory create(WithdrawFiatConfirmationPresenterModule module) {
        return new WithdrawFiatConfirmationPresenterModule_ProvidesWithdrawFiatConfirmationScreenFactory(module);
    }

    public static WithdrawFiatConfirmationScreen proxyProvidesWithdrawFiatConfirmationScreen(WithdrawFiatConfirmationPresenterModule instance) {
        return (WithdrawFiatConfirmationScreen) Preconditions.checkNotNull(instance.providesWithdrawFiatConfirmationScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
