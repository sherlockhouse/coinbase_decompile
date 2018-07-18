package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BuyPresenterModule_ProvidesBuyScreenFactory implements Factory<BuyScreen> {
    private final BuyPresenterModule module;

    public BuyPresenterModule_ProvidesBuyScreenFactory(BuyPresenterModule module) {
        this.module = module;
    }

    public BuyScreen get() {
        return provideInstance(this.module);
    }

    public static BuyScreen provideInstance(BuyPresenterModule module) {
        return proxyProvidesBuyScreen(module);
    }

    public static BuyPresenterModule_ProvidesBuyScreenFactory create(BuyPresenterModule module) {
        return new BuyPresenterModule_ProvidesBuyScreenFactory(module);
    }

    public static BuyScreen proxyProvidesBuyScreen(BuyPresenterModule instance) {
        return (BuyScreen) Preconditions.checkNotNull(instance.providesBuyScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
