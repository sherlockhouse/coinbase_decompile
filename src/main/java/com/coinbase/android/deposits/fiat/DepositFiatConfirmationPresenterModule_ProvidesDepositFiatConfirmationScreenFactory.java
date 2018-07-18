package com.coinbase.android.deposits.fiat;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DepositFiatConfirmationPresenterModule_ProvidesDepositFiatConfirmationScreenFactory implements Factory<DepositFiatConfirmationScreen> {
    private final DepositFiatConfirmationPresenterModule module;

    public DepositFiatConfirmationPresenterModule_ProvidesDepositFiatConfirmationScreenFactory(DepositFiatConfirmationPresenterModule module) {
        this.module = module;
    }

    public DepositFiatConfirmationScreen get() {
        return provideInstance(this.module);
    }

    public static DepositFiatConfirmationScreen provideInstance(DepositFiatConfirmationPresenterModule module) {
        return proxyProvidesDepositFiatConfirmationScreen(module);
    }

    public static DepositFiatConfirmationPresenterModule_ProvidesDepositFiatConfirmationScreenFactory create(DepositFiatConfirmationPresenterModule module) {
        return new DepositFiatConfirmationPresenterModule_ProvidesDepositFiatConfirmationScreenFactory(module);
    }

    public static DepositFiatConfirmationScreen proxyProvidesDepositFiatConfirmationScreen(DepositFiatConfirmationPresenterModule instance) {
        return (DepositFiatConfirmationScreen) Preconditions.checkNotNull(instance.providesDepositFiatConfirmationScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
