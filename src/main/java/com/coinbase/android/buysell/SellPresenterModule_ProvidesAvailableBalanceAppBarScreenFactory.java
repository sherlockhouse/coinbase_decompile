package com.coinbase.android.buysell;

import com.coinbase.android.wbl.AvailableBalanceAppBarScreen;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SellPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory implements Factory<AvailableBalanceAppBarScreen> {
    private final SellPresenterModule module;

    public SellPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory(SellPresenterModule module) {
        this.module = module;
    }

    public AvailableBalanceAppBarScreen get() {
        return provideInstance(this.module);
    }

    public static AvailableBalanceAppBarScreen provideInstance(SellPresenterModule module) {
        return proxyProvidesAvailableBalanceAppBarScreen(module);
    }

    public static SellPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory create(SellPresenterModule module) {
        return new SellPresenterModule_ProvidesAvailableBalanceAppBarScreenFactory(module);
    }

    public static AvailableBalanceAppBarScreen proxyProvidesAvailableBalanceAppBarScreen(SellPresenterModule instance) {
        return (AvailableBalanceAppBarScreen) Preconditions.checkNotNull(instance.providesAvailableBalanceAppBarScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
