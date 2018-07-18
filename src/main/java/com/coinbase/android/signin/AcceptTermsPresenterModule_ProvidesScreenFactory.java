package com.coinbase.android.signin;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AcceptTermsPresenterModule_ProvidesScreenFactory implements Factory<AcceptTermsScreen> {
    private final AcceptTermsPresenterModule module;

    public AcceptTermsPresenterModule_ProvidesScreenFactory(AcceptTermsPresenterModule module) {
        this.module = module;
    }

    public AcceptTermsScreen get() {
        return provideInstance(this.module);
    }

    public static AcceptTermsScreen provideInstance(AcceptTermsPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static AcceptTermsPresenterModule_ProvidesScreenFactory create(AcceptTermsPresenterModule module) {
        return new AcceptTermsPresenterModule_ProvidesScreenFactory(module);
    }

    public static AcceptTermsScreen proxyProvidesScreen(AcceptTermsPresenterModule instance) {
        return (AcceptTermsScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
