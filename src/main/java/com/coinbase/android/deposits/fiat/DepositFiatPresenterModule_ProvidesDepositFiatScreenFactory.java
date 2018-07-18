package com.coinbase.android.deposits.fiat;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class DepositFiatPresenterModule_ProvidesDepositFiatScreenFactory implements Factory<DepositFiatScreen> {
    private final DepositFiatPresenterModule module;

    public DepositFiatPresenterModule_ProvidesDepositFiatScreenFactory(DepositFiatPresenterModule module) {
        this.module = module;
    }

    public DepositFiatScreen get() {
        return provideInstance(this.module);
    }

    public static DepositFiatScreen provideInstance(DepositFiatPresenterModule module) {
        return proxyProvidesDepositFiatScreen(module);
    }

    public static DepositFiatPresenterModule_ProvidesDepositFiatScreenFactory create(DepositFiatPresenterModule module) {
        return new DepositFiatPresenterModule_ProvidesDepositFiatScreenFactory(module);
    }

    public static DepositFiatScreen proxyProvidesDepositFiatScreen(DepositFiatPresenterModule instance) {
        return (DepositFiatScreen) Preconditions.checkNotNull(instance.providesDepositFiatScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
