package com.coinbase.android.settings.gdpr;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PrivacyRightsPresenterModule_ProvidesScreenFactory implements Factory<PrivacyRightsScreen> {
    private final PrivacyRightsPresenterModule module;

    public PrivacyRightsPresenterModule_ProvidesScreenFactory(PrivacyRightsPresenterModule module) {
        this.module = module;
    }

    public PrivacyRightsScreen get() {
        return provideInstance(this.module);
    }

    public static PrivacyRightsScreen provideInstance(PrivacyRightsPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static PrivacyRightsPresenterModule_ProvidesScreenFactory create(PrivacyRightsPresenterModule module) {
        return new PrivacyRightsPresenterModule_ProvidesScreenFactory(module);
    }

    public static PrivacyRightsScreen proxyProvidesScreen(PrivacyRightsPresenterModule instance) {
        return (PrivacyRightsScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
