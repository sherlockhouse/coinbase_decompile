package com.coinbase.android.gdpr;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprMarketingEmailPresenterModule_ProvidesScreenFactory implements Factory<GdprMarketingEmailScreen> {
    private final GdprMarketingEmailPresenterModule module;

    public GdprMarketingEmailPresenterModule_ProvidesScreenFactory(GdprMarketingEmailPresenterModule module) {
        this.module = module;
    }

    public GdprMarketingEmailScreen get() {
        return provideInstance(this.module);
    }

    public static GdprMarketingEmailScreen provideInstance(GdprMarketingEmailPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static GdprMarketingEmailPresenterModule_ProvidesScreenFactory create(GdprMarketingEmailPresenterModule module) {
        return new GdprMarketingEmailPresenterModule_ProvidesScreenFactory(module);
    }

    public static GdprMarketingEmailScreen proxyProvidesScreen(GdprMarketingEmailPresenterModule instance) {
        return (GdprMarketingEmailScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
