package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BuyConfirmationPresenterModule_ProvideBuyConfirmationScreenFactory implements Factory<BuyConfirmationScreen> {
    private final BuyConfirmationPresenterModule module;

    public BuyConfirmationPresenterModule_ProvideBuyConfirmationScreenFactory(BuyConfirmationPresenterModule module) {
        this.module = module;
    }

    public BuyConfirmationScreen get() {
        return provideInstance(this.module);
    }

    public static BuyConfirmationScreen provideInstance(BuyConfirmationPresenterModule module) {
        return proxyProvideBuyConfirmationScreen(module);
    }

    public static BuyConfirmationPresenterModule_ProvideBuyConfirmationScreenFactory create(BuyConfirmationPresenterModule module) {
        return new BuyConfirmationPresenterModule_ProvideBuyConfirmationScreenFactory(module);
    }

    public static BuyConfirmationScreen proxyProvideBuyConfirmationScreen(BuyConfirmationPresenterModule instance) {
        return (BuyConfirmationScreen) Preconditions.checkNotNull(instance.provideBuyConfirmationScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
