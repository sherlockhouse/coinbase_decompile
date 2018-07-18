package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AddPlaidAccountPresenterModule_ProvidesAddPlaidAccountScreenFactory implements Factory<AddPlaidAccountScreen> {
    private final AddPlaidAccountPresenterModule module;

    public AddPlaidAccountPresenterModule_ProvidesAddPlaidAccountScreenFactory(AddPlaidAccountPresenterModule module) {
        this.module = module;
    }

    public AddPlaidAccountScreen get() {
        return provideInstance(this.module);
    }

    public static AddPlaidAccountScreen provideInstance(AddPlaidAccountPresenterModule module) {
        return proxyProvidesAddPlaidAccountScreen(module);
    }

    public static AddPlaidAccountPresenterModule_ProvidesAddPlaidAccountScreenFactory create(AddPlaidAccountPresenterModule module) {
        return new AddPlaidAccountPresenterModule_ProvidesAddPlaidAccountScreenFactory(module);
    }

    public static AddPlaidAccountScreen proxyProvidesAddPlaidAccountScreen(AddPlaidAccountPresenterModule instance) {
        return (AddPlaidAccountScreen) Preconditions.checkNotNull(instance.providesAddPlaidAccountScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
