package com.coinbase.android;

import android.support.v7.app.AppCompatActivity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BaseActivityModule_ProvidesActivityFactory implements Factory<AppCompatActivity> {
    private final BaseActivityModule module;

    public BaseActivityModule_ProvidesActivityFactory(BaseActivityModule module) {
        this.module = module;
    }

    public AppCompatActivity get() {
        return provideInstance(this.module);
    }

    public static AppCompatActivity provideInstance(BaseActivityModule module) {
        return proxyProvidesActivity(module);
    }

    public static BaseActivityModule_ProvidesActivityFactory create(BaseActivityModule module) {
        return new BaseActivityModule_ProvidesActivityFactory(module);
    }

    public static AppCompatActivity proxyProvidesActivity(BaseActivityModule instance) {
        return (AppCompatActivity) Preconditions.checkNotNull(instance.providesActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
