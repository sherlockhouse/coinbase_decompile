package com.coinbase.android.buysell;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BuySellSuccessPresenterModule_ProvidesBuySellSuccessScreenFactory implements Factory<BuySellSuccessScreen> {
    private final BuySellSuccessPresenterModule module;

    public BuySellSuccessPresenterModule_ProvidesBuySellSuccessScreenFactory(BuySellSuccessPresenterModule module) {
        this.module = module;
    }

    public BuySellSuccessScreen get() {
        return provideInstance(this.module);
    }

    public static BuySellSuccessScreen provideInstance(BuySellSuccessPresenterModule module) {
        return proxyProvidesBuySellSuccessScreen(module);
    }

    public static BuySellSuccessPresenterModule_ProvidesBuySellSuccessScreenFactory create(BuySellSuccessPresenterModule module) {
        return new BuySellSuccessPresenterModule_ProvidesBuySellSuccessScreenFactory(module);
    }

    public static BuySellSuccessScreen proxyProvidesBuySellSuccessScreen(BuySellSuccessPresenterModule instance) {
        return (BuySellSuccessScreen) Preconditions.checkNotNull(instance.providesBuySellSuccessScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
