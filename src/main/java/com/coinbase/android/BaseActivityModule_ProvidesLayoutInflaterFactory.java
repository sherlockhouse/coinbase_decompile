package com.coinbase.android;

import android.view.LayoutInflater;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class BaseActivityModule_ProvidesLayoutInflaterFactory implements Factory<LayoutInflater> {
    private final BaseActivityModule module;

    public BaseActivityModule_ProvidesLayoutInflaterFactory(BaseActivityModule module) {
        this.module = module;
    }

    public LayoutInflater get() {
        return provideInstance(this.module);
    }

    public static LayoutInflater provideInstance(BaseActivityModule module) {
        return proxyProvidesLayoutInflater(module);
    }

    public static BaseActivityModule_ProvidesLayoutInflaterFactory create(BaseActivityModule module) {
        return new BaseActivityModule_ProvidesLayoutInflaterFactory(module);
    }

    public static LayoutInflater proxyProvidesLayoutInflater(BaseActivityModule instance) {
        return (LayoutInflater) Preconditions.checkNotNull(instance.providesLayoutInflater(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
