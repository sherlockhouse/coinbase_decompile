package com.coinbase.android.wbl;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawalBasedLimitsDialogPresenterModule_ProvidesScreenFactory implements Factory<WithdrawalBasedLimitsDialogScreen> {
    private final WithdrawalBasedLimitsDialogPresenterModule module;

    public WithdrawalBasedLimitsDialogPresenterModule_ProvidesScreenFactory(WithdrawalBasedLimitsDialogPresenterModule module) {
        this.module = module;
    }

    public WithdrawalBasedLimitsDialogScreen get() {
        return provideInstance(this.module);
    }

    public static WithdrawalBasedLimitsDialogScreen provideInstance(WithdrawalBasedLimitsDialogPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static WithdrawalBasedLimitsDialogPresenterModule_ProvidesScreenFactory create(WithdrawalBasedLimitsDialogPresenterModule module) {
        return new WithdrawalBasedLimitsDialogPresenterModule_ProvidesScreenFactory(module);
    }

    public static WithdrawalBasedLimitsDialogScreen proxyProvidesScreen(WithdrawalBasedLimitsDialogPresenterModule instance) {
        return (WithdrawalBasedLimitsDialogScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
