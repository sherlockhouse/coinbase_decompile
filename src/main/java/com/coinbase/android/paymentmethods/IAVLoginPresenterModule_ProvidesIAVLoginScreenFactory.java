package com.coinbase.android.paymentmethods;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class IAVLoginPresenterModule_ProvidesIAVLoginScreenFactory implements Factory<IAVLoginScreen> {
    private final IAVLoginPresenterModule module;

    public IAVLoginPresenterModule_ProvidesIAVLoginScreenFactory(IAVLoginPresenterModule module) {
        this.module = module;
    }

    public IAVLoginScreen get() {
        return provideInstance(this.module);
    }

    public static IAVLoginScreen provideInstance(IAVLoginPresenterModule module) {
        return proxyProvidesIAVLoginScreen(module);
    }

    public static IAVLoginPresenterModule_ProvidesIAVLoginScreenFactory create(IAVLoginPresenterModule module) {
        return new IAVLoginPresenterModule_ProvidesIAVLoginScreenFactory(module);
    }

    public static IAVLoginScreen proxyProvidesIAVLoginScreen(IAVLoginPresenterModule instance) {
        return (IAVLoginScreen) Preconditions.checkNotNull(instance.providesIAVLoginScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
