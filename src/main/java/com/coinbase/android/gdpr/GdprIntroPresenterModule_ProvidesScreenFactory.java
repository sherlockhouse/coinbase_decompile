package com.coinbase.android.gdpr;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class GdprIntroPresenterModule_ProvidesScreenFactory implements Factory<GdprIntroScreen> {
    private final GdprIntroPresenterModule module;

    public GdprIntroPresenterModule_ProvidesScreenFactory(GdprIntroPresenterModule module) {
        this.module = module;
    }

    public GdprIntroScreen get() {
        return provideInstance(this.module);
    }

    public static GdprIntroScreen provideInstance(GdprIntroPresenterModule module) {
        return proxyProvidesScreen(module);
    }

    public static GdprIntroPresenterModule_ProvidesScreenFactory create(GdprIntroPresenterModule module) {
        return new GdprIntroPresenterModule_ProvidesScreenFactory(module);
    }

    public static GdprIntroScreen proxyProvidesScreen(GdprIntroPresenterModule instance) {
        return (GdprIntroScreen) Preconditions.checkNotNull(instance.providesScreen(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
