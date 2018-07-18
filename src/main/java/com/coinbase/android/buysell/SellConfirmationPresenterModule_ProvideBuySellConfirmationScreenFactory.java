package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SellConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory implements Factory<AbstractBuySellConfirmationScreen> {
    private final SellConfirmationPresenterModule module;

    public SellConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory(SellConfirmationPresenterModule module) {
        this.module = module;
    }

    public AbstractBuySellConfirmationScreen get() {
        return provideInstance(this.module);
    }

    public static AbstractBuySellConfirmationScreen provideInstance(SellConfirmationPresenterModule module) {
        return proxyProvideBuySellConfirmationScreen(module);
    }

    public static SellConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory create(SellConfirmationPresenterModule module) {
        return new SellConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory(module);
    }

    public static AbstractBuySellConfirmationScreen proxyProvideBuySellConfirmationScreen(SellConfirmationPresenterModule instance) {
        return (AbstractBuySellConfirmationScreen) Preconditions.checkNotNull(instance.provideBuySellConfirmationScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
