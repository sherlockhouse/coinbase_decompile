package com.coinbase.android.deposits;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class SepaDepositPresenterModule_ProvidesSepaDepositScreenFactory implements Factory<SepaDepositScreen> {
    private final SepaDepositPresenterModule module;

    public SepaDepositPresenterModule_ProvidesSepaDepositScreenFactory(SepaDepositPresenterModule module) {
        this.module = module;
    }

    public SepaDepositScreen get() {
        return provideInstance(this.module);
    }

    public static SepaDepositScreen provideInstance(SepaDepositPresenterModule module) {
        return proxyProvidesSepaDepositScreen(module);
    }

    public static SepaDepositPresenterModule_ProvidesSepaDepositScreenFactory create(SepaDepositPresenterModule module) {
        return new SepaDepositPresenterModule_ProvidesSepaDepositScreenFactory(module);
    }

    public static SepaDepositScreen proxyProvidesSepaDepositScreen(SepaDepositPresenterModule instance) {
        return (SepaDepositScreen) Preconditions.checkNotNull(instance.providesSepaDepositScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
