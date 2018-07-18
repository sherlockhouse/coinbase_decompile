package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SellPresenterModule_ProvidesSellScreenFactory implements Factory<SellScreen> {
    private final SellPresenterModule module;

    public SellPresenterModule_ProvidesSellScreenFactory(SellPresenterModule module) {
        this.module = module;
    }

    public SellScreen get() {
        return provideInstance(this.module);
    }

    public static SellScreen provideInstance(SellPresenterModule module) {
        return proxyProvidesSellScreen(module);
    }

    public static SellPresenterModule_ProvidesSellScreenFactory create(SellPresenterModule module) {
        return new SellPresenterModule_ProvidesSellScreenFactory(module);
    }

    public static SellScreen proxyProvidesSellScreen(SellPresenterModule instance) {
        return (SellScreen) Preconditions.checkNotNull(instance.providesSellScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
