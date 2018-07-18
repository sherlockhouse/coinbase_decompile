package com.coinbase.android.settings.gdpr;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprPrivacyRequestPresenterModule_ProvidesScreenFactory implements Factory<GdprPrivacyRequestScreen> {
    private final GdprPrivacyRequestPresenterModule module;

    public GdprPrivacyRequestPresenterModule_ProvidesScreenFactory(GdprPrivacyRequestPresenterModule module) {
        this.module = module;
    }

    public GdprPrivacyRequestScreen get() {
        return provideInstance(this.module);
    }

    public static GdprPrivacyRequestScreen provideInstance(GdprPrivacyRequestPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static GdprPrivacyRequestPresenterModule_ProvidesScreenFactory create(GdprPrivacyRequestPresenterModule module) {
        return new GdprPrivacyRequestPresenterModule_ProvidesScreenFactory(module);
    }

    public static GdprPrivacyRequestScreen proxyProvidesScreen(GdprPrivacyRequestPresenterModule instance) {
        return (GdprPrivacyRequestScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
