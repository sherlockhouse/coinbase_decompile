package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BuyConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory implements Factory<AbstractBuySellConfirmationScreen> {
    private final BuyConfirmationPresenterModule module;

    public BuyConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory(BuyConfirmationPresenterModule module) {
        this.module = module;
    }

    public AbstractBuySellConfirmationScreen get() {
        return provideInstance(this.module);
    }

    public static AbstractBuySellConfirmationScreen provideInstance(BuyConfirmationPresenterModule module) {
        return proxyProvideBuySellConfirmationScreen(module);
    }

    public static BuyConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory create(BuyConfirmationPresenterModule module) {
        return new BuyConfirmationPresenterModule_ProvideBuySellConfirmationScreenFactory(module);
    }

    public static AbstractBuySellConfirmationScreen proxyProvideBuySellConfirmationScreen(BuyConfirmationPresenterModule instance) {
        return (AbstractBuySellConfirmationScreen) Preconditions.checkNotNull(instance.provideBuySellConfirmationScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
