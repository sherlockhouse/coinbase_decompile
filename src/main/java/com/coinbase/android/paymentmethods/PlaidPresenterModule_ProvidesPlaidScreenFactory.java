package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PlaidPresenterModule_ProvidesPlaidScreenFactory implements Factory<PlaidScreen> {
    private final PlaidPresenterModule module;

    public PlaidPresenterModule_ProvidesPlaidScreenFactory(PlaidPresenterModule module) {
        this.module = module;
    }

    public PlaidScreen get() {
        return provideInstance(this.module);
    }

    public static PlaidScreen provideInstance(PlaidPresenterModule module) {
        return proxyProvidesPlaidScreen(module);
    }

    public static PlaidPresenterModule_ProvidesPlaidScreenFactory create(PlaidPresenterModule module) {
        return new PlaidPresenterModule_ProvidesPlaidScreenFactory(module);
    }

    public static PlaidScreen proxyProvidesPlaidScreen(PlaidPresenterModule instance) {
        return (PlaidScreen) Preconditions.checkNotNull(instance.providesPlaidScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
