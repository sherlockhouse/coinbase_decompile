package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SellConfirmationPresenterModule_ProvideSellConfirmationScreenFactory implements Factory<SellConfirmationScreen> {
    private final SellConfirmationPresenterModule module;

    public SellConfirmationPresenterModule_ProvideSellConfirmationScreenFactory(SellConfirmationPresenterModule module) {
        this.module = module;
    }

    public SellConfirmationScreen get() {
        return provideInstance(this.module);
    }

    public static SellConfirmationScreen provideInstance(SellConfirmationPresenterModule module) {
        return proxyProvideSellConfirmationScreen(module);
    }

    public static SellConfirmationPresenterModule_ProvideSellConfirmationScreenFactory create(SellConfirmationPresenterModule module) {
        return new SellConfirmationPresenterModule_ProvideSellConfirmationScreenFactory(module);
    }

    public static SellConfirmationScreen proxyProvideSellConfirmationScreen(SellConfirmationPresenterModule instance) {
        return (SellConfirmationScreen) Preconditions.checkNotNull(instance.provideSellConfirmationScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
