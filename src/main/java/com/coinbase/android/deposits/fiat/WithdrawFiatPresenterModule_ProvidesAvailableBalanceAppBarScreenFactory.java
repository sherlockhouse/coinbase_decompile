package com.coinbase.android.deposits.fiat;

import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WithdrawFiatPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory implements Factory<AvailableBalanceAppBarScreen> {
    private final WithdrawFiatPresenterModule module;

    public WithdrawFiatPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory(WithdrawFiatPresenterModule module) {
        this.module = module;
    }

    public AvailableBalanceAppBarScreen get() {
        return provideInstance(this.module);
    }

    public static AvailableBalanceAppBarScreen provideInstance(WithdrawFiatPresenterModule module) {
        return proxyProvidesAvailableBalanceAppBarScreen(module);
    }

    public static WithdrawFiatPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory create(WithdrawFiatPresenterModule module) {
        return new WithdrawFiatPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory(module);
    }

    public static AvailableBalanceAppBarScreen proxyProvidesAvailableBalanceAppBarScreen(WithdrawFiatPresenterModule instance) {
        return (AvailableBalanceAppBarScreen) Preconditions.checkNotNull(instance.providesAvailableBalanceAppBarScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
