package com.coinbase.android;

import android.view.ViewGroup;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import rx.functions.Func0;

public final class BottomNavActivityModule_ProvidesModalViewFactory implements Factory<Func0<ViewGroup>> {
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesModalViewFactory(BottomNavActivityModule module) {
        this.module = module;
    }

    public Func0<ViewGroup> get() {
        return provideInstance(this.module);
    }

    public static Func0<ViewGroup> provideInstance(BottomNavActivityModule module) {
        return proxyProvidesModalView(module);
    }

    public static BottomNavActivityModule_ProvidesModalViewFactory create(BottomNavActivityModule module) {
        return new BottomNavActivityModule_ProvidesModalViewFactory(module);
    }

    public static Func0<ViewGroup> proxyProvidesModalView(BottomNavActivityModule instance) {
        return (Func0) Preconditions.checkNotNull(instance.providesModalView(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
