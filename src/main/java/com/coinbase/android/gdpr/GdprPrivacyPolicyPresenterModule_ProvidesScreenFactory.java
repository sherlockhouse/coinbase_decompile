package com.coinbase.android.gdpr;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprPrivacyPolicyPresenterModule_ProvidesScreenFactory implements Factory<GdprPrivacyPolicyScreen> {
    private final GdprPrivacyPolicyPresenterModule module;

    public GdprPrivacyPolicyPresenterModule_ProvidesScreenFactory(GdprPrivacyPolicyPresenterModule module) {
        this.module = module;
    }

    public GdprPrivacyPolicyScreen get() {
        return provideInstance(this.module);
    }

    public static GdprPrivacyPolicyScreen provideInstance(GdprPrivacyPolicyPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static GdprPrivacyPolicyPresenterModule_ProvidesScreenFactory create(GdprPrivacyPolicyPresenterModule module) {
        return new GdprPrivacyPolicyPresenterModule_ProvidesScreenFactory(module);
    }

    public static GdprPrivacyPolicyScreen proxyProvidesScreen(GdprPrivacyPolicyPresenterModule instance) {
        return (GdprPrivacyPolicyScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
