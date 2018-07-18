package com.coinbase.android.deposits.fiat;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawDepositSuccessPresenterModule_ProvidesWithdrawDepositSuccessScreenFactory implements Factory<WithdrawDepositSuccessScreen> {
    private final WithdrawDepositSuccessPresenterModule module;

    public WithdrawDepositSuccessPresenterModule_ProvidesWithdrawDepositSuccessScreenFactory(WithdrawDepositSuccessPresenterModule module) {
        this.module = module;
    }

    public WithdrawDepositSuccessScreen get() {
        return provideInstance(this.module);
    }

    public static WithdrawDepositSuccessScreen provideInstance(WithdrawDepositSuccessPresenterModule module) {
        return proxyProvidesWithdrawDepositSuccessScreen(module);
    }

    public static WithdrawDepositSuccessPresenterModule_ProvidesWithdrawDepositSuccessScreenFactory create(WithdrawDepositSuccessPresenterModule module) {
        return new WithdrawDepositSuccessPresenterModule_ProvidesWithdrawDepositSuccessScreenFactory(module);
    }

    public static WithdrawDepositSuccessScreen proxyProvidesWithdrawDepositSuccessScreen(WithdrawDepositSuccessPresenterModule instance) {
        return (WithdrawDepositSuccessScreen) Preconditions.checkNotNull(instance.providesWithdrawDepositSuccessScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
