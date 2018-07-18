package com.coinbase.android.gdpr;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class GdprIntroPresenter_Factory implements Factory<GdprIntroPresenter> {
    private final Provider<GdprIntroRouter> gdprIntroRouterProvider;
    private final Provider<GdprIntroScreen> gdprIntroScreenProvider;

    public GdprIntroPresenter_Factory(Provider<GdprIntroScreen> gdprIntroScreenProvider, Provider<GdprIntroRouter> gdprIntroRouterProvider) {
        this.gdprIntroScreenProvider = gdprIntroScreenProvider;
        this.gdprIntroRouterProvider = gdprIntroRouterProvider;
    }

    public GdprIntroPresenter get() {
        return provideInstance(this.gdprIntroScreenProvider, this.gdprIntroRouterProvider);
    }

    public static GdprIntroPresenter provideInstance(Provider<GdprIntroScreen> gdprIntroScreenProvider, Provider<GdprIntroRouter> gdprIntroRouterProvider) {
        return new GdprIntroPresenter((GdprIntroScreen) gdprIntroScreenProvider.get(), (GdprIntroRouter) gdprIntroRouterProvider.get());
    }

    public static GdprIntroPresenter_Factory create(Provider<GdprIntroScreen> gdprIntroScreenProvider, Provider<GdprIntroRouter> gdprIntroRouterProvider) {
        return new GdprIntroPresenter_Factory(gdprIntroScreenProvider, gdprIntroRouterProvider);
    }

    public static GdprIntroPresenter newGdprIntroPresenter(GdprIntroScreen gdprIntroScreen, GdprIntroRouter gdprIntroRouter) {
        return new GdprIntroPresenter(gdprIntroScreen, gdprIntroRouter);
    }
}
