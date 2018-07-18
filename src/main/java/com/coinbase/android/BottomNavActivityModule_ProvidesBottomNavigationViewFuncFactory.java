package com.coinbase.android;

import android.view.View;
import dagger.internal.Factory;
import rx.functions.Func0;

public final class BottomNavActivityModule_ProvidesBottomNavigationViewFuncFactory implements Factory<Func0<View>> {
    private final BottomNavActivityModule module;

    public BottomNavActivityModule_ProvidesBottomNavigationViewFuncFactory(BottomNavActivityModule module) {
        this.module = module;
    }

    public Func0<View> get() {
        return provideInstance(this.module);
    }

    public static Func0<View> provideInstance(BottomNavActivityModule module) {
        return proxyProvidesBottomNavigationViewFunc(module);
    }

    public static BottomNavActivityModule_ProvidesBottomNavigationViewFuncFactory create(BottomNavActivityModule module) {
        return new BottomNavActivityModule_ProvidesBottomNavigationViewFuncFactory(module);
    }

    public static Func0<View> proxyProvidesBottomNavigationViewFunc(BottomNavActivityModule instance) {
        return instance.providesBottomNavigationViewFunc();
    }
}
