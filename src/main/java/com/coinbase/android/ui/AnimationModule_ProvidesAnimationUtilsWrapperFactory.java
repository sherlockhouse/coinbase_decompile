package com.coinbase.android.ui;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AnimationModule_ProvidesAnimationUtilsWrapperFactory implements Factory<AnimationUtilsWrapper> {
    private final Provider<Application> appProvider;
    private final AnimationModule module;

    public AnimationModule_ProvidesAnimationUtilsWrapperFactory(AnimationModule module, Provider<Application> appProvider) {
        this.module = module;
        this.appProvider = appProvider;
    }

    public AnimationUtilsWrapper get() {
        return provideInstance(this.module, this.appProvider);
    }

    public static AnimationUtilsWrapper provideInstance(AnimationModule module, Provider<Application> appProvider) {
        return proxyProvidesAnimationUtilsWrapper(module, (Application) appProvider.get());
    }

    public static AnimationModule_ProvidesAnimationUtilsWrapperFactory create(AnimationModule module, Provider<Application> appProvider) {
        return new AnimationModule_ProvidesAnimationUtilsWrapperFactory(module, appProvider);
    }

    public static AnimationUtilsWrapper proxyProvidesAnimationUtilsWrapper(AnimationModule instance, Application app) {
        return (AnimationUtilsWrapper) Preconditions.checkNotNull(instance.providesAnimationUtilsWrapper(app), "Cannot return null from a non-@Nullable @Provides method");
    }
}
