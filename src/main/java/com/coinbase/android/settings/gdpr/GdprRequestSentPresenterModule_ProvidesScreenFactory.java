package com.coinbase.android.settings.gdpr;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprRequestSentPresenterModule_ProvidesScreenFactory implements Factory<GdprRequestSentScreen> {
    private final GdprRequestSentPresenterModule module;

    public GdprRequestSentPresenterModule_ProvidesScreenFactory(GdprRequestSentPresenterModule module) {
        this.module = module;
    }

    public GdprRequestSentScreen get() {
        return provideInstance(this.module);
    }

    public static GdprRequestSentScreen provideInstance(GdprRequestSentPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static GdprRequestSentPresenterModule_ProvidesScreenFactory create(GdprRequestSentPresenterModule module) {
        return new GdprRequestSentPresenterModule_ProvidesScreenFactory(module);
    }

    public static GdprRequestSentScreen proxyProvidesScreen(GdprRequestSentPresenterModule instance) {
        return (GdprRequestSentScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
